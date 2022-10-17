package comp1110.ass2.gui.Catanboard;
import comp1110.ass2.gui.Player.Player;

public abstract class tilesforBStructures extends tile {
    private Player ownerofstructure;
    private boolean isstructureowned;
    public tilesforBStructures (int x,int y){
        super(x,y);
        ownerofstructure=null;
        isstructureowned=false;
    }
    public boolean getisstructureowned(){
        return isstructureowned;
    }

    public void setisstructureowned(){
        this.isstructureowned = true;
    }
    public Player getOwnerofstructure(){
        return ownerofstructure;
    }
    public void setOwnerofstructure(Player ownstructure){
        this.ownerofstructure= ownstructure;
    }
}
