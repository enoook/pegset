package peg.set;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thinner
 */
public abstract class CardProperty {

    private static final Random randomizer = new Random();
    private int index;
    
    protected CardProperty(Integer index) {
        this.index = index;
    }
    
    protected abstract String getNameForProperty(int index);

    protected abstract int getNumberOfPropertyNames();
    
    @Override
    public String toString() {
        return getNameForProperty(index);
    }

    public CardProperty getMatchingProperty() {
        return newCardProperty(this.getClass(), this.index);
    }

    public static CardProperty getRandomPropertyOfSameTypeAs(CardProperty property) {
        return newCardProperty(property.getClass(), randomizer.nextInt(property.getNumberOfPropertyNames()));
    }
    
    public static CardProperty getPropertyNotMatching(CardProperty property1, CardProperty property2) {
        if(property1.getClass() != property2.getClass()) {
            throw new IllegalArgumentException("The two properties must be of the same class");
        }
        
        if(property1.index == property2.index) {
            throw new IllegalArgumentException("The two properties should have different names");
        }
        
        int newIndex = 0;
        while(newIndex == property1.index || newIndex == property2.index) {
            newIndex++;
        }
        return newCardProperty(property1.getClass(), newIndex);
    }
    
    private static CardProperty newCardProperty(Class<? extends CardProperty> classInstance, int index) {
        try {
            return classInstance.getConstructor(Integer.class).newInstance(index);
        } catch (Exception ex) {
            Logger.getLogger(CardProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static class ColorProperty extends CardProperty {
        private String[] names = {"Pink", "Purple", "Orange"};

        public ColorProperty(Integer index) {
            super(index);
        }
        
        @Override
        protected String getNameForProperty(int index) {
            return names[index];
        }

        @Override
        protected int getNumberOfPropertyNames() {
            return names.length;
        }
    }
    
    public static class ShapeProperty extends CardProperty {
        private String[] names = {"Triangle", "Square", "Circle"};

        public ShapeProperty(Integer index) {
            super(index);
        }
        
        @Override
        protected String getNameForProperty(int index) {
            return names[index];
        }

        @Override
        protected int getNumberOfPropertyNames() {
            return names.length;
        }
    }
    
    public static class QuantityProperty extends CardProperty {
        private String[] names = {"One", "Two", "Three"};

        public QuantityProperty(Integer index) {
            super(index);
        }
        
        @Override
        protected String getNameForProperty(int index) {
            return names[index];
        }

        @Override
        protected int getNumberOfPropertyNames() {
            return names.length;
        }
    }
    
    public static class PatternProperty extends CardProperty {
        private String[] names = {"Blank", "Full", "Half"};

        public PatternProperty(Integer index) {
            super(index);
        }
        
        @Override
        protected String getNameForProperty(int index) {
            return names[index];
        }

        @Override
        protected int getNumberOfPropertyNames() {
            return names.length;
        }
    }
}
