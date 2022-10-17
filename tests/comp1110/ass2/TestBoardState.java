package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TestBoardState {
    Player player1 = new Player(new int[]{0, 0, 0, 0, 0, 0});
    Board board1 = new Board(player1);

    Player player2 = new Player(new int[]{1, 2, 1, 2, 0, 0});
    Board board2 = new Board(player2);

    Player player3 = new Player(new int[]{2, 1, 3, 0, 1, 0});
    Board board3 = new Board(player3);


    @Test
    void testEmpty() {
        Assertions.assertEquals("",board1.boardState());
        Assertions.assertEquals("",board2.boardState());
    }




    @Test
    void testSimple() {
        board1.AddConstructionsForTest(new String[]{"R0,R1,R2,R3,R4"});
        board2.AddConstructionsForTest(new String[]{"R0,R1,R2,R3,R4"});
        Assertions.assertEquals("R0,R1,R2,R3,R4",board1.boardState());
        board1.AddConstructionsForTest(new String[]{"R5"});
        board2.AddConstructionsForTest(new String[]{"R5,R6"});
        Assertions.assertEquals("R0,R1,R2,R3,R4,R5,R6",board2.boardState());
        board3.AddConstructionsForTest(new String[]{"R5"});
    }

    @Test
    void testComplex() {
        board1.AddConstructionsForTest(new String[]{"R0,R1,C7,J1"});
        Assertions.assertEquals("R0,R1,C7,J1",board1.boardState());
        board2.AddConstructionsForTest(new String[]{"R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5"});
        Assertions.assertEquals("R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5",board2.boardState());
        board3.AddConstructionsForTest(new String[]{"R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5,S5,R6,R7,S7,R8,R9,R10,R11,R12,R13,R14,R15"});
        Assertions.assertEquals("R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5,S5,R6,R7,S7,R8,R9,R10,R11,R12,R13,R14,R15",board3.boardState());
    }


    @Test
    void testFull() {
        board1.AddConstructionsForTest(new String[]{"R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5,S5,R6,R7,S7,R8,R9,R10,R11,R12,R13,R14,R15,J3,K3," +
                "J4,J5,J6,K4,K5,K6,C20,C30,S9,S11"});
        Assertions.assertEquals("R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5,S5,R6,R7,S7,R8,R9,R10,R11,R12,R13,R14,R15,J3,K3," +
                "J4,J5,J6,K4,K5,K6,C20,C30,S9,S11",board1.boardState());
        board2.AddConstructionsForTest(new String[]{"R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5,S5,R6,R7,S7,R8,R9,R10,R11,R12,R13,R14,R15,J3,K3," +
                "J4,J5,J6,K4,K5,K6,C20,C30,S9,S11"});
        Assertions.assertEquals("R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5,S5,R6,R7,S7,R8,R9,R10,R11,R12,R13,R14,R15,J3,K3," +
                "J4,J5,J6,K4,K5,K6,C20,C30,S9,S11",board2.boardState());
        board3.AddConstructionsForTest(new String[]{"R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5,S5,R6,R7,S7,R8,R9,R10,R11,R12,R13,R14,R15,J3,K3," +
                "C20,C30,J4,J5,J6,K4,K5,K6,S9,S11"});
        Assertions.assertEquals("R0,R1,C7,J1,K1,R2,S4,R3,R4,C12,J2,K2,R5,S5,R6,R7,S7,R8,R9,R10,R11,R12,R13,R14,R15,J3,K3," +
                "C20,C30,J4,J5,J6,K4,K5,K6,S9,S11",board3.boardState());

    }
}
