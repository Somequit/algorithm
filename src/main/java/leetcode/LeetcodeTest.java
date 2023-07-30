package leetcode;

import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * @date 2023/6/18
 */
public class LeetcodeTest {

    public static void main(String[] args) {
        LeetcodeTest leetcodeTest = new LeetcodeTest();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int[][] operations = AlgorithmUtils.systemInTwoArray();

            int[] res = leetcodeTest.solution(nums, operations);
            System.out.println(Arrays.toString(res));
        }
    }

    private int[] solution(int[] nums, int[][] operations) {
        Map<Integer, Integer> numIndexMap = new HashMap<>(nums.length << 1);
        for (int i = 0; i < nums.length; i++) {
            numIndexMap.put(nums[i], i);
        }

        for (int i = 0; i < operations.length; i++) {
            int tempIndex = numIndexMap.get(operations[i][0]);
            nums[tempIndex] = operations[i][1];
            numIndexMap.put(operations[i][1], tempIndex);
        }

        return nums;
    }
}
