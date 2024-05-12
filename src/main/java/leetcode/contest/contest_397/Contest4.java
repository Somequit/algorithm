package leetcode.contest.contest_397;

import java.util.*;
/**
 */
public class Contest4 {
    private int minScore = 0;
    private int[] res;

    /**
     * 由于 score 为循环计算，因此结果数组允许循环左右移动，选择以 0 为开头字典序最小
     * 结果如果允许为 0 则一定不能出现 nums[i] = i
     */
    public int[] findPermutation(int[] nums) {
        int n = nums.length;

        this.minScore = 0;
        this.res = new int[n];

        for (int i = 1; i < n; i++) {
            this.res[i] = i;
            minScore += Math.abs(res[i - 1] - nums[res[i]]);
        }
        minScore += Math.abs(res[n - 1] - nums[res[0]]);
//        System.out.println(minSub);

        dfs(1, n, 0, new int[n], new boolean[n], nums);

        return res;
    }

    private void dfs(int curIndex, int n, int curScore, int[] curRes, boolean[] vis, int[] nums) {
        if (curScore >= this.minScore) {
            return;
        }

        if (curIndex == n) {
            curScore += Math.abs(curRes[n - 1] - nums[curRes[0]]);

            if (curScore < this.minScore) {
                this.minScore = curScore;

                for (int i = 0; i < n; i++) {
                    this.res[i] = curRes[i];
                }
            }
            return;
        }

        for (int i = 1; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;

                curRes[curIndex] = i;
                dfs(curIndex + 1, n, curScore + Math.abs(curRes[curIndex - 1] - nums[curRes[curIndex]]), curRes, vis, nums);

                vis[i] = false;
            }
        }
    }
}
