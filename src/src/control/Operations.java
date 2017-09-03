package control;

import javafx.scene.effect.Light;
import javafx.scene.shape.Circle;

/**
 * Created by LC on 2017/5/13.
 */
public class Operations {

    /**
     * 点击画布后，重新画的多边形的点
     * @param op
     * @return
     */
    public static Circle[] operateCircle (int op){
        return PolygonPointsMaker.maker(op);
    }


    public static long[] operateNums (long[] numCopys, int op){
        long[] nums = new long[numCopys.length-1];
        int j = 0;
        for (int i = 0; i<numCopys.length; i++) {
            if (i!=op) {
                nums[j] = numCopys[i];
                j++;
            }
        }
        return nums;
    }

    public static char[] operateOperator (char[] operatorCopys, int op){
        char[] operators = new char[operatorCopys.length-1];
        int j = 0;
        for (int i = 0; i<operatorCopys.length; i++) {
            if (i!=op) {
                operators[j] = operatorCopys[i];
                j++;
            }
        }
        return operators;
    }

}
