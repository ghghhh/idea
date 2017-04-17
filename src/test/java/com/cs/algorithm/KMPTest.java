package com.cs.algorithm;

public class KMPTest {

	public static void main(String[] args) {
		String s="aaaaaaaaaaaaaaaaaaab";
		String b="aaaaaaabaaaaaaaaab";
		int []n=getNest(b);	
		for(int i:n){
			System.out.print(i+" ");
		}
		System.out.println();
		search(s, b, n);
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
	
}
