package leetcode.contest.double_113;


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

    private int solution(List<Integer> nums) {
        int res = -1;

        int n = nums.size();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n - 1; j++) {
                if (nums.get((j - i + n) % n) > nums.get((j - i + 1 + n) % n)) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                res = i;
                break;
            }
        }

        return res;
    }



}
