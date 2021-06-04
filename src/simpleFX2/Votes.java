package simpleFX2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Votes extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent loader = FXMLLoader.load(getClass().getResource("question3.fxml"));
        Scene scene = new Scene(loader);

        primaryStage.setTitle("Voting Machine");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
