package leetcode.hot_100.first.easy;

import utils.AlgorithmUtils;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 20. 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * @author gusixue
 * @date 2022/8/31
 */
public class IsValid {

    /**
     * 匹配的左右字符，注意左右字符位置必须对应
     */
    private static final char[] LEFT_DESIGN = {'(', '{', '['};
    private static final char[] RIGHT_DESIGN = {')', '}', ']'};

    private static final Map<Character, Character> pairs = new HashMap<>();

    static {
        pairs.put('(', ')');
        pairs.put('{', '}');
        pairs.put('[', ']');
    }

    /**
     * 左括号加入栈，右括号去匹配栈顶是否为对应的左括号
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            String brackets = AlgorithmUtils.systemInString();
            IsValid isValid = new IsValid();

//            boolean res = isValid.solution(brackets);
//            System.out.println(res);

            boolean res2 = isValid.solution2(brackets);
            System.out.println(res2);
        }
    }

    private boolean solution(String brackets) {
        // 优化点：长度必须是偶数
        if (brackets == null || brackets.length() <= 0 || brackets.length() % 2 == 1) {
            return false;
        }

        byte[] stack = new byte[brackets.length()];
        int pos = 0;

        for (int i = 0; i < brackets.length(); i++) {
            char bracket = brackets.charAt(i);

            if (charPosition(bracket, LEFT_DESIGN) >= 0) {
                stack[pos] = charPosition(bracket, LEFT_DESIGN);
                pos++;
            } else if (charPosition(bracket, RIGHT_DESIGN) >= 0) {
                if (pos > 0 && stack[pos - 1] == charPosition(bracket, RIGHT_DESIGN)) {
                    pos--;
                } else {
                    System.out.println("结果错误,i:" + i + " bracket:" + bracket + " design:" + RIGHT_DESIGN[stack[pos - 1]]);
                    return false;
                }
            } else {
                System.out.println("输入第" + i + "个字符异常:" + bracket);
                return false;
            }
        }

        if (pos == 0) {
            return true;
        } else {
            System.out.print("结果错误，少了右括号：");
            for (int i = 0;i < pos; i++) {
                System.out.print(RIGHT_DESIGN[stack[i]]);
            }
            System.out.println();
            return false;
        }
    }

    private byte charPosition(char bracket, final char[] design) {
        if (design == null || design.length <= 0) {
            return -1;
        }

        for (int i = 0; i < design.length; i++) {
            if (design[i] == bracket) {
                return (byte)i;
            }
        }

        return -1;
    }

    private boolean solution2(String brackets) {
        // 优化点：长度必须是偶数
        if (brackets == null || brackets.length() <= 0 || brackets.length() % 2 == 1) {
            return false;
        }

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < brackets.length(); i++) {
            char bracket = brackets.charAt(i);

            if (pairs.containsKey(bracket)) {
                stack.addFirst(pairs.get(bracket));
            } else if (!stack.isEmpty() && stack.peek() == bracket) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
