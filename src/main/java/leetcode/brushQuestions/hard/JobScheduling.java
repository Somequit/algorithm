package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 1235. 规划兼职工作
 * @date 2024/5/4
 */
public class JobScheduling {

    /**
     * 三个数组统一按照 endTime 升序，接着遍历三数组，二分 endTime数组 找到小于等于 startTime[i] 的最大值，将对应的 totalProfit 值加到当前 profit[i]，
     * 结果与 endTime数组最后（也是最大的）totalProfit 比较，大于其才将 endTime[i] 加入 endTime数组，保证 endTime数组 （totalProfit 与 profit）递增
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Integer[] arrIndexs = new Integer[n];
        Arrays.setAll(arrIndexs, i -> i);

        Arrays.sort(arrIndexs, (int1, int2) -> endTime[int1] - endTime[int2]);
//        System.out.println(Arrays.toString(arrIndexs));

        // endTime、totalProfit
        List<int[]> listEndProfit = new ArrayList<>();
        listEndProfit.add(new int[]{0, 0});

        for (int arrIndex : arrIndexs) {
            int listIndex = binaryLessEquals(listEndProfit, startTime[arrIndex]);

            int curProfit = listEndProfit.get(listIndex)[1] + profit[arrIndex];

            if (curProfit > listEndProfit.get(listEndProfit.size() - 1)[1]) {
                // 保证 endTime数组 中 endTime 不重复
                if (endTime[arrIndex] == listEndProfit.get(listEndProfit.size() - 1)[0]) {
                    listEndProfit.get(listEndProfit.size() - 1)[1] = curProfit;

                } else {
                    listEndProfit.add(new int[]{endTime[arrIndex], curProfit});
                }

            }

        }

        return listEndProfit.get(listEndProfit.size() - 1)[1];
    }

    private int binaryLessEquals(List<int[]> listEndProfit, int target) {
        int left = 0;
        int right = listEndProfit.size() - 1;
        int res = 0;

        while (left <= right) {
            int mid = (right - left) / 2 + left;

            if (listEndProfit.get(mid)[0] > target) {
                right = mid - 1;

            } else if (listEndProfit.get(mid)[0] < target) {
                left = mid + 1;
                res = mid;

            } else {
                res = mid;
                break;
            }
        }

        return res;
    }
}
