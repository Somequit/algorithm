package leetcode.contest.contest_485;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/18 10:26 上午
 */
public class Contest_485_4 {

    private static final List<String> STRS = new ArrayList<>();
    public static void main(String[] args) {
        for (int i = 1; i < 1000000; i++) {
            StringBuilder str = new StringBuilder();
//            dfs(0, i, str);
            int len = (int) (Math.random() * 100) + 2;
            for (int l = 0; l < len; l++) {
                char c = (char) ((int) (Math.random() * 26) + 'a');
                str.append(c);
            }
            STRS.add(str.toString());
        }
//        System.out.println(STRS);

        for (String str : STRS) {
            StringBuilder strTmp = new StringBuilder(str);
            String myRes = new Contest_485_4().lexSmallestAfterDeletion(str);
            String correctRes = new Contest_485_4().lexSmallestAfterDeletionCorrect(strTmp.toString());
            if (!myRes.equals(correctRes)) {
                System.out.println(strTmp + " : " + myRes + " : " + correctRes);
            }
        }
    }

//    private static void dfs(int idx, int end, StringBuilder str) {
//        if (idx == end) {
//            STRS.add(str.toString());
//            return;
//        }
//
//        for (int i = 0; i < 26; i++) {
//            str.append((char) (i + 'a'));
//            dfs(idx + 1, end, str);
//            str.deleteCharAt(str.length() - 1);
//        }
//    }

    public String lexSmallestAfterDeletion(String s) {
        // 每个字符拥有状态：0-未处理，1-不能删，-1-已删除
        int n = s.length();
        int[] status = new int[n];

        // z-a 每个字符尝试删除
        for (int cIdx = 25; cIdx >= 0; cIdx--) {
            int notCnt = 0;
            int lastCanIdx = -1;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) - 'a' == cIdx) {
                    if (status[i] == 0) {
                        lastCanIdx = i;
                        status[i] = -1;

                    } else if (status[i] == 1) {
                        notCnt++;
                    }

                }
            }

            if (notCnt == 0 && lastCanIdx > -1) {
                status[lastCanIdx] = 1;
            }

            // 从后往前遍历，忽略已删除，将 未处理 的点均变成 不能删，直到某个 未处理 的点比前一个点大为止
            int prevNotVal = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (status[i] == -1) {
                    continue;
                }

                if (status[i] == 1) {
                    prevNotVal = s.charAt(i) - 'a';

                } else {
                    if (s.charAt(i) - 'a' > prevNotVal) {
                        prevNotVal = -1;

                    } else {
                        status[i] = 1;
                        prevNotVal = s.charAt(i) - 'a';
                    }
                }
            }

        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (status[i] == 1) {
                res.append(s.charAt(i));
            }
        }

        return res.toString();
    }

    public String lexSmallestAfterDeletionCorrect(String S) {
        char[] s = S.toCharArray();
        int[] left = new int[26];
        for (char ch : s) {
            left[ch - 'a']++;
        }

        StringBuilder st = new StringBuilder();
        for (char ch : s) {
            // 如果 ch 比栈顶小，用 ch 代替栈顶，可以让字典序更小
            while (st.length() > 0 && ch < st.charAt(st.length() - 1) && left[st.charAt(st.length() - 1) - 'a'] > 1) {
                left[st.charAt(st.length() - 1) - 'a']--;
                st.setLength(st.length() - 1);
            }
            st.append(ch);
        }

        // 最后，移除末尾的重复元素
        while (left[st.charAt(st.length() - 1) - 'a'] > 1) {
            left[st.charAt(st.length() - 1) - 'a']--;
            st.setLength(st.length() - 1);
        }

        return st.toString();
    }

}
