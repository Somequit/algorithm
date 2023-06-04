package leetcode.hard;

import utils.AlgorithmUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description
 * 301. 删除无效的括号
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 * 1 <= s.length <= 25
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 * @date 2023/6/4
 */
public class RemoveInvalidParentheses {

    public static void main(String[] args) {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        while(true) {
            String s = AlgorithmUtils.systemInString();
            List<String> result = removeInvalidParentheses.solution(s);
            System.out.println(result);
        }
    }

    public List<String> solution(String s) {
        // 判空
        if (s == null || s.length() <= 0) {
            return new ArrayList<>();
        }

        // 查询最少需要删除多少个 0-'(' 和 1-')'
        int[] removeParentheses = getRemoveParentheses(s);
//         System.out.println(Arrays.toString(removeParentheses));
        int[] numParentheses = getNumParentheses(s);
//         System.out.println(Arrays.toString(numParentheses));

        // 递归的方式删除括号，注意保证满足过程中不会出现负数（删除个数正确即可保证最后为 0），剪枝：剩余的括号不够删即回溯、待删除的括号全删完也回溯，注意去重
        Set<String> resultSet = new HashSet<>();
        recursionRemoveParentheses(0, s, removeParentheses, numParentheses, resultSet, new StringBuffer(""), 0);

        return resultSet.stream().collect(Collectors.toList());
    }

    /**
     * 递归的方式删除括号，注意保证满足过程中不会出现负数（删除个数正确即可保证最后为 0），剪枝：剩余的括号不够删即回溯、待删除的括号全删完也回溯（确保过程不会出现负数），注意去重
     * @param index 当前位于下标
     * @param s 字符串
     * @param removeParentheses 当前剩余待删除括号 0-'(' 和 1-')'
     * @param numParentheses 下标以及之后还有多少括号 0-'(' 和 1-')'
     * @param resultList 所有可能的结果(set 去重)
     * @param result 当前删除后的结果，注意递归前添加多少元素回溯就需要删除多少元素，同时开始添加的元素需要在 return 后删除
     * @param val 当前加减 '('-(1) 和 ')'-(-1) 后的结果
     */
    private void recursionRemoveParentheses(int index, String s, int[] removeParentheses, int[] numParentheses,
                                            Set<String> resultList, StringBuffer result, int val) {
//        System.out.println();
//        System.out.println(Arrays.toString(removeParentheses));
//        System.out.println(Arrays.toString(numParentheses));
//        System.out.println(result.toString());
        // 待删除的括号全删完也回溯（确保过程不会出现负数）
        if (removeParentheses[0] == 0 && removeParentheses[1] == 0) {
            checkAddResultList(index, s, resultList, result, val);
            return;
        }

        int indexTemp = 0;
        while (index + indexTemp < s.length() && s.charAt(index + indexTemp) != '(' && s.charAt(index + indexTemp) != ')') {
            result.append(s.charAt(index + indexTemp));
            indexTemp++;
        }

        if (index + indexTemp >= s.length()) {
            resultList.add(result.toString());
            result.delete(result.length() - indexTemp, result.length());
            return ;
        }

        if (s.charAt(index + indexTemp) == '(') {
            // 删除当前 '('
            if (removeParentheses[0] > 0) {
                removeParentheses[0]--;
                numParentheses[0]--;
                recursionRemoveParentheses(index + indexTemp + 1, s, removeParentheses, numParentheses, resultList, result, val);
                removeParentheses[0]++;
                numParentheses[0]++;
            }
            // 不删除当前 '('
            if (removeParentheses[0] <= numParentheses[0] - 1) {
                numParentheses[0]--;
                result.append(s.charAt(index + indexTemp));
                recursionRemoveParentheses(index + indexTemp + 1, s, removeParentheses, numParentheses, resultList, result, val + 1);
                numParentheses[0]++;
                result.deleteCharAt(result.length() - 1);
            }

        } else {
            // 删除当前 ')'
            if (removeParentheses[1] > 0) {
                removeParentheses[1]--;
                numParentheses[1]--;
                recursionRemoveParentheses(index + indexTemp + 1, s, removeParentheses, numParentheses, resultList, result, val);
                removeParentheses[1]++;
                numParentheses[1]++;
            }
            // 不删除当前 ')'
            if (removeParentheses[1] <= numParentheses[1] - 1 && val > 0) {
                numParentheses[1]--;
                result.append(s.charAt(index + indexTemp));
                recursionRemoveParentheses(index + indexTemp + 1, s, removeParentheses, numParentheses, resultList, result, val - 1);
                numParentheses[1]++;
                result.deleteCharAt(result.length() - 1);
            }
        }

        result.delete(result.length() - indexTemp, result.length());
    }

    /**
     * 待删除的括号全删完也回溯（确保过程不会出现负数）
     */
    private void checkAddResultList(int index, String s, Set<String> resultList, StringBuffer result, int val) {
        int indexTemp = 0;
        while (index + indexTemp < s.length()) {
            if (s.charAt(index + indexTemp) == '(') {
                val++;
            } else if (s.charAt(index + indexTemp) == ')') {
                // 不符合条件不会添加
                if (val <= 0) {
                    return;
                }
                val--;
            }
            indexTemp++;
        }
        result.append(s.substring(index));
        resultList.add(result.toString());
        result.delete(result.length() - indexTemp, result.length());
    }

    /**
     * 查询最少需要删除多少个 0-'(' 和 1-')'
     * 假设 '(' 为 1、')' 为 -1，过程中不允许出现负数，结尾为 0
     */
    private int[] getRemoveParentheses(String s) {
        int[] removeParentheses = new int[2];

        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                val++;

            } else if (s.charAt(i) == ')') {
                if (val <= 0) {
                    removeParentheses[1]++;
                } else {
                    val--;
                }
            }
        }
        removeParentheses[0] = val;

        return removeParentheses;
    }

    /**
     * 查询有多少个 0-'(' 和 1-')'
     */
    private int[] getNumParentheses(String s) {
        int[] numParentheses = new int[2];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                numParentheses[0]++;

            } else if (s.charAt(i) == ')') {
                numParentheses[1]++;
            }
        }

        return numParentheses;
    }
}
