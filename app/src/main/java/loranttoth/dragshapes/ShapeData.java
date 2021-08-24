package loranttoth.dragshapes;

import android.graphics.Paint;

/**
 * Created by freestate on 2018.08.16..
 */
public class ShapeData {

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getSizeX() {
        return sizex;
    }

    public void setSizeX(int sizex) {
        this.sizex = sizex;
    }

    public int getSizeY() {
        return sizey;
    }

    public void setSizeY(int sizey) {
        this.sizey = sizey;
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

    public Coord[] getCoords2() {
        return coords2;
    }

    public void setCoords2(Coord[] coords2) {
        this.coords2 = coords2;
    }

    public enum types {
        SQUARE,
        RECTANGLE,
        TRIANGLE1,
        TRIANGLE2,
        TRIANGLE3,
        PARALELOGRAMA
        //CIRCLE,
        //OVAL
    }

    private int id;
    private types type;
    private Coord[] coords;
    private Coord[] coords2;
    private Coord center;
    private int color;
    private int deg;
    private int sizex;
    private int sizey;
    private int irany;
    private boolean snapped;
    private Paint paint;

    public ShapeData(int id, types type, Coord[] coords, Coord[] coords2, Coord center, int color, int deg, int sizex, int sizey, int irany, Paint paint) {
        this.setId(id);
        this.setType(type);
        this.setCoords(coords);
        this.setCoords2(coords2);
        this.setCenter(center);
        this.setColor(color);
        this.setDeg(deg);
        this.setSizeX(sizex);
        this.setSizeY(sizey);
        this.setIrany(irany);
        this.setPaint(paint);
        this.snapped = false;
    }

    public types getType() {
        return type;
    }

    public void setType(types type) {
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

    public void setCenter(Coord center) {
        this.center = center;
    }

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
}
