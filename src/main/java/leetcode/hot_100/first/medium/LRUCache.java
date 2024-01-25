package leetcode.hot_100.first.medium;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
        System.out.println("1");
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


/**
 * 146. LRU 缓存
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
 * 如果插入操作导致关键字数量超过 capacity，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * @author gusixue
 * @date 2023/3/16
 */
class LRUCacheLinkedHashMap extends LinkedHashMap<Integer, Integer> {

    // LRU 对象最大容量，无法扩容
    private final int maxCapacity;

    /**
     * LinkedHashMap 源码
     * 首先看结构，继承 HashMap，同时新增一个双向链表，双线链表后续说明
     * 其次看构造器有个参数 accessOrder，false 代表插入顺序，true 代表访问顺序，需要使用 true
     * 接着看 get 方法，调用父类的 get 获得元素后
     * 判断如果 accessOrder 为 true，则执行 afterNodeAccess 方法
     * afterNodeAccess 方法将当前节点移动到链表尾部
     * 然后看 put 方法，子类没有实现、看父类 HashMap 的方法，
     * 方法内部发现调用的 newNode、afterNodeAccess、afterNodeInsertion 三个方法
     * newNode 新增了双向链表的处理
     * afterNodeInsertion 方法查看发现，如果 removeEldestEntry(first) 返回 true 则删除链表非空头结点
     * removeEldestEntry(first) 上面解释过（当然方法上源码写得更清楚），当前元素大于容量时返回 true
     * 最后总体，通过源码可得双向链表是维护访问顺序的，头部存最久未放过数据用于删除，某个数据一旦访问过就通过 HashMap 直接找到元素加入链表尾部
     * PS：使用双向链表是因为元素放到尾部需要获得前后节点
     */
    public LRUCacheLinkedHashMap(int capacity) {
        super(capacity, 0.75f, true);
        this.maxCapacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
        return size() > maxCapacity;
    }

    public static void main(String[] args) {
        System.out.println("2");
        LRUCacheLinkedHashMap lRUCacheLinkedHashMap = new LRUCacheLinkedHashMap(2);

        assert lRUCacheLinkedHashMap.maxCapacity == 2;

        assert -1 == lRUCacheLinkedHashMap.get(2);

        lRUCacheLinkedHashMap.put(2, 6);

        assert -1 == lRUCacheLinkedHashMap.get(1);

        lRUCacheLinkedHashMap.put(1, 5);
        lRUCacheLinkedHashMap.put(1, 6);

        assert 6 == lRUCacheLinkedHashMap.get(1);
        assert 6 == lRUCacheLinkedHashMap.get(2);
        assert -1 == lRUCacheLinkedHashMap.get(2);

    }
}



/**
 * 146. LRU 缓存
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
 * 如果插入操作导致关键字数量超过 capacity，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * @author gusixue
 * @date 2023/3/16
 */
class LRUCacheMyLinked {

    static class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode() {

        }
        public LinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // LRU 对象最大容量，无法扩容
    private final int maxCapacity;

    // LRU 对象当前容量
    private int currCapacity = 0;

    // 存储数据与双向链表节点
    private final Map<Integer, LRUCacheMyLinked.LinkedNode> cache;

    // 双向链表头尾，均为虚拟节点
    private final LRUCacheMyLinked.LinkedNode head;

    private final LRUCacheMyLinked.LinkedNode tail;

    /**
     * LinkedHashMap 源码类似，维护 HashMap 加双向链表，
     * HashMap：Key 存储参数 Key，Value 存储双向链表地址
     * 双向链表：需要存储参数 Key 与 Value
     * 使用双向链表是因为查询或更新存在的 K-V 时，要将存在的 K-V 放到链表尾部，而放到尾部需要获得 K-V 以及尾结点的前后节点
     */
    public LRUCacheMyLinked(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("容量不能小于 1 ...");
        }
        this.maxCapacity = capacity;
        cache = new HashMap<>((capacity >> 2) * 3);

        // 双向链表头尾，均为虚拟节点
        head = new LRUCacheMyLinked.LinkedNode();
        tail = new LRUCacheMyLinked.LinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 存在 key：
     *     操作 1：先通过 HashMap 找到当前节点，将当前节点移动到链表尾部，
     *     然后返回节点中的 Value
     * 不存在 key：直接返回 -1
     */
    public int get(int key) {
        LRUCacheMyLinked.LinkedNode currNode = cache.get(key);

        if (currNode != null) {
            // 当前节点移动到链表尾
            LinkedNodeLast(currNode);

            return currNode.value;
        } else {
            return -1;
        }
    }

    /**
     * 存在 key：进行操作 1，然后更新节点中的 Value
     * 不存在 key：
     *     元素已满：找到链表头节点，通过 key 删除 HashMap 的元素，然后删除头结点，
     *         操作 2：链表尾结点添加新的节点，内容为 k-v，新增 HashMap 元素、value 指向新的节点
     *     元素未满：元素个数加 1，进行操作 2
     */
    public void put(int key, int value) {
        LRUCacheMyLinked.LinkedNode currNode = cache.get(key);

        if (currNode != null) {
            // 当前节点移动到链表尾
            LinkedNodeLast(currNode);

            currNode.value = value;
        } else {
            // 元素已满
            if (currCapacity >= maxCapacity) {
                LRUCacheMyLinked.LinkedNode firstNode = head.next;
                cache.remove(firstNode.key);

                // 删除第一个节点
                LinkedFirstDelete();
            } else {
                currCapacity++;
            }

            LRUCacheMyLinked.LinkedNode newNode = new LRUCacheMyLinked.LinkedNode(key, value);
            // 链表尾部添加节点
            LinkedNodeInsertLast(newNode);

            cache.put(key, newNode);
        }
    }

    /**
     * 当前节点移动到链表尾
     * @param currNode
     */
    private void LinkedNodeLast(LinkedNode currNode) {
        if (currNode == tail.prev) {
            return;
        }

        // 删除当前节点
        LinkedCurrDelete(currNode);

        // 当前节点加入尾部
        LinkedNodeInsertLast(currNode);
    }

    /**
     * 删除当前节点
     */
    private void LinkedCurrDelete(LinkedNode currNode) {
        currNode.prev.next = currNode.next;
        currNode.next.prev = currNode.prev;
        currNode.prev = null;
        currNode.next = null;
    }

    /**
     * 删除第一个节点
     */
    private void LinkedFirstDelete() {
        LRUCacheMyLinked.LinkedNode firstNode = head.next;
        LinkedCurrDelete(firstNode);
        return;
    }


    /**
     * 链表尾部添加节点
     */
    private void LinkedNodeInsertLast(LinkedNode currNode) {
        tail.prev.next = currNode;
        currNode.prev = tail.prev;
        tail.prev = currNode;
        currNode.next = tail;
    }


    public static void main(String[] args) {
        System.out.println("3");
        LRUCacheMyLinked lRUCacheLinkedHashMap = new LRUCacheMyLinked(2);

        assert lRUCacheLinkedHashMap.maxCapacity == 2;

        assert -1 == lRUCacheLinkedHashMap.get(2);

        lRUCacheLinkedHashMap.put(2, 6);

        assert -1 == lRUCacheLinkedHashMap.get(1);

        lRUCacheLinkedHashMap.put(1, 5);
        lRUCacheLinkedHashMap.put(1, 6);

        assert 6 == lRUCacheLinkedHashMap.get(1);
        assert 6 == lRUCacheLinkedHashMap.get(2);
        assert -1 == lRUCacheLinkedHashMap.get(2);

    }
}
