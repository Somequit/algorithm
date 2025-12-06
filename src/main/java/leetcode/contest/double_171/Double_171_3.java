package leetcode.contest.double_171;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/6 10:24 下午
 */
public class Double_171_3 {

    public long maxPoints(int[] technique1, int[] technique2, int k) {
        long res = 0;
        int t1LargeCnt = 0;
        List<Integer> listT2LargeSubT1 = new ArrayList<>();
        int n = technique1.length;

        for (int i = 0; i < n; i++) {
            if (technique1[i] >= technique2[i]) {
                res += technique1[i];
                t1LargeCnt++;

            } else{
                res += technique2[i];
                listT2LargeSubT1.add(technique2[i] - technique1[i]);
            }
        }

        k -= t1LargeCnt;
        if (k > 0) {
            Collections.sort(listT2LargeSubT1);
            for (int i = 0; i < k; i++) {
                res -= listT2LargeSubT1.get(i);
            }
        }

        return res;
    }
}
