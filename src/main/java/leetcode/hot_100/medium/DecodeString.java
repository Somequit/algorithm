package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author gusixue
 * @description
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300] 
 * @date 2023/6/28
 */
public class DecodeString {

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        while (true) {
            String s = AlgorithmUtils.systemInString();
            String res = decodeString.solution(s);
            System.out.println(res);
        }
    }

    /**
     * 辅助栈：遍历字符串，出现小写字母直接添加到结果字符串后面，
     * 出现数字则循环将所有数字取出然后后面一定跟着 [，因为原始字符串不包含数字、数字一定正确，将复制次数与当前结果字符串长度入栈，
     * 出现 ] 则出栈，将 上次结果字符串长度 到 当前结果字符串长度 截取出来复制次数减一次添加到结果字符串后面
     * 注意：每次添加都是往后加，所以并不会影响、栈里面存储的结果字符串长度
     * 时间复杂度：O（n），空间复杂度：O（n）辅助栈与结果字符串
     */
    private String solution(String s) {
        // 判空，至少包含一个数字、左右中括号、一个字符，四个元素才能解码
        if (s == null || s.length() <= 3) {
            return s;
        }

        // 存放 [（复制次数_结果字符串开始复制位置下标）的栈
        Deque<String> decodeStack = new LinkedList<>();

        // 循环字符串，[ 入栈，] 出栈复制字符串
        return doDecodeString(s, decodeStack);
    }

    /**
     * 循环字符串，[ 入栈，] 出栈复制字符串
     */
    private String doDecodeString(String s, Deque<String> decodeStack) {
        // 结果字符串
        StringBuffer resultBuffer = new StringBuffer();

        // 循环字符串，[ 入栈，] 出栈复制字符串
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char curChar = s.charAt(i);

            // 英文字母放入结果字符串
            if (isLowerChar(curChar)) {
                resultBuffer.append(curChar);

                // 数字则循环到 [ 结束入栈，因为原始字符串不包含数字、数字一定正确
            } else if(isNumber(curChar)) {
                int count = 0;
                while (isNumber(curChar)) {
                    count = count * 10 + (curChar - '0');
                    i++;
                    curChar = s.charAt(i);
                }
                decodeStack.offerFirst(count + "_" + resultBuffer.length());

                // 只可能为 ]，因为输入的方括号总是符合格式要求
            } else {
                String countIndex = decodeStack.pollFirst();
                int count = Integer.parseInt(countIndex.split("_")[0]);

                // 只需要再复制 count-1 次放入结果字符串结尾
                if (count > 1) {
                    int beginIndex = Integer.parseInt(countIndex.split("_")[1]);
                    String copyString = resultBuffer.substring(beginIndex);
                    for (int j = 1; j < count; j++) {
                        resultBuffer.append(copyString);
                    }
                }

            }
        }
        return resultBuffer.toString();
    }

    private boolean isNumber(char curChar) {
        if (curChar >= '0' && curChar <= '9') {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLowerChar(char curChar) {
        if (curChar >= 'a' && curChar <= 'z') {
            return true;
        } else {
            return false;
        }
    }
}
