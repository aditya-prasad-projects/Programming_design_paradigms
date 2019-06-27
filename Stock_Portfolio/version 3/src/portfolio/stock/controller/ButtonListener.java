package portfolio.stock.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * The ButtonListener class gets the command from a map and runs it when a button is clicked.
 */

public class ButtonListener implements ActionListener {

  /**
   * Sets the map containing all the actions.
   */
  public void setButtonClickedActionMap(Map<String, Runnable> map) {
    buttonClickedActions = map;
  }

  Map<String, Runnable> buttonClickedActions;

  /**
   * Runs the command present in the map when a button is clicked.
   * @param e the event that is to be run.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {

      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
