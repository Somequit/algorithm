package jianzhioffer;

import utils.AlgorithmUtils;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * 如果不存在则输出0。
 */
public class MoreThanHalfNum {

    public static void main(String[] args) {
        int[] array = AlgorithmUtils.systemInArray();
        int result = Solution(array);
        System.out.println(result);
    }

    /**
     * 一个数字的个数大于数组长度的一半，那这个每次删除两个不同的数最后剩下的一个或者相同的两个数就一定是需要的数字。
     * 具体就是循环数组、如果一个数出现多次就记录它次数、如果出现其他数就减去记录的数，最后需要判断记录的数是否符合规则。
     * 注意：其他想法，这个数字一定是中位数、因此也可以使用快排的partition函数实现
     */
    private static int Solution(int [] array) {
        if(array == null || array.length<=0){
            return -1;
        }
        int result = array[0];
        int count = 1;
        for(int i=1;i<array.length;i++){
            if(count<=0){
                count=1;
                result = array[i];
            } else if(array[i] == result){
                count++;
            } else{
                count--;
            }
        }
        count = 0;
        for(int i=0;i<array.length;i++){
            if(result == array[i]){
                count++;
            }
        }
        return count>(array.length>>1)?result:0;
    }

}
