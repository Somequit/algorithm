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
     * @return
     */
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long sLong = Long.parseLong(s);

        return doNumberOfPowerfulInt(finish, limit, sLong, s) - doNumberOfPowerfulInt(start - 1, limit, sLong, s);
    }

    private long doNumberOfPowerfulInt(long maxNum, int limit, long sLong, String s) {
        if (sLong > maxNum) {
            return 0;
        }

        sChar = (maxNum + "").substring(0, (maxNum + "").length() - s.length()).toCharArray();
//        System.out.println("sChar:" + new String(sChar));
        int m = sChar.length;

        // 记忆化搜索：前 i 位
        memo = new long[m];
        // 初始化，-1 表示没有计算过
        Arrays.fill(memo, -1);

        long res = recursionDigitDP(0, limit, true, false) + 1;

        if (checkNotEquals(maxNum, limit, sLong, s)) {
            res--;
        }
        System.out.println("res:" + res);

        return res;
    }

    private boolean checkNotEquals(long maxNum, int limit, long sLong, String s) {
        String maxNumStr = maxNum + "";
        long sLenMaxNum = Long.parseLong(maxNumStr.substring(maxNumStr.length() - s.length()));
//        System.out.println("sLenMaxNum:" + sLenMaxNum);

        if (sLong > sLenMaxNum) {
            for (int i = 0; i < maxNumStr.length() - s.length(); i++) {
                if (maxNumStr.charAt(i) - '0' > limit) {
//                    System.out.println("maxNumStr.charAt(i):" + maxNumStr.charAt(i));
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    private char sChar[];
    private long memo[];

    /**
     * 数位 DP 模板：记忆化搜索获取合法数字的个数
     * @param curIndex 从 i 位开始填数字
     * @param isLimit 前面填写的数字是否与 s 对应上，如果为 true 当前最大为 int(s[i])，否则当前最大为 limit
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

        // isLimit 为 true（前面与 s 一样）以及 isNum 为 false（前面全没有填）仅有一种情况，不需要记忆化
        if (!isLimit && isNum && memo[curIndex] != -1) {
            return memo[curIndex];
        }

        long res = 0;
        // isNum：前面是否填写过数字（即处理前导零用），可以跳过当前数位
        if (!isNum) {
            res = recursionDigitDP(curIndex + 1, limit, false, false);
        }

        /**
         * 判读最大值
         * isLimit：前面填写的数字是否与 s 对应上，如果为 true 当前最大为 int(s[i])，否则当前最大为 limit（否则就超过 n 啦）
         */
        int up = isLimit ? Math.min(sChar[curIndex] - '0', limit) : limit;
        // 枚举要填入的数字 d，isNum：如果为 true 当前可以从 0 开始，否则可以 跳过 或 从 1 开始
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            res += recursionDigitDP(curIndex + 1, limit, isLimit && d == up && up == sChar[curIndex] - '0', true);
        }

        // 记忆化入数组
        if (!isLimit && isNum) {
            memo[curIndex] = res;
        }
        return res;
    }


}
