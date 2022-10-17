package comp1110.ass2;

import java.util.Arrays;

/**
 * Contributor: Wei Wei
 */
public class Player {
    private int[] resource_state;
    int[] scores = new int[]{0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0};
    private int turn = 1;

    public Player() {
        resource_state = new int[6];
    }

    public Player(int[] resource_state){
        this.resource_state = resource_state;
    }

    public void firstRoll(){
        CatanDice.rollDice(6, resource_state);
    }


    public boolean roll_again(String[] num){
        int[] dices = new int[num.length];
        int[] rs = Arrays.copyOf(resource_state, 6);
        int num_of_dices = 0;
        for (int i = 0; i < num.length; i++) {
            if (num[i].equals("")){
                dices[i] = 0;
            } else {
                dices[i] = Integer.parseInt(num[i]);
            }
            rs[i] -= dices[i];
            num_of_dices += dices[i];
            if (dices[i] > 6 || rs[i] < 0){
                System.out.println("dice not correct");
                return false;
            }
        }
        resource_state = rs;
        CatanDice.rollDice(num_of_dices, resource_state);
        return true;
    }

    /**
     * Assuming satisfy the constraint
     * @param construction construction want to build
     * @return true if build successfully
     */
    public boolean build(Construction construction){
        String commend = "build " + construction;
        int[] rs = resource_state;
        int[] requiredResources = construction.getRequiredResources();
        for (int i = 0; i < 6; i++) {
            rs[i] = rs[i] - requiredResources[i];
            if (rs[i]<0){
                return false;
            }
        }
        //update resource state and score
        resource_state = rs;
        scores[turn-1]+=construction.getPoints();
        return true;
    }

    /**
     * trade resource
     * @param resource int present resource want to trade
     * @return true if trade successfully
     */
    public boolean trade(int resource){
        if (resource_state[5]>=2){
            resource_state[resource]++;
            resource_state[5]-=2;
            return true;
        }
        return false;
    }

    public void swap(int resource, int target){
        resource_state[resource]--;
        resource_state[target]++;
    }

    public void nextTurn(){
        if (scores[turn-1]==0){
            //no construction was built in this turn
            scores[turn-1] = -2;
        }
        resource_state = new int[]{0, 0, 0, 0, 0, 0};
        turn++;
    }

    public int[] getResource_state() {
        return resource_state;
    }

    public int getTurn() {
        return turn;
    }

    public int totalScore(){
        int total_score = 0;
        for (int score : scores) {
            total_score += score;
        }
        return total_score;
    }


    /**
     * Contributor: Wenxiao Wu
     */

    public void restartTurn(){
        turn=1;
        scores=new int[]{0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0};
        resource_state = new int[]{0, 0, 0, 0, 0, 0};
    }


}
