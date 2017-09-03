package view.littleWin;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by LC on 2017/5/12.
 */
public class Rules_win{

    String str_title = "规则说明:";
    String str_line_1 = "多边形游戏是一个单人玩的游戏，开始时有一个由n个顶点构成的多边形。 ";
    String str_line_2 = "";
    String str_line_3 = "";
    String str_line_4 = "";
    String str_line_5 = "";


    public void display(){
        Stage window = new Stage();
        window.setTitle("poly game 规则说明");
        //modality要使用Modality.APPLICATION_MODEL
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(300);

        Button button = new Button("返回");
        button.setOnAction(e -> window.close());

        Label label_title = new Label(str_title);
        label_title.setAlignment(Pos.CENTER_RIGHT);
        Label label_line1 = new Label(str_line_1);
        label_line1.setAlignment(Pos.CENTER_RIGHT);
        Label label_line2 = new Label(str_line_2);
        label_line2.setAlignment(Pos.CENTER_RIGHT);
        Label label_line3 = new Label(str_line_3);
        label_line3.setAlignment(Pos.CENTER_RIGHT);
        Label label_line4 = new Label(str_line_4);
        label_line4.setAlignment(Pos.CENTER_RIGHT);
        Label label_line5 = new Label(str_line_5);
        label_line5.setAlignment(Pos.CENTER_RIGHT);

        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.BOTTOM_RIGHT);
        vBox.getChildren().addAll(label_title, label_line1, label_line2, label_line3, label_line3, label_line5, button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        window.showAndWait();
    }
}
