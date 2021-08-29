package loranttoth.dragshapes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.media.Image;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import java.util.Vector;

import loranttoth.dragshapes.data.ImageData;
import loranttoth.dragshapes.data.ShapeData;
import loranttoth.dragshapes.data.ShapeTypes;

/**
 * Created by freestate on 2018.08.16..
 */
public class GameFrame extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;
    Paint paint;

    Paint paintbig;
    Paint paintbig2;
    int[] btn3;

    Paint paintCircle;
    Paint paintCircle2;

    Paint paintBg;

    GameActivity parent;

    int sdx;
    int sdy;

    ShapeData aktShape = null;

    boolean isDrag = false;

    boolean isRotate = false;

    Bitmap bitmap = null;
    Canvas canvasb = null;
    Bitmap bitmap2 = null;

    int oldx = 0;
    int oldy = 0;

    int shapeId = 0;

    Vector<Coord> aktSnapCoords;

    Coord lastSnap = null;

    Vector<Coord> aktCoords;

    ImageData imageData = null;

    int shapeColor = Color.LTGRAY;
    int shapeColor2 = Color.DKGRAY;
    int bgcolor;
    float emptyPixelNum = 0;

    boolean isSaveMode = false;

    boolean isWon = false;

    int level = 0;

    Coord aktCircleCoord = null;

    public GameFrame(Context c, AttributeSet attrs) {
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

        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle.setColor(Color.GRAY);
        paintCircle.setAlpha(100);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(50);

        paintCircle2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCircle2.setColor(Color.BLACK);
        paintCircle2.setStyle(Paint.Style.FILL_AND_STROKE);
        paintCircle2.setStrokeWidth(4);

        bgcolor = Color.parseColor("#707ed7");

        paintBg = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBg.setColor(bgcolor);
        //paintBg.setStyle(Paint.Style.FILL);

        aktSnapCoords = new Vector<>();

        aktCoords = new Vector<>();

    }

    public void setParent(GameActivity parent) {
        this.parent = parent;
    }

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
            aktShape = null;
            makeImageData();

            int bw = (w / 3)-50;

            btn3 = new int[4];
            btn3[0] = (2*bw)+40;
            btn3[1] = maxy - 120;
            btn3[2] = w-10;
            btn3[3] = maxy - 20;
        }

        if (bitmap == null && w >0 && h > 0) {
            bitmap = Bitmap.createBitmap(getWidth(),
                    getHeight(),
                    Bitmap.Config.ARGB_8888);
            canvasb = new Canvas(bitmap);
        }

        canvasb.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        canvas.drawRect(0,0, w, h, paintBg);

        drawBigShape(canvas);
        drawBigShape(canvasb);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);

        ShapeData sd;
        Coord coord;
        if (!isWon) {
            for (int i = 0; i < imageData.shapes.size(); i++) {
                sd = imageData.shapes.elementAt(i);
                drawShape(sd, canvas, true);
                drawShape(sd, canvasb, true);
            }
            if (aktShape != null && !isDrag) {
                drawShape(aktShape, canvas, true);
                drawCircleforRotate(aktShape, canvas);
                //  drawShape(aktShape, canvasb, true);
            }

            Paint paint = new Paint();
            paint.setColor(Color.BLACK);

            for (int i = 0; i < aktSnapCoords.size(); i++) {
                coord = aktSnapCoords.elementAt(i);
                canvas.save();
                canvas.drawCircle(coord.x, coord.y, 10, paint);
                canvas.restore();
            }
            if (aktShape != null) {
                drawShape(aktShape, canvas, true);
                //  drawShape(aktShape, canvasb, true);
            }
        }

        //koords text
        //for (int i = 0; i < shapes.size(); i++) {
        //    sd = shapes.elementAt(i);
        //    drawCoords(sd, canvas,(i+1)*20);
        //}

        Paint tpaint = new Paint();
        tpaint.setColor(Color.BLACK);
        tpaint.setTextSize(40);

        if (bitmap != null && canvasb != null) {
           // draw(canvasb);
            bitmap2 = Bitmap.createScaledBitmap(bitmap, getWidth() / 5,
                    getHeight() / 5,
                    false);

            //if (bitmap2 != null)
            //    canvas.drawBitmap(bitmap2, 100, (getHeight()/2)+200, paint);

            if (!isWon && imageData.checkAllConnected()) {
                isWon = checkVictory();
                //boolean isWon = true;
                float allnum = (imageData.smaxx-imageData.sminx)*(imageData.smaxy-imageData.sminy);
                float percent = emptyPixelNum/allnum;
                canvas.drawText("ISWON: " + isWon + " empty: " + emptyPixelNum + " / " + allnum + " " + percent + "% ", 50, 20, tpaint);
                if (isWon) {
                    //isWon = false;
                    //shapes = new Vector<>();
                    //win modal
                }
            }
        }

        for (int i = 0; i < aktCoords.size(); i++) {
            coord = aktCoords.elementAt(i);
            canvas.save();
            canvas.drawCircle(coord.x, coord.y, 10, paint);
            canvas.restore();
        }
        canvas.drawRect(btn3[0], btn3[1], btn3[2], btn3[3], paint);

        //canvas.drawText(time+"",w-100,h-100,tpaint);
    }

    void drawShape(ShapeData sd, Canvas canvas, boolean isLine) {
        if (canvas == null)
            return;
        Path path;
        ShapeTypes.types type;
        Coord[] coords;
        Coord centr;
        canvas.save();
        type = sd.getType();
        coords = sd.getCoords();
        //parent.showMess("type: "+type+" color: "+sd.getPaint().getColor());
        coords = sd.rotateCoords(sd.getCenter(), sd.getDeg());
        path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(coords[0].x, coords[0].y);
        for (int i = 1; i < coords.length; i++) {
            path.lineTo(coords[i].x, coords[i].y);
        }
        path.lineTo(coords[0].x, coords[0].y);
        path.close();
        if (aktShape != null && sd.getId() == aktShape.getId())
            sd.getPaint().setAlpha(20);
        else
            sd.getPaint().setAlpha(255);
        canvas.drawPath(path, sd.getPaint());
        canvas.restore();

        if (isLine) {
            int lineColor = (aktShape != null && sd.getId() == aktShape.getId() ? Color.YELLOW : Color.BLACK);

            int width = (aktShape != null && sd.getId() == aktShape.getId() ? 6 : 3);

            Paint paintline = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintline.setColor(lineColor);
            paintline.setStyle(Paint.Style.STROKE);
            paintline.setStrokeWidth(width);

            canvas.save();

            path = new Path();
            path.moveTo(coords[0].x, coords[0].y);
            for (int i = 1; i < coords.length; i++) {
                path.lineTo(coords[i].x, coords[i].y);
            }
            path.lineTo(coords[0].x, coords[0].y);
            path.close();
            canvas.drawPath(path, paintline);
            canvas.restore();
        }
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

    void drawCircleforRotate(ShapeData sd, Canvas canvas) {
        if (canvas == null)
            return;
        Path path;
        ShapeTypes.types type;
        Coord[] coords;
        Coord centr;
        canvas.save();
        type = sd.getType();
        coords = sd.getCoords();
        //parent.showMess("type: "+type+" color: "+sd.getPaint().getColor());

        int sminx = 999999;
        int smaxx = -999999;
        int sminy = 999999;
        int smaxy = -999999;
        Coord coord;

        for (int i = 0; i < coords.length; i++) {
            coord = coords[i];
            if (coord.x < sminx)
                sminx = coord.x;
            if (coord.x > smaxx)
                smaxx = coord.x;
            if (coord.y < sminy)
                sminy = coord.y;
            if (coord.y > smaxy)
                smaxy = coord.y;
        }
        Coord p1 = new Coord(sminx, sminy);
        Coord p2 = new Coord(smaxx, smaxy);
        int d = distance(p1, p2);
        int r = d / 2;

        int cr = getWidth() / 20;

        canvas.drawCircle(sd.getCenter().x, sd.getCenter().y, r, paintCircle);

        coord = new Coord(sd.getCenter().x, sd.getCenter().y - r);
        aktCircleCoord = ShapeData.rotateCoord(coord, sd.getCenter(), sd.getDeg());

        canvas.drawCircle(aktCircleCoord.x, aktCircleCoord.y, cr, paintCircle2);

        canvas.restore();
    }

    void drawCoords(ShapeData sd, Canvas canvas, int y) {
        ShapeTypes.types type;
        Coord[] coords;
        Coord centr;
        canvas.save();
        type = sd.getType();
        coords = sd.getCoords();
        //parent.showMess("type: "+type+" color: "+sd.getPaint().getColor());
        coords = sd.rotateCoords(sd.getCenter(), sd.getDeg());
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(18);
        String s = type.toString();
        for (int i = 0; i < coords.length; i++) {
            s+=i+". x: "+coords[i].x+" y: "+coords[i].y+";";
        }
        canvas.drawText(s, 600, y, paint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        int w = getWidth();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                aktSnapCoords.clear();
                if (x >= btn3[0] && x <= btn3[2] && y >= btn3[1] && y <= btn3[3]) {
                    //shapes = new Vector<>();
                    isSaveMode = true;

                    invalidate();
                }
                else {
                    //this.setDrawingCacheEnabled(true);
                    //bitmap = getDrawingCache();
                    //this.setDrawingCacheEnabled(false);
                    oldx = x;
                    oldy = y;

                    try{

                    } catch (Exception e) {
                        //parent.showMess("error: " + e.getLocalizedMessage());
                    }

                    if (bitmap != null) {
                        //int pcol = bitmap.getPixel(x, y);
                        Coord coord = new Coord(x, y);
                        //aktShape = null;
                        Coord[] coords;
                        ShapeData shape;
                        boolean ok = false;

                        if (aktShape != null) {
                            if (aktCircleCoord != null) {
                                int cr = getWidth() / 20;
                                if (x >= this.aktCircleCoord.x - cr && y >= this.aktCircleCoord.y - cr && x <= this.aktCircleCoord.x + cr && y <= this.aktCircleCoord.y + cr) {
                                    isRotate = true;
                                    ok = true;
                                }
                            }
                        }

                        if (!ok) {
                            isRotate = false;
                            for (int i = 0; i < imageData.shapes.size(); i++) {
                                //if (shapes.elementAt(i).getColor() == pcol) {
                                shape = imageData.shapes.elementAt(i);
                                coords = shape.rotateCoords(shape.getCenter(), shape.getDeg());
                                if (containsPoly(coords, coord)) {
                                    aktShape = imageData.shapes.elementAt(i);
                                    isDrag = true;
                                    ok = true;
                                    invalidate();
                                    break;
                                }
                            }
                        }
                    }

                }
                //ptrID1 = event.getPointerId(event.getActionIndex());
                break;
            /*case MotionEvent.ACTION_POINTER_DOWN:
                ptrID2 = event.getPointerId(event.getActionIndex());
                sX = event.getX(event.findPointerIndex(ptrID1));
                sY = event.getY(event.findPointerIndex(ptrID1));
                fX = event.getX(event.findPointerIndex(ptrID2));
                fY = event.getY(event.findPointerIndex(ptrID2));
                invalidate();
                break;*/
            case MotionEvent.ACTION_MOVE:
                /*if(ptrID1 != INVALID_POINTER_ID && ptrID2 != INVALID_POINTER_ID){
                    nsX = event.getX(event.findPointerIndex(ptrID1));
                    nsY = event.getY(event.findPointerIndex(ptrID1));
                    nfX = event.getX(event.findPointerIndex(ptrID2));
                    nfY = event.getY(event.findPointerIndex(ptrID2));

                    mAngle = angleBetweenLines(fX, fY, sX, sY, nfX, nfY, nsX, nsY);
                    invalidate();

                }*/
                //else {
                    if (aktShape != null && x != oldx && y != oldy && isDrag) {
                        Coord newcenter;
                        Coord oldcenter = aktShape.getCenter();
                        int dx = oldcenter.x - x;
                        int dy = oldcenter.y - y;
                        newcenter = new Coord(x,y);
                        if (aktShape.isSnapped()){
                            int d = distance(newcenter, oldcenter);
                            if (d < 20) {
                                return true;
                            }
                        }
                        aktShape.setCenter(newcenter);
                        Coord[] coords = aktShape.getCoords();
                        for (int i = 0; i < coords.length; i++) {
                            coords[i].x -= dx;
                            coords[i].y -= dy;
                        }
                        aktShape.setCoords(coords);
                        aktSnapCoords.clear();
                        Coord delta = getSnappedCoord(aktShape);
                        if (delta != null) {
                            dx = delta.x;
                            dy = delta.y;
                            oldcenter = aktShape.getCenter();
                            newcenter = new Coord(oldcenter.x - dx, oldcenter.y - dy);
                            aktShape.setCenter(newcenter);
                            coords = aktShape.getCoords();
                            Coord[] newcoords = new Coord[coords.length];
                            for (int i = 0; i < coords.length; i++) {
                                newcoords[i] = new Coord(coords[i].x - dx, coords[i].y - dy);
                            }
                            aktShape.setCoords(newcoords);
                            aktShape.setSnapped(true);
                            if (lastSnap == null || (lastSnap.x != dx || lastSnap.y != dy))
                                playSnapSound();
                                lastSnap = new Coord(dx, dy);
                        }
                        else {
                            aktShape.setSnapped(false);
                            lastSnap = null;
                        }
                        oldx = x;
                        oldy = y;

                        invalidate();
                    }
                    else if (aktShape != null && isRotate && (x != oldx || y != oldy)) {
                        Coord cent = aktShape.getCenter();
                        int cx = cent.x;
                        int cy = cent.y;
                        float mAngle = angleBetweenLines( cx, cy, x, y, cx,cy,cx,cy-100);
                        System.out.println("angel: "+mAngle);
                        float f = mAngle / 15;
                        int i = Math.round(f);
                        int angle = i*15;
                        aktShape.setDeg(angle);
                        oldx = x;
                        oldy = y;
                        invalidate();

                        /*if (x != oldx){
                            if (Math.abs(x-oldx) > 50) {
                                if (aktShape != null) {
                                    if (x < oldx) {
                                        aktShape.setDeg(aktShape.getDeg() - 15);
                                        if (aktShape.getDeg() < 0) {
                                            aktShape.setDeg(360);
                                        }
                                    }
                                    else {
                                        aktShape.setDeg(aktShape.getDeg() + 15);
                                        if (aktShape.getDeg() >= 360) {
                                            aktShape.setDeg(0);
                                        }
                                    }

                                    oldx = x;
                                    oldy = y;
                                    invalidate();
                                }
                            }

                        }*/
                    }

                //}
                break;
            case MotionEvent.ACTION_UP:
                //ptrID1 = INVALID_POINTER_ID;
                //System.out.println("koord: "+x+" "+y);
                if (isSaveMode)
                    saveCoord(x,y);
                aktSnapCoords.clear();
                isDrag = false;
                isRotate = false;
                invalidate();
                break;
            //case MotionEvent.ACTION_POINTER_UP:
            //    ptrID2 = INVALID_POINTER_ID;
            //    break;
            case MotionEvent.ACTION_CANCEL:
            //    ptrID1 = INVALID_POINTER_ID;
            //    ptrID2 = INVALID_POINTER_ID;
                break;
        }
        return true;
    }

    void makeImageData() {

        Random rnd = new Random();
        int w = getWidth();
        int h = getHeight();
        if (level >= ImageData.max)
            level = rnd.nextInt(ImageData.max);
        imageData = new ImageData(level);
        imageData.makeShapeCoords(w, h);
        imageData.makeShapes(w, h);

    }

    Coord getSnappedCoord(ShapeData shape) {
        Coord coord;
        Coord[] coords = shape.getCoords();
        coords = shape.rotateCoords(shape.getCenter(), shape.getDeg());
        Coord[] fp = ShapeData.getHalfPoints(coords);


        Coord[] coords1;
        Coord[] fp1;
        Coord coord1;
        int d;
        ShapeData shape1;
        Coord snapPoint = null;
        int dist = 1000;
        for (int i = 0; i < imageData.shapes.size(); i++) {
            shape1 = imageData.shapes.elementAt(i);
            if (shape1.getId() == shape.getId())
                continue;
            coords1 = shape1.rotateCoords(shape1.getCenter(), shape1.getDeg());
            fp1 = ShapeData.getHalfPoints(coords1);
            for (int j = 0; j < coords.length; j++) {
                coord = coords[j];
                for (int k = 0; k < coords1.length; k++) {
                    coord1 = coords1[k];
                    d = distance(coord,coord1);
                    if (d < 50 && d < dist) {
                        int dx = coord.x-coord1.x;
                        int dy = coord.y-coord1.y;
                        dist = d;
                        snapPoint = new Coord(dx,dy);
                    }
                    if (d < 80) {
                        aktSnapCoords.addElement(new Coord(coord1.x, coord1.y));
                    }
                }
                for (int k = 0; k < fp1.length; k++) {
                    coord1 = fp1[k];
                    d = distance(coord,coord1);
                    if (d < 50 && d < dist) {
                        int dx = coord.x-coord1.x;
                        int dy = coord.y-coord1.y;
                        dist = d;
                        snapPoint = new Coord(dx,dy);
                    }
                    if (d < 80) {
                        aktSnapCoords.addElement(new Coord(coord1.x, coord1.y));
                    }
                }
            }
            for (int j = 0; j < fp.length; j++) {
                coord = fp[j];
                for (int k = 0; k < coords1.length; k++) {
                    coord1 = coords1[k];
                    d = distance(coord,coord1);
                    if (d < 50 && d < dist) {
                        int dx = coord.x-coord1.x;
                        int dy = coord.y-coord1.y;
                        dist = d;
                        snapPoint = new Coord(dx,dy);
                    }
                    if (d < 80) {
                        aktSnapCoords.addElement(new Coord(coord1.x, coord1.y));
                    }
                }
                for (int k = 0; k < fp1.length; k++) {
                    coord1 = fp1[k];
                    d = distance(coord,coord1);
                    if (d < 50 && d < dist) {
                        int dx = coord.x-coord1.x;
                        int dy = coord.y-coord1.y;
                        dist = d;
                        snapPoint = new Coord(dx,dy);
                    }
                    if (d < 80) {
                        aktSnapCoords.addElement(new Coord(coord1.x, coord1.y));
                    }
                }
            }


        }

        coords1 = imageData.shapeCoords;
        fp1 = ShapeData.getHalfPoints(coords1);
        for (int j = 0; j < coords.length; j++) {
            coord = coords[j];
            for (int k = 0; k < coords1.length; k++) {
                coord1 = coords1[k];
                d = distance(coord,coord1);
                if (d < 50 && d < dist) {
                    int dx = coord.x-coord1.x;
                    int dy = coord.y-coord1.y;
                    dist = d;
                    snapPoint = new Coord(dx,dy);
                }
                if (d < 80) {
                    aktSnapCoords.addElement(new Coord(coord1.x, coord1.y));
                }
            }
            for (int k = 0; k < fp1.length; k++) {
                coord1 = fp1[k];
                d = distance(coord,coord1);
                if (d < 50 && d < dist) {
                    int dx = coord.x-coord1.x;
                    int dy = coord.y-coord1.y;
                    dist = d;
                    snapPoint = new Coord(dx,dy);
                }
                if (d < 80) {
                    aktSnapCoords.addElement(new Coord(coord1.x, coord1.y));
                }
            }
        }
        for (int j = 0; j < fp.length; j++) {
            coord = fp[j];
            for (int k = 0; k < coords1.length; k++) {
                coord1 = coords1[k];
                d = distance(coord,coord1);
                if (d < 50 && d < dist) {
                    int dx = coord.x-coord1.x;
                    int dy = coord.y-coord1.y;
                    dist = d;
                    snapPoint = new Coord(dx,dy);
                }
                if (d < 80) {
                    aktSnapCoords.addElement(new Coord(coord1.x, coord1.y));
                }
            }
            for (int k = 0; k < fp1.length; k++) {
                coord1 = fp1[k];
                d = distance(coord,coord1);
                if (d < 50 && d < dist) {
                    int dx = coord.x-coord1.x;
                    int dy = coord.y-coord1.y;
                    dist = d;
                    snapPoint = new Coord(dx,dy);
                }
                if (d < 80) {
                    aktSnapCoords.addElement(new Coord(coord1.x, coord1.y));
                }
            }
        }
        return snapPoint;
    }


    int distance(Coord c1, Coord c2) {
        int d = (int)Math.sqrt(((c1.x-c2.x)*(c1.x-c2.x)) + ((c1.y-c2.y)*(c1.y-c2.y)));
        return d;
    }

    private float angleBetweenLines (float fX, float fY, float sX, float sY, float nfX, float nfY, float nsX, float nsY)
    {
        float angle1 = (float) Math.atan2( (fY - sY), (fX - sX) );
        float angle2 = (float) Math.atan2( (nfY - nsY), (nfX - nsX) );

        float angle = ((float)Math.toDegrees(angle1 - angle2)) % 360;
        if (angle < -180.f) angle += 360.0f;
        if (angle > 180.f) angle -= 360.0f;
        return angle;
    }

    boolean checkVictory() {
        boolean isWon = false;
        if (bitmap2 != null) {
            emptyPixelNum = 0;
            int pcol;
            for (int x = 0; x < bitmap2.getWidth(); x++) {
                for (int y = 0; y < bitmap2.getHeight(); y++) {
                    pcol = bitmap2.getPixel(x, y);
                    if (pcol == shapeColor)
                        emptyPixelNum++;
                }
            }
            isWon = emptyPixelNum == 0;
        }
        return isWon;
    }

    void saveCoord(int x, int y) {
        ShapeData sd;
        Coord coord = new Coord(x,y);
        for (int i = 0; i < imageData.shapes.size(); i++) {
            sd = imageData.shapes.elementAt(i);
            searchCoord(sd, coord);
        }
    }

    void searchCoord(ShapeData sd, Coord coord) {
        ShapeTypes.types type;
        Coord[] coords;
        Coord centr;
        type = sd.getType();
        //parent.showMess("type: "+type+" color: "+sd.getPaint().getColor());
        coords = sd.rotateCoords(sd.getCenter(), sd.getDeg());
        String s = type.toString();
        int d;

        int dist = 1000;
        boolean ok;
        int aktx = -1;
        int akty = -1;

        for (int i = 0; i < coords.length; i++) {
            d = distance(coords[i],coord);
            if (d < 20 && d < dist ) {
                ok = true;
                if (aktCoords.size() > 0) {
                    if (Math.abs(aktCoords.elementAt(aktCoords.size()-1).x - coords[i].x) < 10 && Math.abs(aktCoords.elementAt(aktCoords.size()-1).y - coords[i].y) < 10)
                        ok = false;
                }
                if (ok) {
                    aktx = coords[i].x;
                    akty = coords[i].y;
                }
                dist = d;
            }
        }

        if (aktx != -1 && akty != -1) {
            System.out.println("belerakom: "+aktx+" "+akty);

            aktCoords.add(new Coord(aktx, akty));
        }

        Coord c;
/*
        for (int i = 0; i < aktCoords.size(); i++) {
            c = aktCoords.elementAt(i);
            System.out.println(c.x+", "+c.y);
        }*/
    }

    public boolean containsPoly(Coord[] points, Coord coord) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = points.length - 1; i < points.length; j = i++) {
            if ((points[i].y > coord.y) != (points[j].y > coord.y) &&
                    (coord.x < (points[j].x - points[i].x) * (coord.y - points[i].y) / (points[j].y-points[i].y) + points[i].x)) {
                result = !result;
            }
        }
        return result;
    }

    private void playSnapSound() {
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.snap);
        mp.start();
    }

}
