package view;

import control.Operations;
import javafx.event.EventHandler;
import control.Detector;
import control.LabelHandler;
import control.PolygonPointsMaker;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GameData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.MyPolyPane;
import view.littleWin.About_us_win;
import view.littleWin.EndGame_win;
import view.littleWin.Rules_win;

import javax.swing.*;
import model.PolyGame;

public class Main extends Application implements MouseListener{
    private GameData gameData = new GameData();
    private long theScore = 0;
    MyPolyPane myPolyPane;
    int count=0;
    public static int step=0;
    public static String[]  polyBestAllStep;
    public long[] startNums;//最先开始的多边形的操作数
    public char[] startOperater;//最先开始的多边形的运算符
    public String startString;//最先开始表示多边形的字符串
    public PolyGame polyGame=new PolyGame();
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane border = new BorderPane();

        /**主页Top那块的布局*/
        HBox topHBox = new HBox();
        topHBox.setPadding(new Insets(15,12,15,12));//节点到边缘的距离
        topHBox.setSpacing(10); //节点之间的距离
        topHBox.setStyle("-fx-background-color: #336699;");//背景色

        Button btn_main_page = new Button("游戏主页");
        btn_main_page.setPrefSize(100,20);

        Button btn_score = new Button("得分记录");
        btn_score.setPrefSize(100,20);
        btn_score.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
            //Score_win score_win = new Score_win();
        });

        Button btn_rules = new Button("规则说明");
        btn_rules.setPrefSize(100,20);

        Button btn_about_us = new Button("关于我们");
        btn_about_us.setPrefSize(100,20);

        topHBox.getChildren().addAll(btn_main_page,btn_score,btn_rules,btn_about_us);
        border.setTop(topHBox);



        /**主页右侧那块的布局*/
        VBox listViewVBox = new VBox();
        listViewVBox.setPadding(new Insets(120,40,20,40));
        listViewVBox.setSpacing(10);

        Label label_steps = new Label("点击步骤对应计算列表：");
        listViewVBox.getChildren().add(label_steps);

        ListView<String> listView = new ListView<>();
        ObservableList<String> steps = FXCollections.observableArrayList();
        listView.setItems(steps);
        listView.setPrefWidth(240);
        listView.setPrefHeight(430);
        listViewVBox.getChildren().add(listView);
        border.setRight(listViewVBox);


        /**主页center那块的布局*/
        GridPane inputAndCanvas = new GridPane();
        inputAndCanvas.setPadding(new Insets(10,10,10,20));
        inputAndCanvas.setHgap(10);
        inputAndCanvas.setVgap(12);

        Label label_form = new Label("游戏方式:");
        ObservableList<String> form_options = FXCollections.observableArrayList(
                "系统随机——初级",
                "系统随机——中级",
                "系统随机——高级",
                "自定义"
        );
        ComboBox<String> comboBox_form = new ComboBox<>(form_options);
        Label label_N = new Label("多边形顶点数 N =");
        Spinner spinner_N = new Spinner(0,100,0);
        Label label_spinner_tips = new Label("→→→ tips：若是键盘输入，输入后需按 ENTER 键                             ");
        spinner_N.editableProperty().setValue(Boolean.TRUE);
        Label label_data = new Label("输入顶点值及边算符：");
        TextField textField_data = new TextField();

        Button btn_begin_game = new Button("点击我，开始游戏");
        btn_begin_game.setPrefSize(150,20);
        btn_begin_game.setStyle("-fx-background-color: #3377EE;");


        btn_begin_game.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->
                btn_begin_game.setStyle("-fx-background-color: #3377EE;"));
        btn_begin_game.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e)->
                btn_begin_game.setStyle("-fx-background-color: #336699"));


        HBox hBox_begin_game = new HBox();
        hBox_begin_game.getChildren().add(btn_begin_game);
        hBox_begin_game.setAlignment(Pos.CENTER);

        Separator separator_begin = new Separator();

        //画布
        Pane polyCanvas = new Pane();
        polyCanvas.setPrefSize(850,440);
        polyCanvas.setStyle("-fx-background-color: #D9D9D9;");


        //鼠标点击polyCanvas的响应——对textField_oprator 响应；
        polyCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        inputAndCanvas.add(label_form,0,0);
        inputAndCanvas.add(comboBox_form,1,0);
        inputAndCanvas.add(label_N,0,1);
        inputAndCanvas.add(spinner_N,1,1);
        inputAndCanvas.add(label_spinner_tips,2,1);
        inputAndCanvas.add(label_data,0,2);
        inputAndCanvas.add(textField_data,1,2,4,2);
        inputAndCanvas.add(hBox_begin_game,0,3,4,3);
        inputAndCanvas.add(separator_begin,0,4,4,4);
        inputAndCanvas.add(polyCanvas,0,6,4,6); //占第5行第1列 直至 第5行第3列

        border.setCenter(inputAndCanvas);


        /**主页bottom那块的布局*/
        HBox bottomHBox = new HBox();
        bottomHBox.setPadding(new Insets(16));//节点到边缘的距离
        bottomHBox.setSpacing(10);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.setStyle("-fx-border-color: #336699;");

        Button btn_back = new Button("回退一步");
        btn_back.setPrefSize(100,20);
        btn_back.setStyle("-fx-border-color: #336699;");

        Button btn_replay = new Button("重做");
        btn_replay.setPrefSize(100,20);
        btn_replay.setStyle("-fx-border-color: #336699;");

        Button btn_end_game = new Button("结束游戏");
        btn_end_game.setPrefSize(100,20);
        btn_end_game.setStyle("-fx-border-color: #336699;");

        Button btn_best_play = new Button("最高分方案");
        btn_best_play.setPrefSize(100,20);
        btn_best_play.setStyle("-fx-border-color: #336699;");

        bottomHBox.getChildren().addAll(btn_back,btn_replay,btn_end_game,btn_best_play);
        border.setBottom(bottomHBox);



        /**以下：
         *
         *
         * 开始游戏响应，另外文件放代码使得反应速度很慢。因此改回来
         *
         *
         *
         */ 
        btn_begin_game.setOnAction((ActionEvent event) -> {
            switch (comboBox_form.getSelectionModel().getSelectedIndex()) {
                case -1:
                    JOptionPane.showMessageDialog(null, "您还未填入“游戏方式”，请填写", "注意", JOptionPane.ERROR_MESSAGE);
                    break;
                case 0:
                    gameData.setGameForm(0);
                    polyGame.setMode(0);
                    System.out.println("polygame result : "+polyGame.getN());
                    System.out.println("polygame result string: "+polyGame.getDataList());
                    gameData.setN(polyGame.getN());
                    gameData.setData(polyGame.getDataList());
                    break;
                case 1:
                    gameData.setGameForm(1);
                    polyGame.setMode(1);
                    System.out.println("polygame result : "+polyGame.getN());
                    System.out.println("polygame result string: "+polyGame.getDataList());
                    gameData.setN(polyGame.getN());
                    gameData.setData(polyGame.getDataList());
                    break;
                case 2:
                    gameData.setGameForm(2);
                    polyGame.setMode(2);
                    System.out.println("polygame result : "+polyGame.getN());
                    System.out.println("polygame result string: "+polyGame.getDataList());
                    gameData.setN(polyGame.getN());
                    gameData.setData(polyGame.getDataList());
                    break;
                case 3:
                    if (Integer.parseInt(spinner_N.getValue().toString()) == 0){
                        JOptionPane.showMessageDialog(null,"“多边形顶点数不能为零，请填写", "注意", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (textField_data.getText() == null){
                        JOptionPane.showMessageDialog(null,"多边形数据未填写，请填写", "注意", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (Detector.detect(textField_data.getText())) {
                        JOptionPane.showMessageDialog(null,"多边形数据填写不正确，请重新填写", "注意", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        gameData.setGameForm(3);
                        polyGame.setMode(3);
                        int n=Integer.parseInt(spinner_N.getValue().toString());
                        String user_data=textField_data.getText();
                        polyGame.setDataList(n, user_data);//把用户定义的数据传给后台
                        gameData.setN(n);
                        gameData.setData(user_data);
                    }
                    break;
            }
            //保存原始模型
            startString=gameData.getData();
            startNums=gameData.getNums();
            startOperater=gameData.getOperators();
            //重置画布
            polyCanvas.getChildren().clear();
            
            //开始画图
            myPolyPane = new MyPolyPane(gameData.getN(), gameData.getNums(), gameData.getOperators() );
            myPolyPane.drawPolygon(polyCanvas);  //如果是不闭合的画图， 可以按照 此样式，调用myPoly.drawPolyLine(polyCanvas);
           // double canvas_X,canvas_Y;//鼠标点击画布后获得的坐标
           //double x,y;//邻近的文本框的坐标
           //Test_S_N test=new Test_S_N();//创建一个测试类
           
           steps.clear();//步骤列表清空
           step=0;//最高分方案初始化
           polyCanvas.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                     double canvas_X=me.getX();//鼠标点击画布后获得的坐标
                     double canvas_Y=me.getY();
                     //System.out.println("画布横坐标"+canvas_X+"画布纵坐标"+canvas_Y);
                     double x,y;//邻近的文本框的坐标
                     System.out.println("文本框的运算符的个数"+myPolyPane.labelOpraters.length);
                     
                     for(int i=0;i<myPolyPane.labelOpraters.length;i++){  
                         
                         x=myPolyPane.labelOpraters[i].getLayoutX();
                         y=myPolyPane.labelOpraters[i].getLayoutY();  
                         //System.out.println("第"+i+"个文本框"+"文本框横坐标"+x+"文本框纵坐标"+y);
                         if(((canvas_X-x)*(canvas_X-x)+(canvas_Y-y)*(canvas_Y-y))<=700){
                            int test_num=0;
                             
                             //System.out.println(i);
                             //String test_str=test.createStr(i);
                             //int test_num=polyGame.getCurrentN();
                             //System.out.println("str: "+test_str+" 顶点数"+ test_num);
                             //if(startNums.length==polyGame.getDataList().length()){
                             if(gameData.getData().length()==startString.length()){
                                 String test_str=polyGame.deleteEdge(i); //删除第一条边后台相对应的处理
                                 gameData.setData(test_str);
                                 myPolyPane = new MyPolyPane(startNums.length,startNums , startOperater );
                                 polyCanvas.getChildren().clear();//清空画布
                                 myPolyPane.drawPolyLine(polyCanvas,myPolyPane.points); 
                                 
                             }else{
                                 //增加运算步骤
                                 String op=myPolyPane.labelOpraters[i].getText();
                                 String n1=myPolyPane.labelNums[i].getText();
                                 String n2=myPolyPane.labelNums[i+1].getText();
                                 int n3;
                                 if(op=="+"){
                                     n3=Integer.parseInt(n1)+Integer.parseInt(n2);
                                 }else{
                                     n3=Integer.parseInt(n1)*Integer.parseInt(n2);
                                 }                                
                                 steps.add(n1+op+n2+"="+n3);
                                 polyGame.sendStepData(i);
                                 String test_str=polyGame.getDataList();
                                 test_num=polyGame.getCurrentN();
                                 gameData.setN(test_num);
                                 gameData.setData(test_str);
                                 myPolyPane = new MyPolyPane(gameData.getN(), gameData.getNums(), gameData.getOperators() );
                                 polyCanvas.getChildren().clear();//清空画布
                                 myPolyPane.drawPolyLine(polyCanvas,myPolyPane.points);//重画多边形
                                 
                             }
                         }
                     }
                 }
             });
                 
            
            myPolyPane.labelOpraters[1].getLayoutX();
            
            
            
            
            
            
            /*
            label_form.setVisible(false);
            comboBox_form.setVisible(false);
            label_N.setVisible(false);
            spinner_N.setVisible(false);
            label_data.setVisible(false);
            textField_data.setVisible(false);
            label_spinner_tips.setVisible(false);
            btn_begin_game.setVisible(false);*/

            //测试:                         最后将下面这一行注释掉
            JOptionPane.showMessageDialog(null, gameData.getGameForm()+"\n"+gameData.getN()+"\n"+gameData.getData()+"\n", "测试一下", JOptionPane.INFORMATION_MESSAGE);

        });

        comboBox_form.setOnAction(event -> {
            if (comboBox_form.getSelectionModel().getSelectedIndex() != 3) {
                label_N.setVisible(false);
                spinner_N.setVisible(false);
                label_data.setVisible(false);
                textField_data.setVisible(false);
                label_spinner_tips.setVisible(false);
            } else {
                label_N.setVisible(true);
                spinner_N.setVisible(true);
                label_data.setVisible(true);
                textField_data.setVisible(true);
                label_spinner_tips.setVisible(true);
            }
        });
        //关于我们的按钮响应
        btn_about_us.setOnAction(event -> {
            About_us_win about_us_win = new About_us_win();
            about_us_win.display();
        });  
        
        //游戏规则的按钮响应
        btn_rules.setOnAction(event -> {
            Rules_win rules_win = new Rules_win();
            rules_win.display();
        });
       //结束游戏的按钮响应  
        btn_end_game.setOnAction(event -> {
            String result=polyGame.endGame();//得到总分
            System.out.println("result是"+result);
            EndGame_win endGame_win = new EndGame_win(result); //
            //EndGame_win endGame_win = new EndGame_win(theScore);
            endGame_win.display();
            polyCanvas.getChildren().clear();
            polyCanvas.setPrefSize(850,440);
            polyCanvas.setStyle("-fx-background-color: #D9D9D9;");
            label_form.setVisible(true);
            comboBox_form.setVisible(true);
            label_N.setVisible(true);
            spinner_N.setVisible(true);
            label_data.setVisible(true);
            textField_data.setVisible(true);
            label_spinner_tips.setVisible(true);
            btn_begin_game.setVisible(true);
        });

        btn_replay.setOnAction(event -> {
            polyCanvas.getChildren().clear();
            polyCanvas.setPrefSize(850,440);
            polyCanvas.setStyle("-fx-background-color: #D9D9D9;");

            label_form.setVisible(true);
            comboBox_form.setVisible(true);
            label_N.setVisible(true);
            spinner_N.setVisible(true);
            label_data.setVisible(true);
            textField_data.setVisible(true);
            label_spinner_tips.setVisible(true);
            btn_begin_game.setVisible(true);
        });
        
        //回退一步的按钮响应
        Test_S_N test0=new Test_S_N();
        btn_back.setOnAction(event -> {
            if(gameData.getData().length()==startString.length()-1){
                System.out.println("回到第一个的多边形"+startString);
                myPolyPane =new MyPolyPane(startNums.length, startNums,  startOperater);
                polyCanvas.getChildren().clear();//清空画布
                myPolyPane.drawPolygon(polyCanvas);
            }else{
                 System.out.println("事件响应了,上一步的多边形"+polyGame.back());
                 String lastStepData=polyGame.back();
                //System.out.println("当前的顶点数"+polyGame.getCurrentN());
                 gameData.setN(test0.getNum(lastStepData));
                 gameData.setData(lastStepData);
                 myPolyPane = new MyPolyPane(gameData.getN(), gameData.getNums(), gameData.getOperators() );
                 polyCanvas.getChildren().clear();//清空画布
                 myPolyPane.drawPolyLine(polyCanvas,myPolyPane.points);//重画多边形
                 
                
            }
                          
        });  
        
        //最高分方案 按钮响应
       Test_S_N test=new Test_S_N();
      
       btn_best_play.setOnAction((ActionEvent event) -> {
           label_steps.setText("最高分方案列表");
           
           PloyBest ployBest=new PloyBest();
           if(step==0){
               steps.clear();
               steps.add(startString);
               polyBestAllStep=ployBest.ploy_best(startNums, startOperater);
               myPolyPane =new MyPolyPane(startNums.length, startNums,  startOperater);
           }
           String strOneStep;//一次去边操作后新的字符串
           int numOneStep;
           System.out.println("step="+step+"  length="+polyBestAllStep.length);
           strOneStep = polyBestAllStep[step];
           numOneStep = test.getNum(strOneStep);
           System.out.println(strOneStep + "  顶点数目:" + numOneStep);
           steps.add(strOneStep);
           gameData.setN(numOneStep);
           gameData.setData(strOneStep);
           myPolyPane = new MyPolyPane(gameData.getN(), gameData.getNums(), gameData.getOperators());
           polyCanvas.getChildren().clear();//清空画布
           myPolyPane.drawPolyLine(polyCanvas, myPolyPane.points);//重画多边形
           step=(step+1)%polyBestAllStep.length;
          }
       );


        //获取鼠标点击的坐标
        polyCanvas.setOnMouseClicked(event -> {
            myPolyPane.judge(event.getX(), event.getY());
        });

        //窗口
        primaryStage.setTitle("poly game 游戏主页");
        primaryStage.setScene(new Scene(border, 1200, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


 