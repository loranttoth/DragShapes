package loranttoth.dragshapes.data;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

import java.util.Random;
import java.util.Vector;

import loranttoth.dragshapes.Coord;

public class ImageData {

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    int type;

    public static int max = 14;

    public ShapeTypes.types[] shapeTypes;
    public int[] shapeDir;

    public Coord[] shapeCoords;

    public static int getMax() {
        return max;
    }

    public static void setMax(int max) {
        ImageData.max = max;
    }

    public Vector<ShapeData> shapes;

    public int sminx;
    public int smaxx;
    public int sminy;
    public int smaxy;

    public ImageData(int type) {
        this.setType(type);
        shapes = new Vector<>();
    }

    public void makeShapeCoords(int w, int h) {
        System.out.println("unit: " + ShapeData.unit);

        switch (type) {

            case 0:
                //square
                shapeCoords = new Coord[4];
                //shapeCoords[0] = new Coord(0 + sdx, 0 + sdy);
                //shapeCoords[1] = new Coord(w + sdx, 0 + sdy);
                //shapeCoords[2] = new Coord(w + sdx, w + sdy);
                //shapeCoords[3] = new Coord(0 + sdx, w + sdy);

                shapeCoords[0] = new Coord(304, 568);
                shapeCoords[1] = new Coord(772, 570);
                shapeCoords[2] = new Coord(774, 100);
                shapeCoords[3] = new Coord(306, 100);

                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 1;

                break;
            case 1:
                //triangle
                shapeCoords = new Coord[3];
                //shapeCoords[0] = new Coord(0 + sdx, h + sdy);
                //shapeCoords[1] = new Coord(w2 + sdx, h + sdy);
                //shapeCoords[2] = new Coord(w + sdx, 0 + sdy);

                shapeCoords[0] = new Coord(99, 652);
                shapeCoords[1] = new Coord(1037, 652);
                shapeCoords[2] = new Coord(569, 183);

                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 1;

                break;
            case 2:
                //trapeze
                shapeCoords = new Coord[4];
                //shapeCoords[0] = new Coord(0 + sdx, a + sdy);
                //shapeCoords[1] = new Coord((3*a) + sdx, a + sdy);
                //shapeCoords[2] = new Coord((2*a) + sdx, 0 + sdy);
                //shapeCoords[3] = new Coord(a + sdx, 0 + sdy);

                shapeCoords[0] = new Coord(42, 432);
                shapeCoords[1] = new Coord(1038, 432);
                shapeCoords[2] = new Coord(706, 100);
                shapeCoords[3] = new Coord(374, 100);

                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 2;
                shapeDir[6] = 2;

                break;
            case 3:
                //paralelo
                shapeCoords = new Coord[4];
                //shapeCoords[0] = new Coord(0 + sdx, a + sdy);
                //shapeCoords[1] = new Coord((2*a) + sdx, a + sdy);
                //shapeCoords[2] = new Coord((3*a) + sdx, 0 + sdy);
                //shapeCoords[3] = new Coord(a + sdx, 0 + sdy);

                shapeCoords[0] = new Coord(44, 432);
                shapeCoords[1] = new Coord(707, 430);
                shapeCoords[2] = new Coord(1037, 100);
                shapeCoords[3] = new Coord(375, 99);

                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 2;

                break;
            case 4:
                //rect
                shapeCoords = new Coord[4];
                //shapeCoords[0] = new Coord(0 + sdx, a + sdy);
                //shapeCoords[1] = new Coord((2*a) + sdx, a + sdy);
                //shapeCoords[2] = new Coord((2*a) + sdx, 0 + sdy);
                //shapeCoords[3] = new Coord(0 + sdx, 0 + sdy);

                shapeCoords[0] = new Coord(208, 433);
                shapeCoords[1] = new Coord(871, 431);
                shapeCoords[2] = new Coord(870, 100);
                shapeCoords[3] = new Coord(208, 102);

                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 2;

                break;

            case 5:
                //hammer
                shapeCoords = new Coord[8];
                //shapeCoords[0] = new Coord(0 + sdx, 0 + sdy);
                //shapeCoords[1] = new Coord(b + sdx, 0 + sdy);
                //shapeCoords[2] = new Coord(b + sdx, sdy-(3*b));
                //shapeCoords[3] = new Coord(0 + sdx+w, sdy-(3*b));
                //shapeCoords[4] = new Coord(0 + (sdx+w)-w4, sdy-((3*b)+w2));
                //shapeCoords[5] = new Coord(sdx-(w/2), sdy-((3*b)+w2));
                //shapeCoords[6] = new Coord(sdx-(w/2), sdy-(3*b));
                //shapeCoords[7] = new Coord(sdx, sdy-(3*b));

                shapeCoords[0] = new Coord(423, 832);
                shapeCoords[1] = new Coord(588, 831);
                shapeCoords[2] = new Coord(589, 334);
                shapeCoords[3] = new Coord(892, 333);
                shapeCoords[4] = new Coord(658, 100);
                shapeCoords[5] = new Coord(189, 99);
                shapeCoords[6] = new Coord(189, 333);
                shapeCoords[7] = new Coord(424, 335);


                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 2;

                break;

            case 6:
                //square 2
                shapeCoords = new Coord[4];
                //shapeCoords[0] = new Coord(0 + sdx, 0 + sdy);
                //shapeCoords[1] = new Coord(w + sdx, 0 + sdy);
                //shapeCoords[2] = new Coord(w + sdx, w + sdy);
                //shapeCoords[3] = new Coord(0 + sdx, w + sdy);

                shapeCoords[0] = new Coord(304, 568);
                shapeCoords[1] = new Coord(772, 570);
                shapeCoords[2] = new Coord(774, 100);
                shapeCoords[3] = new Coord(306, 100);

                shapeTypes = new ShapeTypes.types[6];
                shapeTypes[0] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;

                shapeDir = new int[6];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                break;

            case 7:
                //trapeze 2
                shapeCoords = new Coord[4];
                //shapeCoords[0] = new Coord(0 + sdx, 0 + sdy);
                //shapeCoords[1] = new Coord(w2 + sdx, 0 + sdy);
                //shapeCoords[2] = new Coord(w + w2 + sdx, w + sdy);
                //shapeCoords[3] = new Coord(0 + sdx, w + sdy);

                shapeCoords[0] = new Coord(189, 569);
                shapeCoords[1] = new Coord(892, 569);
                shapeCoords[2] = new Coord(423, 101);
                shapeCoords[3] = new Coord(190, 102);

                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 1;
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

                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 1;

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


                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 2;

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


                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;

                shapeDir = new int[7];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 2;

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

                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;


                shapeDir = new int[8];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 2;

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

                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;


                shapeDir = new int[8];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 2;

                break;

            case 13:
                //house
                shapeCoords = new Coord[11];

                shapeCoords[0] = new Coord(126, 1296);
                shapeCoords[1] = new Coord(622, 1295);
                shapeCoords[2] = new Coord(621, 1129);
                shapeCoords[3] = new Coord(704, 1131);
                shapeCoords[4] = new Coord(372, 799);
                shapeCoords[5] = new Coord(323, 847);
                shapeCoords[6] = new Coord(323, 613);
                shapeCoords[7] = new Coord(207, 731);
                shapeCoords[8] = new Coord(207, 965);
                shapeCoords[9] = new Coord(42, 1131);
                shapeCoords[10] = new Coord(125, 1131);


                shapeTypes = new ShapeTypes.types[7];
                shapeTypes[0] = ShapeTypes.types.SQUARE;
                shapeTypes[1] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[2] = ShapeTypes.types.TRIANGLE1;
                shapeTypes[3] = ShapeTypes.types.TRIANGLE2;
                shapeTypes[4] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[5] = ShapeTypes.types.TRIANGLE3;
                shapeTypes[6] = ShapeTypes.types.PARALELOGRAMA;


                shapeDir = new int[8];
                shapeDir[0] = 1;
                shapeDir[1] = 1;
                shapeDir[2] = 1;
                shapeDir[3] = 1;
                shapeDir[4] = 1;
                shapeDir[5] = 1;
                shapeDir[6] = 1;

                break;


        }

        sminx = 999999;
        smaxx = -999999;
        sminy = 999999;
        smaxx = -999999;
        Coord coord;

        /*for (int i = 0; i < shapeCoords.length; i++) {
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

        int l = smaxx - sminx;
        int l2 = w - 50;
        float l3 = (float)l2 / (float)l;

        ShapeData.unit *= l3;*/

        float uc = ShapeData.unit / 83f;
        //float uc = 1;



        System.out.println("unit: " + ShapeData.unit + " uc: " + uc);

        for (int i = 0; i < shapeCoords.length; i++) {
            shapeCoords[i].x *= uc;
            shapeCoords[i].y *= uc;
        }

        sminx = 999999;
        smaxx = -999999;
        sminy = 999999;
        smaxx = -999999;

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

        int sch = w / 2;
        int scv = h / 2;

        int pch = (sminx + smaxx) / 2;
        int pcv = (sminy + smaxy) / 2;

        int hd = sch - pch;
        int vd = scv - pcv;
        //int vd = 100 - sminy;

        for (int i = 0; i < shapeCoords.length; i++) {
            shapeCoords[i].x += hd;
            shapeCoords[i].y += vd;
        }
    }

