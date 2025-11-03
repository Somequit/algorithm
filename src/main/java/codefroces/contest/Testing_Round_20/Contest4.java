package codefroces.contest.Testing_Round_20;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description C. Intercepting Butterflies
 * @date 2025/11/4 4:26 上午
 *
 * 爱丽丝有一个整数𝑥，其中 1≤𝑥≤2¹⁵，她需要将其发送给鲍勃（月球上的一名宇航员），因为这是他们在月球上的秘密项目的一个重要参数。幸运的是，爱丽丝有一个秘密存储设备𝑆，它包含集合 {1,2,…,20} 的一个不一定非空的子集。她计划将𝑆发送给鲍勃。鲍勃的目标是仅使用𝑆来恢复𝑥的值。
 * 然而，在爱丽丝通过宇宙飞船发送集合𝑆之后，在鲍勃收到𝑆之前，魔法蝴蝶拦截了这艘宇宙飞船！当鲍勃最终收到𝑆时，可能发生了以下情况之一：
 *     * 从𝑆中移除了一个任意元素。这只有在𝑆非空时才能进行。
 *     * 向𝑆中添加了一个任意元素。添加后仍需满足𝑆⊆{1,2,…,20}。
 *     * 𝑆保持不变。
 * 请设计一个爱丽丝和鲍勃的策略，让鲍勃无论𝑆发生了什么情况，都能确定𝑥的值。具体来说，在这个问题中，你的代码将在每个测试上恰好运行两次。第一次运行时，你将扮演爱丽丝；第二次运行时，你将扮演鲍勃。除了集合𝑆之外，不能有任何其他信息从爱丽丝传递给鲍勃。要获得 “通过” 的判定，你第二次运行的代码必须能够准确恢复第一次运行时接收到的整数。
 *
 * 第一次运行
 * 输入
 *     * 第一行输入包含字符串 “first”。这是为了让你的程序识别出这是第一次运行，此时它应扮演爱丽丝。
 *     * 第二行输入包含恰好一个整数𝑡（1≤𝑡≤10⁴）—— 测试用例的数量。
 *     * 第𝑖个测试用例的唯一一行包含一个整数𝑥（1≤𝑥≤2¹⁵）。
 * 输出
 *     * 对于每个测试用例，通过按以下方式打印两行来向鲍勃发送𝑆：
 *     * 第一行输出一个整数𝑛（0≤𝑛≤20）——𝑆的大小。
 *     * 第二行输出𝑛个用空格分隔的整数𝑆₁,𝑆₂,…,𝑆ₙ（1≤𝑆ᵢ≤20）。
 *     * 特别地，如果𝑛=0，你可以省略第二行。你可以按任意顺序输出𝑆的元素，但它们必须两两不同。
 *     * 然后，你将继续处理下一个测试用例，或者如果处理完所有测试用例，你的程序必须终止。
 * 第二次运行
 * 输入
 *     * 第一行输入包含字符串 “second”。这是为了让你的程序识别出这是第二次运行，此时它应扮演鲍勃。
 *     * 第二行输入包含恰好一个整数𝑡（1≤𝑡≤10⁴）—— 测试用例的数量。请注意，这个数字与第一次运行输入中的𝑡相等。
 *     * 每个测试用例的第一行包含一个整数𝑛′（0≤𝑛′≤20）—— 鲍勃收到的集合𝑆′的大小，即可能被修改过的𝑆。
 *     * 每个测试用例的第二行包含𝑛个整数𝑆₁′,𝑆₂′,…,𝑆ₙ′（1≤𝑆ᵢ′≤20）—— 鲍勃收到的𝑆′的元素。𝑆′的元素按升序排列，即使原始的𝑆不是按升序排列的。
 *     * 请注意，第二次运行中的测试用例可能是打乱顺序的。更多细节请参见示例输入。
 * 输出
 *     * 对于每个测试用例，打印一行，输出𝑥的值（1≤𝑥≤2¹⁵）。
 */

public class Contest4 {

