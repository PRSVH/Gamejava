package comp1110.ass2.gui;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.Random;
import comp1110.ass2.CatanDice;
import comp1110.ass2.Player;

public class DiceSimulator extends Application {
    private ImageView viewDie1 = new ImageView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
