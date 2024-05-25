package leetcode.contest.double_131;

import java.util.*;
/**
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
     * @return
     */
    public List<Boolean> getResults(int[][] queries) {
        int maxNum = 1;
        for (int i = 0; i < queries.length; i++) {
            maxNum = Math.max(maxNum, queries[i][1]);
        }

        Set<Integer> setObstacle = new HashSet<>();
        SegmentTree segmentTree = new SegmentTree(0, maxNum + 1);
        segmentTree.add(0, 1, 1);

        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == 1) {
                segmentTree.add(queries[i][1], queries[i][1] + 1, 1);
                setObstacle.add(queries[i][1]);

            } else {
//                System.out.println(Arrays.toString(segmentTree.queryForMax(0, queries[i][1] + 1)));
                if (!setObstacle.contains(queries[i][1])) {
                    segmentTree.add(queries[i][1], queries[i][1] + 1, 1);
                }
//                System.out.println(Arrays.toString(segmentTree.queryForMax(0, queries[i][1] + 1)));

                res.add((segmentTree.queryForMax(0, queries[i][1] + 1)[0] + 1) >= queries[i][2]);

//                System.out.println(Arrays.toString(segmentTree.queryForMax(0, queries[i][1] + 1)));
                if (!setObstacle.contains(queries[i][1])) {
                    segmentTree.del(queries[i][1], queries[i][1] + 1, 1);
                }
//                System.out.println(Arrays.toString(segmentTree.queryForMax(0, queries[i][1] + 1)));

//                System.out.println();
            }
        }

        return res;
    }

    static class SegmentTree {

        // 所有都是左闭右开区间！！！！！
        public Node root;

        private static final long SEGMENT_TREE_INF = Long.MAX_VALUE;

        public long mod = 1_000_000_007;

        public class Node {
            // [left,right) 左闭右开区间
            public long left;
            public long right;

            // 区间最大连续和
            long maxContinueSum = 0;
            // 区间最左侧连续和
            long leftContinueSum = 0;
            // 区间最右侧连续和
            long rightContinueSum = 0;

            // 孩子节点
            Node leftNode;
            Node rightNode;
        }

        // 所有都是左闭右开区间！！！！！
        SegmentTree(long left, long right) {
            root = new Node();
            root.left = left;
            root.right = right;
            root.maxContinueSum = right - left;
            root.leftContinueSum = right - left;
            root.rightContinueSum = right - left;
        }

        // 区间增加 val
        public void add(long left, long right, int val) {
            addWithNode(root, left, right, val);
        }

        // 区间删除 val
        public void del(long left, long right, int val) {
            delWithNode(root, left, right, val);
        }

        // 查询区间最大值
        public long[] queryForMax(long left, long right) {
            return queryForMaxWithNode(root, left, right);
        }

        private void addWithNode(Node node, long left, long right, int val) {
            if (node.left >= left && node.right <= right) {
                node.maxContinueSum = 0;
                node.leftContinueSum = 0;
                node.rightContinueSum = 0;
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

        private void delWithNode(Node node, long left, long right, int val) {
            if (node.left >= left && node.right <= right) {
                node.maxContinueSum = node.right - node.left;
                node.leftContinueSum = node.right - node.left;
                node.rightContinueSum = node.right - node.left;
                return;
            }
            long mid = (node.left + node.right) >> 1;

            // pushDown
            pushDown(node);
            if (left < mid && node.leftNode != null) {
                delWithNode(node.leftNode, left, Math.min(right, mid), val);
            }
            if (right > mid && node.rightNode != null) {
                delWithNode(node.rightNode, Math.max(left, mid), right, val);
            }
            pushUp(node);
        }

        private long[] queryForMaxWithNode(Node node, long left, long right) {
            if (node == null) {
                return new long[]{0, 0, 0};
            }
            if (node.left >= left && node.right <= right) {
                return new long[]{node.maxContinueSum, node.leftContinueSum, node.rightContinueSum};
            }
            long mid = (node.left + node.right) >> 1;
            pushDown(node);
            long[] res = new long[]{0, 0, 0};
            long[] leftRes = new long[]{0, 0, 0};
            long[] rightRes = new long[]{0, 0, 0};
            if (left < mid) {
                leftRes = queryForMaxWithNode(node.leftNode, left, Math.min(right, mid));
            }
            if (right > mid) {
                rightRes = queryForMaxWithNode(node.rightNode, Math.max(left, mid), right);
            }

            res[0] = Math.max(leftRes[0], Math.max(rightRes[0], leftRes[2] + rightRes[1]));
            res[1] = leftRes[1] + (leftRes[1] == (node.leftNode.right - node.leftNode.left) ? rightRes[1] : 0);
            res[2] = rightRes[2] + (rightRes[2] == (node.rightNode.right - node.rightNode.left) ? leftRes[2] : 0);
            return res;
        }

        // 向下汇总，修改、查询子节点之前，需要调用此方法，清空节点add值
        private void pushDown(Node node) {
            if (node == null) {
                return;
            }
            if (node.left == node.right - 1) {
                // 只有一个点，不用下推了
                return;
            }
            long mid = (node.left + node.right) >> 1;
            // 动态开点
            if (node.leftNode == null) {
                Node leftNode = new Node();
                leftNode.left = node.left;
                leftNode.right = mid;
                leftNode.maxContinueSum = mid - node.left;
                leftNode.leftContinueSum = mid - node.left;
                leftNode.rightContinueSum = mid - node.left;
                node.leftNode = leftNode;
            }
            if (node.rightNode == null) {
                Node rightNode = new Node();
                rightNode.left = mid;
                rightNode.right = node.right;
                rightNode.maxContinueSum = node.right - mid;
                rightNode.leftContinueSum = node.right - mid;
                rightNode.rightContinueSum = node.right - mid;
                node.rightNode = rightNode;
            }
        }

        // 计算完子节点后，上报信息
        private void pushUp(Node node) {
            if (node.leftNode != null && node.rightNode != null) {
                node.maxContinueSum = Math.max(node.leftNode.maxContinueSum, Math.max(node.rightNode.maxContinueSum, node.leftNode.rightContinueSum + node.rightNode.leftContinueSum));
                node.leftContinueSum = node.leftNode.leftContinueSum + (node.leftNode.leftContinueSum == (node.leftNode.right - node.leftNode.left) ? node.rightNode.leftContinueSum : 0);
                node.rightContinueSum = node.rightNode.rightContinueSum + (node.rightNode.rightContinueSum == (node.rightNode.right - node.rightNode.left) ? node.leftNode.rightContinueSum : 0);

//                System.out.println(node.left + " : " + node.right + " : " + node.maxContinueSum + " : " + node.leftContinueSum + " : " + node.rightContinueSum);
            }
        }
    }

}
