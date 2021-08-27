package loranttoth.dragshapes.data;

import android.graphics.Paint;
import loranttoth.dragshapes.Coord;

public class ShapeSquare extends ShapeData {

    public ShapeSquare(int id, int color, Paint paint) {
        this.setId(id);
        this.setColor(color);
        this.setPaint(paint);
        this.setType(ShapeTypes.types.SQUARE);
        this.setSnapped(false);
    }

    @Override
    public void setCenter(Coord center) {
        this.center = new Coord(center.x,center.y);
    }


    @Override
    public void makeCoords() {
            Coord[] coord = new Coord[4];
            coord[0] = new Coord(center.x, center.y);
            coord[1] = new Coord(center.x + (sizex*unit), center.y);
            coord[2] = new Coord(center.x + (sizex*unit), center.y - (sizex*unit));
            coord[3] = new Coord(center.x , center.y - (sizex*unit));
            this.setCoords(coord);
            center = new Coord(center.x+(sizex*unit/2),center.y-(sizex*unit/2));
    }
}
