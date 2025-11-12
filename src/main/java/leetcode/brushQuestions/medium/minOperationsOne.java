package leetcode.brushQuestions.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author gusixue
 * @description 2654. 使数组所有元素变成 1 的最少操作次数
 * @date 2025/11/12 7:05 下午
 */
public class minOperationsOne {

    /**
     * TODO:GSX:
     * 参考：3171. 找到按位或最接近 K 的子数组，使用滑动窗口+栈 O（n）解决
     */
    public int minOperations(int[] nums) {
        int n = nums.length;
        int cnt1 = 0;
        int gcdAll = nums[0];
        int right = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                cnt1++;
            }

            gcdAll = gcd(gcdAll, nums[i]);

            if (gcdAll == 1 && right == -1) {
                right = i;
            }
        }
        if (gcdAll > 1) {
            return -1;
        }
        if (cnt1 > 0) {
            return n - cnt1;
        }
//        System.out.println(n + ":" + right);

        int minGcdSize = right;
        Deque<Integer> stackLeftIntervalGcd = new ArrayDeque<>();
        int stackBottle = right;
        int left = right;
        gcdAll = nums[right];
        // [0, right] 倒序入栈，直到 1 就不入栈，因为 gcd([0, right])==1
        for (; left >= 0; left--) {
            gcdAll = gcd(gcdAll, nums[left]);

            if (gcdAll == 1) {
                minGcdSize = right - left;
                break;
            }

            stackLeftIntervalGcd.push(gcdAll);
        }
        left++;
        right++;
//        System.out.println(stackLeftIntervalGcd);

        // gcd([stackBottle+1,right])
        int rightGcd = nums[right == n ? 0 : right];
        for (; right < n; right++) {
            // 右元素入栈，直到 [left,right] 等于 1
            rightGcd = gcd(rightGcd, nums[right]);
//            System.out.println(rightGcd);

            // 左元素出栈，直到栈空或者 [left,right] 大于 1
            while (!stackLeftIntervalGcd.isEmpty() && gcd(rightGcd, stackLeftIntervalGcd.peekFirst()) == 1) {
                minGcdSize = Math.min(minGcdSize, right - left);
                stackLeftIntervalGcd.pop();
                left++;
            }
//            System.out.println(left + " : " + stackBottle + " : " + right);
//            System.out.println(stackLeftIntervalGcd);

            // 栈空，[stackBottle, right] 倒序入栈，直到 1 就不入栈，因为 gcd(stackBottle, rightGcd)==1 才会将空栈
            if (stackLeftIntervalGcd.isEmpty()) {
                gcdAll = nums[right];
                for (left = right; left >= stackBottle; left--) {
                    gcdAll = gcd(gcdAll, nums[left]);

                    if (gcdAll == 1) {
                        minGcdSize = Math.min(minGcdSize, right - left);
                        rightGcd = nums[right == n - 1 ? 0 : right + 1];
                        stackBottle = right;
                        break;
                    }

                    stackLeftIntervalGcd.push(gcdAll);
                }

                left++;
//                System.out.println(left + " : " + stackBottle + " : " + right);
//                System.out.println(stackLeftIntervalGcd);
            }

        }

        return n - 1 + minGcdSize;
    }

    private static int gcd(int a,int b){
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}
