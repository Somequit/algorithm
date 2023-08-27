package leetcode.contest.contest_360;


import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            List<Integer> receiver = AlgorithmUtils.systemInList();
            int k = AlgorithmUtils.systemInNumberInt();

            long res = contest.solution(receiver, k);
            System.out.println(res);
        }

    }

    public long solution(List<Integer> receiver, long k) {
        int n = receiver.size();

        // 2^i 次后 当前下标 - f（x）的值
        long[][] valueReceive = new long[n][2];
        // 初始化 2^0 次
        for (int i = 0; i < n; i++) {
            valueReceive[i][0] = receiver.get(i);
            valueReceive[i][1] = receiver.get(i);
        }

        // k 次后 当前下标 - f（x）的值
        long[][] targetValueReceive = new long[n][2];
        // 初始化 0 次
        for (int i = 0; i < n; i++) {
            targetValueReceive[i][0] = i;
            targetValueReceive[i][1] = i;
        }

        long[][] valueReceiveTemp = new long[n][2];
        while (k > 0) {
            if ((k & 1) == 1) {
                for (int i = 0; i < n; i++) {
                    int curPos = (int)targetValueReceive[i][0];
                    targetValueReceive[i][0] = valueReceive[curPos][0];
                    targetValueReceive[i][1] += valueReceive[curPos][1];

                }
            }
//            AlgorithmUtils.systemOutArray(valueReceive);
//            AlgorithmUtils.systemOutArray(targetValueReceive);
//            System.out.println();

            k >>= 1;

            for (int i = 0; i < n; i++) {
                valueReceiveTemp[i][0] = valueReceive[i][0];
                valueReceiveTemp[i][1] = valueReceive[i][1];
            }
            for (int i = 0; i < n; i++) {
                int curPos = (int)valueReceive[i][0];
                valueReceive[i][0] = valueReceiveTemp[curPos][0];
                valueReceive[i][1] += valueReceiveTemp[curPos][1];
            }
        }

        long res = 0L;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, targetValueReceive[i][1]);
        }
//        AlgorithmUtils.systemOutArray(targetValueReceive);
        return res;
    }


}
