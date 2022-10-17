package comp1110.ass2.gui.resources;
import java.util.*;
import comp1110.ass2.gui.Player.Player;
public class die {
    private Integer face;
    private Random random = new Random();

    public void roll() {
        face = random.nextInt(5);
    }

    public Integer getFace() {
        return face;
    }

    // ORE = 0;
    // GRAIN = 1;
    // WOOL = 2;
    // TIMBER = 3;
    // BRICK = 4;
    // GOLD = 5;
    public void incrementresource(Player current) {
        int[] resource = current.getResourcestate();
        switch (getFace()) {
            case 0:
                resource[0]++;
                current.setResourcestate(resource);
            case 1:
                resource[1]++;
                current.setResourcestate(resource);
            case 2:
                resource[2]++;
                current.setResourcestate(resource);
            case 3:
                resource[3]++;
                current.setResourcestate(resource);
            case 4:
                resource[4]++;
                current.setResourcestate(resource);
            case 5:
                resource[5]++;
                current.setResourcestate(resource);
        }
    }

    public Integer[] roll2dice() {
        die die1 = new die();
        die die2 = new die();
        die1.roll();
        die2.roll();
        return (new Integer[]{die1.getFace(), die2.getFace()});
    }

    public Integer[] roll3dice() {
        die die1 = new die();
        die die2 = new die();
        die die3 = new die();
        die1.roll();
        die2.roll();
        die3.roll();
        return (new Integer[]{die1.getFace(), die2.getFace(), die3.getFace()});
    }
    public Integer[] roll4dice() {
        die die1 = new die();
        die die2 = new die();
        die die3 = new die();
        die die4 = new die();
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        return (new Integer[]{die1.getFace(), die2.getFace(), die3.getFace(),die4.getFace()});
    }
    public Integer[] roll5dice() {
        die die1 = new die();
        die die2 = new die();
        die die3 = new die();
        die die4 = new die();
        die die5 = new die();
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();
        return (new Integer[]{die1.getFace(), die2.getFace(), die3.getFace(),die4.getFace(),die5.getFace()});
    }
    public Integer[] roll6dice() {
        die die1 = new die();
        die die2 = new die();
        die die3 = new die();
        die die4 = new die();
        die die5 = new die();
        die die6 = new die();
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();
        die6.roll();
        return (new Integer[]{die1.getFace(), die2.getFace(), die3.getFace(),die4.getFace(),die5.getFace(),die6.getFace()});
    }
}