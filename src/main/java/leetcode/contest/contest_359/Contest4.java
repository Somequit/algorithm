package leetcode.contest.contest_359;


import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            List<Integer> nums = AlgorithmUtils.systemInList();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, k);
            System.out.println(res);
        }

    }

    public int solution(List<Integer> nums, int k) {
        int n = nums.size();
        // nums[i] - prefix(gap)
        List<Integer>[] equalsArrayList = new ArrayList[n + 1];
        for (int i = 1; i < equalsArrayList.length; i++) {
            equalsArrayList[i] = new ArrayList<>();
        }

        Map<Integer, Integer> numIndexMap = new HashMap<>(n << 1);
        for (int i = 0; i < n; i++) {
            if (!numIndexMap.containsKey(nums.get(i))) {
                numIndexMap.put(nums.get(i), i);

            } else {
                equalsArrayList[nums.get(i)].add(i - numIndexMap.get(nums.get(i)) - 1);

                int listSize = equalsArrayList[nums.get(i)].size();
                if (listSize > 1) {
                    int prefixGap = equalsArrayList[nums.get(i)].get(listSize - 1) + equalsArrayList[nums.get(i)].get(listSize - 2);
                    equalsArrayList[nums.get(i)].set(listSize - 1, prefixGap);
                }

                numIndexMap.put(nums.get(i), i);
            }
        }
//        for (int i = 1; i < equalsArrayList.length; i++) {
//            System.out.println("i:" + i + " : " + equalsArrayList[i]);
//        }

        int res = 1;
        for (int i = 1; i < equalsArrayList.length; i++) {
            if (equalsArrayList[i].size() > 0) {
                for (int j = 0; j < equalsArrayList[i].size(); j++) {
                    if (k >= equalsArrayList[i].get(j)) {
                        res = Math.max(res, j + 2);

                    } else if (j > 0 && equalsArrayList[i].get(j) - equalsArrayList[i].get(j - 1) <= k) {
                        int minIndex = binaryMinIndex(equalsArrayList[i], 0, j - 1, equalsArrayList[i].get(j) - k);
                        res = Math.max(res, j - minIndex + 1);
                    }
                }
            }
        }

        return res;
    }

    /**
     * 非递减数组 [left,right] 大于等于 k 的最小下标
     */
    private int binaryMinIndex(List<Integer> list, int left, int right, int k) {
        int res = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= k) {
                res = mid;
                right = mid - 1;

            } else {
                left = mid + 1;
            }
        }
        return res;
    }


}
