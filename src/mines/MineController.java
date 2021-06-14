package mines;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MineController {

    private GridPane gridPane = new GridPane();
    @FXML
    private Button resetButton;
    @FXML
    private TextField widthController;
    @FXML
    private TextField heightController;
    @FXML
    private TextField minesController;
    @FXML
    private Pane gridLayout;

    @FXML
    void newGame(ActionEvent event) {
        gridPane.getChildren().clear();

        Image mineImage = new Image(getClass().getResourceAsStream("mine.png"),
                24, 24, false, false);
        Image flagImage = new Image(getClass().getResourceAsStream("flag.png"),
                24, 24, false, false);
        Image coolBig = new Image(getClass().getResourceAsStream("coolBig.png"));
        Image loseBig = new Image(getClass().getResourceAsStream("loseBig.png"));

        Alert winMsg = new Alert(Alert.AlertType.INFORMATION);
        winMsg.setTitle("Congratulations!!!!");
        winMsg.setHeaderText(null);
        winMsg.setContentText("WOW you won, you are the best");
        winMsg.setGraphic(new ImageView(coolBig));

        Alert loseMsg = new Alert(Alert.AlertType.INFORMATION);
        loseMsg.setTitle("Congratulations!!!!");
        loseMsg.setHeaderText(null);
        loseMsg.setContentText("ohh no, you lost");
        loseMsg.setGraphic(new ImageView(loseBig));


        gridPane = new GridPane();

        int height = Integer.parseInt(heightController.getText());
        int width = Integer.parseInt(widthController.getText());
        int numOfMines = Integer.parseInt(minesController.getText());

        Mines game = new Mines(height, width, numOfMines);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Button button = new Button();

                //sets the button's style
                button.setStyle("-fx-background-color: WHEAT");
                button.setText(".");
                button.setPrefSize(40, 40);

                int reserveI = i;
                int reserveJ = j;
                button.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                    //checks whether the left mouse or the right mouse button has been clicked
                    if (e.isPrimaryButtonDown() && !game.getTile(reserveI, reserveJ).isOpen()) {
                        game.open(reserveI, reserveJ);
                        if (game.getTile(reserveI, reserveJ).isHasMine()) {
                            game.setShowAll(true);
                            gridPane.setDisable(true);
                            loseMsg.showAndWait();
                        }
                        //checks whether the game is won
                        else if (game.isDone()) {
                            game.setShowAll(true);
                            gridPane.setDisable(true);
                            winMsg.showAndWait();
                        }
                    }
                    if (e.isSecondaryButtonDown()) {
                        if (!game.getTile(reserveI, reserveJ).isHasFlag()) {
                            button.setGraphic(new ImageView(flagImage));
                        }
                        if (game.getTile(reserveI, reserveJ).isHasFlag()) {
                            button.setGraphic(null);
                        }
                        game.toggleFlag(reserveI, reserveJ);
                    }

                    for (int k = 0; k < height; k++) {
                        for (int l = 0; l < width; l++) {
                            Button currentButton = currentButton = (Button) gridPane.getChildren().get(width * k + l);
                            currentButton.setText(game.get(k, l));

                            //if the current button is a mine
                            if (currentButton.getText().equals("X")) {
                                currentButton.setText("");
                                currentButton.setStyle("-fx-background-color: WHEAT");
                                currentButton.setGraphic(new ImageView(mineImage));
                            }

                            //if the current button is a flag
                            if (currentButton.getText().equals("F")) {
                                currentButton.setText("");
                                currentButton.setStyle("-fx-background-color: WHEAT");
                                currentButton.setGraphic(new ImageView(flagImage));
                            }

                            //if the current button is an open space
                            if (currentButton.getText().equals(" ")) {
                                currentButton.setStyle("-fx-background-color: CORNSILK");
                            }

                            //if the current button is a closed one
                            if (!currentButton.getText().equals("F") && !currentButton.getText().equals("X")) {
                                currentButton.setStyle("-fx-background-color: WHEAT");
                            }

                            //if the current button is open but not a flag, mine or a dot
                            if (!currentButton.getText().equals("F") && !currentButton.getText().equals("X")
                                    && !currentButton.getText().equals(".")) {
                                currentButton.setStyle("-fx-background-color: CORNSILK");
                            }
                        }
                    }
                    System.out.println(game);
                });
                if(height>=width) {
                    gridPane.add(button, j, i);
                }
                else if (height < width) {
                    gridPane.add(button,i,j);
                }
            }
        }
        gridLayout.getChildren().add(gridPane);
    }
}