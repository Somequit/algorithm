package jianzhioffer;

import utils.AlgorithmUtils;

/**
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * TODO:负数是否满足
 */
public class FindNumsAppearOnce {

    public static void main(String[] args) {
        int[] array = AlgorithmUtils.systemInArray();
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        (new FindNumsAppearOnce()).Solution(array, num1, num2);
        System.out.println("num1:"+num1[0]+" num2:"+num2[0]);
    }

    /**
     * 因为相同的数字异或、结果为0，因此所有数字异或一遍结果为num1^num2
     * 接着找出二进制位为1的值，这一位中num1与num2一定是有一个是0、有一个是1
     * 根据这个特性将该位为1的值取出来再次异或一遍就得到了num1或者num2
     */
    public void Solution(int [] array,int num1[] , int num2[]) {
        if(array==null || array.length<2){
            return;
        }
        // 所有数字异或、获取x=num1^num2的值
        int betweenNumXor = getBetweenNumXor(array);
        // 获取x的按位为1的第一位y
        int betweenNumXorBit1 = getBit1(betweenNumXor);
        if(betweenNumXorBit1 < 1){
            return;
        }
        // 获取数组中第z位为1的所有数的异或 就是num1、同时可以获的num2
        num1[0] = getNumXorByBit1(array, betweenNumXorBit1-1);
        num2[0] = betweenNumXor ^ num1[0];
        return;
    }
    /**
     * 所有数字异或、获取x=num1^num2的值
     */
    private int getBetweenNumXor(int [] array){
        int result = array[0];
        int len = array.length;
        for(int i=1;i<len;i++){
            result ^= array[i];
        }
        return result;
    }
    /**
     * 获取x的按位为1的第一位y
     */
    private int getBit1(int x){
        for(int i = 1;i <= 31;i++){
            if((x & 1) == 1){
                return i;
            }
            x = (x >> 1);
        }
        return 0;
    }
    /**
     * 获取数组中第z位为1的所有数的异或 就是num1
     */
    private int getNumXorByBit1(int [] array, int z){
        int result = 0;
        int len = array.length;
        for(int i = 0;i < len;i++){
            if(((array[i] >> z) & 1) == 1){
                result ^= array[i];
            }
        }
        return result;
    }

}
