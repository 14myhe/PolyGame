package control;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import static java.lang.Math.sqrt;

/**
 * Created by LC on 2017/5/13.
 */
public class Distanse {

    public static double distance(double x1, double y1, double x2, double y2) {
        return sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }


}
