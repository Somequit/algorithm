package interview;

import utils.AlgorithmUtils;

import java.util.Arrays;

/**
 * @author gusixue
 * @description
 * 一个数组 nums 长度为 n， 每个元素都是整数
 * 选择一个下标 i (0<=i<n)和一个任意数 x，使得 nums[i] = x
 * 请问最少进行多少次改变，能够使得 nums 的任意一个长度大于 1 的子数组都是非负数：sum(subNums)>=0，subNums 为数组中连续元素，len(subNums) > 1
 * 例子：
 * [1,4,-7,-1,3]
 * 1
 * 解释：
 * nums[2] = 1000000, nums = [1,4,1000000,-1,3]
 * 假设 nums 数组任意子数组元素和均在 int 范围内
 * @date 2023/6/10
 */
public class PositiveSubArray {

    public static void main(String[] args) {
        PositiveSubArray positiveSubArray = new PositiveSubArray();

        for (int i = 0; i < 4000; i++) {
            System.out.println("check " +i);
            positiveSubArray.check();
        }

//        while (true) {
//            int[] nums = AlgorithmUtils.systemInArray();
//            int res = positiveSubArray.solution(nums);
//            System.out.println(res);
//
//            int res2 = positiveSubArray.solution2(nums);
//            System.out.println(res2);
//        }


//        while (true) {
//            int[] nums = AlgorithmUtils.systemInArray();
//            System.out.println(positiveSubArray.checkPositiveSubArray(nums, nums.length));
//            positiveSubArray.getSuffixMin(nums, nums.length - 1);
//        }

//        while (true) {
//            int[] nums = AlgorithmUtils.systemInArray();
//            int res = AlgorithmUtils.systemInNumberInt();
//            System.out.println(positiveSubArray.checkSolution(nums, res));
//        }
    }

    private void check() {
        // 随机获取 n 个元素，范围在 (-100..100)
        int[] nums = randomNums();
        System.out.println(Arrays.toString(nums));

        // 动规获取结果
        int res = solution(nums);
        int res2 = solution2(nums);
        System.out.println(res + ":" + res2 + " (res==res2):" + (res==res2));

        if (res > 0) {
            // 通过递归暴力验证结果是否可行，结果减一是否可行
            boolean resFlag1 = checkPositiveSubArray(nums, res);
            boolean resFlag2 = checkPositiveSubArray(nums, res - 1);
            System.out.println(resFlag1 + ":" + resFlag2);
        } else {
            checkPositiveSubArray(nums, res);
            System.out.println("0 true:false");
        }
        System.out.println();
    }


    private int[] randomNums() {
        int len = (int)(47*Math.random()) + 3;
        System.out.println(len);

        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = (int)(201*Math.random()) - 100;
        }

