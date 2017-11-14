package com.cs.sort;

public class Test {

	public static void main(String[] args) {
		int i=setp(15);
		System.out.println(i);
	}

	/**
	 * 找出数组中超过相同数学超过一半的数 
	 */
	public static void findOut(int[] nums){
		int times=1;
		int num=nums[0];
		for(int i=1;i<nums.length;i++){
			if(times<0){								
				num=nums[i];
				times=1;							
			}else if(num==nums[i]){
				times++;
			}else{
				times--;
			}
		}
		int count=0;
		for(int j=0;j<nums.length;j++){
			if(nums[j]==num){
				count++;
			}
		}
		if(count>nums.length>>1){
			System.out.println("find: "+num);
		}else{
			System.out.println("none");
		}
	}
	
	/*
	 * 台阶问题，总共15级台阶，小明一次最多走3级 问有多少种走法 
	 * 最后一步可以走1级或2级或3级
	 */
	public static int setp(int m){
		if(m==1){
			return 1;
		}else if(m==2){
			return 2;
		}else if(m==3){
			return 4;
		}
		else{
			return setp(m-1)+setp(m-2)+setp(m-3);
		}		
	}
	
	//数组最大连续和
	
	
	public static int say(int[] n){
		int sum=0;
		int min=-10000;
		for(int i=0;i<n.length;i++){
			if(sum<0){
				sum=n[i];
			}else{
				sum=sum+n[i];
			}
			if(sum>min){
				min=sum;
			}
		}
		return min;
	}

	//二分查找
	public static int search1(int[] nums,int min,int max,int target){
		if(min==max){
			return -1;
		}
		int mid=(min+max)/2;
		if(nums[mid]==target){
			return mid;
		}else if(nums[mid]>target){
			return search1(nums,min,mid-1,target);
		}else if(nums[mid]<target){
			return search1(nums,mid+1,max,target);
		}else{
			return 0;
		}
	}
	public static int search(int[] nums,int target){
		int left=0;
		int right=nums.length;
		while(left<=right){
			int mid=(left+right)/2;
			if(nums[mid]==target){
				return mid;
			}else if(nums[mid]>target){
				right=mid-1;
			}else if(nums[mid]<target){
                left=mid+1;
			}
		}
		return -1;
	}
}
