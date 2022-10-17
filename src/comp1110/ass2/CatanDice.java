package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatanDice {

    /**
     * Check if the string encoding of a board state is well formed.
     * Note that this does not mean checking if the state is valid
     * (represents a state that the player could get to in game play),
     * only that the string representation is syntactically well formed.
     *
     * @param board_state: The string representation of the board state.
     * @return true iff the string is a well-formed representation of
     * a board state, false otherwise.
     */

    /**
     * Contributor: Preshtibye Raggoo
     */
    public static boolean isBoardStateWellFormed(String board_state) {
        String[] roads = {"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10", "R11", "R12", "R13", "R14", "R15"};
        String[] settlements = {"S3", "S4", "S5", "S7", "S9", "S11"};
        String[] cities = {"C7", "C12", "C20", "C30"};
        String[] knights = {"K1", "K2", "K3", "K4", "K5", "K6"};
        String[] usedknights = {"J1", "J2", "J3", "J4", "J5", "J6"};

        String[] boardstate = board_state.split(",");
        boolean flag = true;
        int i = 0;
        if (board_state.equals("")) {
            return flag;
        }
        while ((flag) & (i < boardstate.length)) {
            if (Arrays.asList(roads).contains(boardstate[i]) | Arrays.asList(settlements).contains(boardstate[i]) |
                    Arrays.asList(cities).contains(boardstate[i]) | Arrays.asList(knights).contains(boardstate[i]) |
                    Arrays.asList(usedknights).contains(boardstate[i])) {
                i++;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    // FIXME: Task #3


    /**
     * Check if the string encoding of a player action is well formed.
     *
     * @param action: The string representation of the action.
     * @return true iff the string is a well-formed representation of
     * a board state, false otherwise.
     */
    public static boolean isActionWellFormed(String action) {
        String[] roads = {"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10", "R11", "R12", "R13", "R14", "R15"};
        String[] settlements = {"S3", "S4", "S5", "S7", "S9", "S11"};
        String[] cities = {"C7", "C12", "C20", "C30"};
        String[] knights = {"K1", "K2", "K3", "K4", "K5", "K6"};
        String[] usedknights = {"J1", "J2", "J3", "J4", "J5", "J6"};
        String[] actiononly = action.split(" ");
        String[] acceptableactions = {"build", "trade", "swap"};
        if (actiononly[0].equals(acceptableactions[0])) {
            if (Arrays.asList(roads).contains(actiononly[1]) | Arrays.asList(settlements).contains(actiononly[1])
                    | Arrays.asList(cities).contains(actiononly[1]) | Arrays.asList(knights).contains(actiononly[1]) |
                    Arrays.asList(usedknights).contains(actiononly[1])) {
                return true;
            } else {
                return false;
            }
        } else if (actiononly[0].equals(acceptableactions[1])) {
            if (Integer.parseInt(actiononly[1]) >= 0 & Integer.parseInt(actiononly[1]) <= 4) {
                return true;
            } else {
                return false;
            }


        } else if (actiononly[0].equals(acceptableactions[2])) {
            if ((Integer.parseInt(actiononly[1]) >= 0 & Integer.parseInt(actiononly[1]) <= 5) &
                    (Integer.parseInt(actiononly[2]) >= 0 & Integer.parseInt(actiononly[2]) <= 5)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * Roll the specified number of die and add the result to the
     * resource state.
     * <p>
     * The resource state on input is not necessarily empty. This
     * method should only _add_ the outcome of the die rolled to
     * the resource state, not remove or clear the resources already
     * represented in it.
     *
     * @param n_dice:         The number of die to roll (>= 0).
     * @param resource_state: The available resources that the die
     *                        roll will be added to.
     *                        <p>
     *                        This method does not return any value. It should update the given
     *                        resource_state.
     */
    public static void rollDice(int n_dice, int[] resource_state) {
        // FIXME: Task #6
        for (int i = 0; i < n_dice; i++) {
            int new_resource = (int) (Math.random() * 6);
            resource_state[new_resource]++;
        }
    }

    /**
     * Check if the specified structure can be built next, given the
     * current board state. This method should check that the build
     * meets the constraints described in section "Building Constraints"
     * of the README file.
     *
     * @param structure:   The string representation of the structure to
     *                     be built.
     * @param board_state: The string representation of the board state.
     * @return true iff the structure is a possible next build, false
     * otherwise.
     */
    public static boolean checkBuildConstraints(String structure,
                                                String board_state) {
        boolean isBuildValid = false;
        boolean isConnected = false;
        boolean isInOrder = false;
        String[] buildings = board_state.split(",");
        // get structure type and number
        char[] chars = structure.toCharArray();
        String target_type = chars[0] + "";
        int target_num;
        if (chars.length == 2) {
            target_num = Integer.parseInt(String.valueOf(chars[1]));
        } else {
            target_num = Integer.parseInt(String.valueOf(chars[1]) + String.valueOf(chars[2]));
        }
        // check whether the certain construction has been built
        for (String building : buildings) {
            String ID = structure.substring(1);
            if (building.equals(structure) || (building.equals("K" + ID) && target_type.equals("J"))) {
                return false;
            }
        }
        // start
        switch (target_type) {
            case "R" -> {
                if (structure.equals("R0")) {
                    isBuildValid = true;
                }
                for (String building : buildings) {
                    if (building.equals("R" + (target_num - 1))) {
                        isBuildValid = true;
                    } else if (building.equals("R0") && structure.equals("R2")) {
                        isBuildValid = true;
                    } else if (building.equals("R3") && structure.equals("R5")) {
                        isBuildValid = true;
                    } else if (building.equals("R7") && structure.equals("R12")) {
                        isBuildValid = true;
                    }
                }
            }
            case "S" -> {
                for (String building : buildings) {
                    if (structure.equals("S4") && building.equals("R2")) {
                        isConnected = true;
                    } else if (building.equals("R" + target_num)) {
                        isConnected = true;
                    }
                    if (target_num > 5) {
                        if (building.equals("S" + (target_num - 2))) {
                            isInOrder = true;
                        }
                    } else {
                        if (building.equals("S" + (target_num - 1))) {
                            isInOrder = true;
                        }
                    }
                }
                isBuildValid = isConnected & isInOrder;
                // Build S3
                if (structure.equals("S3")) {
                    isBuildValid = true;
                }
            }
            case "C" -> {
                for (String building : buildings) {
                    //check isConnected
                    if (structure.equals("C7") && building.equals("R1")) {
                        isConnected = true;
                        isInOrder = true;
                    } else if (structure.equals("C12") && building.equals("R4")) {
                        isConnected = true;
                    } else if (structure.equals("C20") && building.equals("R13")) {
                        isConnected = true;
                    } else if (structure.equals("C30") && building.equals("R15")) {
                        isConnected = true;
                    }
                    //check isInOrder
                    if (structure.equals("C12") && building.equals("C7")) {
                        isInOrder = true;
                    } else if (structure.equals("C20") && building.equals("C12")) {
                        isInOrder = true;
                    } else if (structure.equals("C30") && building.equals("C20")) {
                        isInOrder = true;
                    }
                }
                isBuildValid = isConnected & isInOrder;
            }
            case "J" -> {
                for (String building : buildings) {
                    if (building.equals("J" + (target_num - 1)) || building.equals("K" + (target_num - 1))) {
                        isBuildValid = true;
                    } else if (building.equals(structure)) {
                        return false;
                    }
                }
                if (structure.equals("J1")) {
                    isBuildValid = true;
                }
            }
            default -> {
            }
        }
        return isBuildValid; // FIXME: Task #8
    }

    /**
     * Check if the available resources are sufficient to build the
     * specified structure, without considering trades or swaps.
     *
     * @param structure:      The string representation of the structure to
     *                        be built.
     * @param resource_state: The available resources.
     * @return true iff the structure can be built with the available
     * resources, false otherwise.
     */
    public static boolean checkResources(String structure,
                                         int[] resource_state) {
        char[] s = structure.toCharArray();
        String structureType = s[0] + "";
        int[] required_resource = new int[6];
        if (structureType.equals("R")) {
            required_resource = new int[]{0, 0, 0, 1, 1, 0};
        } else if (structureType.equals("S")) {
            required_resource = new int[]{0, 1, 1, 1, 1, 0};
        } else if (structureType.equals("J")) {
            required_resource = new int[]{1, 1, 1, 0, 0, 0};
        } else if (structureType.equals("C")) {
            required_resource = new int[]{3, 2, 0, 0, 0, 0};
        }
        boolean canBuild = true;
        for (int i = 0; i < 6; i++) {
            if (resource_state[i] < required_resource[i]) {
                canBuild = false;
            }
        }
        return canBuild; // FIXME: Task #7
    }

    public static String newboardstate(String infuncboard_state, int swap) {
        String[] temparrbrd = infuncboard_state.split(",");
        if (Arrays.asList(temparrbrd).contains("J" + (swap))) {
            int index = findIndex(temparrbrd, "J" + (swap));
            temparrbrd[index] = ("K" + (swap + 1));
        } else if (Arrays.asList(temparrbrd).contains("J6")) {
            int index = findIndex(temparrbrd, "J6");
            temparrbrd[index] = ("K6");
        }
        infuncboard_state = "";
        for (int v = 0; v < temparrbrd.length - 1; v++) {
            infuncboard_state = infuncboard_state + temparrbrd[v] + ',';
        }
        return (infuncboard_state + temparrbrd[temparrbrd.length - 1]);

    }

    /**
     * Check if the available resources are sufficient to build the
     * specified structure, considering also trades and/or swaps.
     * This method needs access to the current board state because the
     * board state encodes which Knights are available to perform swaps.
     *
     * @param structure:      The string representation of the structure to
     *                        be built.
     * @param board_state:    The string representation of the board state.
     * @param resource_state: The available resources.
     * @return true iff the structure can be built with the available
     * resources, false otherwise.
     */
    public static boolean checkResourcesWithTradeAndSwap(String structure,
                                                         String board_state,
                                                         int[] resource_state) {
        String[] constructions = board_state.split(",");
        boolean istradepossible = false;
        int[] required_resource = new int[6];
        int[] infuncresource_state = resource_state;
        String infuncboard_state = board_state;
        char[] s = structure.toCharArray();
        char structureType = s[0];
        if (structureType == 'R') {
            required_resource = new int[]{0, 0, 0, 1, 1, 0};
        } else if (structureType == 'S') {
            required_resource = new int[]{0, 1, 1, 1, 1, 0};
        } else if (structureType == 'J') {
            required_resource = new int[]{1, 1, 1, 0, 0, 0};
        } else if (structureType == 'C') {
            required_resource = new int[]{3, 2, 0, 0, 0, 0};
        }
        int[] newresource_state = new int[6];
        if (checkResources(structure, infuncresource_state)) {
            return true;
        } else {
            if (infuncresource_state[5] >= 2) {
                switch (structureType) {
                    case 'R':
                        if (infuncresource_state[3] == 0) {
                            if (canDoAction("trade 3", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{0, 0, 0, 1, 0, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[4] == 0) {
                            if (canDoAction("trade 4", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{0, 0, 0, 0, 1, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                    case 'S':
                        if (infuncresource_state[1] == 0) {
                            if (canDoAction("trade 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{0, 1, 0, 0, 0, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[2] == 0) {
                            if (canDoAction("trade 2", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{0, 0, 1, 0, 0, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[3] == 0) {
                            if (canDoAction("trade 3", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{0, 0, 0, 1, 0, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[4] == 0) {
                            if (canDoAction("trade 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{0, 0, 0, 0, 1, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                    case 'J':
                        if (infuncresource_state[0] == 0) {
                            if (canDoAction("trade 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{1, 0, 0, 0, 0, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[1] == 0) {
                            if (canDoAction("trade 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{0, 1, 0, 0, 0, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[2] == 0) {
                            if (canDoAction("trade 2", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{0, 0, 1, 0, 0, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                    case 'C':
                        if (infuncresource_state[0] < 3) {
                            if (canDoAction("trade 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{1, 0, 0, 0, 0, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[1] < 2) {
                            if (canDoAction("trade 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), new int[]{0, 1, 0, 0, 0, 0});
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                }
            }
            if (infuncboard_state.contains("J6")) {

                switch (structureType) {
                    case 'R':
                        if (infuncresource_state[3] == 0) {
                            if (canDoAction("swap 0 3", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 1 3", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 2 3", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (resource_state[4] > 1) {
                                if (canDoAction("swap 4 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 5 3", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 1, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[4] == 0) {
                            if (canDoAction("swap 0 4", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 1 4", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 2 4", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (resource_state[3] > 1) {
                                if (canDoAction("swap 3 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 5 4", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 0, 1, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        break;
                    case 'S':
                        if (infuncresource_state[1] == 0) {
                            if (canDoAction("swap 0 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (infuncresource_state[2] > 1) {
                                if (canDoAction("swap 2 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[3] > 1) {
                                if (canDoAction("swap 3 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[4] > 1) {
                                if (canDoAction("swap 4 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 5 3", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 1, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[2] == 0) {
                            if (canDoAction("swap 0 2", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (infuncresource_state[1] > 1) {
                                if (canDoAction("swap 1 2", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[3] > 1) {
                                if (canDoAction("swap 3 2", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[4] > 1) {
                                if (canDoAction("swap 4 2", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 5 2", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 1, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[3] == 0) {
                            if (canDoAction("swap 0 3", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (infuncresource_state[1] > 1) {
                                if (canDoAction("swap 1 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[2] > 1) {
                                if (canDoAction("swap 2 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[4] > 1) {
                                if (canDoAction("swap 4 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 5 3", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 1, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[4] == 0) {
                            if (canDoAction("swap 0 4", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (infuncresource_state[1] > 1) {
                                if (canDoAction("swap 1 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[2] > 1) {
                                if (canDoAction("swap 2 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[3] > 1) {
                                if (canDoAction("swap 3 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 5 4", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 0, 1, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        break;
                    case 'J':
                        if (infuncresource_state[0] == 0) {
                            if (infuncresource_state[1] > 1) {
                                if (canDoAction("swap 1 0", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[2] > 1) {
                                if (canDoAction("swap 2 0", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 3 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 4 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 5 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[1] == 0) {
                            if (infuncresource_state[0] > 1) {
                                if (canDoAction("swap 0 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[2] > 1) {
                                if (canDoAction("swap 2 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 3 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 4 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 5 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 1, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[2] == 0) {
                            if (infuncresource_state[0] > 1) {
                                if (canDoAction("swap 0 2", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[1] > 1) {
                                if (canDoAction("swap 1 2", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 3 2", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 4 2", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 5 2", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 1, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        break;
                    case 'C':
                        if (infuncresource_state[0] < 3) {
                            if (infuncresource_state[1] > 2) {
                                if (canDoAction("swap 1 0", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 2 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 3 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 4 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 5 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        if (infuncresource_state[1] < 2) {
                            if (infuncresource_state[0] > 3) {
                                if (canDoAction("swap 0 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 6);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 2 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 3 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 4 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 5 1", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 1, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 6);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        break;
                }
            }
            if (infuncboard_state.contains("J1")) {
                switch (structureType) {
                    case 'J':
                        if (infuncresource_state[0] == 0) {
                            if (infuncresource_state[1] > 1) {
                                if (canDoAction("swap 1 0", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 1);

                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (infuncresource_state[2] > 1) {
                                if (canDoAction("swap 2 0", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 1);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 3 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 1);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 4 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 1);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 5 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 1);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        break;
                    case 'C':
                        if (infuncresource_state[0] < 3) {
                            if (infuncresource_state[1] > 2) {
                                if (canDoAction("swap 1 0", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 1);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            } else if (canDoAction("swap 2 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 1);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 3 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 1);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 4 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 1);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            } else if (canDoAction("swap 5 0", infuncboard_state, infuncresource_state)) {
                                newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{1, 0, 0, 0, 0, 0});
                                infuncboard_state = newboardstate(infuncboard_state, 1);
                                return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                            }
                        }
                        break;

                }
                if (infuncboard_state.contains("J2")) {

                    switch (structureType) {
                        case 'S':
                            if (infuncresource_state[1] == 0) {
                                if (canDoAction("swap 0 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 2);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (infuncresource_state[2] > 1) {
                                    if (canDoAction("swap 2 1", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 2);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (infuncresource_state[3] > 1) {
                                    if (canDoAction("swap 3 1", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 2);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (infuncresource_state[4] > 1) {
                                    if (canDoAction("swap 4 1", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 2);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (canDoAction("swap 5 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 2);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            }
                            break;

                        case 'J':
                            if (infuncresource_state[1] == 0) {
                                if (infuncresource_state[0] > 1) {
                                    if (canDoAction("swap 0 1", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 2);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (infuncresource_state[2] > 1) {
                                    if (canDoAction("swap 2 1", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 2);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (canDoAction("swap 3 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 2);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (canDoAction("swap 4 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 2);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (canDoAction("swap 5 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 2);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            }
                            break;
                        case 'C':
                            if (infuncresource_state[1] < 2) {
                                if (infuncresource_state[0] > 3) {
                                    if (canDoAction("swap 0 1", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 2);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (canDoAction("swap 2 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 2);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (canDoAction("swap 3 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 2);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (canDoAction("swap 4 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 2);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (canDoAction("swap 5 1", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 1, 0, 0, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 2);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            }
                            break;
                    }
                    if (infuncboard_state.contains("J3")) {
                        switch (structureType) {
                            case 'S':
                                if (infuncresource_state[2] == 0) {
                                    if (canDoAction("swap 0 2", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 3);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    } else if (infuncresource_state[1] > 1) {
                                        if (canDoAction("swap 1 2", infuncboard_state, infuncresource_state)) {
                                            newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                            infuncboard_state = newboardstate(infuncboard_state, 3);
                                            return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                        }
                                    } else if (infuncresource_state[3] > 1) {
                                        if (canDoAction("swap 3 2", infuncboard_state, infuncresource_state)) {
                                            newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                            infuncboard_state = newboardstate(infuncboard_state, 3);
                                            return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                        }
                                    } else if (infuncresource_state[4] > 1) {
                                        if (canDoAction("swap 4 2", infuncboard_state, infuncresource_state)) {
                                            newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                            infuncboard_state = newboardstate(infuncboard_state, 3);
                                            return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                        }
                                    } else if (canDoAction("swap 5 2", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 1, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 3);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                }
                                break;
                            case 'J':
                                if (infuncresource_state[2] == 0) {
                                    if (infuncresource_state[0] > 1) {
                                        if (canDoAction("swap 0 2", infuncboard_state, infuncresource_state)) {
                                            newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                            infuncboard_state = newboardstate(infuncboard_state, 3);
                                            return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                        }
                                    } else if (infuncresource_state[1] > 1) {
                                        if (canDoAction("swap 1 2", infuncboard_state, infuncresource_state)) {
                                            newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                            infuncboard_state = newboardstate(infuncboard_state, 3);
                                            return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                        }
                                    } else if (canDoAction("swap 3 2", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 3);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    } else if (canDoAction("swap 4 2", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 1, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 3);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    } else if (canDoAction("swap 5 2", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 1, 0, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 3);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                }
                                break;
                        }
                    }
                }
                if (infuncboard_state.contains("J4")) {
                    switch (structureType) {
                        case 'R':
                            if (infuncresource_state[3] == 0) {
                                if (canDoAction("swap 0 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 4);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (canDoAction("swap 1 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 4);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (canDoAction("swap 2 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 4);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (resource_state[4] > 1) {
                                    if (canDoAction("swap 4 3", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 4);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (canDoAction("swap 5 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 4);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            }
                            break;
                        case 'S':
                            if (infuncresource_state[3] == 0) {
                                if (canDoAction("swap 0 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 4);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (infuncresource_state[1] > 1) {
                                    if (canDoAction("swap 1 3", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 4);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (infuncresource_state[2] > 1) {
                                    if (canDoAction("swap 2 3", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 4);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (infuncresource_state[4] > 1) {
                                    if (canDoAction("swap 4 3", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 0, 1, 0, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 4);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (canDoAction("swap 5 3", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 1, 0, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 4);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            }
                            break;
                    }
                }
                if (infuncboard_state.contains("J5")) {
                    switch (structureType) {
                        case 'R':
                            if (infuncresource_state[4] == 0) {
                                if (canDoAction("swap 0 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 5);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (canDoAction("swap 1 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 5);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (canDoAction("swap 2 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 5);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (resource_state[3] > 1) {
                                    if (canDoAction("swap 3 4", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 1, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 5);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (canDoAction("swap 5 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 5);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            }
                            break;
                        case 'S':
                            if (infuncresource_state[4] == 0) {
                                if (canDoAction("swap 0 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{1, 0, 0, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 5);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                } else if (infuncresource_state[1] > 1) {
                                    if (canDoAction("swap 1 4", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 1, 0, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 5);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (infuncresource_state[2] > 1) {
                                    if (canDoAction("swap 2 4", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 1, 0, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 5);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (infuncresource_state[3] > 1) {
                                    if (canDoAction("swap 3 4", infuncboard_state, infuncresource_state)) {
                                        newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 0, 0}), new int[]{0, 0, 0, 0, 1, 0});
                                        infuncboard_state = newboardstate(infuncboard_state, 5);
                                        return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                    }
                                } else if (canDoAction("swap 5 4", infuncboard_state, infuncresource_state)) {
                                    newresource_state = addElementwise(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 1}), new int[]{0, 0, 0, 0, 1, 0});
                                    infuncboard_state = newboardstate(infuncboard_state, 5);
                                    return checkResourcesWithTradeAndSwap(structure, infuncboard_state, newresource_state);
                                }
                            }
                            break;
                    }

                }

            }
            return false;
        }
        // FIXME: Task #12
    }

    /**
     * Check if a player action (build, trade or swap) is executable in the
     * given board and resource state.
     *
     * @param action:         String representatiion of the action to check.
     * @param board_state:    The string representation of the board state.
     * @param resource_state: The available resources.
     * @return true iff the action is applicable, false otherwise.
     */
    public static boolean canDoAction(String action,
                                      String board_state,
                                      int[] resource_state) {
        if (isBoardStateWellFormed(board_state) && isActionWellFormed(action)) {
            // get action
            String[] s = action.split(" ");
            if (s[0].equals("build") &&
                    checkResources(s[1], resource_state) &&
                    checkBuildConstraints(s[1], board_state)) {
                return true;
            } else if (s[0].equals("trade") && resource_state[5] >= 2) {
                return true;
            } else if (s[0].equals("swap")) {
                String[] constructions = board_state.split(",");
                int id = Integer.parseInt(s[2]) + 1;
                String requiredStructure = "J" + id;
                if (resource_state[Integer.parseInt(s[1])] == 0) {
                    return false;
                } else {
                    for (String construction : constructions) {
                        if (construction.equals(requiredStructure) | construction.equals("J6")) {
                            return true;
                        }
                    }
                }
            }
        }
        return false; // FIXME: Task #9
    }

    public static int[] subtractElementwise(int[] a, int[] b) throws ArithmeticException {

        if (a.length != b.length) {
            throw new ArithmeticException();
        } else {
            int[] result = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                result[i] = a[i] - b[i];
            }
            return result;
        }
    }

    public static int[] addElementwise(int[] first, int[] second) {
        int length = Math.min(first.length, second.length);
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = first[i] + second[i];
        }
        return result;
    }

    public static int findIndex(String arr[], String t) {
        if (arr == null) {
            return -1;
        }
        int len = arr.length;
        int i = 0;
        while (i < len) {
            if (arr[i].equals(t)) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    public static boolean canDoSequence(String[] actions,
                                        String board_state,
                                        int[] resource_state) {
        String[] infuncactions = new String[actions.length];
        System.arraycopy(actions, 0, infuncactions, 0, actions.length);
        int lenaction = infuncactions.length;
        String infuncboard_state = String.valueOf(board_state);
        int[] infuncresource_state = new int[resource_state.length];
        System.arraycopy(resource_state, 0, infuncresource_state, 0, resource_state.length);
        int i = 0;
        boolean valid = true;
        while ((i < lenaction) && (valid)) {
            if (!canDoAction(infuncactions[i], infuncboard_state, infuncresource_state)) {
                valid = false;
                return valid;
            } else {
                String[] s = infuncactions[i].split(" ");
                if ((s[0].equals("build") && canDoAction(infuncactions[i], infuncboard_state, infuncresource_state))) {
                    char[] s1 = s[1].toCharArray();
                    char building = s1[0];
                    switch (building) {
                        case 'R':
                            System.arraycopy(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 1, 1, 0}), 0, infuncresource_state, 0, 6);
                            break;
                        case 'S':
                            System.arraycopy(subtractElementwise(infuncresource_state, new int[]{0, 1, 1, 1, 1, 0}), 0, infuncresource_state, 0, 6);
                            break;
                        case 'J':
                            System.arraycopy(subtractElementwise(infuncresource_state, new int[]{1, 1, 1, 0, 0, 0}), 0, infuncresource_state, 0, 6);
                            break;
                        case 'C':
                            System.arraycopy(subtractElementwise(infuncresource_state, new int[]{3, 2, 0, 0, 0, 0}), 0, infuncresource_state, 0, 6);
                            break;
                    }
                    infuncboard_state = infuncboard_state + "," + s[1];
                }
                if (s[0].equals("trade") && canDoAction(infuncactions[i], infuncboard_state, infuncresource_state)) {
                    System.arraycopy(subtractElementwise(infuncresource_state, new int[]{0, 0, 0, 0, 0, 2}), 0, infuncresource_state, 0, 6);
                    String[] tradeact = infuncactions[i].split(" ");
                    int traderes = Integer.parseInt(tradeact[1]);
                    int[] temptrade = new int[6];
                    for (int j = 0; j < 6; j++) {
                        if (j == traderes) {
                            temptrade[j] = 1;
                        } else {
                            temptrade[j] = 0;
                        }
                    }
                    System.arraycopy(addElementwise(infuncresource_state, temptrade), 0, infuncresource_state, 0, 6);
                }
                if (s[0].equals("swap") && canDoAction(infuncactions[i], infuncboard_state, infuncresource_state)) {
                    String[] swapact = infuncactions[i].split(" ");
                    int swap1 = Integer.parseInt(swapact[1]);
                    int[] temp = new int[6];
                    for (int j = 0; j < 6; j++) {
                        if (j == swap1) {
                            temp[j] = 1;
                        } else {
                            temp[j] = 0;
                        }
                    }
                    System.arraycopy(subtractElementwise(infuncresource_state, temp), 0, infuncresource_state, 0, 6);
                    int[] temp2 = new int[6];
                    int swap2 = Integer.parseInt(swapact[2]);
                    for (int k = 0; k < 6; k++) {
                        if (k == swap2) {
                            temp2[k] = 1;
                        } else {
                            temp2[k] = 0;
                        }
                    }
                    System.arraycopy(addElementwise(infuncresource_state, temp2), 0, infuncresource_state, 0, 6);
                    String[] temparrbrd = infuncboard_state.split(",");
                    if (Arrays.asList(temparrbrd).contains("J" + (swap2 + 1))) {
                        int index = findIndex(temparrbrd, "J" + (swap2 + 1));
                        temparrbrd[index] = ("K" + (swap2 + 1));
                    } else if (Arrays.asList(temparrbrd).contains("J6")) {
                        int index = findIndex(temparrbrd, "J6");
                        temparrbrd[index] = ("K6");
                    }
                    infuncboard_state = "";
                    for (int v = 0; v < temparrbrd.length - 1; v++) {
                        infuncboard_state = infuncboard_state + temparrbrd[v] + ',';
                    }
                    infuncboard_state = infuncboard_state + temparrbrd[temparrbrd.length - 1];
                }
            }
            i++;
        }
        return valid; // FIXME: Task #11
    }

    /**
     * Find the path of roads that need to be built to reach a specified
     * (unbuilt) structure in the current board state. The roads should
     * be returned as an array of their string representation, in the
     * order in which they have to be built. The array should _not_ include
     * the target structure (even if it is a road). If the target structure
     * is reachable via the already built roads, the method should return
     * an empty array.
     * <p>
     * Note that on the Island One map, there is a unique path to every
     * structure.
     *
     * @param target_structure: The string representation of the structure
     *                          to reach.
     * @param board_state:      The string representation of the board state.
     * @return An array of string representations of the roads along the
     * path.
     */
    public static String[] pathTo(String target_structure,
                                  String board_state) {
        String[] result = {};
        String[] boardstate = board_state.split(",");
        String[] roads = new String[]{"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10", "R11", "R12", "R13", "R14", "R15"};
        String[] pathtohexa1 = {"S3", "R0", "R1", "C7"};
        String[] pathtohexa2 = {"S3", "R0", "R2", "S4", "R3", "R4", "C12"};
        String[] pathtoS11 = {"S3", "R0", "R2", "S4", "R3", "R5", "S5", "R6", "R7", "S7", "R8", "R9", "S9", "R10", "R11", "S11"};
        String[] pathtoC30 = {"S3", "R0", "R2", "S4", "R3", "R5", "S5", "R6", "R7", "R12", "R13", "C20", "R14", "R15", "C30"};
        String[] arrayname;
        String path = "";
        int index = 0;
        if (findIndex(pathtohexa1, target_structure) == -1) {
            if (findIndex(pathtohexa2, target_structure) == -1) {
                if (findIndex(pathtoS11, target_structure) == -1) {
                    index = findIndex(pathtoC30, target_structure);
                    arrayname = pathtoC30;

                } else {
                    index = findIndex(pathtoS11, target_structure);
                    arrayname = pathtoS11;
                }
            } else {
                index = findIndex(pathtohexa2, target_structure);
                arrayname = pathtohexa2;
            }
        } else {
            index = findIndex(pathtohexa1, target_structure);
            arrayname = pathtohexa1;
        }
        if (index == 0) {
            return result;
        } else {
            for (int i = 1; i < index - 1; i++) {
                if ((Arrays.asList(roads).contains(arrayname[i])) & (!(Arrays.asList(boardstate).contains(arrayname[i])))) {
                    path = path + arrayname[i] + " ";
                }

            }
            if ((Arrays.asList(roads).contains(arrayname[index - 1])) & (!(Arrays.asList(boardstate).contains(arrayname[index - 1]))))
                path = path + arrayname[index - 1];
            if (path != "") result = path.split(" ");
        }
        return result; // FIXME: Task #13
    }

    /**
     * Generate a plan (sequence of player actions) to build the target
     * structure from the given board and resource state. The plan may
     * include trades and swaps, as well as bulding other structures if
     * needed to reach the target structure or to satisfy the build order
     * constraints.
     * <p>
     * However, the plan must not have redundant actions. This means it
     * must not build any other structure that is not necessary to meet
     * the building constraints for the target structure, and it must not
     * trade or swap for resources if those resources are not needed.
     * 5*
     * If there is no valid build plan for the target structure from the
     * specified state, return null.
     *
     * @param target_structure: The string representation of the structure
     *                          to be built.
     * @param board_state:      The string representation of the board state.
     * @param resource_state:   The available resources.
     * @return An array of string representations of player actions. If
     * there exists no valid build plan for the target structure,
     * the method should return null.
     */
    public static String[] buildPlan(String target_structure,
                                     String board_state,
                                     int[] resource_state) {
        // get all constructions required to be built
        int[] resources = Arrays.copyOf(resource_state, 6);
        Construction target = new Construction(target_structure);
        ArrayList<Construction> toBuildList = new ArrayList<>();
        if (!target.type.equals("J")){
            String[] unBuildRoads = pathTo(target_structure, board_state);
            for (String unBuildRoad : unBuildRoads) {
                toBuildList.add(new Construction(unBuildRoad));
            }
        }
        ArrayList<String> onBoardStructures = new ArrayList<>(List.of(board_state.split(",")));
        if (!target.type.equals("R")){
            for (int i = 1; i <= Integer.parseInt(target.ID); i++) {
                if (CatanDice.isBoardStateWellFormed(target.type+i) &&
                        !(onBoardStructures.contains(target.type+i)||(target.type.equals("J")&&onBoardStructures.contains("K"+i)))){
                    toBuildList.add(new Construction(target.type+i));
                }
            }
        } else {
            toBuildList.add(new Construction(target.toString()));
        }
        // test whether all these constructions can be built
        ArrayList<String> actions = new ArrayList<>();
        int[] totalRequiredResources = new int[6];
        for (Construction construction : toBuildList) {
            for (int i = 0; i < 6; i++) {
                totalRequiredResources[i] += construction.getRequiredResources()[i];
            }
        }
        for (int i = 0; i < 6; i++) {
            resources[i] -= totalRequiredResources[i];
        }


        ArrayList<Integer> resourceForSwap = new ArrayList<Integer>();
        for (int i = 0; i < 6; i++) {
            if (resources[i] == 1){
                resourceForSwap.add(i);
            } else if (resources[i] == 2) {
                resourceForSwap.add(i);
                resourceForSwap.add(i);
            } else if (resources[i] == 3) {
                resourceForSwap.add(i);
                resourceForSwap.add(i);
                resourceForSwap.add(i);
            } else if (resources[i] == 4) {
                resourceForSwap.add(i);
                resourceForSwap.add(i);
                resourceForSwap.add(i);
                resourceForSwap.add(i);
            }
        }

        for (int i = 0; i < 6; i++) {
            if (resources[i] < 0 && resourceForSwap.size()!= 0 && onBoardStructures.contains("J" + (i+1))){
                String action = "swap "+resourceForSwap.get(0) + " "+i;
                resources[resourceForSwap.get(0)]--;
                resourceForSwap.remove(0);
                actions.add(action);
                resources[i]++;
            }
            for (int j = 0; j < 3; j++) {
                if (resources[i] < 0 && resources[5] >= 2){
                    String action = "trade "+i;
                    actions.add(action);
                    resources[i]++;
                    resources[5]-=2;
                }
            }
        }
        boolean canBuild = true;
        for (int resource : resources) {
            if (resource < 0){
                canBuild = false;
            }
        }
        if (canBuild) {
            for (Construction construction : toBuildList) {
                actions.add("build " + construction);
            }
        } else {
            //check whether some more complex strategy can be used
            for (String action : actions) {
                if (action.split(" ")[0].equals("trade")){
                    resources[Integer.parseInt(action.split(" ")[1])]--;
                    resources[5]+=2;
                }
            }
            actions.clear();
            for (int i = 0; i < 6; i++) {
                if (resources[i] == -1 && !target.toString().equals("J"+(i+1))){
                    if (i != 0 && !(onBoardStructures.contains("J"+i)||onBoardStructures.contains("K"+i))){
                        return null;
                    }
                    String[] actions_1 = buildPlan("J" + (i + 1), board_state, resource_state);
                    if (actions_1 == null){
                        return null;
                    }
                    String newBoardState = "";
                    onBoardStructures.add("J"+(i+1));
                    for (String s : actions_1) {
                        if (s.split(" ")[0].equals("swap")){
                            int id = Integer.parseInt(s.split(" ")[2]);
                            resources[id]++;
                            resources[Integer.parseInt(s.split(" ")[1])]--;
                            for (int j = 0; j < onBoardStructures.size(); j++) {
                                if (onBoardStructures.get(j).equals("J"+(id+1))){
                                    onBoardStructures.set(j, "K"+(id+1));
                                }
                            }
                        }
                    }
                    for (int j = 0; j < onBoardStructures.size(); j++) {
                        if(j==0){
                            newBoardState = onBoardStructures.get(j);
                        } else {
                            newBoardState +=","+onBoardStructures.get(j);
                        }
                    }
                    int[] newResourceState = new int[6];
                    for (int j = 0; j < 6; j++) {
                        if (j < 3){
                            newResourceState[j] = resources[j] - 1;
                        } else {
                            newResourceState[j] = resources[j];
                        }
                    }
                    for (int j = 0; j < 6; j++) {
                        newResourceState[j] += totalRequiredResources[j];
                    }
                    String[] action_2 = buildPlan(target_structure, newBoardState, newResourceState);
                    if (action_2 != null){
                        actions.addAll(Arrays.asList(actions_1));
                        actions.addAll(Arrays.asList(action_2));
                        return actions.toArray(new String[0]);
                    } else {
                        return null;
                    }
                }
            }
        }
        if (actions.size()==0){
            return null;
        }
        return actions.toArray(new String[0]); // FIXME: Task #14
    }

}


