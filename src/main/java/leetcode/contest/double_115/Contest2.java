package leetcode.contest.double_115;


import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private List<String> solution(int n, String[] words, int[] groups) {
        int[] dp = new int[n];
        int[] prev = new int[n];
        dp[0] = 1;
        prev[0] = -1;
        int maxDp = 1;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            int prevI = -1;
            int maxNum = 0;

            for (int j = i - 1; j >= 0; j--) {
                if (groups[j] != groups[i]) {
                    if (maxNum < dp[j]) {
                        maxNum = dp[j];
                        prevI = j;
                    }
                }
            }

            dp[i] = maxNum + 1;
            prev[i] = prevI;

            if (maxDp < dp[i]) {
                maxDp = dp[i];
                maxIndex = i;
            }
        }

        List<String> res = new ArrayList<>();
        for (int prevI = maxIndex; prevI != -1; prevI = prev[prevI]) {
            res.add(words[prevI]);
        }
        for (int i = 0; i < res.size() / 2; i++) {
            String temp = res.get(i);
            res.set(i, res.get(res.size() - i - 1));
            res.set(res.size() - i - 1, temp);
        }

        return res;
    }



}
