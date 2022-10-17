package comp1110.ass2;

/**
 * Contributor: Wei Wei
 */
public class Construction {
    String type;
    String ID;
    private int points;
    private int[] requiredResources;

    public Construction(String type, String ID) {
        this.type = type;
        this.ID = ID;
        if (CatanDice.isBoardStateWellFormed(type+ID)){
            if (type.equals("R")){
                requiredResources = new int[]{0, 0, 0, 1, 1, 0};
                points = 1;
            } else if (type.equals("J")) {
                requiredResources = new int[]{1, 1, 1, 0, 0, 0};
                points = Integer.parseInt(ID);
            } else if (type.equals("S")) {
                requiredResources = new int[]{0, 1, 1, 1, 1, 0};
                points = Integer.parseInt(ID);
            } else if (type.equals("C")){
                requiredResources = new int[]{3, 2, 0, 0, 0, 0};
                points = Integer.parseInt(ID);
            }
        } else {
            throw new RuntimeException("Invalid construction");
        }
    }

    public Construction(String construction){
        this(construction.substring(0,1),
                construction.substring(1,construction.length()));
    }

    public int[] getRequiredResources() {
        return requiredResources;
    }

    public int getPoints() {
        return points;
    }

    /**
     * change Joker to Knight
     */
    public void JokerToKnight(){
        if (type.equals("J")){
            type = "K";
        } else {
            throw new RuntimeException("Only joker can use this method");
        }
    }

    @Override
    public String toString() {
        return type+ID;
    }
}
