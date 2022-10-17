package comp1110.ass2;

import java.util.ArrayList;

/**
 * Contributor: Wei Wei
 */
public class Board {
    ArrayList<Construction> constructions = new ArrayList<Construction>();
    Player player;

    public Board(Player player) {
        this.player = player;
    }

    /**
     * @return Return current board state
     */
    public String boardState(){
        String BoardState = "";
        for (int i = 0; i < constructions.size(); i++) {
            Construction construction = constructions.get(i);
            if (i==0){
                BoardState += construction;
            } else {
                BoardState += "," + construction;
            }
        }
        return BoardState;
    }

    /**
     * Build required construction on board based on current resource and board state
     * @param construction the required construction
     * @return ture if the required construction is shown on the board state,
     * false if that construction cannot be built
     */
    public boolean buildConstruction(Construction construction){
        //Check constraint
        if (CatanDice.canDoAction("build " + construction, boardState(), player.getResource_state())){
            if (player.build(construction)){
                constructions.add(construction);
                return true;
            }
        }
        return false;
    }

    /**
     * Swap resource according to requirements
     * @param resource the resource can be used for swap
     * @param target the resource player can and want to swap for
     * @return true if swap successfully
     */
    public boolean swapResource(int resource, int target){
        String targetJoker = "J" + (target+1);
        for (Construction construction : constructions) {
            if (targetJoker.equals(String.valueOf(construction))){
                construction.JokerToKnight();
                player.swap(resource, target);
                return true;
            }
        }
        return false;
    }

    /**
     * show all resource player can swap in String[]
     * used in gui dropdown menu
     * @return String[] contains resources player can swap for
     */
    public String[] showTargetAvailableForSwap(){
        ArrayList<String> ResourceList = new ArrayList<>();
        for (Construction construction : constructions) {
            if (construction.type.equals("J")){
                switch (construction.ID){
                    case "1"-> {
                        if (!ResourceList.contains("Ore")) {
                            ResourceList.add("Ore");
                        }
                    }
                    case "2"-> {
                        if (!ResourceList.contains("Grain")) {
                            ResourceList.add("Grain");
                        }
                    }
                    case "3"-> {
                        if (!ResourceList.contains("Wool")) {
                            ResourceList.add("Wool");
                        }
                    }
                    case "4"-> {
                        if (!ResourceList.contains("Timber")) {
                            ResourceList.add("Timber");
                        }
                    }
                    case "5"-> {
                        if (!ResourceList.contains("Brick")) {
                            ResourceList.add("Brick");
                        }
                    }
                    case "6"-> {
                        return new String[]{"Ore","Grain","Wool","Timber","Brick","Gold"};
                    }
                }
            }
        }
        return ResourceList.toArray(new String[0]);
    }

    /**
     * This method will return constructions which can be built by player
     * based on current board state and resource state.
     * The purpose for implement this method is to make gui more concise.
     * We use dropdown menu for player to select construction they want to build
     * and there will be too many options if we provide all constructions.
     * Therefore, with this method, we are able to provide useful options for player.
     * Also, the Strings represent different constructions are in order based on construction type(R,S,C,J) and ID;
     * @return an ArrayList contains all construction player can build at current stage
     */
    public ArrayList<String> showBuildableConstructions(){
        ArrayList<String> buildableConstructions = new ArrayList<>();
        String b = "build ";
        if (CatanDice.canDoAction(b+"R0", boardState(), player.getResource_state())){
            buildableConstructions.add("R0");
        }
        if (CatanDice.canDoAction(b+"S3", boardState(), player.getResource_state())){
            buildableConstructions.add("S3");
        }
        if (CatanDice.canDoAction(b+"J1", boardState(), player.getResource_state())){
            buildableConstructions.add("J1");
        }
        if (CatanDice.canDoAction(b+"C7", boardState(), player.getResource_state())){
            buildableConstructions.add("C7");
        }
        for (Construction construction : constructions) {
            switch (construction.type){
                case "R"->{
                    String r1 = "R" + (Integer.parseInt(construction.ID)+1);
                    String r2 = "R" + (Integer.parseInt(construction.ID)+2);
                    String r3 = "R" + (Integer.parseInt(construction.ID)+5);
                    if (CatanDice.canDoAction(b+r1, boardState(), player.getResource_state())
                            && !buildableConstructions.contains(r1)){
                        buildableConstructions.add(r1);
                    }
                    if (CatanDice.canDoAction(b+r2, boardState(), player.getResource_state())
                            && !buildableConstructions.contains(r2)){
                        buildableConstructions.add(r2);
                    }
                    if (CatanDice.canDoAction(b+r3, boardState(), player.getResource_state())
                            && !buildableConstructions.contains(r3)){
                        buildableConstructions.add(r3);
                    }
                }
                case "S"->{
                    String s1 = "S" + (Integer.parseInt(construction.ID) + 1);
                    String s2 = "S" + (Integer.parseInt(construction.ID) + 2);
                    if (CatanDice.canDoAction(b+s1, boardState(), player.getResource_state())&&(!buildableConstructions.contains(s1))){
                        buildableConstructions.add(s1);
                    } else if (CatanDice.canDoAction(b+s2, boardState(), player.getResource_state())&&(!buildableConstructions.contains(s2))){
                        buildableConstructions.add(s2);
                    }
                }
                case "C"->{
                    if (CatanDice.canDoAction(b+"C7", boardState(), player.getResource_state())&&(!buildableConstructions.contains("C7"))){
                        buildableConstructions.add("C7");
                    } else if (CatanDice.canDoAction(b+"C12", boardState(), player.getResource_state())&&(!buildableConstructions.contains("C12"))) {
                        buildableConstructions.add("C12");
                    } else if (CatanDice.canDoAction(b+"C20", boardState(), player.getResource_state())&&(!buildableConstructions.contains("C20"))) {
                        buildableConstructions.add("C20");
                    } else if (CatanDice.canDoAction(b+"C30", boardState(), player.getResource_state())&&(!buildableConstructions.contains("C30"))) {
                        buildableConstructions.add("C30");
                    }
                }
                case "J", "K" ->{
                    String j1 = "J" + (Integer.parseInt(construction.ID)+1);
                    if (CatanDice.canDoAction(b+j1, boardState(), player.getResource_state())){
                        buildableConstructions.add(j1);
                    }
                }
            }
        }
        ArrayList<String> OrderedList = new ArrayList<>();
        for (String buildableConstruction : buildableConstructions) {
            if (buildableConstruction.charAt(0)=='R'){
                OrderedList.add(buildableConstruction);
            }
        }
        for (String buildableConstruction : buildableConstructions) {
            if (buildableConstruction.charAt(0)=='S'){
                OrderedList.add(buildableConstruction);
            }
        }
        for (String buildableConstruction : buildableConstructions) {
            if (buildableConstruction.charAt(0)=='C'){
                OrderedList.add(buildableConstruction);
            }
        }
        for (String buildableConstruction : buildableConstructions) {
            if (buildableConstruction.charAt(0)=='J'){
                OrderedList.add(buildableConstruction);
            }
        }
        return OrderedList;
    }

    public ArrayList<Construction> getConstructions() {
        return constructions;
    }

    /**
     * Only used for testing
     * @param construction the String represent different constructions
     */
    public void AddConstructionsForTest(String[] construction){
        for (String s : construction) {
            if (CatanDice.isBoardStateWellFormed(s)){
                constructions.add(new Construction(s));
            }
        }
    }


    /**
     * Contributor: Wenxiao Wu
     */
    public void restartboard(){
        if (constructions.size()!=0) {
            constructions.clear();
        }
    }

}
