package leetcode.contest.double_118;

import utils.AlgorithmUtils;

import java.util.*;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

            int res = contest.solution(nums);
            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int solution(int[] nums) {
        int n = nums.length;

        long[] preSum = new long[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        // 0-长度 1-最后最小值
        long[][] dp = new long[2][n];
        dp[0][0] = 1;
        dp[1][0] = nums[0];

        // 线段树维护 dp[1][j]+preSum[j]
        SegmentTree segmentTree = new SegmentTree(0, n);
        segmentTree.update(0, 1, dp[1][0] + preSum[0]);

        for (int i = 1; i < n; i++) {
            if (nums[i] >= dp[1][i - 1]) {
                dp[0][i] = dp[0][i - 1] + 1;
                dp[1][i] = nums[i];
                segmentTree.update(i, i + 1, dp[1][i] + preSum[i]);
//                System.out.println("update:" + i + " : " + (dp[1][i] + preSum[i]));
                continue;

            } else {
                dp[0][i] = dp[0][i - 1];
                dp[1][i] = dp[1][i - 1] + nums[i];
            }

            int ansIndex = segmentTree.queryMinForRightIndex(preSum[i]);
//            System.out.println(i + " : " + ansIndex);
            if (ansIndex != -1 && dp[0][i] - 1 <= dp[0][ansIndex]) {
                dp[0][i] = dp[0][ansIndex] + 1;
                dp[1][i] = preSum[i] - preSum[ansIndex];
            }
            segmentTree.update(i, i + 1, dp[1][i] + preSum[i]);

            // 暴力处理，可以优化
//            for (int j = i - 1; j >= 0; j--) {
//                long sum = preSum[i] - preSum[j];
//                if (dp[0][i] - 1 <= dp[0][j]) {
//                    if (sum >= dp[1][j]) {
//                        dp[0][i] = dp[0][j] + 1;
//                        dp[1][i] = sum;
//                        break;
//                    }
//
//                } else {
//                    break;
//                }
//            }

        }
//        System.out.println(Arrays.toString(dp[0]));
//        System.out.println(Arrays.toString(dp[1]));

        return (int) dp[0][n - 1];
    }


    class SegmentTree {

        // 所有都是左闭右开区间！！！！！
        public Node root;

        public long mod = 1_000_000_007;

        public class Node {
            // [left,right) 左闭右开区间
            public long left;
            public long right;

            // 区间最大值
            long max = 0;
            // 区间最小值
            long min = Long.MAX_VALUE;
            // 区间和
            long sum = 0;
            // 区间平方和
            long square = 0;
            // 懒标记(需要调整的寄存值)
            long add = 0;

            // 孩子节点
            Node leftNode;
            Node rightNode;
        }

        // 所有都是左闭右开区间！！！！！
        SegmentTree(long left, long right) {
            root = new Node();
            root.left = left;
            root.right = right;
        }

        SegmentTree(long[] nums) {
            root = new Node();
            root.left = 0;
            root.right = nums.length;
            buildTree(nums, root);
        }

        // 区间增加 val
        public void add(long left, long right, int val) {
            addWithNode(root, left, right, val);
        }

        // 区间重置成 val
        public void update(long left, long right, long val) {
            updateWithNode(root, left, right, val);
        }

        // 查询整个区间小于等于 val 的最右下标，没有则返回 -1
        public int queryMinForRightIndex(long val) {
            return queryMinForRightIndexWithNode(root, val);
        }

        public void buildTree(long[] nums, Node node) {
            // 只有一个点
            if (node.left == node.right - 1) {
                int cur = (int)node.left;
                node.square = 1L * nums[cur] * nums[cur];
                node.square %= mod;
                node.sum = nums[cur];
                node.max = nums[cur];
                node.min = nums[cur];
                node.add = 0;
                return;
            }

            // 动态开点
            pushDown(node);
            buildTree(nums, node.leftNode);
            buildTree(nums, node.rightNode);
            pushUp(node);
        }

        private int queryMinForRightIndexWithNode(Node node, long val) {
            if (node == null || node.min > val) {
                return -1;
            }

            // 叶子节点
            if (node.left + 1 == node.right) {
                return (int) node.left;
            }
            pushDown(node);
            int res = -1;
            if (node.rightNode.min <= val) {
                res = queryMinForRightIndexWithNode(node.rightNode, val);

            } else {
                res = queryMinForRightIndexWithNode(node.leftNode, val);
            }

            return res;
        }

        private void updateWithNode(Node node, long left, long right, long val) {
            if (node.left >= left && node.right <= right) {
                node.square = val * val * (node.right - node.left);
                node.square %= mod;
                node.sum = (node.right - node.left) * val;
                node.max = val;
                node.min = val;
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

        private void addWithNode(Node node, long left, long right, int val) {
            if (node.left >= left && node.right <= right) {
                node.square += val * val * (node.right - node.left) + 2 * val * node.sum;
                node.square %= mod;
                node.sum += (node.right - node.left) * val;
                node.max += val;
                node.min += val;
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
        private void pushDown(Node node) {
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
                Node leftNode = new Node();
                leftNode.left = node.left;
                leftNode.right = mid;
                node.leftNode = leftNode;
            }
            if (node.rightNode == null) {
                Node rightNode = new Node();
                rightNode.left = mid;
                rightNode.right = node.right;
                node.rightNode = rightNode;
            }
            if (node.add == 0) {
                return;
            }
            node.leftNode.add += node.add;
            node.leftNode.max += node.add;
            node.leftNode.min += node.add;
            node.leftNode.square += node.add * node.add * (node.leftNode.right - node.leftNode.left) + 2 * node.add * node.leftNode.sum;
            node.leftNode.square %= mod;
            node.leftNode.sum += node.add * (node.leftNode.right - node.leftNode.left);

            node.rightNode.add += node.add;
            node.rightNode.max += node.add;
            node.rightNode.min += node.add;
            node.rightNode.square += node.add * node.add * (node.rightNode.right - node.rightNode.left) + 2 * node.add * node.rightNode.sum;
            node.rightNode.square %= mod;
            node.rightNode.sum += node.add * (node.rightNode.right - node.rightNode.left);

            node.add = 0;
        }

        // 计算完子节点后，上报信息
        private void pushUp(Node node) {
            if (node.leftNode != null && node.rightNode != null) {
                node.sum = node.leftNode.sum + node.rightNode.sum;
                node.square = (node.leftNode.square + node.rightNode.square) % mod;
                node.max = Math.max(node.leftNode.max, node.rightNode.max);
                node.min = Math.min(node.leftNode.min, node.rightNode.min);
            }
        }
    }
}
