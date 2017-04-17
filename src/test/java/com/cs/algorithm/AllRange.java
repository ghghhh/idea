package com.cs.algorithm;

/**
 * 数组的全排列
 *
 * */
public class AllRange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] c={"1","2","3","4","5"};
		allRange(c, 0, c.length);
	}

	//递归实现
	public static void allRange(String[] c,int start,int length){
		if(start==length-1){
			for(String r:c){
				System.out.print(r);
			}
			System.out.println();
		}else{
			for(int i=start;i<=length-1;i++){
				swap(c,start,i);
				allRange(c, start+1, length);
				swap(c,start,i);
			}
		}
		
	}
	public static void swap(String []c,int i,int j){
		String s=new String();
		s=c[i];
		c[i]=c[j];
		c[j]=s;
	}
	//回溯法实现
	public static void allRange1(String[] s){
		int[] i=new int[s.length];

	}
}
