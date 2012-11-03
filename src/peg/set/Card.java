/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set;
import java.util.EnumMap;
import java.util.Random;

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
    
    public static Card createRandomizedCard() {
    	Random random = new Random();
    	SHAPE shape = SHAPE.values()[random.nextInt(3)];
    	NUMBER_OF numberOf = NUMBER_OF.values()[random.nextInt(3)];
    	COLOR color = COLOR.values()[random.nextInt(3)];
    	PATTERN pattern = PATTERN.values()[random.nextInt(3)];
    	return new Card(shape, numberOf, color, pattern);
    }
    
    public static Card createSet(Card card1, Card card2) {
    	SHAPE s;
    	NUMBER_OF n;
    	COLOR c;
    	PATTERN p;
    	if (card1.shape == card2.shape) {
    		s = card1.shape;
    	}
    	else {
    		if (card1.shape.ordinal() != 0 && card2.shape.ordinal() != 0) {
    			s = SHAPE.values()[0];
    		}
    		else if (card1.shape.ordinal() != 1 && card2.shape.ordinal() != 1) {
    			s = SHAPE.values()[1];
    		}
    		else {
    			s = SHAPE.values()[2];
    		}
    	} 
    	if (card1.numberOf == card2.numberOf) {
    		n = card1.numberOf;
    	}
    	else {
    		if (card1.numberOf.ordinal() != 0 && card2.numberOf.ordinal() != 0) {
    			n = NUMBER_OF.values()[0];
    		}
    		else if (card1.numberOf.ordinal() != 1 && card2.numberOf.ordinal() != 1) {
    			n = NUMBER_OF.values()[1];
    		}
    		else {
    			n = NUMBER_OF.values()[2];
    		}
    	}
    	if (card1.color == card2.color) {
    		c = card1.color;
    	}
    	else {
    		if (card1.color.ordinal() != 0 && card2.color.ordinal() != 0) {
    			c = COLOR.values()[0];
    		}
    		else if (card1.color.ordinal() != 1 && card2.color.ordinal() != 1) {
    			c = COLOR.values()[1];
    		}
    		else {
    			c = COLOR.values()[2];
    		}
    	}
    	if (card1.pattern == card2.pattern) {
    		p = card1.pattern;
    	}
    	else {
    		if (card1.pattern.ordinal() != 0 && card2.pattern.ordinal() != 0) {
    			p = PATTERN.values()[0];
    		}
    		else if (card1.pattern.ordinal() != 1 && card2.pattern.ordinal() != 1) {
    			p = PATTERN.values()[1];
    		}
    		else {
    			p = PATTERN.values()[2];
    		}
    	}
    	return new Card(s, n, c, p);
    }
    
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
    
    public String getPropertyString() {
        return "card" + shape + numberOf + color + pattern;
    }
}
