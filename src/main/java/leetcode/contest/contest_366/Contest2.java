package leetcode.contest.contest_366;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/10/08
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

    /**
     * @return
     */
    private int solution(List<Integer> processorTime, List<Integer> tasks) {
        Collections.sort(processorTime);
        Collections.sort(tasks, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int res = 0;

        for (int i = 0; i < processorTime.size(); i++) {
            res = Math.max(res, processorTime.get(i) + tasks.get(i * 4));
            res = Math.max(res, processorTime.get(i) + tasks.get(i * 4 + 1));
            res = Math.max(res, processorTime.get(i) + tasks.get(i * 4 + 2));
            res = Math.max(res, processorTime.get(i) + tasks.get(i * 4 + 3));
        }

        return res;
    }


}
