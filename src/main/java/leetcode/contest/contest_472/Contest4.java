package leetcode.contest.contest_472;

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

    class SegmentTree {

        // 所有都是左闭右开区间！！！！！
        public SegmentTree.Node root;

        private static final long SEGMENT_TREE_INF = Long.MAX_VALUE;

        public long mod = 1_000_000_007;

        public class Node {
            // [left,right) 左闭右开区间
            public long left;
            public long right;

            // 区间最小值
            long min = SegmentTree.SEGMENT_TREE_INF;
            // 区间最大值
            long max = -SegmentTree.SEGMENT_TREE_INF;
            // 区间和
            long sum = 0;
            // 区间平方和
            long square = 0;
            // 懒标记(需要调整的寄存值)
            long add = 0;

            // 孩子节点
            SegmentTree.Node leftNode;
            SegmentTree.Node rightNode;
        }

        // 所有都是左闭右开区间！！！！！
        SegmentTree(long left, long right) {
            root = new SegmentTree.Node();
            root.left = left;
            root.right = right;
        }


        // 区间增加 val
        public void add(long left, long right, int val) {
            addWithNode(root, left, right, val);
        }

        // 区间重置成 val
        public void update(long left, long right, long val) {
            updateWithNode(root, left, right, val);
        }

        // 查询区间等于 val 的下标，不存在则返回 -1
        public long queryIndexForValue(long val) {
            return queryIndexForValueWithNode(root, val);
        }

        // 查询区间等于 val 的下标，不存在则返回 -1
        private long queryIndexForValueWithNode(SegmentTree.Node node, long value) {
            if (node == null) {
                return -1;
            }
            // 区间内没有 value
            if (node.min > value || node.max < value) {
                return -1;
            }
            // 当前区间均等于 value，取最小的下标
            if (node.min == node.max) {
                return node.left;
            }
            pushDown(node);
            // 左边存在就只找左边，否则只找右边
            if (node.leftNode.min <= value && node.leftNode.max >= value) {
                return queryIndexForValueWithNode(node.leftNode, value);

            } else {
                return queryIndexForValueWithNode(node.rightNode, value);
            }
        }

        private void updateWithNode(SegmentTree.Node node, long left, long right, long val) {
            if (node.left >= left && node.right <= right) {
                node.square = val * val * (node.right - node.left);
                node.square %= mod;
                node.sum = (node.right - node.left) * val;
                node.min = val;
                node.max = val;
                node.add = val;
                // 由于是重置操作，和add不太一样，将孩子节点清空
                node.leftNode = null;
                node.rightNode = null;
                return;
            }
            long mid = (node.left + node.right) >> 1;

            // pushDown
            pushDown(node);
            if (left < mid) {
                updateWithNode(node.leftNode, left, Math.min(right, mid), val);
            }
            if (right > mid) {
                updateWithNode(node.rightNode, Math.max(left, mid), right, val);
            }
            pushUp(node);
        }

        private void addWithNode(SegmentTree.Node node, long left, long right, int val) {
            if (node.left >= left && node.right <= right) {
                node.square += val * val * (node.right - node.left) + 2 * val * node.sum;
                node.square %= mod;
                node.sum += (node.right - node.left) * val;
                node.min += val;
                node.max += val;
                node.add += val;
                return;
            }
            long mid = (node.left + node.right) >> 1;

            // pushDown
            pushDown(node);
            if (left < mid && node.leftNode != null) {
                addWithNode(node.leftNode, left, Math.min(right, mid), val);
            }
            if (right > mid && node.rightNode != null) {
                addWithNode(node.rightNode, Math.max(left, mid), right, val);
            }
            pushUp(node);
        }

        // 向下汇总，修改、查询子节点之前，需要调用此方法，清空节点add值
        private void pushDown(SegmentTree.Node node) {
            if (node == null) {
                return;
            }
            if (node.left == node.right - 1) {
                // 只有一个点，不用下推了
                node.add = 0;
                return;
            }
            long mid = (node.left + node.right) >> 1;
            // 动态开点
            if (node.leftNode == null) {
                SegmentTree.Node leftNode = new SegmentTree.Node();
                leftNode.left = node.left;
                leftNode.right = mid;
                node.leftNode = leftNode;
            }
            if (node.rightNode == null) {
                SegmentTree.Node rightNode = new SegmentTree.Node();
                rightNode.left = mid;
                rightNode.right = node.right;
                node.rightNode = rightNode;
            }
            if (node.add == 0) {
                return;
            }
            node.leftNode.add += node.add;
            node.leftNode.min += node.add;
            node.leftNode.max += node.add;
            node.leftNode.square += node.add * node.add * (node.leftNode.right - node.leftNode.left) + 2 * node.add * node.leftNode.sum;
            node.leftNode.square %= mod;
            node.leftNode.sum += node.add * (node.leftNode.right - node.leftNode.left);

            node.rightNode.add += node.add;
            node.rightNode.min += node.add;
            node.rightNode.max += node.add;
            node.rightNode.square += node.add * node.add * (node.rightNode.right - node.rightNode.left) + 2 * node.add * node.rightNode.sum;
            node.rightNode.square %= mod;
            node.rightNode.sum += node.add * (node.rightNode.right - node.rightNode.left);

            node.add = 0;
        }

        // 计算完子节点后，上报信息
        private void pushUp(SegmentTree.Node node) {
            if (node.leftNode != null && node.rightNode != null) {
                node.sum = node.leftNode.sum + node.rightNode.sum;
                node.square = (node.leftNode.square + node.rightNode.square) % mod;
                node.min = Math.min(node.leftNode.min, node.rightNode.min);
                node.max = Math.max(node.leftNode.max, node.rightNode.max);
            }
        }
    }


    /**
     * 最大最小值线段树+lazy更新
     * 线段树每个点存的值：前i个元素中 不同奇数-不同偶数 的差，如果此时的差与前面的差相同，那么可看做：不同奇数i-不同偶数i = 不同奇数j-不同偶数j -> 不同奇数(i-j) = 不同偶数(i-j)
     * 同时 i 与 i-1 差别仅有三种情况：+1、0、-1，因此点区间内的区间值（就是 不同奇数-不同偶数 的差）均能取到
     * 然后区间用 [最小值，最大值] 表示，而最小值与最大值可以分开求不影响
     * 最后重复出现数字，仅看该数字最后一次出现的位置，因此前面的区间 +1/-1 即可
     */
    public int longestBalanced(int[] nums) {
        int res = 0;
        int n = nums.length;

        // 添加一个最小值 下标0处值为0
        SegmentTree segmentTree = new SegmentTree(0, n + 1);
        segmentTree.update(0, 1, 0);
        // 最后重复出现数字，仅看该数字最后一次出现的位置
        Map<Integer, Integer> numIndexMap = new HashMap<>();
        int even = 0;
        int odd = 0;
        for (int i = 0; i < n; i++) {

            // 未出现过则判断奇偶，查询 不同奇数-不同偶数 的差 前面是否存在，最后更新线段树
            if (!numIndexMap.containsKey(nums[i])) {
                numIndexMap.put(nums[i], i);
                if (nums[i] % 2 == 0) {
                    even++;

                } else {
                    odd++;
                }

                // 不同奇数-不同偶数 的差 前面是否存在
                int equalsIndex = (int)segmentTree.queryIndexForValue(odd - even);
                if (equalsIndex >= 0) {
                    res = Math.max(res, i + 1 - equalsIndex);
                }

                segmentTree.update(i + 1, i + 2, odd - even);

                // 以前出现过则先将之前出现过的值删除，查询 不同奇数-不同偶数 的差 前面是否存在，最后更新线段树
            } else {
                int prevIndex = numIndexMap.get(nums[i]);
                // 以前出现过则先将之前出现过的值删除
                segmentTree.add(prevIndex + 1, i + 1, nums[i] % 2 == 0 ? 1 : -1);

                // 不同奇数-不同偶数 的差 前面是否存在
                int equalsIndex = (int)segmentTree.queryIndexForValue(odd - even);
                if (equalsIndex >= 0) {
                    res = Math.max(res, i + 1 - equalsIndex);
                }

                segmentTree.update(i + 1, i + 2, odd - even);
                numIndexMap.put(nums[i], i);

            }

//            System.out.println(even + " : " + odd + " : " +  numIndexMap + " : " + res);
        }

        return res;
    }


}
