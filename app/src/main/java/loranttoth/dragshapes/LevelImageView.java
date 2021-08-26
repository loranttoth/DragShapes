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

/**
 * TODO: document your custom view class.
 */
public class LevelImageView extends View {
        Context context;
        Paint paint;

        Paint paintbig;
        Paint paintbig2;

        int unit = 50;
        int sizex = 2;
        int sizey = 2;

        int sdx;
        int sdy;

        Coord[] shapeCoords;

        int shapeColor = Color.LTGRAY;
        int shapeColor2 = Color.DKGRAY;

        int sminx;
        int smaxx;
        int sminy;
        int smaxy;

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


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int maxx = getWidth();
            int maxy = getHeight();
            int w = getWidth();
            int h = getHeight();
            if (shapeCoords == null && maxx > 0 && maxy > 0){
                int min = Math.min(w, h);
                unit = min/13;
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
            path.moveTo(shapeCoords[0].x, shapeCoords[0].y);
            for (int i = 1; i < shapeCoords.length; i++) {
                path.lineTo(shapeCoords[i].x, shapeCoords[i].y);
            }
            path.lineTo(shapeCoords[0].x, shapeCoords[0].y);
            path.close();
            canvas.drawPath(path, paintbig2);
            canvas.restore();

            canvas.save();
            path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.moveTo(shapeCoords[0].x, shapeCoords[0].y);
            for (int i = 1; i < shapeCoords.length; i++) {
                path.lineTo(shapeCoords[i].x, shapeCoords[i].y);
            }
            path.lineTo(shapeCoords[0].x, shapeCoords[0].y);
            path.close();
            canvas.drawPath(path, paintbig);
            canvas.restore();
        }

