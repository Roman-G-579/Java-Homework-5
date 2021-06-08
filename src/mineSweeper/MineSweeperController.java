package mineSweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MineSweeperController {

    @FXML
    private Button resetButton;

    @FXML
    private TextField widthController;

    @FXML
    private TextField heightController;

    @FXML
    private TextField minesController;

    @FXML
    private StackPane gridlayout;

    @FXML
    void newGame(ActionEvent event) {

        int height = Integer.parseInt(heightController.getText());
        int width = Integer.parseInt(widthController.getText());
        int numOfMines = Integer.parseInt(minesController.getText());

        Mines game = new Mines(height, width, numOfMines);

        GridPane gridPane = new GridPane();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gridPane.add();
            }
        }

    }

}
