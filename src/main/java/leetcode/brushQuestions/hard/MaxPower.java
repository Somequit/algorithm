package leetcode.brushQuestions.hard;

import ch.qos.logback.core.encoder.EchoEncoder;

import java.util.*;

/**
 * @author gusixue
 * @description 2528. 最大化城市的最小电量
 * @date 2025/11/7 8:09 上午
 */
public class MaxPower {

    /**
     * 滑窗求电量，二分（电量）答案
     * 贪心使 [i,i+2*r] 添加 changePower-power[i]（正数） 电量，使用差分快速求的
     */
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        long[] power = new long[n];

        long curPowerTemp = stations[0];
        long minPower = Long.MAX_VALUE;
        // 滑窗求电量
        for (int i = 0, leftIndex = 0, rightIndex = 1; i < n; i++) {
            while (i - leftIndex > r) {
                curPowerTemp -= stations[leftIndex];
                leftIndex++;
            }
            while (rightIndex < n && rightIndex - i <= r) {
                curPowerTemp += stations[rightIndex];
                rightIndex++;
            }

            power[i] = curPowerTemp;
            minPower = Math.min(minPower, power[i]);
        }
//        System.out.println(Arrays.toString(power));

        if (k == 0) {
            return minPower;
        }

        // 二分（电量）答案
        long left = minPower;
        long right = minPower + k;
        long res = left;

        while (left <= right) {
            long mid = (right - left) / 2 + left;

            if (check(power, r, k, mid)) {
                res = mid;
                left = mid + 1;

            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    private boolean check(long[] power, int r, int k, long changePower) {
        int n = power.length;
        long suffixAdd = 0;
        long suffixSub = 0;
        long[] suffixSubArr = new long[n];
        for (int i = 0; i < n; i++) {
            suffixSub += suffixSubArr[i];

            // 贪心使 [i,i+2*r] 添加 changePower-power[i]（正数） 电量，使用差分快速求的
            if (power[i] + suffixAdd + suffixSub < changePower) {
                long newAdd = changePower - (power[i] + suffixAdd + suffixSub);
                suffixAdd += newAdd;
                if (i + 2 * r + 1 < n) {
                    suffixSubArr[i + 2 * r + 1] -= newAdd;
                }

                k -= newAdd;
                if (k < 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
