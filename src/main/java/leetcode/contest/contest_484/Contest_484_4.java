package leetcode.contest.contest_484;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/11 10:13 上午
 */
public class Contest_484_4 {
//    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
//    }
    public int maximumAND(int[] nums, int k, int m) {
        int res = 0;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        list.sort(Collections.reverseOrder());

        for (int b = 30; b >= 0; b--) {
            int curMask = (1 << b);

            int maskCnt = list.size();
            List<Integer> listTmp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) < curMask) {
                    maskCnt = i;
                    break;

                } else {
                    listTmp.add(list.get(i) - curMask);
                }
            }


            if (maskCnt >= m) {
                res += curMask;

//                for (int i = 0; i < maskCnt; i++) {
//                    listTmp.add(list.get(i) - curMask);
//                }
                list = listTmp;

            } else {
                long surplus = 0;
                for (int i = maskCnt; i < m; i++) {
                    surplus += curMask - list.get(i);
                }

                if (surplus <= k) {
                    k -= surplus;
                    res += curMask;

                    for (int i = maskCnt; i < m; i++) {
                        listTmp.add(0);
                    }
                    list = listTmp;

                } else {
                    for (int i = maskCnt; i < list.size(); i++) {
                        listTmp.add(list.get(i));
                    }
                    // 可以使用 快速选择算法 优化
                    listTmp.sort(Collections.reverseOrder());
                    list = listTmp;
                }
            }
//            System.out.println(list + " : " + res);

        }

        return res;
    }
}
