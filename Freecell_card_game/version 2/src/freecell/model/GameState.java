package freecell.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.Constants;

import static java.lang.Math.pow;

/**
 * Represents the gameState of the Freecell game.
 */
class GameState implements GameRepresentation {
  private Map<Integer, List<Cards>> foundationPile;
  private Map<Integer, List<Cards>> openPile;
  private Map<Integer, List<Cards>> cascadePile;

  /**
   * Instantiates the different piles in the gameState.
   */
  GameState() {
    foundationPile = new HashMap<>();
    openPile = new HashMap<>();
    cascadePile = new HashMap<>();
    setOpenPileSize(4);
    setCascadePileSize(8);
    setFoundationPileSize(4);
  }

  /**
   * Returns the foundation pile.
   *
   * @return the foundation pile.
   */
  @Override
  public Map<Integer, List<Cards>> getFoundationPile() {
    return foundationPile;
  }

  /**
   * Sets the foundation pile.
   *
   * @param foundationPile the value to set the current foundation pile.
   */
  @Override
  public void setFoundationPile(Map<Integer, List<Cards>> foundationPile) {
    this.foundationPile = foundationPile;
  }

  /**
   * A getter for the openPile.
   *
   * @return the openPile.
   */
  @Override
  public Map<Integer, List<Cards>> getOpenPile() {
    return openPile;
  }

  /**
   * A setter for the openPile.
   *
   * @param openPile The value to set the openPile.
   */
  @Override
  public void setOpenPile(Map<Integer, List<Cards>> openPile) {
    this.openPile = openPile;
  }

  /**
   * A getter for the cascadePile.
   *
   * @return the cascadePile.
   */
  @Override
  public Map<Integer, List<Cards>> getCascadePile() {
    return cascadePile;
  }

  /**
   * A setter for the cascadePile.
   *
   * @param cascadePile The value to set the cascadePile
   */
  @Override
  public void setCascadePile(Map<Integer, List<Cards>> cascadePile) {
    this.cascadePile = cascadePile;
  }

  /**
   * Used to set the cascadePileSize.
   *
   * @param c the number of cascadePiles in the game.
   */
  @Override
  public void setCascadePileSize(int c) {
    if (c < Constants.MIN_CASCADE_PILES) {
      throw new IllegalArgumentException("Pile size cannot be lower than 4");
    }
    cascadePile = new HashMap<>();
    for (int i = 0; i < c; i++) {
      cascadePile.put(i, new ArrayList<Cards>());
    }
  }

  /**
   * Used to addCards to cascadePile when the game begins.
   *
   * @param c         the card to be added.
   * @param pileIndex The index in the cascadePile where the card is to be added.
   */
  @Override
  public void addCardCascadePile(Cards c, int pileIndex) {
    List<Cards> tempList;
    if (null != cascadePile.get(pileIndex)) {
      tempList = cascadePile.get(pileIndex);
    } else {
      tempList = new ArrayList<>();
    }
    tempList.add(c);
    cascadePile.put(pileIndex, tempList);
  }

  /**
   * Used to set the OpenPileSize.
   *
   * @param c the number of openPiles.
   */
  @Override
  public void setOpenPileSize(int c) {
    if (c < Constants.MIN_OPEN_PILES) {
      throw new IllegalArgumentException("Pile size cannot be lower than 1");
    }
    openPile = new HashMap<>();
    for (int i = 0; i < c; i++) {
      openPile.put(i, new ArrayList<Cards>());
    }
  }

  /**
   * Used to set the foundationPileSize.
   *
   * @param c the number of foundationOpenPiles.
   */
  private void setFoundationPileSize(int c) {
    foundationPile = new HashMap<>();
    for (int i = 0; i < c; i++) {
      foundationPile.put(i, new ArrayList<Cards>());
    }
  }

  /**
   * Getter for the CascadePileSize.
   *
   * @return the number of CascadePiles.
   */
  @Override
  public int getCascadePileSize() {
    return cascadePile.size();
  }

