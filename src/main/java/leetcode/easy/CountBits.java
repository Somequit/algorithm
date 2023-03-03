package leetcode.easy;

import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 338. 比特位计数
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * 0 <= n <= 10^5
 * 很容易就能实现时间复杂度为 O(nlogn) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
 * @author gusixue
 * @date 2023/2/21
 */
public class CountBits {

    public static void main(String[] args) {
        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();

            CountBits countBits = new CountBits();

            int[] res = countBits.solution(n);
            System.out.println(Arrays.toString(res));

            int[] res2 = countBits.solution2(n);
            System.out.println(Arrays.toString(res2));
        }
    }

    /**
     * 偏招：我们知道 n 最大就是十万，因此可以通过打表的方式，直接算出来存入数组等容器里，然后查询每个数只需要 O(1) 的时间复杂度
     * 解法一：首先找规律、将前 15 个数的二进制写出来，可以找到是 0 结果就是 0，大于 0 则需要减去前一个数转化为二进制后，
     * 从末尾向前数连续 1 的个数再加 1，这时问题转化为求每个数末尾向前连续 1 的个数，然后思考二进制方式：(n^(n+1))>>1，
     * 将末尾连续的 1 取出来，然后加 1 得到 2 的次方，这个次方就是连续 1 的个数，可以再打表存储 2 的次方即可（由于指数爆炸、存储的数据较少）
     */
    private int[] solution(int n) {
        int[] res = new int[n + 1];

        Map<Integer, Integer> powerNum = new HashMap<>();
        for (int i = 1, j = 0; i <= 100000 ; i<<=1, j++) {
            powerNum.put(i, j);
        }
//        powerNum.entrySet().forEach(entry -> {System.out.println(entry.getKey() + ":" + entry.getValue());});

        for (int i = 1; i <= n; i++) {
            res[i] = res[i - 1] - continuityNum(i - 1, powerNum) + 1;
        }

        return res;
    }

    /**
     * O（1）时间复杂度转化为二进制后，从末尾向前数连续 1 的个数
     */
    private int continuityNum(int i, Map<Integer, Integer> powerNum) {
        return powerNum.get(((i ^ (i + 1)) >> 1) + 1);
    }

    /**
     * 解法二：0 结果即是 0，大于 0 时（如 n），我们已知 res[0, n-1] 的结果，
     * 可以使用 n>>1，此时我们知道 res[n>>1] 的结果，最后加上被移除的那个位置 1 的个数（0 个或者 1 个）
     * 可以使用 n&(n-1) 将最后一位 1 变成 0，此时我们知道 res[n&(n-1)] 的结果，最后加 1
     */
    private int[] solution2(int n) {
        int[] res = new int[n + 1];

        for (int i = 1; i <= n; i++) {
//            res[i] = res[i >> 1] + (i & 1);
            res[i] = res[i & (i - 1)] + 1;
        }

        return res;
    }

}
