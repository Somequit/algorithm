package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

/**
 * @author gusixue
 * @description
 * 647. 回文子串
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 * @date 2023/7/1
 */
public class CountSubstrings {

    public static void main(String[] args) {
        CountSubstrings countSubstrings = new CountSubstrings();
        while (true) {
            String s = AlgorithmUtils.systemInString();
            int res = countSubstrings.solution(s);
            System.out.println(res);
        }
    }

    /**
     * 中心扩展：以每个元素为中心向两边扩展，还可以两个元素之间的间隙为中心，暴力求回文个数，中心个数为 2*len-1，
     * 分为两种情况：（i,i）（i-1,i+1）（i-2,i+2）与（i,i+1）（i-1,i+2）（i-2,i+3）
     * 时间复杂度：O（n*n），空间复杂度：O（1）
     */
    private int solution(String s) {
        // 判空
        if (s == null || s.length() <= 0) {
            return 0;
        }

        int res = 0;

        int len = s.length();
        // 以每个元素为中心向两边扩展，还可以两个元素之间的间隙为中心，暴力求回文个数，中心个数为 2*len-1
        int centerNum = 2 * len - 1;
        for (int center = 0; center < centerNum; center++) {
            int i = center >> 1;

            // 两种情况：（i,i）（i-1,i+1）（i-2,i+2）与（i,i+1）（i-1,i+2）（i-2,i+3）
            int left = i;
            int right = i + (center & 1);
//            System.out.println(left + " : " + right);
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                res++;
                left--;
                right++;
            }
        }

        return res;
    }

}
