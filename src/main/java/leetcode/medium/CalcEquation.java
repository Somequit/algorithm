package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description
 * 399. 除法求值
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个
 * Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 * @date 2023/5/24
 */
public class CalcEquation {

    public static void main(String[] args) {
        CalcEquation calcEquation = new CalcEquation();
        while(true) {
            List<List<String>> equations = Arrays.stream(AlgorithmUtils.systemInTwoArrayString())
                    .map(Arrays::asList).collect(Collectors.toList());
            double[] values = AlgorithmUtils.systemInArrayDouble();
            List<List<String>> queries = Arrays.stream(AlgorithmUtils.systemInTwoArrayString())
                    .map(Arrays::asList).collect(Collectors.toList());

            double[] res = calcEquation.solution(equations, values, queries);
            System.out.println(Arrays.toString(res));
        }
    }

    /**
     * 并查集：将 equations 与 values 的值放入并查集中：parent(equations[i][0])=equations[i][1] 代表 equations[i][0]/equations[i][1]，
     * 同时设置：parentValue(equations[i][0])=values[i]、parentValue(equations[i][1])=1
     * 并查集必须路径压缩：parent(son)=father 与 parent(father)=grandfather 压缩为：parent(son)=grandfather
     * 新parentValue(son)=旧parentValue(son) * parentValue(father)（=son/father * father/grandfather）
     * 合并两个并查集：son1、son2 需要先路径压缩：ancestor1 = parent(son1)，ancestor2 = parent(son2)，
     * 再合并两个元素的祖先：parent(ancestor1)=ancestor2，然后计算值：parentValue(ancestor1)=value / parentValue(son1) * parentValue(son2)
     * (=son1/son2 / son1/ancestor1 * son2/ancestor2)
     * 循环 queries 分类讨论搜索答案，其实是求出：queries[0]/queries[1]，分类讨论：
     *     如果 queries[0] 或 queries[1] 任一元素不再任何并查集中，则返回 -1.0（equations 没有出现过）
     *     如果 queries[0] 或 queries[1] 不再同一个并查集中，则返回 -1.0（相同并查集中元素代表可以通过计算获得比例，不同并查集代表无法产生交集）
     *     否则 计算 parentValue(queries[0])/parentValue(queries[1]) 就是答案（（queries[0]/queries[根]）/（queries[1]/queries[根]））
     * equations.length（== values.length）为 n、queries.length 为 m，equations 里不同字符的个数为 x，时间复杂度：O（(n+m)*α(x)），空间复杂度：O（x）
     */
    public double[] solution(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 判空
        if (equations == null || values == null || queries == null || equations.size() != values.length) {
            return new double[0];
        }
        // 将 equations 与 values 的值放入并查集中
        int len = values.length;
        Map<String, String> parent = new HashMap<>((len / 3) << 2);
        Map<String, Double> parentValue = new HashMap<>((len / 3) << 2);
        for (int i = 0; i < len; i++) {
            // equations 新参数初始化：指向自己，值为 1.0
            initEquations(equations.get(i).get(0), parent, parentValue);
            initEquations(equations.get(i).get(1), parent, parentValue);
            union(equations.get(i).get(0), equations.get(i).get(1), parent, values[i], parentValue);
            // System.out.println(parent);
            // System.out.println(parentValue);
        }

        // 循环 queries 分类讨论搜索答案，其实是求出：queries[0]/queries[1]，分类讨论
        double[] answer = new double[queries.size()];
        int index = 0;
        for (List<String> queriesSingle : queries) {
            answer[index] = getAnswerByUnionFind(parent, queriesSingle.get(0), queriesSingle.get(1), parentValue);
            index++;
        }

        return answer;
    }

    private void initEquations(String factor, Map<String,String> parent, Map<String,Double> parentValue) {
        if (!parent.containsKey(factor)) {
            parent.put(factor, factor);
            parentValue.put(factor, 1.0);
        }
    }

    private void union(String son, String father, Map<String,String> parent, double value, Map<String, Double> parentValue) {
        String sonAncestor = find(son, parent, parentValue);
        String fatherAncestor = find(father, parent, parentValue);
        parent.put(sonAncestor, fatherAncestor);
        parentValue.put(sonAncestor, value / parentValue.get(son) * parentValue.get(father));
    }

    private String find(String factor, Map<String,String> parent, Map<String, Double> parentValue) {
        if (!parent.containsKey(factor)) {
            return null;
        }

        if (factor.equals(parent.get(factor))) {
            return factor;
        }
        String ancestor = find(parent.get(factor), parent, parentValue);
        // 路径压缩
        parentValue.put(factor, parentValue.get(factor) * parentValue.get(parent.get(factor)));
        parent.put(factor, ancestor);

        return ancestor;
    }

    private double getAnswerByUnionFind(Map<String,String> parent, String q0, String q1, Map<String, Double> parentValue) {
        if (q0 == null || q1 == null) {
            return -1.0;
        } else if (find(q0, parent, parentValue) == null || find(q1, parent, parentValue) == null) {
            return -1.0;
        } else if (!find(q0, parent, parentValue).equals(find(q1, parent, parentValue))) {
            return -1.0;
        } else {
            return parentValue.get(q0)/parentValue.get(q1);
        }
    }


}
