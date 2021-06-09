package mineSweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

        GridPane gridPane = new GridPane();

        if (!gridPane.getChildren().isEmpty()) {// TODO: 08/06/2021 remove existing gridpane
            for (int i = 0; i < gridPane.getRowCount(); i++) {

            }
        }

        int height = Integer.parseInt(heightController.getText());
        int width = Integer.parseInt(widthController.getText());
        int numOfMines = Integer.parseInt(minesController.getText());

        Mines game = new Mines(height, width, numOfMines);


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Button button = new Button();
                button.setText(game.get(i, j));
                button.setPrefSize(40, 40);
                int finalI = i;
                int finalJ = j;
                button.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                    if (e.isPrimaryButtonDown()) {
                        button.setStyle("");
                        game.open(finalI, finalJ);
                        if (game.getTile(finalI, finalJ).isHasMine()) {
                            button.setStyle("-fx-background-color: RED");
                        }
                    }
                    if (e.isSecondaryButtonDown()) {
                        game.toggleFlag(finalI, finalJ);
                        if (!game.getTile(finalI, finalJ).isOpen()) {
                            if (game.getTile(finalI, finalJ).isHasFlag()) {
                                button.setStyle("-fx-background-color: GREEN");
                            }
                            if (!game.getTile(finalI, finalJ).isHasFlag()) {
                                button.setStyle("");
                            }
                        }
                    }
                    button.setText(game.get(finalI, finalJ));
                });
                gridPane.add(button, j, i);
            }
        }
        gridLayout.getChildren().add(gridPane);
    }
}