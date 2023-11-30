package leetcode.brushQuestions.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 1657. 确定两个字符串是否接近
 * @date 2023/11/30
 */
public class CloseStrings {

    public static void main(String[] args) {
        CloseStrings closeStrings = new CloseStrings();

        while (true) {
            String word1 = AlgorithmUtils.systemInString();
            String word2 = AlgorithmUtils.systemInString();

            boolean res = closeStrings.solution(word1, word2);
            System.out.println(res);
        }

    }

    /**
     * 操作一位置可以任意交换，因此可以统计每种字符个数，
     * 操作二俩现有字符互相变化，因此现有字符种类必须全部相同，尽管每种个数可以不同、但是排序后个数必须相同
     * @return
     */
    public boolean solution(String word1, String word2) {
        int[] wordCount1 = new int[26];
        for (char word : word1.toCharArray()) {
            wordCount1[word - 'a']++;
        }

        int[] wordCount2 = new int[26];
        for (char word : word2.toCharArray()) {
            wordCount2[word - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if ((wordCount1[i] > 0 && wordCount2[i] == 0) || (wordCount1[i] == 0 && wordCount2[i] > 0)) {
                return false;
            }
        }

        Arrays.sort(wordCount1);
        Arrays.sort(wordCount2);
        for (int i = 0; i < 26; i++) {
            if (wordCount1[i] != wordCount2[i]) {
                return false;
            }
        }
        return true;
    }
}
