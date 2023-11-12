package codefroces;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class CodefrocesQuickTest {

    private static final double EPS = 1e-1;

    public static void main(String[] args) throws IOException {
        Read sc = new Read();

        int n = sc.nextInt();
        int m = sc.nextInt();
        BIT bit = new BIT(n);

        for (int i = 0; i < n; i++) {
            bit.add(i, sc.nextInt());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();

            if (a == 1) {
                int x = sc.nextInt();
                int k = sc.nextInt();
                bit.add(x - 1, k);

            } else {
                int x = sc.nextInt();
                int y = sc.nextInt();
                sc.println(bit.queryForSum(x - 1, y));
            }
        }
        sc.bw.flush();
        sc.bw.close();

    }

    static class BIT {
        int[] sum;
        int size;

        public BIT(int length) {
            this.size = length + 1;
            sum = new int[this.size];
        }

        private int lowbit(int x) {
            return x & (-x);
        }

        // 单点更新：下标 index 添加 value
        public void add(int index, int value) {
            // bit 需要从 1 开始
            for (index++; index < this.size; index += lowbit(index)) {
                this.sum[index] += value;
            }
        }

        // 区间查询，前闭后开区间 [left, right)
        public int queryForSum(int left, int right) {
            return queryForSum(right) - queryForSum(left);
        }

        // 前缀区间查询，前闭后开区间 [0, index)
        public int queryForSum(int index) {
            int res = 0;
            for (; index > 0; index -= lowbit(index)) {
                res += this.sum[index];
            }
            return res;
        }
    }

    static class Read{
        BufferedReader bf;
        StringTokenizer st;
        BufferedWriter bw;
        public Read(){
            bf=new BufferedReader(new InputStreamReader(System.in));
            st=new StringTokenizer("");
            bw=new BufferedWriter(new OutputStreamWriter(System.out));
        }
        public String nextLine() throws IOException{
            return bf.readLine();
        }
        public String next() throws IOException{
            while(!st.hasMoreTokens()){
                st=new StringTokenizer(bf.readLine());
            }
            return st.nextToken();
        }
        public char nextChar() throws IOException{
            return next().charAt(0);
        }
        public int nextInt() throws IOException{
            return Integer.parseInt(next());
        }
        public long nextLong() throws IOException{
            return Long.parseLong(next());
        }
        public double nextDouble() throws IOException{
            return Double.parseDouble(next());
        }
        public float nextFloat() throws IOException{
            return Float.parseFloat(next());
        }
        public byte nextByte() throws IOException{
            return Byte.parseByte(next());
        }
        public short nextShort() throws IOException{
            return Short.parseShort(next());
        }
        public BigInteger nextBigInteger() throws IOException{
            return new BigInteger(next());
        }
        public void println(int a) throws IOException{
            bw.write(String.valueOf(a));
            bw.newLine();
            return;
        }
        public void print(int a) throws IOException{
            bw.write(String.valueOf(a));
            return;
        }
        public void println(String a) throws IOException{
            bw.write(a);
            bw.newLine();
            return;
        }
        public void print(String a) throws IOException{
            bw.write(a);
            return;
        }
        public void println(long a) throws IOException{
            bw.write(String.valueOf(a));
            bw.newLine();
            return;
        }
        public void print(long a) throws IOException{
            bw.write(String.valueOf(a));
            return;
        }
        public void println(double a) throws IOException{
            bw.write(String.valueOf(a));
            bw.newLine();
            return;
        }
        public void print(double a) throws IOException{
            bw.write(String.valueOf(a));
            return;
        }
    }
}