package leetcode.brushQuestions;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gusixue
 * @description
 * 421. 数组中两个数的最大异或值
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 1 <= nums.length <= 2 * 10 ^ 5
 * 0 <= nums[i] <= 2 ^ 31 - 1
 * @date 2023/11/4
 */
public class FindMaximumXOR {

    public static void main(String[] args) {
        FindMaximumXOR findMaximumXOR = new FindMaximumXOR();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = findMaximumXOR.solution(nums);
            System.out.println(res);

            int res2 = findMaximumXOR.solutionOptimization(nums);
            System.out.println(res2);
        }

    }

    class Trie {
        public class TrieNode{
//             Map<Character, TrieNode> nextNode;
//             // 上一层是否为结束
//             boolean isEnd;
            TrieNode one;
            TrieNode zero;
        }

        public TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }

        public void insert(int num) {
            TrieNode curNode = this.root;

            for (int i = 30; i >= 0; i--) {
                int bitNum = (1 << i);

                // 该位为 0
                if ((num & bitNum) == 0) {
                    if (curNode.zero == null) {
                        curNode.zero = new TrieNode();
                    }
                    curNode = curNode.zero;

                } else {
                    if (curNode.one == null) {
                        curNode.one = new TrieNode();
                    }
                    curNode = curNode.one;
                }
            }
        }

        /**
         * 将 num 从高位到低位遍历，在字典树中找是否有该位相反的元素，没有则找相同的元素继续，返回 num 异或字典树的最大值
         */
        public int searchXorMax(int num) {
            int res = 0;
            TrieNode curNode = this.root;

            for (int i = 30; i >= 0; i--) {
                int bitNum = (1 << i);

                // 该位为 0
                if ((num & bitNum) == 0) {
                    if (curNode.one == null) {
                        curNode = curNode.zero;

                    } else {
                        res += bitNum;
                        curNode = curNode.one;
                    }

                } else {
                    if (curNode.zero == null) {
                        curNode = curNode.one;

                    } else {
                        res += bitNum;
                        curNode = curNode.zero;
                    }
                }

            }
            return res;
        }
    }


    /**
     * 字典树优化暴力：0/1字典树存储所有元素（转化为 30-0 位），每次暴力将每个元素循环 30-0 位，在字典树中找是否有该位相反的元素
     * @param nums
     * @return
     */
    private int solutionOptimization(int[] nums) {
        Trie trie = new Trie();
        for (int num : nums) {
            trie.insert(num);
        }

        int res = 0;
        for (int num : nums) {
            res = Math.max(res, trie.searchXorMax(num));
        }
        return res;
    }

    /**
     * 分治：从 30 位到 0 位 倒序分治处理
     */
    public int solution(int[] nums) {
        // 去重后将 最高位 0/1 分别返回
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>(set);

        List<Integer> zeroList = new ArrayList<>();
        List<Integer> oneList = new ArrayList<>();
        partitionOneZero(list, zeroList, oneList, 1 << 30);

        // 从 29 位到 0 位 倒序分治处理
        int res;
        if (zeroList.size() == 0) {
            res = recursionPartition(oneList, oneList, 29);

        } else if (oneList.size() == 0) {
            res = recursionPartition(zeroList, zeroList, 29);

        } else {
            res = recursionPartition(zeroList, oneList, 29) + (1 << 30);
        }

        return res;
    }

    private int recursionPartition(List<Integer> list1, List<Integer> list2, int bit) {
        if (bit < 0) {
            return 0;
        }

        List<Integer> zeroList1 = new ArrayList<>();
        List<Integer> oneList1 = new ArrayList<>();
        partitionOneZero(list1, zeroList1, oneList1, 1 << bit);

        List<Integer> zeroList2 = new ArrayList<>();
        List<Integer> oneList2 = new ArrayList<>();
        partitionOneZero(list2, zeroList2, oneList2, 1 << bit);

        int res;
        if (zeroList1.size() == 0 && zeroList2.size() == 0) {
            res = recursionPartition(oneList1, oneList2, bit - 1);

        } else if (zeroList1.size() == 0 && oneList2.size() == 0) {
            res = recursionPartition(oneList1, zeroList2, bit - 1) + (1 << bit);

        } else if (oneList1.size() == 0 && zeroList2.size() == 0) {
            res = recursionPartition(zeroList1, oneList2, bit - 1) + (1 << bit);

        } else if (oneList1.size() == 0 && oneList2.size() == 0) {
            res = recursionPartition(zeroList1, zeroList2, bit - 1);

        } else if (zeroList1.size() == 0) {
            res = recursionPartition(oneList1, zeroList2, bit - 1) + (1 << bit);

        } else if (oneList1.size() == 0) {
            res = recursionPartition(zeroList1, oneList2, bit - 1) + (1 << bit);

        } else if (zeroList2.size() == 0) {
            res = recursionPartition(zeroList1, oneList2, bit - 1) + (1 << bit);

        } else if (oneList2.size() == 0) {
            res = recursionPartition(oneList1, zeroList2, bit - 1) + (1 << bit);

        } else {
            res = Math.max(recursionPartition(zeroList1, oneList2, bit - 1),
                    recursionPartition(zeroList2, oneList1, bit - 1)) + (1 << bit);
        }

        return res;
    }

    private void partitionOneZero(List<Integer> list, List<Integer> zeroList, List<Integer> oneList, int bitNum) {
        for (int num : list) {
            if ((num & bitNum) > 0) {
                oneList.add(num);

            } else {
                zeroList.add(num);
            }
        }
    }

}
