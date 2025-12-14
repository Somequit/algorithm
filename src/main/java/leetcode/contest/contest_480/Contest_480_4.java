package leetcode.contest.contest_480;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/14 11:49 上午
 */
public class Contest_480_4 {

    public int[] minDeletions(String s, int[][] queries) {
        int n = s.length();
        char[] chars = new char[n + 2];
        for (int i = 0; i < n; i++) {
            chars[i + 1] = s.charAt(i);
        }
        chars[0] = s.charAt(0);
        chars[n + 1] = s.charAt(n - 1);
//        System.out.println(Arrays.toString(chars));

        int curNum = 1;
        long[] arr = new long[n + 2];
        arr[0] = curNum;
        for (int i = 1; i < n + 2; i++) {
            if (chars[i] == chars[i - 1]) {
                curNum++;
            }
            arr[i] = curNum;
        }
        BITRange bit = new BITRange(arr);

        List<Integer> listRes = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int status = queries[i][0];
            if (status == 1) {
                int idx = queries[i][1] + 1;
                if (chars[idx] == chars[idx - 1] && chars[idx] == chars[idx + 1]) {
                    // (idx)-1、(idx+1,)-2
                    bit.rangeAdd(idx, idx + 1, -1);
                    bit.rangeAdd(idx + 1, n + 2, -2);

                } else if (chars[idx] != chars[idx - 1] && chars[idx] == chars[idx + 1]) {
                    // (idx)+1
                    bit.rangeAdd(idx, idx + 1, 1);

                } else if (chars[idx] == chars[idx - 1] && chars[idx] != chars[idx + 1]) {
                    // (idx)-1
                    bit.rangeAdd(idx, idx + 1, -1);

                } else {
                    // (idx)+1、(idx+1,)+2
                    bit.rangeAdd(idx, idx + 1, 1);
                    bit.rangeAdd(idx + 1, n + 2, 2);

                }
                chars[idx] = chars[idx] == 'A' ? 'B' : 'A';

            } else {
                int left = queries[i][1] + 1;
                int right = queries[i][2] + 1;

                // (right) - (left)
                listRes.add((int) (bit.queryForRangeSum(right, right + 1) - bit.queryForRangeSum(left, left + 1)));
            }
        }

        return listRes.stream().mapToInt(Integer::intValue).toArray();
    }


    class BITRange {
        // 维护差分 d[i]=a[i]-a[i-1]，下标取到 [1, size)
        long[] sum1;
        // 维护 d[i] * i，下标取到 [1, size)
        long[] sum2;
        int size;

        public BITRange(int length) {
            this.size = length + 2;
            sum1 = new long[this.size];
            sum2 = new long[this.size];
        }

        public BITRange(long[] arr) {
            this.size = arr.length + 2;
            sum1 = new long[this.size];
            sum2 = new long[this.size];

            for (int i = 0; i < arr.length; i++) {
                rangeAdd(i, i + 1, arr[i]);
            }
        }

        private int lowbit(int x) {
            return x & (-x);
        }

        /**
         * 区间更新
         * 下标 [left, right) 增加 value
         */
        public void rangeAdd(int left, int right, long value) {
            // 下标 left 的 d[i] 增加 value
            add(sum1, left, value);
            // 下标 right 的 d[i] 增加 -value
            add(sum1, right, -value);

            // 下标 left 的 d[i]*i 增加 value*left
            add(sum2, left, value * left);
            // 下标 right 的 d[i]*i 增加 -value*right
            add(sum2, right, -value * right);
        }

        private void add(long[] sum, int index, long value) {
            for (index++; index < this.size; index += lowbit(index)) {
                sum[index] += value;
            }
        }

        /**
         * 单点求和
         */
        public long queryForPointSum(int index) {
            long res = 0;
            for (index++; index > 0; index -= lowbit(index)) {
                res += sum1[index];
            }
            return res;
        }

        /**
         * 区间求和，[left, right)
         */
        public long queryForRangeSum(int left, int right) {
            return queryForPrefixSum(right) - queryForPrefixSum(left);
        }

        /**
         * 前缀区间求和 [0, index)
         */
        private long queryForPrefixSum(int index) {
            return queryForSum(sum1, index) * index - queryForSum(sum2, index);
        }

        private long queryForSum(long[] sum, int index) {
            long res = 0;
            for (; index > 0; index -= lowbit(index)) {
                res += sum[index];
            }
            return res;
        }

    }
}
