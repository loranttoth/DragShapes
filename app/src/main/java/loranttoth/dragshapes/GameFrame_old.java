package loranttoth.dragshapes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.shapes.Shape;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import java.util.Vector;

/**
 * Created by freestate on 2018.08.16..
 */
public class GameFrame_old extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;
    Paint paint;

    Paint paintbig;
    Paint paintbig2;

    int[] btn1;
    int[] btn2;

    int[] btn3;

    ShapeMainActivity parent;

    Vector<ShapeData> shapes;

    int unit = 50;
    int sizex = 2;
    int sizey = 2;

    int sdx;
    int sdy;

    ShapeData aktShape = null;

    boolean isDrag = false;

    Bitmap bitmap = null;
    Canvas canvasb = null;
    Bitmap bitmap2 = null;

    int oldx = 0;
    int oldy = 0;

    int shapeId = 0;

    Vector<Coord> aktSnapCoords;

    private static final int INVALID_POINTER_ID = -1;
    private float fX, fY, sX, sY;
    private float nfX, nfY, nsX, nsY;
    private int ptrID1, ptrID2;
    private float mAngle;

    int shapeColor = Color.LTGRAY;
    int shapeColor2 = Color.DKGRAY;

    int sminx;
    int smaxx;
    int sminy;
    int smaxy;

    float emptyPixelNum = 0;

    ShapeData.types[] shapeTypes;
    int[] shapeDir;

    Coord lastSnap = null;

    public GameFrame_old(Context c, AttributeSet attrs) {
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


        shapes = new Vector<>();

        aktSnapCoords = new Vector<>();

    }

    public void setParent(ShapeMainActivity parent) {
        this.parent = parent;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int maxx = getWidth();
        int maxy = getHeight();
        int w = getWidth();
        int h = getHeight();
        if (shapes.size() == 0 && maxx > 0 && maxy > 0){
            int min = Math.min(w, h);
            unit = min/13;
            aktShape = null;
            makeShapes();
        }

        if (bitmap == null && w >0 && h > 0) {
            bitmap = Bitmap.createBitmap(getWidth(),
                    getHeight(),
                    Bitmap.Config.ARGB_8888);
            canvasb = new Canvas(bitmap);
        }
        canvasb.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        int bw = (w / 3)-50;

        btn1 = new int[4];
        btn1[0] = 10;
        btn1[1] = maxy - 120;
        btn1[2] = bw+10;
        btn1[3] = maxy - 20;

        btn2 = new int[4];
        btn2[0] = bw+20;
        btn2[1] = maxy - 120;
        btn2[2] = (2*bw)+30;
        btn2[3] = maxy - 20;


        btn3 = new int[4];
        btn3[0] = (2*bw)+40;
        btn3[1] = maxy - 120;
        btn3[2] = w-10;
        btn3[3] = maxy - 20;


        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);

        ShapeData sd;
        for (int i = 0; i < shapes.size(); i++) {
            sd = shapes.elementAt(i);
            drawShape(sd, canvas, false);
            drawShape(sd, canvasb, true);
        }
        if (aktShape != null) {
            drawShape(aktShape, canvas, true);
          //  drawShape(aktShape, canvasb, true);
        }

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        Coord coord;
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

        //koords text
        for (int i = 0; i < shapes.size(); i++) {
            sd = shapes.elementAt(i);
            drawCoords(sd, canvas,(i+1)*20);
        }

        Paint tpaint = new Paint();
        tpaint.setColor(Color.BLACK);
        tpaint.setTextSize(40);

        if (bitmap != null && canvasb != null) {
           // draw(canvasb);
            bitmap2 = Bitmap.createScaledBitmap(bitmap, getWidth() / 5,
                    getHeight() / 5,
                    false);

            if (bitmap2 != null)
                canvas.drawBitmap(bitmap2, 100, (getHeight()/2)+200, paint);


        }


        canvas.drawRect(btn1[0], btn1[1], btn1[2], btn1[3], paint);
        canvas.drawRect(btn2[0], btn2[1], btn2[2], btn2[3], paint);
        canvas.drawRect(btn3[0], btn3[1], btn3[2], btn3[3], paint);

        //canvas.drawText(time+"",w-100,h-100,tpaint);
    }

    void drawShape(ShapeData sd, Canvas canvas, boolean isLine) {
        if (canvas == null)
            return;
        Path path;
        ShapeData.types type;
        Coord[] coords;
        Coord[] coords2;
        Coord centr;
        canvas.save();
        type = sd.getType();
        coords = sd.getCoords();
        coords2 = sd.getCoords2();
        //parent.showMess("type: "+type+" color: "+sd.getPaint().getColor());
        coords = rotateCoords(coords, sd.getCenter(), sd.getDeg());
        coords2 = rotateCoords(coords2, sd.getCenter(), sd.getDeg());
        path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(coords[0].x, coords[0].y);
        for (int i = 1; i < coords.length; i++) {
            path.lineTo(coords[i].x, coords[i].y);
        }
        path.lineTo(coords[0].x, coords[0].y);
        path.close();
        canvas.drawPath(path, sd.getPaint());
        canvas.restore();

        if (isLine) {
            int lineColor = (aktShape != null && sd.getId() == aktShape.getId() ? Color.YELLOW : Color.BLUE);

            Paint paintline = new Paint(Paint.ANTI_ALIAS_FLAG);
            paintline.setColor(lineColor);
            paintline.setStyle(Paint.Style.STROKE);
            paintline.setStrokeWidth(10);

            canvas.save();

            path = new Path();
            path.moveTo(coords2[0].x, coords2[0].y);
            for (int i = 1; i < coords2.length; i++) {
                path.lineTo(coords2[i].x, coords2[i].y);
            }
            path.lineTo(coords2[0].x, coords2[0].y);
            path.close();
            canvas.drawPath(path, paintline);
            canvas.restore();
        }
    }


    void drawCoords(ShapeData sd, Canvas canvas, int y) {
        ShapeData.types type;
        Coord[] coords;
        Coord centr;
        canvas.save();
        type = sd.getType();
        coords = sd.getCoords();
        //parent.showMess("type: "+type+" color: "+sd.getPaint().getColor());
        coords = rotateCoords(coords, sd.getCenter(), sd.getDeg());
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
                if (x >= btn1[0] && x <= btn1[2] && y >= btn1[1] && y <= btn1[3]) {
                    if (aktShape != null) {
                        aktShape.setDeg(aktShape.getDeg() - 15);
                        if (aktShape.getDeg() < 0) {
                            aktShape.setDeg(360);
                        }
                        invalidate();
                    }
                }
                else if (x >= btn2[0] && x <= btn2[2] && y >= btn2[1] && y <= btn2[3]) {
                    if (aktShape != null) {
                        aktShape.setDeg(aktShape.getDeg() + 15);
                        if (aktShape.getDeg() >= 360) {
                            aktShape.setDeg(0);
                        }
                        invalidate();
                    }
                }
                else if (x >= btn3[0] && x <= btn3[2] && y >= btn3[1] && y <= btn3[3]) {
                    shapes = new Vector<>();
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
                        parent.showMess("error: " + e.getLocalizedMessage());
                    }

                    if (bitmap != null) {
                        int pcol = bitmap.getPixel(x, y);
                        //aktShape = null;
                        for (int i = 0; i < shapes.size(); i++) {
                            if (shapes.elementAt(i).getColor() == pcol) {
                                aktShape = shapes.elementAt(i);
                                isDrag = true;
                                invalidate();
                                break;
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
                    if (x >= btn1[0] && x <= btn1[2] && y >= btn1[1] && y <= btn1[3]) {
                    }
                    else if (x >= btn2[0] && x <= btn2[2] && y >= btn2[1] && y <= btn2[3]) {
                    }
                    else if (x >= btn3[0] && x <= btn3[2] && y >= btn3[1] && y <= btn3[3]) {
                    }
                    else if (aktShape != null && x != oldx && y != oldy && isDrag) {
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
                        Coord[] coords2 = aktShape.getCoords2();
                        for (int i = 0; i < coords2.length; i++) {
                            coords2[i].x -= dx;
                            coords2[i].y -= dy;
                        }
                        aktShape.setCoords2(coords2);
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
                            coords2 = aktShape.getCoords2();
                            Coord[] newcoords2 = new Coord[coords2.length];
                            for (int i = 0; i < coords2.length; i++) {
                                newcoords2[i] = new Coord(coords2[i].x - dx, coords2[i].y - dy);
                            }
                            aktShape.setCoords2(newcoords2);
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
                    else if (aktShape != null && x != oldx && y != oldy) {
                        if (x < oldx){
                            if (Math.abs(x-oldx) > 100) {
                                if (aktShape != null) {
                                    aktShape.setDeg(aktShape.getDeg() - 15);
                                    if (aktShape.getDeg() < 0) {
                                        aktShape.setDeg(360);
                                    }
                                    oldx = x;
                                    oldy = y;
                                    invalidate();
                                }
                            }
                        }
                        else {
                            if (Math.abs(x-oldx) > 100) {
                                if (aktShape != null) {
                                    aktShape.setDeg(aktShape.getDeg() + 15);
                                    if (aktShape.getDeg() >= 360) {
                                        aktShape.setDeg(0);
                                    }
                                    oldx = x;
                                    oldy = y;
                                    invalidate();
                                }
                            }
                        }
                    }

                //}
                break;
            case MotionEvent.ACTION_UP:
                //ptrID1 = INVALID_POINTER_ID;
                aktSnapCoords.clear();
                isDrag = false;
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

    void makeShapes() {

        int a;

        int w = getWidth();
        int h = getHeight();

        int sw = getWidth();
        int sh = getHeight();

        shapeTypes = new ShapeData.types[7];
        shapeTypes[0] = ShapeData.types.SQUARE;
        shapeTypes[1] = ShapeData.types.TRIANGLE1;
        shapeTypes[2] = ShapeData.types.TRIANGLE1;
        shapeTypes[3] = ShapeData.types.TRIANGLE2;
        shapeTypes[4] = ShapeData.types.TRIANGLE3;
        shapeTypes[5] = ShapeData.types.TRIANGLE3;
        shapeTypes[6] = ShapeData.types.PARALELOGRAMA;

        shapeDir = new int[7];
        shapeDir[0] = 1;
        shapeDir[1] = 1;
        shapeDir[2] = 1;
        shapeDir[3] = 1;
        shapeDir[4] = 1;
        shapeDir[5] = 1;
        shapeDir[6] = 1;

        Random rnd = new Random();



        for (int i = 0; i < shapeTypes.length; i++) {
            try {
                Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
                int x = rnd.nextInt(w-100) + 50;
                int y = rnd.nextInt((h/2)-100) + (h/2)+100;
                Coord center = new Coord(x, y);
                //int size = rnd.nextInt(3) + 1;
                //int sizex = 2;
                //int sizey = 2;
                int irany = rnd.nextInt(2);
                int r = rnd.nextInt(255);
                int g = rnd.nextInt(255);
                int b = rnd.nextInt(255);
                int color = Color.rgb(r, g, b);
                int deg = rnd.nextInt(360 / 15) * 15;
                paint1.setColor(color);

                //int t = rnd.nextInt(ShapeData.types.values().length);
                //ShapeData.types type = ShapeData.types.values()[t];
                ShapeData.types type = shapeTypes[i];
                Coord[] coords = null;
                Coord[] coords2 = null;

                Coord[][] allcoords = null;
                switch (type) {
                    case SQUARE:
                        allcoords = makeSquare(center, sizex);
                        coords = allcoords[0];
                        coords2 = allcoords[1];
                        center = new Coord(center.x+(sizex*unit/2),center.y-(sizex*unit/2));
                        break;
                    case RECTANGLE:
                        allcoords = makeRectangle(center, sizex, sizey);
                        coords = allcoords[0];
                        coords2 = allcoords[1];
                        center = new Coord(center.x+(sizex*unit/2),center.y-(sizey*unit/2));
                        break;
                    case TRIANGLE1:
                        allcoords = makeTriangle1(center, sizex, shapeDir[i]);
                        coords = allcoords[0];
                        coords2 = allcoords[1];
                        center = getCentroid(coords);
                        break;
                    case TRIANGLE2:
                        allcoords = makeTriangle2(center, sizex,shapeDir[i]);
                        coords = allcoords[0];
                        coords2 = allcoords[1];
                        center = getCentroid(coords);
                        break;
                    case TRIANGLE3:
                        allcoords = makeTriangle3(center, sizex,shapeDir[i]);
                        coords = allcoords[0];
                        coords2 = allcoords[1];
                        center = getCentroid(coords);
                        break;
                    case PARALELOGRAMA:
                        allcoords = makeParalelograma(center, sizex, shapeDir[i]);
                        coords = allcoords[0];
                        coords2 = allcoords[1];
                        center = new Coord(center.x,center.y-(sizex*unit/2));
                        break;
                }

                ShapeData data = new ShapeData(shapeId++, type, coords, coords2, center, color, deg, sizex, sizey, irany, paint1);
                shapes.addElement(data);
            }catch (Exception e) {
                parent.showMess("error: "+e.getLocalizedMessage());
            }
        }
    }

    Coord[][] makeSquare(Coord center, int size) {
        Coord[][] ret = new Coord[2][4];
        Coord[] coord = new Coord[4];
        coord[0] = new Coord(center.x, center.y);
        coord[1] = new Coord(center.x + (size*unit), center.y);
        coord[2] = new Coord(center.x + (size*unit), center.y - (size*unit));
        coord[3] = new Coord(center.x , center.y - (size*unit));

        Coord[] coord2 = new Coord[4];
        coord2[0] = new Coord(center.x-1, center.y+1);
        coord2[1] = new Coord(center.x + (size*unit)+1, center.y+1);
        coord2[2] = new Coord(center.x + (size*unit)+1, (center.y - (size*unit))-1);
        coord2[3] = new Coord(center.x-1 , (center.y - (size*unit))-1);

        ret[0] = coord;
        ret[1] = coord2;

        return ret;
    }

    Coord[][] makeParalelograma(Coord center, int sizex, int dir) {
        Coord[][] ret = new Coord[2][4];
        Coord[] coord = new Coord[4];
        Coord[] coord2 = new Coord[4];
        if (dir == 1) {
            coord[0] = new Coord(center.x, center.y);
            coord[1] = new Coord(center.x + (sizex*unit), center.y- (sizex*unit));
            coord[2] = new Coord(center.x, center.y - (sizex*unit));
            coord[3] = new Coord(center.x - (sizex*unit), center.y);

            coord2[0] = new Coord(center.x-1, center.y+1);
            coord2[1] = new Coord(center.x + (sizex*unit)+1, (center.y- (sizex*unit))-1);
            coord2[2] = new Coord(center.x-1, (center.y - (sizex*unit))-1);
            coord2[3] = new Coord((center.x - (sizex*unit))-1, center.y+1);
        }
        else {
            coord[0] = new Coord(center.x, center.y);
            coord[1] = new Coord(center.x + (sizex*unit), center.y);
            coord[2] = new Coord(center.x, center.y - (sizex*unit));
            coord[3] = new Coord(center.x - (sizex*unit), center.y - (sizex*unit));

            coord2[0] = new Coord(center.x-1, center.y+1);
            coord2[1] = new Coord(center.x + (sizex*unit)+1, center.y+1);
            coord2[2] = new Coord(center.x-1, (center.y - (sizex*unit))-1);
            coord2[3] = new Coord((center.x - (sizex*unit)-1), (center.y - (sizex*unit))-1);
        }
        ret[0] = coord;
        ret[1] = coord2;
        return ret;
    }

    Coord[][] makeRectangle(Coord center, int sizex, int sizey) {
        Coord[][] ret = new Coord[2][4];
        Coord[] coord = new Coord[4];
        Coord[] coord2 = new Coord[4];

        coord[0] = new Coord(center.x, center.y);
        coord[1] = new Coord(center.x + (sizex*unit), center.y);
        coord[2] = new Coord(center.x + (sizex*unit), center.y - (sizey*unit));
        coord[3] = new Coord(center.x , center.y - (sizey*unit));

        coord2[0] = new Coord(center.x-1, center.y+1);
        coord2[1] = new Coord(center.x + (sizex*unit)+1, center.y+1);
        coord2[2] = new Coord(center.x + (sizex*unit)+1, (center.y - (sizey*unit))-1);
        coord2[3] = new Coord(center.x-1 , (center.y - (sizey*unit))-1);

        ret[0] = coord;
        ret[1] = coord2;
        return ret;
    }

    Coord[][] makeTriangle1(Coord center, int sizex, int dir) {
        Coord[][] ret = new Coord[2][3];
        Coord[] coord = new Coord[3];
        Coord[] coord2 = new Coord[3];
        if (dir == 1) {
            coord[0] = new Coord(center.x, center.y);
            coord[1] = new Coord(center.x + (sizex * unit), center.y);
            coord[2] = new Coord(center.x, center.y - (sizex * unit));

            coord2[0] = new Coord(center.x-1, center.y+1);
            coord2[1] = new Coord(center.x + (sizex * unit)+1, center.y+1);
            coord2[2] = new Coord(center.x-1, (center.y - (sizex * unit))-1);
        }
        else {
            coord[0] = new Coord(center.x, center.y);
            coord[1] = new Coord(center.x - (sizex * unit), center.y);
            coord[2] = new Coord(center.x, center.y - (sizex * unit));

            coord2[0] = new Coord(center.x+1, center.y+1);
            coord2[1] = new Coord((center.x - (sizex * unit))-1, center.y+1);
            coord2[2] = new Coord(center.x+1, (center.y - (sizex * unit))-1);
        }
        ret[0] = coord;
        ret[1] = coord2;
        return ret;
    }

    Coord[][] makeTriangle2(Coord center, int sizex, int dir) {
        Coord[][] ret = new Coord[2][3];
        Coord[] coord = new Coord[3];
        Coord[] coord2 = new Coord[3];
        if (dir == 1) {
            coord[0] = new Coord(center.x, center.y);
            coord[1] = new Coord(center.x + (sizex * unit * 2), center.y);
            coord[2] = new Coord(center.x + (sizex * unit), center.y - (sizex * unit));

            coord2[0] = new Coord(center.x-1, center.y+1);
            coord2[1] = new Coord(center.x + (sizex * unit * 2)+1, center.y+1);
            coord2[2] = new Coord(center.x + (sizex * unit)+1, (center.y - (sizex * unit))-1);
        }
        else {
            coord[0] = new Coord(center.x, center.y);
            coord[1] = new Coord(center.x - (sizex * unit * 2), center.y);
            coord[2] = new Coord(center.x - (sizex * unit), center.y - (sizex * unit));

            coord2[0] = new Coord(center.x+1, center.y+1);
            coord2[1] = new Coord((center.x - (sizex * unit * 2))-1, center.y+1);
            coord2[2] = new Coord((center.x - (sizex * unit))-1, (center.y - (sizex * unit))-1);
        }
        ret[0] = coord;
        ret[1] = coord2;
        return ret;
    }

    Coord[][] makeTriangle3(Coord center, int sizex, int dir) {
        Coord[][] ret = new Coord[2][3];
        Coord[] coord = new Coord[3];
        Coord[] coord2 = new Coord[3];
        if (dir == 1) {
            coord[0] = new Coord(center.x, center.y);
            coord[1] = new Coord(center.x + (sizex * unit * 2), center.y);
            coord[2] = new Coord(center.x, center.y - (sizex * unit * 2));

            coord2[0] = new Coord(center.x-1, center.y+1);
            coord2[1] = new Coord(center.x + (sizex * unit * 2)+1, center.y+1);
            coord2[2] = new Coord(center.x-1, center.y - (sizex * unit * 2));
        }
        else {
            coord[0] = new Coord(center.x, center.y);
            coord[1] = new Coord(center.x - (sizex * unit * 2), center.y);
            coord[2] = new Coord(center.x, center.y - (sizex * unit * 2));

            coord2[0] = new Coord(center.x+1, center.y+1);
            coord2[1] = new Coord((center.x - (sizex * unit * 2))-1, center.y+1);
            coord2[2] = new Coord(center.x+1, (center.y - (sizex * unit * 2))-1);
        }
        ret[0] = coord;
        ret[1] = coord2;
        return ret;
    }

    Coord getCentroid(Coord[] coords) {
        Coord ca = coords[0];
        Coord cb = coords[1];
        Coord cc = coords[2];

        double a = Math.sqrt( ((cc.x - cb.x) * (cc.x - cb.x)) + ((cc.y - cb.y) * (cc.y - cb.y)));
        double b = Math.sqrt( ((cc.x - ca.x) * (cc.x - ca.x)) + ((cc.y - ca.y) * (cc.y - ca.y)));
        double c = Math.sqrt(((cb.x - ca.x) * (cb.x - ca.x)) + ((cb.y - ca.y) * (cb.y - ca.y)));

        int x = (int)((a*ca.x+b*cb.x+c*cc.x)/(a+b+c));
        int y = (int)((a*ca.y+b*cb.y+c*cc.y)/(a+b+c));
        return new Coord(x,y);
    }

    Coord getSnappedCoord(ShapeData shape) {
        Coord coord;
        Coord[] coords = shape.getCoords();
        coords = rotateCoords(coords, shape.getCenter(), shape.getDeg());
        Coord[] fp = getHalfPoints(coords);


        Coord[] coords1;
        Coord[] fp1;
        Coord coord1;
        int d;
        ShapeData shape1;
        Coord snapPoint = null;
        int dist = 1000;
        for (int i = 0; i < shapes.size(); i++) {
            shape1 = shapes.elementAt(i);
            if (shape1.getId() == shape.getId())
                continue;
            coords1 = shape1.getCoords();
            coords1 = rotateCoords(coords1, shape1.getCenter(), shape1.getDeg());
            fp1 = getHalfPoints(coords1);
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
        return snapPoint;
    }

    int distance(Coord c1, Coord c2) {
        int d = (int)Math.sqrt(((c1.x-c2.x)*(c1.x-c2.x)) + ((c1.y-c2.y)*(c1.y-c2.y)));
        return d;
    }

    Coord[] rotateCoords(Coord[] coords, Coord c, int r) {
        Coord[] c2 = new Coord[coords.length];
        int dx = c.x;
        int dy = c.y;

        double rad = r*Math.PI/180;

        int px,py;
        int x,y;
        for (int i = 0; i < coords.length; i++) {
            x = coords[i].x-dx;
            y = coords[i].y-dy;
            //forgatás
            px = (int)(x*Math.cos(rad)-y*Math.sin(rad));
            py = (int)(x*Math.sin(rad)+y*Math.cos(rad));
            px = px+dx;
            py = py+dy;
            c2[i] = new Coord(px,py);
        }
        return c2;
    }

    Coord[] getHalfPoints(Coord[] coords) {
        Coord[] fp = new Coord[coords.length];
        Coord p1,p2;
        for (int i = 0; i < coords.length; i++) {
            p1 = coords[i];
            if (i < coords.length-1) {
                p2 = coords[i+1];
            }
            else {
                p2 = coords[0];
            }
            fp[i] = new Coord((p1.x+p2.x)/2, (p1.y+p2.y)/2);
        }

        return fp;
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

    private void playSnapSound() {
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.snap);
        mp.start();
    }

}
