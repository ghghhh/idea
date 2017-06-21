package com.cs.java8;

/**
 * Created by s0c00q3 on 2017/4/13.
 */
/**
 * 常规的表达式求值，我们都会根据计算的优先级来计算。比如 * /的优先级就高于+-。
        但是小易所生活的世界的表达式规则很简单，从左往右依次计算即可，
        而且小易所在的世界没有除法，意味着表达式中没有/，只有(+, – 和 *)。现在给出一个表达式，需要你帮忙计算出小易所在的世界这个表达式的值为多少

        输入描述:

        输入为一行字符串，即一个表达式。其中运算符只有-,+,*。参与计算的数字只有0~9.
        保证表达式都是合法的，排列规则如样例所示。
        输出描述:

        输出一个数，即表达式的值
        输入例子:

        3+5*7
        输出例子:

        56
* */
public class Expression {
    public static void main(String[] args) {
        int i= 0;
        try {
            i = expression("1+-2*3+2*3-1+1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(i);
    }

    public static int expression(String s) throws Exception {
        char[] c=s.toCharArray();
        int i=0;
        int j=1;
        int num=Character.getNumericValue(c[0]);
        while(!(j>c.length-2)){
            num=sum(num,c[i+2],c[j]);
            i=i+2;
            j=j+2;
        }
        return num;
    }
    public static int sum(int a,char b,char c) throws Exception{
        int nb=Character.getNumericValue(b);
        int num=0;
        if('+'==c){
            num=a+nb;
        }else if('-'==c){
            num=a-nb;
        }else if('*'==c){
            num=a*nb;
        }else{
            throw new Exception("表达式不对");
        }
        return num;
    }
}
