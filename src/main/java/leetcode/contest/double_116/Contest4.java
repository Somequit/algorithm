package leetcode.contest.double_116;


import java.util.*;

/**
 * @author gusixue
 * @date 2023/10/28
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }
    /**
     * @return
     */
    private int solution(int[] nums) {
        int mod = 1_000_000_007;

        SegmentTree segmentTree = new SegmentTree(0, nums.length);
        Map<Integer, Integer> numIndexMap = new HashMap<>();

        long res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (numIndexMap.containsKey(nums[i])) {
                segmentTree.add(numIndexMap.get(nums[i]) + 1, i + 1, 1);

                res += segmentTree.queryForSquare(0, i + 1);
                res %= mod;

            } else {
                segmentTree.add(0, i + 1, 1);

                res += segmentTree.queryForSquare(0, i + 1);
                res %= mod;
            }
            numIndexMap.put(nums[i], i);
        }

        return (int) (res % mod);
    }


    public static class SegmentTree {

        // 所有都是左闭右开区间！！！！！
        public Node root;

        public long mod = 1_000_000_007;

        public class Node {
            // [left,right) 左闭右开区间
            public long left;
            public long right;

            // 区间最大值
            long max = 0;
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

        // 区间增加 val
        public void add(long left, long right, int val) {
            addWithNode(root, left, right, val);
        }

        // 查询区间平方和
        public long queryForSquare(long left, long right) {
            return queryFoSquareWithNode(root, left, right);
        }

        private long queryFoSquareWithNode(Node node, long left, long right) {
            if (node == null) {
                return 0;
            }
            if (node.left >= left && node.right <= right) {
                return node.square;
            }
            long mid = (node.left + node.right) >> 1;
            pushDown(node);
            long res = 0;
            if (left < mid) {
                res += queryFoSquareWithNode(node.leftNode, left, Math.min(right, mid));
            }
            if (right > mid) {
                res += queryFoSquareWithNode(node.rightNode, Math.max(left, mid), right);
            }
            return res;
        }

        private void addWithNode(Node node, long left, long right, int val) {
            if (node.left >= left && node.right <= right) {
                node.square += val * val * (node.right - node.left) + 2 * val * node.sum;
                node.square %= mod;
                node.sum += (node.right - node.left) * val;
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
            node.leftNode.square += node.add * node.add * (node.leftNode.right - node.leftNode.left) + 2 * node.add * node.leftNode.sum;
            node.leftNode.square %= mod;
            node.leftNode.sum += node.add * (node.leftNode.right - node.leftNode.left);

            node.rightNode.add += node.add;
            node.rightNode.max += node.add;
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
            }
        }
    }
}
