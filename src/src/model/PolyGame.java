package model;
import java.io.*;
import java.util.*;

public class PolyGame {

	private int n=0;  //��ǰ��N
	private int currenN=0;   //��ǰ��N
	private int mode=0;  //ģʽ
	private List historyList=new LinkedList();  //��ʷ��¼
	private String gameList="";  //��ǰ���б�
	
	public PolyGame(){}
	
	public void setMode(int m){
		this.mode=m;
		if(this.mode==0){
                    this.n=8;
                    this.currenN=8;
                    initData();
                }else if (this.mode==1){
                     this.n=15;
                     this.currenN=15;
                     initData();
                }else{
                     this.currenN=25;
                     this.n=25;
                     initData();
                }
			
	}
        public void initData(){
            int end=101;
            this.historyList.clear();
            this.gameList="";
            for (int i=0;i<this.n;i++){
                Random ran=new Random();
                //int num=ran.nextInt(end-begin+1)%(begin-end+1)+begin;
                int num=ran.nextInt(end);
                int oper=ran.nextInt(2);
                if(oper==0){
                    this.gameList=this.gameList+num+'+';
                }
                else{
                    this.gameList=this.gameList+num+'*';		
                    System.out.println(num+" "+oper);
                }
                this.historyList.add(this.gameList);
        }
       }
	
	
	
	//����Զ������Ϸ������
	public void setN(int n){
		this.n=n;
		this.currenN=n;
	}
	
	//��ò������ĸ���
	public int getCurrentN(){
		return this.currenN;
	}
        public int getN(){
            return this.n;
        }
	
	//����Զ������Ϸ�б�
	public void setDataList(int n, String game){
		this.setN(n);
		this.gameList=game;
		this.historyList.add(game);		
	}
	
	
	
	//�����Ϸ����Ϸ�б�(���ϵͳ���ģʽ)
	public String getDataList(){
		int begin=-100,end=101;
		if(this.gameList==""){  //�����ǰ���б�Ϊ��
			for (int i=0;i<this.n;i++){
				Random ran=new Random();
				//int num=ran.nextInt(end-begin+1)%(begin-end+1)+begin;
				int num=ran.nextInt(end);
				int oper=ran.nextInt(2);
				if(oper==0){
					this.gameList=this.gameList+num+'+';
				}
				else
					this.gameList=this.gameList+num+'*';		
				System.out.println(num+" "+oper);
			}
			this.historyList.add(this.gameList);	 //��ӵ���ʷ��¼��		
		}
		return this.gameList;
	}
	
	
	//���һ���ߣ�index�Ǵ�������������±꣬��0��ʼ
	
//点击一条边，index是纯操作符数组的下标，从0开始
	public void sendStepData(int index){
		int num=0;
		int begin1=0,end1=-1,i=0;
		char oper='+';
		//找到第index+1个操作符和第一个操作数
		for (i=0;i<this.gameList.length() && num<index+1;i++){
			if( this.gameList.charAt(i)=='+'){
				begin1=end1+1;
				end1=i;  //end指向的操作符符当前的下标
				oper='+';
				num++;
			}
			else if(this.gameList.charAt(i)=='*'){
				begin1=end1+1;
				end1=i;
				oper='*';
				num++;
			}
		}
		System.out.println("this.gamelist= "+this.gameList);;
		System.out.println("i= "+i);;
		System.out.println("begin1= "+begin1+" end1="+end1);
		String n1=this.gameList.substring(begin1, end1);
		System.out.println("n1= "+n1);;
		int num1=Integer.parseInt(n1);
		
		int begin2=i,end2;		
		//找到下一个操作数
		for ( ; i<this.gameList.length() ;i++){
			 if(this.gameList.charAt(i)=='*' || this.gameList.charAt(i)=='+'){
				 break;
			 }	 
		}
		end2=i;
		
		String n2=this.gameList.substring(begin2, end2);  //半开区间[begin,end)
		int num2 = Integer.parseInt(n2);
		
		//计算num1 oper num2
		int sum=0;
		if( oper=='+'){
			sum=num1+num2;
		}
		else{
			sum=num1*num2;
		}
		//截取前半部分
		String s1=this.gameList.substring(0, begin1);
		//截取后半部分
		String s2=this.gameList.substring(end2,this.gameList.length());
		//拼接成新的字符串
		String s=s1+sum+s2;
		this.gameList=s;
		this.historyList.add(s);
		this.currenN-=1;  
	}

        public void testSendStepData(){
		this.setDataList(5, "1+12+33*4+56");
		System.out.println("��ʼ�� "+this.gameList+"\n��ʷ��¼");
		for(int i=0;i<this.historyList.size();i++){
			System.out.println(this.historyList.get(i));
		}
		this.sendStepData(2);
		System.out.println("��һ�Σ� "+this.gameList+"\n��ʷ��¼");
		for(int i=0;i<this.historyList.size();i++){
			System.out.println(this.historyList.get(i));
		}
		this.sendStepData(2);
		System.out.println("�ڶ��Σ� "+this.gameList+"\n��ʷ��¼");
		for(int i=0;i<this.historyList.size();i++){
			System.out.println(this.historyList.get(i));
		}
		this.back();
		System.out.println("���ˣ� "+this.gameList+"\n��ʷ��¼");
		for(int i=0;i<this.historyList.size();i++){
			System.out.println(this.historyList.get(i));
		}
	}
	
