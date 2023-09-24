package leetcode.contest.contest_364;


import java.util.List;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private long solution(List<Integer> maxHeights) {
        long res = 0L;
        for (int i = 0; i < maxHeights.size(); i++) {

            long curTotalNum = maxHeights.get(i);

            int curMaxNum = maxHeights.get(i);
            for (int j = i - 1; j >= 0; j--) {
                curMaxNum = Math.min(curMaxNum, maxHeights.get(j));
                curTotalNum += curMaxNum;
            }

            curMaxNum = maxHeights.get(i);
            for (int j = i + 1; j < maxHeights.size(); j++) {
                curMaxNum = Math.min(curMaxNum, maxHeights.get(j));
                curTotalNum += curMaxNum;
            }
            res = Math.max(res, curTotalNum);
        }

        return res;
    }


}
