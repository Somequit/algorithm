package leetcode.brushQuestions.hard;

import javafx.util.Pair;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 2454. 下一个更大元素 IV
 * @date 2023/12/13
 */
public class SecondGreaterElement {

    /**
     * 反向思考 + 两次单调队列 + 排序二分/TreeSet：
     *
     * 第 1 步：
     * 题目可以转化为两步处理，
     * 通过下标 i 先找到 i 右边第一个 nums[k] > nums[i] 的 k，
     * 再存储 i 和 k 在找到 k 右边第一个 nums[j] > nums[i] 的 nums[j]
     *
     * 第 2 步：
     * 找 k 可以使用倒序遍历，
     * 然后使用一个严格递减的单调栈，
     * 每次第一个大于 nums[i] 的 nums 下标即为 k，
     *     * 因为当求 i-1 时，要么 k 就是之前放入的 i(nums[i-1] < nums[i])，要么就是栈里剩下的某个元素(nums[i-1] >= nums[i])
     * 将所有 i 与 k 均存起来，
     *
     * 第 3 步：
     * 由于求 i-1 时，此时的 k1 可能比求 i 时的 k2 更靠右，此时 j1 就应该在 [0,k1) 对应的单调栈中，
     * 在线求特别麻烦，因此可以考虑离线（即将所有 i-k 存起来，排序后统一处理）
     *
     * 第 4 步：
     * 对 k 降序排，然后再次倒序使用一个严格递减的单调栈，
     * 当遍历的值等于某个 k 时，使用 二分/TreeSet 栈中大于 nums[i] 的最小值，即为结果
     * 遍历单调栈与 k 使用类似双指针的方式处理
     *
     * 第 5 步：
     * 时间复杂度：O（n*logn）排序，空间复杂度：O（n）
     *
     */
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;

        // 所有 i 与 k 均存起来，k 不存在则使用 -1
        List<Pair<Integer, Integer>> indexList = new ArrayList<>();
        int[] res = new int[n];

        // 找 k 可以使用倒序遍历，然后使用一个严格递减的单调栈，
        Deque<Integer> decreaseStack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!decreaseStack.isEmpty() && nums[decreaseStack.peekFirst()] <= nums[i]) {
                decreaseStack.pop();
            }

            indexList.add(new Pair<>(i, decreaseStack.isEmpty() ? -1 : decreaseStack.peekFirst()));
            // k 等于 -1 则结果也为 -1
            res[i] = -1;

            decreaseStack.push(i);
        }

        // 对 k 降序排，然后再次倒序使用一个严格递减的单调栈
        Collections.sort(indexList, (o1, o2) -> o2.getValue() - o1.getValue());
//        System.out.println(indexList);

        // 当遍历的值等于某个 k 时，使用 二分/TreeSet 栈中大于 nums[i] 的最小值，即为结果
        TreeSet<Integer> treeSet = new TreeSet<>();
        decreaseStack.clear();

        int listIndex = 0;
        for (int i = n - 1; i >= 0 && listIndex < n; i--) {
            while (listIndex < n && i == indexList.get(listIndex).getValue()) {
                int numsIndex = indexList.get(listIndex).getKey();

                Integer resTemp = treeSet.higher(nums[numsIndex]);
                res[numsIndex] = resTemp == null ? -1 : resTemp;

                listIndex++;
            }

            while (!decreaseStack.isEmpty() && nums[decreaseStack.peekFirst()] <= nums[i]) {
                treeSet.remove(nums[decreaseStack.peekFirst()]);
                decreaseStack.pop();
            }

            treeSet.add(nums[i]);
            decreaseStack.push(i);
        }

        return res;
    }


}
