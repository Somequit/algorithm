package leetcode.contest.contest_354;

import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            List<Integer> nums = CollectionUtils.arrayToList(AlgorithmUtils.systemInArray());

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    public int solution(List<Integer> nums) {
        int res = -1;

        int count = 0;
        int num = 0;

        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            numMap.put(nums.get(i), numMap.getOrDefault(nums.get(i), 0) + 1);
            if (numMap.get(nums.get(i)) * 2 > nums.size()) {
                num = nums.get(i);
            }
        }

        if (num > 0) {
            count = numMap.get(num);

            int left = 0;
            for (int i = 0; i < nums.size(); i++) {
                if (nums.get(i) == num) {
                    left++;
                }

                if (left * 2 > i + 1 && (count - left) * 2 > nums.size() - i - 1) {
                    res = i;
                    break;
                }
            }
        }
//        System.out.println(num + " : " + count + " : " + numMap);

        return res;
    }


}
