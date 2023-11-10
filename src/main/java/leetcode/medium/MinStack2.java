package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author gusixue
 * @description
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * -2 ^ 31 <= val <= 2 ^ 31 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 10 ^ 4 次
 * @date 2023/11/10
 */
public class MinStack2 {

    private final Deque<Long> stack;
    private int stackMin;

    /**
     * 仅开一个栈，使用一个变量 min 保存当前最小值，但是存入栈里面的数据为 原始数据-min 的值，
     * 将第一个元素先置为 min，其他元素如果大于等于其则减去 min 得到非负数存入栈，最小值 min 不会改变，出栈时发现非负数只需要弹出 元素+min 即可，
     * 如果新加入的元素小于 min，存入的将是减完后的负数，同时改变 min 成新的最小值，出栈时发现负数需要弹出 min，然后 min=min-栈中负数
     */
    public MinStack2() {
        stack = new ArrayDeque<>();
        stackMin = 0;
    }

    /**
     * 将第一个元素先置为 min，存入 0（cur-min=0）
     * 其他元素如果大于等于其则减去 min 得到非负数存入栈，最小值 min 不会改变
     * 如果新加入的元素小于 min，存入的将是减完后的负数，同时改变 min 成新的最小值
     */
    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(0L);
            stackMin = val;

        } else {
            stack.push(0L + val - stackMin);
        }

        stackMin = Math.min(stackMin, val);
    }

    /**
     * 负数需要min=min-栈中负数
     */
    public void pop() {
        if (stack.peekFirst() < 0) {
            stackMin -= stack.peekFirst();
        }
        stack.pop();
    }

    /**
     * 非负数只需要返回 元素+min 即可，
     * 负数需要直接是 min
     */
    public int top() {
        if (stack.peekFirst() >= 0) {
            return (int)(stack.peekFirst() + stackMin);

        } else {
            return stackMin;
        }
    }

    /**
     * 返回维护的最小值
     */
    public int getMin() {
        return stackMin;
    }

    public static void main(String[] args) {
        MinStack2 minStack2 = new MinStack2();
        minStack2.solution(minStack2);
    }

    private void solution(MinStack2 minStack) {
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(stack + " : " + stackMin);

        System.out.println(minStack.getMin());
        System.out.println(stack + " : " + stackMin);

        minStack.pop();
        System.out.println(stack + " : " + stackMin);

        System.out.println(minStack.top());
        System.out.println(stack + " : " + stackMin);

        System.out.println(minStack.getMin());
        System.out.println(stack + " : " + stackMin);
    }
}
