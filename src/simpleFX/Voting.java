package simpleFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

public class Voting extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        HBox root = new HBox();
        Scene scene = new Scene(root, 640, 480);


        Button ofraButton = new Button("Ofra Haza");
        Button yardenaButton = new Button("Yardena Arazi");


        root.getChildren().add(ofraButton);
        ofraButton.setPadding(new Insets(10,50,10,10));
        yardenaButton.setPadding(new Insets(10,10,10,50));
        root.getChildren().add(yardenaButton);
        stage.setTitle("Voting");
        stage.setScene(scene);
        stage.show();
    }

    public interface EventHandler<T extends Event>{
        void Handle(T event);
    }
}
