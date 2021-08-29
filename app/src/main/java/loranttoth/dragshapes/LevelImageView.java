package loranttoth.dragshapes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import java.util.Vector;

import loranttoth.dragshapes.data.ImageData;
import loranttoth.dragshapes.data.ShapeData;

/**
 * TODO: document your custom view class.
 */
public class LevelImageView extends View {
        Context context;
        Paint paint;

        Paint paintbig;
        Paint paintbig2;

        ImageData imageData = null;

        int shapeColor = Color.LTGRAY;
        int shapeColor2 = Color.DKGRAY;

        int level = 0;

        public LevelImageView(Context c, AttributeSet attrs) {
            super(c, attrs);
            context = c;
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.BLUE);

            paintbig = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintbig.setColor(shapeColor);
            paintbig.setStyle(Paint.Style.FILL);

            paintbig2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintbig2.setColor(shapeColor2);
            paintbig2.setStyle(Paint.Style.STROKE);
            paintbig2.setStrokeWidth(10);
        }

        //public void setParent(GameActivity parent) {
        //    this.parent = parent;
        // }

        public void setLevel(int level_) {
            level = level_;
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int maxx = getWidth();
            int maxy = getHeight();
            int w = getWidth();
            int h = getHeight();
            if (imageData == null && maxx > 0 && maxy > 0){
                int min = Math.min(w, h);
                ShapeData.unit = min/13;
                //unit = 100;
                makeShapeCoords();
            }

            drawBigShape(canvas);
        }


        void drawBigShape(Canvas canvas) {
            if (canvas == null)
                return;
            Path path;
            canvas.save();
            path = new Path();
            path.moveTo(imageData.shapeCoords[0].x, imageData.shapeCoords[0].y);
            for (int i = 1; i < imageData.shapeCoords.length; i++) {
                path.lineTo(imageData.shapeCoords[i].x, imageData.shapeCoords[i].y);
            }
            path.lineTo(imageData.shapeCoords[0].x, imageData.shapeCoords[0].y);
            path.close();
            canvas.drawPath(path, paintbig2);
            canvas.restore();

            canvas.save();
            path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(imageData.shapeCoords[0].x, imageData.shapeCoords[0].y);
            for (int i = 1; i < imageData.shapeCoords.length; i++) {
                path.lineTo(imageData.shapeCoords[i].x, imageData.shapeCoords[i].y);
            }
            path.lineTo(imageData.shapeCoords[0].x, imageData.shapeCoords[0].y);
            path.close();
            canvas.drawPath(path, paintbig);
            canvas.restore();
        }

        void makeShapeCoords() {

            Random rnd = new Random();
            int max = ImageData.max;

            if (level >= max)
                level = rnd.nextInt(max);
            int w = getWidth();
            int h = getHeight();
            if (level >= ImageData.max)
                level = rnd.nextInt(ImageData.max);
            imageData = new ImageData(level);
            imageData.makeShapeCoords(w, h);

            imageData.setSizetoFit(getWidth());
        }
    }
