package mines;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MinesFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MineController controller = new MineController();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("MineWindow.fxml"));
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("mines/icon.png"));
            primaryStage.setTitle("😎Minesweeper😎");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.show();
    }
}
