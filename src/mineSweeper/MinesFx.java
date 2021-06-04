package mineSweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MinesFX extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vbox;
        MineSweeperController controller;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MineWindow.fxml"));
            vbox = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(vbox);

        primaryStage.setTitle("😎😎😎😎😎😎😎😎😎😎😎");
        primaryStage.setScene(scene);
        primaryStage.show();

//        Button resetButton = controller.resetGame();

    }

}