	//����
	public String back(){
		int len=this.historyList.size();
		if(len>0){  
			this.historyList.remove(len-1);
			int index=this.historyList.size();			
			if( index>0){
				this.gameList=(String)this.historyList.get(index-1);
				this.currenN=(this.gameList.length())/2+1;  //有错
                                
			}
			
		}	
		else
			System.out.println("�Ѿ��˵���ʼ״̬�ˣ�");
		return this.gameList;
	}
	
	public void testback(){
		this.setMode(0);
		this.historyList.add("1+2*4+3+5");
		this.historyList.add("1+2*4+8");
		this.historyList.add("1+8+8");
		this.historyList.add("9+8");
		this.gameList="9+8";
		for (int j=0;j<3;j++){
			String str=this.getDataList();
			System.out.println("��ǰ��¼��"+str);
			System.out.println("��ʷ��¼��");
			for (int i=0;i<this.historyList.size();i++){
				System.out.println(this.historyList.get(i));
			}
			this.back();
		}
		
		
	}
	
	//������Ϸ
	public String replay(){
		if(this.gameList!=""){  //�����ǰ����Ϸ��Ϊ��
			this.gameList=(String)this.historyList.get(0);
			this.historyList.clear();
			this.historyList.add(this.gameList);
			this.currenN=this.n;
		}
		
		return this.gameList;
	}
	public void testReplay(){
		this.setMode(2);
		this.getDataList();
		this.historyList.add("hfaehfoaie");
		System.out.println(this.gameList);
		for (int i=0;i<this.historyList.size();i++){
			System.out.println(this.historyList.get(i));
					
		}
		System.out.println("�����");
		this.replay();
		System.out.println(this.gameList);
		for (int i=0;i<this.historyList.size();i++){
			System.out.println(this.historyList.get(i));
					
		}
	}
	
	//������Ϸ
	
//结束游戏
	public String endGame(){
		//若有得分时保存,然后将所有数据清零
		String grades="0";
		if( isNumeric(this.gameList)){
			String strGame=(String)this.historyList.get(0);
			grades=this.gameList;
			writeToFile(grades,strGame);
			this.n=0;
			this.mode=0;
			this.currenN=0;
			this.gameList="";
			this.historyList.clear();
		}
		
		return grades;
	}
	
	

	public void testEndGame(){
		this.setMode(0);
		this.historyList.add("1+2*4+3+5");
		this.historyList.add("8");
		this.historyList.add("1+8+8");
		this.historyList.add("9+8");
		this.gameList="9+8";
		for (int j=0;j<2;j++){
			this.back();
			String str=this.getDataList();
			System.out.println("��ǰ��¼��"+str);
			System.out.println("��ʷ��¼��");
			for (int i=0;i<this.historyList.size();i++){
				System.out.println(this.historyList.get(i));
			}
			
		}
		this.endGame();
	}
	
	//�ж��ַ����Ƿ�������
	public boolean isNumeric(String str){
		for( int i=0;i<str.length();i++){
			//����ַ��������ֵĻ�������
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	//����������Ϸģʽд���ļ���
//将分数和游戏模式写入文件中
	public void writeToFile(String grades,String str){
		try{
			File file=new File("data.txt");
			if ( !file.exists() ){
				file.createNewFile();
			}
			FileWriter fw=new FileWriter(file.getName(),true);
			BufferedWriter bw=new BufferedWriter(fw);
			String s="";
			s=s+grades+' '+str+'\n';
			bw.write(s);
			bw.close();
			System.out.println("done");			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	//#ɾ����һ�߲�����������
	public String deleteEdge(int n){
		if( this.gameList!=""){
			int num=0;
			int index=0;
			for (index=0;index<this.gameList.length() && num<n+1;index++){
				if(this.gameList.charAt(index)=='+'){
					num++;
				}
				else if(this.gameList.charAt(index)=='*'){
					num++;
				}
			}
			index-=1;  //��ǰŲһ���ַ�
			//System.out.println("index="+index);
			//��ȡ����
			String s1=this.gameList.substring(index+1, this.gameList.length());
			//��ȡǰ���
			String s2=this.gameList.substring(0,index);
			String s=s1+s2;
			this.gameList=s;
			this.historyList.clear();
			this.historyList.add(this.gameList);
			
		}
                return this.gameList;
	}
	
	public void testDeleteEdge(){
		this.gameList="fho+aeh*fi+aefap+";
		this.deleteEdge(3);
		System.out.println(this.gameList);
		for (int i=0;i<this.historyList.size();i++){
			System.out.println(this.historyList.get(i));
		}
		
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PolyGame pg=new PolyGame();
		//pg.testRandom();
		//pg.testEndGame();
		pg.testSendStepData();

	}

}
