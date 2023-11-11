package leetcode.brushQuestions;

import utils.AlgorithmUtils;

/**
 * 319. 灯泡开关
 * 初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭第二个。
 * 第三轮，你每三个灯泡就切换第三个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换第 i 个灯泡的开关。
 * 直到第 n 轮，你只需要切换最后一个灯泡的开关。
 * 找出并返回 n 轮后有多少个亮着的灯泡。
 */
public class BulbSwitch {

    public static void main(String[] args) {
        for (int i = 1; i < 31624; i++) {
            int result = solution(i * i);
            if (result != i) {
                System.out.println("error:" + result + " : " + i);
            }
        }
        while (true) {
            int n = AlgorithmUtils.systemInNumberInt();
            System.out.println(solution(n));
        }
    }

    /**
     * 模拟算法、0-暗、1-亮，可以使用"异或"，异或 1 代表改变开关、异或 0 代表未改变开关
     * 因此n为 6 方法如下：0 ^ 0b111111 ^ 0b010101 ^ 0b001001 ^ 0b000100 ^ 0b000010 ^ 0b000001
     *
     * 易得、第i位灯泡是亮还是暗，取决于 i 这个数字对[1 ~ n]整除的个数是奇数还是偶数，转化为 i 的公约数是奇数个还是偶数个
     * 此时、质数只能被 1 和他本身整除、则结果一定为 0，合数则模拟线性筛计算[3 ~ n]中合数是奇数个还是偶数个
     * 注意0、1以及乘法溢出即可
     *
     * 但是使用线性筛过程中，发现如果相同俩数乘积等于 n 的情况下，就只开关一次、否则一定开关两次变回原状态
     * 易得、结果其实就是[1 ~ n]^2 <= n 的个数，转换一下就是n的开方下取整
     * 注意需要加上 0.5 避免sqrt(3.99999)等这类浮点精度问题
     */
    private static int solution(int n) {
        int result = (int)Math.sqrt(n + 0.5);
        return result;
    }
}
