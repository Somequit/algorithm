package leetcode.contest_vp.contest_379_vp;

import java.util.*;
/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

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
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {

        int res1 = 100;
        //（a，b）与（e，f）同行/同列且中间有（c，d）阻挡
        if ((a == e && a == c && ((b < d && d < f) || ((b > d && d > f))))
                || (b == f && b == d && ((a < c && c < e) || (a > c && c > e)))) {
            res1 = 2;

            // （a，b）与（e，f）不 同行与同列
        } else if (a != e && b != f) {
            res1 = 2;

            //（a，b）与（e，f）同行/同列且中间没有阻挡
        } else {
            res1 = 1;
        }


        int res2 = 100;
        //（c，d）与（e，f）同斜线且中间有（a，b）阻挡
        if (Math.abs(c - e) == Math.abs(d - f) && Math.abs(a - e) == Math.abs(b - f) && Math.abs(a - c) == Math.abs(b - d)
                && Math.abs(a - e) < Math.abs(c - e) && ((a - e < 0 && c - e < 0) || (a - e > 0 && c - e > 0))) {

            res2 = 2;

            //（c，d）与（e，f）同斜线且中间没有阻挡
        } else if (Math.abs(c - e) == Math.abs(d - f)) {
            res2 = 1;

            //（c，d）与（e，f）不同斜线，（c，d）与（e，f）行列和偶数差
        } else if ((Math.abs(c - e) + Math.abs(d - f)) % 2 == 0) {
            res2 = 2;
        }
//        System.out.println(res1);
//        System.out.println(res2);

        return Math.min(res1, res2);
    }


}
