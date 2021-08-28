package loranttoth.dragshapes.data;

import android.graphics.Paint;

import loranttoth.dragshapes.Coord;

/**
 * Created by freestate on 2018.08.16..
 */
public abstract class ShapeData {

    public static int unit = 50;
    public static int sizex = 2;
    public static int sizey = 2;

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getIrany() {
        return irany;
    }

    public void setIrany(int irany) {
        this.irany = irany;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSnapped() {
        return snapped;
    }

    public void setSnapped(boolean snapped) {
        this.snapped = snapped;
    }

    protected int id;
    protected ShapeTypes.types type;
    protected Coord[] coords;
    protected Coord center;
    protected int color;
    protected int deg;
    protected int irany;
    protected boolean snapped;
    protected Paint paint;

    public ShapeTypes.types getType() {
        return type;
    }

    public void setType(ShapeTypes.types type) {
        this.type = type;
    }

    public Coord[] getCoords() {
        return coords;
    }

    public void setCoords(Coord[] coords) {
        this.coords = coords;
    }

    public Coord getCenter() {
        return center;
    }

    public abstract void setCenter(Coord center);

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public abstract void makeCoords();

    public Coord[] rotateCoords(Coord c, int r0) {
        Coord[] c2 = new Coord[coords.length];
        for (int i = 0; i < coords.length; i++) {
            Coord coordP = ShapeData.rotateCoord(coords[i], c, r0);
            c2[i] = coordP;
        }
        return c2;
    }

    public static Coord rotateCoord(Coord coord, Coord c, int r) {
        int dx = c.x;
        int dy = c.y;

        double rad = r*Math.PI/180;

        int px,py;
        int x,y;
            x = coord.x-dx;
            y = coord.y-dy;

            //forgatás
            px = (int)(x*Math.cos(rad)-y*Math.sin(rad));
            py = (int)(x*Math.sin(rad)+y*Math.cos(rad));
            px = px+dx;
            py = py+dy;
            return new Coord(px,py);
    }

    public static Coord[] getHalfPoints(Coord[] coords) {
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

    public Coord getCentroid_old() {
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

    public Coord getCentroid() {
        Coord ca = coords[1];
        Coord cc = coords[2];
        int x = (int)((ca.x+cc.x)/2);
        int y = (int)((ca.y+cc.y)/2);
        return new Coord(x,y);
    }
}
