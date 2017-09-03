package control;

/**
 * Created by LC on 2017/5/13.
 */
public class Detector {


    public static boolean detect(String data) {

        if (data.charAt(0) == '*' || data.charAt(0) == '+'){
            return false;
        }
        else if (data.charAt(data.length()-1) != '*' || data.charAt(data.length()-1) != '+') {
            return false;
        }
        for (int i = 0; i<data.length()-1; i++) {
            if ((data.charAt(i) == '*' || data.charAt(i) =='+') && (data.charAt(i+1) == '*' || data.charAt(i+1) =='+')){
                return  false;
            }
        }
        return true;
    }
}
