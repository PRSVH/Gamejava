package comp1110.ass2.gui.Catanboard;
//deals with where the tiles are to be places onto the game board
public class placement {
    private int x,y;
    public placement(int xcoordinate,int ycoordinate){
        x= xcoordinate;
        y=ycoordinate;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
