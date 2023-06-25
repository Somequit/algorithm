package leetcode.contest.contest_351;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int m = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(n, m);
            System.out.println(res);
        }

    }

    public int solution(int num1, int num2) {
        if (num1 <= num2) {
            return -1;
        }
        long num1Long = num1;
        int res = 0;
        while (!(num1Long  < 0 && num2 > 0) && num1Long > res) {
            num1Long -= num2;
            res++;
            if (res >= binaryOne(num1Long) && num1Long >= res) {
                return res;
            }
        }
        return -1;
    }

    private int binaryOne(long num1Long) {
        int res = 0;
        while (num1Long > 0) {
            res++;
            num1Long -= lowbit(num1Long);
        }
        return res;
    }

    private long lowbit(long num) {
        return num & -num;
    }
}
