package leetcode.contest.contest_337;


import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @date 2023/3/19
 */
public class Contest3_DP {

    public static void main(String[] args) {
        Contest3_DP contest = new Contest3_DP();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, k);
            System.out.println(res);
        }

    }

    /**
     * 数组先升序排序，然后遍历、将相同元素以及连续相差 k 的元素放在一起，元素通过 HashMap 整理成 g 组，
     *     HashMap 的 key 存每组最大的元素，value 存此组所有元素，
     *     遍历元素时，查询等于当前元素、存在则加入该元素，查询小于 k 的元素、存在则加入该元素更新 key，都不存在新建组
     * 然后将 g 组排列在一起（无关顺序），此时相同元素全排在一起，
     * 比任意元素大 k 元素要么不存在、要么一定排在去除它相同元素的后一个
     * 比如：k 为 2，数组可能组成：2 2 4 4 4 1 1 3 3 5 9
     * 然后模拟 01 背包，设计一个 dp[i][j]，i 代表 [0, i] 这些元素，j 仅有 0-不取当前元素、1-取当前元素，
     * 初始化 dp[0][0] == 0，dp[0][1] == 1，递推方程式 dp[i][0] = dp[i-1][0] + dp[i-1][1]，dp[i][1] 需要分类讨论：
     *     为了方便，设计一个 s 数组，代表当前元素前面有多少个与其相同，含当前元素
     *     再设计一个 prefixDp1 数组，代表 dp[i][1] 的前缀和
     *     i 比 i-1 元素大 k，i-1 与前面相同的都不选：dp[i][1] = 1 + dp[i-1-s[i-1]][0] + dp[i-1-s[i-1]][1]，i-1-s[i-1] 小于 0 则不需要
     *     i 和 i-1 元素相同，可能 i-1 前面存在比 i 小 k 的元素：
     *         如果相同元素前面没有数据了，即 i-s[i] 小于 0：dp[i][1] = 1 + dp[i-1][0] + dp[i-1][1]
     *         如果 i 比 i-s[i] 大 k，先选任意与 i 相同的、然后与上一条一致跳过 i-1 及相同：dp[i][1] = 1 + (dp[i - s[i] + 1][1] + ... + dp[i - 1][1]) + (dp[i - s[i] + 1][1] - 1)
     *         否则随意选：dp[i][1] = 1 + dp[i-1][0] + dp[i-1][1]
     *     否则随意选：dp[i][1] = 1 + dp[i-1][0] + dp[i-1][1]
     */
    private int solution(int[] nums, int k) {
        // 数据比较大则结果需要对大质数取模
        int mod = 1_000_000_007;
        int len = nums.length;

        // 相同元素全排在一起，比任意元素大 k 元素要么不存在、要么一定排在去除它相同元素的后一个
        int[] groupNums = groupKNums(nums, k);

        int[] same = new int[len];
        same[0] = 1;
        int[][] dp = new int[len][2];
        dp[0][1] = 1;
        int[] prefixDp1 = new int[len];
        prefixDp1[0] = 1;

        for (int i = 1; i < groupNums.length; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1])%mod;

            if (groupNums[i] == groupNums[i - 1]) {
                same[i] = same[i - 1] + 1;
            } else {
                same[i] = 1;
            }
//            System.out.print(groupNums[i] + " ");

            if (groupNums[i] - groupNums[i - 1] == k) {
                int difi1 = i - 1 - same[i - 1];

                if (difi1 < 0) {
//                    System.out.print("01 ");
                    dp[i][1] = 1;

                } else {
//                    System.out.print("02 ");
                    dp[i][1] = 1 + dp[difi1][0] + dp[difi1][1];
                }

            } else if (groupNums[i] == groupNums[i - 1]) {
                int difi = i - same[i];

                if (difi < 0) {
//                    System.out.print("03 ");
                    dp[i][1] = 1 + dp[i - 1][0] + dp[i - 1][1];
                } else if (groupNums[i] - groupNums[difi] == k) {
//                    System.out.print("04 ");
                    dp[i][1] = 1 + (prefixDp1[i - 1] - prefixDp1[difi] + mod) % mod + (dp[difi + 1][1] - 1);
                } else {
//                    System.out.print("05 ");
                    dp[i][1] = 1 + dp[i - 1][0] + dp[i - 1][1];
                }

            } else {
//                System.out.print("06 ");
                dp[i][1] = 1 + dp[i - 1][0] + dp[i - 1][1];
            }
            dp[i][1] %= mod;
//            System.out.println(dp[i][1]);
            prefixDp1[i] = (prefixDp1[i - 1] + dp[i][1]) % mod;
        }
//        System.out.println(Arrays.toString(same));
//        System.out.println(Arrays.toString(prefixDp1));
//        for (int i = 0; i < dp.length; i++) {
//            System.out.println(dp[i][0] + ":" + dp[i][1]);
//        }

        return (dp[len - 1][0] + dp[len - 1][1]) % mod;
    }

    private int[] groupKNums(int[] nums, int k) {
        // 升序排序
        Arrays.sort(nums);

        // 按照 k 拆分 g 组到 HasaMap
        Map<Integer, List<Integer>> groupMap = new HashMap<>((nums.length << 2) / 3);
        for (int num : nums) {
            List<Integer> numList = groupMap.getOrDefault(num, null);
            if (numList != null) {
                numList.add(num);
                continue;
            }

            int preNum = num - k;
            List<Integer> preNumList = groupMap.getOrDefault(num - k, null);
            if (preNumList != null) {
                preNumList.add(num);
                groupMap.put(num, preNumList);
                groupMap.put(preNum, null);
                continue;
            }

            numList = new ArrayList<>();
            numList.add(num);
            groupMap.put(num, numList);

        }
//        groupMap.forEach((key, val) -> {System.out.println(key + ":" + val);});

        int[] groupNums = new int[nums.length];
        int index = 0;
        // g 组排列在一起
        for (Map.Entry<Integer, List<Integer>> entry : groupMap.entrySet()) {
            List<Integer> groupList = entry.getValue();
            if (groupList == null) {
                continue;
            }

            for (Integer groupNum : groupList) {
                groupNums[index] = groupNum;
                index++;
            }
        }
//        System.out.println(Arrays.toString(groupNums));

        return groupNums;
    }


}