        return nums;
    }

    /**
     * 二分答案 + dfs 暴力校验：
     * 如果 res 此改变可以满足，那么 res 次改变以上也可以满足，同时最多修改的个数是两种情况的最小值、负数的个数（负数全部修改为正数） 或
     * 总个数除以 2（从第二个元素开始每隔一个元素修改为正数、这样每个规定的子数组一定包含此正数），因此可以二分 [0，min（负数个数，总个数/2）]
     * 验证修改 res 个元素是否可行，先使用 dfs 修改 res 个元素为 int 最大值，再判断每种修改后的情况、判断每个子数组是否非负，只要有一种符合代表结果可行；
     * dfs 修改时从第 2 个元素开始修改（第 1 个元素要形成规定子数组一定需要第 2 个元素），并且不需要修改连续两个元素，
     * 需要判断剩下的元素是否足够修改，同时如果不修改当前节点还需要满足以当前元素为后缀起点的长度大于 1 的最小值为非负数
     */
    private int solution2(int[] nums) {
        // 判空 + 特殊判断
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // 获取最多可以修改的个数，如果没有负数则不需要修改
        int negativeNum = getNegativeNum(nums);
        System.out.println("negativeNum：" + negativeNum);
        int minNum = 0;
        int maxNum = Math.min(negativeNum, nums.length >> 1);
        if (maxNum == 0) {
            return 0;
        }
        System.out.println("minNum：" + minNum + " maxNum：" + maxNum);

        // 二分答案
        return binarySearchAnswer(minNum, maxNum, nums);
    }

    /**
     * 元素负数的个数
     */
    private int getNegativeNum(int[] nums) {
        int negativeNum = 0;
        for (int num : nums) {
            if (num < 0) {
                negativeNum++;
            }
        }
        return negativeNum;
    }

    /**
     * 二分答案，使用 dfs 验证答案
     * @param left 左边缘（含）
     * @param right 右边缘（含）
     */
    private int binarySearchAnswer(int left, int right, int[] nums) {
        int res = left;
        while(left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (checkPositiveSubArray(nums, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    /**
     * 验证修改 res 个元素是否可行，先使用 dfs 修改 res 个元素为 int 最大值，再判断每种修改后的情况、判断每个子数组是否非负，只要有一种符合代表结果可行
     * dfs 修改时从第 2 个元素开始修改，并且不需要修改连续两个元素，需要判断剩下的元素是否足够修改，同时如果不修改当前节点还需要满足以当前元素为后缀起点的长度大于 1 的最小值为非负数
     */
    private boolean checkPositiveSubArray(int[] nums, int res) {
        int len = nums.length;
        boolean flag = recursionNums(nums, 1, len, res);
        System.out.println(res + ":" + flag);
        return flag;
    }

    /**
     * dfs 修改时从第 2 个元素开始修改，并且不需要修改连续两个元素，需要判断剩下的元素是否足够修改，同时如果不修改当前节点还需要满足以当前元素为后缀起点的长度大于 1 的最小值为非负数
     * @param nums 被修改后的数组
     * @param index 当前可能需要修改的下标
     * @param len 总共元素个数
     * @param res 还需要修改的个数
     */
    private boolean recursionNums(int[] nums, int index, int len, int res) {
        if (index >= len) {
//            System.out.println(Arrays.toString(nums) + "：" + res);
            if (res > 0) {
                return false;
            }
            return checkAllSubArray(nums, len);
        }

        boolean flag = false;
        // 修改当前下标，不需要修改连续两个元素，剩下元素必须足够修改
        if (res > 0 && ((len - index - 1) >> 1) >= res - 1) {
            int tmp = nums[index];
            nums[index] = Integer.MAX_VALUE;
            flag = recursionNums(nums, index + 2, len, res - 1);
            nums[index] = tmp;
        }

        // 不修改当前下标，有一个结果成功就直接返回，剩下元素必须足够修改，以当前元素为后缀起点的长度大于 1 的最小值为非负数
        if (!flag && ((len - index) >> 1) >= res && getSuffixMin(nums, index) >= 0) {
            flag = recursionNums(nums, index + 1, len, res);
        }

        return flag;
    }

    /**
     * 以当前元素为后缀起点的长度大于 1 的最小值
     */
    private long getSuffixMin(int[] nums, int index) {
        long suffixMin = nums[index] + nums[index - 1];
        long suffix = nums[index];
        for (int i = index - 1; i >= 0; i--) {
            suffix += nums[i];
            suffixMin = Math.min(suffixMin, suffix);
//            System.out.println(suffix + ":" + suffixMin);
        }
        return suffixMin;
    }

    /**
     * 验证当前数组所有子数组是否全部为非负
     * @return 子数组任一为负数返回 false，否则返回 true
     */
    private boolean checkAllSubArray(int[] nums, int len) {
        for (int i = 0; i < len; i++) {
            long prefix = nums[i];
            for (int j = i + 1; j < len; j++) {
                prefix += nums[j];
                if (prefix < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 动态规划
     * 第一步：从第二个元素开始，以当前元素为后缀起点，获取长度大于一的后缀和的最小值，动态规划方程式：dp[i]=min(dp[i-1],nums[i-1])+nums[i]
     * 第二步：往后遍历，直到 dp[i] 小于 0，则将 nums[i] 置为 inf，此时从 i+1 位置为第一位回到第一步（如果 i+1 是最后一个元素，则结束），因为 i 设置为 inf 后无论任何数加上它都是正数
     * 结果就是置为 inf 的个数
     * 空间压缩：dp 中 i 仅仅与 i-1 相关，同时只有一重循环，因此只需要一个变量 dp 覆盖即可
     * 时间复杂度：O（n），空间复杂度：O（1）
     * @param nums
     * @return
     */
    private int solution(int[] nums) {
        // 判空 + 特殊判断
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // 定义动规变量加初始化
        int dp = nums[0];
        int len = nums.length;

        // 循环数组获取运算结果
        int res = 0;
        for (int i = 1; i < len; i++) {
            dp = Math.min(dp, nums[i - 1]) + nums[i];
            if (dp < 0) {
                res++;
                i++;
                if (i >= len -1) {
                    break;
                }
                dp = nums[i];
            }
        }

        return res;
    }
}
