package leetcode.contest.double_171;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/6 10:24 下午
 */
public class Double_171_4 {

    public static void main(String[] args) {
        Double_171_4 contest = new Double_171_4();
        contest.minInversionCount(new int[]{3, 1, 2, 5, 4}, 3);
        System.out.println();
        contest.minInversionCount(new int[]{5, 3, 2, 1}, 4);
        System.out.println();
        contest.minInversionCount(new int[]{2, 1}, 1);
    }

    public long minInversionCount(int[] nums, int k) {
        int n = nums.length;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            treeSet.add(nums[i]);
        }
        int[] dedupNum = new int[treeSet.size()];
        int idx = 0;
        for (int num : treeSet) {
            dedupNum[idx] = num;
            idx++;
        }

        BIT bit = new BIT(n);
        long curMinInvCnt = 0;
        for (int i = 0; i < k; i++) {
            int curNum = Arrays.binarySearch(dedupNum, nums[i]);
            curMinInvCnt += i - bit.queryForSum(curNum + 1);
            bit.add(curNum, 1);
//            System.out.println(curMinInvCnt);
        }

        long res = curMinInvCnt;
        for (int right = k; right < n; right++) {
            int prevNum = Arrays.binarySearch(dedupNum, nums[right - k]);
            bit.add(prevNum,-1);
            curMinInvCnt -= bit.queryForSum(prevNum);

            int curNum = Arrays.binarySearch(dedupNum, nums[right]);
            curMinInvCnt += k - 1 - bit.queryForSum(curNum + 1);
            bit.add(curNum,1);

//            System.out.println(curMinInvCnt);

            res = Math.min(res, curMinInvCnt);
        }

        return res;
    }

    class BIT {
        int[] sum;
        int size;

        public BIT(int length) {
            this.size = length + 1;
            sum = new int[this.size];
        }

        private int lowbit(int x) {
            return x & (-x);
        }

        // 单点更新：下标 index 添加 value
        public void add(int index, int value) {
            // bit 需要从 1 开始
            for (index++; index < this.size; index += lowbit(index)) {
                this.sum[index] += value;
            }
        }

        // 区间查询，前闭后开区间 [left, right)
        public int queryForSum(int left, int right) {
            return queryForSum(right) - queryForSum(left);
        }

        // 前缀区间查询，前闭后开区间 [0, index)
        public int queryForSum(int index) {
            int res = 0;
            for (; index > 0; index -= lowbit(index)) {
                res += this.sum[index];
            }
            return res;
        }
    }
}
