package leetcode.medium;

import template.Algorithm;
import utils.AlgorithmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10 ^ 5
 * s 和 t 由英文字母组成
 * @date 2023/11/10
 */
public class MinWindow {

    public static void main(String[] args) {
        MinWindow minWindow = new MinWindow();
        while (true) {
            String s = AlgorithmUtils.systemInString();
            String t = AlgorithmUtils.systemInString();

            String res = minWindow.solution(s, t);
            System.out.println(res);
        }
    }

    /***
     * 滑动窗口：遍历 t 每个字符将字符与个数存入 hashMap 中，然后滑动窗口遍历 s，每次右指针移动一次然后校验当前区间是否包含 t，
     * 包含则更新左指针到最右包含 t 的位置，更新长度与左指针位置然后左指针再右移一位继续循环，不包含则直接继续循环，
     * 校验当前区间是否包含 t：使用一个变量 count 记录 hashMap 中（存在的元素）有多少个元素小于等于 0，如果与 hashMap 长度相等则包含，
     * 每次更新指针时加减在 hashMap 中的元素个数，减到 0 时 count++，加到 1 时 count--
     * 时间复杂度：O(n+m)，空间复杂度：O（m）
     */
    private String solution(String s, String t) {
        // 判空与 s 长度不小于 t 长度
        if (s == null || t == null || s.length() < t.length() || t.length() == 0) {
            return "";
        }

        // 遍历 t 每个字符将字符与个数存入 hashMap 中
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            charCountMap.put(t.charAt(i), charCountMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        // 滑动窗口遍历 s
        int resIndex = -1;
        int resLen = Integer.MAX_VALUE;
        int count = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            char sChar = s.charAt(right);

            // 每次更新指针时加减在 hashMap 中的元素个数，减到 0 时 count++，加到 1 时 count--
            if (charCountMap.containsKey(sChar)) {
                int sCharCount = charCountMap.get(sChar) - 1;
                charCountMap.put(sChar, sCharCount);

                if (sCharCount == 0) {
                    count++;
                }
            }

            // 每次右指针移动一次然后校验当前区间是否包含 t,包含则更新左指针到最右包含 t 的位置，更新长度与左指针位置然后左指针再右移一位继续循环，不包含则直接继续循环，
            if (count != charCountMap.size()) {
                continue;
            }

            while (left < right) {
                char sLeftChar = s.charAt(left);
                if (!charCountMap.containsKey(sLeftChar)) {
                    left++;

                } else if (charCountMap.get(sLeftChar) != 0) {
                    int sLeftCharCount = charCountMap.get(sLeftChar) + 1;
                    charCountMap.put(sLeftChar, sLeftCharCount);
                    left++;

                } else {
                    break;
                }
            }

            if (right - left + 1 < resLen) {
                resLen = right - left + 1;
                resIndex = left;
            }

            int sLeftCharCount = charCountMap.get(s.charAt(left)) + 1;
            charCountMap.put(s.charAt(left), sLeftCharCount);
            count--;
            left++;

        }

        return resIndex == -1 ? "" : s.substring(resIndex, resIndex + resLen);
    }
}
