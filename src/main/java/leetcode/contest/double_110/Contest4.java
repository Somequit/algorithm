package leetcode.contest.double_110;

import javafx.util.Pair;
import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;

import java.util.*;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            List<Integer> nums1 = AlgorithmUtils.systemInList();
            List<Integer> nums2 = AlgorithmUtils.systemInList();
            int x = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums1, nums2, x);
            System.out.println(res);
        }

    }

    public int solution(List<Integer> nums1, List<Integer> nums2, int x) {
        // 判空
        if (CollectionUtils.isEmpty(nums1) || CollectionUtils.isEmpty(nums2) || nums1.size() != nums2.size()) {
            return -1;
        }

        int total1 = 0;
        int total2 = 0;
        for (int i = 0; i < nums1.size(); i++) {
            total1 += nums1.get(i);
            total2 += nums2.get(i);
        }

        int n = nums1.size();

        // nums2 升序保证后面选的元素应该删除更大的 nums2[i]，对应 nums1 也同时改变（使用时改变）
        List<Pair<Integer, Integer>> numsPair2 = new ArrayList<>(nums2.size());
        for (int i = 0; i < n; i++) {
            numsPair2.add(new Pair<>(nums2.get(i), i));
        }
        Collections.sort(numsPair2, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        });
//        System.out.println(numsPair2);

        // dp[i][j] 前 i 个元素中选 j 个互不相同的元素操作、删除的最大值（清理为 0 代表删除对应的 nums[i]），默认初始化 dp[i][0] = dp[0][j] = 0
        int[][] dp = new int[n + 1][n + 1];

        // 转移方程式：第 i 个元素是否选择来更新：dp[i][j] = max(dp[i-1][j], dp[i-1][j-1]+nums1[i-1](注意对应 nums2[i-1])+j*nums2[i-1])（i>=j）
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + nums1.get(numsPair2.get(i - 1).getValue()) + j * numsPair2.get(i - 1).getKey());
            }
        }
//        AlgorithmUtils.systemOutArray(dp);

        int res = -1;
        // sum(nums1) + j*sum(nums2) - dp[n][j] <= x，返回最小的 j
        for (int j = 0; j < n + 1; j++) {
//            System.out.println(total1 + " : " + j * total2 + " : " + dp[n][j]);
            if (total1 + j * total2 - dp[n][j] <= x) {
                res = j;
                break;
            }
        }

        return res;
    }

}
