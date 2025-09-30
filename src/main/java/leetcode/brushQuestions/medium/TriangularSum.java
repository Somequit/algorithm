package leetcode.brushQuestions.medium;

import utils.AlgorithmUtils;

import java.math.BigInteger;

/**
 * @author gusixue
 * @description 2221. 数组的三角和
 * @date 2025/9/30 11:16 上午
 */
public class TriangularSum {

    public static void main(String[] args) {
        TriangularSum triangularSum = new TriangularSum();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            int res = triangularSum.triangularSum(nums);
            System.out.println(res);
        }

    }

    /**
     * 打表可得每个元素贡献值符合 杨辉三角 的次数，而杨辉三角某行数据为 C(n-1 ,0) ... C(n-1, n-1)
     *     模拟一下：nums=[a,b,c,d,e]，倒数第二轮是 [a+3b+3c+d,b+3c+3d+e]，相加得到 [a+4b+6c+4d+e]，也是杨辉三角
     * 结果就是：(num[0]*C(n-1, 0) + num[1]*C(n-1, 1) + ... + num[n-1]*C(n-1, n-1)) % 10；而 C(i, j+1) 可以通过 C(i, j) 通过 O(1) 方式获取
     * 因为结果需要 %10，而在求 C(i, j) 需要求除法，因此需要乘 逆元
     *     但是 10 不一定和 i 互质，需要把每个数中的质因子 2 和 5 分离出来，并统计质因子 2 和 5 的个数。
     *     一个数去掉其中所有质因子 2 和 5 之后，得到的整数 a 与 10 互质，这样就可以用欧拉定理计算整数 a 在模 10 下的逆元
     * 当然可以也可以使用高精度...
     * 时间复杂度：O（n），空间复杂度：O（n）
     */
    public int triangularSum(int[] nums) {
        BigInteger triangleRes = new BigInteger("1");
        int n = nums.length;

        int res = triangleRes.intValue() * nums[0];
        for (int i = 1, tmp = 1; i < n; i++, tmp++) {
            triangleRes = triangleRes.multiply(new BigInteger((n - tmp) + "")).divide(new BigInteger(tmp + ""));
//            triangleRes %= 10;
//            System.out.println(triangleRes);

            res += triangleRes.remainder(new BigInteger("10")).intValue() * nums[i];
            res %= 10;
        }

        return res;
    }

}