    /**
     * 如果 S 可重复的话，可以做，现在不正确，详见：Testing_Round_20_2168C
     * S 按规则存 x 的二进制下有哪些位置是 1，且存入 20 个不够使用 20 补充，此时要么被删一个，要么不变
     * 每个数要么仅占 1-15位，要么仅有第16位存在，
     * 接着遍历每一位，查询连续 1 的存在：
     *     如果 i 位 1 不与前后连续，则 s 加入两个 i，避免删除后找不到
     *     如果 （i，j）位有连续 k>1 个 1，则 s 加入 k+1 个 i，最后加入一个 j，删除 i 有 j 找到，删除 j 通过 i 个数找到（也可避免单独的 i 可连续的 i 混淆）
     * 特殊：有以下五个数 20 位放不下，28087 : 110110110110111；28091 : 110110110111011；28123 : 110110111011011；28379 : 110111011011011；30427 : 111011011011011
     * 因此分别使用 20 个 16、17、18、19、20 来表示
     * 此时 S` 依次遍历
     *     如果出现 1 个 i，代表原始为 2 个 i
     *     如果出现 2 个 i，后面存在 j=i+1，则代表原始为 (i,j)，（注意清除掉 j）；否则原始为 2 个 i
     *     如果出现超过 2（如 k 个） 个 i，后面存在单独的 j，则代表原始为 (i,j)，（注意清除掉 j）；否则原始为 (i,i+k-2)
     */
    public static void main(String[] args) throws IOException {

//        for (int i = 1; i <= (1 << 15); i++) {
//            System.out.println(i + " : "+ Integer.toBinaryString(i));
//            System.out.println(Arrays.toString(solveFirst(i)));
//        }

        String purpose = scanString();

        if (purpose.equals("first")) {
            int t = scanInt();
            while (t > 0) {
                int x = scanInt();

                int[] res = solveFirst(x);

                print(res.length);
                printArray(res);

                t--;
            }

        } else {
            int t = scanInt();
            while (t > 0) {
                int n = scanInt();
                int[] s = scanIntArray(n);

                int res = solveSecond(n, s);

                print(res);

                t--;
            }
        }
    }

    private static int[] solveFirst(int x) {
        int[] res = new int[20];

        Map<Integer, Integer> mapSpecial = new HashMap<>();
        mapSpecial.put(28087, 16);
        mapSpecial.put(28091, 17);
        mapSpecial.put(28123, 18);
        mapSpecial.put(28379, 19);
        mapSpecial.put(30427, 20);
        if (mapSpecial.containsKey(x)) {
            Arrays.fill(res, mapSpecial.get(x));
            return res;
        }

        Arrays.fill(res, 20);
        for (int i = 0, left = 0, j = 0; i < 17; i++) {
            if ((x & (1 << i)) > 0) {
                // 当前是 1 下一位不是 1，[left,i] 全是 1
                if ((x & (1 << (i + 1))) == 0) {

                    if (left == i) {
                        res[j] = left + 1;
                        j++;
                        res[j] = left + 1;
                        j++;


                    } else {
                        for (int ll = left; ll <= i + 1; ll++) {
                            res[j] = left + 1;
                            j++;
                        }
                        res[j] = i + 1;
                        j++;

                    }
                }

            } else {
                left = i + 1;
            }
        }

//        if (res[20] != 20) {
//            System.out.println(x + " : "+ Integer.toBinaryString(x));
//        }
//        System.out.println(Arrays.toString(res));

        return res;
    }

    private static int solveSecond(int n, int[] s) {
        Map<Integer, Integer> mapSpecial = new HashMap<>();
        mapSpecial.put(16, 28087);
        mapSpecial.put(17, 28091);
        mapSpecial.put(18, 28123);
        mapSpecial.put(19, 28379);
        mapSpecial.put(20, 30427);
        if (mapSpecial.containsKey(s[0]) && mapSpecial.containsKey(s[n - 1]) && s[0] == s[n]) {
            return mapSpecial.get(s[0]);
        }

        Map<Integer, Integer> mapSCnt = new TreeMap<>();
        for (int i = 0; i < s.length; i++) {
            if (s[i] == 20) {
                break;
            }

            mapSCnt.merge(s[i], 1, Integer::sum);
        }

        int[][] sCnt = new int[mapSCnt.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : mapSCnt.entrySet()) {
            sCnt[index][0] = entry.getKey();
            sCnt[index][1] = entry.getValue();
            index++;
        }

        int res = 0;
        for (int i = 0; i < sCnt.length; i++) {
            if (sCnt[i][1] == 1) {
                res += (1 << (sCnt[i][0] - 1));

            } else if (sCnt[i][1] == 2) {
                if (i + 1 < sCnt.length && sCnt[i + 1][0] == sCnt[i][0] + 1) {
                    res += (1 << (sCnt[i][0] - 1)) + (1 << (sCnt[i][0]));
                    i++;

                } else{
                    res += (1 << (sCnt[i][0] - 1));
                }
            } else {
                if (i + 1 < sCnt.length && sCnt[i + 1][1] == 1) {
                    for (int j = sCnt[i][0]; j <= sCnt[i + 1][0]; j++) {
                        res += (1 << (j - 1));
                    }
                    i++;

                } else {
                    for (int j = sCnt[i][0]; j <= sCnt[i][0] + sCnt[i][1] - 2; j++) {
                        res += (1 << (j - 1));
                    }
                    i++;
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
