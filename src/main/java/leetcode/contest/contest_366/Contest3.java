package leetcode.contest.contest_366;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/10/08
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int solution2(String s1, String s2, int x) {
        List<Integer> differenceList = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                differenceList.add(i);
            }
        }

        if (differenceList.size() % 2 == 1) {
            return -1;

        } else if (differenceList.size() == 0) {
            return 0;

        } else {
            int n = differenceList.size();
            int inf = 250000;
            // 前 i 个元素有 j=0/1 个第二步操作需要的最少代价
            int[][] dp = new int[n][2];
            dp[0][0] = inf;
            dp[0][1] = x;
            dp[1][0] = Math.min(dp[0][1], differenceList.get(1) - differenceList.get(0));
            dp[1][1] = inf;

            for (int i = 2; i < n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 2][0] + differenceList.get(i) - differenceList.get(i - 1));
                dp[i][1] = Math.min(dp[i - 1][0] + x, dp[i - 2][1] + differenceList.get(i) - differenceList.get(i - 1));
            }

            return dp[n - 1][0];
        }
    }

    /**
     * @return
     */
    private int solution(String s1, String s2, int x) {
        List<Integer> differenceList = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                differenceList.add(i);
            }
        }

        if (differenceList.size() % 2 == 1) {
            return -1;

        } else if (differenceList.size() == 0) {
            return 0;

        } else {
            int n = differenceList.size();
            // [i,j) 进行匹配最少需要多少代价，j-i 一定为大于1的偶数
            int[][] dp = new int[n][n + 1];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            return memorizeSearch(dp, 0, n, differenceList, x);
        }
    }

    private int memorizeSearch(int[][] dp, int left, int right, List<Integer> differenceList, int x) {
        if (right - left == 0) {
            return 0;
        }
        if (right - left == 2) {
            dp[left][right] = operations(left, right, differenceList, x);
            return dp[left][right];
        }
        if (dp[left][right] != Integer.MAX_VALUE) {
            return dp[left][right];
        }

        for (int i = left + 2; i <= right; i+=2) {
            dp[left][right] = Math.min(dp[left][right], operations(left, i, differenceList, x)
                    + memorizeSearch(dp, left + 1, i - 1, differenceList, x)
                    + memorizeSearch(dp, i, right, differenceList, x));
        }

        return dp[left][right];
    }

    private int operations(int left, int right, List<Integer> differenceList, int x) {
        return Math.min(differenceList.get(right - 1) - differenceList.get(left) , x);
    }


}
