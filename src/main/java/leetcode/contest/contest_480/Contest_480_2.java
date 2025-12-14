package leetcode.contest.contest_480;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/14 11:49 上午
 */
public class Contest_480_2 {
    public String reverseWords(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        int vowelCnt = -1;
        for (String word : s.split(" ")) {
            if (vowelCnt == -1) {
                vowelCnt = getVowelCnt(word);
                stringBuilder.append(word);

            } else {
                stringBuilder.append(" ");
                if (getVowelCnt(word) == vowelCnt) {
                    stringBuilder.append(new StringBuilder(word).reverse());

                } else{
                    stringBuilder.append(word);
                }
            }

        }

        return stringBuilder.toString();
    }

    private int getVowelCnt(String word) {
        int res = 0;
        for (char c : word.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                res++;
            }
        }

        return res;
    }
}
