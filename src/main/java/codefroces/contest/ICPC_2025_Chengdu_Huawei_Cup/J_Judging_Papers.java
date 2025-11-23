package codefroces.contest.ICPC_2025_Chengdu_Huawei_Cup;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/23 4:44 上午
 */
public class J_Judging_Papers {

    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int m = scanInt();
            int k = scanInt();
            int b = scanInt();
            int[][] score = new int[n][m];
            for (int i = 0; i < n; i++) {
                score[i] = scanIntArray(m);
            }

            int res = solve(n, m, k, b, score);

            print(res);

            t--;
        }
    }

    private static int solve(int n, int m, int k, int b, int[][] score) {
        int res = 0;
        int listNeedCnt = 0;

        for (int i = 0; i < n; i++) {
            int totalScore = 0;
            for (int j = 0; j < m; j++) {
                totalScore += score[i][j];
            }

            if (totalScore >= k) {
                res++;

            } else {
                totalScore = 0;

                for (int j = 0; j < m; j++) {
                    if (score[i][j] > 0) {
                        score[i][j]--;

                    } else {
                        score[i][j]++;
                    }

                    totalScore += score[i][j];
                }

                if (totalScore >= k) {
                    listNeedCnt++;
                }
            }
        }

//        System.out.println(listNeedCnt + " : " + res);


        return res + Math.min(b, listNeedCnt);
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
