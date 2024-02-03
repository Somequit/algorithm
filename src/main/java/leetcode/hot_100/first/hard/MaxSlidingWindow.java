package leetcode.hot_100.first.hard;

import utils.AlgorithmUtils;

import java.util.*;

/**
 *
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
 * 滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 * @author gusixue
 * @date 2023/3/24
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int k = AlgorithmUtils.systemInNumberInt();

            int[] res = maxSlidingWindow.maxSlidingWindow(nums, k);
            System.out.println(Arrays.toString(res));

            int[] res2 = maxSlidingWindow.maxSlidingWindowOptimize(nums, k);
            System.out.println(Arrays.toString(res2));

            int[] res3 = maxSlidingWindow.maxSlidingWindowOptimizeSecond(nums, k);
            System.out.println(Arrays.toString(res3));
        }

    }

    /**
     * 考虑单调双端队列（存元素与下标）
     * 保证队头到队尾元素递减，先将 k - 1 个元素以及下标依次按规则放入队尾，
     * 接着依次将后续元素按相同规则放入队尾，放一个后就取出队头元素（不删），
     * 如果该元素下标不在当前滑动窗口内，则删除继续取队头元素，直到元素在窗口内，
     * 此时它就是当前窗口最大元素（不删）
     * 循环上述操作直到最后一个元素入队尾、查到窗口内队头结束。
     * 放入规则是：
     * 如果当前队尾有元素且不大于自己的，则删除队尾元素，直到空或者元素比自己大就放到队尾即可。
     * 解释：
     * 每次放入队尾时，删除不大于自己的元素不会影响后续取查询，因此后续取窗口中只要有删除的元素就一定包含自己，
     * 其次第 k 个元素以及之后的元素每次是一定会进入队尾的，接着查询队头元素删除时最多就是将插入队尾前的所有元素删除（均不在窗口），那么一定不会变空
     *
     * 时间复杂度：O（n）（每个元素最多进出一次），空间复杂度：O（n）
     */
    public int[] maxSlidingWindowOptimizeSecond(int[] nums, int k) {
        // 判空
        if (nums == null || nums.length < k || k <= 0) {
            return new int[0];
        }

        int numsLen = nums.length;
        int[] res = new int[numsLen - k + 1];

        // 单调双端队列，队头到队尾元素递减（保证不扩容）
        Deque<List<Integer>> deque = new ArrayDeque<>(numsLen + 1);


        // 先按规则放 k - 1 个元素到队尾，然后再放一个元素就取一个元素，循环找到当前窗口的队头
        for (int i = 0; i < numsLen; i++) {
            if (i < k - 1) {
                addPollLastDeque(deque, nums[i], i);
            } else {
                addPollLastDeque(deque, nums[i], i);

                res[i - k + 1] = peekAndPollFirstDeque(deque, nums[i], i - k + 1);
            }
            deque.forEach(e -> System.out.print(e.get(0) + ":" + e.get(1) + " "));
            System.out.println();
        }

        return res;
    }

    /**
     * 如果当前队尾有元素且不大于自己的，则删除队尾元素，直到空或者元素比自己大就放到队尾即可。
     */
    private void addPollLastDeque(Deque<List<Integer>> deque, int num, int index) {
        while (deque.peekFirst() != null && deque.peekLast().get(0) <= num) {
            deque.pollLast();
        }
        List<Integer> numList = new ArrayList<>();
        numList.add(num);
        numList.add(index);
        deque.addLast(numList);
    }

    /**
     * 取出队头，如果该元素下标不在当前滑动窗口内，则删除继续取队头元素，直到元素在窗口内，
     * @param begin 滑动窗口开始位置（含）
     */
    private int peekAndPollFirstDeque(Deque<List<Integer>> deque, int num, int begin) {
        while (deque.peekFirst() != null && deque.peekFirst().get(1) < begin) {
            deque.pollFirst();
        }

        return deque.peekFirst().get(0);
    }

    /**
     * 使用 TreeSet 存储滑动窗口内的数字、它底层是红黑树（TreeMap 包装的），可在 O（logn） 时间复杂度添加与删除对应值、找到最大值
     * 但是因为有相同数字、添加只会保留一个数，导致删除时多删了后面的数，因此加入每个数都加上不同的小数，
     * 此时添加与删除都是不同的数，同时比较时、小数不同不会影响整数不分，也不会影响整数的大小比较情况，注意整数相同取哪个都可以，
     * 每个数小数必须不同，因此考虑根据每个数下标来设置，[0, n) 除以大于等于 n 的最小的 10 的次方更加方便与不易有精度问题，
     * 还要注意如果是负数，则需要减去小数，否则负数的整数部分会被改变，
     * 注意个数最大是十万个，所以小数标识最大就是十万，不会超过 int
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 判空
        if (nums == null || nums.length < k || k <= 0) {
            return null;
        }

        int numsLen = nums.length;
        int[] res = new int[numsLen - k + 1];

        // 大于等于 numsLen 的最小的 10 的次方
        int numsLenTen = getMinNumsLenTen(numsLen);

        // 使用 TreeSet 存储滑动窗口、它底层是红黑树（TreeMap 包装的），可在 O（logn） 时间复杂度添加与删除对应值、找到最大值
        TreeSet<Double> slidingWindowSet = new TreeSet<>();
        for (int i = 0; i < k - 1; i++) {
            double numDecimal = getNumDecimal(nums[i], i, numsLenTen);
            slidingWindowSet.add(numDecimal);
        }
        slidingWindowSet.forEach(e -> System.out.print(e + " "));
        System.out.println();

        for (int i = k - 1; i < numsLen; i++) {
            double numDecimal = getNumDecimal(nums[i], i, numsLenTen);
            slidingWindowSet.add(numDecimal);

            slidingWindowSet.forEach(e -> System.out.print(e + " "));
            System.out.println();

            res[i - k + 1] = slidingWindowSet.last().intValue();

            double numPrevDecimal = getNumDecimal(nums[i - k + 1], i - k + 1, numsLenTen);
            slidingWindowSet.remove(numPrevDecimal);
        }

        return res;
    }

    /**
     * 通过下标 i 和除以的数 numsLenTen，以及原数 num，获取调整后的小数
     */
    private double getNumDecimal(int num, int i, int numsLenTen) {
        double res = 0;
        if (num >= 0) {
            res = num + (double)i / numsLenTen;
        } else {
            res = num - (double)i / numsLenTen;
        }
        return res;
    }

    /**
     * 大于等于 numsLen 的最小的 10 的次方
     */
    private int getMinNumsLenTen(int numsLen) {
        int res = 10;
        while (res < numsLen) {
            res *= 10;
        }
        return res;
    }


    /**
     * 在第一种 TreeSet 的基础上，我们可以优化下，TreeSet 直接存储不会相同的下标，然后重写比较器、比较下标对应的数组的值即可，
     * 注意比较器返回 0 会控制删除，需要保证下标相等才返回 0
     */
    public int[] maxSlidingWindowOptimize(int[] nums, int k) {
        // 判空
        if (nums == null || nums.length < k || k <= 0) {
            return null;
        }

        int numLen = nums.length;
        int[] res = new int[numLen - k + 1];

        TreeSet<Integer> numsTreeSet = new TreeSet<>(new Comparator<Integer>() {
            // 不管等于的升序
            @Override
            public int compare(Integer o1, Integer o2) {
                // 不要用加减法、避免越界
                if (nums[o1] > nums[o2]) {
                    return 1;
                } else if (nums[o1] < nums[o2]){
                    return -1;
                } else {
                    return Integer.compare(o1, o2);
                }
            }
        });

        for (int i = 0; i < k - 1; i++) {
            numsTreeSet.add(i);
        }

        for (int i = k - 1; i < numLen; i++) {
            numsTreeSet.add(i);
//            numsTreeSet.forEach(e -> System.out.print(e + " "));
//            System.out.println();

            res[i - k + 1] =  nums[numsTreeSet.last()];

            numsTreeSet.remove(i - k + 1);
        }

        return res;
    }

}
