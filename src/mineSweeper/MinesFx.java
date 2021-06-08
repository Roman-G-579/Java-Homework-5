package mineSweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MinesFX extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MineSweeperController controller = new MineSweeperController();

        Parent loader = FXMLLoader.load(getClass().getResource("MineWindow.fxml"));
        Scene scene = new Scene(loader);



        primaryStage.setTitle("😎😎😎😎😎😎😎😎😎😎😎");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
