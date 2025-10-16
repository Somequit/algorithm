package leetcode.brushQuestions.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gusixue
 * @description 3003. 执行操作后的最大分割数量
 * @date 2025/10/17 1:34 上午
 */
public class MaxPartitionsAfterOperations {

    /**
     * 首先使用 dp 求出每个后缀的段数，dp[i] = dp[j] + 1，(j > i) 且 [i, j) 恰好形成一段（也有看到可以证明将后缀倒序当做前缀求），
     * 其次如果 k=26 一定只有一段，因此以下均为 k<26，求出不改变任何元素时每段的起始位置、字母个数与每个字母起始下标，注意细节
     * 接着修改分为两种情况：
     *     修改 i 导致正前方分段、即 i 前面刚好有 k 种字符，此时 i 不能改成前面出现过的，其他都可能试试（不能改为不重复字符原因：前面一段+后面一段可能包含 26 种字母）
     *         此时由于每一段中越靠前修改一定越优，因此仅需要找到第一个可以修改的地方即可
     *         同时修改后求当前段结尾，最多就将下一段找完（否则下一段也不会划分成一段），因此时间也就 2*n*26
     *     修改 i 导致后面某处分段，可直接保证改成当前段的不重复元素
     *         此时每一段中相同字符最先出现处修改一定不差于后面的地方修改，因此找到每个字母起始下标修改即可，时间也就 26*n
     *         注意如果该段某字母仅出现一次那么修改与否都不会增加结果，因此不用修改
     * 时间复杂度：O(26*n)，空间复杂度：O（26*n）
     */
    public int maxPartitionsAfterOperations(String s, int k) {
        if (k == 26) {
            return 1;
        }

        int n = s.length();
        int[] suffixPartCountDP = new int[n + 1];
        int[] charCount = new int[26];
        int curCharNum = 0;

        // 获取后缀段数
        for (int i = n - 1, r = n - 1; i >= 0; i--) {
            int curIndex = s.charAt(i) - 'a';
            charCount[curIndex]++;

            if (charCount[curIndex] == 1) {
                curCharNum++;

                while (curCharNum > k) {
                    charCount[s.charAt(r) - 'a']--;

                    if (charCount[s.charAt(r) - 'a'] == 0) {
                        curCharNum--;
                    }
                    r--;
                }
            }

            suffixPartCountDP[i] = suffixPartCountDP[r + 1] + 1;
        }
//        System.out.println(Arrays.toString(suffixPartCountDP));

        // 不改动时 s 分段
        Arrays.fill(charCount, 0);
        curCharNum = 0;
        // 0-段开始下标、段结束下标(闭区间)，1-26个字母个数，2-26个字母起始下标
        List<int[][]> partList = new ArrayList<>();
        partList.add(new int[][]{{0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});
        for (int i = 0, curPartNum = 0; i < n; i++) {
            int curIndex = s.charAt(i) - 'a';
            charCount[curIndex]++;

            if (charCount[curIndex] == 1) {
                curCharNum++;

                if (curCharNum > k) {
                    partList.get(curPartNum)[0][1] = i - 1;
//                    System.out.println(" : " + Arrays.toString(charCount));

                    partList.get(curPartNum)[1] = charCount.clone();
                    // charCount 多加的退出去
                    partList.get(curPartNum)[1][curIndex] = 0;

                    Arrays.fill(charCount, 0);
                    charCount[curIndex] = 1;
                    curCharNum = 1;
                    curPartNum++;
                    partList.add(new int[][]{{i, i}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});
//                    partList.get(curPartNum)[0][0] = i;
//                    partList.get(curPartNum)[0][1] = i;
                    // charCount 少加的加回来
                    partList.get(curPartNum)[1][curIndex] = 1;
                    partList.get(curPartNum)[2][curIndex] = i;
                    continue;

                } else {
                    partList.get(curPartNum)[2][curIndex] = i;
                }
            }

            if (i == n - 1) {
                partList.get(curPartNum)[0][1] = i;
//                System.out.println(" : " + Arrays.toString(charCount));
                partList.get(curPartNum)[1] = charCount.clone();
            }
        }
//        partList.forEach(arr -> Arrays.stream(arr).forEach(arr2 -> System.out.println(Arrays.toString(arr2))));

        int res = partList.size();
        // i 前方分段，此时 i 不能改成前面出现过的，其他都可能试试（同时前面一段+后面一段可能包含 26 种字母）
        for (int l = 0; l < partList.size(); l++) {
            int[][] partArr = partList.get(l);

            Arrays.fill(charCount, 0);
            curCharNum = 0;
            for (int i = partArr[0][0]; i <= partArr[0][1]; i++) {
                int curIndex = s.charAt(i) - 'a';
                charCount[curIndex]++;

                if (charCount[curIndex] == 1) {
                    curCharNum++;

                    // 前面已包含 k 种元素，每一段中越靠前修改一定越优
                } else if (curCharNum == k) {
                    // 改成前面未出现过
                    for (int c = 0; c < 26; c++) {
                        if (charCount[c] == 0) {
                            // r 是 i 前方分段，i 改为当前段也未出现过的字母后，当前段结束下标（不含）
                            // 最多就将下一段找完
                            int r = getCurPartStopIndex(c, i + 1, n, k, s);
                            res = Math.max(res, l + 2 + suffixPartCountDP[r]);
//                            System.out.println("i 前方分段：" + i + " : " + (r - 1) + " : " + l + " : " + suffixPartCountDP[r]);
                        }
                    }
                    break;
                }

            }
        }

        // i 后面分段，可直接保证改成当前段不重复元素
        for (int l = 0; l < partList.size(); l++) {
            int[][] partArr = partList.get(l);

            for (int c = 0; c < 26; c++) {
                // 某字母仅出现一次那么修改与否都不会增加结果
                if (partArr[1][c] > 1) {

                    Arrays.fill(charCount, 0);
                    curCharNum = 0;
                    for (int i = partArr[0][0]; i < n; i++) {
                        // 相同字符最先出现处修改一定不差于后面的地方修改
                        if (i == partArr[2][c]) {
                            curCharNum++;
                            continue;
                        }

                        int curIndex = s.charAt(i) - 'a';
                        charCount[curIndex]++;
                        if (charCount[curIndex] == 1) {
                            curCharNum++;

                            if (curCharNum > k) {
//                                System.out.println("i 后方分段：" + partArr[0][0] + " : " + (i - 1) + " : " + l + " : " + suffixPartCountDP[i]);
                                res = Math.max(res, l + 1 + suffixPartCountDP[i]);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    private int getCurPartStopIndex(int c, int startIndex, int stopIndex, int k, String s) {
        int[] charCount = new int[26];
        charCount[c]++;
        int curCharNum = 1;
        for (int i = startIndex; i < stopIndex; i++) {
            int curIndex = s.charAt(i) - 'a';

            charCount[curIndex]++;
            if (charCount[curIndex] == 1) {
                curCharNum++;

                if (curCharNum > k) {
                    return i;
                }
            }
        }

        return stopIndex;
    }
}
