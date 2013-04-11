/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peg.set;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 *
 * @author thinner
 */
public abstract class CardImages {

    private static Bitmap circle(int color) {
        Bitmap bm = Bitmap.createBitmap(GameBoard.CARD_WIDTH_IN_PIXELS, GameBoard.CARD_HEIGHT_IN_PIXELS, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint();
        p.setColor(color);
        c.drawCircle(bm.getWidth() / 2, bm.getHeight() / 2, bm.getHeight() / 2, p);
        return bm;
    }

    private static Bitmap rectangle(int color) {
        Bitmap bm = Bitmap.createBitmap(GameBoard.CARD_WIDTH_IN_PIXELS, GameBoard.CARD_HEIGHT_IN_PIXELS, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint();
        p.setColor(color);

        int offset = bm.getWidth() / 2 - bm.getHeight() / 2;
        c.drawRect(offset, 0, bm.getHeight(), bm.getHeight(), p);
        return bm;
    }

    private static Bitmap triangle(int color) {
        Bitmap bm = Bitmap.createBitmap(GameBoard.CARD_WIDTH_IN_PIXELS, GameBoard.CARD_HEIGHT_IN_PIXELS, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint();
        p.setColor(color);

        float[] points = new float[6];
        points[0] = bm.getWidth() / 2;
        points[1] = 0;

        points[2] = bm.getWidth() - 1;
        points[3] = bm.getHeight() - 1;

        points[4] = 0;
        points[5] = bm.getHeight() - 1;

        c.drawPoints(points, p);
        return bm;
    }

    private static Bitmap filledPattern() {
        Bitmap bm = Bitmap.createBitmap(GameBoard.CARD_WIDTH_IN_PIXELS, GameBoard.CARD_HEIGHT_IN_PIXELS, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        c.drawColor(0xFF000000);
        return bm;
    }

    private static Bitmap outlinedPattern() {
        return Bitmap.createBitmap(GameBoard.CARD_WIDTH_IN_PIXELS, GameBoard.CARD_HEIGHT_IN_PIXELS, Bitmap.Config.ARGB_8888);
    }

    private static Bitmap halvedPattern() {
        Bitmap bm = Bitmap.createBitmap(GameBoard.CARD_WIDTH_IN_PIXELS, GameBoard.CARD_HEIGHT_IN_PIXELS, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint();
        p.setColor(0xFF000000);
        c.drawRect(bm.getWidth() / 2, 0, bm.getWidth(), bm.getHeight(), p);
        return bm;
    }

    private static Bitmap mergeShapeAndPattern(Bitmap patternBitmap, Bitmap shapeBitmap) {
        // The pattern covers the entire bitmap
        // The shape has alpha 0 for pixels of the pattern that should not be included

        Bitmap toReturn = Bitmap.createBitmap(patternBitmap.getWidth(), patternBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        // Iterate all pixels in the pattern and set the alpha of that pixel to the corresponding shape pixel
        for (int x = 0; x < patternBitmap.getWidth(); x++) {
            for (int y = 0; y < patternBitmap.getHeight(); y++) {
                int color = patternBitmap.getPixel(x, y);
                int colorWithoutAlpha = 0x00FFFFFF & color;
                int alpha = shapeBitmap.getPixel(x, y) & 0xFF000000;
                color = alpha | colorWithoutAlpha;
                toReturn.setPixel(x, y, color);
            }
        }
        return toReturn;
    }

    private static Bitmap setQuantityTwo(Bitmap representationOfOneGlyph) {
        Bitmap toReturn = Bitmap.createBitmap(representationOfOneGlyph.getWidth(), representationOfOneGlyph.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(toReturn);

        int verticalMidline = representationOfOneGlyph.getWidth() / 2;
        Rect size = new Rect(0, 0, verticalMidline, representationOfOneGlyph.getHeight());
        c.drawBitmap(representationOfOneGlyph, null, size, null);
        size.offset(verticalMidline, 0);
        c.drawBitmap(representationOfOneGlyph, null, size, null);

        return toReturn;
    }

    private static Bitmap setQuantityThree(Bitmap representationOfOneGlyph) {
        Bitmap toReturn = Bitmap.createBitmap(representationOfOneGlyph.getWidth(), representationOfOneGlyph.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(toReturn);

        int horizontalMidline = representationOfOneGlyph.getHeight() / 2;
        Rect size = new Rect(0, 0, representationOfOneGlyph.getWidth(), horizontalMidline);
        c.drawBitmap(setQuantityTwo(representationOfOneGlyph), null, size, null);

        size.offset(0, horizontalMidline); // Hur skalas den tro? Stretchas den är det fel här
        c.drawBitmap(representationOfOneGlyph, null, size, null);

        return toReturn;
    }
    
    
    public static Bitmap oneHollowCircle(int color) {
        return circle(color);
    }
    
    public static Bitmap oneHalvedCircle(int  color) {
        Bitmap circle = circle(color);
        
        return null;
    }
}
