package com.cs.sort;

/**
 * Created by cs on 2017/6/26.
 */
public class QuickSort {
    public static void main(String[]args){

        int [] nums={2,5,1,6,9,11,3,4,8,10};
        sort(nums,0,nums.length-1);
        for(int i=0;i<nums.length;i++){
            System.out.print(nums[i]+" ");
        }
    }

    public static void sort(int[] nums,int left,int right){
        if(left>=right){
            return;
        }
        //temp中存的就是基准数
        int temp=nums[left];
        int i,j,t;
        i=left;
        j=right;
        while(i!=j){
            //顺序很重要，要先从右往左找比基准数小的
            while (nums[j]>=temp&&j>i){
                j--;
            }
            while (nums[i]<=temp&&i<j){
                i++;
            }
            //交换两个数在数组中的位置
            //当i，j还没相遇时
            if(i<j){
                t=nums[i];
                nums[i]=nums[j];
                nums[j]=t;
            }
        }
        //最终将基准数归位
        nums[left]=nums[i];
        nums[i]=temp;
        //递归继续处理
        sort(nums, left, i-1);
        sort(nums,i+1,right);
    }
}
