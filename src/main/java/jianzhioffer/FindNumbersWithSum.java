package jianzhioffer;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 */
public class FindNumbersWithSum {

    public static void main(String[] args) {
        int[] array = AlgorithmUtils.systemInArray();
        long sum = AlgorithmUtils.systemInNumber();
        List<Integer> resultList = (new FindNumbersWithSum()).Solution(array, sum);
        System.out.println(resultList);
    }

    /**
     * 数组递增、并且a+b=sum,因此a从小到大遍历的时候、b只需要从大到小遍历、且b不需要回溯，所以时间复杂度O(N)
     * 假设：若b>a,且存在，
     * a + b = s;
     * (a - m ) + (b + m) = s
     * 则：(a - m )(b + m)=ab - (b-a)m - m*m < ab；说明外层的乘积更小
     * 所以只需要循环找到第一个匹配的数据解释结果
     */
    private List<Integer> Solution(int[] array, long sum) {
        ArrayList<Integer> resultList = new ArrayList<>();
        // 双指针
        int len = array.length;
        for(int i=0,j=len-1;i<j;i++){
            for(;j>=0;j--){
                if(array[i]+array[j]<sum){
                    break;
                } else if(array[i]+array[j]==sum){
                    resultList.add(array[i]);
                    resultList.add(array[j]);
                    return resultList;
                }
            }
        }
        return resultList;

    }
}
