package leetcode.brushQuestions.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description 118. 杨辉三角
 * @date 2024/4/9
 */
public class Generate {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.get(0).add(1);

        for (int i = 1; i < numRows; i++) {
            List<Integer> curList = new ArrayList<>();
            curList.add(1);

            List<Integer> prevList = res.get(res.size() - 1);

            for (int j = 0; j < prevList.size() - 1; j++) {
                curList.add(prevList.get(j) + prevList.get(j + 1));
            }

            curList.add(1);
            res.add(curList);

        }

        return res;
    }
}
