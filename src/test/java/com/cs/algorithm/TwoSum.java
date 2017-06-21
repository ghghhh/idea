package com.cs.algorithm;

/**
 * Created by s0c00q3 on 2017/4/18.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 * */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums={3,7,11,2};
        int[] i=twoSum1(nums,9);
       for(int j=0;j<i.length;j++){
           System.out.println(i[j]);
       }
    }
    public static int[] twoSum(int[] nums,int target){
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                return new int[]{i};
            }
            count=count+nums[i];
        }
        Arrays.sort(nums);
        if(nums[0]>target){
            System.out.println("最小值比目标值大，无解");
            return null;
        }else if (count<target){
            System.out.println("输入数组总和比目标值小，无解");
            return null;
        }else if (count==target){
            int [] i=new int[nums.length];
            for(int j=0;j<nums.length;j++){
                i[j]=j;
            }
            return i;
        }


        return null;
    }

    public static int[] twoSum1(int[] nums,int target){
        Map<Integer,Integer> map=new HashMap<>();
        int[] result=new int[2];
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                result[1]=i;
                result[0]=map.get(nums[i]);
                return result;
            }else{
                map.put(target-nums[i],i);
            }
        }
        return result;
    }
}
