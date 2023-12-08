package leetcode.brushQuestions.medium;

import utils.AlgorithmUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 2048. 下一个更大的数值平衡数
 * @date 2023/12/9
 */
public class NextBeautifulNumber {

    public static void main(String[] args) {
        NextBeautifulNumber nextBeautifulNumber = new NextBeautifulNumber();
        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();

            int res = nextBeautifulNumber.nextBeautifulNumber(n);
            System.out.println(res);
        }
    }

    /**
     * 暴力 + 二分/TreeSet：暴力找到 [0,10 ^ 6] 中 数值平衡数，然后加上大于 10 ^ 6 的最小的 数值平衡数 1224444（暴力求出），
     * 放入 TreeSet 中搜索即可（或者二分）
     */
    public int nextBeautifulNumber(int n) {
        System.out.println(numSet.size());
        return numSet.higher(n);
    }


    private static final TreeSet<Integer> numSet = new TreeSet<>();

    static {
        for (int i = 1; i <= 1_000_000; i++) {
            if (checkBeautifulNumber(i)) {
                numSet.add(i);
            }
        }
        for (int i = 1_000_001; ; i++) {
            if (checkBeautifulNumber(i)) {
                numSet.add(i);
                break;
            }
        }
    }
    /**
     * num 是否为 数值平衡数
     */
    private static boolean checkBeautifulNumber(int num) {
        int[] count = new int[10];
        while (num > 0) {
            count[num % 10]++;
            num /= 10;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0 && count[i] != i) {
                return false;
            }
        }
        return true;
    }

}
