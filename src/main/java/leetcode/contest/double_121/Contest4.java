package leetcode.contest.double_121;

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
     * Java +  + 数位 DP：
     * 第 1 步：
     * 根据题意可以想到"记忆化搜素的数位 DP 模板"，
     *
     *
     * 第 2 步：
     * 计算 [0, finish] - [0, start-1]，将其置为 maxNum，
     * 将 maxNum 中超过 s 的前面位 sChar 取出来，其可以直接套用"记忆化搜素的数位 DP 模板"，
     * 注意套用模板时，每一位的上限为 limit，它可能小于 sChar[curIndex]，如果这样后面也是随便，
     *
     * 第 3 步：
     * 最后结果加上 0+s 的可能性，
     * 判断是否 DP 中是否取了不能取的 sChar+s，是则减去
     * 时间复杂度：O（limit * log10(finish)），空间复杂度：O（log10(finish)）
     */
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        // 转化为 [0, finish] - [0, start-1]
        return doNumberOfPowerfulInt(finish, limit, Long.parseLong(s), s)
                - doNumberOfPowerfulInt(start - 1, limit, Long.parseLong(s), s);
    }

    /**
     * 求出 [0, maxNum] 的结果
     */
    private long doNumberOfPowerfulInt(long maxNum, int limit, long sLong, String s) {
        if (sLong > maxNum) {
            return 0;
        }

        // 将 maxNum 中超过 s 的前面位 sChar 取出来，其可以直接套用"记忆化搜素的数位 DP 模板"
        sChar = (maxNum + "").substring(0, (maxNum + "").length() - s.length()).toCharArray();
        int m = sChar.length;

        // 记忆化搜索：超过 s 的前面位
        memo = new long[m];
        // 初始化，-1 表示没有计算过
        Arrays.fill(memo, -1);

        // 记忆化搜素的数位 DP 模板，注意 0+s 可能可以
        long res = recursionDigitDP(0, limit, true, false) + 1;

        // s 大于 maxNum 对应的位置，sChar 均不大于 limit，此时 DP 中取了不能取的 sChar+s
        if (checkNotEquals(maxNum, limit, sLong, s)) {
            res--;
        }
        System.out.println("res:" + res);

        return res;
    }

    private boolean checkNotEquals(long maxNum, int limit, long sLong, String s) {
        String maxNumStr = maxNum + "";
        long sLenMaxNum = Long.parseLong(maxNumStr.substring(maxNumStr.length() - s.length()));

        // s 大于 maxNum 对应的位置
        if (sLong > sLenMaxNum) {
            for (int i = 0; i < maxNumStr.length() - s.length(); i++) {
                if (maxNumStr.charAt(i) - '0' > limit) {
                    return false;
                }
            }

            // sChar 均不大于 limit
            return true;
        }
        return false;
    }

    private char sChar[];
    private long memo[];

    /**
     * 数位 DP 模板：记忆化搜索获取合法数字的个数
     * @param curIndex 从 i 位开始填数字
     * @param isLimit 前面填写的数字是否与 sChar 对应上，如果为 true 当前最大为 int(sChar[i])，否则当前最大为 limit
     * @param isNum 前面是否填写过数字（即处理前导零用），如果为 true 当前可以从 0 开始，否则可以 跳过 或 从 1 开始
     * @return 返回合法数字的个数
     */
    private long recursionDigitDP(int curIndex, int limit, boolean isLimit, boolean isNum) {
        /**
         * 仅有此位置最终定义是否合法
         * isNum 为 true 表示得到了一个合法数字
         */
        if (curIndex == sChar.length) {
            return isNum ? 1 : 0;
        }

        // isLimit 为 true（前面与 sChar 一样）以及 isNum 为 false（前面全没有填）仅有一种情况，不需要记忆化
        if (!isLimit && isNum && memo[curIndex] != -1) {
            return memo[curIndex];
        }

        long res = 0;
        // isNum：前面是否填写过数字（即处理前导零用），可以跳过当前数位
        if (!isNum) {
            res = recursionDigitDP(curIndex + 1, limit, false, false);
        }

        /**
         * 判断最大值
         * isLimit：前面填写的数字是否与 sChar 对应上，如果为 true 当前最大为 int(sChar[i])，否则当前最大为 limit（否则就超过 n 啦）
         */
        int up = isLimit ? Math.min(sChar[curIndex] - '0', limit) : limit;
        // 枚举要填入的数字 d，isNum：如果为 true 当前可以从 0 开始，否则可以 跳过 或 从 1 开始
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            // 由于最大值可能小于 sChar[curIndex]，如果这样后面也是随便选因此返回 false
            res += recursionDigitDP(curIndex + 1, limit, isLimit && d == up && up == sChar[curIndex] - '0', true);
        }

        // 记忆化入数组
        if (!isLimit && isNum) {
            memo[curIndex] = res;
        }
        return res;
    }


}
