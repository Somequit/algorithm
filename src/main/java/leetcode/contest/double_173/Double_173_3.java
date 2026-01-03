package leetcode.contest.double_173;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/3 10:29 下午
 */
public class Double_173_3 {

//    public static void main(String[] args) {
//
////        doTest(10);
//        for (int x = 0; x < 1; x++) {
//            doTest((int) (Math.random() * 1000) + 50);
//        }
//    }
//
//    private static void doTest(int n) {
//        System.out.println("n = " + n);
//        Set<int[][]> set = new HashSet<>();
//        int[] a = new int[n];
//        int[] diff = new int[n - 1];
//
//        for (int r = 0; r < (int) (Math.random() * 1000) + 10; r++) {
//            int maskRan = (int) (Math.random() * (n - 1)) + 1;
//            int[][] restrictions = new int[maskRan][2];
//
//            Set<Integer> set2 = new HashSet<>();
//            while (set2.size() < maskRan) {
//                set2.add((int) (Math.random() * (n - 1)) + 1);
//            }
//
//
//            Iterator<Integer> it = set2.iterator();
//            for (int i = 0; i < set2.size(); i++) {
//                restrictions[i][0] = it.next();
//            }
//            set.add(restrictions);
//        }
//
//        dfs(a, 1, n, diff, set);
//        System.out.println(cnt);
//    }
//
//    private static int dfsCnt = 0;
//
//    private static void dfs(int[] a, int curIdx, int endIdx, int[] diff, Set<int[][]> set) {
//        if (dfsCnt > 10000) {
//            return;
//        }
//
//        if (curIdx == endIdx) {
////            System.out.print(Arrays.toString(a) + "\t");
////            System.out.println(Arrays.toString(diff));
//
//            dfsCnt++;
//            doCompare(a, diff, set);
//
//            return;
//        }
//
////        for (int i = -Math.min((int) (Math.random() * a[curIdx - 1]), 10); i < 11; i += (int) (Math.random() * 10) + 1) {
//        for (int i = (int) (Math.random() * 9) + 2; i >= -Math.min((int) (Math.random() * a[curIdx - 1]), 10); i -= (int) (Math.random() * 10) + 1) {
//            if (i == 0) {
//                continue;
//            }
//            a[curIdx] = a[curIdx - 1] + i;
//            diff[curIdx - 1] = Math.abs(i);
//            dfs(a, curIdx + 1, endIdx, diff, set);
//        }
//        if (dfsCnt > 10000) {
//            return;
//        }
//    }
//
//    private static int cnt = 0;
//    private static void doCompare(int[] a, int[] diff, Set<int[][]> set) {
//        int n = a.length;
//        int[] diffTmp = Arrays.copyOf(diff, diff.length);
//        for (int[][] restrictions : set) {
//            for (int i = 0; i < restrictions.length; i++) {
//                restrictions[i][1] = a[restrictions[i][0]];
//            }
//
//
//            for (int i = 0; i < n - 1; i++) {
//                diffTmp[i] = Math.min(10, diff[i] + (int) (Math.random() * 11));
//            }
//            for (int i = 0; i < restrictions.length; i++) {
//                restrictions[i][1] += (int) (Math.random() * 3237);
//            }
//
//            System.out.println(Arrays.toString(a));
//            System.out.println(Arrays.toString(diffTmp));
//            Arrays.stream(restrictions).forEach(r -> System.out.print(Arrays.toString(r) + "\t"));
//            System.out.println();
//
//            int res1 = findMaxVal(n, restrictions, diffTmp);
//            int res2 = findMaxVal2(n, restrictions, diffTmp);
//            if (res1 != res2) {
//                System.out.println("ERROR");
//                throw new NullPointerException();
//            } else {
//                cnt++;
//            }
////            System.out.println(res1 + " : " + res2);
//        }
//    }


    public static int findMaxVal(int n, int[][] restrictions, int[] diff) {
        int[] a = new int[n];
        Arrays.fill(a, Integer.MAX_VALUE);
        a[0] = 0;
        for (int[] r : restrictions) {
            a[r[0]] = r[1];
        }

        for (int i = 1; i < n; i++) {
            a[i] = Math.min(a[i], a[i - 1] + diff[i - 1]);
        }

        int res = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            a[i] = Math.min(a[i], a[i + 1] + diff[i]);
            res = Math.max(res, a[i]);
        }

        return res;
    }

    public static int findMaxVal2(int n, int[][] restrictions, int[] diff) {
        int[] a = new int[n];
        Arrays.fill(a, -1);
        a[0] = 0;
        for (int[] r : restrictions) {
            a[r[0]] = r[1];
        }

        int[] prefixDiff = new int[n];
        for (int i = 1; i < n; i++) {
            prefixDiff[i] = prefixDiff[i - 1] + diff[i - 1];
        }

        int preDiff = 0;
        int prev = 0;
        int maxAIdx = 0;
        for (int i = 1; i < n; i++) {
            preDiff += diff[i - 1];

            if (a[i] > -1) {
                if (a[prev] + preDiff < a[i]) {
                    a[i] = a[prev] + preDiff;

                } else if (a[prev] - preDiff > a[i]) {
                    maxAIdx = i;
                }

                prev = i;
                preDiff = 0;

            }
        }

        preDiff = 0;
        prev = maxAIdx;
        for (int i = maxAIdx - 1; i >= 0; i--) {
            preDiff += diff[i];

            if (a[i] > -1) {
                if (a[prev] + preDiff < a[i]) {
                    a[i] = a[prev] + preDiff;
                }

                prev = i;
                preDiff = 0;

            }
        }

//        System.out.println(Arrays.toString(a));


        int res = 0;
        prev = 0;
        maxAIdx = 0;
        for (int i = 1; i < n; i++) {
            if (a[i] > -1) {
                res = Math.max(res, getMaxVal(prev, i, a[prev], a[i], diff, prefixDiff));

                prev = i;
                maxAIdx = i;
            }
        }


        if (a[n - 1] == -1) {
            int curMax = a[maxAIdx];
            for (int i = maxAIdx; i < n - 1; i++) {
                curMax += diff[i];
                res = Math.max(res, curMax);
            }
        }

        return res;
    }

    private static int getMaxVal(int prevIdx, int curIdx, int prevNum, int curNum, int[] diff, int[] prefixDiff) {
        int res = Math.max(prevNum, curNum);

        for (int i = prevIdx; i < curIdx; i++) {
            if (prevNum + diff[i] == curNum + (prefixDiff[curIdx] - prefixDiff[i + 1])) {
                res = Math.max(res, prevNum + diff[i]);
                break;

            } else if (prevNum + diff[i] < curNum + (prefixDiff[curIdx] - prefixDiff[i + 1])) {
                res = Math.max(res, prevNum + diff[i]);
                prevNum += diff[i];

            } else {
                res = Math.max(res, curNum + (prefixDiff[curIdx] - prefixDiff[i + 1]));
                break;
            }
        }

        return res;
    }


}