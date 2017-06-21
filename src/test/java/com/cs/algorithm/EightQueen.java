package com.cs.algorithm;

public class EightQueen {

	public static void main(String[] args) {
		queen(8);
	}

	public static boolean check(int[] a,int n){
		for(int i=0;i<n;i++){
			if(a[i]==a[n]||Math.abs(a[i]-a[n])==Math.abs(i-n)){
				//跟前n行的皇后冲突
				return false;
			}
		}
		return true;
	}
	
    public static void show(int[] a){
		
		for(int i=0;i<a.length;i++){
			int b=a[i];
			for(int j=0;j<b;j++){
				System.out.print(" * ");
			}
			System.out.print(" Q ");
			for(int k=b+1;k<a.length;k++){
				System.out.print(" * ");
			}			
			System.out.println();
		}
		System.out.println("----------------------");
	}
	//n个皇后
    public static void queen(int n){
    	//初始化棋盘，每个皇后位于0位置
    	int[] a=new int[n];
    	int count=0;
    	int i=0;
    	while(true){
    		if(a[i]<n){
    			//检查i行与之前行是否冲突
    			if(!check(a, i)){
    				//与之前冲突
    				a[i]++;
    				continue;
    			}
    			if(i>=n-1){
    				//最后一行，打印符合的解
    				show(a);
    				count++;
    				//回溯，试当前行下一格是否符合
    				a[i]++;
    				continue;
    			}
    			i++;
    			continue;
    		}else{
    			//棋子已经出了棋盘，该行复位
    			a[i]=0;
    			//返回上一行
    			i--;
    			if(i<0){
    				System.out.println("queen count:"+count);
    				return;
    			}
    			//上一行的棋子往下走一步
    			a[i]++;
    		}
    	}
    }
}
