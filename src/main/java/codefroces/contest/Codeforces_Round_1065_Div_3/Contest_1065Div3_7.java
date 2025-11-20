package codefroces.contest.Codeforces_Round_1065_Div_3;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/11/18 10:55 下午
 */
public class Contest_1065Div3_7 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] p = scanIntArray(n);

            List<int[]> res = solve(n, p);

            if (res == null) {
                print("No");

            } else {
                print("Yes");
                for (int[] resTemp : res) {
                    printArray(resTemp);
                }
            }

            t--;
        }
    }

    private static List<int[]> solve(int n, int[] p) {
        List<int[]> res = new ArrayList<>();

        Deque<Integer> stackMin = new LinkedList<>();
        stackMin.push(p[0]);
        for (int i = 1; i < n; i++) {
            int curMin = p[i];
            while (!stackMin.isEmpty() && stackMin.peekFirst() < p[i]) {
                res.add(new int[]{p[i], stackMin.peekFirst()});
                curMin = Math.min(stackMin.pollFirst(), curMin);
            }

            stackMin.push(curMin);
        }

        if (stackMin.size() > 1) {
            return null;
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