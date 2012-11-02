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

    public static final int CARDS_IN_A_SET = 3;
    private GameBoard gameboard;
    private List<Card> selectedCards;
    private int correctSets;

    public void initGameBoard() {
    }

    public void select(Card card) {
        selectedCards.add(card);
        gameboard.select(card);
        if (selectedCards.size() == CARDS_IN_A_SET) {
            submitSelectedCards();
        }
    }

    private void submitSelectedCards() {
        if (checkThatSelectedCardsFormASet()) {
            handleCorrectSelection();
        }
        deselectAllSelectedCards();
    }

    private void handleCorrectSelection() {
        correctSets++;
    }

    private boolean checkThatSelectedCardsFormASet() {
        return false;
    }

    private void deselectAllSelectedCards() {
        Iterator<Card> iterator = selectedCards.iterator();
        while (iterator.hasNext()) {
            deselect(iterator.next());
        }
    }

    public void deselect(Card card) {
        gameboard.deselect(card);
        selectedCards.remove(card);
    }
}
