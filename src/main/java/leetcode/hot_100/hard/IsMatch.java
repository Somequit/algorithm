package leetcode.hot_100.hard;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * （理解应为：'X*' 匹配零个或多个 X）
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 1 <= s.length <= 20
 * 1 <= p.length <= 20
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 * @date 2023/11/10
 */
public class IsMatch {

    public static void main(String[] args) {
        IsMatch isMatch = new IsMatch();
        while (true) {
            String s = AlgorithmUtils.systemInString();
            String p = AlgorithmUtils.systemInString();

            boolean res = isMatch.solution(s, p);
            System.out.println(res);
        }
    }

    /**
     * 动态规划：
     * 定义状态（子问题）：dp[i+1][j+1] 代表 s 字符串 [0, i] 与 p 字符串 [0,j] 是否匹配，注意 dp 数组中 0 在 s与p 中不具有含义、仅方便处理
     * 初始化：dp 所有元素 false，dp[0][0]=true，如果 p 形如 X*X*X*...，即 p[2*i+1] 为 *，可以将其全部置为零个就是空，此时 dp[0][2*i+2]=true，直到 p[2*i+1] 非 *，
     * 转移方程式：i>0、j>0 时 dp[i][j] = (dp[i-1][j-1] && (p[j-1]=='.' || s[i-1]==p[j-1]))，如果 j>1、p[j-1]=='*'时 还可以使 p[j-2]p[j-1] 统一处理成零到多个，
     *     零个：dp[i][j] = dp[i][j] || dp[i][j-2]，一个：dp[i][j] = dp[i][j] || dp[i][j-1]，
     *     超过一个（多个 s 的字符都相同或者 p 多个 . 随意匹配）：dp[i][j] = dp[i][j] || (dp[i-1][j] && (p[j-2]=='.' || s[i-1] == p[j-2]))
     *
     */
    private boolean solution(String s, String p) {
        // 判空
        if (s == null || p == null || s.length() == 0 || p.length() == 0) {
            return true;
        }

        // 定义状态（子问题）：dp[i+1][j+1] 代表 s 字符串 [0, i] 与 p 字符串 [0,j] 是否匹配，注意 dp 数组中 0 在 s与p 中不具有含义、仅方便处理
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        // 初始化：dp 所有元素 false，dp[0][0]=true
        dp[0][0] = true;
        // 如果 p 形如 X*X*X*...，即 p[2*i+1] 为 *，可以将其全部置为零个就是空，此时 dp[0][2*i+2]=true，直到 p[2*i+1] 非 *，
        for (int i = 1; i < m; i+=2) {
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = true;

            } else {
                break;
            }
        }

        // 转移方程式：i>0、j>0 时 dp[i][j] = (dp[i-1][j-1] && (p[j-1]=='.' || s[i-1]==p[j-1]))，如果 j>1、p[j-1]=='*'时 还可以使 p[j-2]p[j-1] 统一处理成零到多个，
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j - 1] && (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1));

                if (j > 1 && p.charAt(j - 1) == '*' && !dp[i][j]) {
                    // 零个：dp[i][j] = dp[i][j] || dp[i][j-2]，一个：dp[i][j] = dp[i][j] || dp[i][j-1]，
                    dp[i][j] = dp[i][j - 2] || dp[i][j - 1];

                    // 超过一个（多个 s 的字符都相同或者 p 多个 . 随意匹配）：dp[i][j] = dp[i][j] || (dp[i-1][j] && (p[j-2]=='.' || s[i-1] == p[j-2]))
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && (p.charAt(j - 2) == '.' || s.charAt(i - 1) == p.charAt(j - 2)));
                }
            }
        }

        return dp[n][m];
    }

}
