package com.cs.sort;

public class ShellSort {

	public static void main(String[] args) {
		int [] a={2,5,3,8,1,7,2,4,9};
		shellSort1(a);
		for(int n:a){
			System.out.print(n+" ");
		}

	}

	//网络版
	public static void shellSort(int[] a){
		int gap=a.length/2;
		while(gap>0){
			for(int i=gap;i<a.length;i++){
				for(int j=i-gap;j>=0&&a[j]>a[j+gap];j=j-gap){
					int temp=a[j];
					a[j]=a[j+gap];
					a[j+gap]=temp;
				}
			}
			gap=gap/2;
		}
	}
	//自己动手版
	public static void shellSort1(int[] a){
		for(int gap=a.length/2;gap>0;gap=gap/2){
			for(int i=0;i<gap;i++){
				for(int j=gap;j<a.length;j++){
					if(a[j]>a[j-gap]){
						int temp=a[j];
						int k=j;
						while(k>=gap&&temp>a[k-gap]){
							a[k]=a[k-gap];
							k=k-gap;
						}
						a[k]=temp;
					}
					
				}
			}
		}
	}
}
