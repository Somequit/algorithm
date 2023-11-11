package leetcode.hot_100.medium;

import template.Algorithm;
import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gusixue
 * @description
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * 1 <= s.length, p.length <= 3 * 10 ^ 4
 * s 和 p 仅包含小写字母
 * @date 2023/11/8
 */
public class FindAnagrams {

    public static void main(String[] args) {
        FindAnagrams findAnagrams = new FindAnagrams();
        while (true) {
            String s = AlgorithmUtils.systemInString();
            String p = AlgorithmUtils.systemInString();

            List<Integer> res = findAnagrams.solution(s, p);
            System.out.println(res);
        }
    }

    /**
     * 首先必须 p 长度小于等于 s，接着使用 pArr 数组将 p 每个小写字母按照下标、个数存储，将大于 0 的个数存入 count 中方便判断
     * 然后依次从 s 中取 p.length 长度的元素（类似滑动窗口）在 pArr 数组中加/减，过程中如果 pArr 数组加/减到 0 则 count--、如果从 0 加/减到 1/-1 则 count++，
     * 每次判断 count 是否为 0，为空则代表 pArr 数组所有元素都为 0、即此位是 p 的 异位词 的子串
     * 时间复杂度：O(m+n)，空间复杂度：O（26）
     */
    private List<Integer> solution(String s, String p) {
        // 判空 且 必须 p 长度小于等于 s
        if (s == null || p == null || p.length() > s.length()) {
            return new ArrayList<>();
        }

        int pLen = p.length();
        // 暂时只有小写字母，总共 26 个
        int charCount = 26;
        // 按照不同小写字母放入 pArr 数组中
        int[] pArr = new int[charCount];

        // 将大于 0 的个数存入 count 中方便判断
        int count = 0;

        for (int i = 0; i < pLen; i++) {
            int pIndex = doAnagramsToInt(p.charAt(i));
            pArr[pIndex]++;

            if (pArr[pIndex] == 1) {
                count++;
            }
        }


        List<Integer> res = new ArrayList<>();
        int sLen = s.length();
        // 依次从 s 中取 p.length 长度的元素（类似滑动窗口）在 pArr 数组中加/减
        for (int left = 0, right = 0; right < sLen; right++) {

            int sRightIndex = doAnagramsToInt(s.charAt(right));
            pArr[sRightIndex]--;
            // 过程中如果 pArr 数组加/减到 0 则 count--、如果从 0 加/减到 1/-1 则 count++，
            if (pArr[sRightIndex] == 0) {
                count--;

            } else if (pArr[sRightIndex] == -1) {
                count++;
            }

            if (right - left + 1 > pLen) {
                int sLeftIndex = doAnagramsToInt(s.charAt(left));
                pArr[sLeftIndex]++;
                if (pArr[sLeftIndex] == 0) {
                    count--;

                } else if (pArr[sLeftIndex] == 1) {
                    count++;
                }
                left++;
            }

            if (count == 0) {
                res.add(left);
            }
        }

        return res;
    }

    private int doAnagramsToInt(char c) {
        return c - 'a';
    }
}
