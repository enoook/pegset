/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set.controller;

import java.util.Iterator;
import java.util.List;
import peg.set.Card;
import peg.set.GameBoard;

/**
 *
 * @author thinner
 */
public abstract class GameController {

    private GameBoard gameboard;
    private List<Card> selectedCards;

    public void initGameBoard() {
    }

    private void submitSelectedCards() {
        if (checkSelectedCards()) {
            
        }
        deselectAllSelectedCards();
    }

    private boolean checkSelectedCards() {
        return false;
    }

    private void deselectAllSelectedCards() {
        Iterator<Card> iterator = selectedCards.iterator();
        while (iterator.hasNext()) {
            deselect(iterator.next());
        }
    }

    public void select(Card card) {
        selectedCards.add(card);
        if (selectedCards.size() == 3) {
            submitSelectedCards();
        }
    }
    
    public void deselect(Card card) {
        gameboard.deselect(card);
        selectedCards.remove(card);
    }
}
