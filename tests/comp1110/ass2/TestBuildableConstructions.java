package comp1110.ass2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestBuildableConstructions {
    Player player1 = new Player(new int[]{0, 0, 0, 0, 0, 0});
    Player player2 = new Player(new int[]{2, 2, 2, 2, 2, 2});
    Player player3 = new Player(new int[]{3, 3, 3, 3, 3, 3});

    Board board1 = new Board(player1);
    Board board2 = new Board(player2);
    Board board3 = new Board(player3);

    public String ArraylistToString(ArrayList<String> target){
        String res = "";
        for (String s : target) {
            if (res.equals("")){
                res = s;
            } else {
                res += ","+s;
            }
        }
        return res;
    }

    @Test
    public void testEmptyBoard(){
        Assertions.assertEquals("",ArraylistToString(board1.showBuildableConstructions()));
        Assertions.assertEquals("R0,S3,J1",ArraylistToString(board2.showBuildableConstructions()));
        Assertions.assertEquals("R0,S3,J1",ArraylistToString(board3.showBuildableConstructions()));
    }

    @Test
    public void testBoard(){
        board1.AddConstructionsForTest(new String[]{"R0"});
        board2.AddConstructionsForTest(new String[]{"R0"});
        board3.AddConstructionsForTest(new String[]{"R0"});
        Assertions.assertEquals("",ArraylistToString(board1.showBuildableConstructions()));
        Assertions.assertEquals("R1,R2,S3,J1",ArraylistToString(board2.showBuildableConstructions()));
        Assertions.assertEquals("R1,R2,S3,J1",ArraylistToString(board3.showBuildableConstructions()));
        board1.AddConstructionsForTest(new String[]{"R1","R2","J1"});
        board2.AddConstructionsForTest(new String[]{"R1","R2","J1"});
        board3.AddConstructionsForTest(new String[]{"R1","R2","J1"});
        Assertions.assertEquals("",ArraylistToString(board1.showBuildableConstructions()));
        Assertions.assertEquals("R3,S3,J2",ArraylistToString(board2.showBuildableConstructions()));
        Assertions.assertEquals("R3,S3,C7,J2",ArraylistToString(board3.showBuildableConstructions()));
        // change J1 to K1
        board1.getConstructions().remove(3);
        board1.AddConstructionsForTest(new String[]{"K1"});
        board2.getConstructions().remove(3);
        board2.AddConstructionsForTest(new String[]{"K1"});
        board3.getConstructions().remove(3);
        board3.AddConstructionsForTest(new String[]{"K1"});
        Assertions.assertEquals("",ArraylistToString(board1.showBuildableConstructions()));
        Assertions.assertEquals("R3,S3,J2",ArraylistToString(board2.showBuildableConstructions()));
        Assertions.assertEquals("R3,S3,C7,J2",ArraylistToString(board3.showBuildableConstructions()));
        // test with more constructions
        board1.AddConstructionsForTest(new String[]{"R3","S3","C7","R4","R5","J2"});
        board2.AddConstructionsForTest(new String[]{"R3","S3","C7","R4","R5","J2"});
        board3.AddConstructionsForTest(new String[]{"R3","S3","C7","R4","R5","J2"});
        Assertions.assertEquals("",ArraylistToString(board1.showBuildableConstructions()));
        Assertions.assertEquals("R6,S4,J3",ArraylistToString(board2.showBuildableConstructions()));
        Assertions.assertEquals("R6,S4,C12,J3",ArraylistToString(board3.showBuildableConstructions()));
        board1.AddConstructionsForTest(new String[]{"S4"});
        board2.AddConstructionsForTest(new String[]{"S4"});
        board3.AddConstructionsForTest(new String[]{"S4"});
        Assertions.assertEquals("",ArraylistToString(board1.showBuildableConstructions()));
        Assertions.assertEquals("R6,S5,J3",ArraylistToString(board2.showBuildableConstructions()));
        Assertions.assertEquals("R6,S5,C12,J3",ArraylistToString(board3.showBuildableConstructions()));
        //Test the fork at the R7
        board1.AddConstructionsForTest(new String[]{"R6","J3","R7"});
        board2.AddConstructionsForTest(new String[]{"R6","J3","R7"});
        board3.AddConstructionsForTest(new String[]{"R6","J3","R7"});
        Assertions.assertEquals("",ArraylistToString(board1.showBuildableConstructions()));
        Assertions.assertEquals("R8,R12,S5,J4",ArraylistToString(board2.showBuildableConstructions()));
        Assertions.assertEquals("R8,R12,S5,C12,J4",ArraylistToString(board3.showBuildableConstructions()));
        board1.AddConstructionsForTest(new String[]{"R12","R13","C12"});
        board2.AddConstructionsForTest(new String[]{"R12","R13","C12"});
        board3.AddConstructionsForTest(new String[]{"R12","R13","C12"});
        Assertions.assertEquals("",ArraylistToString(board1.showBuildableConstructions()));
        Assertions.assertEquals("R8,R14,S5,J4",ArraylistToString(board2.showBuildableConstructions()));
        Assertions.assertEquals("R8,R14,S5,C20,J4",ArraylistToString(board3.showBuildableConstructions()));


    }

    @Test
    public void testFullBoard(){
        board1.getConstructions().clear();
        board2.getConstructions().clear();
        board3.getConstructions().clear();
        board1.AddConstructionsForTest(new String[]{"R0","R1","R2","R3","R4","R5","R6","R7","R8","R9","R10","R11"
                ,"R12","R13","R14","R15","S3","S4","S5","S7","S9","S11","C7","C12","C20","C30","J1","J2","K3"
                ,"K4","J5","K6"});
        board2.AddConstructionsForTest(new String[]{"R0","R1","R2","R3","R4","R5","R6","R7","R8","R9","R10","R11"
                ,"R12","R13","R14","R15","S3","S4","S5","S7","S9","S11","C7","C12","C20","C30","K1","J2","J3"
                ,"J4","K5","J6"});
        board3.AddConstructionsForTest(new String[]{"R0","R1","R2","R3","R4","R5","R6","R7","R8","R9","R10","R11"
                ,"R12","R13","R14","R15","S3","S4","S5","S7","S9","S11","C7","C12","C20","C30","K1","K2","K3"
                ,"K4","J5","K6"});
        Assertions.assertEquals("",ArraylistToString(board1.showBuildableConstructions()));
        Assertions.assertEquals("",ArraylistToString(board2.showBuildableConstructions()));
        Assertions.assertEquals("",ArraylistToString(board3.showBuildableConstructions()));
    }

    public static void main(String[] args) {
        TestBuildableConstructions tests = new TestBuildableConstructions();
        System.out.println("Testing...");
        tests.testEmptyBoard();
        tests.testBoard();
        tests.testFullBoard();
        System.out.println("all done!");
    }
}
