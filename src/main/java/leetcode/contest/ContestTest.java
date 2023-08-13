package leetcode.contest;

import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.*;

/**
 * @author gusixue
 * @date 2023/3/19
 */
public class ContestTest {

    public static void main(String[] args) {
        ContestTest contestTest = new ContestTest();
        int[] nums = AlgorithmUtils.systemInArray();
        int k = AlgorithmUtils.systemInNumberInt();

        int res = contestTest.solution(nums, k);
        System.out.println(res);
    }

    public int solution(int[] nums, int k) {
        return 0;
    }

}
