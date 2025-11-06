package codefroces.contest.Codeforces_Global_Round_30;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description C. Dungeon
 * @date 2025/10/21 10:37 上午
 */
public class Contest3 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int m = scanInt();
            int[] a = scanIntArray(n);
            int[] b = scanIntArray(m);
            int[] c = scanIntArray(m);

            int res = solve(n, m, a, b, c);

            print(res);

            t--;
        }
    }

    private static int solve(int n, int m, int[] a, int[] b, int[] c) {
        PriorityQueue<Integer> pQueMin = new PriorityQueue<>();
        for (int aNum : a) {
            pQueMin.add(aNum);
        }

        Integer[] indexCnt = new Integer[m];
        for (int i = 0; i < m; i++) {
            indexCnt[i] = i;
        }
        Arrays.sort(indexCnt, Comparator.comparingInt(i -> b[i]));
//        System.out.println(Arrays.toString(indexCnt));
        PriorityQueue<Integer> pQueMax = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int res = 0;
        int bInt = 0;
        while (!pQueMin.isEmpty()) {
            int curDamage = pQueMin.poll();
//            System.out.println("curDamage:" + curDamage);

            while (bInt < m && b[indexCnt[bInt]] <= curDamage) {
//                System.out.println("c[indexCnt[bInt]]:" + c[indexCnt[bInt]]);
                pQueMax.add(c[indexCnt[bInt]]);
                bInt++;
            }

//            System.out.println(pQueMin);
//            System.out.println(pQueMax);

            if (!pQueMax.isEmpty()) {
                int curC = pQueMax.poll();
                res++;

                if (curC > 0) {
                    pQueMin.add(Math.max(curC, curDamage));
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
