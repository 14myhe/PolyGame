package view.littleWin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by LC on 2017/5/12.
 */
public class Score_win extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane scorePane = new BorderPane();
        primaryStage.setTitle("poly game - 得分记录");
        primaryStage.setScene(new Scene(scorePane, 200, 100));
        primaryStage.show();
    }

    Score_win(){
        launch();
    }
}
