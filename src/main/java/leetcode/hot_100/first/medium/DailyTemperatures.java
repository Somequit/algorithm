package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 739. 每日温度
 * 给定一个整数数组 temperatures ，表示每天的温度，
 * 返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 * @date 2023/5/22
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        while(true) {
            int[] temperatures = AlgorithmUtils.systemInArray();

            int[] answer = dailyTemperatures.solution(temperatures);
            System.out.println("answer:" + Arrays.toString(answer));

            int[] answer2 = dailyTemperatures.solutionOptimization(temperatures);
            System.out.println("answer:" + Arrays.toString(answer2));
        }
    }

    /**
     * 倒序单调栈：温度数组倒序加入单调栈，每加一个同时获取当前答案，单调栈中仅存储温度下标并保持对应温度值严格递增
     * 流程：最后一个直接元素入栈，答案为 0，倒数第二个元素开始入栈前，将小于当前元素的元素全部出栈、直到栈空或栈顶元素大于当前元素，
     * 然后当前元素入栈，入栈前如果栈空答案为 0，否则就是栈顶元素下标减去当前下标
     * 解释：栈空代表后面没有元素大于当前元素，答案直接为 0，栈非空代表栈顶元素一定大于当前元素，同时由于倒序入栈、栈顶元素下标一定是大于当前元素中最小下标，
     * 反证法证明，如果存在另一个答案，那此答案下标比栈顶元素下标小，则一定在栈顶元素后入栈，此答案比当前元素值大，则一定不会出栈，那么存在矛盾
     * temperatures.length 为 n，时间复杂度：O（n），空间复杂度：O（n）
     */
    private int[] solutionOptimization(int[] temperatures) {
        // 判空
        if (temperatures == null || temperatures.length <= 0) {
            return new int[0];
        }
        // 单调栈
        Deque<Integer> monotonicStack = new LinkedList<>();
        // 倒序加入单调栈
        int len = temperatures.length;
        int[] answer = new int[len];

        for (int i = len - 1; i >= 0; i--) {
            while (!monotonicStack.isEmpty() && temperatures[i] >= temperatures[monotonicStack.peekFirst()]) {
                monotonicStack.pop();
            }
            if (monotonicStack.isEmpty()) {
                answer[i] = 0;
                monotonicStack.offerFirst(i);
            } else {
                answer[i] = monotonicStack.peekFirst() - i;
                monotonicStack.offerFirst(i);
            }
            System.out.println(monotonicStack);
        }

        return answer;
    }

    /**
     * 链表：由于 temperatures 最多只有 71 个数，但是个数最多有 10^5 个，因此可能存在大量重复温度，可以将相同温度的下标按照升序放入链表数组中，
     * 遍历温度数组，遍历一个元素就在链表数组中删除它、然后确定它的答案，这样每次存在链表中的下标均大于当前遍历的下标，又由于链表中下标升序，
     * 删除：仅删除对应温度的链表头
     * 答案：比较大于此温度的所有链表头（如果存在），最小的下标减去当前下标就是答案
     * temperatures.length 为 n，temperatures 范围为 m，时间复杂度：O（n*m），空间复杂度：O（n）
     *
     */
    private int[] solution(int[] temperatures) {
        // 判空
        if (temperatures == null || temperatures.length <= 0) {
            return new int[0];
        }
        // 存入链表数组
        LinkedList<Integer>[] temperaturesLinked = transferLinkedArrayByTemperatures(temperatures);
        System.out.println(Arrays.toString(temperaturesLinked));

        // 遍历温度数组、删除同时确定答案
        int[] answer = doAnswerByTemperatures(temperatures, temperaturesLinked);

        return answer;
    }

    /**
     * 存入链表数组：数组下标存 temperatures[i] - 30，链表按照升序存下标
     */
    private LinkedList<Integer>[] transferLinkedArrayByTemperatures(int[] temperatures) {
        // temperatures 最多只有 71 个数
        LinkedList<Integer>[] temperaturesLinked = new LinkedList[71];
        for (int i = 0; i < 71; i++) {
            temperaturesLinked[i] = new LinkedList<>();
        }

        int len = temperatures.length;
        for (int i = 0; i < len; i++) {
            int index = temperatures[i] - 30;
            temperaturesLinked[index].offerLast(i);
        }

        return temperaturesLinked;
    }

    /**
     * 遍历温度数组、删除同时确定答案
     */
    private int[] doAnswerByTemperatures(int[] temperatures, LinkedList<Integer>[] temperaturesLinked) {
        int len = temperatures.length;
        int[] answer = new int[len];

        for (int i = 0; i < len; i++) {
            int temperatureTemp = temperatures[i] - 30;
            // 删除：仅删除对应温度的链表头
            temperaturesLinked[temperatureTemp].removeFirst();
            // 比较大于此温度的所有链表头（如果存在），最小的下标减去当前下标就是答案，没有就是 0
            int linkedLen = temperaturesLinked.length;
            int answerTemp = Integer.MAX_VALUE;
            for (int j = temperatureTemp + 1; j < linkedLen; j++) {
                if (!temperaturesLinked[j].isEmpty()) {
                    answerTemp = Math.min(temperaturesLinked[j].peekFirst(), answerTemp);
                }
            }
            answer[i] = answerTemp == Integer.MAX_VALUE ? 0 : answerTemp - i;
        }

        return answer;
    }
}
