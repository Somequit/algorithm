package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 * 1 <= strs.length <= 10 ^ 4
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 * @date 2023/9/13
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();

        while (true) {
            String[] strs = AlgorithmUtils.systemInArrayString();

            List<List<String>> res = groupAnagrams.solution(strs);
            System.out.println(res);
        }
    }

    public List<List<String>> solution(String[] strs) {
        // 判空
        if (strs == null || strs.length <= 0) {
            return new ArrayList<>();
        }

        List<List<String>> res = new ArrayList<>();
        // 每个字符串按照字典序排序后放入 HashMap 的 key，value 存原字符串
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String str : strs) {
            String anagram = sortStr(str);
//            System.out.println(anagram);

            if (!groupMap.containsKey(anagram)) {
                groupMap.put(anagram, new ArrayList<>());
                // HashMap 同一个 key 就是字母异位词
                res.add(groupMap.get(anagram));
            }
            List<String> groupList = groupMap.get(anagram);

            groupList.add(str);
        }

        return res;
    }

    /**
     * 字符串按照字典序排序
     */
    private String sortStr(String str) {
        int[] charCount = new int[26];
        int len = str.length();
        for (int i = 0; i < len; i++) {
            charCount[str.charAt(i) - 'a']++;
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);

            while (charCount[i] > 0) {
                stringBuffer.append(c);
                charCount[i]--;
            }
        }

        return stringBuffer.toString();
    }
}
