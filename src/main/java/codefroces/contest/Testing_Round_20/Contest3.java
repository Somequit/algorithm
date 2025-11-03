package codefroces.contest.Testing_Round_20;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description B. Locate
 * @date 2025/10/21 10:37 上午
 */
public class Contest3 {

    /**
     * 首先我们一定可以通过多次询问找到 [1,n] 的最小范围 [l,r] 使得结果为 数字n-1，即 p[l]=1,p[r]=数字n 或 p[l]=数字n,p[r]=1，此时使用 x 确定左边一种还是右边一种情况，
     * 具体写时，我们仅需要根据 x 确定 数字n 左右，假设 数字n=p[l]，那仅需要二分 [1,n] 为 l，r 固定为 下标n，找到最大的 l 使得 [l,r] 结果为 数字n-1，即可找到 数字n 在下标 l
     */
    public static void main(String[] args) throws IOException {
        String purpose = scanString();

        if (purpose.equals("first")) {
            int t = scanInt();
            while (t > 0) {
                int n = scanInt();
                int[] p = scanIntArray(n);

                int x = solveFirst(n, p);

                print(x);

                t--;
            }

        } else {
            int t = scanInt();
            while (t > 0) {
                int n = scanInt();
                int x = scanInt();

                int res = solveSecond(n, x);

                print("! " + res);

                t--;
            }
        }
    }


    private static int solveFirst(int n, int[] p) {
        for (int num : p) {
            if (num == 1) {
                return 1;

            } else if (num == n) {
                // n 在 1 在左边
                return 0;
            }
        }

        return 0;
    }

    private static int solveSecond(int n, int x) throws IOException {
        int left = 1;
        int right = n;

        int res = left;
        while (left <= right) {
            int middle = (right - left) / 2 + left;

            StringBuilder question = new StringBuilder("? ");
            // n 在 1 在左边
            if (x == 0) {
                question.append(middle);
                question.append(" ");
                question.append(n);
                print(question);

                int subtract = scanInt();
                if (subtract == n - 1) {
                    res = middle;
                    left = middle + 1;

                } else {
                    right = middle - 1;
                }

            } else {
                question.append(1);
                question.append(" ");
                question.append(middle);
                print(question);

                int subtract = scanInt();
                if (subtract == n - 1) {
                    res = middle;
                    right = middle - 1;

                } else {
                    left = middle + 1;
                }
            }
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

