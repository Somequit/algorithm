package leetcode.brushQuestions.medium;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gusixue
 * @description 955. 删列造序 II
 * @date 2025/12/21 8:58 下午
 */
public class MinDeletionSize {

    public int minDeletionSize(String[] strs) {
        int notDelCnt = 0;

        int delTotal = strs[0].length();
        int strCnt = strs.length;
        List<List<Integer>> listStrEquals = new ArrayList<>();
        listStrEquals.add(IntStream.range(0, strCnt).boxed().collect(Collectors.toList()));
        for (int i = 0; i < strs[0].length(); i++) {

            List<List<Integer>> listStrEqualsTmp = new ArrayList<>();
            boolean curFlag = true;
            for (List<Integer> listTmp : listStrEquals) {

                List<Integer> curList = new ArrayList<>();
                for (int j = 1; j < listTmp.size(); j++) {
                    curList.add(listTmp.get(j - 1));

                    if (strs[listTmp.get(j)].charAt(i) < strs[listTmp.get(j - 1)].charAt(i)) {
                        curFlag = false;
                        break;

                    } else if (strs[listTmp.get(j)].charAt(i) > strs[listTmp.get(j - 1)].charAt(i)) {
                        listStrEqualsTmp.add(curList);
                        curList = new ArrayList<>();
                    }
                }

                if (!curFlag) {
                    break;
                }
                curList.add(listTmp.get(listTmp.size() - 1));
                listStrEqualsTmp.add(curList);
            }

            if (curFlag) {
                notDelCnt++;
                listStrEquals = listStrEqualsTmp;

                if (listStrEquals.size() == strCnt) {
                    notDelCnt += delTotal - i - 1;
                    break;
                }
            }
        }

        return delTotal - notDelCnt;
    }
}
