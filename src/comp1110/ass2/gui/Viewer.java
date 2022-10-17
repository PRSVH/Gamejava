package comp1110.ass2.gui;

import comp1110.ass2.CatanDice;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Objects;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField playerTextField;
    private TextField boardTextField;


    /**
     * Show the state of a (single player's) board in the window.
     *
     * @param board_state:The string representation of the board state.
     */
    /**
     * Contributor: Wenxiao Wu
     */
    void displayState(String board_state) {
        //Board display description
        //If the player enters a road code such as R1, R1 will be
        // blocked by a polygon in the displayed window to indicate
        // that the road has been constructed. The same is true for
        // Settlements and city. In addition, when the player enters J1,
        // it means that a knight is built at 1 point, but it has not been used,
        // and the displayed window will have a blue triangle hat on the head of
        // the corresponding knight in the board. When the player enters K1, it means
        // that the knight at 1 point has been used, and the triangle hat of
        // the corresponding knight will turn red.


        // FIXME Task 5: implement the state viewer
        //set initial variables
        double road_x=710;
        double road_y=275;
        double settlement_x=740;
        double settlement_y=257.5;
        double city_x=537.5;
        double city_y=360;
        double knight_x=620;
        double knight_y=170;

        //set initial board
        Image image = new Image("comp1110/ass2/gui/image/newboard.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setLayoutX(500);
        imageView.setLayoutY(25);
        controls.getChildren().add(imageView);

        //Separate the input string with "," and store it in the string array
        String[] boardstate = board_state.split(",");

        //Check if the string encoding of a board state is well formed
        if (CatanDice.isBoardStateWellFormed(board_state)) {
            for (String s : boardstate) {
                if (Objects.equals(s, "R0")) {
                    road R0 = new road(road_x, road_y);
                    R0.setRotate(30);
                    controls.getChildren().add(R0);
                }
                if (Objects.equals(s, "R1")){
                    road R1 = new road(road_x-86, road_y+45);
                    R1.setRotate(90);
                    controls.getChildren().add(R1);
                }
                if (Objects.equals(s, "R2")){
                    road R2 = new road(road_x, road_y+91);
                    R2.setRotate(-30);
                    controls.getChildren().add(R2);
                }
                if (Objects.equals(s, "R3")){
                    road R3 = new road(road_x, road_y+220);
                    R3.setRotate(30);
                    controls.getChildren().add(R3);
                }
                if (Objects.equals(s, "R4")){
                    road R4 = new road(road_x-88, road_y+260);
                    R4.setRotate(90);
                    controls.getChildren().add(R4);
                }
                if (Objects.equals(s, "R5")){
                    road R5 = new road(road_x, road_y+305);
                    R5.setRotate(-30);
                    controls.getChildren().add(R5);
                }
                if (Objects.equals(s, "R6")){
                    road R6 = new road(road_x+108, road_y+365);
                    R6.setRotate(90);
                    controls.getChildren().add(R6);
                }
                if (Objects.equals(s, "R7")){
                    road R7 = new road(road_x+190, road_y+320);
                    R7.setRotate(30);
                    controls.getChildren().add(R7);
                }
                if (Objects.equals(s, "R8")){
                    road R8 = new road(road_x+190, road_y+200);
                    R8.setRotate(-30);
                    controls.getChildren().add(R8);
                }
                if (Objects.equals(s, "R9")){
                    road R9 = new road(road_x+190, road_y+105);
                    R9.setRotate(30);
                    controls.getChildren().add(R9);
                }
                if (Objects.equals(s, "R10")){
                    road R10 = new road(road_x+190, road_y-20);
                    R10.setRotate(-30);
                    controls.getChildren().add(R10);
                }
                if (Objects.equals(s, "R11")){
                    road R11 = new road(road_x+195, road_y-115);
                    R11.setRotate(30);
                    controls.getChildren().add(R11);
                }
                if (Objects.equals(s, "R12")){
                    road R12 = new road(road_x+300, road_y+255);
                    R12.setRotate(90);
                    controls.getChildren().add(R12);
                }
                if (Objects.equals(s, "R13")){
                    road R13 = new road(road_x+375, road_y+215);
                    R13.setRotate(30);
                    controls.getChildren().add(R13);
                }
                if (Objects.equals(s, "R14")){
                    road R14 = new road(road_x+375, road_y+90);
                    R14.setRotate(-30);
                    controls.getChildren().add(R14);
                }
                if (Objects.equals(s, "R15")){
                    road R15 = new road(road_x+380, road_y-5);
                    R15.setRotate(30);
                    controls.getChildren().add(R15);
                }
                if (Objects.equals(s, "S3")){
                    Settlements S3= new Settlements(settlement_x, settlement_y);
                    controls.getChildren().add(S3);
                }
                if (Objects.equals(s, "S4")){
                    Settlements S4= new Settlements(settlement_x, settlement_y+225);
                    controls.getChildren().add(S4);
                }
                if (Objects.equals(s, "S5")){
                    Settlements S5= new Settlements(settlement_x+7.5, settlement_y+434);
                    controls.getChildren().add(S5);
                }
                if (Objects.equals(s, "S7")){
                    Settlements S7= new Settlements(settlement_x+196, settlement_y+328);
                    controls.getChildren().add(S7);
                }
                if (Objects.equals(s, "S9")){
                    Settlements S9= new Settlements(settlement_x+190, settlement_y+113);
                    controls.getChildren().add(S9);
                }
                if (Objects.equals(s, "S11")){
                    Settlements S11= new Settlements(settlement_x+195, settlement_y-108);
                    controls.getChildren().add(S11);
                }
                if (Objects.equals(s, "C7")){
                    City C7= new City(city_x, city_y+5);
                    controls.getChildren().add(C7);
                }
                if (Objects.equals(s, "C12")){
                    City C12= new City(city_x, city_y+224);
                    controls.getChildren().add(C12);
                }
                if (Objects.equals(s, "C20")){
                    City C20= new City(city_x+569, city_y+113);
                    controls.getChildren().add(C20);
                }
                if (Objects.equals(s, "C30")){
                    City C30= new City(city_x+570, city_y-104);
                    controls.getChildren().add(C30);
                }
                if (Objects.equals(s, "J1")){
                    knight J1= new knight(knight_x, knight_y);
                    controls.getChildren().add(J1);
                }
                if (Objects.equals(s, "J2")){
                    knight J2= new knight(knight_x+1.5, knight_y+217);
                    controls.getChildren().add(J2);
                }
                if (Objects.equals(s, "J3")){
                    knight J3= new knight(knight_x+190.5, knight_y+325);
                    controls.getChildren().add(J3);
                }
                if (Objects.equals(s, "J4")){
                    knight J4= new knight(knight_x+382, knight_y+218);
                    controls.getChildren().add(J4);
                }
                if (Objects.equals(s, "J5")){
                    knight J5= new knight(knight_x+380, knight_y+3);
                    controls.getChildren().add(J5);
                }
                if (Objects.equals(s, "J6")){
                    knight J6= new knight(knight_x+191, knight_y-110);
                    controls.getChildren().add(J6);
                }
                if (Objects.equals(s, "K1")){
                    knight K1= new knight(knight_x, knight_y);
                    K1.setFill(Color.RED);
                    controls.getChildren().add(K1);
                }
                if (Objects.equals(s, "K2")){
                    knight K2= new knight(knight_x+1.5, knight_y+217);
                    K2.setFill(Color.RED);
                    controls.getChildren().add(K2);
                }
                if (Objects.equals(s, "K3")){
                    knight K3= new knight(knight_x+190.5, knight_y+325);
                    K3.setFill(Color.RED);
                    controls.getChildren().add(K3);
                }
                if (Objects.equals(s, "K4")){
                    knight K4= new knight(knight_x+382, knight_y+218);
                    K4.setFill(Color.RED);
                    controls.getChildren().add(K4);
                }
                if (Objects.equals(s, "K5")){
                    knight K5= new knight(knight_x+380, knight_y+3);
                    K5.setFill(Color.RED);
                    controls.getChildren().add(K5);
                }
                if (Objects.equals(s, "K6")){
                    knight K6= new knight(knight_x+191, knight_y-110);
                    K6.setFill(Color.RED);
                    controls.getChildren().add(K6);
                }



            }
        }
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Board State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(500);
        Button button = new Button("Show");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                displayState(boardTextField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel, boardTextField, button);
        hb.setSpacing(10);
        controls.getChildren().add(hb);
    }

    /**
     * Contributor: Wenxiao Wu
     */

    /**
     * Create a road class and Implement a method that creates a road
     */
    static class road extends Polygon {
        public  road(double x, double y) {
            getPoints().addAll(
                    x, y,
                    x, y +70.0,
                    x + 25.0, y +70.0,
                    x + 25.0, y
            );

            //Set color and initial rotation state
            setFill(Color.MIDNIGHTBLUE);
            setRotate(0);
        }
    }

    /**
     * Contributor: Wenxiao Wu
     */
    /**
     * Create a Settlements class and Implement a method that creates a Settlements
     */

    static class Settlements extends Polygon {
        public  Settlements(double x, double y) {
            getPoints().addAll(
                    x, y+5,
                    x, y -20.0,
                    x-5,y-20,
                    x +15.0, y -45.0,
                    x+35,y-20,
                    x+30,y-20,
                    x+30,y-20,
                    x+30,y+5
            );

            //Set color and initial rotation state
            setFill(Color.MIDNIGHTBLUE);
            setRotate(0);

        }
    }

    /**
     * Contributor: Wenxiao Wu
     */

    /**
     * Create a City class and Implement a method that creates a City
     */
    static class City extends Polygon {
        public  City(double x, double y) {
            getPoints().addAll(
                    x-2, y+5,
                    x-2,y-20,
                    x+20,y-20,
                    x+20,y-25,
                    x+15,y-25,
                    x+32.5,y-45,
                    x+50,y-25,
                    x+45,y-25,
                    x+45,y-20,
                    x+45,y+5


            );

            //Set color and initial rotation state
            setFill(Color.MIDNIGHTBLUE);
            setRotate(0);

        }
    }

    /**
     * Create a knight class and Implement a method that creates a knight
     */

    /**
     * Contributor: Wenxiao Wu
     */
    static class knight extends Polygon {
        public  knight(double x, double y) {
            getPoints().addAll(
                    x, y,
                    x + 15.0, y -30.0,
                    x + 30.0, y
            );

            //Set color and initial rotation state
            setFill(Color.MIDNIGHTBLUE);
            setRotate(0);
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Board State Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
