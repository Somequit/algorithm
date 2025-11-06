package codefroces.contest.Codeforces_Global_Round_30;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description B Even Modulo Pair
 * @date 2025/10/21 10:37 上午
 */
public class Contest2 {

    /**
     * 如果出现两个偶数 x < y，则一定符合条件，因为取模本质上是 y 不断减去 x 直到不能再减，可看做事偶数不断减去偶数，最后结果一定是偶数，因此最多 1 个偶数，
     * 考虑两个奇数 x < y，如果 y < 2x 则一定出现偶数，因为此时 y%x = y-x 结果一定是偶数，因此每一个 奇数x 存在，要不出现偶数则 y>2x，z>2y...，因此最多也就 30多个元素
     */
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] a = scanIntArray(n);

            int[] res = solve(n, a);

            if (res[0] == -1) {
                print(-1);

            } else {
                printArray(res);
            }

            t--;
        }
    }

    public static int[] solve(int n, int[] a) throws IOException {
        int[] res = new int[]{-1, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (((a[i] % a[j]) & 1) == 0) {
                    return new int[]{a[j], a[i]};
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
