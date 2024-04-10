package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 1702. 修改后的最大二进制字符串
 * @date 2024/4/10
 */
public class MaximumBinaryString {

    /**
     * 贪心：0 可以左移，n 个 0 可以变成 n-1 个 1 最后加一个 0，
     * 因此如果有超过 1 个 0 则变成全 1 ，接着将（第一个 0 下标 + 0个数 - 1）的下标置为 0
     */
    public String maximumBinaryString(String binary) {
        int firstZeroIndex = 0;
        int zeroCount = 0;
        for (char c : binary.toCharArray()) {
            if (c == '0') {
                zeroCount++;
            }

            if (zeroCount == 0) {
                firstZeroIndex++;
            }
        }

        if (zeroCount < 2) {
            return binary;
        }

        StringBuilder s = new StringBuilder();
        for (int i=0; i<binary.length(); i++) {
            if (i == zeroCount + firstZeroIndex - 1) {
                s.append('0');
            } else {
                s.append('1');
            }
        }

        return s.toString();
    }
}
