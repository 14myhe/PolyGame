package view.littleWin;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by LC on 2017/5/12.
 */
public class About_us_win {
    String str_leader = "小组组长：何铭宜";
    String str_menber_1 = "小组成员：刘彩君";
    String str_menber_2 = "           周  丹";
    String str_menber_3 = "           林鹏珊";
    String str_menber_4 = "           张敏华";
    String str_menber_5 = "           卢  程";


    public void display(){
        Stage window = new Stage();
        window.setTitle("poly game 关于我们");
        //modality要使用Modality.APPLICATION_MODEL
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(300);

        Button button = new Button("返回");
        button.setOnAction(e -> window.close());

        Label label_leader = new Label(str_leader);
        label_leader.setAlignment(Pos.CENTER_RIGHT);
        Label label_member1 = new Label(str_menber_1);
        label_member1.setAlignment(Pos.CENTER_RIGHT);
        Label label_member2 = new Label(str_menber_2);
        label_member2.setAlignment(Pos.CENTER_RIGHT);
        Label label_member3 = new Label(str_menber_3);
        label_member3.setAlignment(Pos.CENTER_RIGHT);
        Label label_member4 = new Label(str_menber_4);
        label_member4.setAlignment(Pos.CENTER_RIGHT);
        Label label_member5 = new Label(str_menber_5);
        label_member5.setAlignment(Pos.CENTER_RIGHT);

        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.BOTTOM_RIGHT);
        vBox.getChildren().addAll(label_leader, label_member1, label_member2, label_member3, label_member4, label_member5, button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        window.showAndWait();
    }
}
