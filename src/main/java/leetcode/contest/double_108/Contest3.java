package leetcode.contest.double_108;

import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            String s = AlgorithmUtils.systemInString();

            int res = contest.solution(s);
            System.out.println(res);
        }

    }

    private int solution(String s) {
        if (s.charAt(0) == '0') {
            return -1;
        }

        Set<Integer> beautifulSet = new HashSet<>();
        int beautifulNum = 1;
        for (int i = 0; i < 7; i++) {
            beautifulSet.add(beautifulNum);
            beautifulNum *= 5;
//            System.out.println(Integer.toBinaryString(beautifulNum));
        }

        int[] divide = new int[s.length()];
        int res = dfs(s, 0, s.length(), divide, 0, beautifulSet);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int dfs(String s, int index, int length, int[] divide, int divideNum, Set<Integer> beautifulSet) {
        if (index >= length - 1) {
//            System.out.println(Arrays.toString(divide));
            if (judgement(s, divide, beautifulSet)) {
                return divideNum + 1;
            }
            return Integer.MAX_VALUE;
        }

        int res = Integer.MAX_VALUE;
        if (s.charAt(index + 1) == '0') {
            // 不划分
            res = dfs(s, index + 1, length, divide, divideNum, beautifulSet);

        } else {
            // 不划分
            res = Math.min(res, dfs(s, index + 1, length, divide, divideNum, beautifulSet));

            // 划分
            divide[index] = 1;
            res = Math.min(res, dfs(s, index + 1, length, divide, divideNum + 1, beautifulSet));
            divide[index] = 0;

        }

        return res;
    }

    private boolean judgement(String s, int[] divide, Set<Integer> beautifulSet) {
        int now = 0;
        int pow = 0;
        for (int i = divide.length - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                now += 1 << pow;
            }
            pow++;

            if (i == 0 || divide[i - 1] == 1) {
//                System.out.print(now + " ");
                if (!beautifulSet.contains(now)) {
                    return false;
                }
                now = 0;
                pow = 0;
            }

        }
//        System.out.println();

        return true;
    }

}
