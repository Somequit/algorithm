package leetcode.brushQuestions.easy;

import java.util.*;

/**
 * @author gusixue
 * @description 2273. 移除字母异位词后的结果数组
 * @date 2025/10/13 1:22 上午
 */
public class RemoveAnagrams {

    /**
     * 每个 words[i] 排序后与 words[i-1] 比较
     */
    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        res.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            char[] chars1 = words[i - 1].toCharArray();
            Arrays.sort(chars1);
            char[] chars2 = words[i].toCharArray();
            Arrays.sort(chars2);

            if (!Arrays.toString(chars1).equals(Arrays.toString(chars2))) {
                res.add(words[i]);
            }
        }

        return res;
    }
}
