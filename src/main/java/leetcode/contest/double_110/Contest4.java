package leetcode.contest.double_110;

import javafx.util.Pair;
import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;

import java.util.*;


/**
 * 2809. 使数组和小于等于 x 的最少时间
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

            int res = contest.minimumTime(nums1, nums2, x);
            System.out.println(res);
        }

    }

    /**
     * DP+贪心+正难则反：
     *
     * 第 1 步：
     * 每个 nums1[i] 置为 0，超过一次不如仅最后一次置为 0，
     * 可知对于 nums1[i] 一样大，且不会影响其他 nums1，
     * 因此时间最多就是 nums1.len、超过则无意义，
     *
     * 第 2 步：
     * 问题转化为经过最少 s 秒且 s <= nums1.len，使得 nums3 = nums2[x]*(s-1) + nums2[y]*(s-2) + ... + nums2[z]*0 + nums1[a] + ...，nums3.len = nums1.len
     * nums3 总和 sum(nums3) 小于等于 x 时，最小的 s 就是答案，否则答案就是 -1，
     * 同时不可能推出 s 单调性，因此无法二分答案，
     *
     * 第 3 步：
     * 正面直接求最小值无法想到什么贪心策略，如果使用 DP 则第 j+1 秒会影响前 j 秒的数据，因为每秒每个元素会加上 nums2，**不满足无后效性**，
     * 正难则反：
     *     * 求经过 s 秒最多可以减少多少
     *     * 总数 sum(nums1) + s*sum(nums2) 减去即可
     *
     * 第 4 步：
     * 动规状态：dp[i][j] 为前 i 个数经过 j 秒最多可以减少多少，注意 i>=j，
     * 初始化 dp[i][0]=0，即前 i 个数不经过任何时间则无法减少
     *
     * **第 5 步**：
     * 转移方程：dp[i][j]=max(dp[i-1][j], dp[i-1][j-1] + nums1[i-1]+j*nums2[i-1])，
     *     * 即第 i 个数经过 j 秒是否删除的最大值，取决于是否需要再第 j 秒清理掉第 i 个元素
     *     * **此时 DP 无后效性**，因为第 i+1 个数不会影响前 i 个数的更新，
     *     * 同时需要满足减少 j 个元素的最大值、等于减少前（i-1 个元素中选 j-1 个元素）的最大值加第 i 个元素，
     *     * 可以观察减少的是 nums1[i-1]+j*nums2[i-1]，由于前 j 秒等价于选 j 个元素，此时 nums1 顺序无所谓，**j 固定升序、保证 j*nums2[i-1] 最大需要使 nums2 升序（nums1 跟随排序）**
     *
     * 第 6 步：
     * 最后结果就是枚举秒数 j=[0,nums1.len]，sum(nums1) + j * sum(nums2) - dp[nums1.len][j] <= x，找到最小的 j，没有则返回 -1
     * 空间压缩：由于 dp[i][j] 仅与 dp[i-1][j]、dp[i-1][j-1] 有关，因此可以使用一维 dp[j] 倒序处理
     * 时间复杂度：O(n ^ 2)，空间复杂度：O（n）
     */
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        // 判空与异常
        if (nums1 == null || nums2 == null || nums1.size() != nums2.size() || nums1.size() <= 0) {
            return -1;
        }

        int n = nums1.size();
        // 求总和
        int sumNums1 = nums1.stream().mapToInt(Integer::intValue).sum();
        int sumNums2 = nums2.stream().mapToInt(Integer::intValue).sum();
//        System.out.println(sumNums1);
//        System.out.println(sumNums2);

        // j 固定升序、保证 j*nums2[i-1] 最大需要使 nums2 升序（nums1 跟随排序）
        List<Pair<Integer, Integer>> numsPair = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            numsPair.add(new Pair<>(nums2.get(i), nums1.get(i)));
        }
        Collections.sort(numsPair, (o1, o2) -> o1.getKey() - o2.getKey());
//        System.out.println(numsPair);

        // 动规状态：dp[i][j] 为前 i 个数经过 j 秒最多可以减少多少，注意 i>=j，初始化 dp[i][0]=0，即前 i 个数不经过任何时间则无法减少，空间压缩掉 i
        int[] dp = new int[n + 1];

        // 转移方程：dp[i][j]=max(dp[i-1][j], dp[i-1][j-1] + nums1[i-1]+j*nums2[i-1])，
        for (int i = 1; i < n + 1; i++) {
            // 空间压缩，一维 dp[j] 倒序处理
            for (int j = i; j >=1; j--) {
                dp[j] = Math.max(dp[j], dp[j-1] + numsPair.get(i-1).getValue() + j * numsPair.get(i-1).getKey());
            }
        }
//        AlgorithmUtils.systemOutArray(dp);

        int res = -1;
        // 枚举秒数 j=[0,nums1.len]，sum(nums1) + j * sum(nums2) - dp[nums1.len][j] <= x，找到最小的 j，没有则返回 -1
        for (int j = 0; j <= n; j++) {
            if (sumNums1 + j * sumNums2 - dp[j] <= x) {
                res = j;
                break;
            }
        }

        return res;
    }

}
