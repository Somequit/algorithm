package leetcode.brushQuestions.hard;

/**
 * @author gusixue
 * @description 1278. 分割回文串 III
 * @date 2025/3/3
 */
public class PalindromePartition {

    /**
     * Java + 划分型 dp
     *
     * 第一步：
     * 计算字符串 [i,j) 最少需要改变多少字符才能成为回文串 strToPal[i][j]
     *
     * 第二步：
     * 初始化 dp[i][j] 代表字符串 [0,i) 划分出 j 个回文串最少需要修改多少字符
     *
     * 第三步：
     * 动态规划态转移方程为 dp[i][j]=min(dp[l][j-1] + strToPal[l][i])
     *
     */
    public int palindromePartition(String s, int k) {
        int len = s.length();
        int[][] strToPal = new int[len + 1][len + 1];
        doStrToPal(s, len, strToPal);

        int[][] dp = new int[len + 1][k + 1];
        initDp(len, strToPal, dp);

        for (int i = 1; i < len + 1; i++) {
            for (int j = 2; j < Math.min(k + 1, i); j++) {
                // 此时 dp[j-1][j-1]=0 即每个元素均为单个字母
                dp[i][j] = dp[j - 1][j - 1] + strToPal[j - 1][i];

                for (int l = j; l < i; l++) {
                    dp[i][j] = Math.min(dp[i][j], dp[l][j - 1] + strToPal[l][i]);
                }

            }
        }

        return dp[len][k];
    }

    private void initDp(int len, int[][] strToPal, int[][] dp) {
        for (int i = 1; i < len + 1; i++) {
            dp[i][1] = strToPal[0][i];
        }
    }

    /**
     * 动态规划方式求出，即 [i,j) = [i+1,j-1) + (s[i]==s[j-1])
     * 注意循环方式
     */
    private void doStrToPal(String s, int len, int[][] strToPal) {
        for (int l = 2; l < len + 1; l++) {
            for (int i = 0; i + l < len + 1; i++) {
                strToPal[i][i + l] = strToPal[i + 1][i + l - 1] + (s.charAt(i) == s.charAt(i + l - 1) ? 0 : 1);
//                System.out.println(i + " " + (i + l) + " = " + strToPal[i][i + l]);
            }
        }
    }
}
