package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 1671. 得到山形数组的最少删除次数
 * @date 2023/12/22
 */
public class MinimumMountainRemovals {

    /**
     * Java + 枚举山顶 + LIS：
     *
     * 第 1 步：
     * 枚举每个点作为山顶，
     * 分别求左右最少删除的个数（即 LIS），
     * 最后求出哪个作为山顶删除的点最少,
     * 注意：山顶左右一定要有元素！！！
     *
     * 第 2 步：
     * 先将大于等于山顶元素踢出，代表一定需要删除的元素，
     * 剩下的元素从山顶下一个元素开始按照二分的 LIS 做，
     *
     * 第 3 步：
     * 构造一个严格递减数组，依次将每个元素放入对应位置，数组个数就是结果
     *    * 第一个元素放头，第二个元素 i 开始二分查询：
     *    * 所有元素都比 i 小，则 i 替换第一个位置，因为 i 放在第一位最优
     *    * 所有元素都比 i 大，则 i 追加在最后，因为前面的元素在原数组中在 i 前面，因此 i 可以增长结果
     *    * 如果某个元素等于 i，则 i 替换相等元素（方便编码，本质是应该抛弃的）
     *    * 否则，i 替换小于 i 的最大值，因为 i 放在此位、与前面的元素可形成最长严格递减子序列才最优
     * PS：构造的严格递减数组不一定是真正最长严格递减子序列，因为替换元素时、数组中 i 后面的元素不正确，但不影响结果
     *
     * 时间复杂度：O（n*n*logn），空间复杂度：O（n）
     */
    public int minimumMountainRemovals(int[] nums) {
        int inf = (int)1e5;
        int res = inf;
        int n = nums.length;
        List<Integer> decreaseList = new ArrayList<>();

        // 枚举每个点作为山顶，
        for (int top = 1; top < n - 1; top++) {

            // 求左边最少删除的个数、先将大于等于山顶元素踢出
            for (int j = top - 1; j >= 0; j--) {
                if (nums[j] < nums[top]) {
                    decreaseList.add(nums[j]);
                }
            }
            // 注意：山顶左右一定要有元素！！！
            if (decreaseList.size() == 0) {
                continue;
            }
            int minRes = decreaseList.size() - doLis(decreaseList) + (top - decreaseList.size());
//            System.out.println(top + ":" + minRes);
            decreaseList.clear();

            // 求右边最少删除的个数、先将大于等于山顶元素踢出
            for (int j = top + 1; j < n; j++) {
                if (nums[j] < nums[top]) {
                    decreaseList.add(nums[j]);
                }
            }
            // 注意：山顶左右一定要有元素！！！
            if (decreaseList.size() == 0) {
                continue;
            }
            minRes += decreaseList.size() - doLis(decreaseList) + (n - top - 1 - decreaseList.size());
//            System.out.println(top + ":" + minRes);
            decreaseList.clear();

            res = Math.min(res, minRes);

        }

        return res;
    }

    /**
     * 求出 LIS 最长严格递减子序列的长度
     */
    private int doLis(List<Integer> decreaseList) {
        // 方便求小于等于 i 的最大值
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        List<Integer> resList = new ArrayList<>();

        // 构造一个严格递减数组，依次将每个元素放入对应位置，数组个数就是结果
        resList.add(decreaseList.get(0));
        treeMap.put(decreaseList.get(0), 0);
        for (int i = 1; i < decreaseList.size(); i++) {
            int curVal = decreaseList.get(i);

            if (resList.get(0) < curVal) {
                treeMap.remove(resList.get(0));
                treeMap.put(curVal, 0);

                resList.set(0, curVal);

            } else if (resList.get(resList.size() - 1) > curVal) {
                treeMap.put(curVal, resList.size());

                resList.add(curVal);

            } else {
                Map.Entry<Integer, Integer> entry = treeMap.floorEntry(curVal);
                treeMap.remove(entry.getKey());
                treeMap.put(curVal, entry.getValue());

                resList.set(entry.getValue(), curVal);
            }
        }

//        System.out.println(resList + " : " + decreaseList);

        return resList.size();
    }
}
