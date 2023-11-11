package leetcode.brushQuestions;

import utils.AlgorithmUtils;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * @author gusixue
 * @date 2023/3/22
 */
public class MinDistance {


    public static void main(String[] args) {
        MinDistance minDistance = new MinDistance();

        while (true) {
            String word1 = AlgorithmUtils.systemInString();
            String word2 = AlgorithmUtils.systemInString();

            int res = solution(word1, word2);
            System.out.println(res);
        }
    }

    /**
     * 考虑一个 dp[i][j]，代表 word1 前 i 个元素，变成 word2 前 j 个元素、的最少操作次数，
     * 我们知道如果 dp[0][j] 结果就是 j（添加 word2 个元素即可），dp[i][0] 结果解释 i（删除 word1 个元素即可），
     * 当 i、j 均大于 0 时，如果 word1 第 i 个元素等于 word2 第 j 个元素：dp[i][j] = min(dp[i-1][j-1], dp[i-1][j]+1, dp[i][j-1]+1)
     *     可看做他们从 i-1、j-1 个元素不变化来的，
     *     或者从 i-1、j 个元素删除一个字符变化的，即先将前 i-1 个元素变成 j 个元素，然后删除第 i 个元素，
     *     或者从 i、j-1 个元素添加一个字符变化的，即先将前 i 个元素变成 j-1 个元素，然后添加第 j 个元素，
     * 如果 word1 第 i 个元素不等于 word2 第 j 个元素：dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
     *     可看做他们从 i-1、j-1 个元素变化最后一个字符来的，即先将前 i-1 个元素变成 j-1 个元素，然后改变第 i 个元素，
     *     或者从 i-1、j 个元素删除一个字符变化的，即先将前 i-1 个元素变成 j 个元素，然后删除第 i 个元素，
     *     或者从 i、j-1 个元素添加一个字符变化的，即先将前 i 个元素变成 j-1 个元素，然后添加第 j 个元素，
     * 结果就是 dp[word1.len][word2.len]
     */
    private static int solution(String word1, String word2) {
        // 判空
        if (word1 == null && word2 == null) {
            return 0;
        } else if (word1 == null) {
            return word2.length();
        } else if (word2 == null) {
            return word1.length();
        }

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // 初始化
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j], dp[i][j + 1] + 1), dp[i + 1][j] + 1);
                } else {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j], dp[i][j + 1]), dp[i + 1][j]) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }

}
