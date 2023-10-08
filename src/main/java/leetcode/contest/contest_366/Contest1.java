package leetcode.contest.contest_366;


/**
 * @author gusixue
 * @date 2023/10/08
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

    /**
     * @return
     */
    private int solution(int n, int m) {
        int total1 = 0;
        int total2 = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                total2 += i;
            } else {
                total1 += i;
            }
        }

        return total1 - total2;
    }


}
