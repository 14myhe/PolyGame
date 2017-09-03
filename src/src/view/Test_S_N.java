/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author zhangminhua
 */
public class Test_S_N {
    public Test_S_N(){} 
    public String createStr(int i){
        String str;
        switch(i){
            case 0:str="2+3+4+5+1";  break;
            case 1:str="2+7+5+1"; break;
            case 2:str="2+7+6"; break;
            default : str="00"; break;
        }
        return str;
    }
    public int getNum(String s){
        int num=1;
        for(int i=0;i<s.length();i++){
            if((s.charAt(i)=='+')||(s.charAt(i)=='*')){
                num++;
            }
            if((i==s.length()-1)&&((s.charAt(i)=='+')||(s.charAt(i)=='*'))){
                //num=num-1;
            }
        }
        return num;
    }
    
    
}
