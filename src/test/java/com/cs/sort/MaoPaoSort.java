package com.cs.sort;

public class MaoPaoSort {

	public static void main(String[] args) {
		int []n={7,2,1,3,2,6,4};
        sort2(n);
        for(int a:n){
        	System.out.print(a+" ");
        }
	}

	public static void swap(int[]n,int i,int j){
		int a=n[i];
		n[i]=n[j];
		n[j]=a;
	}
	/**
	 * 选择排序
	 * */
	public static void sort0(int[] n){
		for(int i=0;i<n.length;i++){
			for(int j=i+1;j<n.length;j++){
				if(n[i]<n[j]){
					swap(n, i, j);
				}
			}
		}
	}
	//冒泡排序
	public static void sort1(int[] n){
		for(int i=0;i<n.length;i++){
			for(int j=0;j<n.length-i-1;j++){
				if(n[j]<n[j+1]){
					swap(n, j, j+1);
				}
			}
		}
	}
	//插入排序
	//int []n={1,2,1,3,2,6,4};
	public static void sort2(int[] n){
		
		for(int i=1;i<n.length;i++){
			if(n[i]<n[i-1]){
				int temp=n[i];
				int j=i;
				while(j>0&&n[j-1]>temp){
					n[j]=n[j-1];
					j--;
				}
				n[j]=temp;
			}
		}
	}
}
