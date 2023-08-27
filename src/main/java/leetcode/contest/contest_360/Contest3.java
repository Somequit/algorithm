package leetcode.contest.contest_360;


import utils.AlgorithmUtils;

import java.util.*;

/**
 *
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            List<Integer> nums = AlgorithmUtils.systemInList();
            int target = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, target);
            System.out.println(res);
        }

    }

    public int solution(List<Integer> nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 31; i++) {
            map.put(1 << i, i);
        }
//        System.out.println(map);

        long total = 0;
        int[] bitCount = new int[100];
        for (Integer num : nums) {
            total += num;
            bitCount[map.get(num)]++;
        }
//        System.out.println(Arrays.toString(bitCount));
        if (total < target) {
            return -1;
        }

        int res = 0;
        int pos = -1;
        for (int i = 0; i < 31; i++) {

            if (pos != -1 && bitCount[i] > 0) {
                bitCount[i]--;
                res += i - pos;
                pos = -1;
            }

            if ((target & (1 << i)) > 0) {
//                System.out.println(target + " : " + i + " : " + (target & (1 << i)));

                if (bitCount[i] > 0) {
                    bitCount[i]--;

                } else if (pos == -1) {
                    pos = i;
                }

            }

            bitCount[i + 1] += bitCount[i] / 2;

//            System.out.println(res + " : " + pos + " : " + Arrays.toString(bitCount));
        }

        return res;
    }

}
