package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * @author gusixue
 * @date 2023/1/25
 */
public class GenerateParenthesis {

    public static void main(String[] args) {

        while (true) {
            int parNum = AlgorithmUtils.systemInNumberInt();

            GenerateParenthesis generateParenthesis = new GenerateParenthesis();
            List<String> res = generateParenthesis.solution(parNum);

            System.out.println(res);
        }
    }

    /**
     * 左括号代表 1，右括号代表 -1，然后每个位置添加左括号或者右括号，满足以下条件
     * 1. 从 0 开始计算，每个位置均为非负数
     * 2. 左右括号均不能超过 parNum 个，且用完
     */
    private List<String> solution(int parNum) {

        // 判空
        if (parNum <= 0) {
            return new ArrayList<>();
        }

        List<String> resPar = new ArrayList<>();
        StringBuilder nowPar = new StringBuilder("(");

        // 递归生成结果
        generate(resPar, nowPar, parNum - 1, parNum, 1);

        return resPar;
    }

    /**
     * 递归生成结果
     * @param nowPar 当前结果
     * @param leftNum 剩余左括号的个数
     * @param rightNum 剩余右括号的个数
     * @param nowVal 当前的值，每个位置均为非负数
     */
    private void generate(List<String> resPar, StringBuilder nowPar, int leftNum, int rightNum, int nowVal) {
        if (rightNum == 0) {
            resPar.add(nowPar.toString());
        } else if (leftNum == 0) {
            generate(resPar, nowPar.append(")"), leftNum, rightNum - 1, nowVal - 1);
        } else {
            StringBuilder nowParTemp = new StringBuilder(nowPar);
            generate(resPar, nowParTemp.append("("), leftNum - 1, rightNum, nowVal + 1);

            if (nowVal > 0) {
                generate(resPar, nowPar.append(")"), leftNum, rightNum - 1, nowVal - 1);
            }
        }
    }
}
