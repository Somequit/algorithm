package codefroces.contest.Codeforces_Round_184_Div2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description
 * @date 2025/11/14 10:34 下午
 */
public class Contest2 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            String s = scanString();

            int res = solve(s);

            print(res);

            t--;
        }
    }

    private static int solve(String s) {
        if (s.length() == 1) {
            return 1;
        }

        int n = s.length();
        int status = -1;
        int leftCnt = 0;
        int rightCnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '<') {
                if (status != -1) {
                    return -1;
                } else {
                    leftCnt++;
                }

            } else if (s.charAt(i) == '*') {
                if (status == -1) {
                    status = 1;

                } else {
                    return -1;
                }
                leftCnt++;
                rightCnt++;

            } else {
                status = 1;

                rightCnt++;
            }
        }

        return Math.max(leftCnt, rightCnt);

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

