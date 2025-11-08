package leetcode.brushQuestions.rating_2200;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 3306. 元音辅音字符串计数 II
 * @date 2025/11/3 11:48 上午
 */
public class Score_2200_CountOfSubstrings {

    /**
     * 从 i 开始最近l到最远r(不含)可到某个位置，即 [i，l）符合要求的最短字符串，[i，r）符合要求的最长字符串
     * 也叫三指针滑窗
     */
    public long countOfSubstrings(String word, int k) {
        Map<Character, Integer> mapVowel = new HashMap<>();
        mapVowel.put('a', 0);
        mapVowel.put('e', 1);
        mapVowel.put('i', 2);
        mapVowel.put('o', 3);
        mapVowel.put('u', 4);
        int[] cntVowel = new int[5];
        int kindVowel = 0;
        int cntConsonant = 0;

        long res = 0;

        // 从 i 开始最近到最远(不含)可到某个位置
        for (int i = -1, l = 0, r = -1; i < word.length(); i++) {
            // 删除 word[i]
            if (i >= 0) {
                char wordI = word.charAt(i);

                if (mapVowel.containsKey(wordI)) {
                    cntVowel[mapVowel.get(wordI)]--;
                    if (cntVowel[mapVowel.get(wordI)] == 0) {
                        kindVowel--;
                    }

                } else {
                    cntConsonant--;
                }
            }
//            System.out.println(i + " : " + kindVowel + " : " + cntConsonant);

            // 最近(不含)可到某个位置
            while (l < word.length()) {
                // 超过
                if (cntConsonant > k) {
                    break;
                }
                // 符合
                if (cntConsonant == k && kindVowel == 5) {
                    break;
                }

                char wordL = word.charAt(l);

                if (mapVowel.containsKey(wordL)) {
                    cntVowel[mapVowel.get(wordL)]++;
                    if (cntVowel[mapVowel.get(wordL)] == 1) {
                        kindVowel++;
                    }

                } else {
                    cntConsonant++;
                }

                l++;
            }

//            System.out.println(l + " : " + kindVowel + " : " + cntConsonant);

            // 最远(不含)可到某个位置，即最近的位置后面元音字母的个数
            if (cntConsonant == k && kindVowel == 5) {
                // 之前求得复用
                if (r < l) {
                    r = l;
                    while (r < word.length()) {
                        char c = word.charAt(r);

                        if (!mapVowel.containsKey(c)) {
                            break;
                        }

                        r++;
                    }
                }

                res += r - l + 1;
            }

//            System.out.println(r + " : " + kindVowel + " : " + cntConsonant);

        }

        return res;
    }
}
