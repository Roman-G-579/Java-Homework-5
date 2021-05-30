package simpleFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Voting extends Application {

    private Label votes;
    private int counter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        VBox box = new VBox();
        Scene scene = new Scene(box, 250, 100);
        HBox firstRow = new HBox();

        //creates the first button and it's behavior
        Button ofraButton = new Button("Ofra Haza");
        ofraButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                votes.setText(++counter + "");
                if (counter > 0) {
                    votes.setStyle("-fx-background-color: CYAN");
                }
                if (counter == 0) {
                    votes.setStyle("-fx-background-color: GRAY");
                }
            }
        });

        //creates the second button and it's behavior
        Button yardenaButton = new Button("Yardena Arazi");
        yardenaButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                votes.setText(--counter + "");
                if (counter < 0) {
                    votes.setStyle("-fx-background-color: TOMATO");
                }
                if (counter == 0) {
                    votes.setStyle("-fx-background-color: GRAY");
                }
            }
        });

        //creates the votes label
        votes = new Label(counter + "");
        //sets the label's background color to gray
        votes.setStyle("-fx-background-color: GRAY");
        //sets the text inside the label to align at the center
        votes.setAlignment(Pos.CENTER);
        //sets the label width to fit the width of the window
        votes.setMaxWidth(Double.MAX_VALUE);
        //sets label height to be 30px
        votes.setMinHeight(30);

        //adds the two buttons
        firstRow.getChildren().addAll(ofraButton, yardenaButton);
        firstRow.setSpacing(10);
        box.getChildren().addAll(firstRow);

        //adds spacing on all edges of the window
        box.setPadding(new Insets(10));

        //adds spacing between the layouts
        box.setSpacing(10);

        //adds the label as the second row
        box.getChildren().add(votes);

        stage.setTitle("Voting Machine");
        stage.setScene(scene);
        stage.show();

    }
}