package codefroces.contest.Codeforces_Round_1065_Div_3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description
 * @date 2025/11/18 10:55 下午
 */
public class Contest_1065Div3_8 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] a = scanIntArray(n);
            int[] b = scanIntArray(n);

            long[] res = solve(n, a, b);

            prlongArray(res);

            t--;
        }
    }

    private static long[] solve(int n, int[] a, int[] b) {
        // b 数组尽量 先除 后 部分减1，再除在减
        long[] res = new long[2];
        res[1] = 1;

        boolean divFlag = true;
        int[] subCnt = new int[n];
        long subTotal = 0;

        while (divFlag) {
            subTotal = 0;
            Arrays.fill(subCnt, 0);
            for (int i = 0; i < n; i++) {
                if (b[i] > a[i] && (b[i] & 1) == 1) {
                    subCnt[i]++;
                    subTotal++;
                }

                if ((b[i] >> 1) < a[i]) {
                    divFlag = false;
                    break;
                }
            }

            if (!divFlag) {
                break;
            }

//            System.out.println(subTotal);
            // 除法
            res[0] += subTotal + 1;
            for (int i = 0; i < n; i++) {
                b[i] >>= 1;
            }

            // 减法序列
            // 达到 mod 阶乘会包含 素数mod 的因子，结果一定为 0
            if (subTotal >= MOD) {
                res[1] = 0;
                continue;
            }

            res[1] = res[1] * FAC[(int) subTotal] % MOD;
            for (int i = 0; i < n; i++) {
                res[1] = res[1] * INV_FAC[subCnt[i]] % MOD;
            }
        }

        // 最后减法序列
        subTotal = 0;
        Arrays.fill(subCnt, 0);
        for (int i = 0; i < n; i++) {
            subCnt[i] = b[i] - a[i];
            subTotal += subCnt[i];
        }

        res[0] += subTotal;

        // 达到 mod 阶乘会包含 素数mod 的因子，结果一定为 0
        if (subTotal >= MOD) {
            res[1] = 0;
            return res;
        }

        res[1] = res[1] * FAC[(int) subTotal] % MOD;
        for (int i = 0; i < n; i++) {
            res[1] = res[1] * INV_FAC[subCnt[i]] % MOD;
        }

        return res;
    }

    // 组合数模板
    private static final int MOD = 1_000_003;
    private static final int MX = 1_000_003;
    // 阶乘
    private static final long[] FAC = new long[MX];
    // 阶乘的逆元
    private static final long[] INV_FAC = new long[MX];

    static {
        FAC[0] = 1;
        for (int i = 1; i < MX; i++) {
            FAC[i] = FAC[i - 1] * i % MOD;
        }

        INV_FAC[MX - 1] = qPow(FAC[MX - 1], MOD - 2, MOD);
        for (int i = MX - 1; i > 0; i--) {
            INV_FAC[i - 1] = INV_FAC[i] * i % MOD;
        }
    }

    private static long qPow(long value, long pow, long mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res *= value;
                res %= mod;
            }

            value *= value;
            value %= mod;
            pow >>= 1;
        }
        return res;
    }


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

    static void prlongArray(long arr[]) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (long e : arr) {
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