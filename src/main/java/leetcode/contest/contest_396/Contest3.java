package leetcode.contest.contest_396;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public int minAnagramLength1(String s) {
        int n = s.length();
        int[] wordCount = new int[26];

        int res = n;
        for (int i = 1; i < n; i++) {
            if (n % i != 0) {
                continue;
            }

            Arrays.fill(wordCount, 0);
            for (int j = 0; j < i; j++) {
                wordCount[s.charAt(j) - 'a']++;
            }

            boolean flag = true;
            for (int k = i; k < n; k += i) {
                int[] wordCountTemp = new int[26];
                for (int j = k; j < k + i; j++) {
                    wordCountTemp[s.charAt(j) - 'a']++;
                    if (wordCountTemp[s.charAt(j) - 'a'] > wordCount[s.charAt(j) - 'a']) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) {
                    break;
                }
            }

            if (flag) {
                res = i;
                break;
            }
        }

        return res;
    }


    public int minAnagramLength(String s) {
        int n = s.length();
        int res = n;

        for (int i = 1; i < n; i++) {
            if (n % i != 0) {
                continue;
            }

            char[] charTemp = s.substring(0, i).toCharArray();
            Arrays.sort(charTemp);
            String strTemp = new String(charTemp);

            boolean flag = true;
            for (int j = i; j < n; j += i) {
                char[] charTemp2 = s.substring(j, j + i).toCharArray();
                Arrays.sort(charTemp2);

                if (!strTemp.equals(new String(charTemp2))) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                res = i;
                break;
            }

        }

        return res;
    }

}
