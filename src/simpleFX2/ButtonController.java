package simpleFX2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ButtonController {

    private int counter = 0;

    @FXML
    private Button ofraButton;

    @FXML
    private Button yardenaButton;

    @FXML
    private Label label;

    @FXML
    void decrease(ActionEvent event) {
        label.setText(Integer.toString(--counter));
    }

    @FXML
    void increase(ActionEvent event) {
        label.setText(Integer.toString(++counter));
    }

}