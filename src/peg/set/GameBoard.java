/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.Map;
import java.util.Queue;
import peg.set.controller.GameController;

/**
 * @author thinner
 */
public class GameBoard extends TableLayout implements OnClickListener {
    
    public static final int CARDS_IN_A_COLUMN = 3;
    public static final int CARD_WIDTH_IN_PIXELS = 40;
    public static final int CARD_HEIGHT_IN_PIXELS = (int) Math.round(40 / 1.618);
    private static final int BUTTON_TAG_IS_SELECTED = 0;
    private static final int BUTTON_TAG_POSITION = 1;
    private static final int BUTTON_TAG_CARD = 2;
    private Map<Point, ImageView> buttonPositions;
    private Map<String, Bitmap> cardImageCache;
    private Queue<Point> emptyLocations;
    private GameController controller;
    
    public GameBoard(Context context) {
        super(context);
    }
    
    private void layout(Context context) {
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.FILL_PARENT);
        this.setLayoutParams(lp);
        
        int numberOfCardsInRow = 0, numberOfRows = 0;
        TableRow row = new TableRow(context);
        for (int i = 0; i < 12; i++) {
            ImageView button = new ImageView(context);
            button.setOnClickListener(this);
            Point pos = new Point(numberOfCardsInRow, numberOfRows);
            button.setTag(BUTTON_TAG_IS_SELECTED, null);
            button.setTag(BUTTON_TAG_POSITION, pos);
            
            buttonPositions.put(pos, button);
            emptyLocations.offer(pos);
            
            row.addView(button);
            if (++numberOfCardsInRow == CARDS_IN_A_COLUMN) {
                numberOfRows++;
                this.addView(row);
                row = new TableRow(context);
            }
        }
    }
    
    public void add(Card card) {
        if (!emptyLocations.isEmpty()) {
            Point location = emptyLocations.poll();
            ImageView button = buttonPositions.get(location);
            button.setImageBitmap(getOrCreateCardImage(card));
            button.setTag(BUTTON_TAG_CARD, card);
        } else {
            throw new IllegalStateException("No empty card slots");
        }
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
    
    public void remove(Card card) {
        ImageView button = getButtonFromCard(card);
        deselectButton(button);
        emptyLocations.add((Point) button.getTag(BUTTON_TAG_POSITION));
    }
    
    private ImageView getButtonFromCard(Card card) {
        for (ImageView buttonCandidate : buttonPositions.values()) {
            if (buttonCandidate.getTag(BUTTON_TAG_CARD) == card) {
                return buttonCandidate;
            }
        }
        return null;
    }
    
    public void onClick(View button) {
        if (button.getTag(BUTTON_TAG_IS_SELECTED) == null) {
            selectButton(button);
        } else {
            deselectButton(button);
        }
    }
    
    private void selectButton(View button) {
        button.setTag(BUTTON_TAG_IS_SELECTED, this);
        button.setBackgroundColor(0xFF09cdda);
        controller.select((Card) button.getTag(BUTTON_TAG_CARD));
    }
    
    private void deselectButton(View button) {
        button.setTag(BUTTON_TAG_IS_SELECTED, null);
        button.setBackgroundColor(0xFFFFFFFF);
        controller.deselect((Card) button.getTag(BUTTON_TAG_CARD));
    }
}
