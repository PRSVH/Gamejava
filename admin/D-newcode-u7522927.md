# New Code for Deliverable D2D

## < u7522927 > < Preshtibye Raggoo >

For Deliverable D2D, I contributed the following new statements of original code:


- Added the [swapResource](https://gitlab.cecs.anu.edu.au/u7545926/comp1110-ass2/-/blob/main/src/comp1110/ass2/Board.java#L55-65) function to board class.

This method is used to swap a given available resource with a given target resource provided that the joker 
J+(target+1) is available on the current player's board. It also converts the Joker to a Knight once it has been used.
The Junit test written for this method is TestswapResource and this checks whether the method works well over a range of boards and resource states.  
