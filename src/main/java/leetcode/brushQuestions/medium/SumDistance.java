package leetcode.brushQuestions.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 2731. 移动机器人
 * 有一些机器人分布在一条无限长的数轴上，他们初始坐标用一个下标从 0 开始的整数数组 nums 表示。当你给机器人下达命令时，它们以每秒钟一单位的速度开始移动。
 * 给你一个字符串 s ，每个字符按顺序分别表示每个机器人移动的方向。'L' 表示机器人往左或者数轴的负方向移动，'R' 表示机器人往右或者数轴的正方向移动。
 * 当两个机器人相撞时，它们开始沿着原本相反的方向移动。
 * 请你返回指令重复执行 d 秒后，所有机器人之间两两距离之和。由于答案可能很大，请你将答案对 109 + 7 取余后返回。
 * 注意：
 * 对于坐标在 i 和 j 的两个机器人，(i,j) 和 (j,i) 视为相同的坐标对。也就是说，机器人视为无差别的。
 * 当机器人相撞时，它们 立即改变 它们的前进时间，这个过程不消耗任何时间。
 * 当两个机器人在同一时刻占据相同的位置时，就会相撞。
 * 例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 2 并往左移动，下一秒，它们都将占据位置 1，并改变方向。再下一秒钟后，第一个机器人位于位置 0 并往左移动，而另一个机器人位于位置 2 并往右移动。
 * 例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 1 并往左移动，下一秒，第一个机器人位于位置 0 并往左行驶，而另一个机器人位于位置 1 并往右移动。
 * 2 <= nums.length <= 10^5
 * -2 * 10^9 <= nums[i] <= 2 * 10^9
 * 0 <= d <= 10^9
 * nums.length == s.length 
 * s 只包含 'L' 和 'R' 。
 * nums[i] 互不相同。
 * @date 2023/6/11
 */
public class SumDistance {

    public static void main(String[] args) {
        SumDistance sumDistance = new SumDistance();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            String s = AlgorithmUtils.systemInString();
            int d = AlgorithmUtils.systemInNumberInt();
            int res = sumDistance.solution(nums, s, d);
            System.out.println(res);
        }
    }

    /**
     * 宏观来看，碰撞其实相当于机器人相互穿过，只不过机器人变成对方了，但是机器人视为无差别，因此只需要计算每个机器人穿过后的位置、不需要一一对应，因此只需要加/减 d 即可，
     * 机器人两两位置不能暴力，可以升序排序机器人位置，然后计算出每段相邻的距离使用次数、再求和即可，使用次数：该段左边个数乘以右边个数，因为这是两两计算时穿过该段的个数
     * 注意：计算位置一定不能取 mod，内部最好全部使用 long，最后计算每次最好都要 mod
     * 时间复杂度：O（nlogn）主要是排序，空间复杂度：O（n）
     */
    private int solution(int[] nums, String s, int d) {
        // 判空
        if (nums == null || nums.length <= 0 || nums.length != s.length() || d < 0) {
            return 0;
        }

        int mod = 1_000_000_000 + 7;
//        System.out.println(mod);

        // 获取蚂蚁 d 秒后的位置
        int len = nums.length;
        long[] positionAfter = getPositionAfter(nums, len, s, d, mod);
        // System.out.println(Arrays.toString(positionAfter));

        // 蚂蚁位置排序
        Arrays.sort(positionAfter);
        // System.out.println(Arrays.toString(positionAfter));

        // 获取最后结果
        long res = 0;
        for (int i = 1; i < len; i++) {
            res = (Math.abs(positionAfter[i] - positionAfter[i - 1]) % mod * i % mod * (len - i) % mod + res) % mod;
            // System.out.println(res);
        }
        return (int)res;
    }

    /**
     * 注意计算位置一定不能取 mod
     */
    private long[] getPositionAfter(int[] nums, int len, String s, int d, int mod) {
        long[] positionAfter = new long[len];
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'L') {
                positionAfter[i] = ((long)nums[i] - d);
            } else {
                positionAfter[i] = ((long)nums[i] + d);
            }
        }
        return positionAfter;
    }
}
