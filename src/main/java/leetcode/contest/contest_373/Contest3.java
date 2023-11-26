package leetcode.contest.contest_373;

import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.*;

/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int limit = AlgorithmUtils.systemInNumberInt();
//            List<Integer> list = AlgorithmUtils.systemInList();

            int[] res = contest.solution(nums, limit);
            System.out.println(Arrays.toString(res));
        }

    }

    /**
     * @return
     */
    private int[] solution(int[] nums, int limit) {
        int n = nums.length;

        // 下标
        Integer[] indexInt = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexInt[i] = i;
        }
        // 值升序-下标升序
        Arrays.sort(indexInt, (o1, o2) -> {
            if (nums[o1] == nums[o2]){
                return o1.compareTo(o2);

            } else {
                return nums[o1] - nums[o2];
            }
        });
//        System.out.println(Arrays.toString(indexInt));

        // 小顶堆存 nums 的下标
        Queue<Integer> heapIndex = new PriorityQueue<>();

        // 堆存值为 nums[indexInt[left]]，nums[indexInt[left]]+limit
        int left = 0;
        int right = 0;
        for (; right < n; right++) {
            int curIndexRight = indexInt[right];

            if (nums[curIndexRight] > nums[(left == right ? curIndexRight : indexInt[right - 1])] + limit) {
                break;
            }

            heapIndex.add(curIndexRight);
        }

        int[] res = new int[n];
        // 双指针求出当前最小值（nums[indexInt[left]]）放入能够放入放入的最小下标 res[heap.poll]
        while (left < right) {
            int heapIndexRight = heapIndex.poll();

            res[heapIndexRight] = nums[indexInt[left]];
            left++;
//            System.out.println(heapIndexRight + " : " + left + " : " + right);
//            System.out.println(heapIndex);

            for (; right < n; right++) {
                int curIndexRight = indexInt[right];

                if (nums[curIndexRight] > nums[(left == right ? curIndexRight : indexInt[right - 1])] + limit) {
                    break;
                }

                heapIndex.add(curIndexRight);
            }

        }

        return res;
    }


}
