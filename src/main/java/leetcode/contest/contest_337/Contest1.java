package leetcode.contest.contest_337;

import utils.AlgorithmUtils;

import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * @author gusixue
 * @date 2023/3/19
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();

            int[] res = contest.solution(n);
            System.out.println(Arrays.toString(res));
        }

    }

    private int[] solution(int n) {
        int res[] = new int[2];

        int even = 0;
        int odd = 0;
        int i = 0;

        while (n > 0) {
            if ((n & 1) == 1) {
                if (i % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }
            i++;
            n >>= 1;
        }

        res[0] = even;
        res[1] = odd;
        return res;
    }

}
