package leetcode.contest.double_126;

import javafx.util.Pair;

import java.util.*;
/**
 * 100249. 替换字符串中的问号使分数最小
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * Java + 排序 + 贪心
     *
     * 第一步：
     * 首先想到的是 dp，但是 dp 需要以每个小写字母个数作为 key，因此时间复杂度是 26 的次方级，
     * 其次思考分数与字母位置无关，因为分数可看做**相同字母的对数**，
     * 因此每个 ? 替换的是当前（字母个数最小的、字典序最小的），
     * 最后还可以升序排序 ? 替换的字符，让字符序最小
     *
     * 第二步：
     * 替换 ? 前、先计算已有字符的个数，
     * 接着找到每个 ? 找到已有字母个数最小的、字典序最小的，
     * 最后更新已有字母（即 ? 替换成的字母）
     *
     * 第三步：
     * 由于分数与字母位置无关，
     * 因此最后升序排序 ? 替换的字符，可以让字符序最小
     * 例如：27 个 ?，就是 a-z 各 1 个，再 a 1 个，但是结果为：aabcd...z
     *
     * 时间复杂度：O（26*n），空间复杂度：O（26+n）
     *
     */
    public String minimizeStringValue(String s) {
        // 已有字符的个数
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (c != '?') {
                count[c - 'a']++;
            }
        }

        // ? 需要替换的小写字母个数
        int[] addCharCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '?') {
                // 已有字母个数最小的、字典序最小的
                int minVal = Integer.MAX_VALUE;
                int minCharIndex = 0;
                for (int j = 0; j < 26; j++) {
                    if (minVal > count[j]) {
                        minVal = count[j];
                        minCharIndex = j;
                    }
                }
                addCharCount[minCharIndex]++;

                // ? 已确定为 minCharIndex+'a'
                count[minCharIndex]++;
            }

        }

        // 按照字典序从小到大更新 ? 成需要替换的字母
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '?') {
                // 可用双指针（不过时间复杂度不以此处）
                for (int j = 0; j < 26; j++) {
                    if (addCharCount[j] > 0) {
                        c = (char)(j + 'a');
                        addCharCount[j]--;
                        break;
                    }
                }
            }

            res.append(c);
        }

        return res.toString();
    }


}
