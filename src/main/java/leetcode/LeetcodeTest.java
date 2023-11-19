package leetcode;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 854. 相似度为 K 的字符串
 * 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
 * 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1 和 s2  只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母
 * s2 是 s1 的一个字母异位词
 * @date 2023/6/18
 */
public class LeetcodeTest {

    public static void main(String[] args) {
        LeetcodeTest leetcodeTest = new LeetcodeTest();
        while (true) {
            String s1 = AlgorithmUtils.systemInString();
            String s2 = AlgorithmUtils.systemInString();

            int res = leetcodeTest.solution(s1, s2);
            System.out.println(res);
        }
    }

    private int solution(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }

        int n = s1.length();
        char[] char2 = s2.toCharArray();

        Map<String, Integer> visMap = new HashMap<>();
        visMap.put(s1, 0);
        Deque<String> queue = new LinkedList<>();
        queue.offer(s1);

        while (!queue.isEmpty()) {
            String curS = queue.poll();
            char[] charCur = curS.toCharArray();
//            System.out.println("curS:" + curS);

            for (int i = n - 1; i >= 0; i--) {
                if (char2[i] != charCur[i]) {

                    for (int j = i - 1; j >= 0; j--) {
                        if (char2[i] == charCur[j]) {
                            swap(charCur, i, j);
                            String newS = new String(charCur);
                            swap(charCur, i, j);
//                            System.out.println(i + " : " + j);
//                            System.out.println("newS:" + newS);

                            if (s2.equals(newS)) {
                                return visMap.get(curS) + 1;

                            } else if (!visMap.containsKey(newS)) {
                                visMap.put(newS, visMap.get(curS) + 1);
                                queue.offer(newS);
                            }

                        }
                    }
                    break;
                }
            }


        }

        return -1;
    }

    private void swap(char[] charCur, int i, int j) {
        char temp = charCur[i];
        charCur[i] = charCur[j];
        charCur[j] = temp;
    }
}
