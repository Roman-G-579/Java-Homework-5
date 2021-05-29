package simpleFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Voting extends Application {

    private Label votes;
    private int counter;

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
        ofraButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                votes.setText(++counter + "");
            }
        });
        Button yardenaButton = new Button("Yardena Arazi");
        yardenaButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                votes.setText(--counter + "");
            }
        });

        votes = new Label(counter + "");
        votes.setStyle("-fx-background-color: RED");
        votes.setAlignment(Pos.CENTER);
        votes.setMaxWidth(Double.MAX_VALUE);
        votes.setMinHeight(30);
        GridPane.setHalignment(votes, HPos.CENTER);

        root.add(ofraButton, 0, 0);
        root.add(yardenaButton, 1, 0);
        root.add(votes, 0, 1, 2, 1);
        stage.setTitle("Voting Machine");
        stage.setScene(scene);
        stage.show();

    }
}


/*    BorderPane base = new BorderPane();

    //creates the top layout for the two buttons
    HBox buttons = new HBox();
        base.setTop(buttons);

                //designs the spacing in the buttons layouts
                buttons.setSpacing(10);
                buttons.setPadding(new Insets(10));

                //creates the buttons
                Button ofraButton = new Button("Ofra Haza");
                Button yardenaButton = new Button("Yardena Arazi");

                //adds the buttons to the layout
                buttons.getChildren().addAll(ofraButton, yardenaButton);

                //creates the center layout for the votes label
                Pane center = new Pane();
                Label votes = new Label(counter + "");
                votes.setAlignment(Pos.CENTER);
                votes.setStyle("-fx-background-color: RED");
                base.setCenter(center);
                center.getChildren().add(votes);

                //creates the event handlers for the buttons
                ofraButton.setOnAction(new EventHandler<>() {
@Override
public void handle(ActionEvent event) {
        counter++;
        votes.setText(counter + "");
        }
        });
        yardenaButton.setOnAction(new EventHandler<>() {
@Override
public void handle(ActionEvent event) {
        counter--;
        votes.setText(counter + "");
        }
        });

        Scene scene = new Scene(base, 250, 120);

        stage.setTitle("Voting Machine");
        stage.setScene(scene);
        stage.show();*/
