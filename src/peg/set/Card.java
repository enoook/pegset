/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set;

/**
 *
 * @author HANANANANANA
 */
public class Card {
    public static enum SHAPE {TRIANGLE, SQUARE, CIRCLE};
    public static enum NUMBER_OF {ONE, TWO, THREE};
    public static enum COLOR {PINK, PURPLE, ORANGE};
    public static enum PATTERN {BLANK, FULL, HALF};
    
    private SHAPE shape;
    private NUMBER_OF numberOf;
    private COLOR color;
    private PATTERN pattern;
    
    public Card(SHAPE shape, NUMBER_OF numberOf, COLOR color, PATTERN pattern) {
    	this.shape = shape;
    	this.numberOf = numberOf;
    	this.color = color;
    	this.pattern = pattern;
    }
    
    public SHAPE getShape() {
    	return shape;
    }
    
    public NUMBER_OF getNumberOf() {
    	return numberOf;
    }
    
    public COLOR getColor() {
    	return color;
    }
    
    public PATTERN getPattern() {
    	return pattern;
    }
}
