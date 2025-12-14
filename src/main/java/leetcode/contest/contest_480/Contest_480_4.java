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

        BIT bit = new BIT(n + 2);
        int curNum = 1;
        bit.rangeAdd(0, 0, curNum);
        for (int i = 1; i < n + 2; i++) {
            if (chars[i] == chars[i - 1]) {
                curNum++;
            }
            bit.rangeAdd(i, i, curNum);
        }

        List<Integer> listRes = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int status = queries[i][0];
            if (status == 1) {
                int idx = queries[i][1] + 1;
                if (chars[idx] == chars[idx - 1] && chars[idx] == chars[idx + 1]) {
                    // (idx)-1、(idx+1,)-2
                    bit.rangeAdd(idx, idx, -1);
                    bit.rangeAdd(idx + 1, n + 2, -2);

                } else if (chars[idx] != chars[idx - 1] && chars[idx] == chars[idx + 1]) {
                    // (idx)+1
                    bit.rangeAdd(idx, idx, 1);

                } else if (chars[idx] == chars[idx - 1] && chars[idx] != chars[idx + 1]) {
                    // (idx)-1
                    bit.rangeAdd(idx, idx, -1);

                } else {
                    // (idx)+1、(idx+1,)+2
                    bit.rangeAdd(idx, idx, 1);
                    bit.rangeAdd(idx + 1, n + 2, 2);

                }
                chars[idx] = chars[idx] == 'A' ? 'B' : 'A';

            } else {
                int left = queries[i][1] + 1;
                int right = queries[i][2] + 1;

                // (right) - (left)
                listRes.add((int) (bit.pointQuery(right) - bit.pointQuery(left)));
            }
        }

        return listRes.stream().mapToInt(Integer::intValue).toArray();
    }


    class BIT {
        private int n;
        private long[] tree;

        public BIT(int size) {
            this.n = size;
            this.tree = new long[n + 2];
        }

        // 在区间 [l, r] 上每个元素增加 val
        public void rangeAdd(int l, int r, long val) {
            if (l < 1) l = 1;
            if (r > n) r = n;
            if (l > r) return;

            // 差分思想：在l处加val，在r+1处减val
            add(l, val);
            add(r + 1, -val);
        }

        // 单点更新（内部方法）
        private void add(int index, long val) {
            while (index <= n + 1) {
                tree[index] += val;
                index += lowbit(index);
            }
        }

        // 单点查询：获取位置 index 的值
        public long pointQuery(int index) {
            long sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= lowbit(index);
            }
            return sum;
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }
}
