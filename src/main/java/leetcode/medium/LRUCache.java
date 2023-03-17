package leetcode.medium;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 146. LRU 缓存
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity
 * ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * @author gusixue
 * @date 2023/3/15
 */
public class LRUCache {

    // LRU 对象最大容量，无法扩容
    private final int maxCapacity;

    // LRU 对象当前容量
    private int currCapacity = 0;

    // 存储 LRU 对象的具体的 key-value 值
    private final Map<Integer, Integer> hashMap;

    // 存储 LRU 对象 key 值，用于筛选最久未使用元素
    private final Queue<Integer> queue;

    // 存储 LRU 对象 key 值与 Queue 中 key 还有多少个
    private final Map<Integer, Integer> hashNum;

    public LRUCache(int capacity) {
        this.maxCapacity = capacity;
        this.hashMap = new ConcurrentHashMap<>(capacity);
        this.queue = new ConcurrentLinkedQueue<>();
        this.hashNum = new ConcurrentHashMap<>(capacity);
    }

    /**
     * {@code hashMap} 中查询元素是否存在，存在则记录 key 被使用一次然后返回 vale，不存在直接返回 -1
     * @param key
     * @return
     */
    public int get(int key) {
        if (hashMap.containsKey(key)) {
            recordUsed(key);
            return hashMap.get(key);
        } else {
            return -1;
        }
    }

    /**
     * 记录 key 被使用一次
     */
    private void recordUsed(int key) {
        queue.add(key);
        int num = hashNum.getOrDefault(key, 0);
        hashNum.put(key, num + 1);

//        queue.stream().forEach(k -> System.out.print(k + " "));
//        System.out.println();
//        hashNum.forEach((k, v) -> System.out.print(k + ":" + v + " "));
//        System.out.println();
//        hashMap.forEach((k, v) -> System.out.print(k + ":" + v + " "));
//        System.out.println("\n");
    }

    /**
     * 找到最久未使用：延迟删除策略，循环删除队头数据、如果队头不是最新的数据（计数判断），就继续删除直到删到最新数据，
     * 它一定是最久未使用的，并且每个元素最多进出队列一次，因此平均时间复杂度为 O（1）
     *
     * 如果 key 不存在且元素已满，执行删除最久未使用：
     *     循环取出并删除 {@code queue} 队头元素 currKey，然后 {@code hashNum} 将 value - 1，
     *     直到减完后 value 为 0，删除 {@code hashNum} 与 {@code hashMap} 中的 currKey，currCapacity 减 1，
     * 接着执行添加与记录操作，当然 key 不存在或者元素没有满也相同
     *     元素在 {@code hashMap} 不存在则 currCapacity 加 1、否则不加，然后元素添加到 {@code hashMap}，记录 key 被使用一次
     * ，
     */
    public void put(int key, int value) {
        if (!hashMap.containsKey(key) && currCapacity >= maxCapacity) {
            Integer currKey = queue.poll();

            while (currKey != null) {
                int currNum = hashNum.getOrDefault(currKey, 0);

                if (currNum <= 1) {
                    hashNum.remove(currKey);
                    hashMap.remove(currKey);
                    currCapacity--;
                    break;

                } else {
                    hashNum.put(currKey, currNum - 1);
                }

                currKey = queue.poll();
            }
        }

        if (!hashMap.containsKey(key)) {
            currCapacity++;
        }
        hashMap.put(key, value);
        recordUsed(key);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        assert lruCache.maxCapacity == 2;

        assert -1 == lruCache.get(2);

        lruCache.put(2, 6);

        assert -1 == lruCache.get(1);

        lruCache.put(1, 5);
        lruCache.put(1, 6);

        assert 6 == lruCache.get(1);
        assert 6 == lruCache.get(2);
        assert -1 == lruCache.get(2);

    }
}
