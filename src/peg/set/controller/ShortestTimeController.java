/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set.controller;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author thinner
 */
public class ShortestTimeController implements GameWinConditionHandler{
    
    public static final long GAME_DURATION_IN_SECONDS = 2;
    private ScheduledExecutorService ses;
    private GameController controller;
    
    public ShortestTimeController() {
        ses = new ScheduledThreadPoolExecutor(1);
    }

    public void registerController(GameController controller) {
        this.controller = controller;
    }

    public void selectionWasCorrect() {
    }

    public void gameStarted() {
        if(controller == null) {
            throw new IllegalStateException("Cannot start a game without registering a controller with the WinConditionHandler");
        }
        ses.schedule(new EndGameTask(), GAME_DURATION_IN_SECONDS, TimeUnit.SECONDS);
    }
    
    private class EndGameTask implements Runnable {

        @Override
        public void run() {
            controller.endGame();
        }
    }
}
