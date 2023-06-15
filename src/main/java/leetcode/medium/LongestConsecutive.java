package leetcode.medium;

import utils.AlgorithmUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    /**
     * 首先可以想到如果排序好数组、那么可以使用双指针的方式计数连续值，但时间复杂度超了，因此从连续方向考虑：每个元素 num 使用哈希搜索 num+1、num+2...
     */
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
     * 哈希 + 贪心：按照 num 连续的方式换一种思路，首先我们思考暴力：将元素全部放入哈希中，接着遍历每一个 num，每个 num 搜索 num+1、num+2... 直到结束，
     * 这样时间复杂度为O（n^2），但是仔细思考可知：每个大于 1 个元素的连续区间，我们重复遍历了多次；因此考虑如何让每个连续区间只搜索一次，上面的方式可看做使用了记忆化搜索，
     * 除此之外每个连续区间均从最小的 num 开始，这样就不需要让该连续区间大于 num 的元素搜索一遍了，问题转化为：如何在遍历时、O（1）复杂度确认 num 是否为该连续区间最小值，
     * 解法：遍历时判断 num-1 是否存在哈希中，如果不存在则代表 num 是该连续区间的最小值、此时使用 num 搜索整个连续区间的个数，如果存在则不需要搜索
     * 时间复杂度：O（n），空间复杂度：O（n）
     * @param nums
     * @return
     */
    public int solution3(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        // 元素存入哈希，哈希仅用于校验是否存在
        Set<Integer> numsSet = putNumsIntoSet(nums);
//        System.out.println(numsSet);

        // 遍历元素，并保证每个连续区间均从最小的 num 开始搜索
        int res = doLongestConsecutive3(nums, numsSet);

        return res;
    }

    /**
     * 元素存入哈希
     */
    private Set<Integer> putNumsIntoSet(int[] nums) {
        // 初始化 HashSet，所有元素最多添加一次、避免扩容
        int len = nums.length;
        Set<Integer> consecutiveSet = new HashSet<>((len / 3) << 2);

        for (int num : nums) {
            consecutiveSet.add(num);
        }

        return consecutiveSet;
    }

    /**
     * 遍历元素，并保证每个连续区间均从最小的 num 开始搜索
     */
    private int doLongestConsecutive3(int[] nums, Set<Integer> numsSet) {
        // 最少一个元素
        int res = 1;

        // 遍历去重后的集合
        for (int num : numsSet) {
            // 判断 num-1 存在哈希，直接往后遍历
            if (numsSet.contains(num - 1)) {
                continue;
            }

//            System.out.println(num);

            // 判断 num-1 不存在哈希，代表连续区间头元素，搜索整个区间
            int next = num + 1;
            while (numsSet.contains(next)) {
                next++;
            }

            // 校验结果
            res = Math.max(res, next - num);
        }

        return res;
    }

    /**
     * 哈希 + 带权并查集：考虑搜索连续区间可以使用带权并查集，连续的元素合并时小值指向大值，由于合并是俩根节点结合，因此仅需要保证根节点的权值代表连续区间的元素个数集合，
     * 同时注意每次合并时仅更新根节点权值，路径压缩时不需要更新权值，
     * 具体方式：现将所有元素放入哈希指向自己，然后遍历每个 num，如果 num-1 存在、则判断其与 num 的根节点是否相同，不同则合并、根节点权值 +1，
     * 如果 num+1 存在、则判断其与 num 的根节点是否相同，不同则合并、根节点权值 +1，结尾是根节点的最大权值
     * 带路径压缩的并查集合并的 α(n) 为不超过 4，时间复杂度：O（α(n)*n），空间复杂度：O（n）
     * @param nums
     * @return
     */
    public int solution4(int[] nums) {
        // 判空
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        // 创建哈希的并查集同时初始化，nums 所有元素指向自己
        int len = nums.length;
        Map<Integer, Integer> parentMap = initUnionFind(nums, len);
        // 所有元素权值设为 1
        Map<Integer, Integer> weightMap = initUnionFindWeight(nums, len);

        // System.out.println(parentMap + " : " + weightMap);

        // 连续的元素合并时小值指向大值，由于合并是俩根节点结合，因此仅需要保证根节点的权值代表连续区间的元素个数集合
        int res = doLongestConsecutive4(parentMap, weightMap);

        return res;
    }

    /**
     * 连续的元素合并时小值指向大值，由于合并是俩根节点结合，因此仅需要保证根节点的权值代表连续区间的元素个数集合
     */
    private int doLongestConsecutive4(Map<Integer,Integer> parentMap, Map<Integer,Integer> weightMap) {
        // 结果最小为 1
        int res = 1;

        // 遍历任一哈希，减少重复元素校验，同时哈希创建好后只修改不会更改结构
        for (Integer num : weightMap.keySet()) {

            // 校验 num-1 存在，同时俩根节点不同则允许则合并 num 和 num-1 的根节点（小值指向大值），返回根节点权值
            int ans = unionFind(num, num-1, parentMap, weightMap);
            // 更新结果
            res = Math.max(res, ans);

            // 校验 num+1 存在，同时俩根节点不同则允许则合并 num 和 num+1 的根节点（小值指向大值），返回根节点权值
            ans = unionFind(num, num+1, parentMap, weightMap);
            // 更新结果
            res = Math.max(res, ans);
        }

        return res;
    }

    /**
     * 校验 other 存在，同时俩根节点不同则允许则合并 current 和 other 的根节点（小值指向大值），返回根节点权值
     */
    private int unionFind(int current, int other, Map<Integer,Integer> parentMap, Map<Integer,Integer> weightMap) {
        // other 存在才可能合并
        if (!weightMap.containsKey(other)) {
            return weightMap.get(current);
        }

        // 获取 current 与 other 的根节点
        int currentParent = find(current, parentMap);
        int otherParent = find(other, parentMap);
//        System.out.println("current : " + current + " : " + currentParent);
//        System.out.println("other : " + other + " : " + otherParent);

        // 俩根节点不同则合并 current 与 other（小值指向大值），根节点权值 +1
        if (currentParent != otherParent) {
            // 返回根节点
            int parent = union(currentParent, otherParent, parentMap, weightMap);
//            System.out.println(parentMap + " : " + weightMap);
            return weightMap.get(parent);
        }

        return weightMap.get(currentParent);
    }

    /**
     * 获取元素的根节点，同时路径压缩
     */
    private int find(int son, Map<Integer,Integer> parentMap) {
        if (son == parentMap.get(son)) {
            return son;
        }

        int father = parentMap.get(son);
        int parent = find(father, parentMap);
        // 路径压缩
        parentMap.put(son, parent);
        return parent;
    }

    /**
     * 合并 current 与 other（小值指向大值），根节点权值 +1，返回根节点
     */
    private int union(int current, int other, Map<Integer,Integer> parentMap, Map<Integer,Integer> weightMap) {
        if (current < other) {
            parentMap.put(current, other);
            weightMap.put(other, weightMap.get(other) + weightMap.get(current));
            return other;
        } else {
            parentMap.put(other, current);
            weightMap.put(current, weightMap.get(other) + weightMap.get(current));
            return current;
        }
    }

    /**
     * 创建哈希的并查集同时初始化，nums 所有元素指向自己
     */
    private Map<Integer,Integer> initUnionFind(int[] nums, int len) {
        // 所有元素最多添加一次、避免扩容
        Map<Integer, Integer> parentMap = new HashMap<>((len / 3) << 2);

        for (int num : nums) {
            parentMap.put(num, num);
        }

        return parentMap;
    }

    /**
     * 创建哈希的并查集同时初始化，所有元素权值设为 1
     */
    private Map<Integer,Integer> initUnionFindWeight(int[] nums, int len) {
        // 所有元素最多添加一次、避免扩容
        Map<Integer, Integer> weightMap = new HashMap<>((len / 3) << 2);

        for (int num : nums) {
            weightMap.put(num, 1);
        }

        return weightMap;
    }
}
