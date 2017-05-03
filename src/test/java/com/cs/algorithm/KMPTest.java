package com.cs.algorithm;

public class KMPTest {
	

	public static void main(String[] args) {
		String s="aaaaaaaaaaaaaaaab";
		String b="aaaaaaaaaaaaaaaab11";
		find(s,b);
	}

	public static int[] getNest(String s){
		int len=s.length();
		int j=0;
		int next[]=new int[len+1];
		next[0]=next[1]=0;
		for(int i=1;i<len;i++){
			while(j>0&&s.charAt(i)!=s.charAt(j)){
				j=next[j];
			}
			if(s.charAt(i)==s.charAt(j)){
				j++;
			}
			next[i+1]=j;
		}
		return next;
	}
	
	public static void search(String original,String find,int next[]){
		int j=0;
		int num=0;
		for(int i=0;i<original.length();i++){
			while(j>0&&original.charAt(i)!=find.charAt(j)){
				j=next[j];
			}
			if(original.charAt(i)==find.charAt(j)){
				j++;
			}
			num++;
			if(j==find.length()){
				System.out.println("find at position"+(i-j));
				System.out.println(original.substring(i-j+1, i+1));
				j=next[j];
				System.out.println("次数："+num);
				break;
			}
		}
	}
	//暴力匹配
	public static int find(String target,String find){
		int i=target.length();
		int j=find.length();
		for(int a=0;a<=i-j;a++){
			int b;
			for(b=0;b<j;b++){
				if(target.charAt(a+b)!=find.charAt(b)){
					break;
				}
			}
			if(b==j){
				System.out.println("找到匹配的字符串,范围为:"+a+"-"+(a+b));
				return a;
			}
		}
		System.out.println("没有找到匹配的字符串");
		return -1;
	}
}
