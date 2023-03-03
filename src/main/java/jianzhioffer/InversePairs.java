package jianzhioffer;

import utils.AlgorithmUtils;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 */
public class InversePairs {

    public static void main(String[] args) {
        while(true){
            int[] array = AlgorithmUtils.systemInArray();
            int result = inversePairs(array, 0, array.length);
            AlgorithmUtils.systemOutArray(array);
            System.out.println("result:"+result);
        }
    }

    /**
     * 模拟使用二路归并排序的方法
     * 两个有序数据排序的时候，后一个数组移动时前一个数据剩余数据就是逆序对数量
     * 注：归并排序是稳定排序
     * @param array
     */
    private static int inversePairs(int[] array, int start, int end) {
        int result = 0;
        if(end - start <= 1){
            return result;
        } else if(end - start == 2){
            int middle = (end + start >> 1);
            // 合并两个有序数据 （这儿是指两个长度为1的数据）
            result = merge(array, start, middle, end);
        } else{
            // 二分 分治
            int middle = (end + start >> 1);
            result = inversePairs(array, start, middle)+inversePairs(array, middle, end);
            result %= 1000000007;
            result += merge(array, start, middle, end);
        }
        return result%1000000007;
    }

    /**
     * 合并两个有序数据
     * @param array 有序数据
     * @param start 第一个数组左边界
     * @param middle 第二个数组左边界
     * @param end 第二个数组右边界
     * @return
     */
    private static int merge(int[] array, int start, int middle, int end) {
        int result = 0;
        // 需要一个新的暂存数据
        int tempLen = end-start;
        int[] tempSort = new int[tempLen];
        // 两个指针对两个有序数据移动
        int leftPoint = start;
        int rightPoint = middle;
        for(int i=0;i<tempLen;i++){
            if(leftPoint >= middle){
                tempSort[i] = array[rightPoint];
                rightPoint++;
            } else if(rightPoint >= end){
                tempSort[i] = array[leftPoint];
                leftPoint++;
            } else if(array[leftPoint] <= array[rightPoint]){
                tempSort[i] = array[leftPoint];
                leftPoint++;
            } else{
                tempSort[i] = array[rightPoint];
                rightPoint++;
                // 第一个数组剩下的个数 就是当前数在此合并时的逆序对数
                result += middle - leftPoint;
                result %= 1000000007;
            }
        }
        // 将暂存数据返回原数组
        for(int i=0;i<tempLen;i++){
            array[i+start] = tempSort[i];
        }
        return result;
    }
}
