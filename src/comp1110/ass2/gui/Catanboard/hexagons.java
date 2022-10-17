package comp1110.ass2.gui.Catanboard;

import java.awt.*;

public class hexagons {
    public final static boolean orFLAT= true;
    public final static boolean orPOINT= false;
    public static boolean ORIENT= orFLAT;  //this is not used. We're never going to do pointy orientation

    public static boolean XYVertex=true;	//true: x,y are the co-ords of the first vertex.
    //false: x,y are the co-ords of the top left rect. co-ord.

    private static int BORDERS=200;	//default number of pixels for the border.

    private static int s=0;	// length hexagon side
    private static int t=0;
    private static int r=0;
    private static int h=0;

    public static void setXYasVertex(boolean b) {
        XYVertex=b;
    }
    public static void setBorders(int b){
        BORDERS=b;
    }

    //dimensions of hexagon
    public static void setSide(int side) {
        s=side;
        t =  (int) (s / 2);
        r =  (int) (s * 0.8660254037844);	//r = s cos(30)
        h=2*r;
    }
    public static void setHeight(int height) {
        h = height;			// h = basic dimension: height (distance between two adj centresr aka size)
        r = h/2;			// r = radius of inscribed circle
        s = (int) (h / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
        t = (int) (r / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
    }

    public static Polygon hex (int x0, int y0) {

        int y = y0 + BORDERS;
        int x = x0 + BORDERS;
        int[] cx,cy;

        if (XYVertex)
            cx = new int[] {x,x+s,x+s+t,x+s,x,x-t};
        else
            cx = new int[] {x+t,x+s+t,x+s+t+t,x+s+t,x+t,x};

        cy = new int[] {y,y,y+r,y+r+r,y+r+r,y+r};
        return new Polygon(cx,cy,6);
    }

    public static void drawHex(int i, int j, Graphics2D g2) {
        int x = i * (s+t);
        int y = j * h + (i%2) * h/2;
        Polygon poly = hex(x,y);
        //g2.setColor(hexgame.COLOURCELL);
        //g2.fillPolygon(hexmech.hex(x,y));
        g2.fillPolygon(poly);
        //g2.setColor(hexgame.COLOURGRID);
        g2.drawPolygon(poly);
    }

    }

