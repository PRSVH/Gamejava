# New Code for Deliverable D2D

## < u7543073 > < Wenxiao Wu >

For Deliverable D2D, I contributed the following new statements of original code:

- Added the [displayState(String board_state)](https://gitlab.cecs.anu.edu.au/u7545926/comp1110-ass2/-/blob/main/src/comp1110/ass2/gui/Game.java#L338-366) function to Game class
-  Added the [refreshMap()](https://gitlab.cecs.anu.edu.au/u7545926/comp1110-ass2/-/blob/main/src/comp1110/ass2/gui/Game.java#L253-255) function to Game class

displayState(String board_state) method takes a string parameter and displays the corresponding board state according to the string parameter.

Board display description：

If the player enters a road code such as R1, R1 will be blocked by a polygon in the displayed window to indicate that the road has been constructed. The same is true for Settlements and city. In addition, when the player enters J1, it means that a knight is built at 1 point, but it has not been used, and the displayed window will have a blue triangle hat on the head of the corresponding knight in the board. When the player enters K1, it means that the knight at 1 point has been used, and the triangle hat of the corresponding knight will turn red.

refreshMap() method is designed to update the board based on the player's actions. In this method, the displayState(String board_state) method will be called to realize the function of updating the board.
