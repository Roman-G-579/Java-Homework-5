package mineSweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MinesFx extends Application {

    String content_Url = "<iframe width=\"560\" height=\"315\" " +
            "src=\"https://www.youtube.com/embed/dQw4w9WgXcQ\"></iframe>";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(content_Url);

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Scene scene = new Scene(root, 580, 335);

        primaryStage.setTitle("MineSweeper B-)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