  @Override
  public String toString() {
    String ret = "";
    if (numberOfCards() == 0) {
      return ret;
    }
    int temp;
    for (Map.Entry<Integer, List<Cards>> entry : foundationPile.entrySet()) {
      temp = entry.getKey() + 1;
      ret = ret + "F" + temp + ": " + entry.getValue().toString() + "\n";
    }
    for (Map.Entry<Integer, List<Cards>> entry : openPile.entrySet()) {
      temp = entry.getKey() + 1;
      ret = ret + "O" + temp + ": " + entry.getValue().toString() + "\n";
    }
    for (Map.Entry<Integer, List<Cards>> entry : cascadePile.entrySet()) {
      temp = entry.getKey() + 1;
      ret = ret + "C" + temp + ": " + entry.getValue().toString() + "\n";
    }
    ret = ret.replace("[", "").replace("]", "")
            .replace(" \n", "\n").trim();
    return ret;
  }

  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise.
   */
  @Override
  public boolean isGameOver() {
    for (Map.Entry<Integer, List<Cards>> entry : cascadePile.entrySet()) {
      if (entry.getValue().size() != 0) {
        return false;
      }
    }
    for (Map.Entry<Integer, List<Cards>> entry : foundationPile.entrySet()) {
      if (entry.getValue().size() != 13) {
        return false;
      }
    }
    for (Map.Entry<Integer, List<Cards>> entry : openPile.entrySet()) {
      if (entry.getValue().size() != 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks what kind of pile it is.
   *
   * @param type the type of pile to decide.
   * @return the type of pile.
   */
  private Map<Integer, List<Cards>> pileMapping(PileType type) {
    if (type == PileType.CASCADE) {
      return cascadePile;
    } else if (type == PileType.FOUNDATION) {
      return foundationPile;
    } else {
      return openPile;
    }
  }

  /**
   * The total number of cards in all the three piles.
   *
   * @return the number of cards in all the three piles.
   */
  private int numberOfCards() {
    int count = 0;
    for (Map.Entry<Integer, List<Cards>> entry : foundationPile.entrySet()) {
      count = count + entry.getValue().size();
    }
    for (Map.Entry<Integer, List<Cards>> entry : cascadePile.entrySet()) {
      count = count + entry.getValue().size();
    }
    for (Map.Entry<Integer, List<Cards>> entry : openPile.entrySet()) {
      count = count + entry.getValue().size();
    }
    return count;
  }

  /**
   * Checks if a move is valid.
   *
   * @param source         the source PileType from which the card is to be moved.
   * @param pileNumber     The pile number in the source PileType.
   * @param cardIndex      The cardIndex in the pile which is to be moved.
   * @param destination    the destination PileType to which the card is to be moved.
   * @param destPileNumber The pile number in the destination PileType.
   */
  private void checkValidMove(PileType source, int pileNumber, int cardIndex,
                              PileType destination, int destPileNumber) {
    if (numberOfCards() == 0) {
      throw new IllegalStateException("Start the game before making a move");
    }
    if (destPileNumber < 0 || destPileNumber >= pileMapping(destination).size()) {
      throw new IllegalArgumentException("Invalid source pile numbers");
    }
    if (pileNumber < 0 || pileNumber >= pileMapping(source).size()) {
      throw new IllegalArgumentException("Invalid destination pile number");
    }

    if (destination == PileType.OPEN && pileMapping(destination).get(destPileNumber).size() == 1) {
      throw new IllegalArgumentException("Open pile can have only 1 card");
    }
  }

  /**
   * Move a card from the given source pile to the given destination pile, if the move is valid. If
   * a card is taken from a position and put back in the same position, it throws an exception. but
   * the card's position is not changed.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible {@link PileType})
   * @throws IllegalStateException    if a move is attempted before the game has starts
   */
  @Override
  public void moveCards(PileType source, int pileNumber, int cardIndex,
                        PileType destination, int destPileNumber)
          throws IllegalStateException, IllegalArgumentException {

    checkValidMove(source, pileNumber, cardIndex, destination, destPileNumber);
    if (pileMapping(source).get(pileNumber).size() - 1 != cardIndex) {
      throw new IllegalArgumentException("Only last card can be moved");
    }
    if (destination == PileType.FOUNDATION) {
      moveToFoundation(source, pileNumber, cardIndex, destPileNumber);
    } else if (destination == PileType.CASCADE) {
      moveToCascade(source, pileNumber, cardIndex, destPileNumber);
    } else if (destination == PileType.OPEN) {
      moveToOpen(source, pileNumber, cardIndex, destPileNumber);
    }
  }

  /**
   * Used to move from any pile to OpenPile.
   *
   * @param source         the source PileType from which the card is to be moved.
   * @param pileNumber     The pile number in the source PileType.
   * @param cardIndex      The cardIndex in the pile which is to be moved.
   * @param destPileNumber The pile number in the destination PileType.
   */
  private void moveToOpen(PileType source, int pileNumber, int cardIndex,
                          int destPileNumber) {
    Cards toMoveCard = pileMapping(source).get(pileNumber).remove(cardIndex);
    openPile.get(destPileNumber).add(toMoveCard);
  }

  private void moveToCascade(PileType source, int pileNumber, int cardIndex,
                             int destPileNumber) {
    Cards toMoveCard = pileMapping(source).get(pileNumber).get(cardIndex);
    if (cascadePile.get(destPileNumber).size() == 0) {
      cascadePile.get(destPileNumber).add(toMoveCard);
      pileMapping(source).get(pileNumber).remove(cardIndex);
      return;
    }
    Cards onMoveCard = cascadePile.get(destPileNumber)
            .get(cascadePile.get(destPileNumber).size() - 1);
    if (toMoveCard.getColor() == onMoveCard.getColor() || toMoveCard.getValue().getOrder()
            + 1 != onMoveCard.getValue().getOrder()) {
      throw new IllegalArgumentException("Invalid move");
    }
    cascadePile.get(destPileNumber).add(toMoveCard);
    pileMapping(source).get(pileNumber).remove(cardIndex);
  }

  private void moveToFoundation(PileType source, int pileNumber, int cardIndex,
                                int destPileNumber) {
    Cards toMoveCard = pileMapping(source).get(pileNumber).get(cardIndex);
    if (foundationPile.get(destPileNumber).size() == 0) {
      if (toMoveCard.getValue().getOrder() == 1) {
        foundationPile.get(destPileNumber).add(toMoveCard);
        pileMapping(source).get(pileNumber).remove(cardIndex);
        return;
      }
      throw new IllegalArgumentException("Only Ace can be added to empty foundation");
    }
    Cards onMoveCard = foundationPile.get(destPileNumber)
            .get(foundationPile.get(destPileNumber).size() - 1);
    if (toMoveCard.getColor() != onMoveCard.getColor() || toMoveCard.getValue().getOrder()
            != onMoveCard.getValue().getOrder() + 1) {
      throw new IllegalArgumentException("Invalid move");
    }
    foundationPile.get(destPileNumber).add(toMoveCard);
    pileMapping(source).get(pileNumber).remove(cardIndex);
  }

  @Override
  public void emptyPiles() {
    for (Map.Entry<Integer, List<Cards>> entry : foundationPile.entrySet()) {
      entry.setValue(new ArrayList<>());
    }
    for (Map.Entry<Integer, List<Cards>> entry : openPile.entrySet()) {
      entry.setValue(new ArrayList<>());
    }
    for (Map.Entry<Integer, List<Cards>> entry : cascadePile.entrySet()) {
      entry.setValue(new ArrayList<>());
    }
  }

  @Override
  public void multiMove(PileType source, int pileNumber, int cardIndex,
                        PileType destination, int destPileNumber)
          throws IllegalStateException, IllegalArgumentException {
    if (!(source == PileType.CASCADE && destination == PileType.CASCADE)) {
      moveCards(source, pileNumber, cardIndex, destination, destPileNumber);
      return;
    }
    checkValidMove(source, pileNumber, cardIndex, destination, destPileNumber);

    List<Cards> moveList = new ArrayList<>();
    for (int i = cardIndex; i < cascadePile.get(pileNumber).size(); i++) {
      moveList.add(cascadePile.get(pileNumber).get(i));
    }

    checkValidMultiMove(moveList, destPileNumber);
    for (int i = 0; i < moveList.size(); i++) {
      cascadePile.get(destPileNumber).add(moveList.get(i));
    }

    for (int i = cascadePile.get(pileNumber).size() - 1; i >= cardIndex; i--) {
      cascadePile.get(pileNumber).remove(i);
    }
  }

  private void checkValidMultiMove(List<Cards> moveList, int destPileNumber) {
    for (int i = 1; i < moveList.size(); i++) {
      if (moveList.get(i).getColor() == moveList.get(i - 1).getColor()) {
        throw new IllegalArgumentException("The colours must be alternating!");
      }
      if (moveList.get(i).getValue().getOrder() != moveList.get(i - 1).getValue().getOrder() - 1) {
        throw new IllegalArgumentException("The numbers must be in decending order");
      }
    }
    int n = 0;
    for (int j = 0; j < openPile.size(); j++) {
      if (openPile.get(j).size() == 0) {
        n++;
      }
    }
    int k = 0;
    for (int j = 0; j < cascadePile.size(); j++) {
      if (cascadePile.get(j).size() == 0) {
        k++;
      }
    }
    if (moveList.size() > (n + 1) * pow(2, k)) {
      throw new IllegalArgumentException("Not enough empty open and cascade piles!");
    }
    if (cascadePile.get(destPileNumber).get(cascadePile.get(destPileNumber).size() - 1).getColor()
            == moveList.get(0).getColor() || cascadePile.get(destPileNumber).get(cascadePile
            .get(destPileNumber).size() - 1).getValue().getOrder() != moveList.get(0).getValue()
            .getOrder() + 1) {
      throw new IllegalArgumentException("Can't be added to destination card!");
    }
  }
}
