package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeSum {

    public static void main(String[] args) {
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
//            List<List<Integer>> result = (new ThreeSum()).solution(nums);
//            System.out.println("三数之和 结果:" + result);
            List<List<Integer>> result2 = (new ThreeSum()).solution2(nums);
            System.out.println("三数之和 结果:" + result2);
        }
    }

    /**
     * 排序 + 双指针 + 判断去重
     * @param nums
     * @return
     */
    public List<List<Integer>> solution2(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return resultList;
        }

        // 排序数组
        Arrays.sort(nums);

        /**
         * 三重循环，第三重循环倒序却无回溯、二三重循环双指针
         * 去重：前两个数均与之前的结果相同、则直接 continue
         */
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                System.out.println(String.format("三数之和 i 重复 i:%s nums[i]:%s", i, nums[i]));
                continue;
            }

            for (int j = i + 1, k = nums.length - 1; j < k; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    System.out.println(String.format("三数之和 j 重复 j:%s nums[j]:%s", j, nums[j]));
                    continue;
                }

                while (nums[i] + nums[j] + nums[k] > 0 && j < k) {
                    k --;
                }
                System.out.println(String.format("三数之和 i:%s j:%s k:%s nums[i]:%s nums[j]:%s nums[k]:%s", i, j, k,
                        nums[i], nums[j], nums[k]));
                if (j < k && nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> threeSumList = new ArrayList<>(3);
                    threeSumList.add(nums[i]);
                    threeSumList.add(nums[j]);
                    threeSumList.add(nums[k]);
                    resultList.add(threeSumList);
                    k--;
                }
            }
        }

        return resultList;
    }

    /**
     * 双重循环 + set 查询 + set 去重
     * @param nums
     * @return
     */
    public List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        // 入参校验
        if (nums == null || nums.length < 3) {
            return resultList;
        }

        // 去重 set
        Set<String> distinctSet = new HashSet<>((nums.length << 2) / 3);
        Set<Integer> numSet = new HashSet<>((nums.length << 2) / 3);
        // 双重循环找出三元组
        for (int i = 1; i < nums.length; i++) {
            // 第三个数在后俩数前面，保证相同数字不会使用多次
            numSet.add(nums[i - 1]);
            System.out.println("三数之和 nums[i - 1]:" + nums[i - 1]);
            for (int j = i+1; j < nums.length; j++) {

                int otherNum = 0 - nums[i] - nums[j];
                // 和为 0 的第三个数前面存在，且三数以前没出现过
                if (numSet.contains(otherNum) && distinctNum(distinctSet, nums[i], nums[j], otherNum)) {
                    List<Integer> threeSumList = new ArrayList<>(3);
                    threeSumList.add(otherNum);
                    threeSumList.add(nums[i]);
                    threeSumList.add(nums[j]);
                    resultList.add(threeSumList);
                    System.out.println(String.format("三数之和 i:%s j:%s nums[i]:%s nums[j]:%s", i, j, nums[i], nums[j]));
                }
            }
        }

        return resultList;
    }

    /**
     * 判断后三个数是否唯一，唯一则加入集合
     */
    private boolean distinctNum(Set<String> distinctSet, int num1, int num2, int num3) {
        // 从小到大升序排列三个数，依次拼接
        String ascStr = ascNum(num1, num2, num3);
        System.out.println("三数之和 ascStr:" + ascStr);

        // 判断后三个数是否出现过
        if (distinctSet.contains(ascStr)) {
            return false;
        }

        // 没有则加入集合
        distinctSet.add(ascStr);
        return true;
    }

    /**
     * 从小到大升序排列三个数，依次拼接
     */
    private String ascNum(int num1, int num2, int num3) {
        StringBuilder ascStr = new StringBuilder();
        int minNum = Math.min(Math.min(num1, num2), num3);
        int maxNum = Math.max(Math.max(num1, num2), num3);
        int midNum = num1 + num2 + num3 - minNum - maxNum;
        return ascStr.append(minNum).append(midNum).append(maxNum).toString();
    }
}
