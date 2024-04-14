package leetcode.contest.contest_393;

import java.util.Arrays;

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

    static class Solution {
        private static final int INF = Integer.MAX_VALUE / 2;
        /**
         * @return
         */
        public int minimumValueSum(int[] nums, int[] andValues) {
            int n = nums.length;
            int m = andValues.length;

            // nums[i]结尾、匹配andValues[j] 的 最小值的和
            int[][] dp = new int[n][m];
            // 避免相加越界
            Arrays.stream(dp).forEach(arr -> Arrays.fill(arr, INF));

            // [startIndex[j][0], i] 与 [startIndex[j][1], i] 区间 & 后的值，注意要相等且尽可能不小于 andValues[j]
            int[][] startIndex = new int[m][2];
            // [startIndex[j][1], i] 区间 & 后的值，按位压缩到 startIndexBit[j] 中
            int[][] startIndexBit = new int[m][20];

            // 使用线段树求区间最小值与单点更新
            SegmentTree[] segmentTree = new SegmentTree[m - 1];
            for (int j = 0; j < m - 1; j++) {
                segmentTree[j] = new SegmentTree(0, n);
            }

            for (int j = 0; j < m; j++) {
                for (int i = 0; i < n; i++) {
                    int curAndVal = andValues[j];

//                // 全为 1
//                int val = Integer.MAX_VALUE;
//                for (int s = i; s >= 0; s--) {
//                    val &= nums[s];
//
//                    if (val < curAndVal) {
//                        break;
//
//                    } else if (val == curAndVal) {
//                        // 第一个 andValues 一定从 nums 开头开始匹配，同时 nums 开头匹配的一定是第一个 andValues
//                        if ((j == 0 || s == 0) && j + s != 0) {
//                            continue;
//                        }
//                        dp[i][j] = Math.min(dp[i][j], (s == 0 ? 0 : dp[s - 1][j - 1]) + nums[i]);
////                        System.out.println(s + " : " + i + " : " + curAndVal + " : " + dp[i][j]);
//                    }
//
//                }

                    // 双指针：同一个 andValues[j] 在 i 后移过程中，满足条件的 s 要么不变要么整体（存在则一定是一个区间）后移
                    // 后移时代表在 & 区间中删除一个数，可以使用按位判断当前 1 的个数，类似：3097. 或值至少为 K 的最短子数组 II
                    int curBitVal = andSubarray(curAndVal, startIndex[j], startIndexBit[j], nums, i);
//                System.out.println(curAndVal + " : " + startIndex[j][0] + " : " + startIndex[j][1] + " : " + i);
//                System.out.println(curBitVal + " : " + Arrays.toString(startIndexBit[j]));

                    if (curBitVal == curAndVal) {
                        // 获得满足条件的 [startIndex[j][0], startIndex[j][1]]=s、dp[s-1][j-1] 的最小值，使用线段树求区间最小值与单点更新
                        // 第一个 andValues 一定从 nums 开头开始匹配，同时 nums 开头匹配的一定是第一个 andValues
                        if (j == 0) {
                            if (startIndex[j][0] == 0) {
                                dp[i][j] = Math.min(dp[i][j], nums[i]);
                            }

                        } else {
                            int prevDp = INF;
                            // 避免子数组出现重复，保证后面可以匹配上 andValues[j]
                            if (startIndex[j][1] >= j) {
                                int startMinIndex = startIndex[j][0] < j ? (j - 1) : (startIndex[j][0] - 1);
                                int startMaxIndex = startIndex[j][1] - 1;
                                prevDp = (int) segmentTree[j - 1].queryForMin(startMinIndex, startMaxIndex + 1);
                            }


//                        for (int k = Math.max(j-1, startMinIndex); k <= startMaxIndex; k++) {
//                            prevDp = Math.min(prevDp, dp[k][j - 1]);
//                        }

                            dp[i][j] = Math.min(dp[i][j], prevDp + nums[i]);
                        }
                    }

                    if (j < m - 1) {
                        segmentTree[j].update(i, i + 1, dp[i][j]);
                    }

                }
//            System.out.println();
            }

            return dp[n - 1][m - 1] == INF ? -1 : dp[n - 1][m - 1];
        }

        /**
         * 双指针：同一个 andValues[j] 在 i 后移过程中，满足条件的 s 要么不变要么整体（存在则一定是一个区间）后移
         * 后移时代表在 & 区间中删除一个数，可以使用按位判断当前 1 的个数，类似：3097. 或值至少为 K 的最短子数组 II
         */
        private int andSubarray(int curAndVal, int[] startIndex, int[] startIndexBit, int[] nums, int end) {
            // 直接移动到最后，因为一定小于 curAndVal
            if (nums[end] < curAndVal) {
                startIndex[0] = end;
                startIndex[1] = end;
                Arrays.fill(startIndexBit, 0);
                updateBitVal(startIndexBit, nums[end], 1);

                return nums[end];
            }

            updateBitVal(startIndexBit, nums[end], 1);
            int curBitVal = getBitVal(startIndexBit, end - startIndex[1] + 1);
            // start 移动可能会变大，所以没必要移动
            if (curBitVal > curAndVal) {
                return curBitVal;
            }

            // start 移动可能会变大，尝试移动
            int min = -1;
            int max = -1;
            if (curBitVal == curAndVal) {
                min = startIndex[0];
                max = startIndex[1];
            }

            for (int s = startIndex[1]; s < end; s++) {
                updateBitVal(startIndexBit, nums[s], -1);
                curBitVal = getBitVal(startIndexBit, end - s);

                if (curBitVal > curAndVal) {
                    // 没有相等
                    if (min == -1) {
                        min = s + 1;
                        max = s + 1;

                    } else {
                        // 前一个相等则回滚
                        updateBitVal(startIndexBit, nums[s], 1);
                        curBitVal = getBitVal(startIndexBit, end - s + 1);
                    }
                    break;

                } else if (curBitVal == curAndVal) {
                    max = s + 1;

                    if (min == -1) {
                        min = s + 1;
                    }
                }
            }

            if (min == -1) {
                startIndex[0] = end;
                startIndex[1] = end;

            } else {
                startIndex[0] = min;
                startIndex[1] = max;
            }

            return curBitVal;
        }

        private void updateBitVal(int[] startIndexBit, int num, int addVal) {
            for (int i = 0; i < startIndexBit.length; i++) {
                if ((num & (1 << i)) > 0) {
                    startIndexBit[i] += addVal;
                }
            }
        }

        private int getBitVal(int[] startIndexBit, int count) {
            int res = 0;
            for (int i = 0; i < startIndexBit.length; i++) {
                if (startIndexBit[i] == count) {
                    res += (1 << i);
                }
            }
            return res;
        }

        static class SegmentTree {

            // 所有都是左闭右开区间！！！！！
            public Node root;

            private static final long SEGMENT_TREE_INF = Solution.INF;

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

            // 查询区间最小值
            public long queryForMin(long left, long right) {
                return queryForMinWithNode(root, left, right);
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
                    node.min = nums[cur];
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

            private long queryForMinWithNode(Node node, long left, long right) {
                if (node == null) {
                    return 0;
                }
                if (node.left >= left && node.right <= right) {
                    return node.min;
                }
                long mid = (node.left + node.right) >> 1;
                pushDown(node);
                long res = SegmentTree.SEGMENT_TREE_INF;
                if (left < mid) {
                    res = Math.min(queryForMinWithNode(node.leftNode, left, Math.min(right, mid)), res);
                }
                if (right > mid) {
                    res = Math.min(queryForMinWithNode(node.rightNode, Math.max(left, mid), right), res);
                }
                return res;
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
                long res = -SegmentTree.SEGMENT_TREE_INF;
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

            private void addWithNode(Node node, long left, long right, int val) {
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
            private void pushUp(Node node) {
                if (node.leftNode != null && node.rightNode != null) {
                    node.sum = node.leftNode.sum + node.rightNode.sum;
                    node.square = (node.leftNode.square + node.rightNode.square) % mod;
                    node.min = Math.min(node.leftNode.min, node.rightNode.min);
                    node.max = Math.max(node.leftNode.max, node.rightNode.max);
                }
            }
        }
    }


}
