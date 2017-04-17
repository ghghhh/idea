package com.cs.sort;

public class Test {

	public static void main(String[] args) {
		int[] nums={1,2,3,4,5,-1,1,3,4,5,-7};
		//findOut(nums);
        System.out.println(say(nums));
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
	 * 
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
}
