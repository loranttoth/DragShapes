package loranttoth.dragshapes.data;

import android.graphics.Paint;
import loranttoth.dragshapes.Coord;

public class ShapeTriangle1 extends ShapeData {

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    int dir;

    public ShapeTriangle1(int id, int color, Paint paint) {
        this.setId(id);
        this.setColor(color);
        this.setPaint(paint);
        this.setType(ShapeTypes.types.SQUARE);
        this.setSnapped(false);
    }

    @Override
    public void setCenter(Coord center) {
        this.center = new Coord(center.x , center.y);
    }

    @Override
    public void makeCoords() {
            Coord[] coord = new Coord[3];
            if (dir == 1) {
                coord[0] = new Coord(center.x, center.y);
                coord[1] = new Coord(center.x + (sizex * unit), center.y);
                coord[2] = new Coord(center.x, center.y - (sizex * unit));
            }
            else {
                coord[0] = new Coord(center.x, center.y);
                coord[1] = new Coord(center.x - (sizex * unit), center.y);
                coord[2] = new Coord(center.x, center.y - (sizex * unit));
            }
            setCoords(coord);
            center = getCentroid();
    }
}
