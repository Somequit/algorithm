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
