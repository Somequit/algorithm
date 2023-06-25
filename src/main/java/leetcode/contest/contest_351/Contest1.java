package leetcode.contest.contest_351;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    public int solution(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (doCountBeautifulPairs(nums[i], nums[j])) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean doCountBeautifulPairs(int a, int b) {
        while (a >= 10) {
            a /= 10;
        }
        b %= 10;

//        System.out.println(a + " : " + b);
        if (gcd(a, b) == 1) {
            return true;
        }
        return false;
    }

    private int gcd(int a,int b){
        return (a == 0 ? b : gcd(b % a, a));
    }



}
