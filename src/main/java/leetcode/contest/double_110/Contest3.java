package leetcode.contest.double_110;

import leetcode.ListNode;
import utils.AlgorithmUtils;

import java.util.*;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            List<Integer> nums = AlgorithmUtils.systemInList();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    public int solution(List<Integer> nums) {
        if (nums.size() == 1) {
            return 0;
        }

        int n = nums.size();
        Map<Integer, List<Integer>> sameMap = new HashMap<>(n << 1);
        for (int i = 0; i < n; i++) {
            if (!sameMap.containsKey(nums.get(i))) {
                List<Integer> list = new ArrayList();
                list.add(-1);
                sameMap.put(nums.get(i), list);
            }
            List<Integer> list = sameMap.get(nums.get(i));
            list.set(list.size() - 1, i - list.get(list.size() - 1) - 1);
            list.add(i);
        }
//        System.out.println(sameMap);

        int res = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : sameMap.entrySet()) {
            List<Integer> list = entry.getValue();
            list.set(0, list.get(0) + n - 1 - list.get(list.size() - 1));
//            System.out.println(list);

            int max = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                max = Math.max(max, (list.get(i) + 1) / 2);
            }
            res = Math.min(res, max);
        }

        return res;
    }

}
