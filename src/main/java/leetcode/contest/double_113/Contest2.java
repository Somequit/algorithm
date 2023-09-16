package leetcode.contest.double_113;


import java.util.*;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private int solution(List<Integer> nums) {
        Map<Integer, Integer> countMap = new HashMap<>(nums.size() << 1);
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> same = new ArrayList<>();
        for (Integer count : countMap.values()) {
            same.add(count);
        }
        Collections.sort(same, Collections.reverseOrder());

        int n = nums.size();
        int res = 0;
        if (same.size() == 1) {
            res = same.get(0);
        } else if (2 * same.get(0) - n > 0) {
            res = 2 * same.get(0) - n;
        } else {
            res = n % 2;
        }

        return res;
    }



}
