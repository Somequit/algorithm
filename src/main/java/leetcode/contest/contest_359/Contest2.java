package leetcode.contest.contest_359;


import utils.AlgorithmUtils;

/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(n, k);
            System.out.println(res);
        }

    }

    public int solution(int n, int k) {
        boolean[] not = new boolean[100];
        for (int i = 1; i < (k + 1) / 2; i++) {
            not[k - i] = true;
        }

        int res = 0;
        int count = 0;
        for (int i = 1; count < n; i++) {
            if (!not[i]) {
                res += i;
                count++;
            }
        }

        return res;
    }


}
