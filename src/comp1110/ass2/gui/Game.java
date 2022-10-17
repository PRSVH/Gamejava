package comp1110.ass2.gui;

import comp1110.ass2.Board;
import comp1110.ass2.CatanDice;
import comp1110.ass2.Construction;
import comp1110.ass2.Player;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;
    Player player = new Player();
    Board board = new Board(player);
    int rollTimes = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene_design();
        stage.setScene(scene);
        stage.show();
        displayState(board.boardState());
        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("Exit");
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            window.setTitle("Confirmation");
            window.setWidth(250);
            window.setHeight(200);

            String message = "The game will not be saved. \nDo you still want to exit the game?";
            Label label = new Label(message);
            label.setLayoutX(50);
            label.setLayoutY(30);
            Button Yes = new Button("Yes");
            Yes.setLayoutX(70);
            Yes.setLayoutY(110);
            Button No = new Button("No");
            No.setLayoutX(150);
            No.setLayoutY(110);
            Yes.setOnAction(e->{
                window.close();
                stage.close();
            });
            No.setOnAction(e->{
                window.close();
            });

            Group Ab = new Group();
            Ab.getChildren().addAll(label, Yes, No);

            Scene scene1 = new Scene(Ab);
            window.setScene(scene1);
            window.showAndWait();
        });
    }

    /**
     * Contributor: Wei Wei
     */
    public void scene_design(){
        player.firstRoll();
        Label turn = new Label("Turn: " + player.getTurn());
        turn.setLayoutX(100);
        turn.setLayoutY(50);
        root.getChildren().add(turn);

        // show resource state
        Button skip = new Button("skip");
        Label ore = new Label("Ore: "+ player.getResource_state()[0]);
        Label grain = new Label("Grain: "+ player.getResource_state()[1]);
        Label wool = new Label("Wool: "+ player.getResource_state()[2]);
        Label timber = new Label("Timber: "+ player.getResource_state()[3]);
        Label brick = new Label("Bricks: "+ player.getResource_state()[4]);
        Label gold = new Label("Gold: "+ player.getResource_state()[5]);
        ore.setLayoutX(100);
        ore.setLayoutY(100);
        grain.setLayoutX(200);
        grain.setLayoutY(100);
        wool.setLayoutX(300);
        wool.setLayoutY(100);
        timber.setLayoutX(100);
        timber.setLayoutY(150);
        brick.setLayoutX(200);
        brick.setLayoutY(150);
        gold.setLayoutX(300);
        gold.setLayoutY(150);

        //roll button
        Button roll = new Button("roll again");
        TextField O = new TextField();
        O.setPromptText("Number of Ore");
        TextField G = new TextField();
        G.setPromptText("Number of Grain");
        TextField W = new TextField();
        W.setPromptText("Number of Wool");
        TextField T = new TextField();
        T.setPromptText("Number of Timber");
        TextField B = new TextField();
        B.setPromptText("Number of Bricks");
        TextField Go = new TextField();
        Go.setPromptText("Number of Gold");
        O.setLayoutX(100);
        O.setLayoutY(240);
        G.setLayoutX(100);
        G.setLayoutY(280);
        W.setLayoutX(100);
        W.setLayoutY(320);
        T.setLayoutX(100);
        T.setLayoutY(360);
        B.setLayoutX(100);
        B.setLayoutY(400);
        Go.setLayoutX(100);
        Go.setLayoutY(440);
        TextField[] textFields = new TextField[]{O, G, W, T, B, Go};
        roll.setLayoutX(100);
        roll.setLayoutY(200);

        //skip button
        skip.setLayoutX(200);
        skip.setLayoutY(200);
        this.root.getChildren().addAll(ore, grain, wool, timber, brick, gold, roll, skip);
        this.root.getChildren().addAll(O,G,W,T,B,Go);

        //show map need to be extended later
        Label map = new Label("Map: "+board.boardState());
        map.setLayoutX(500);
        map.setLayoutY(500);
        this.root.getChildren().add(map);

        //build, trade, swap action
        Button build = new Button("build");
        ChoiceBox<String> constructionChoice = new ChoiceBox<>();
        //setConstructionChoice(constructionChoice);
        build.setLayoutX(100);
        build.setLayoutY(200);
        constructionChoice.setLayoutX(100);
        constructionChoice.setLayoutY(250);

        Button trade = new Button("trade");
        ChoiceBox<String> tradeChoice = new ChoiceBox<>();
        tradeChoice.getItems().addAll("Ore", "Grain", "Wool", "Timber", "Brick");
        trade.setLayoutX(200);
        trade.setLayoutY(200);
        tradeChoice.setLayoutX(200);
        tradeChoice.setLayoutY(250);

        Button swap = new Button("swap");
        ChoiceBox<String> swapResource = new ChoiceBox<>();
        ChoiceBox<String> swapTarget = new ChoiceBox<>();
        setSwapResourceOption(swapResource);
        setSwapTargetOption(swapTarget);
        swap.setLayoutX(100);
        swap.setLayoutY(300);
        swapResource.setLayoutX(100);
        swapResource.setLayoutY(350);
        swapTarget.setLayoutX(200);
        swapTarget.setLayoutY(350);

        // show score
        Label Score = new Label("Score: " + player.totalScore());
        Score.setLayoutX(100);
        Score.setLayoutY(550);
        this.root.getChildren().add(Score);

        //to next turn
        Button next = new Button("next");
        next.setLayoutX(100);
        next.setLayoutY(600);

        //have a new game Contributor: Wenxiao Wu
        Button new_game = new Button("New Game");
        new_game.setLayoutX(200);
        new_game.setLayoutY(600);

        //show build rule Contributor: Wenxiao Wu
        Button build_rule= new Button("Build Rule");
        build_rule.setLayoutX(380);
        build_rule.setLayoutY(120);
        this.root.getChildren().add(build_rule);

        roll.setOnAction(e->{
            String[] textField = new String[]{O.getText(), G.getText(), W.getText(), T.getText(), B.getText(), Go.getText()};
            if (player.roll_again(textField)){
                rollTimes++;
                ore.setText("Ore: "+ player.getResource_state()[0]);
                grain.setText("Grain: "+ player.getResource_state()[1]);
                wool.setText("Wool: "+ player.getResource_state()[2]);
                timber.setText("Timber: "+ player.getResource_state()[3]);
                brick.setText("Bricks: "+ player.getResource_state()[4]);
                gold.setText("Gold: "+ player.getResource_state()[5]);
            } else {
                AlertBox("Problem", "roll action is not correct");
            }
            clearTextField(textFields);

            if (rollTimes == 2){
                this.root.getChildren().removeAll(roll, skip, O,G,W,T,B,Go);
                this.root.getChildren().addAll(build, trade, swap, constructionChoice,
                        tradeChoice, swapResource, swapTarget, next,new_game);
                setSwapResourceOption(swapResource);
                setConstructionChoice(constructionChoice);
            }
        });

        skip.setOnAction(e ->{
            this.root.getChildren().removeAll(roll, skip, O,G,W,T,B,Go);
            this.root.getChildren().addAll(build, trade, swap, constructionChoice,
                    tradeChoice, swapResource, swapTarget, next,new_game);
            setSwapResourceOption(swapResource);
            setConstructionChoice(constructionChoice);
        });

        build.setOnAction(actionEvent -> {
            if (constructionChoice.getValue()!=null){
                Construction construction = new Construction(constructionChoice.getValue());
                System.out.println("want to build " + construction);
                if (board.buildConstruction(construction)){
                    refreshResource(ore, grain, wool, timber, brick, gold);
                    refreshMap();
                    setSwapTargetOption(swapTarget);
                    setSwapResourceOption(swapResource);
                    setConstructionChoice(constructionChoice);
                } else {
                    System.out.println("cannot build " + construction);
                }
            }
        });

        trade.setOnAction(e->{
            int i = resNameToInt(tradeChoice);
            if (player.trade(i)){
                refreshResource(ore, grain, wool, timber, brick, gold);
                setSwapResourceOption(swapResource);
                setConstructionChoice(constructionChoice);
            } else {
                AlertBox("Problem","You cannot trade any resource with gold less than 2.");
            }
        });

        swap.setOnAction(e->{
            int Res = resNameToInt(swapResource);
            int Target = resNameToInt(swapTarget);
            if (board.swapResource(Res, Target)){
                refreshResource(ore, grain, wool, timber, brick, gold);
                refreshMap();
                setSwapTargetOption(swapTarget);
                setSwapResourceOption(swapResource);
                setConstructionChoice(constructionChoice);
            } else {
                AlertBox("Problem","Cannot swap");
            }
        });

        next.setOnAction(e->{
            rollTimes = 0;
            player.nextTurn();
            turn.setText("Turn: " + player.getTurn());
            Score.setText("Score: " + player.totalScore());
            if (player.getTurn()>15){
                endgame("Your score is " + player.totalScore());
                Stage stage = (Stage) next.getScene().getWindow();
                stage.close();
                return;
            }
            player.firstRoll();
            refreshResource(ore, grain, wool, timber, brick, gold);
            this.root.getChildren().removeAll(build, trade, swap, constructionChoice,
                    tradeChoice, swapResource, swapTarget, next,new_game);
            this.root.getChildren().addAll(roll, skip, O,G,W,T,B,Go);
        });

        //Contributor: Wenxiao Wu
        new_game.setOnAction(e->{
            rollTimes = 0;
            player.restartTurn();
            turn.setText("Turn: " + player.getTurn());
            Score.setText("Score: "+player.totalScore());

            player.firstRoll();
            player.restartTurn();
            board.restartboard();
            player.firstRoll();
            refreshMap();
            refreshResource(ore, grain, wool, timber, brick, gold);
            this.root.getChildren().removeAll(build, trade, swap, constructionChoice,
                    tradeChoice, swapResource, swapTarget, next,new_game);
            this.root.getChildren().addAll(roll, skip, O,G,W,T,B,Go);
            System.out.println("Game Restart!");

        });

        //Contributor: Wenxiao Wu
        build_rule.setOnAction(e->hit_buildrule());
    }

    /**
     * Contributor: Wei Wei
     */
    public void refreshResource(Label ore, Label grain, Label wool, Label timber, Label brick, Label gold){
        ore.setText("Ore: "+ player.getResource_state()[0]);
        grain.setText("Grain: "+ player.getResource_state()[1]);
        wool.setText("Wool: "+ player.getResource_state()[2]);
        timber.setText("Timber: "+ player.getResource_state()[3]);
        brick.setText("Bricks: "+ player.getResource_state()[4]);
        gold.setText("Gold: "+ player.getResource_state()[5]);
    }
    /**
     * Contributor: Wenxiao Wu
     */
    public void refreshMap(){
        displayState(board.boardState());
    }

    /**
     * Contributor: Wei Wei
     */
    public void setSwapTargetOption(ChoiceBox<String> option){
        option.getItems().remove("Ore");
        option.getItems().remove("Grain");
        option.getItems().remove("Wool");
        option.getItems().remove("Timber");
        option.getItems().remove("Brick");
        String[] strings = board.showTargetAvailableForSwap();
        for (String string : strings) {
            option.getItems().add(string);
        }
    }

    /**
     * Contributor: Wei Wei
     */
    public void setSwapResourceOption(ChoiceBox<String> option){
        option.getItems().remove("Ore");
        option.getItems().remove("Grain");
        option.getItems().remove("Wool");
        option.getItems().remove("Timber");
        option.getItems().remove("Brick");
        option.getItems().remove("Gold");
        int[] resource_state = player.getResource_state();
        if (resource_state[0]!=0){
            option.getItems().add("Ore");
        }
        if (resource_state[1]!=0){
            option.getItems().add("Grain");
        }
        if (resource_state[2]!=0){
            option.getItems().add("Wool");
        }
        if (resource_state[3]!=0){
            option.getItems().add("Timber");
        }

        if (resource_state[4]!=0){
            option.getItems().add("Brick");
        }

        if (resource_state[5]!=0){
            option.getItems().add("Gold");
        }
    }

    /**
     * Contributor: Wei Wei
     */
    ArrayList<String> oldChoices = new ArrayList<>();
    public void setConstructionChoice(ChoiceBox<String> option){
        ArrayList<String> newChoices = board.showBuildableConstructions();
        ArrayList<Construction> constructions = board.getConstructions();
        if (!oldChoices.isEmpty()){
            for (String oldChoice : oldChoices) {
                if (option.getItems().remove(oldChoice)){
                    System.out.println(oldChoice + " removed");
                }
            }
        }
        oldChoices.clear();
        oldChoices.addAll(newChoices);
        for (String string : newChoices) {
            option.getItems().add(string);
            System.out.println(string + " added");
        }
    }

    /**
     * Contributor: Wei Wei
     */
    public int resNameToInt(ChoiceBox<String> choiceBox){
        String res = choiceBox.getValue();
        int resource = -1;
        switch (res) {
            case "Ore" -> resource = 0;
            case "Grain" -> resource = 1;
            case "Wool" -> resource = 2;
            case "Timber" -> resource = 3;
            case "Brick" -> resource = 4;
            case "Gold" -> resource = 5;
        }
        return resource;
    }

    /**
     * Contributor: Wei Wei
     */
    public void clearTextField(TextField[] textFields){
        for (TextField textField : textFields) {
            textField.setText("");
        }
    }

    /**
     * Contributor: Wenxiao Wu
     */

    void displayState(String board_state) {
        double road_x=710;
        double road_y=275;
        double settlement_x=740;
        double settlement_y=257.5;
        double city_x=537.5;
        double city_y=360;
        double knight_x=620;
        double knight_y=170;

        Image image = new Image("comp1110/ass2/gui/image/newboard.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setLayoutX(500);
        imageView.setLayoutY(25);
        root.getChildren().add(imageView);

        String[] boardstate = board_state.split(",");
        if (CatanDice.isBoardStateWellFormed(board_state)) {
            for (String s : boardstate) {
                if (Objects.equals(s, "R0")) {
                    Game.road R0 = new Game.road(road_x, road_y);
                    R0.setRotate(30);
                    root.getChildren().add(R0);
                }
                if (Objects.equals(s, "R1")){
                    Game.road R1 = new Game.road(road_x-85, road_y+45);
                    R1.setRotate(90);
                    root.getChildren().add(R1);
                }
                if (Objects.equals(s, "R2")){
                    Game.road R2 = new Game.road(road_x, road_y+90);
                    R2.setRotate(-30);
                    root.getChildren().add(R2);
                }
                if (Objects.equals(s, "R3")){
                    Game.road R3 = new Game.road(road_x, road_y+220);
                    R3.setRotate(30);
                    root.getChildren().add(R3);
                }
                if (Objects.equals(s, "R4")){
                    Game.road R4 = new Game.road(road_x-85, road_y+260);
                    R4.setRotate(90);
                    root.getChildren().add(R4);
                }
                if (Objects.equals(s, "R5")){
                    Game.road R5 = new Game.road(road_x, road_y+305);
                    R5.setRotate(-30);
                    root.getChildren().add(R5);
                }
                if (Objects.equals(s, "R6")){
                    Game.road R6 = new Game.road(road_x+110, road_y+365);
                    R6.setRotate(90);
                    root.getChildren().add(R6);
                }
                if (Objects.equals(s, "R7")){
                    Game.road R7 = new Game.road(road_x+190, road_y+320);
                    R7.setRotate(30);
                    root.getChildren().add(R7);
                }
                if (Objects.equals(s, "R8")){
                    Game.road R8 = new Game.road(road_x+190, road_y+200);
                    R8.setRotate(-30);
                    root.getChildren().add(R8);
                }
                if (Objects.equals(s, "R9")){
                    Game.road R9 = new Game.road(road_x+190, road_y+105);
                    R9.setRotate(30);
                    root.getChildren().add(R9);
                }
                if (Objects.equals(s, "R10")){
                    Game.road R10 = new Game.road(road_x+190, road_y-20);
                    R10.setRotate(-30);
                    root.getChildren().add(R10);
                }
                if (Objects.equals(s, "R11")){
                    Game.road R11 = new Game.road(road_x+195, road_y-115);
                    R11.setRotate(30);
                    root.getChildren().add(R11);
                }
                if (Objects.equals(s, "R12")){
                    Game.road R12 = new Game.road(road_x+300, road_y+255);
                    R12.setRotate(90);
                    root.getChildren().add(R12);
                }
                if (Objects.equals(s, "R13")){
                    Game.road R13 = new Game.road(road_x+375, road_y+215);
                    R13.setRotate(30);
                    root.getChildren().add(R13);
                }
                if (Objects.equals(s, "R14")){
                    Game.road R14 = new Game.road(road_x+375, road_y+90);
                    R14.setRotate(-30);
                    root.getChildren().add(R14);
                }
                if (Objects.equals(s, "R15")){
                    Game.road R15 = new Game.road(road_x+380, road_y-5);
                    R15.setRotate(30);
                    root.getChildren().add(R15);
                }
                if (Objects.equals(s, "S3")){
                    Game.Settlements S3= new Game.Settlements(settlement_x, settlement_y);
                    root.getChildren().add(S3);
                }
                if (Objects.equals(s, "S4")){
                    Game.Settlements S4= new Game.Settlements(settlement_x, settlement_y+225);
                    root.getChildren().add(S4);
                }
                if (Objects.equals(s, "S5")){
                    Game.Settlements S5= new Game.Settlements(settlement_x+7.5, settlement_y+432.5);
                    root.getChildren().add(S5);
                }
                if (Objects.equals(s, "S7")){
                    Game.Settlements S7= new Game.Settlements(settlement_x+195, settlement_y+327.5);
                    root.getChildren().add(S7);
                }
                if (Objects.equals(s, "S9")){
                    Game.Settlements S9= new Game.Settlements(settlement_x+190, settlement_y+113);
                    root.getChildren().add(S9);
                }
                if (Objects.equals(s, "S11")){
                    Game.Settlements S11= new Game.Settlements(settlement_x+195, settlement_y-108);
                    root.getChildren().add(S11);
                }
                if (Objects.equals(s, "C7")){
                    Game.City C7= new Game.City(city_x, city_y+5);
                    root.getChildren().add(C7);
                }
                if (Objects.equals(s, "C12")){
                    Game.City C12= new Game.City(city_x, city_y+220);
                    root.getChildren().add(C12);
                }
                if (Objects.equals(s, "C20")){
                    Game.City C20= new Game.City(city_x+567.5, city_y+112.5);
                    root.getChildren().add(C20);
                }
                if (Objects.equals(s, "C30")){
                    Game.City C30= new Game.City(city_x+570, city_y-105);
                    root.getChildren().add(C30);
                }
                if (Objects.equals(s, "J1")){
                    Game.knight J1= new Game.knight(knight_x, knight_y);
                    root.getChildren().add(J1);
                }
                if (Objects.equals(s, "J2")){
                    Game.knight J2= new Game.knight(knight_x+1.5, knight_y+217);
                    root.getChildren().add(J2);
                }
                if (Objects.equals(s, "J3")){
                    Game.knight J3= new Game.knight(knight_x+190.5, knight_y+325);
                    root.getChildren().add(J3);
                }
                if (Objects.equals(s, "J4")){
                    Game.knight J4= new Game.knight(knight_x+382, knight_y+218);
                    root.getChildren().add(J4);
                }
                if (Objects.equals(s, "J5")){
                    Game.knight J5= new Game.knight(knight_x+380, knight_y+3);
                    root.getChildren().add(J5);
                }
                if (Objects.equals(s, "J6")){
                    Game.knight J6= new Game.knight(knight_x+191, knight_y-110);
                    root.getChildren().add(J6);
                }
                if (Objects.equals(s, "K1")){
                    Game.knight K1= new Game.knight(knight_x, knight_y);
                    K1.setFill(Color.RED);
                    root.getChildren().add(K1);
                }
                if (Objects.equals(s, "K2")){
                    Game.knight K2= new Game.knight(knight_x+1.5, knight_y+217);
                    K2.setFill(Color.RED);
                    root.getChildren().add(K2);
                }
                if (Objects.equals(s, "K3")){
                    Game.knight K3= new Game.knight(knight_x+190.5, knight_y+325);
                    K3.setFill(Color.RED);
                    root.getChildren().add(K3);
                }
                if (Objects.equals(s, "K4")){
                    Game.knight K4= new Game.knight(knight_x+382, knight_y+218);
                    K4.setFill(Color.RED);
                    root.getChildren().add(K4);
                }
                if (Objects.equals(s, "K5")){
                    Game.knight K5= new Game.knight(knight_x+380, knight_y+3);
                    K5.setFill(Color.RED);
                    root.getChildren().add(K5);
                }
                if (Objects.equals(s, "K6")){
                    Game.knight K6= new Game.knight(knight_x+191, knight_y-110);
                    K6.setFill(Color.RED);
                    root.getChildren().add(K6);
                }



            }
        }
    }

    /**
     * Contributor: Wenxiao Wu
     */
    class road extends Polygon {
        public  road(double x, double y) {
            getPoints().addAll(
                    x, y,
                    x, y +70.0,
                    x + 25.0, y +70.0,
                    x + 25.0, y
            );

            // set up the visuals and a click listener for the tile
            setFill(Color.LIGHTGREY);
            setStrokeWidth(1);
            setStroke(Color.BLACK);
            setRotate(0);
        }
    }
    /**
     * Contributor: Wenxiao Wu
     */

    class Settlements extends Polygon {
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

            // set up the visuals and a click listener for the tile
            setFill(Color.LIGHTGREY);
            setStrokeWidth(1);
            setStroke(Color.BLACK);
            setRotate(0);

        }
    }

    /**
     * Contributor: Wenxiao Wu
     */
    class City extends Polygon {
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

            // set up the visuals and a click listener for the tile
            setFill(Color.LIGHTGREY);
            setStrokeWidth(1);
            setStroke(Color.BLACK);
            setRotate(0);

        }
    }

    /**
     * Contributor: Wenxiao Wu
     */
    class knight extends Polygon {
        public  knight(double x, double y) {
            getPoints().addAll(
                    x, y,
                    x + 15.0, y -30.0,
                    x + 30.0, y
            );

            // set up the visuals and a click listener for the tile
            setFill(Color.LIGHTGREY);
            setStrokeWidth(1);
            setStroke(Color.BLACK);
            setRotate(0);
        }
    }

    /**
     * Contributor: Wenxiao Wu
     */
    public void endgame(String player_score){
        Image image = new Image("comp1110/ass2/gui/image/Thank-you-for-playing.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setLayoutX(500);
        imageView.setLayoutY(0);


        Stage window = new Stage();
        window.setTitle("GAME END");
        window.setWidth(700);
        window.setHeight(550);
        Label label = new Label(player_score);

        label.setStyle("-fx-font: 26 TimesRomes;");


        Button close = new Button("Close");
        close.setOnAction(e->{
            window.close();
        });
        VBox ed = new VBox(10);
        ed.setAlignment(Pos.CENTER);
        ed.getChildren().add(imageView);
        ed.getChildren().add(label);
        ed.getChildren().add(close);

        Scene scene = new Scene(ed);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Contributor: Wenxiao Wu
     */
    public void hit_buildrule(){
        Image image = new Image("comp1110/ass2/gui/image/build_rule.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setLayoutX(500);
        imageView.setLayoutY(0);

        Stage window = new Stage();
        window.setTitle("Build rule");
        window.setWidth(750);
        window.setHeight(600);


        Button close = new Button("Close");
        close.setOnAction(e->window.close());
        VBox hb = new VBox(10);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(imageView);
        hb.getChildren().add(close);

        Scene scene = new Scene(hb);
        window.setScene(scene);
        window.showAndWait();

    }


    /**
     * Contributor: Wei Wei
     */
    public void AlertBox(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setWidth(250);
        window.setHeight(200);

        Label label = new Label(message);
        label.setLayoutX(100);
        label.setLayoutY(100);
        Button close = new Button("Close");
        close.setOnAction(e->window.close());
        VBox Ab = new VBox(10);
        Ab.getChildren().addAll(label, close);
        Ab.setAlignment(Pos.CENTER);

        Scene scene = new Scene(Ab);
        window.setScene(scene);
        window.showAndWait();
    }
}
