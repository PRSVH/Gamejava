package comp1110.ass2.gui.Player;

import comp1110.ass2.gui.resources.*;
import comp1110.ass2.gui.Structures.*;

import java.util.ArrayList;

import static comp1110.ass2.gui.Structures.structures.*;

public class Player {
    private String name;
    private int[] resourcestate = {0,0,0,0,0,0}; //{NumORE,NumGRAIN,NumWOOL,NumTIMBER,NumBRICK,NumGOLD}
    //private int TotalNumResources;
    private int NumberOfRoads;
    private int NumberOfCities;
    private int NumberOfSettlements;
    private int NumberOfknights;
    private int score;
    private int turn;
    private String [] boardstate;
    private int turnscore;
    private int totalScore;


    public Player(String name){
        this.name = name;
        this.score = 0;
        this.turn = 0;
        this.NumberOfknights=0;
        this.NumberOfCities=0;
        this.NumberOfRoads=0;
        this.NumberOfSettlements=0;
        this.turnscore=0;
        //TotalNumResources=0;
        this.resourcestate=new int[6];
        this.totalScore=0;
    }
    public String getName() {
        return name;
    }
    public int getNumberOfSettlements() {
        return NumberOfSettlements;
    }
    public int getNumberOfRoads(){
        return NumberOfRoads;
    }
    public int getNumberOfCities(){
        return NumberOfCities;
    }
    public int getNumberOfknights(){
        return NumberOfknights;
    }

    public void setResourcestate(int[] resource){
        resourcestate=resource;
    }
    public int[] getResourcestate() {
        return resourcestate;
    }
    private void useresource(Player current , structures Structure){
        switch (Structure){
            case ROAD -> { //1 Brick and 1 Timber
                current.resourcestate[3]--;
                current.resourcestate[4]--;
            }
            case CITY -> { // 1 Brick, 1 Timber, 1 wool, 1 grain
                current.resourcestate[1]--;
                current.resourcestate[2]--;
                current.resourcestate[3]--;
                current.resourcestate[4]--;
            }
            case SETTLEMENT -> { // 3 Ores, 2 grains
                current.resourcestate[0]=resourcestate[0]-3;
                current.resourcestate[1]=resourcestate[1]-2;

            }
            case KNIGHT -> { // 1 Ore, 1 Wool, 1 Grain
                current.resourcestate[1]--;
                current.resourcestate[2]--;
                current.resourcestate[0]--;

            }
        }

    }
    public void buildroad(){
        this.NumberOfRoads++;
        this.useresource(this ,ROAD);

    }
    public void buildcities(){
        this.NumberOfCities++;
        this.useresource(this ,CITY);
    }
    public void buildSettlement(){
        this.NumberOfSettlements++;
        this.useresource(this,SETTLEMENT);
    }
    public void buildKnight(){
        this.NumberOfknights++;
        this.useresource(this,KNIGHT);
    }
    public int getTotalScore()
    {
        return totalScore;
    }
    public void addTotalScore(int score)
    {
        totalScore += score;
    }
    public int getTurnScore()
    {
        return this.turnscore;
    }
    public void addTurnScore(int score)
    {
        this.turnscore += score;
    }
    public void clearTurnScore()
    {
        this.turnscore = 0;
    }

    public void endturn(){ //clear resourcestate at the end of player's turn
        this.resourcestate=null;
        turn++;
    }

    public void traderesources(Resources tobetraded){ //trading 2 golds for a resource tobetraded
        this.resourcestate[5]=this.resourcestate[5]-2;
        this.resourcestate[tobetraded.hashCode()]++;
    }

    public void swap(Resources beforeswap, Resources afterswap){ //need to change K to J
        this.resourcestate[beforeswap.hashCode()]--;
        this.resourcestate[afterswap.hashCode()]++;
    }

}

