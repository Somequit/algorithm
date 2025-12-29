package leetcode.contest.contest_482;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/28 10:19 上午
 */
public class Contest_482_4 {
    public long countBalanced(long low, long high) {
        return doBalanced(high) - doBalanced(low - 1);
    }

    private char s[];
    private long memo[][];

    private long doBalanced(long num) {
        s = Long.toString(num).toCharArray();
        int m = s.length;

        // 记忆化搜索：前 i 位，奇-偶+72
        memo = new long[m][145];
        // 初始化，-1 表示没有计算过
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        return recursionDigitDP(0, 72, true, false);
    }

    /**
     * 数位 DP 模板：记忆化搜索获取合法数字的个数
     * @param curIndex 从 i 位开始填数字，i 前面填的数字集合是 mask
     * @param mask 奇-偶+72
     * @param isLimit 前面填写的数字是否与 s 对应上，如果为 true 当前最大为 int(s[i])，否则当前最大为 9
     * @param isNum 前面是否填写过数字（即处理前导零用），如果为 true 当前可以从 0 开始，否则可以 跳过 或 从 1 开始
     * @return 返回合法数字的个数
     */
    private long recursionDigitDP(int curIndex, int mask, boolean isLimit, boolean isNum) {
        /**
         * 仅有此位置最终定义是否合法
         * isNum 为 true 表示得到了一个合法数字
         */
        if (curIndex == s.length) {
            return (isNum && mask == 72) ? 1 : 0;
        }

        // isLimit 为 true（前面与 s 一样）以及 isNum 为 false（前面全没有填）仅有一种情况，不需要记忆化
        if (!isLimit && isNum && memo[curIndex][mask] != -1) {
            return memo[curIndex][mask];
        }

        long res = 0;
        // isNum：前面是否填写过数字（即处理前导零用），可以跳过当前数位
        if (!isNum) {
            res = recursionDigitDP(curIndex + 1, mask, false, false);
        }

        /**
         * 判读最大值
         * isLimit：前面填写的数字是否与 s 对应上，如果为 true 当前最大为 int(s[i])，否则当前最大为 9（否则就超过 n 啦）
         */
        int up = isLimit ? s[curIndex] - '0' : 9;
        // 枚举要填入的数字 d，isNum：如果为 true 当前可以从 0 开始，否则可以 跳过 或 从 1 开始
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            /**
             * 判断合法情况
             * d 不在 mask 中
             */
            res += recursionDigitDP(curIndex + 1, mask + d * (((curIndex + 1) & 1) == 1 ? 1 : -1), isLimit && d == up, true);
        }

        // 记忆化入数组
        if (!isLimit && isNum) {
            memo[curIndex][mask] = res;
        }
        return res;
    }
}
