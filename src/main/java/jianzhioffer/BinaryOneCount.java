package jianzhioffer;

/**
 * 二进制中1的个数
 * 输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。
 */
public class BinaryOneCount {

    public static void main(String[] args) {
        while(true){
            getPositiveOf1(1);
            getPositiveOf1New(1);
        }
    }

    /**
     * 每次“干掉”最后一位1、直到结果为0
     * @param num
     * @return
     */
    public static int getPositiveOf1New(int num){
        int result = 0;
        while(num != 0){
            result++;
            num = num & (num-1);
        }
        return result;
    }


    /**
     * 解答：
     * 必须使用int，因为int才能是32位
     * 询最后一位是否是1，然后每次右移一位（高位添0）直到结果位0
     * 对于负数，因为计算机内部负数就是补码，但是需要注意无符号右移动、否则符号位不会变动、导致-1死循环
     */
    public static int getPositiveOf1(int num){
        int result = 0;
        while(num != 0){
            if((num & 1) == 1){
                result++;
            }
            num = (num >>> 1);
        }
        return result;
    }


}
