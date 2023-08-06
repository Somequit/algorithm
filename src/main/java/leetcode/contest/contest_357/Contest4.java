package leetcode.contest.contest_357;

import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.*;


/**
 *
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();
        while (true) {
            int[][] items = AlgorithmUtils.systemInTwoArray();
            int k = AlgorithmUtils.systemInNumberInt();

            long res = contest.solution(items, k);
            System.out.println(res);
        }
    }

    public long solution(int[][] items, int k) {
        // 判空
        if (items == null || items.length == 0 || k <= 0) {
            return 0;
        }

        // profit 降序
        int n = items.length;
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        // 先取 profit 最大的 k 个元素
        long totalProfit = 0;
        Set<Integer> categorySet = new HashSet<>();
        // 使用栈（双端队列模拟）存储需要替换的元素（预先加入的 k 个元素），元素必须重复且不能最大
        Deque<Integer> duplicate = new ArrayDeque<>(n);
        for (int i = 0; i < k; i++) {
            int profit = items[i][0];
            int category = items[i][1];

            totalProfit += profit;
            // 重复的元素，同时由于 profit 降序，因此这一定不是最大的 profit
            if (!categorySet.add(category)) {
                duplicate.addFirst(profit);
            }
        }
        long maxElegance = totalProfit + (long)categorySet.size() * categorySet.size();
//        System.out.println(totalProfit + " : " + categorySet);
//        System.out.println(duplicate);

        // 反悔将后续元素替换前面的元素，后续元素 profit 不占优，因此需要 category 增加，同时 category 增加 x 个可能不是最优、但增加 x+1 个是最优
        for (int i = k; i < n; i++) {
            int profit = items[i][0];
            int category = items[i][1];

            // 注意先判断可替换的是否为空，没有出现过就替换前面栈顶的最小重复的 profit 元素，同时由于 profit 降序，因此这一定是没出现的最大 profit
            if (!duplicate.isEmpty() && categorySet.add(category)) {
                totalProfit -= duplicate.pollFirst();
                totalProfit += profit;
            }
//            System.out.println(totalProfit + " : " + categorySet);

            maxElegance = Math.max(maxElegance, totalProfit + (long)categorySet.size() * categorySet.size());
        }

        return maxElegance;
    }
}
