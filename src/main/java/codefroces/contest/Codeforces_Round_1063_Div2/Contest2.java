package codefroces.contest.Codeforces_Round_1063_Div2;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/10/31 12:34 上午
 */
public class Contest2 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] p = scanIntArray(n);
            String x = scanString();

            List<int[]> res = solve(n, p, x);

            if (res == null) {
                print(-1);

            } else{
                print(res.size());
                for (int i = 0; i < res.size(); i++) {
                    printArray(res.get(i));
                }
            }

            t--;
        }
    }

    private static List<int[]> solve(int n, int[] p, String x) {
        int minIndex = 0;
        int maxIndex = 0;
        boolean[] xOne = new boolean[n];
        int xOneCnt = 0;
        for (int i = 0; i < n; i++) {
            if (x.charAt(i) == '1') {
                xOne[i] = true;
                xOneCnt++;
            }

            if (p[i] > p[maxIndex]) {
                maxIndex = i;
            }

            if (p[i] < p[minIndex]) {
                minIndex = i;
            }
        }

        List<int[]> res = new ArrayList<>();
        maxIndex++;
        minIndex++;
        res.add(new int[]{1, maxIndex});
        res.add(new int[]{maxIndex, n});
        res.add(new int[]{1, minIndex});
        res.add(new int[]{minIndex, n});
        res.add(new int[]{Math.min(minIndex, maxIndex), Math.max(minIndex, maxIndex)});

        for (int i = 0; i < res.size(); i++) {
            for (int j = res.get(i)[0]; j < res.get(i)[1] - 1; j++) {
                int minP = Math.min(p[res.get(i)[0] - 1], p[res.get(i)[1] - 1]);
                int maxP = Math.max(p[res.get(i)[0] - 1], p[res.get(i)[1] - 1]);
                if (xOne[j] && p[j] > minP && p[j] < maxP) {
                    xOne[j] = false;
                    xOneCnt--;
                }
            }
        }

        return xOneCnt == 0 ? res : null;
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