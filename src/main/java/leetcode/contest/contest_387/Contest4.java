package leetcode.contest.contest_387;

import java.util.*;
/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int[] resultArray(int[] nums) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            treeMap.put(num, 1);
        }
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer key : treeMap.keySet()) {
            map.put(key, count);
            count++;
        }

        List<Integer> list1 = new ArrayList<>();
        list1.add(nums[0]);
        BIT bit1 = new BIT(count);
        bit1.add(map.get(nums[0]), 1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(nums[1]);
        BIT bit2 = new BIT(count);
        bit2.add(map.get(nums[1]), 1);


        for (int i = 2; i < nums.length; i++) {
            long count1 = bit1.queryForSum(map.get(nums[i]) + 1, count);
            long count2 = bit2.queryForSum(map.get(nums[i]) + 1, count);
            if (count1 > count2 || (count1 == count2 && list1.size() <= list2.size())) {
                list1.add(nums[i]);
                bit1.add(map.get(nums[i]), 1);

            } else {
                list2.add(nums[i]);
                bit2.add(map.get(nums[i]), 1);
            }
        }


        list1.addAll(list2);
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = list1.get(i);
        }
        return res;

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
}
