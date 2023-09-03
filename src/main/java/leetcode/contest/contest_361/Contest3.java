package leetcode.contest.contest_361;


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
            int modulo = AlgorithmUtils.systemInNumberInt();
            int k = AlgorithmUtils.systemInNumberInt();

            long res = contest.solution(nums, modulo, k);
            System.out.println(res);
            long res2 = contest.solution2(nums, modulo, k);
            System.out.println(res2);
        }

    }

    private long solution2(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        Map<Integer, Integer> prefixModCountMap = new HashMap<>(n << 1);
        prefixModCountMap.put(0, 1);

        long res = 0;
        long prefixCount = 0L;
        for (Integer num : nums) {
            prefixCount += (num % modulo == k ? 1 : 0);
            prefixCount %= modulo;

            res += prefixModCountMap.getOrDefault((int)((prefixCount - k + modulo) % modulo), 0);
            prefixModCountMap.put((int)prefixCount, prefixModCountMap.getOrDefault((int)prefixCount, 0) + 1);
        }

        return res;
    }

    public long solution(List<Integer> nums, int modulo, int k) {
        int n = nums.size();

        int[] numArrays = new int[n];
        for (int i = 0; i < n; i++) {
            numArrays[i] = (nums.get(i) % modulo == k ? 1 : 0);
        }
//        System.out.println(Arrays.toString(numArrays));

        List<Integer> left = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < n; i++) {
            if (numArrays[i] == 0) {
                count++;
            } else {
                left.add(count);
                count = 1;
            }
        }

        List<Integer> right = new ArrayList<>();
        count = 1;
        for (int i = n-1; i >= 0; i--) {
            if (numArrays[i] == 0) {
                count++;

            } else {
                right.add(count);
                count = 1;
            }
        }
        int len = left.size();
        for (int i = 0; i < len / 2; i++) {
            swap(right, i, len - i - 1);
        }
//        System.out.println(left + " : " + right);

        if (left.size() < k) {
            return 0;
        }

        long res = 0;
        long[] prefix = new long[len];
        if (k > 0) {
            for (int i = k - 1; i < len; i++) {
                res += 1L * right.get(i) * left.get(i - k + 1);
            }

            for (int i = k + modulo - 1; i < len; i++) {
                prefix[i - k + 1] += left.get(i - (k + modulo) + 1) + prefix[i - (k + modulo) + 1];
                res += 1L * right.get(i) * prefix[i - k + 1];
            }

        } else {
            count = 0;
            for (int i = 0; i < n; i++) {
                if (numArrays[i] == 0) {
                    count++;

                } else {
                    res += 1L * count * (count + 1) / 2;
                    count = 0;
                }
            }
            res += 1L * count * (count + 1) / 2;

            for (int i = modulo - 1; i < len; i++) {
                prefix[i] += left.get(i - modulo + 1);
                if (i >= modulo) {
                    prefix[i] += prefix[i - modulo];
                }
                res += 1L * right.get(i) * prefix[i];
            }
        }
//        System.out.println(res);
//        System.out.println(Arrays.toString(prefix));

        return res;
    }

    private void swap(List<Integer> right, int i, int j) {
        int temp = right.get(j);
        right.set(j, right.get(i));
        right.set(i, temp);
    }

}
