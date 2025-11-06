package codefroces.contest.Codeforces_Global_Round_30;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description D Copy String
 * @date 2025/10/21 10:37 上午
 */
public class Contest4 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int k = scanInt();
            String s = scanString();
            String tt = scanString();

            String[] res = solve(n, k, s, tt);

            if (res == null) {
                print(-1);

            } else {
                print(res.length - 1);
                for (int i = 1; i < res.length; i++) {
                    print(res[i]);
                }
            }

            t--;
        }
    }

    public static String[] solve(int n, int k, String s, String tt) throws IOException {
        List<Integer>[] arrListCharIndex = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            arrListCharIndex[i] = new ArrayList<Integer>();
        }
        int[] charIndexLast = new int[26];
        Arrays.fill(charIndexLast, -1);
        for (int i = 0; i < n; i++) {
            arrListCharIndex[s.charAt(i) - 'a'].add(i);
            charIndexLast[s.charAt(i) - 'a']++;
        }

        int[] changeIndex = new int[n];
        int curTTIndex = n - 1;
        int maxCnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            int ttNum = tt.charAt(i) - 'a';

            while (charIndexLast[ttNum] > -1 && arrListCharIndex[ttNum].get(charIndexLast[ttNum]) > curTTIndex) {
                charIndexLast[ttNum]--;
            }
            if (charIndexLast[ttNum] == -1) {
                return null;
            }

            changeIndex[i] = arrListCharIndex[ttNum].get(charIndexLast[ttNum]);
            maxCnt = Math.max(maxCnt, i - changeIndex[i]);

            if (changeIndex[i] < curTTIndex) {
                curTTIndex = changeIndex[i];
            }
            if (curTTIndex == i) {
                curTTIndex--;
            }
        }
//        System.out.println(Arrays.toString(changeIndex));

        if (maxCnt > k) {
            return null;
        }

        String[] res = new String[maxCnt + 1];
        res[0] = s;
        for (int i = 1; i < maxCnt + 1; i++) {
            StringBuilder curStrB = new StringBuilder();
            curStrB.append(res[i - 1].charAt(0));

            for (int j = 1; j < n; j++) {
                if (j - changeIndex[j] >= i) {
                    curStrB.append(res[i - 1].charAt(j - 1));

                } else {
                    curStrB.append(res[i - 1].charAt(j));
                }
            }

            res[i] = curStrB.toString();
        }

        return res;
    }

    static int MOD = 1_000_000_007;
    static int INF = (int) 1e9;
    static long fact[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int scanInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static long scanLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    static String scanString() throws IOException {
        return nextToken();
    }

    static String nextToken() throws IOException {
        if (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static int[] scanIntArray(int size) throws IOException {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanInt();
        }
        return array;
    }

    static long[] scanLongArray(int size) throws IOException {
        long array[] = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanLong();
        }
        return array;
    }

    static void printArray(int arr[]) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int e : arr) {
            sb.append(e + " ");
        }
        bw.write(sb.toString().trim());
        bw.newLine();
        bw.flush();
    }

    static void print(Object o) throws IOException {
        bw.write(o.toString());
        bw.newLine();
        bw.flush();
    }
}
