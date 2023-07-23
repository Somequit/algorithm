package leetcode.contest.contest_355;

import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;

import java.util.*;


/**
 * 6955. 长度递增组的最大数目
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
     * 二分答案 + 贪心：根据题意易得最好的分法就是第 i 组拥有 i 个不重复元素，i 从 1 开始每次递增 1，其次我们并不关心哪个数字在哪个位置，因此可以次数可以排序，
     * 如果最多可以分成 i 组，那么 [1,i] 组均可以分，因此答案满足非递减顺序，可以二分答案转化为：校验是否可以划分为 g 组，（分别为 1、2...g 个元素、且组内不重复）
     *     首先将 usageLimits 降序排序，
     *     然后循环数组依次与 g、g-1、g-2...1 个元素比较，例如 u[i] 与 g 代表将 u[i] 个数分到第 [1,g] 组每组一个元素
     *         如果 u[i] == g 代表 [1,g] 每组恰好填一个元素
     *         如果 u[i] < g 代表还需要 g-u[i] 个元素补充 [u[i]+1,g] 组
     *         如果 u[i] > g 代表可以补充前面的 u[i]-g 个缺口，同时注意补完了则清空、不能留下补后面的缺口
     *     最后如果缺口全部补完则可以划分，否则不能划分
     *     注意 g 如果减少到 0 则不再减少，如果 u[i] 还剩则可以将 u[i] 全部补充缺口
     *     注意不能留下补后面的缺口是因为，后面的组一定会与当前添加过的组重复、会导致某组出现两个 u[i]，而补充前面的组不会重复，同时由于数字递减、因此一个数不会补太多前面缺口导致某组重复
     *         举例：5 2 2 2 分成 4 组，5 填满了 [1,4] 组剩 1，而后面只有 [1,3] [1,2] [1,1] 组，如果补充会重复，u[3] 的 2 填满了 [1,1] 剩 1 可以补 u[1] 的 2 无法填充的第 3 组
     *
     * 时间复杂度：O(n*logn)，空间复杂度：O(n)（如果修改传入 List 可以做到 O(1)）
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
        int gap = 0;
        for (int usageLimitTemp : usageLimitTemps) {

            // 当前 group 组无法填满
            if (usageLimitTemp < group){
                // 需要后续元素补充
                gap += group - usageLimitTemp;

            // 补充前面缺少的元素，注意不能补充后面缺少的元素
            } else {
                gap -= (usageLimitTemp - group);
                // 不能补充后面差的元素
                gap = Math.max(0, gap);
            }

            if (group > 0) {
                group--;
            }
        }

        return gap == 0;
    }

}
