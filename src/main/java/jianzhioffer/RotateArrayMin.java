package jianzhioffer;

import utils.AlgorithmUtils;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组[3,4,5,1,2]为[1,2,3,4,5]的一个旋转，该数组的最小值为1。
 */
public class RotateArrayMin {

    public static void main(String[] args) {
        int[] array = AlgorithmUtils.systemInArray();
        int result = minNumberOnRotateArray(array);
        System.out.println(result);
    }

    /**
     * 二分数据,如果中间的值大于左边缘、则代表最小值在左边缘到当前中
     * 如果中间的值小于右边缘、则代表最小值在当前到右边缘中
     * 两者都非代表当前数组是一个非递减数组
     */
    private static int minNumberOnRotateArray(int[] array) {
        if(array.length <= 0){
            return 0;
        }
        if(array.length == 1){
            return array[0];
        }
        // 二分查询
        int start = 0;
        int end = array.length-1;
        while(start < end - 1){
            int mid = ((start + end) >> 1);
            if(array[mid] > array[end]){
                start = mid;
            } else if(array[mid] < array[start]){
                end = mid;
            // 当前数组是一个非递减数组
            } else{
                return array[start];
            }
        }
        return Math.min(array[start], array[end]);
    }
}
