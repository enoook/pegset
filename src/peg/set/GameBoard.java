/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thinner
 */
public class GameBoard extends View{
    
    public static final int CARDS_PER_COLUMN = 3;
    public static final int CARD_WIDTH_IN_PIXELS = 40;
    public static final int CARD_HEIGHT_IN_PIXELS = (int) Math.round(40/1.618);
    private int verticalSpaceBetweenCards;
    private int horizontalSpaceBetweenCards;
    private List<Card> cards;
    private Map<String, Bitmap> cardImageCache;

    public GameBoard(Context context) {
        super(context);
    }
    
    public void select(Card card) {
        
    }
    
    public void deselect(Card card) {
        
    }
    
    public void add(Card card) {
    	
    }
    
    private Bitmap getOrCreateCardImage(Card card) {
        Bitmap image = cardImageCache.get(card.getPropertyString());
        if(image == null) {
            image = createAndCacheCardImage(card);
        }
        return image;
    }
    
    private Bitmap createAndCacheCardImage(Card card) {
        Bitmap image = BitmapFactory.decodeFile(card.getPropertyString() + ".png");
        cardImageCache.put(card.getPropertyString(), image);
        return image;
    }
    
    public List<Card> getCards() {
    	return cards;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        int cardImageXCoord = 0, cardImageYCoord = 0;
        int columnToDrawIn = 0;
        for(Card card : cards) {
            // Draw image
            
            if(++columnToDrawIn == CARDS_PER_COLUMN) {
                columnToDrawIn = 0;
                cardImageXCoord = 0;
                cardImageYCoord += verticalSpaceBetweenCards + CARD_HEIGHT_IN_PIXELS;
            } else {
                cardImageXCoord += horizontalSpaceBetweenCards + CARD_WIDTH_IN_PIXELS;                
            }
        }
    }
    
    
    
}
