package leetcode.contest.double_112;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    public long solution(List<Integer> nums, int m, int k) {
        long prefix = 0;
        Map<Integer, Integer> countMap = new HashMap<>(nums.size() << 1);
        for (int i = 0; i < k; i++) {
            countMap.put(nums.get(i), countMap.getOrDefault(nums.get(i), 0) + 1);
            prefix += nums.get(i);
        }

        long res = 0;
        if (countMap.size() >= m) {
            res = prefix;
        }

        for (int i = k; i < nums.size(); i++) {
            countMap.put(nums.get(i), countMap.getOrDefault(nums.get(i), 0) + 1);
            prefix += nums.get(i);

            countMap.put(nums.get(i - k), countMap.get(nums.get(i - k)) - 1);
            if (countMap.get(nums.get(i - k)) == 0) {
                countMap.remove(nums.get(i - k));
            }
            prefix -= nums.get(i - k);

            if (countMap.size() >= m) {
                res = Math.max(prefix, res);
            }
        }

        return res;
    }



}
