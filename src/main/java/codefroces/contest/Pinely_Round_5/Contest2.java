package codefroces.contest.Pinely_Round_5;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/10/31 1:59 上午
 */
public class Contest2 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            String[] grids = new String[n];
            for (int i = 0; i < n; i++) {
                grids[i] = scanString();
            }
//            System.out.println(Arrays.toString(grids));
            boolean res = solve(n, grids);
            print(res ? "YES" : "NO");

            t--;
        }
    }

    private static boolean solve(int n, String[] grids) {
        if (n <= 2) {
            return true;
        }

        int firstX = -1;
        int firstY = -1;
        int blackCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grids[i].charAt(j) == '#') {
                    blackCount++;

                    if (firstX == -1) {
                        firstX = i;
                        firstY = j;
                    }
                }
            }
        }

        // 一个或者刚好四个形成方块也可以
        if (blackCount <= 1 || (blackCount == 4 && firstX + 1 < n && firstY + 1 < n
                && grids[firstX].charAt(firstY + 1) == '#'
                && grids[firstX + 1].charAt(firstY) == '#'
                && grids[firstX + 1].charAt(firstY + 1) == '#')) {

            return true;
        }

        // 最上方一个，分别向 左下/下左/右下/下右 扩展到底，判断是否包含原有
        // PS：注意下左时第一个右边可以存在一个元素，可以改第一个元素将右下改成下右、左下改成下左，注意改完后第一个元素可能不存在
        if (extendGridsContain(grids, blackCount, new int[]{firstX, firstY}, new int[]{0, -1}, new int[]{1, 0})
                || extendGridsContain(grids, blackCount, new int[]{firstX, firstY + 1}, new int[]{0, -1}, new int[]{1, 0})
                || extendGridsContain(grids, blackCount, new int[]{firstX, firstY}, new int[]{0, 1}, new int[]{1, 0})
                || extendGridsContain(grids, blackCount, new int[]{firstX, firstY - 1}, new int[]{0, 1}, new int[]{1, 0})) {

            return true;
        }

        return false;
    }

    private static boolean extendGridsContain(String[] grids, int blackCount, int[] first, int[] move1, int[] move2) {
        int n = grids.length;
        int x = first[0];
        int y = first[1];

        if (x >= 0 && x < n && y >= 0 && y < n && grids[x].charAt(y) == '#') {
            blackCount--;
        }
        while (true) {
            x += move1[0];
            y += move1[1];
//            System.out.println(x + " : " + y);
            if (x >= 0 && x < n && y >= 0 && y < n) {
//                System.out.println(grids[x].charAt(y));
                if (grids[x].charAt(y) == '#') {
                    blackCount--;
                }
            } else {
                break;
            }
            if (blackCount == 0) {
                return true;
            }

            x += move2[0];
            y += move2[1];
//            System.out.println(x + " : " + y);
            if (x >= 0 && x < n && y >= 0 && y < n) {
//                System.out.println(grids[x].charAt(y));
                if (grids[x].charAt(y) == '#') {
                    blackCount--;
                }
            } else {
                break;
            }

            if (blackCount == 0) {
                return true;
            }
        }

        return false;
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
