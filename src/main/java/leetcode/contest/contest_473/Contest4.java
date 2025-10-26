package leetcode.contest.contest_473;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * 问题转化为：前缀和的差 [0，j]（即 prefix[j]） - [0，i]（即 prefix[i]） 等于 k 的倍数，左右均模 k 转换可得 prefix[i]%k == prefix[j]%k，区间 [i+1，j] 就是良好数组，
     * 先遍历数组 nums，一边遍历一边求前缀和 prefix，
     * 每次结果加上 Map 中 prefix[j]%k 出现过的个数，即按照问题转化中 i 的个数，即良好数组的个数，
     * 判断前缀和 prefix[j]%k 是否存入 Map 中，由于需要去除数值序列相同的值，同时数组是非递减的，要找 数值序列相同的值
     *     当 j 不变时不论 i 在哪儿，因为 [i，j] 个数不同，数值序列均不相同，
     *     当 j 增大时
     *         如果 nums[j]!=nums[j-1]，nums[j] 之前没有出现过，因此数值序列均不相同，
     *         如果 nums[j]==nums[j-1]，此时 nums[i]==nums[j]，否则 数组[0,j] 中 nums[j] 重复个数全出现了，在之前数值序列一定没有出现过，
     * 结果可得：数值序列相同的数组一定出现在区间 [i,j] 中每个值均相同的情况下，同时 1、2、3... 个 重复nums[j] 的和等于 k 的倍数也需要加入结果，
     * 做法就是最后一段相同的 nums[j] 先不加入 Map 中作为 i，此时区间 [i,j] 就一定包含当前所有等于 nums[j] 的值，
     *     如果 nums[i] == nums[j] 就满足：同时 1、2、3... 个 重复nums[j] 的和等于 k 的倍数也需要加入结果
     *     如果 nums[i] != nums[j] 则满足：数组[0,j] 中 nums[j] 重复个数全出现了，在之前数值序列一定没有出现过
     * 最后等出现不同的值时，将整段相同的 nums[j-1] 顺序加入 Map 中
     * 时间复杂度：O（n），空间复杂度：O（n）
     */
    public long numGoodSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n];
        prefix[0] = nums[0];

        Map<Integer, Integer> prefixModCountMap = new HashMap<>();
        prefixModCountMap.put(0, 1);
        long res = prefixModCountMap.getOrDefault((int) (prefix[0] % k), 0);

        for (int i = 1, l = 0; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];

            // 相同的值先不记录进 Map，避免了数值序列相同的数组
            if (nums[i] != nums[i - 1]) {
                for (int j = l; j < i; j++) {
                    prefixModCountMap.merge((int) (prefix[j] % k), 1, Integer::sum);
                }

                l = i;
            }

            res += prefixModCountMap.getOrDefault((int) (prefix[i] % k), 0);
//            System.out.println(res + " : " + prefixModCountMap);
        }

        return res;
    }


}
