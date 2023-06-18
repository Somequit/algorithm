package leetcode.contest.contest_350;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int mainTank = AlgorithmUtils.systemInNumberInt();
            int additionalTank = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(mainTank, additionalTank);
            System.out.println(res);
        }

    }

    public int solution(int mainTank, int additionalTank) {
        int res = 0;

        while (mainTank > 0) {
            if (mainTank < 5) {
                res += mainTank*10;
                break;
            }
            res += 50;
            mainTank -= 5;
            if (additionalTank > 0) {
                additionalTank--;
                mainTank++;
            }
        }
        return res;
    }


}
