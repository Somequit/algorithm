package codefroces;

import java.io.*;

public class CodefrocesQuickTest {

    private static final double EPS = 1e-1;
    // 快速读入对象
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    // 字符串快速读入对象
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 快速输出对象
    private static final PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    // 读入整数
    public static int readInt() {
        try {
            st.nextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (int) st.nval;
    }


    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        int n = readInt();
        int m = readInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = readInt();
        }

        for (int i = 0; i < m; i++) {
            int a = readInt();
            int b = readInt();
            int c = readInt();

            out.println(a + " : " + b);
        }
        out.flush();

    }
}