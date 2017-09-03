package view.littleWin;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by LC on 2017/5/13.
 */
public class EndGame_win {

    String str_f = "您的得分是：:";
    String s;


    public void display() {
        Stage window = new Stage();
        window.setTitle("poly game 游戏结束");
        //modality要使用Modality.APPLICATION_MODEL
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(300);

        Button button = new Button("返回");
        button.setOnAction(e -> window.close());

        Label label_f = new Label(str_f);
        label_f.setAlignment(Pos.CENTER_RIGHT);
        Label label_s = new Label(s);
        label_s.setAlignment(Pos.CENTER_RIGHT);
        label_s.setFont(new Font("Arial", 40));


        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.BOTTOM_RIGHT);
        vBox.getChildren().addAll(label_f, label_s, button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        window.showAndWait();
    }


    public String getS() {
        return s;
    }

    public void setS(String str_s) {
        this.s = s;
    }

    public EndGame_win(String s) {
        this.s = s;
    }
}