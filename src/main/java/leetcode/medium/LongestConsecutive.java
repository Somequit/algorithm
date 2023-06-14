package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * @date 2023/6/14
 */
public class LongestConsecutive {

    public static void main(String[] args) {
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();
            int res = longestConsecutive.solution(nums);
            System.out.println(res);

            int res2 = longestConsecutive.solution2(nums);
            System.out.println(res2);

            int res3 = longestConsecutive.solution3(nums);
            System.out.println(res3);

            int res4 = longestConsecutive.solution4(nums);
            System.out.println(res4);
        }
    }

    /**
     * 首先可以想到如果排序好数组、那么可以使用双指针的方式计数连续值，但时间复杂度超了，因此从连续方向考虑：每个元素 num 使用哈希搜索 num+1、num+2...
     * 哈希：每个元素放入依次 HashMap 中，HashMap 中 key 存放（多个）连续一段数的头与尾元素（如果仅一个元素就只存当前元素与 0）、
     * value 存放该段另一端减去 key 的大小（num+value 与 num 形成闭区间，key 为端头 value 为正数、key 为端尾 value 为负数），
     * 具体插入方式：先不考虑重复元素，每个元素 num 存入前，搜其是否为某段最后一个节点的后一个元素、某段第一个节点的前一个元素，此时有四种情况，如下：
     *     num-1 与 num+1 都存在 key 中，num 可将两连续段合并（num-1 为后缀、num+1 为前缀），哈希中删除 key 为 num-1 与 num+1 的元素，
     *         将 key 为 num-1+value(num-1) 的 value 改/添为 num+1+value(num+1) - num-1+value(num-1)，
     *         将 key 为 num+1+value(num+1) 的 value 改/添为 -（num+1+value(num+1) - num-1+value(num-1)），
     *     仅 num-1 存在 key 中，合并前一段（num-1 为后缀），哈希中删除 key 为 num-1 的元素，
     *         将 key 为 num-1+value(num-1) 的 value 改/添为 num - num-1+value(num-1)，
     *         再添加 num 为后缀的区间（num，-（num - num-1+value(num-1)））
     *     仅 num+1 存在 key 中，合并后一段（num+1 为前缀），哈希中删除 key 为 num+1 的元素，
     *         将 key 为 num+1+value(num+1) 的 value 改/添为 -（num+1+value(num+1) - num），
     *         再添加 num 为前缀的区间（num，num+1+value(num+1) - num）
     *     num-1 与 num+1 都未存在 key 中，num 没与任何段有交集，则将 (num,0) 放入
     * 结果是每次插入后求 value+1 的最大值；添加过程中每次均将能合并的区间尽量合并，然后再删除 key 的中间节点，留下的 key 仅为每个区间的头与尾（单值头尾相同、因此仅留下一个值），
     * 特殊情况：如果有重复元素，此时会出现区间相交与碰撞，最简单的办法是在创建一个 set 集合，判断有重复元素就不插入，
     * 空间压缩：如果不添加 set 集合判断重复元素，那么插入元素时比较麻烦，就需要特殊判断：
     *     num 存在端点中，则 num 存在 key 中，则不插入
     *     num 存在端点周围，value(num+1) 为负数或 value(num-1) 为正数，则不插入
     *     num 存在端点内部，正常处理逻辑即可，由于上两条规则，仅会出现大区间内存在小区间的问题（小区间需要的端点值会被大区间干掉），不影响求值
     * 注意：HashMap 初始化为 nums.len*4/3 避免扩容，因为最坏情况是所有节点互不连续
     * 时间复杂度：O（n），空间复杂度：O（n）
     */
    public int solution(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        // 初始化 HashMap
        int len = nums.length;
        Map<Integer, Integer> consecutiveMap = new HashMap<>(((len / 3) << 2) + 1);

        // 依次插入每个元素 num 到 HashMap，返回计算结果
        int res = doLongestConsecutive(nums, len, consecutiveMap);
        // System.out.println(res + "\r\n");

        return res;
    }

    /**
     * 依次插入每个元素 num 到 HashMap，HashMap 中 key 存放（多个）连续一段数的头与尾元素（如果仅一个元素就只存当前元素与 0）、value 存放该段尾减去头的大小（元素个数 - 1）
     * 返回计算结果
     */
    private int doLongestConsecutive(int[] nums, int len, Map<Integer,Integer> consecutiveMap) {
        // 最少一个元素
        int res = 1;
        for (int num : nums) {
//            System.out.print(num + " : ");
            // 则 num 存在 key 中或 value(num+1) 为负数或 value(num-1) 为正数，不插入
            if (consecutiveMap.containsKey(num)) {
                continue;
            }
            Integer valNext = consecutiveMap.get(num + 1);
            if (valNext != null && valNext < 0) {
                continue;
            }
            Integer valPrev = consecutiveMap.get(num - 1);
            if (valPrev != null && valPrev > 0) {
                continue;
            }

            // num-1 与 num+1 都存在 key 中，num 可将两连续段合并
            if (valNext != null && valPrev != null) {
                consecutiveMap.remove(num - 1);
                consecutiveMap.remove(num + 1);

                int valPositive = num + 1 + valNext - (num - 1 + valPrev);
                consecutiveMap.put(num - 1 + valPrev, valPositive);
                consecutiveMap.put(num + 1 + valNext, -valPositive);

                res = Math.max(res, valPositive + 1);

            // 仅 num-1 存在 key 中，合并前一段
            } else if (valPrev != null) {
                consecutiveMap.remove(num - 1);

                int valPositive = num - (num - 1 + valPrev);
                consecutiveMap.put(num - 1 + valPrev, valPositive);
                consecutiveMap.put(num, -valPositive);

                res = Math.max(res, valPositive + 1);

            // 仅 num+1 存在 key 中，合并后一段
            } else if (valNext != null) {
                consecutiveMap.remove(num + 1);

                int valPositive = num + 1 + valNext - num;
                consecutiveMap.put(num + 1 + valNext, -valPositive);
                consecutiveMap.put(num, valPositive);

                res = Math.max(res, valPositive + 1);

            // num-1 与 num+1 都未存在 key 中，num 没与任何段有交集，则将 (num,0) 放入
            } else {
                consecutiveMap.put(num, 0);
            }
//            System.out.println(consecutiveMap);
        }

        return res;
    }

    /**
     * 哈希优化：上述 HashMap 仅存储端点、因此不方便去重还需要删除中间节点，如果存储所有元素、那么就不需要删除节点同时直接可以判断去重，
     * HashMap 中 key 代表每个元素，如果该元素为左/右端点、value 为从左到右的元素个数，如果该元素为中间节点、value 为它作为端点时的元素个数、此时该元素在新增时并不会被用到（仅用于去重）
     * 具体插入方式：
     *     判断 num 是否添加过，添加过则不再添加，
     *     否则查询 num-1 与 num+1 的 value 值、空则返回 0，此时 num-1 如果非空则一定是区间右端点、num+1 非空则一定是左端点（不是端点则代表一定包含 num，这与前面的去重冲突），
     *     接着更新左右端点的值，
     *     然后将 num 加入哈希、value 任意（要么 num 不是端点、要么已更新了），
     *     最后更新结果值、在其与左/右端点 value 取最大值
     * 时间复杂度：O（n），空间复杂度：O（n）
     * @param nums
     * @return
     */
    public int solution2(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        // 初始化 HashMap，所有元素最多添加一次、避免扩容
        int len = nums.length;
        Map<Integer, Integer> consecutiveMap = new HashMap<>((len / 3) << 2);

        // 依次加入元素
        int res = doLongestConsecutive2(nums, len, consecutiveMap);

        return res;
    }

    /**
     * 具体插入方式：
     *     判断 num 是否添加过，添加过则不再添加，
     *     否则查询 num-1 与 num+1 的 value 值、空则返回 0，此时 num-1 如果非空则一定是区间右端点、num+1 非空则一定是左端点（不是端点则代表一定包含 num，这与前面的去重冲突），
     *     接着将 num 加入哈希、value 任意（要么 num 不是端点、要么是端点但后面会更新）
     *     然后更新左右端点的值，
     *     最后更新结果值、在其与左/右端点 value 取最大值
     */
    private int doLongestConsecutive2(int[] nums, int len, Map<Integer,Integer> consecutiveMap) {
        int res = 0;
        for (int num : nums) {
            // 判断 num 是否添加过，添加过则不再添加
            if (consecutiveMap.containsKey(num)) {
                continue;
            }

            // 查询 num-1 与 num+1 的 value 值、空则返回 0
            int previous = consecutiveMap.getOrDefault(num - 1, 0);
            int next = consecutiveMap.getOrDefault(num + 1, 0);

            // 将 num 加入哈希、value 任意（要么 num 不是端点、要么是端点但后面会更新）
            consecutiveMap.put(num, -1);

            // 更新左右端点的值
            int current = previous + next + 1;
            consecutiveMap.put(num - previous, current);
            consecutiveMap.put(num + next, current);

            // 更新结果值、在其与左/右端点 value 取最大值
            res = Math.max(res, current);
        }

        return res;
    }

    /**
     * 并查集：
     *
     * 时间复杂度：O（n），空间复杂度：O（n）
     * @param nums
     * @return
     */
    public int solution3(int[] nums) {
        // 判空

        //

        return 0;
    }

    /**
     * 哈希 + 贪心：
     *
     * 时间复杂度：O（n），空间复杂度：O（n）
     * @param nums
     * @return
     */
    public int solution4(int[] nums) {
        // 判空

        //

        return 0;
    }
}
