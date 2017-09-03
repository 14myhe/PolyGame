package control;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by LC on 2017/5/13.
 */
public class PolygonPointsMaker {

    public static Circle[] maker(int n){
        Circle[] points = new Circle[n];
        double arc;        
        final int point_r = 18;
        final double beginX = 425.0;
        final double beginY = 30.0;
        final int r = 180;
        for (int i = 0; i < points.length; i++) {
            if (i == 0) {
                Circle c = new Circle(beginX, beginY, point_r);
                c.setFill(Color.valueOf("#FFFF00"));
                points[i] = c;
            } else {
                Circle c = new Circle(0.0, 0.0, point_r);
                points[i] = c;
            }
        }
        for (int i = 1; i < points.length; i++) {
            arc = ((2 * Math.PI) / n) * i;
            points[i].setCenterX(points[0].getCenterX() - r * sin(arc));
            points[i].setCenterY(points[0].getCenterY() + r - r * cos(arc));
        }

        return points;
    }
}
