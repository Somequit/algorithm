package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的"最长子串"的长度。
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        while (true) {
            String str = AlgorithmUtils.systemInString();
            System.out.println(lengthOfLongestSubstring(str));
            System.out.println(lengthOfLongestSubstring2(str));
        }
    }

    /**
     * 滑动窗口（Sliding Window），只有ASCII码、因此可以使用 int[256] 类似位图记录字符
     * 先设置虚拟的左边缘设置为-1，然后右边缘右移，判断是否再次出现重复字符，出现则左边缘右移直到没有重复字符
     * @return
     */
    public static int lengthOfLongestSubstring(String str) {
        int result = 0;

        if(str == null || str.length() == 0){
            return result;
        }

        int leftIndex = -1;
        int[] bitMap = new int[256];
        Arrays.fill(bitMap, -1);

        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            int newIndex = bitMap[c];
            // 重复字符、窗口左边缘右移
            leftIndex = Math.max(newIndex, leftIndex);

            result = Math.max(result, i-leftIndex);
            bitMap[c] = i;
        }
        return result;
    }

    /**
     * 另一种滑动窗口（其实是双指针）的实现，以每一个字符为开始/左边缘，那么滑动窗口右边缘要么不变要么右移
     * 因此只需要记录、某个字符是否出现过，如果出现过则左边缘右移、否则右边缘右移
     */
    public static int lengthOfLongestSubstring2 (String str) {
        int result = 0;
        byte[] bitMap = new byte[256];
        int leftIndex = 0;
        int rightIndex = 0;

        while(rightIndex < str.length()) {
            char c = str.charAt(rightIndex);
            if (bitMap[c] == 0) {
                bitMap[c] = 1;
                rightIndex++;
            } else {
                bitMap[str.charAt(leftIndex)] = 0;
                leftIndex++;
            }
            result = Math.max(result, rightIndex - leftIndex);
        }
        return result;
    }
}
