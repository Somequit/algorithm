package leetcode.contest.contest_336;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * 2589. 完成所有任务的最少时间
 * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，其中 tasks[i] = [starti, endi, durationi]
 * 表示第 i 个任务需要在 闭区间 时间段 [starti, endi] 内运行 durationi 个整数时间点（但不需要连续）。
 * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
 * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
 * 1 <= tasks.length <= 2000
 * tasks[i].length == 3
 * 1 <= starti, endi <= 2000
 * 1 <= durationi <= endi - starti + 1
 *
 * @author gusixue
 * @date 2023/3/12
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int[][] tasks = new int[n][3];

            for (int i = 0; i < n; i++) {
                tasks[i][0] = AlgorithmUtils.systemInNumberInt();
                tasks[i][1] = AlgorithmUtils.systemInNumberInt();
                tasks[i][2] = AlgorithmUtils.systemInNumberInt();
            }

            int res = contest.solution(tasks);
            System.out.println(res);
        }

    }

    /**
     * 每个任务按照右端点升序，然后循环每个任务，保证运行时间足够的情况下先让后缀运行，这样能与后面的任务重叠的可能性更高，
     * 最简单的是开一个标记数组，代表当前时间点正在运行，循环任务数组同时循环标记数组即可
     * U 代表 end 的最大值，时间复杂度：O（nU），空间复杂度：O（U）
     */
    private int solution(int[][] tasks) {
        if (tasks == null || tasks.length == 0) {
            return 0;
        }

        int res = 0;

        // 右端点升序任务数组
        Arrays.sort(tasks, (t1, t2) -> {return t1[1] - t2[1];});
//        Arrays.stream(tasks).forEach(t -> {System.out.println(t[0] + ":" + t[1] + ":" + t[2]);});

        // 标记数组长度为最大的时间（最后一个节点的结束时间）
        boolean[] flag = new boolean[tasks[tasks.length - 1][1] + 1];

        // 循环任务数组
        for (int i = 0; i < tasks.length; i++) {

            int duration = tasks[i][2];
            // 已标记过代表已经执行过了
            for (int j = tasks[i][1]; j >= tasks[i][0] && duration > 0; j--) {
                if (flag[j]) {
                    duration--;
                }
            }

            // 如果未达到需要执行的时间点，从该任务后缀开始更新标记数组
            if(duration > 0) {
                for (int j = tasks[i][1]; j >= tasks[i][0] && duration > 0; j--) {
                    if (!flag[j]) {
                        duration--;
                        res++;
                        flag[j] = true;
                    }
                }
            }
        }

        return res;
    }

}