        void makeShapeCoords() {
            Random rnd = new Random();
            int max = 14;
            int r = rnd.nextInt(max);

            //r = 8;
            //r = 13;
            //r = 1;

            int a;
            int w;

            int sw = getWidth();
            int sh = getHeight();

            System.out.println("unit: "+unit);



            switch(r) {

                case 0:
                    //square
                    a = sizex * unit * 2;
                    w = (int) Math.sqrt((a * a) + (a * a));
                    sdx = 50;
                    sdy = 100;

                    shapeCoords = new Coord[4];
                    shapeCoords[0] = new Coord(304, 568);
                    shapeCoords[1] = new Coord(772, 570);
                    shapeCoords[2] = new Coord(774, 100);
                    shapeCoords[3] = new Coord(306, 100);

                    break;
                case 1:

                    shapeCoords = new Coord[3];

                    shapeCoords[0] = new Coord(99, 652);
                    shapeCoords[1] = new Coord(1037, 652);
                    shapeCoords[2] = new Coord(569, 183);
                    break;
                case 2:
                    //trapeze

                    shapeCoords = new Coord[4];

                    shapeCoords[0] = new Coord(42, 432);
                    shapeCoords[1] = new Coord(1038, 432);
                    shapeCoords[2] = new Coord(706, 100);
                    shapeCoords[3] = new Coord(374, 100);


                    break;
                case 3:
                    //paralelo

                    shapeCoords = new Coord[4];

                    shapeCoords[0] = new Coord(44, 432);
                    shapeCoords[1] = new Coord(707, 430);
                    shapeCoords[2] = new Coord(1037, 100);
                    shapeCoords[3] = new Coord(375, 99);

                    break;
                case 4:
                    //rect

                    shapeCoords = new Coord[4];

                    shapeCoords[0] = new Coord(208, 433);
                    shapeCoords[1] = new Coord(871, 431);
                    shapeCoords[2] = new Coord(870, 100);
                    shapeCoords[3] = new Coord(208, 102);

                    break;

                case 5:
                    //hammer

                    shapeCoords = new Coord[8];

                    shapeCoords[0] = new Coord(423, 832);
                    shapeCoords[1] = new Coord(588, 831);
                    shapeCoords[2] = new Coord(589, 334);
                    shapeCoords[3] = new Coord(892, 333);
                    shapeCoords[4] = new Coord(658, 100);
                    shapeCoords[5] = new Coord(189, 99);
                    shapeCoords[6] = new Coord(189, 333);
                    shapeCoords[7] = new Coord(424, 335);

                    break;

                case 6:
                    //square 2

                    shapeCoords = new Coord[4];

                    shapeCoords[0] = new Coord(304, 568);
                    shapeCoords[1] = new Coord(772, 570);
                    shapeCoords[2] = new Coord(774, 100);
                    shapeCoords[3] = new Coord(306, 100);
                    break;

                case 7:
                    //trapeze 2
                    shapeCoords = new Coord[4];
                    shapeCoords[0] = new Coord(189, 569);
                    shapeCoords[1] = new Coord(892, 569);
                    shapeCoords[2] = new Coord(423, 101);
                    shapeCoords[3] = new Coord(190, 102);

                    break;
                case 8:
                    //bird1
                    shapeCoords = new Coord[13];

                    shapeCoords[0] = new Coord(392, 1112);
                    shapeCoords[1] = new Coord(723, 1112);
                    shapeCoords[2] = new Coord(888, 946);
                    shapeCoords[3] = new Coord(888, 712);
                    shapeCoords[4] = new Coord(771, 595);
                    shapeCoords[5] = new Coord(770, 526);
                    shapeCoords[6] = new Coord(936, 526);
                    shapeCoords[7] = new Coord(770, 360);
                    shapeCoords[8] = new Coord(654, 478);
                    shapeCoords[9] = new Coord(654, 712);
                    shapeCoords[10] = new Coord(722, 780);
                    shapeCoords[11] = new Coord(253, 780);
                    shapeCoords[12] = new Coord(487, 1013);

                    break;

                case 9:
                    //rocket
                    shapeCoords = new Coord[10];

                    shapeCoords[0] = new Coord(433, 1097);
                    shapeCoords[1] = new Coord(598, 931);
                    shapeCoords[2] = new Coord(763, 1095);
                    shapeCoords[3] = new Coord(762, 763);
                    shapeCoords[4] = new Coord(928, 929);
                    shapeCoords[5] = new Coord(928, 763);
                    shapeCoords[6] = new Coord(595, 432);
                    shapeCoords[7] = new Coord(266, 765);
                    shapeCoords[8] = new Coord(267, 931);
                    shapeCoords[9] = new Coord(432, 765);
                    break;

                case 10:
                    //cat
                    shapeCoords = new Coord[17];
                    shapeCoords[0] = new Coord(466, 1363);
                    shapeCoords[1] = new Coord(797, 1362);
                    shapeCoords[2] = new Coord(1000, 1245);
                    shapeCoords[3] = new Coord(1043, 1086);
                    shapeCoords[4] = new Coord(840, 1203);
                    shapeCoords[5] = new Coord(797, 1362);
                    shapeCoords[6] = new Coord(796, 1031);
                    shapeCoords[7] = new Coord(563, 796);
                    shapeCoords[8] = new Coord(622, 738);
                    shapeCoords[9] = new Coord(622, 504);
                    shapeCoords[10] = new Coord(505, 621);
                    shapeCoords[11] = new Coord(389, 503);
                    shapeCoords[12] = new Coord(388, 738);
                    shapeCoords[13] = new Coord(505, 855);
                    shapeCoords[14] = new Coord(398, 962);
                    shapeCoords[15] = new Coord(564, 1128);
                    shapeCoords[16] = new Coord(563, 1265);

                    break;
                case 11:
                    //fish
                    shapeCoords = new Coord[18];

                    shapeCoords[0] = new Coord(511, 763);
                    shapeCoords[1] = new Coord(511, 680);
                    shapeCoords[2] = new Coord(535, 655);
                    shapeCoords[3] = new Coord(651, 773);
                    shapeCoords[4] = new Coord(651, 539);
                    shapeCoords[5] = new Coord(676, 514);
                    shapeCoords[6] = new Coord(675, 431);
                    shapeCoords[7] = new Coord(792, 548);
                    shapeCoords[8] = new Coord(909, 431);
                    shapeCoords[9] = new Coord(792, 314);
                    shapeCoords[10] = new Coord(675, 431);
                    shapeCoords[11] = new Coord(668, 346);
                    shapeCoords[12] = new Coord(643, 322);
                    shapeCoords[13] = new Coord(643, 88);
                    shapeCoords[14] = new Coord(527, 204);
                    shapeCoords[15] = new Coord(502, 180);
                    shapeCoords[16] = new Coord(509, 100);
                    shapeCoords[17] = new Coord(179, 432);

                    break;

                case 12:
                    //horse
                    shapeCoords = new Coord[18];

                    shapeCoords[0] = new Coord(263, 1224);
                    shapeCoords[1] = new Coord(323, 998);
                    shapeCoords[2] = new Coord(241, 854);
                    shapeCoords[3] = new Coord(474, 1089);
                    shapeCoords[4] = new Coord(482, 1117);
                    shapeCoords[5] = new Coord(648, 1117);
                    shapeCoords[6] = new Coord(482, 951);
                    shapeCoords[7] = new Coord(695, 735);
                    shapeCoords[8] = new Coord(929, 735);
                    shapeCoords[9] = new Coord(813, 619);
                    shapeCoords[10] = new Coord(646, 618);
                    shapeCoords[11] = new Coord(645, 453);
                    shapeCoords[12] = new Coord(811, 453);
                    shapeCoords[13] = new Coord(645, 287);
                    shapeCoords[14] = new Coord(480, 454);
                    shapeCoords[15] = new Coord(481, 619);
                    shapeCoords[16] = new Coord(241, 854);
                    shapeCoords[17] = new Coord(181, 1080);

                    break;

                case 13:
                    //house
                    shapeCoords = new Coord[11];

                    shapeCoords[0] = new Coord(126,1296);
                    shapeCoords[1] = new Coord(622,1295);
                    shapeCoords[2] = new Coord(621,1129);
                    shapeCoords[3] = new Coord(704,1131);
                    shapeCoords[4] = new Coord(372,799);
                    shapeCoords[5] = new Coord(323,847);
                    shapeCoords[6] = new Coord(323,613);
                    shapeCoords[7] = new Coord(207,731);
                    shapeCoords[8] = new Coord(207,965);
                    shapeCoords[9] = new Coord(42,1131);
                    shapeCoords[10] = new Coord(125,1131);

                    break;
            }



            sminx = 999999;
            smaxx = -999999;
            sminy = 999999;
            smaxx = -999999;
            Coord coord;

            float uc = unit / 83f;
            //float uc = 1;

            System.out.println("unit: "+unit+" uc: "+uc);

            for (int i = 0; i < shapeCoords.length; i++) {
                shapeCoords[i].x*=uc;
                shapeCoords[i].y*=uc;
            }

            for (int i = 0; i < shapeCoords.length; i++) {
                coord = shapeCoords[i];
                if (coord.x < sminx)
                    sminx = coord.x;
                if (coord.x > smaxx)
                    smaxx = coord.x;
                if (coord.y < sminy)
                    sminy = coord.y;
                if (coord.y > smaxy)
                    smaxy = coord.y;
            }

            int sch = sw / 2;
            int scv = sh / 2;

            int pch = (sminx + smaxx) / 2;
            int pcv = (sminy + smaxy) / 2;

            int hd = sch - pch;
            int vd = scv - pcv;
            //int vd = 10-sminy;

            for (int i = 0; i < shapeCoords.length; i++) {
                shapeCoords[i].x += hd;
                shapeCoords[i].y += vd;
            }
        }
    }
