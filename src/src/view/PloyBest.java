package view;

import model.*;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.naming.InitialContext;

public class PloyBest {
	/**
	 * @author Cj 2017.05.12
	 */
	public long[] v;
	public char[] op;
	public int n;
	public int[] step;
	public int[][] process;
	public String[] every_step;
	public int min = -999999;
	public int count;

	public void initial(long[] point, char[] operate) {
		this.v = point;
		this.op = operate;
		this.n = point.length;
		this.count = n;
		this.process = new int[n + 1][n + 1];
		this.step = new int[n];
		this.every_step = new String[n];
	}

	public long m(int a, int b) {
		if (b == 1)
			return v[a];
		int max = min,round_max = min,round_length = min, temp = min;

		for (int i = 1; i < b; i++) {
			if (op[(a + i - 1) % n] == '+')
				temp = (int)m(a % n, i) +(int) m((a + i) % n, b - i);
			else if (op[(a + i - 1) % n] == '*')
				temp = (int)m(a % n, i) * (int)m((a + i) % n, b - i);
			if (temp > round_max) {
				round_max = temp;
				round_length = i;
			}
			if (max < temp) {
				int tmp = max;
				max = temp;
				temp = tmp;
			}
		}
		process[a][b] = round_length;
		return max;
	}

	public void find_step(int a, int b) {
		int index = process[a][b];
		if (((a + index) % n) == 0)
			step[--count] = n;
		else {
			step[--count] = (a + index) % n;
		}
		if (index >= 2)
			find_step(a, index);
		if (b - (index) >= 2)
			find_step((a + index) % n, b - (index));
	}

	public String find_first(int index) {
		StringBuilder stringBuilder = new StringBuilder("");
		for(int i=0;i<n;i++){
			int k=(i+index)%n;
			stringBuilder.append(v[k]);
			if(i<n-1) stringBuilder.append(op[k]);
		}
		return stringBuilder.toString();
	}
	
	public String find_string(int now,int index) {
		long tmp=0;
		if(op[now]=='+')
			tmp=v[now]+v[(now+1)%n];
		else tmp=v[now]*v[(now+1)%n];
		
		v[now]=tmp;
		v[(now+1)%n]=tmp;

		int left=(now+n-1)%n;
		int right=(now+1)%n;
		while(op[(left+n)%n]=='?'){
			v[(left+n)%n]=tmp;
			left--;
			if(op[(left+n)%n]!='?') break;
		}
		while(op[right%n]=='?'){
			v[(right+1)%n]=tmp;
			right++;
			if(op[right%n]!='?') break;
		}
		op[now]='?';
		StringBuilder stringBuilder = new StringBuilder("");
		for(int i=0;i<n;i++){
			int k=(i+index)%n;
			if(op[(i+n+index-1)%n]!='?') stringBuilder.append(v[k]);
			if(i<n-1&&op[(i+index)%n]!='?') stringBuilder.append(op[k]);
		}
		return stringBuilder.toString();
	}
	
	
	//ֱ�ӵ�����������������������ùܣ��������Ž⣬���Ž���һ��string���飬��Ҫ�����������飺�������int[],����������char[]
	//����һ����̬����������Ҫ���������ã�ֱ�ӵ��þͿ���
	//������䣺PloyBest.ploy_best(point, operate);�Ϳ���
	//���ص���string���飬����point�Ǵ�������飬operate�Ǵ������������
	public String[] ploy_best(long[] point, char[] operate) {
		initial(point, operate);
		long result = min,temp = min;
                int index = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= n; j++) {
				temp = (m(i, j));
			}
			if (temp > result) {
				result = temp;
				index = i;
			}
		}
		find_step(index, n);
		if (index == 0)
			step[--count] = n;
		else
			step[--count] = index;
		
		every_step[0]=find_first(step[0]);
		
		for(int i=1;i<n;i++){
			every_step[i]=find_string(step[i]-1,step[0]);
		}
		return every_step;
	}
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    //	int point[] = new int[] { 4, -7, -2, 5, 3, -4, 9, -5, 10, -10, -5, -6,-3 };
		//char operate[] = new char[] { '*', '*', '+', '+', '+', '+', '*', '+',
		//		'*', '+', '*', '+', '*' };
	
//		int point[] = new int[] { 3,4, 7, 2, 5, 3, 4, 9, 5, 10, 10, 5, 6,3 ,9};
//		char operate[] = new char[] { '+','*', '*', '+', '+', '+', '+', '*', '+',
//				'*', '+', '*', '+', '*' ,'+'};
//		
//		String[] rStrings=ploy_best(point, operate);
//		
//		for(int i=0;i<n;i++){
//			System.out.println(rStrings[i]);
//		}
	}
*/
}
