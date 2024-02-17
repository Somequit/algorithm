package leetcode.contest.double_124;

import javafx.util.Pair;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * Java + 模拟（分段/区间）
     * 第 1 步：
     * 特判只有一位结果为 1，
     * 结果与 nums 顺序无关则先排序，然后将连续的段放入 TreeMap，
     * 最后顺序遍历每个连续的段，判断是否可以与前一段拼接，找到最大值
     *
     * 第 2 步：
     * 找连续的数字时，注意如果此段有重复数，则该段可以拓展 +1
     * 例如：1 2 2 3，可以拓展成 1 2 3 4
     * 注意记录下该段是否有过拓展
     *
     * 第 3 步：
     * 顺序遍历每个连续的段时，有三种情况
     *     * 可以与前一段拼接，因为前一段拓展了（否则就不会分成两段）
     *     * 比前一段大 2 且前一段未拓展，则仅前一段可以整体向后移动一位（前一段的前一段要么没有、要么拓展了才可以和前一段拼接）
     *     * 两段无法拼接
     * 处理三种情况，获得最大值
     *
     * 时间复杂度：O（n*logn），空间复杂度：O（n）
     *
     */
    public int maxSelectedElements(int[] nums) {
        int res = 0;
        int n = nums.length;
        // 特判只有一位结果为 1
        if (n == 1) {
            return 1;
        }

        // 结果与 nums 顺序无关则先排序
        Arrays.sort(nums);
//        System.out.println(Arrays.toString(nums));

        int prev = nums[0];
        // 该段是否有过拓展
        boolean countGreater1 = false;
        // 连续的段放入 TreeMap，记录下该段是否有过拓展（Boolean）
        TreeMap<Integer, Pair<Integer, Boolean>> beginEndGreater1Map = new TreeMap<>();
        for (int i = 1; i < n; i++) {
            // 此段有重复数，则该段可以拓展 +1
            if (nums[i] - nums[i - 1] == 0) {
                countGreater1 = true;

            } else if (nums[i] - nums[i - 1] == 1) {
                continue;

            // 此处断开，前面凑成一段，如果可拓展则断尾 +1
            } else {
                beginEndGreater1Map.put(prev, new Pair<>(nums[i - 1] + (countGreater1 ? 1 : 0), countGreater1));
                prev = nums[i];
                countGreater1 = false;
            }
        }

        // 注意最后一段在循环外
        beginEndGreater1Map.put(prev, new Pair<>(nums[n - 1] + (countGreater1 ? 1 : 0), countGreater1));
//        System.out.println(beginEndGreater1Map);

        prev = -100;
        // 到目前为止最大连续个数
        int continuous = 0;
        // 前一连续的段的值
        Map.Entry<Integer, Pair<Integer, Boolean>> prevEntry = null;
        // 顺序遍历每个连续的段，判断是否可以与前一段拼接
        for (Map.Entry<Integer, Pair<Integer, Boolean>> entry : beginEndGreater1Map.entrySet()) {
//            System.out.println(prevEntry);
            // 此段可以与前一段拼接，因为前一段拓展了（否则就不会分成两段）
            if (entry.getKey() - prev == 1) {
                continuous += entry.getValue().getKey() - entry.getKey() + 1;

            // 此段比前一段大 2 且前一段未拓展，则仅前一段可以整体向后移动一位（前一段的前一段要么没有、要么拓展了才可以和前一段拼接）
            } else if (entry.getKey() - prev == 2 && !prevEntry.getValue().getValue()) {
                continuous = (entry.getValue().getKey() - entry.getKey() + 1)
                        + (prevEntry.getValue().getKey() - prevEntry.getKey() + 1);

            // 两段无法拼接
            } else {
                continuous = entry.getValue().getKey() - entry.getKey() + 1;
            }

            prev = entry.getValue().getKey();
            prevEntry = entry;
            res = Math.max(continuous, res);
        }

        return res;
    }


}
