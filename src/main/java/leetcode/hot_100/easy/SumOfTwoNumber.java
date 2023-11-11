package leetcode.hot_100.easy;

import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.*;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 你可以按任意顺序返回答案。
 */
public class SumOfTwoNumber {

    public static void main(String[] args) {
        int[] nums = AlgorithmUtils.systemInArray();
        int target = (int)AlgorithmUtils.systemInNumber();
        int[] result = twoSum(nums, target);
        System.out.println(result[0]);
        System.out.println(result[1]);

        int[] result2 = twoSum2(nums, target);
        System.out.println(result2[0]);
        System.out.println(result2[1]);
    }

    /**
     * 数组排序，然后使用双指针方式方式获取两个数据
     * 时间复杂度：O（nlog2n）、空间复杂度：O（n）
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum (int[] nums, int target) {
        int[] result = new int[2];
        List<Pair<Integer, Integer>> numsList = new ArrayList<>(nums.length);
        for(int i=0;i<nums.length;i++){
            Pair<Integer, Integer> pairTemp = new Pair<>(nums[i], i);
            numsList.add(pairTemp);
        }
        Collections.sort(numsList, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        int startPoint = 0;
        int endPoint = numsList.size() - 1;
        while(startPoint <= endPoint){
            int startKey = numsList.get(startPoint).getKey();
            int endKey = numsList.get(endPoint).getKey();
            if(startKey + endKey > target){
                endPoint--;
            } else if(startKey + endKey < target){
                startPoint++;
            } else{
                result[0] = numsList.get(startPoint).getValue();
                result[1] = numsList.get(endPoint).getValue();
                break;
            }
        }
        return result;
    }

    /**
     * 遍历每个数、判断target-num[i]是否在Map(value, index)中，不在则将num[i]加入Map
     * 由于只能有一组结果、因此如果某个数字出现两次，要么两个数之和就是结果、要么结果不会出现该数字，因此可以覆盖加入map
     * 时间复杂度：O（n）、空间复杂度：O（n）
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2 (int nums[], int target) {
        int[] result = new int[2];

        int length = nums.length;
        Map<Integer, Integer> storageMap = new HashMap<>(length << 1);
        for (int i=0; i<length; i++) {
            if (storageMap.containsKey(target - nums[i])) {
                result[0] = storageMap.get(target - nums[i]);
                result[1] = i;
            } else {
                storageMap.put(nums[i], i);
            }
        }
        return result;
    }
}


