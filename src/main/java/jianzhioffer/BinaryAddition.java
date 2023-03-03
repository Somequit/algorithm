package jianzhioffer;

import utils.AlgorithmUtils;

/**
 * 求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号
 */
public class BinaryAddition {

    public static void main(String[] args) {
        while(true){
            int[] num = AlgorithmUtils.systemInArray();
            int result = add(num[0], num[1]);
            System.out.println(result );
        }
    }

    /**
     * 使用位运算替代加法，类似计算机内部运算
     * 使用异或替代位加法，使用与运算以及移位替代进位，循环运算
     * 需要注意的一点是，其中有负数、计算方式不改变，因为计算机内部将负数作为补码（正数反码+1）计算，然后进行加法运算
     *
     * -7 + 10
     * 11111111111111111111111111111001 +   1010
     * 11111111111111111111111111110011 +  10000
     * …
     * 10000000000000000000000000000011 + 10000000000000000000000000000000
     * 11 + 0
     * 结果=3
     */
    public static int add(int num1,int num2) {
        while(num2 != 0){
            int temp = num1 ^ num2;
            num2 = ((num1 & num2) << 1);
            num1 = temp;
        }
        return num1;
    }

}
