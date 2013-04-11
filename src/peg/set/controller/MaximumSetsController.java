/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set.controller;

/**
 *
 * @author thinner
 */
public class MaximumSetsController implements GameWinConditionHandler {

    private static final int SETS_TO_FIND = 60;
    private GameController controller;
    
    public void registerController(GameController controller) {
        this.controller = controller;
    }

    public void selectionWasCorrect() {
        if(controller.getNumberOfSetsFound() == SETS_TO_FIND) {
            controller.endGame();
        }
    }

    public void gameStarted() {
        if(controller == null) {
            throw new IllegalStateException("Cannot start a game without registering a controller with the WinConditionHandler");
        }
    }
    
}
