package leetcode.contest_353;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int num = AlgorithmUtils.systemInNumberInt();
            int t = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(num, t);
            System.out.println(res);
        }

    }

    public int solution(int num, int t) {
        return num + t + t;
    }


}
