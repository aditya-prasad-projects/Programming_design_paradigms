package freecell.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import freecell.model.FreecellOperations;
import freecell.model.PileType;

/**
 * A Controller for the FreecellOperations type model.
 *
 */
public class FreecellController implements IFreecellController {
  private final Readable in;
  private final Appendable out;

  /**
   * Initialises the Controller with the Readable and appendable.
   * @param rd the readable object.
   * @param ap the appendable object.
   */
  public FreecellController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    this.in = rd;
    this.out = ap;
  }


  /**
   * Used to play a game of Freecell.
   * Depending on the model passed, it runs either the Freecell model
   * or the FreecellMultiMove model.
   * @param deck    the deck to be used to play this game
   * @param model   the model for the game
   * @param shuffle shuffle the deck if true, false otherwise
   * @throws IllegalArgumentException If deck and model is null.
   * @throws IllegalStateException if it cant access the appendable object.
   */
  @Override
  public void playGame(List deck, FreecellOperations model, boolean shuffle)
          throws IllegalArgumentException, IllegalStateException {
    if (deck == null || model == null) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    try {
      model.startGame(deck, shuffle);
    } catch (IllegalArgumentException e) {
      try {
        out.append("Invalid deck");
        return;
      } catch (IOException f) {
        throw new IllegalStateException();
      }
    }
    try {
      this.out.append(model.getGameState() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    Scanner sc = new Scanner(this.in);
    String source = "";
    String destination = "";
    String cardIndex = "";
    while (true) {
      source = getPilesInput(sc);
      if (source.equals("quit")) {
        return;
      }
      cardIndex = getCardIndexInput(sc);
      if (cardIndex.equals("quit")) {
        return;
      }
      destination = getPilesInput(sc);
      if (destination.equals("quit")) {
        return;
      }
      makeMove(source, cardIndex, destination, model);
    }
  }

  /**
   * Used to call the move method of the respective model and append the output appropriately.
   * @param source The source PileType.
   * @param cardIndex The card index in the source pile type.
   * @param destination the destination PileType.
   * @param model The model for which the move is to be called.
   */
  private void makeMove(String source, String cardIndex, String destination,
                        FreecellOperations model) {
    try {
      model.move(returnPiletype(source.charAt(0)),
              Integer.parseInt(source.substring(1)) - 1,
              Integer.parseInt(cardIndex) - 1,
              returnPiletype(destination.charAt(0)),
              Integer.parseInt(destination.substring(1)) - 1);
      try {
        this.out.append(model.getGameState() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException();
      }

      if (model.isGameOver()) {
        try {
          out.append(model.getGameState() + "\n");
          out.append("Game over.\n");
        } catch (IOException e) {
          throw new IllegalStateException();
        }
      }
    } catch (IllegalArgumentException e) {
      try {
        out.append("Invalid move. Try again. " + e.getMessage() + "\n");
      } catch (IOException f) {
        throw new IllegalStateException();
      }
    }
  }

  /**
   * Used to quit if q or Q is called.
   * @return quit indicating success.
   */
  private String quitInput() {
    try {
      out.append("Game quit prematurely.\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    return "quit";
  }

  /**
   * Used to parse the source pile input.
   * @param sc The readable object.
   * @return quit indicating success.
   */
  private String getPilesInput(Scanner sc) {
    String source = "";
    while (true) {
      if (sc.hasNext()) {
        source = sc.next();

        if (source.equals("q") || source.equals("Q")) {
          return quitInput();
        }
        if ((source.charAt(0) == 'C' || source.charAt(0) == 'F'
                || source.charAt(0) == 'O') && source.substring(1).matches("-?\\d+")) {
          break;
        }
      } else {
        return "quit";
      }
    }
    return source;
  }

  /**
   * Used to parse the CardIndex.
   * @param sc The readable object
   * @return  quit indicating success.
   */
  private String getCardIndexInput(Scanner sc) {
    String cardIndex = "";
    while (true) {
      if (sc.hasNext()) {
        cardIndex = sc.next();

        if (cardIndex.equals("q") || cardIndex.equals("Q")) {
          return quitInput();
        }
        if (cardIndex.matches("-?\\d+")) {
          break;
        }
      } else {
        return "quit";
      }
    }
    return cardIndex;
  }

  /**
   * Returns the pile type depending on the char parameter.
   * @param c the Source and destination pile type to be decided.
   * @return the pile type of the characxter passed.
   */
  private PileType returnPiletype(char c) {
    if (c == 'O') {
      return PileType.OPEN;
    } else if (c == 'C') {
      return PileType.CASCADE;
    } else if (c == 'F') {
      return PileType.FOUNDATION;
    } else {
      return null;
    }
  }
}
