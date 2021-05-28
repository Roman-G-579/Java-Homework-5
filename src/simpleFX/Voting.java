package simpleFX;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

public class Voting extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 250, 120);
        root.setPadding(new Insets(10));
        root.setVgap(30);
        root.setHgap(50);
        root.setGridLinesVisible(true);

        Button ofraButton = new Button("Ofra Haza");
        Button yardenaButton = new Button("Yardena Arazi");

        Label votes = new Label("0");
        votes.setStyle("-fx-background-color: RED");
        GridPane.setHalignment(votes, HPos.CENTER);
        GridPane.setFillWidth(votes, true);
        votes.setMaxWidth(Double.MAX_VALUE);

        root.add(ofraButton, 0, 0);
        root.add(yardenaButton, 1, 0);
        root.add(votes, 0, 1, 2, 1);
        stage.setTitle("Voting Machine");
        stage.setScene(scene);
        stage.show();
    }

    public interface EventHandler<T extends Event> {
        void Handle(T event);
    }
}
