package comp1110.ass2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSwapResource {
    Player player1 = new Player(new int[]{0, 0, 0, 0, 0, 0});
    Player player2 = new Player(new int[]{2, 4, 1, 3, 0, 0});

    Board board1 = new Board(player1);
    Board board2 = new Board(player2);
    private String errorPrefix(int resource, int target) {
        return "Board.swapResource(" + (resource) + "," + (target) + ")";

    }

    private void test(int r, int t, boolean answer) {
        String errorPrefix = errorPrefix(r, t);
        boolean out = board1.swapResource(r, t);
        assertEquals(answer, out, errorPrefix);
    }
    private void test2(int r, int t, boolean answer) {
        String errorPrefix = errorPrefix(r, t);
        boolean out = board2.swapResource(r, t);
        assertEquals(answer, out, errorPrefix);
    }
    @Test
    public void trivialFalse() {
        test(1,4,false); //board1 is empty and player1 has no resources
        board1.AddConstructionsForTest(new String[]{"J1"});
        test(2, 3, false);  //player1 has no resources therefore no swaps should be possible
        player1=new Player(new int[]{0,1,0,0,3,1}); //player1 has no resource at index 3 to swap for the target despite J1 present on board1
        test(3,1,false);
    }
    @Test
    public void trivialTrue(){
        board2.AddConstructionsForTest(new String[]{"J2"});
        test2(2,1,true);
        board2.AddConstructionsForTest(new String[]{"J4"});
        test2(1,3,true);
    }

    @Test
    public void testfullboard(){
        Player player1=new Player(new int[]{ 3,1,0,0,0,2});
        board1=new Board(player1);
        board1.AddConstructionsForTest(new String[]{"R0","R1","S3","J1","J2","J3"});
        test(0,2,true); //valid swap
        test(2,3,false); //Resource at index 2 not available for swapping
        test(0,4,false); //No J5 on board
        Player player2=new Player(new int[]{ 0,1,1,2,2,0 });
        board2=new Board(player2);
        board2.AddConstructionsForTest(new String[]{"R0","R1","R2","S3","K1","K2","K3"});
        //No jokers present on board.No swaps will be possible
        test2(0,5,false);
        test2(2,0,false);
        board2=new Board(player1);
        board2.AddConstructionsForTest(new String[]{"R0","R1","R2","S3","J1","J2","J3"});
        //same resources different board
        test2(1,0,true);
        test2(4,3,false);
        test2(4,2,true);
        test(0,2,false);
        test(2,3,false);
        test(2,1,true);

    }

    public static void main(String[] args) {
        TestSwapResource tests = new TestSwapResource();
        System.out.println("testing...");
        tests.trivialFalse();
        tests.trivialTrue();
        tests.testfullboard();
        System.out.println("all done!");

    }

}