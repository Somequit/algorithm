package leetcode.contest.contest_355;

import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;

import java.util.*;


/**
 * 给你一个下标从 0 开始、长度为 n 的数组 usageLimits 。
 * 你的任务是使用从 0 到 n - 1 的数字创建若干组，并确保每个数字 i 在 所有组 中使用的次数总共不超过 usageLimits[i] 次。此外，还必须满足以下条件：
 * 每个组必须由 不同 的数字组成，也就是说，单个组内不能存在重复的数字。
 * 每个组（除了第一个）的长度必须 严格大于 前一个组。
 * 在满足所有条件的情况下，以整数形式返回可以创建的最大组数。
 * 1 <= usageLimits.length <= 10 ^ 5
 * 1 <= usageLimits[i] <= 10 ^ 9
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            List<Integer> usageLimits = CollectionUtils.arrayToList(AlgorithmUtils.systemInArray());

            int res = contest.solution(usageLimits);
            System.out.println(res);
        }

    }

    /**
     *
     */
    public int solution(List<Integer> usageLimits) {
        int min = 1;
        // 最多分成 max 组（最好情况是第 i 组拥有 i 个不重复元素），则最后一组一定包含 usageLimits 的每个元素，因此最多就是 usageLimits.size() 组
        int max = usageLimits.size();

        // 二分答案
        int res = min;
        while (min <= max) {
            int mid = (min + max) >> 1;

            if (doMaxIncreasingGroups(usageLimits, mid)) {
                res = mid;
                min = mid + 1;

            } else {
                max = mid - 1;
            }
        }

        return res;
    }

    /**
     * 判断是否可以分成 group 组
     */
    private boolean doMaxIncreasingGroups(List<Integer> usageLimits, int group) {
        List<Integer> usageLimitTemps = new ArrayList<>(usageLimits);
        // 倒序排序
        Collections.sort(usageLimitTemps, (o1, o2) -> {return o2 - o1;});

        // 还需要多少后续元素补充
        int complement = 0;
        for (int usageLimitTemp : usageLimitTemps) {

            // 不能使当前 group 组都分到
            if (usageLimitTemp < group){
                // 需要后续元素补充
                complement += group - usageLimitTemp;

            // 补充前面差的元素，注意不能补充后面差的元素
            } else {
                complement -= (usageLimitTemp - group);
                // 不能补充后面差的元素
                complement = Math.max(0, complement);
            }

            if (group > 0) {
                group--;
            }
        }

        return complement == 0;
    }

}
