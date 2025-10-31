package codefroces.contest.Pinely_Round_5;

import java.io.*;
import java.util.*;

/**
 * @author gusixue
 * @description D. Locked Out
 * @date 2025/10/31 1:59 上午
 */
public class Contest4 {
    public static void main(String[] args) throws IOException {
        int t = scanInt();

        while (t > 0) {
            int n = scanInt();
            int[] nums = scanIntArray(n);
            int res = solve(nums, n);
            print(res);
            t--;
        }


    }

    /**
     * 按照 numIndex-(nums[i], i) 双升序，放在值域上跑 dp，dp[j] 代表依次添加 一个到全部的j=nums[i] 时 numIndex[j] 处最多保留多少元素、保留 其中最大值为 maxDP，结果为 n-maxDP
     * 跑 dp 时 重复的nums[i] 需要与值域中（如果存在） 重复的nums[i]-1 中最大的dp[nums[i]-1]一起处理，因此每一段开始等于上一段最大的dp[nums[i]-1] +1；还可以前一段全不要
     */
    private static int solve(int[] nums, int n) {
        int maxNum = nums[0];
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        List<Integer>[] numIndexsList = new List[maxNum + 1];

        for (int i = 0; i < n; i++) {
            if (numIndexsList[nums[i]] == null) {
                numIndexsList[nums[i]] = new ArrayList<>();
            }

            numIndexsList[nums[i]].add(i);
        }

        // 输出结果验证
//        Arrays.stream(numIndexsList).forEach(arr -> System.out.print(arr + ","));
//        System.out.println();


//        // 使用 JDK8 的 Arrays.sort 方法配合 Lambda 表达式进行排序
//        Arrays.sort(numIndex,
//                Comparator.comparingInt((int[] arr) -> arr[0])  // 第一序列升序
//                        .thenComparingInt(arr -> arr[1])       // 第二序列升序
//        );

        int[] dp = new int[maxNum + 1];
        int[] dpMax = new int[maxNum + 1];
        dp[0] = 0;
        int maxDP = 0;
        int prevMaxDpIndex = -1;
        // i 是 nums[j] 的值，放在值域上跑 dp
        for (int i = 1; i < maxNum + 1; i++) {
            dp[i] = maxDP;

            if (numIndexsList[i] == null) {
//                System.out.println("11111:" + dp[i]);
                prevMaxDpIndex = -1;
                dpMax[i] = dp[i];
                continue;
            }

            // i-1 不存在，i 可以全部加入结果
            if (numIndexsList[i - 1] == null || prevMaxDpIndex == -1) {
                dp[i] += numIndexsList[i].size();
//                System.out.println("22222:" + dp[i]);
                maxDP = dp[i];
                dpMax[i] = dp[i];

                prevMaxDpIndex = numIndexsList[i].size() - 1;
                continue;
            }

            // i-1 存在，则依次判断每个加入每个 i 时应该删除那些 i-1
            int prevMaxDpIndexTemp = -1;
            for (int indexL = 0, indexR = 0; indexR < numIndexsList[i].size(); indexR++) {
                dp[i]++;

                while (indexL <= prevMaxDpIndex && numIndexsList[i - 1].get(indexL) < numIndexsList[i].get(indexR)) {
                    indexL++;
                    dp[i]--;
                }
                // i-1 删完后，需要找到
//                System.out.println("33333:" + indexL + " : " + indexR + " : " + dp[i]);

                // dp[i] 必须严格更大，下次求 i+1 才需要用到 i 的 indexR
                if (maxDP < dp[i]){
                    maxDP = dp[i];
                    dpMax[i] = dp[i];
                    prevMaxDpIndexTemp = indexR;
                }
            }
            prevMaxDpIndex = prevMaxDpIndexTemp;

            // 前一段全不要
            dp[i] = dpMax[i - 2] + numIndexsList[i].size();
            if (maxDP < dp[i]) {
//                System.out.println("44444:" + maxDP + " : " + dp[i]);
                maxDP = dp[i];
                dpMax[i] = maxDP;
                prevMaxDpIndex = numIndexsList[i].size() - 1;
            }

//            System.out.println(maxDP);
        }

        return n - maxDP;
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