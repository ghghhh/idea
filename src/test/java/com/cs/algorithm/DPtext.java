package com.cs.algorithm;

//切割钢条
public class DPtext {

	public static void main(String[] args) {
		int[] price={0,1,5,8,9,10,17,17,20,24,30};
        System.out.println("the price:");
        for(Integer i:price){
        	System.out.print(i+" ");
        }
        printCutRodSolution(price, 10);
        System.out.println("递归调用"+digui(price, 9));
	}

	public static int digui(int[]p,int n){
		int profit=0;
		if(n==0){
			return 0;
		}else if(n>p.length){
			profit=Integer.max(profit, digui(p, p.length)+digui(p, n-p.length));
		}else{
			for(int i=1;i<n;i++){
				profit=Integer.max(profit, p[i]+digui(p, n-i));
				//System.out.println("digui:"+i);
			}
			
		}
		return profit;
		
	}
	/**
	 * 自底向上非递归方法解决钢条切割问题
	 * 不仅保存最优收益值，还保存对应的切割方案
	 * @param p 不同长度钢条的价格
	 * @param n 钢条的总长度  
	 */
	public static int[][] extendedBottomUpCutRod(int[]p,int n){
		int [][] s=new int[2][n+1];
		s[0][0]=0;
		
		for(int j=1;j<=n;j++){
			int q=-99999;
			for(int i=1;i<=j;i++){
				if(q<p[i]+s[0][j-i]){
					q=p[i]+s[0][j-i];
					s[1][j]=i;
				}
			}
			s[0][j]=q;
		}
		System.out.println("最终收益："+s[0][n]);
		return s;		
	}
	
	/**
	 * 根据算法打印出长度为n的钢条完整的最优切割方案
	 * 
	 */
	public static void printCutRodSolution(int []p,int n){
		int result[][]=extendedBottomUpCutRod(p, n);
		System.out.println("钢条切割方案：");
		while(n>0){
			System.out.print(result[1][n]+" ");
			n=n-result[1][n];
		}
	}
	
}
