package leetcode.contest.double_115;


import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/7/22
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

    private List<Integer> solution(List<String> words) {
        List<Integer> res = new ArrayList<>();
        List<Integer> wordInt = new ArrayList<>();

        int continuous = 0;
        for (String word : words) {
            if ("prev".equals(word)) {
                continuous++;
                if (continuous > wordInt.size()) {
                    res.add(-1);
                } else {
                    res.add(wordInt.get(wordInt.size() - continuous));
                }

            } else {
                continuous = 0;
                wordInt.add(Integer.parseInt(word));
            }
        }

        return res;
    }



}
