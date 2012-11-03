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
public class GameBoard extends View {

    public static final int CARDS_PER_COLUMN = 3;
    public static final int CARD_WIDTH_IN_PIXELS = 40;
    public static final int CARD_HEIGHT_IN_PIXELS = (int) Math.round(40 / 1.618);
    private int verticalSpaceBetweenCards;
    private int horizontalSpaceBetweenCards;
    private List<Card> cards;
    private List<Card> selectedCards;
    private Map<String, Bitmap> cardImageCache;

    public GameBoard(Context context) {
        super(context);
    }

    public void select(Card card) {
        selectedCards.add(card);
    }

    public void deselect(Card card) {
        selectedCards.remove(card);
    }

    private Bitmap getOrCreateCardImage(Card card) {
        Bitmap image = cardImageCache.get(card.getPropertyString());
        if (image == null) {
            image = createAndCacheCardImage(card);
        }
        return image;
    }

    private Bitmap createAndCacheCardImage(Card card) {
        Bitmap image = readCardPNGFromFile(card);
        cacheCardImage(card, image);
        return image;
    }

    private Bitmap readCardPNGFromFile(Card card) {
        return BitmapFactory.decodeFile(card.getPropertyString() + ".png");
    }

    private void cacheCardImage(Card card, Bitmap image) {
        cardImageCache.put(card.getPropertyString(), image);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        
        int cardImageXCoord = 0, cardImageYCoord = 0;
        int columnToDrawIn = 0;
        for (Card card : cards) {
            canvas.drawBitmap(getOrCreateCardImage(card), cardImageXCoord, cardImageYCoord, null);
            if (selectedCards.contains(card)) {

            }
            
            if (++columnToDrawIn == CARDS_PER_COLUMN) {
                columnToDrawIn = 0;
                cardImageXCoord = 0;
                cardImageYCoord += verticalSpaceBetweenCards + CARD_HEIGHT_IN_PIXELS;
            } else {
                cardImageXCoord += horizontalSpaceBetweenCards + CARD_WIDTH_IN_PIXELS;
            }
        }
    }
}
