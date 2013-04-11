/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set;
import java.util.Random;

/**
 *
 * @author HANANANANANA
 */
public class Card {
   
    private CardProperty.ShapeProperty shape;
    private CardProperty.QuantityProperty quantity;
    private CardProperty.ColorProperty color;
    private CardProperty.PatternProperty pattern;
    
    public static Card createRandomizedCard() {
    	Random random = new Random();
    	SHAPE shape = SHAPE.values()[random.nextInt(3)];
    	QUANTITY quantity = QUANTITY.values()[random.nextInt(3)];
    	COLOR color = COLOR.values()[random.nextInt(3)];
    	PATTERN pattern = PATTERN.values()[random.nextInt(3)];
    	return new Card(shape, quantity, color, pattern);
    }
    
    public static Card createSet(Card card1, Card card2) {
    	SHAPE s;
    	QUANTITY q;
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
    	if (card1.quantity == card2.quantity) {
    		q = card1.quantity;
    	}
    	else {
    		if (card1.quantity.ordinal() != 0 && card2.quantity.ordinal() != 0) {
    			q = QUANTITY.values()[0];
    		}
    		else if (card1.quantity.ordinal() != 1 && card2.quantity.ordinal() != 1) {
    			q = QUANTITY.values()[1];
    		}
    		else {
    			q = QUANTITY.values()[2];
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
    	return new Card(s, q, c, p);
    }
    
    public Card(CardProperty.ShapeProperty shape, CardProperty.QuantityProperty quantity, CardProperty.ColorProperty color, CardProperty.PatternProperty pattern) {
    	this.shape = shape;
    	this.quantity = quantity;
    	this.color = color;
    	this.pattern = pattern;
    }
    
    public CardProperty.ShapeProperty getShape() {
    	return shape;
    }
    
    public CardProperty.QuantityProperty getQuantity() {
    	return quantity;
    }
    
    public CardProperty.ColorProperty getColor() {
    	return color;
    }
    
    public CardProperty.PatternProperty getPattern() {
    	return pattern;
    }
    
    public String getPropertyString() {
        return "card" + shape + quantity + color + pattern;
    }
}
