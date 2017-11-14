package com.cs.sort;

public class ChaRu {

	public static void main(String[] args) {
		int[] nums={12,2,4,5,2,3,10,4,5,2,1,5};
		sort(nums);
		for(int i:nums){
			System.out.print(i+" ");
		}
	}

	public static void sort(int [] nums){
		for(int i=1;i<nums.length;i++){
			int j=i;
			int n;
			while(j>0&&nums[j]>nums[j-1]){
				n=nums[j];
				nums[j]=nums[j-1];
				nums[j-1]=n;
				j--;
			}
		}
	}
}
