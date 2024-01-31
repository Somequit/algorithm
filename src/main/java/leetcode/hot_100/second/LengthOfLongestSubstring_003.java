package leetcode.hot_100.second;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * @date 2024/1/31
 */
public class LengthOfLongestSubstring_003 {

    /**
     * Java + 滑动窗口
     *
     * 第 1 步：
     * 首先判空，
     * 然后使用滑动窗口，每次符合要求时校验最大值
     *
     * 第 2 步：
     * 滑动窗口：开左右两个端点，右端点每次向右移动一位，判断左右端点间是否符合要求，符合则继续，不符合则左端点向右滑动到最左满足要求的位置再继续
     */
    public int lengthOfLongestSubstring(String s) {
        // 判空
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 滑动窗口，每次符合要求时校验最大值
        int res = 0;
        Map<Character, Integer> mapCharCount = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            mapCharCount.merge(rightChar, 1, Integer::sum);

            while (mapCharCount.get(rightChar) > 1) {
                char leftChar = s.charAt(left);
                mapCharCount.merge(leftChar, -1, Integer::sum);
                left++;
            }

            res = Math.max(right - left + 1, res);
        }

        return res;
    }
}
