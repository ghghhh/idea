package com.cs.algorithm;

public class BeiBao {

	public static void main(String[] args) {
		int []price={500,1500,1200,2000};
		int []capacity={1,3,2,5};
		int m=3;
		int max=beiBao(price, capacity, m);
		System.out.println("背包容量为:"+m+",最大价格为:"+max);
	}

	/**
	 * 物品无限个
	 * @param price 价格
	 * @param capacity 容量
	 * @param m 背包容量
	 */
	public static int beiBao(int[] price,int[] capacity,int m){
		if(m==0){
			return 0;
		}
		int max=0;
		for(int i=1;i<=m;i++){			
			for(int j=0;j<price.length;j++){
				if(i>=capacity[j]){
					max=Integer.max(max,price[j]+beiBao(price, capacity, i-capacity[j]));
				}
			}
		}
		return max;
	}
	/**
	 * 物品每样只有1个
	 * @param price 价格
	 * @param capacity 容量
	 * @param m 背包容量
	 */
	public static int beiBao1(int[] price,int[] capacity,int m){
		if(m==0){
			return 0;
		}
		int max=0;
		int[] flag=new int[price.length];
		for(int i=1;i<=m;i++){			
			for(int j=0;j<price.length;j++){
				if(i>=capacity[j]){
					max=Integer.max(max,price[j]+beiBao(price, capacity, i-capacity[j]));
				}
			}
		}
		return max;
	}
}
