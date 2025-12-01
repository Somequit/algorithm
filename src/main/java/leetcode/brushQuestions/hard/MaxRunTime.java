package leetcode.brushQuestions.hard;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author gusixue
 * @description 2141. 同时运行 N 台电脑的最长时间
 * @date 2025/12/1 7:27 上午
 */
public class MaxRunTime {

    /**
     * 二分答案+小于答案的电池相向双指针遍历
     */
    public long maxRunTime(int n, int[] batteries) {
        batteries = Arrays.stream(batteries).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        int m = batteries.length;
        if (m == n) {
            return batteries[m - 1];
        }
//        System.out.println(Arrays.toString(batteries));

        long left = batteries[n - 1];
        long right = 0;
        for (int i = 0; i < m; i++) {
            right += batteries[i];
        }
        right /= n;

        long res = 0;
        while (left <= right) {
            long mid = (right - left) / 2 + left;
            if (doMaxRunTime(n, batteries, m, mid)) {
                res = mid;
                left = mid + 1;

            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    private boolean doMaxRunTime(int n, int[] batteries, int m, long runTime) {
//        System.out.println(runTime);
        int[] batteriesTmp = Arrays.copyOf(batteries, m);
        int left = 0;
        int right = m - 1;
        long runTimeTmp = runTime;
        while (left <= right) {
            while (left < right && batteriesTmp[left] + batteriesTmp[right] < runTimeTmp){
                runTimeTmp -= batteriesTmp[right];
                right--;
            }
//            System.out.println(left + " : " + right + " : " + runTimeTmp);

            if (left == right) {
                if (batteriesTmp[left] < runTimeTmp) {
                    left--;
                }
                break;

            } else {
                batteriesTmp[right] -= (int) (Math.max(runTimeTmp - batteriesTmp[left], 0));
            }
//            System.out.println(Arrays.toString(batteriesTmp));

            runTimeTmp = runTime;
            left++;
        }

        return left >= n - 1;
    }
}
