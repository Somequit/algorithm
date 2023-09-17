package leetcode.contest.contest_363;


import java.util.List;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private int solution(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int left = 0;
        int right = 1_000_000_000;
        int res = 0;
        while(left <= right) {
            int mid = ((right - left) >> 1) + left;

            if (checkNumberOfAlloys(mid, n, k, budget, composition, stock, cost)) {
                res = mid;
                left = mid + 1;

            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    private boolean checkNumberOfAlloys(int num, int n,int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        boolean res = false;

        for (int i = 0; i < k; i++) {
            List<Integer> compositionNumList = composition.get(i);

            long budgetNeed = 0;
            for (int j = 0; j < n; j++) {
                int compositionNum = compositionNumList.get(j);
                int stockNum = stock.get(j);

                if ((long)stockNum < (long)compositionNum * num) {
                    budgetNeed += ((long)compositionNum * num - stockNum) * cost.get(j);

                    if(budgetNeed > budget) {
                        break;
                    }
                }
            }

            if(budgetNeed <= budget) {
                res = true;
                break;
            }
        }

        return res;
    }


}
