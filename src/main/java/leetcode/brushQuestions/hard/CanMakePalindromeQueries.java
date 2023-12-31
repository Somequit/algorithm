package leetcode.brushQuestions.hard;

/**
 * @author gusixue
 * @description 100129. 回文串重新排列查询
 * @date 2023/12/31
 */
public class CanMakePalindromeQueries {


    /**
     * Java + 区间相交关系 + 前缀和:
     *
     * 第 1 步：
     * 根据题目可以想到，将一个字符串变成两个字符串，按照"中心对折"方式（后一半字符串反转），判断两字符串相等就是回文串，
     * 接着俩字符串各自有一个区间可以随便排序，即区间外部全等 + 区间内部每种字符相等即可，
     *
     * 第 2 步：
     * "中心对折"其实是将 queries[i] = [ai, bi, ci, di] 的 ci,di 反转，并从 0 开始
     *
     * 第 3 步：
     * 区间外部全等：区间内包含所有不一致的字符，
     * 区间内部每种字符相等：区间内 a-z 个数均一致，
     * 可以先求出（对应不相等字符、对应 a-z 个数）前缀和，然后求差分解决，
     *
     * 第 4 步：
     * 先特判俩字符串字符个数有不相等的，则一定都不能凑成，
     * 在判断区间相交关系，有三种情况
     *     * 区间包含，仅存大区间，仅判断区间内包含所有不一致的字符是否包含全部
     *     * 区间不相交，存两个区间，先判断俩区间内包含所有不一致的字符和是否包含全部，再分别判断区间内 a-z 个数均一致
     *     * 区间相交，先判断合并成单个区间内、包含所有不一致的字符是否包含全部，在判断合并成单个区间内、a-z 个数均一致，
     *     最后看前一个区间能重新排列的字符串1、是否均包含字符串2的前一个区间非重叠的元素
     *
     * 时间复杂度：O（26*(n+m)），空间复杂度：O（26*n）
     */
    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        int len = s.length();
        int mid = len >> 1;

        // "中心对折"其实是将 queries[i] = [ai, bi, ci, di] 的 ci,di 反转，并从 0 开始
        for (int[] querie : queries) {
            int temp2 = querie[2];
            int temp3 = querie[3];
            querie[2] = len + mid - 1 - temp3 - mid;
            querie[3] = len + mid - 1 - temp2 - mid;
        }

        // 均往后移位 1，方便计算前缀和
        // 对应不相等字符
        int[] palDiff = new int[mid + 1];
        // 对应 a-z 个数
        int[][] palWordCount1 = new int[mid + 1][26];
        int[][] palWordCount2 = new int[mid + 1][26];
        for (int i = 0; i < mid; i++) {
            int j = len - i - 1;
            if (s.charAt(i) != s.charAt(j)) {
                palDiff[i + 1] = 1;
            }

            palWordCount1[i + 1][s.charAt(i) - 'a']++;
            palWordCount2[i + 1][s.charAt(j) - 'a']++;
        }
        for (int i = 0; i < mid; i++) {
            palDiff[i + 1] += palDiff[i];

            for (int j = 0; j < 26; j++) {
                palWordCount1[i + 1][j] += palWordCount1[i][j];
                palWordCount2[i + 1][j] += palWordCount2[i][j];
            }
        }

        boolean[] res = new boolean[queries.length];
        // 特判俩字符串字符个数有不相等的，则一定都不能凑成
        for (int i = 0; i < 26; i++) {
            if (palWordCount1[mid][i] != palWordCount2[mid][i]) {
                return res;
            }
        }

        for (int i = 0; i <queries.length; i++) {
            int[] querie = queries[i];

            // 区间包含，仅存大区间，仅判断区间内包含所有不一致的字符是否包含全部
            if ((querie[0] <= querie[2] && querie[1] >= querie[3]) || (querie[2] <= querie[0] && querie[3] >= querie[1])) {
                int left = Math.min(querie[0], querie[2]);
                int right = Math.max(querie[1], querie[3]);

                if (palDiff[right + 1] - palDiff[left] == palDiff[mid]) {
                    res[i] =true;
                }

            // 区间不相交，存两个区间，先判断俩区间内包含所有不一致的字符和是否包含全部，再分别判断区间内 a-z 个数均一致
            } else if (querie[1] < querie[2] || querie[3] < querie[0]) {
                if ((palDiff[querie[1] + 1] - palDiff[querie[0]]) + (palDiff[querie[3] + 1] - palDiff[querie[2]]) != palDiff[mid]) {
                    continue;
                }

                boolean flag = true;
                for (int j = 0; j < 26; j++) {
                    if (palWordCount1[querie[1] + 1][j] - palWordCount1[querie[0]][j] != palWordCount2[querie[1] + 1][j]  - palWordCount2[querie[0]][j]
                            || palWordCount1[querie[3] + 1][j] - palWordCount1[querie[2]][j] != palWordCount2[querie[3] + 1][j]  - palWordCount2[querie[2]][j] ) {
                        flag = false;
                        break;
                    }
                }
                res[i] = flag;

            // 区间相交，先判断合并成单个区间内、包含所有不一致的字符是否包含全部，在判断合并成单个区间内、a-z 个数均一致，
            // 最后看前一个区间能重新排列的字符串1、是否均包含字符串2的前一个区间非重叠的元素
            } else {
                int left = Math.min(querie[0], querie[2]);
                int right = Math.max(querie[1], querie[3]);

                if (palDiff[right + 1] - palDiff[left] != palDiff[mid]) {
                    continue;
                }
                boolean flag = true;
                for (int j = 0; j < 26; j++) {
                    if (palWordCount1[right + 1][j] - palWordCount1[left][j] != palWordCount2[right + 1][j]  - palWordCount2[left][j]) {
                        flag = false;
                        break;
                    }
                }

                // 0 2 1 3 形式
                if (querie[0] < querie[2] || querie[1] < querie[3]) {
                    for (int j = 0; j < 26; j++) {
                        if (palWordCount1[querie[1] + 1][j] - palWordCount1[querie[0]][j] < palWordCount2[querie[2]][j]  - palWordCount2[querie[0]][j]) {
                            flag = false;
                            break;
                        }
                    }

                // 2 0 3 1 形式
                } else {
                    for (int j = 0; j < 26; j++) {
                        if (palWordCount2[querie[3] + 1][j] - palWordCount2[querie[2]][j] < palWordCount1[querie[0]][j]  - palWordCount1[querie[2]][j]) {
                            flag = false;
                            break;
                        }
                    }
                }
                res[i] = flag;
            }

        }

        return res;
    }
}
