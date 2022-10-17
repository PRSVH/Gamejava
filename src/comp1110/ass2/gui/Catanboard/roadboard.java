package comp1110.ass2.gui.Catanboard;


//road can be horizontal or diagonal
public class roadboard extends tilesforBStructures{
    public enum orientationroad {
        DIAGRIGHTL,
        DIAGLEFT,
        HORIZONTAL
    }
    private roadboard.orientationroad orientation;
    public roadboard (orientationroad orientation,int x,int y){
        super(x,y);
        this.orientation=orientation;
    }
    public roadboard.orientationroad getOrientation(){
        return orientation;
    }

}