    public void makeShapes(int w, int h) {
        //makeshapes
        Random rnd = new Random();
        int shapeId = 0;
        for (int i = 0; i < shapeTypes.length; i++) {
            try {
                Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
                int x = rnd.nextInt(w-200)+50;
                int y = rnd.nextInt((h/2)-200) + (h/2)+100;
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
                ShapeTypes.types type = shapeTypes[i];
                Coord[] coords = null;
                ShapeData data = null;
                switch (type) {
                    case SQUARE:
                        data = new ShapeSquare(shapeId++, color, paint1);
                        data.setCenter(center);
                        data.makeCoords();
                        break;
                    case RECTANGLE:
                        data = new ShapeRectangle(shapeId++, color, paint1);
                        data.setCenter(center);
                        data.makeCoords();
                        break;
                    case TRIANGLE1:
                        data = new ShapeTriangle1(shapeId++, color, paint1);
                        data.setCenter(center);
                        ((ShapeTriangle1)data).setDir(shapeDir[i]);
                        data.makeCoords();
                        break;
                    case TRIANGLE2:
                        data = new ShapeTriangle2(shapeId++, color, paint1);
                        data.setCenter(center);
                        ((ShapeTriangle2)data).setDir(shapeDir[i]);
                        data.makeCoords();
                        break;
                    case TRIANGLE3:
                        data = new ShapeTriangle3(shapeId++, color, paint1);
                        data.setCenter(center);
                        ((ShapeTriangle3)data).setDir(shapeDir[i]);
                        data.makeCoords();
                        break;
                    case PARALELOGRAMA:
                        data = new ShapeParalelogram(shapeId++, color, paint1);
                        data.setCenter(center);
                        ((ShapeParalelogram)data).setDir(shapeDir[i]);
                        data.makeCoords();
                        break;
                }

                data.setDeg(deg);
                data.setType(type);

                //ShapeData data = new ShapeData(shapeId++, type, coords, center, color, deg, sizex, sizey, irany, paint1);
                shapes.addElement(data);
                System.out.println("data: "+shapes.size());
            }catch (Exception e) {
                System.out.println("error: "+e);

                //parent.showMess("error: "+e.getLocalizedMessage());
            }
        }
    }

