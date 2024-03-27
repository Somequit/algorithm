package leetcode.brushQuestions.medium;

import java.util.Arrays;

/**
 * @author gusixue
 * @description 1997. 访问完所有房间的第一天
 * @date 2024/3/28
 */
public class FirstDayBeenInAllRooms {

    /**
     * 当第一次走到 i 时，
     *     [0, i) 一定经过偶数次，否则区间只能不懂或者往前，只有偶数次才会 +1 往后，
     *     i 经过了奇数次，注意从第 1 天开始算，此时在位置 0、它经过了 1 次，
     *     (i, n-1] 没有走过，
     * 接着第二天（+1）移动到 nextVisit[i]，和之前一样的次数后、第二次走到 i，此时 [0, i) 一定也经过偶数次、第二天（+1）就第一次走到 i+1，
     * 和之前一样的次数：step[i] 代表从 0 走到 i 需要多少天，而从 nextVisit[i] 走到 i 需要 （step[i] - step[nextVisit[i]]）天，
     * 最后加上上述两次 +1，就构成结果
     */
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int mod = (int) (1e9 + 7);
        int n = nextVisit.length;

        int[] step = new int[n];

        for (int i = 0; i < n - 1; i++) {
            step[i + 1] = ((step[i] * 2 - step[nextVisit[i]] + 2) % mod + mod) % mod;
        }

//        System.out.println(Arrays.toString(step));

        return step[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(test(new int[]{0,0,0,0,0,0,0,0,0,9,2,8})));
    }

    /**
     * 暴力验证
     */
    private static int[] test(int[] nextVisit) {
        int n = nextVisit.length;

        int[] step = new int[n];
        boolean[] stepOdd = new boolean[n];

        int curStep = 1;
        int curIndex = 0;
        for (int i = 1; i < n; i++) {
            while (curIndex != i) {
                stepOdd[curIndex] = !stepOdd[curIndex];
                if (stepOdd[curIndex]) {
                    curIndex = nextVisit[curIndex];

                } else {
                    curIndex++;
                }

                curStep++;
            }

            step[i] = curStep;
        }

        return step;
    }
}
