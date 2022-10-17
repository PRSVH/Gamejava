package comp1110.ass2.gui.Catanboard;

public class tile {
    private int xcoordinate;
    private int ycoordinate;

    public tile(int xcoordinate, int ycoordinate){
        this.xcoordinate=xcoordinate;
        this.ycoordinate=ycoordinate;
    }
    public int getxcoordinate(){
        return xcoordinate;
    }
    public int getycoordinate(){return ycoordinate;}
}
