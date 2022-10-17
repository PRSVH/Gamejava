# New Code for Deliverable D2D

## < u7545926 > < Wei Wei >

For Deliverable D2D, I contributed the following new statements of original code:

- Extended the [Board](https://gitlab.cecs.anu.edu.au/u7545926/comp1110-ass2/-/blob/main/src/comp1110/ass2/Board.java) class
- Added the [showBuildableConstructions()](https://gitlab.cecs.anu.edu.au/u7545926/comp1110-ass2/-/blob/main/src/comp1110/ass2/Board.java#L121-205) function to Board class.
- etc.

This Board class is used to implement all the functions about player's action on the board;   
This Board class use an ArrayList<Construction> to contain all constructions and get resource state from Player;  
For showBuildableConstructions(), it will return an ArrayList containing all these constructions which player can build at current stage;    
The logic of this method is firstly check if some constructions without build constraints can be built, such as R0, S3, C7, J1;  
If some of these constructions haven't been built and player have enough resource to build it, that construction will be added to ArrayList;  
Then, the method will infer some constructions based on current board state and player's resource state ,and then added these constructions to ArrayList;  
However, the order of these constructions might not in order and therefore would be difficult for testing;  
So the final step is to order these constructions;  
The purpose for implement this method is to make gui more concise;  
We use dropdown menu for player to select construction they want to build and there will be too many options if we provide all constructions;  