package template;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description KMP 字符串匹配算法
 * @date 2024/1/21
 */
public class KMP {

    /**
     * 求 next 数组
     * next 数组：匹配串当前下标的为结尾的后缀、最大能与从头开始的前缀匹配的个数
     * @param patternStr 匹配串（小串）
     */
    private static int[] getNext(StringBuilder patternStr) {
        int[] next = new int[patternStr.length()];

        int p = 0;
        for (int i = 1; i < patternStr.length(); i++) {
            char patCharI = patternStr.charAt(i);
            while (p > 0 && patternStr.charAt(p) != patCharI) {
                /**
                 * 核心跳转：（p - 1）为结尾的 next 个字符、与从头开始的 next 个字符与相匹配、也与 （i - 1）为结尾的 next 个字符相匹配
                 * 举例子：
                 * 下标：    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
                 * 数组：    A A A A B A A B A A A  A  B  A  A  A
                 * next数组：0 1 2 3 0 1 2 0 1 2 3  4  5  6  7  3
                 * next[15]可以看出来，此时 i 为 15，p 为 7
                 */
                p = next[p - 1];
            }

            if (patCharI == patternStr.charAt(p)) {
                p++;
            }

            next[i] = p;
        }
        return next;
    }

    /**
     * kmp 根据 next 数组 进行匹配，直到文本串匹配结束
     * 返回文本串匹配上所有初始位置
     * @param matchStr 文本串（大串）
     * @param patternStr 匹配串（小串）
     * @param next next数组
     * @return
     */
    public List<Integer> kmpMatchTotal(String matchStr, String patternStr, int[] next) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0, j = 0; i < matchStr.length(); i++) {
            while (j > 0 && matchStr.charAt(i) != patternStr.charAt(j)){
                j = next[j - 1];
            }


            if (matchStr.charAt(i) == patternStr.charAt(j)) {
                j++;
            }

            // 匹配成功
            if (j == patternStr.length()) {
                res.add(i + 1 - patternStr.length());
                // 继续寻找下一个
                j = next[j - 1];
            }
        }
        // 匹配结束
        return res;
    }
}
