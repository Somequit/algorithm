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
public class MinStack {

    private final Deque<Integer> stack;

    private final Deque<Integer> notIncreaseStack;

    /**
     * 空间换时间，在创建一个普通栈的同时创建一个非递增单调栈
     */
    public MinStack() {
        stack = new ArrayDeque<>();
        notIncreaseStack = new ArrayDeque<>();
    }

    /**
     * 普通栈直接入栈顶，仅 val 小于等于非递增单调栈栈顶元素或空栈则进入
     */
    public void push(int val) {
        stack.push(val);
        if (notIncreaseStack.isEmpty() || val <= notIncreaseStack.peekFirst()) {
            notIncreaseStack.push(val);
        }
    }

    /**
     * 普通栈直接栈顶出栈，如果出栈 val 等于非递增单调栈栈顶元素，则其也出栈
     */
    public void pop() {
        int val = stack.pop();
        if (val == notIncreaseStack.peekFirst()) {
            notIncreaseStack.pop();
        }
    }

    /**
     * 返回普通栈直接栈顶元素
     */
    public int top() {
        return stack.peekFirst();
    }

    /**
     * 返回非递增单调栈栈顶元素
     */
    public int getMin() {
        return notIncreaseStack.peekFirst();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
    }
}
