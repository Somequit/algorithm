package template;

/**
 * @author gusixue
 * @description 树状数组模板
 * @date 2023/11/7
 */
public class BIT {
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
