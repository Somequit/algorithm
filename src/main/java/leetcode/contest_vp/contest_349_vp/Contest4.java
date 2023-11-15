package leetcode.contest_vp.contest_349_vp;


import utils.AlgorithmUtils;

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
    private int[] solution(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int qLen = queries.length;

        // 离散化
        Set<Integer> set = new TreeSet<>();
        // nums1-nums2 联合按照 nums1 降序
        Integer[] numsIndex = new Integer[n];
        for (int i = 0; i < n; i++) {
            set.add(nums1[i]);
            set.add(nums2[i]);
            numsIndex[i] = i;
        }
        // queries 联合按照 queries[i][0] 降序
        Integer[] queriesIndex = new Integer[qLen];
        for (int i = 0; i < qLen; i++) {
            set.add(queries[i][0]);
            set.add(queries[i][1]);
            queriesIndex[i] = i;
        }
        Map<Integer, Integer> numMap = new HashMap<>();
        int count = 0;
        for (int setNum : set) {
            numMap.put(setNum, count);
            count++;
        }
//        System.out.println(numMap);

        // nums1-nums2 联合按照 nums1 降序
        Arrays.sort(numsIndex, (o1, o2) -> {
            return nums1[o2] - nums1[o1];});
//        System.out.println(Arrays.toString(numsIndex));

        // queries 联合按照 queries[i][0] 降序
        Arrays.sort(queriesIndex, (o1, o2) -> {
            return queries[o2][0] - queries[o1][0];});
//        System.out.println(Arrays.toString(queriesIndex));

        // 创建线段树
        SegmentTree segmentTree = new SegmentTree(0, numMap.size());

        int[] res = new int[qLen];
        // 双指针处理 queries[i][0] 与 nums1
        for (int i = 0, nI = 0; i < qLen; i++) {
            int qI = queriesIndex[i];
            int x1 = queries[qI][0];
            int y1 = queries[qI][1];

            while (nI < n && nums1[numsIndex[nI]] >= x1) {
                // nums2[numsIndex[nI]] 相同位置的数需要更新成更大值
                int left = numMap.get(nums2[numsIndex[nI]]);
                if (segmentTree.queryForMax(left, left+1) < nums1[numsIndex[nI]] + nums2[numsIndex[nI]]) {
                    segmentTree.update(left, left + 1,nums1[numsIndex[nI]] + nums2[numsIndex[nI]]);
                }

                nI++;
            }

            res[qI] = (int)segmentTree.queryForMax(numMap.get(y1), numMap.size());
            res[qI] = (res[qI] == 0 ? -1 : res[qI]);
        }

        return res;
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

        // 查询区间最大值
        public long queryForMax(long left, long right) {
            return queryForMaxWithNode(root, left, right);
        }

        // 查询区间和
        public long queryForSum(long left, long right) {
            return queryForSumWithNode(root, left, right);
        }

        // 查询区间平方和
        public long queryForSquare(long left, long right) {
            return queryFoSquareWithNode(root, left, right);
        }

        public void buildTree(long[] nums, Node node) {
            // 只有一个点
            if (node.left == node.right - 1) {
                int cur = (int)node.left;
                node.square = 1L * nums[cur] * nums[cur];
                node.square %= mod;
                node.sum = nums[cur];
                node.max = nums[cur];
                node.add = 0;
                return;
            }

            // 动态开点
            pushDown(node);
            buildTree(nums, node.leftNode);
            buildTree(nums, node.rightNode);
            pushUp(node);
        }

        private long queryForMaxWithNode(Node node, long left, long right) {
            if (node == null) {
                return 0;
            }
            if (node.left >= left && node.right <= right) {
                return node.max;
            }
            long mid = (node.left + node.right) >> 1;
            pushDown(node);
            long res = Long.MIN_VALUE;
            if (left < mid) {
                res = Math.max(queryForMaxWithNode(node.leftNode, left, Math.min(right, mid)), res);
            }
            if (right > mid) {
                res = Math.max(queryForMaxWithNode(node.rightNode, Math.max(left, mid), right), res);
            }
            return res;
        }

        private long queryForSumWithNode(Node node, long left, long right) {
            if (node == null) {
                return 0;
            }
            if (node.left >= left && node.right <= right) {
                return node.sum;
            }
            long mid = (node.left + node.right) >> 1;
            pushDown(node);
            long res = 0;
            if (left < mid) {
                res += queryForSumWithNode(node.leftNode, left, Math.min(right, mid));
            }
            if (right > mid) {
                res += queryForSumWithNode(node.rightNode, Math.max(left, mid), right);
            }
            return res;
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

        private void updateWithNode(Node node, long left, long right, long val) {
            if (node.left >= left && node.right <= right) {
                node.square = val * val * (node.right - node.left);
                node.square %= mod;
                node.sum = (node.right - node.left) * val;
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
