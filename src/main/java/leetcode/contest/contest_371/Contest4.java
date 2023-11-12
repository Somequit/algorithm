package leetcode.contest.contest_371;


import utils.AlgorithmUtils;

import java.util.*;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = contest.solution(nums);
            System.out.println(res);

            int res2 = contest.solution2(nums);
            System.out.println(res2);
        }

    }

    /**
     * @return
     */
    private int solution(int[] nums) {
        BIT bit = new BIT(1 << 20);

        int maxNum = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
            maxNum = Math.max(maxNum, num);
        }
        for (int num : set) {
            bit.add(num, 1);
        }

        int maxBit = 0;
        for (int i = 19; i >= 0; i--) {
            if ((maxNum & (1 << i)) > 0) {
                maxBit = i;
                break;
            }
        }

        int res = 0;
        for (int i = maxBit; i >= 0; i--) {
            for (int num : set) {
                int max;
                int min;

                int cur = ((num ^ res) >> (i + 1) << (i + 1));
                // 当前位为 1
                if ((num & (1 << i)) > 0) {
                    max = Math.min(cur + (1 << i) - 1, num - 1);
                    min = Math.max(cur, (num + 1) >> 1);

                // 当前位为 0
                } else {
                    max = Math.min(cur + (1 << (i + 1)) - 1, num - 1);
                    min = Math.max(cur + (1 << i), (num + 1) >> 1);
                }

                if (min > max) {
                    continue;
                }
                if (bit.queryForSum(min, max + 1) > 0) {
                    res |= (1 << i);
                    break;
                }
            }
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


    private int solution2(int[] nums) {
        Arrays.sort(nums);

        int res = 0;
        Trie trie = new Trie();
        trie.insert(nums[0], 1);
        for (int left = 0, right = 1; right < nums.length; right++) {
            while (nums[right] > (nums[left] << 1)) {
                trie.insert(nums[left], -1);
                left++;
            }

            if (left < right) {
                res = Math.max(res, trie.searchXorMax(nums[right]));
            }
            trie.insert(nums[right], 1);
        }

        return res;
    }

    class Trie {
        public class TrieNode{
            //        Map<Character, TrieNode> nextNode;
//        // 上一层是否为结束
//        // boolean isEnd;
            TrieNode one;
            TrieNode zero;
            int count;
        }

        public TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }

        public void insert(int num, int val) {
            TrieNode curNode = this.root;

            for (int i = 19; i >= 0; i--) {
                int bitNum = (1 << i);

                // 该位为 0
                if ((num & bitNum) == 0) {
                    if (curNode.zero == null) {
                        curNode.zero = new TrieNode();
                    }
                    curNode.zero.count += val;

                    curNode = curNode.zero;

                } else {
                    if (curNode.one == null) {
                        curNode.one = new TrieNode();
                    }
                    curNode.one.count += val;

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

            for (int i = 19; i >= 0; i--) {
                int bitNum = (1 << i);

                // 该位为 0
                if ((num & bitNum) == 0) {
                    if (curNode.one == null || curNode.one.count == 0) {
                        curNode = curNode.zero;

                    } else {
                        res += bitNum;
                        curNode = curNode.one;
                    }

                } else {
                    if (curNode.zero == null || curNode.zero.count == 0) {
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
}


