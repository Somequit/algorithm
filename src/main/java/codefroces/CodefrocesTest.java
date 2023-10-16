package codefroces;

import java.util.Scanner;

public class CodefrocesTest {

    private static final double EPS = 1e-1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int p = sc.nextInt();
        int q = sc.nextInt();

        double[] dp = new double[a + 1];
        for (int i = b; i <= a; i++) {
            if (b == 1 && q > 0) {
                dp[i] = Math.max((p + 100 * dp[i - b]) / 100D + 1, dp[i - b] + 100 / (100 - q));
            } else {
                dp[i] = Math.max(p + 100 * dp[i - b], q * dp[i - b + 1] + (100 - q) * dp[i - b]) / 100D + 1;
            }
        }
        System.out.println(dp[a]);
        sc.close();
    }
}