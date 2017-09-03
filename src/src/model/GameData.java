package model;

/**
 * Created by LC on 2017/5/12.
 */
public class GameData {

    public int gameForm = -1;  //游戏方式， -1：未选   0：系统随机初级   1：系统随机中级    2：系统随机高级    3：自定义
    public int N = 0;          //多边形顶点数，初始化为0，表示未选
    public String data;        // 存数据： eg：‘1+2*3+4*5+6*7’  -----------------------刚获取未处理的数据，待处理转换

    public long[] nums;
    public char[] operators;

    public int getGameForm() {
        return gameForm;
    }

    public void setGameForm(int gameForm) {
        this.gameForm = gameForm;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        this.N = n;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        dealData();
    }

    public long[] getNums() {
        return nums;
    }

    public void setNums(long[] nums) {
        this.nums = nums;
    }

    public char[] getOperators() {
        return operators;
    }

    public void setOperators(char[] operators) {
        this.operators = operators;
    }

    public GameData(int gameForm, int N, String data){
        this.gameForm = gameForm;
        this.N = N;
        this.data = data;
    }

    public GameData(){

    }

    /**
     *处理数据，将 String data 转化成数字数组 和 符号数组
     */
    public void dealData(){
        //来到这里的data都是经过Detector检测过的，一定合法
        int mark=0;  //符标
        int p=0;  //num[]的位置记录
        int q=0;  //char[]的位置记录

        nums = new long[this.getN()];
        operators = new char[this.getN()];//这里有错，只考虑了12+12+12+这种情况，没有考虑12+12+12这种清空

        for (int i = 0; i<data.length()-1 ; i++) {
            if((i==data.length()-2)&&data.charAt(i+1)!='*'&&data.charAt(i+1)!='+'){
         
                nums[p] = Integer.parseInt(String.valueOf(data.substring(mark,data.length())));
                System.out.println("yeyyeyeyeyyyyyyyyyyye"+nums[p]);
            }
            if (data.charAt(i+1) == '*' || data.charAt(i+1) == '+'){
                operators[q] = data.charAt(i+1);
                if (mark==i){
                    nums[p] = Integer.parseInt(String.valueOf(data.charAt(i)));
                }
                else {
                    nums[p] = Integer.parseInt(String.valueOf(data.substring(mark,i+1)));
                    System.out.println(nums[p]);
                }
                q++;
                p++;
                mark = i+2;
            }
        }
    }
}
