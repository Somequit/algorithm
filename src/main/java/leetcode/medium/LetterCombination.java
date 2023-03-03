package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @author gusixue
 * @date 2022/9/6
 */
public class LetterCombination {

    public static void main(String[] args) {
        while (true) {
            String digits = AlgorithmUtils.systemInString();
            LetterCombination letterCombination = new LetterCombination();

            List<String> reslt = letterCombination.solution(digits);
            System.out.println("结果为:" + reslt);
        }
    }

    /**
     * DFS 深度优先搜索
     */
    private List<String> solution(String digits) {
        // 校验参数
        if (digits == null || digits.length() <= 0) {
            return new ArrayList<>();
        }
        // 初始化电话号码
        Map<Integer, String> digitMap = initDigitMap();

        List<String> resultList = new ArrayList<>((int)Math.pow(4, digits.length()));
        // DFS 搜索
        dfsCombination(digits, 0, digitMap, resultList, new StringBuilder());

        return resultList;
    }

    /**
     * DFS 搜索
     * @param digits 输入字符串
     * @param now 当前字符串位置
     * @param digitMap 电话号码，存储每个数字代表的结果
     * @param resultList 添加结果
     * @param resStr 结果字符串
     */
    private void dfsCombination(String digits, int now, Map<Integer, String> digitMap, List<String> resultList,
                                StringBuilder resStr) {
        System.out.println("now:" + now + " resStr:" + resStr);
        if (now >= digits.length()) {
            resultList.add(resStr.toString());
            return;
        }

        int nowDigit = digits.charAt(now) - '0';
        if (!digitMap.containsKey(nowDigit)) {
            System.out.println("输入参数异常 位数:" + now + " 异常数:" + nowDigit);
            return;
        }
        // 找出当前字符代表的结果（多个字符），然后遍历他们
        String digitRes = digitMap.get(nowDigit);
        for (int i = 0; i < digitRes.length(); i++) {
            resStr.append(digitRes.charAt(i));
            dfsCombination(digits, now + 1, digitMap, resultList, resStr);
            resStr.deleteCharAt(now);
        }
    }

    private Map<Integer,String> initDigitMap() {
        Map<Integer, String> digitMap = new HashMap<>();
        digitMap.put(2, "abc");
        digitMap.put(3, "def");
        digitMap.put(4, "ghi");
        digitMap.put(5, "jkl");
        digitMap.put(6, "mno");
        digitMap.put(7, "pqrs");
        digitMap.put(8, "tuv");
        digitMap.put(9, "wxyz");
        return digitMap;
    }
}
