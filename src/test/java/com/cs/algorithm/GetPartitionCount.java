package com.cs.algorithm;

public class GetPartitionCount {

	public static void main(String[] args) {
		System.out.println(count(2,2));

	}

	public static int count(int n,int max){
		if(n==1||max==1){
			//System.out.println (1);
			return 1;
		}else if(n<max){
			return count(n,n);
		}else if(n==max){
			return 1+count(n, n-1);
		}else{
			return count(n, max-1)+count(n-max, max);
		}
	}
}

