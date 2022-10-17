## Code Review

Reviewed by: Wenxiao Wu, u7543073

Reviewing code written by: Wei Wei, u7545926

Component: [scene_design()](https://gitlab.cecs.anu.edu.au/u7545926/comp1110-ass2/-/blob/main/src/comp1110/ass2/gui/Game.java#L154-241)

### Comments 

1. This code overall is excellent ,  the logic is also clear, and the required functions are well implemented.

2. Critical code is partially commented. But there are parts of the code that are not well documented, which can lead to questions for others reading the code.

3. My group member WEIWEI is also implementing other methods to help program implementation, such as setSwapResourceOption(ChoiceBox<String> option), setSwapTargetOption(ChoiceBox<String> option) ,These can make the whole program more concise and logical clear.

4. At the same time, I think the names of the methods and variables are set correctly.
 
5. But I think there should be more comments in the method. Due to the complexity of the program code, other methods are also called, but some parts are not add enough commented, which can cause confusion for others when reading the code. For example, can add some comments in the section where the button corresponds to the event. Since most of the subsequent events call the same method, it only needs to be added at the first call. This neither adds too many repetitive comments, but also makes it easier for others to understand when reading the code. 

