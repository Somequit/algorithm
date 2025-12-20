package leetcode.contest.double_172;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description
 * @date 2025/12/20 10:27 下午
 */
public class Double_172_2 {
    public int maximumSum(int[] nums) {
        List<Integer>[] list = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            list[i] = new ArrayList<>();
        }

        for (int num : nums) {
            list[num % 3].add(num);
        }
        list[0].sort(Comparator.reverseOrder());
        list[1].sort(Comparator.reverseOrder());
        list[2].sort(Comparator.reverseOrder());

        int res = 0;
        int n0 = list[0].size();
        int n1 = list[1].size();
        int n2 = list[2].size();
        if (n0 > 2) {
            res = Math.max(res, prefixCntSum(list[0], 3));
        }
        if (n1 > 2) {
            res = Math.max(res, prefixCntSum(list[1], 3));
        }
        if (n2 > 2) {
            res = Math.max(res, prefixCntSum(list[2], 3));
        }
        if (n0 > 0 && n1 > 0 && n2 > 0) {
            res = Math.max(res, list[0].get(0) + list[1].get(0) + list[2].get(0));
        }

        return res;
    }

    private int prefixCntSum(List<Integer> list, int n) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += list.get(i);
        }
        return res;
    }
}
