package com.cs.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class Merge {

	public static void main(String[] args) {
		int [] a={1,4,6,9,22,32,44,55,66,78,122};
        int [] b={3,5,7,8,32};
       int []c= merge0(a, b);
       Arrays.sort(c);
	}

	public static void merge(int[]a,int[]b){
		int i=a.length;
		int j=b.length;
		int k=i+j;
		int[] c=new int[k];
		int l=-1;
		int f1=0;
		int f2=0;
		for(int m=f1;m<a.length;m++){
			for(int n=f2;n<b.length;n++){
				if(a[m]<=b[n]){
					c[++l]=a[m];
					f2=n;
					break;
				}else{
					c[++l]=b[n];
					
					continue;
				}
			}
		}
		for(int a1:c){
			System.out.print(a1+" ");
		}
	}
	public static int[] merge0(int[]a,int[]b){
		int i=a.length;
		int j=b.length;
		int[] c=new int[i+j];
		int n=0;
		int f1=0;
		int f2=0;
		/*
		int [] a={1,4,6,9,22};
        int [] b={3,5,7,8,32};
        */
		while(f1!=i&&f2!=j){
			if(a[f1]<b[f2]){
				c[n]=a[f1];
				f1++;
			}else{
				c[n]=b[f2];
				f2++;				
			}
			n++;
		}
		while(f1<i){
			c[n++]=a[f1++];
		}
		while(f2<j){
			c[n++]=b[f2++];
		}
		for(int a1:c){
			System.out.print(a1+" ");
		}
		return c;
	}
}
