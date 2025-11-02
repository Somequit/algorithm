package leetcode.contest.contest_474;

import java.util.*;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        if (n == 1) {
            return s.charAt(0) <= target.charAt(0) ? "" : s;
        }

        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // 判断出现单个字母
        char singleChar = '0';
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                if (singleChar != '0') {
                    return "";
                }

                singleChar = (char) (i + 'a');
            }
        }
//        System.out.println(singleChar);

        // 单个字母放中间
        int lastIndex = n / 2 - 1;
        char[] res = new char[n];
        Arrays.fill(res, '0');
        if (singleChar != '0') {
            res[n / 2] = singleChar;
            cnt[singleChar - 'a']--;
        }
//        System.out.println(lastIndex);
//        System.out.println(Arrays.toString(res));

        // 按照 target 给 res 的前一半赋值，如果全可以相等再看是否 >target，如果某处只能大于 target 则后面按照字母序放置，如果某处只能小于 target 则此处开始回滚找到最后一个最少大于 target 的位置
        for (int i = 0; i <= lastIndex; i++) {
            int curTargetInt = target.charAt(i) - 'a';
            for (int j = curTargetInt; j < 26; j++) {
                if (cnt[j] == 0) {
                    continue;
                }

                // 可以和 target 相等
                cnt[j] -= 2;
                res[i] = (char) (j + 'a');
                res[n - i - 1] = (char) (j + 'a');
                break;
            }

            // 只能小于 target
            if (res[i] == '0') {
                lastIndex = i - 1;
                break;

                // 只能大于 target 则后面按照字母序放置
            } else if (res[i] > target.charAt(i)) {
                for (int j = 0, k = i + 1; j < 26; j++) {
                    while (cnt[j] > 0) {
                        res[k] = (char) (j + 'a');
                        res[n - k - 1] = (char) (j + 'a');
                        cnt[j] -= 2;
                        k++;
                    }
                }
                break;
            }
        }

        // 无论是否全部赋值，只要不大于 target，则开始回滚找到最后一个最少大于 target 的位置
        if (target.compareTo(new String(res)) >= 0) {
            // 回滚找到最后一个最少大于 target 的位置
            int curIndex = doLexPalindromicPermutation(cnt, n, lastIndex, res, target);
            if (curIndex == -1) {
                return "";
            }

            for (int j = 0, k = curIndex + 1; j < 26; j++) {
                while (cnt[j] > 0) {
                    res[k] = (char) (j + 'a');
                    res[n - k - 1] = (char) (j + 'a');
                    cnt[j] -= 2;
                    k++;
                }
            }
        }

        return new String(res);
    }

    private int doLexPalindromicPermutation(int[] cnt, int n, int end, char[] res, String target) {
        for (int i = end; i >= 0; i--) {
            int curTargetInt = target.charAt(i) - 'a';
            cnt[curTargetInt] += 2;
            res[i] = '0';
            res[n - i - 1] = '0';

            for (int j = curTargetInt + 1; j < 26; j++) {
                if (cnt[j] > 0) {
                    cnt[j] -= 2;
                    res[i] = (char) (j + 'a');
                    res[n - i - 1] = (char) (j + 'a');
                    return i;
                }
            }
        }

        return -1;
    }


}
