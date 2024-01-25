package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gusixue
 * @description
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * @date 2023/9/13
 */
public class Permute {

    public static void main(String[] args) {
        Permute permute = new Permute();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            List<List<Integer>> res = permute.solution(nums);
            System.out.println(res);
        }
    }

    public List<List<Integer>> solution(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        int[] vis = new int[len];
        List<Integer> listTemp = new ArrayList<>();
        // dfs 全排列
        permuteDfs(nums, 0, vis, listTemp, res);

        return res;
    }

    private void permuteDfs(int[] nums, int curIndex, int[] vis, List<Integer> listTemp, List<List<Integer>> res) {
        int len = nums.length;
        if (curIndex == len) {
            List<Integer> listAdd = new ArrayList<>(listTemp);
            res.add(listAdd);
            return;
        }

        for (int i = 0; i < len; i++) {
            if (vis[i] == 0) {
                vis[i] = 1;
                listTemp.add(nums[i]);
                permuteDfs(nums, curIndex + 1, vis, listTemp, res);
                vis[i] = 0;
                listTemp.remove(listTemp.size() - 1);
            }
        }

    }
}
