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
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.Map;
import peg.set.controller.GameController;

/**
 *
 * @author thinner
 */
public class GameBoard extends TableLayout implements OnClickListener {

    public static final int CARDS_IN_A_COLUMN = 3;
    public static final int CARD_WIDTH_IN_PIXELS = 40;
    public static final int CARD_HEIGHT_IN_PIXELS = (int) Math.round(40 / 1.618);
    private Map<Point, Button> buttonPositions;
    private Map<String, Bitmap> cardImageCache;
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
            Button button = new Button(context);
            button.setOnClickListener(this);
            Point pos = new Point(numberOfCardsInRow, numberOfRows);
            button.setTag(0, null);
            button.setTag(1, pos);
            
            buttonPositions.put(pos, button);
            
            row.addView(button);
            if (++numberOfCardsInRow == CARDS_IN_A_COLUMN) {
                numberOfRows++;
                this.addView(row);
                row = new TableRow(context);
            }
        }
    }

    public void select(Card card) {
    }

    public void deselect(Card card) {
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

    public void onClick(View button) {
        if(button.getTag(0) == null) {
            button.setTag(0, this);
            button.setBackgroundColor(0xFF09cdda);
            controller.select(null);
        } else {
            button.setTag(0, null);
            button.setBackgroundColor(0xFFFFFFFF);
            controller.deselect(null);
        }
    }
}
