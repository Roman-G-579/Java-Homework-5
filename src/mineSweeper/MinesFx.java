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

        MineController controller = new MineController();

        Parent root = FXMLLoader.load(getClass().getResource("MineWindow.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("😎😎😎😎😎😎😎😎😎😎😎");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
