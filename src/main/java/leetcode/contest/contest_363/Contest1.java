package leetcode.contest.contest_363;


import java.util.List;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private int solution(List<Integer> nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (checkIndicesWithKSetBits(i, k)) {
                res += nums.get(i);
            }
        }
        return res;
    }

    private boolean checkIndicesWithKSetBits(int num, int k) {
        int count = 0;
        while (num > 0) {
            count++;
            num -= (num & -num);
        }
        return count == k;
    }


}
