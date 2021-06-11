package mines;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MineController {

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

        Image image = new Image(getClass().getResourceAsStream("flag.png"),
                20, 20, false, false);

        GridPane gridPane = new GridPane();


        int height = Integer.parseInt(heightController.getText());
        int width = Integer.parseInt(widthController.getText());
        int numOfMines = Integer.parseInt(minesController.getText());

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
                Mines game = new Mines(height, width, numOfMines);
                game(image, game, height, width, numOfMines);
            }
        });

        Mines game = new Mines(height, width, numOfMines);
        game(image, game, height, width, numOfMines);

    }

    void game(Image image, Mines game, int height, int width, int numOfMines) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Button button = new Button();
                button.setText(".");
                button.setPrefSize(40, 40);
                int reserveI = i;
                int reserveJ = j;
                button.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                    //checks whether the left mouse or the right mouse button has been clicked
                    if (e.isPrimaryButtonDown() && !game.getTile(reserveI, reserveJ).isOpen()) {
                        game.open(reserveI, reserveJ);

                        //if the current tile contains a mine, paints it red
                        if (game.getTile(reserveI, reserveJ).isHasMine()) {
                            button.setStyle("-fx-background-color: RED");
                        }
                    }
                    if (e.isSecondaryButtonDown()) {
                        game.toggleFlag(reserveI, reserveJ);
                        if (!game.getTile(reserveI, reserveJ).isOpen()) {
                            //if right clicked on the tile, colors the tile green or clears the color
                            if (game.getTile(reserveI, reserveJ).isHasFlag()) {
                                //adds to the current tile an image of a flag
                                button.setGraphic(new ImageView(image));
                            } else {
                                //clears the image of the flag from the tile
                                button.setGraphic(null);
                            }
                        }
                    }
                    System.out.println(game);
                    for (int k = 0; k < height; k++) {
                        for (int l = 0; l < width; l++) {
                            Button currentButton = (Button) gridPane.getChildren().get(height * k + l);
                            currentButton.setText(game.get(k, l));
                            if (currentButton.getText().equals("F")) {
                                currentButton.setText("");
                            }
                            if (!currentButton.getText().equals(".") && !currentButton.getText().equals("X")
                                    && !currentButton.getText().equals("")) {
                                currentButton.setStyle("-fx-background-color: BURLYWOOD");
                            }
                        }
                    }
                    System.out.println(game);
                });
                gridPane.add(button, j, i);
                if (game.isDone()){
                    System.out.println("YOU WON! the G.O.A.T is in the house");
                }
            }
        }
        gridLayout.getChildren().add(gridPane);
    }
}