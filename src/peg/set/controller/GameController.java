/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
    private List<Card> cards;
    private int correctSets;

    public void initGameBoard() {
    }

    public void select(Card card) {
        selectedCards.add(card);
        if (selectedCards.size() == CARDS_IN_A_SET) {
            submitSelectedCards();
        }
    }

    private void submitSelectedCards() {
        if (checkThatSelectedCardsFormASet()) {
            handleCorrectSelection();
        } else {
            deselectAllSelectedCards();
        }
    }

    private void handleCorrectSelection() {
        correctSets++;
        deselectAndRemoveAllSelectedCards();
    }

    private boolean checkThatSelectedCardsFormASet() {
        Card card1 = selectedCards.get(0);
        Card card2 = selectedCards.get(1);
        Card card3 = selectedCards.get(2);
        if (!(card1.getColor() == card2.getColor() && card1.getColor() == card3.getColor()) && !(card1.getColor() != card2.getColor() && card1.getColor() != card3.getColor() && card2.getColor() != card3.getColor())) {
            return false;
        } else if (!(card1.getNumberOf() == card2.getNumberOf() && card1.getNumberOf() == card3.getNumberOf()) && !(card1.getNumberOf() != card2.getNumberOf() && card1.getNumberOf() != card3.getNumberOf() && card2.getNumberOf() != card3.getNumberOf())) {
            return false;
        } else if (!(card1.getShape() == card2.getShape() && card1.getShape() == card3.getShape()) && !(card1.getShape() != card2.getShape() && card1.getShape() != card3.getShape() && card2.getShape() != card3.getShape())) {
            return false;
        } else if (!(card1.getPattern() == card2.getPattern() && card1.getPattern() == card3.getPattern()) && !(card1.getPattern() != card2.getPattern() && card1.getPattern() != card3.getPattern() && card2.getPattern() != card3.getPattern())) {
            return false;
        } else {
            return true;
        }
    }

    private void deselectAllSelectedCards() {
        Iterator<Card> iterator = selectedCards.iterator();
        while (iterator.hasNext()) {
            deselect(iterator.next());
        }
    }

    public void deselect(Card card) {
        selectedCards.remove(card);
    }

    private void deselectAndRemoveAllSelectedCards() {
        Iterator<Card> iterator = selectedCards.iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            gameboard.remove(card);
            deselect(card);
        }
    }

    public void addNewCards() {
        selectedCards.clear();// Unsafe as it is not synced with GameBoard
        for (int i = 0; i < cards.size() - 2; i++) {
            for (int j = i + 1; j < cards.size() - 1; j++) {
                for (int k = j + 1; k < cards.size(); k++) {
                    selectedCards.add(cards.get(i));
                    selectedCards.add(cards.get(j));
                    selectedCards.add(cards.get(k));

                    if (checkThatSelectedCardsFormASet()) {
                        selectedCards.clear();
                        for (int l = 0; l < CARDS_IN_A_SET; l++) {
                            Card newCard = Card.createRandomizedCard();
                            cards.add(newCard);
                            gameboard.add(newCard);
                        }
                        return;
                    }
                }
            }
        }

        Random random = new Random();
        Card randomCard1 = cards.get(random.nextInt(cards.size()));
        cards.remove(randomCard1);
        Card randomCard2 = cards.get(random.nextInt(cards.size()));
        cards.add(randomCard1);

        Card newCard = Card.createSet(randomCard1, randomCard2);
        cards.add(newCard);
        gameboard.add(newCard);

        selectedCards.clear();
    }
}