    public boolean checkAllConnected() {
        boolean isConnected = true;

        for (int c = 0; c < shapes.size(); c++) {
            ShapeData shape = shapes.elementAt(c);
            Coord coord;
            Coord[] coords = shape.rotateCoords(shape.getCenter(), shape.getDeg());
            Coord[] fp = ShapeData.getHalfPoints(coords);

            Coord[] coords1;
            Coord[] fp1;
            Coord coord1;
            ShapeData shape1;
            boolean ok = false;
            for (int i = 0; i < shapes.size(); i++) {
                shape1 = shapes.elementAt(i);
                if (shape1.getId() == shape.getId())
                    continue;
                coords1 = shape1.rotateCoords(shape1.getCenter(), shape1.getDeg());
                fp1 = ShapeData.getHalfPoints(coords1);
                for (int j = 0; j < coords.length; j++) {
                    coord = coords[j];
                    for (int k = 0; k < coords1.length; k++) {
                        coord1 = coords1[k];
                        if (coord.x == coord1.x && coord.y == coord1.y) {
                            ok = true;
                            break;
                        }
                    }
                    if (!ok) {
                        for (int k = 0; k < fp1.length; k++) {
                            coord1 = fp1[k];
                            if (coord.x == coord1.x && coord.y == coord1.y) {
                                ok = true;
                                break;
                            }
                        }
                    }
                }
                if (!ok){
                    for (int j = 0; j < fp.length; j++) {
                        coord = fp[j];
                        for (int k = 0; k < coords1.length; k++) {
                            coord1 = coords1[k];
                            if (coord.x == coord1.x && coord.y == coord1.y) {
                                ok = true;
                                break;
                            }
                        }
                    }
                }
                if (!ok) {
                    coords1 = shapeCoords;
                    fp1 = ShapeData.getHalfPoints(coords1);
                    for (int j = 0; j < coords.length; j++) {
                        coord = coords[j];
                        for (int k = 0; k < coords1.length; k++) {
                            coord1 = coords1[k];
                            if (coord.x == coord1.x && coord.y == coord1.y) {
                                ok = true;
                                break;
                            }
                        }
                    }
                    if (!ok) {
                        for (int j = 0; j < coords.length; j++) {
                            coord = coords[j];
                            for (int k = 0; k < fp1.length; k++) {
                                coord1 = fp1[k];
                                if (coord.x == coord1.x && coord.y == coord1.y) {
                                    ok = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!ok) {
                        for (int j = 0; j < fp.length; j++) {
                            coord = fp[j];
                            for (int k = 0; k < coords1.length; k++) {
                                coord1 = coords1[k];
                                if (coord.x == coord1.x && coord.y == coord1.y) {
                                    ok = true;
                                    break;
                                }
                            }
                            for (int k = 0; k < fp1.length; k++) {
                                coord1 = fp1[k];
                                if (coord.x == coord1.x && coord.y == coord1.y) {
                                    ok = true;
                                    break;
                                }
                            }

                        }
                    }
                }
            }
            if (!ok)
                isConnected = false;
        }
        return isConnected;
    }
}
