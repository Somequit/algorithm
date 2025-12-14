package template;

/**
 * @author gusixue
 * @description 树状数组模板
 * @date 2023/11/7
 */
public class BIT {
    // 下标取到 [1, size)
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

/**
 * 区间加区间和：使用两个树状数组维护差分数组解决
 * https://oi-wiki.org/ds/fenwick/#%E5%8C%BA%E9%97%B4%E5%8A%A0%E5%8C%BA%E9%97%B4%E5%92%8C
 */
class BITRange {
    // 维护差分 d[i]=a[i]-a[i-1]，下标取到 [1, size)
    long[] sum1;
    // 维护 d[i] * i，下标取到 [1, size)
    long[] sum2;
    int size;

    public BITRange(int length) {
        this.size = length + 2;
        sum1 = new long[this.size];
        sum2 = new long[this.size];
    }

    public BITRange(long[] arr) {
        this.size = arr.length + 2;
        sum1 = new long[this.size];
        sum2 = new long[this.size];

        for (int i = 0; i < arr.length; i++) {
            rangeAdd(i, i + 1, arr[i]);
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    /**
     * 区间更新
     * 下标 [left, right) 增加 value
     */
    public void rangeAdd(int left, int right, long value) {
        // 下标 left 的 d[i] 增加 value
        add(sum1, left, value);
        // 下标 right 的 d[i] 增加 -value
        add(sum1, right, -value);

        // 下标 left 的 d[i]*i 增加 value*left
        add(sum2, left, value * left);
        // 下标 right 的 d[i]*i 增加 -value*right
        add(sum2, right, -value * right);
    }

    private void add(long[] sum, int index, long value) {
        for (index++; index < this.size; index += lowbit(index)) {
            sum[index] += value;
        }
    }

    /**
     * 单点求和
     */
    public long queryForPointSum(int index) {
        long res = 0;
        for (index++; index > 0; index -= lowbit(index)) {
            res += sum1[index];
        }
        return res;
    }

    /**
     * 区间求和，[left, right)
     */
    public long queryForRangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left);
    }

    /**
     * 前缀区间求和 [0, index)
     */
    private long prefixSum(int index) {
        return sum(sum1, index) * index - sum(sum2, index);
    }

    /**
     * sum 前缀区间求和 [0, index)
     */
    private long sum(long[] sum, int index) {
        long res = 0;
        for (; index > 0; index -= lowbit(index)) {
            res += sum[index];
        }
        return res;
    }

}
