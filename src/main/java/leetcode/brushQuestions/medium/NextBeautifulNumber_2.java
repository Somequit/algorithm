package leetcode.brushQuestions.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 2048. 下一个更大的数值平衡数
 * @date 2025/10/24 6:34 下午
 */
public class NextBeautifulNumber_2 {

//    private static final TreeSet<Integer> BEAUTIFUL_NUM;

    static {
//        int[] tempArrs = new int[]{1, 22, 122, 333, 1333, 4444, 14444, 22333, 55555, 122333, 155555, 224444, 666666};
//        BEAUTIFUL_NUM = new TreeSet<>();
//        BEAUTIFUL_NUM.add(1224444);
//        for (int tempArr : tempArrs) {
//            String tempStr = tempArr + "";
//            boolean[] vis = new boolean[6];
//            dfs(tempStr, new StringBuilder(), vis);
//        }

//        System.out.println(BEAUTIFUL_NUM);
    }

    /**
     * 获取 tempArr 所有排列组合
     */
//    private static void dfs(String tempStr, StringBuilder curNumStr, boolean[] vis) {
//        if (curNumStr.length() == tempStr.length()) {
//            BEAUTIFUL_NUM.add(Integer.parseInt(curNumStr.toString()));
//            return;
//        }
//
//        for (int i = 0; i < tempStr.length(); i++) {
//            if (!vis[i]) {
//                vis[i] = true;
//                curNumStr.append(tempStr.charAt(i));
//                dfs(tempStr, curNumStr, vis);
//                curNumStr.deleteCharAt(curNumStr.length() - 1);
//                vis[i] = false;
//            }
//        }
//    }

    /**
     * 按照题意从小到大仅有这些数和其排列组合满足要求（按照结果位数）：1，2，1+2，3，1+3，4，1+4，2+3，5，1+2+3，1+5，2+4，6，1224444，打表后直接顺序判断即可
     * 时间复杂度：O（1），空间复杂度：O（1）
     */
    public int nextBeautifulNumber(int n) {
        int[] beautifulNum = new int[]{1, 22, 122, 212, 221, 333, 1333, 3133, 3313, 3331, 4444, 14444, 22333, 23233,
                23323, 23332, 32233, 32323, 32332, 33223, 33232, 33322, 41444, 44144, 44414, 44441, 55555, 122333,
                123233, 123323, 123332, 132233, 132323, 132332, 133223, 133232, 133322, 155555, 212333, 213233, 213323,
                213332, 221333, 223133, 223313, 223331, 224444, 231233, 231323, 231332, 232133, 232313, 232331, 233123,
                233132, 233213, 233231, 233312, 233321, 242444, 244244, 244424, 244442, 312233, 312323, 312332, 313223,
                313232, 313322, 321233, 321323, 321332, 322133, 322313, 322331, 323123, 323132, 323213, 323231, 323312,
                323321, 331223, 331232, 331322, 332123, 332132, 332213, 332231, 332312, 332321, 333122, 333212, 333221,
                422444, 424244, 424424, 424442, 442244, 442424, 442442, 444224, 444242, 444422, 515555, 551555, 555155,
                555515, 555551, 666666, 1224444};

        for (int curNum : beautifulNum) {
            if (curNum > n) {
                return curNum;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        for (int d = 1; d < 46; d++) {
            System.out.println(d + " : ");

            dfs(d, new StringBuilder(), 9);

            System.out.println();
        }
    }

    private static void dfs(int d, StringBuilder stringBuilder, int curNum) {
        if (d == 0) {
            System.out.println(stringBuilder.toString());
            return;
        }
        if (curNum == 0 || d < 0) {
            return;
        }

        stringBuilder.append(curNum);
        dfs(d - curNum, stringBuilder, curNum - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        dfs(d, stringBuilder, curNum - 1);
    }
}
