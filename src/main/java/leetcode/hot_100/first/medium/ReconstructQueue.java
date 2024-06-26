package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gusixue
 * @description 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * 1 <= people.length <= 2000
 * 0 <= hi <= 10 ^ 6
 * 0 <= ki < people.length
 * @date 2023/11/7
 */
public class ReconstructQueue {

    public static void main(String[] args) {
        ReconstructQueue reconstructQueue = new ReconstructQueue();
        while (true) {
            int[][] people = AlgorithmUtils.systemInTwoArray();

            int[][] res = reconstructQueue.solution(people);
            AlgorithmUtils.systemOutArray(res);

            int[][] res2 = reconstructQueue.solutionOptimization(people);
            AlgorithmUtils.systemOutArray(res2);
        }
    }

    /**
     * 贪心+二分+树状数组：先根据 h 非降序、再根据 k 降序，当 h 不相等时每次放的位置前面留 k 个空位，因为比 h 小的全部已放置且不会影响 h、而接下来放置的全部比 h 大，插入对应空位即可，
     * 如果 h 相同、因为 k 降序，所以第一个 h1 放置后，第二个 h2 会放置在 h1 前面，不会影响 h1 与 h2，因此相同处理即可，
     * 具体放置时二分位置（越靠后空位会不变或者越多），然后使用树状数组查询区间有多少个 1（空位），找到位置后更新空位（1->0）
     * 时间复杂度：O（n*logn*logn），空间复杂度：O（n）
     */
    private int[][] solutionOptimization(int[][] people) {
        // 根据 h 非降序、再根据 k 降序
        Arrays.sort(people, (o1, o2) -> {
            int h1 = o1[0];
            int k1 = o1[1];
            int h2 = o2[0];
            int k2 = o2[1];
            if (h1 != h2) {
                return h1 - h2;
            } else {
                return k2 - k1;
            }
        });

        int n = people.length;
        int[][] res = new int[n][2];
        // 建立树状数组代表 n 个空位（1）
        BIT bit = new BIT(n);
        for (int i = 0; i < n; i++) {
            bit.add(i, 1);
        }

        for (int i = 0; i < n; i++) {
            int k = people[i][1];

            // 放置时二分下标，查询 [0,mid+1) 有 k+1 个 1 中最靠前的位置
            int left = 0;
            int right = n - 1;
            int pos = -1;
            while (left <= right) {
                int mid = ((right - left) >> 1) + left;

                // 查询 [0,index+1) 有多少个 1
                if (bit.queryForSum(mid + 1) >= k + 1) {
                    right = mid - 1;
                    pos = mid;

                } else {
                    left = mid + 1;
                }
            }

            // 树状数组查询区间有多少个 1（空位），找到位置后更新空位（1->0）
            res[pos][0] = people[i][0];
            res[pos][1] = people[i][1];
            bit.add(pos, -1);
        }

        return res;
    }


    class BIT {

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
            return queryForSum(right) - queryForSum(left - 1);
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
     * 先按照 k 非降序，k 相同按照 h 降序，然后遍历结果数组 找到前面大于等于 h 的 k 个元素、放在那一位就行
     * 注意：当前此位后面不可能出现（h1，k1）在 h1<h 导致后面的排列错误，因为（h1，k1）如果出现 k1<k 时一定会放在前面，
     * 因为前面大于等于 h 的元素个数=k 一定 不少于 大于等于 h1 的元素个数，即大于等于 h1 的元素个数 k1>=k，这与前提 k1<k 违背
     * 时间复杂度：O（n ^ 2），空间复杂度：O（n+logn）辅助存储下标（可直接存入 res、然后 res 移位）+排序辅助栈
     *
     * @param people
     * @return
     */
    private int[][] solution(int[][] people) {
        int n = people.length;

        // 先按照 k 非降序，k 相同按照 h 降序
        Arrays.sort(people, (o1, o2) -> {
            int h1 = o1[0];
            int k1 = o1[1];
            int h2 = o2[0];
            int k2 = o2[1];
            if (k1 != k2) {
                return k1 - k2;
            } else {
                return h2 - h1;
            }
        });
        // 存储 people 下标在哪一位
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int h = people[i][0];
            int k = people[i][1];

            int pos = 0;
            for (; pos < indexList.size() && k > 0; pos++) {
                /**
                 * 找到前面大于等于 h 的 k 个元素、放在那一位就行
                 * 注意：当前此位后面不可能出现（h1，k1）在 h1<h 导致后面的排列错误，因为（h1，k1）如果出现 k1<k 时一定会放在前面，
                 * 因为前面大于等于 h 的元素个数=k 一定 不少于 大于等于 h1 的元素个数，即大于等于 h1 的元素个数 k1>=k，这与前提 k1<k 违背
                 */

                int curH = people[indexList.get(pos)][0];
                if (curH >= h) {
                    k--;
                }
            }
            indexList.add(pos, i);
        }

        // 通过 indexList 找到对应下标放入最终结果
        int[][] res = new int[n][2];
        for (int i = 0; i < n; i++) {
            int index = indexList.get(i);
            res[i][0] = people[index][0];
            res[i][1] = people[index][1];
        }

        return res;
    }
}
