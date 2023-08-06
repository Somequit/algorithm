package leetcode.contest.contest_357;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            List<Integer> nums = AlgorithmUtils.systemInList();
            int m = AlgorithmUtils.systemInNumberInt();

            boolean res = contest.solution(nums, m);
            System.out.println(res);
        }

    }

    public boolean solution(List<Integer> nums, int m) {
        if (nums.size() <= 2) {
            return true;
        }

        boolean res = true;

        int len = nums.size();
        for (int i = 0; i < len - 1; i++) {

            List<Integer> numsTemp = new ArrayList<>();

            int maxNum = 0;
            int maxIndex = -1;
            for (int j = 0; j < nums.size() - 1; j++) {
                if (nums.get(j) + nums.get(j + 1) > maxNum) {
                    maxNum = nums.get(j) + nums.get(j + 1);
                    maxIndex = j;
                }
            }

            if (maxNum < m) {
                res = false;
                break;
            }
            for (int j = 0; j < nums.size(); j++) {
                if (j == maxIndex) {
                    numsTemp.add(maxNum);
                } else if (j - 1 != maxIndex) {
                    numsTemp.add(nums.get(j));
                }
            }
            nums = numsTemp;
//            System.out.println(maxNum + " : " + maxIndex + " : " + nums);

        }
        return res;
    }


}
