/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set.controller;

/**
 *
 * @author thinner
 */
public interface GameWinConditionHandler {
    void registerController(GameController controller);
    void selectionWasCorrect();
    void gameStarted();
}
