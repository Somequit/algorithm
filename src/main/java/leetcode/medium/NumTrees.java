package leetcode.medium;

import utils.AlgorithmUtils;

/**
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * 1 <= n <= 19
 * @author gusixue
 * @date 2023/3/11
 */
public class NumTrees {

    public static void main(String[] args) {

        NumTrees numTrees = new  NumTrees();

        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();

            int res = numTrees.solution(n);
            System.out.println(res);
        }
    }

    /**
     * 刚开始的想法是 n 只有 19 个数，所以可以初始化所有数据然后存入内存、就是打表，
     * 初始化使用 dfs 从根节点开始枚举每个还未使用的点，然后左右子树就确认了哪些了（左边比他小、右边比他大），
     * 后来想到可以使用记忆化 dp，枚举每个点为根节点后，可以找到左右子树分别还有多少节点，
     * 而左右子树组成的次数之前已经求出来了，直接左子树次数乘以右子树次数即可，
     * 注意：如果左右子树没有节点，则置为 1，虽然不能构成树、但有根节点所有不是 0 种情况
     */
    private int solution(int n) {
        // 避免越界、导致数组个数定义异常以及超过 int 范围
        if (n < 1 || n > 19) {
            return 0;
        }

        // 如果范围更大，需要改用 long 或者 BigInteger
        int[] numDp = new int[n + 1];

        // 当有 0 个节点时，不能构成树、但有根节点所有不能为 0，这样是方便计算
        numDp[0] = 1;
        numDp[1] = 1;

        for (int i = 2; i <= n; i++) {
            // 以每个 j 为根节点，可以找到左边有 j - 1 个节点，右边有 i - j 个节点，使用记忆化 DP
            for (int j = 1; j <= i; j++) {
                numDp[i] += numDp[j - 1] * numDp[i - j];
            }
        }
        return numDp[n];
    }
}
