package leetcode.brushQuestions.easy;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gusixue
 * @description
 * @date 2024/3/4
 */
public class MyQueue {

    /**
     * 按照栈的方式存储新加元素，当 STACK2 为空时循环加入（模拟成队列）
     */
    private final Deque<Integer> STACK1;
    /**
     * 栈顶为队顶元素，栈为空则将 STACK1 循环加入其中
     */
    private final Deque<Integer> STACK2;

    public MyQueue() {
        STACK1 = new LinkedList<>();
        STACK2 = new LinkedList<>();
    }

    public void push(int x) {
        STACK1.push(x);
    }

    public int pop() {
//        if (empty()) {
//
//        }
        if (STACK2.isEmpty()) {
            stackToStack(STACK1, STACK2);
        }

        return STACK2.pop();
    }

    /**
     * stack1 循环加入 stack2
     */
    private void stackToStack(Deque<Integer> stack1, Deque<Integer> stack2) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }

    public int peek() {
//        if (empty()) {
//
//        }
        if (STACK2.isEmpty()) {
            stackToStack(STACK1, STACK2);
        }

        return STACK2.peek();
    }

    public boolean empty() {
        return STACK1.isEmpty() && STACK2.isEmpty();
    }
}
