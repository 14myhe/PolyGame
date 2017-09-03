package model;


import control.Distanse;
import control.Operations;
import control.PolygonPointsMaker;
import java.util.TimerTask;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;

/**
 * Created by LC on 2017/5/13.
 */
public class MyPolyPane {

    public Circle[] points;
    public Label[] labelNums;
    public Label[] labelOpraters;
    long[] nums;
    char[] strOpraters;
    
    int n = 0;


    public MyPolyPane(int n, long[] nums, char[] strOpraters) {
        this.n = n;
        this.nums = nums;
        this.strOpraters = strOpraters;


        points = PolygonPointsMaker.maker(n);
        //避免 对象数组 空指针异常
        labelNums = new Label[n];
        for (int i = 0; i < points.length; i++) {
            Label l = new Label("0");
            labelNums[i] = l;
        }
        labelOpraters = new Label[n];
        for (int i = 0; i < points.length; i++) {
            Label la = new Label("+");
            labelOpraters[i] = la;
        }



       for (int i = 0; i < points.length; i++) {
        // for (int i = 0; i < n; i++) {
       //求 labelNums 的坐标————与points一样
            labelNums[i].setLayoutX(points[i].getCenterX() + 3);
            labelNums[i].setLayoutY(points[i].getCenterY() + 3);
            labelNums[i].setText(String.valueOf(nums[i]));
            labelNums[i].setFont(new Font("Arial", 19));
        }
    }


    /**
     * //画多边形（闭合）
     * @param canvas
     * @return
     */
    public Pane drawPolygon(Pane canvas) {

        for (int i = 0; i < points.length; i++) {
            points[i].setFill(Color.valueOf("#FFFF00"));
            canvas.getChildren().add(points[i]);
            canvas.getChildren().add(labelNums[i]);

            //求 labelOperator 的坐标 ————画多边形（闭合）
            if (i == (points.length - 1)) {
                labelOpraters[i].setLayoutX((points[i].getCenterX() + points[0].getCenterX()) / 2);
                labelOpraters[i].setLayoutY((points[i].getCenterY() + points[0].getCenterY()) / 2);
            } else { 
                labelOpraters[i].setLayoutX((points[i].getCenterX() + points[i + 1].getCenterX()) / 2);
                labelOpraters[i].setLayoutY((points[i].getCenterY() + points[i + 1].getCenterY()) / 2);
            }
            //改变labelOpraters[i]的值
            labelOpraters[i].setText(String.valueOf(strOpraters[i]));
            labelOpraters[i].setFont(new Font("Arial", 19));

            canvas.getChildren().add(labelOpraters[i]);
        }

        Path pointPath = new Path();
        pointPath.setStroke(Color.valueOf("#336699")); 
        pointPath.setStrokeWidth(4);

        pointPath.getElements().add(new MoveTo(points[0].getCenterX(), (points[0].getCenterY())));

        for (int i = 0; i < points.length; i++) {
            pointPath.getElements().add(new LineTo(points[i].getCenterX(), points[i].getCenterY()));
        }
        pointPath.getElements().add(new LineTo(points[0].getCenterX(), (points[0].getCenterY())));
        pointPath.setOpacity(0.7);
        canvas.getChildren().add(pointPath);

        return canvas;
    }

    /**
     * //画折线（不闭合）
     * @param canvas
     * @return
     */
    public Pane drawPolyLine(Pane canvas, Circle[] pointsTmp) {

        for (int i = 0; i < pointsTmp.length; i++) {
            pointsTmp[i].setFill(Color.valueOf("#FFFF00"));
            canvas.getChildren().add(pointsTmp[i]);
            canvas.getChildren().add(labelNums[i]);
            
            //求 labelOperator 的坐标 ————画多边形（闭合）
            if (i != (pointsTmp.length - 1)) {
                labelOpraters[i].setLayoutX((pointsTmp[i].getCenterX() + pointsTmp[i + 1].getCenterX()) / 2);
                labelOpraters[i].setLayoutY((pointsTmp[i].getCenterY() + pointsTmp[i + 1].getCenterY()) / 2);
                
                //改变labelOpraters[i]的值
                labelOpraters[i].setText(String.valueOf(strOpraters[i]));

                canvas.getChildren().add(labelOpraters[i]);
            }

        }
        Path pointPath = new Path();
        pointPath.setStroke(Color.valueOf("#336699"));
        pointPath.setStrokeWidth(4);

        pointPath.getElements().add(new MoveTo(pointsTmp[1].getCenterX(), (pointsTmp[1].getCenterY())));

        for (int i = 0; i < pointsTmp.length; i++) {
            pointPath.getElements().add(new LineTo(pointsTmp[i].getCenterX(), pointsTmp[i].getCenterY()));
        }
        canvas.getChildren().add(pointPath);
        return canvas;
    }
    
    //消除第一条边画折线图的函数
    public Pane drawPolyLineOne(Pane canvas, Circle[] pointsTmp) {

        for (int i = 0; i < pointsTmp.length; i++) {
            pointsTmp[i].setFill(Color.valueOf("#FFFF00"));
            canvas.getChildren().add(pointsTmp[i]);
            canvas.getChildren().add(labelNums[i]);

            //求 labelOperator 的坐标 ————画多边形（闭合）
            if (i != (pointsTmp.length - 1)) {
                labelOpraters[i].setLayoutX((pointsTmp[i].getCenterX() + pointsTmp[i + 1].getCenterX()) / 2);
                labelOpraters[i].setLayoutY((pointsTmp[i].getCenterY() + pointsTmp[i + 1].getCenterY()) / 2);

                //改变labelOpraters[i]的值
                labelOpraters[i].setText(String.valueOf(strOpraters[i]));

                canvas.getChildren().add(labelOpraters[i]);
            }

        }
        Path pointPath = new Path();
        pointPath.setStroke(Color.valueOf("#336699"));
        pointPath.setStrokeWidth(4);

        pointPath.getElements().add(new MoveTo(pointsTmp[1].getCenterX(), (pointsTmp[1].getCenterY())));

        for (int i = 0; i < pointsTmp.length; i++) {
            pointPath.getElements().add(new LineTo(pointsTmp[i].getCenterX(), pointsTmp[i].getCenterY()));
        }
        canvas.getChildren().add(pointPath);
        return canvas;
    }

    
    public void judge(double x, double y){
        int op = -1;
        for (int i = 0; i < labelOpraters.length; i++) {
            if (Distanse.distance(labelOpraters[i].getLayoutX(),labelOpraters[i].getLayoutY(),x,y) < 12){
                op = i;
                break;
            }
        }

        if (op != -1){
            Operations.operateCircle(op);
        }
    }




}