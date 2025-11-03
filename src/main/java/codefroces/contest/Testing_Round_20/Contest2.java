package codefroces.contest.Testing_Round_20;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author gusixue
 * @description A2. Encode and Decode (Hard Version)
 * @date 2025/10/21 10:37 上午
 */
public class Contest2 {

    /**
     * 七个字符代表一个数字
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String purpose = scanString();

        if (purpose.equals("first")) {
            int n = scanInt();
            int[] arr = scanIntArray(n);

            String res = solveFirst(n, arr);

            print(res);

        } else {
            String str = scanString();

            int[] res = solveSecond(str);

            print(res.length);
            printArray(res);
        }
    }

    private static int[] solveSecond(String str) {
        int[] res = new int[str.length() / 7];

        for (int i = 0; i < str.length(); i+=7) {
            int curNum = 0;
            for (int j = i; j < i + 7; j++) {
                curNum += (str.charAt(j) - 'a') * Math.pow(26, j - i);
            }
            res[i / 7] = curNum;
        }

        return res;
    }

    private static String solveFirst(int n, int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int num : arr) {
            int temp = num;
            for (int i = 0; i < 7; i++) {
                stringBuilder.append((char) (temp % 26 + 'a'));
                temp /= 26;
            }
        }

        return stringBuilder.toString();
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

