package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 621. 任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU
 * 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 * 1 <= task.length <= 10 ^ 4
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 * @date 2023/9/19
 */
public class LeastInterval {

    public static void main(String[] args) {
        LeastInterval leastInterval = new LeastInterval();
        while (true) {
            char[] tasks = AlgorithmUtils.systemInArrayChar();
            int n = AlgorithmUtils.systemInNumberInt();

            int res = leastInterval.solution(tasks, n);
            System.out.println(res);

            int res2 = leastInterval.solution2(tasks, n);
            System.out.println(res2);
        }
    }

    /**
     * 构造：将任务按照种类数合并、得到每种任务个数，然后将任务个数最多（maxCount 个）的任一（maxKind 种）种类、作为每一组的开头，
     * 那么最少需要执行 (n+1)*(maxCount-1)+maxKind 次，如果任务总数小于等于它就没问题：
     *     非最后一组放置 (n+1) 个共 (maxCount-1) 组，最后一组放置 maxKind 个，因为最大个数就是 maxCount 个，因此不可能让某种任务放两个到同一组
     * 但是如果任务总数大于它，可看做即种类太多了，这时可以将多余元素的依次放入每组后面，即使每组超过 (n+1) 也不违反规则，这样不会产生空闲时间，因此答案为人任务总数：tasks.length
     * 总结结果为：max(tasks.length， (n+1)*(maxCount-1)+maxKind)
     * n 为 tasks.length、即总任务个数，时间复杂度：O（n+任务种类数），空间复杂度：O（任务种类数）
     */
    private int solution2(char[] tasks, int n) {
        // 统计每种字符个数
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        // 获取任务个数最多（maxCount 个）的任一（maxKind 种）种类
        int maxCount = 0;
        int maxKind = 0;
        for (int i = 0; i < count.length; i++) {
            if (maxCount < count[i]) {
                maxCount = count[i];
                maxKind = 1;

            } else if (maxCount == count[i]) {
                maxKind++;
            }
        }
//        System.out.println(maxCount + " : " + maxKind);

        return Math.max(tasks.length, (n + 1) * (maxCount - 1) + maxKind);
    }

    /**
     * 优先队列：将 n+1 个元素组成一组，每组元素均放置不同元素，贪心的使 n+1 种任务数最大的任务放入一组（优先队列存储每种任务剩余数量）、即每种任务数减一，直到全部任务分配完成
     * 当任务种类小于 n+1 时一定产生空闲、此时仅需要将所有种类任务数减一，最后一组任务如果不满 n+1 也不需要空闲
     * n 为 tasks.length、即总任务个数，时间复杂度：O（n*logn+任务种类数），空间复杂度：O（任务种类数）
     */
    private int solution(char[] tasks, int n) {
        // 统计每种字符个数
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }

        // 将大于 0 的个数放入大顶堆中
        Queue<Integer> countQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                countQueue.add(count[i]);
            }
        }

        int res = 0;
        // 每次弹出 n+1 个元素
        while (!countQueue.isEmpty()) {

            List<Integer> list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                // 如果不够则不弹出
                if (countQueue.isEmpty()) {
                    break;
                }
                // 将弹出的元素均 -1
                list.add(countQueue.poll() - 1);
            }

            // 除最后一次、每次结果需要加上 n+1，最后一次加弹出的元素个数，最后一次：大顶堆扣完后不再有任何元素
            if (list.get(0) > 0) {
                res += n + 1;
            } else {
                res += list.size();
            }

            // 将剩余大于 0 的元素塞回大顶堆
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == 0) {
                    break;
                }
                countQueue.add(list.get(i));
            }
        }

        return  res;
    }
}
