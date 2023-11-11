package leetcode.hot_100.medium;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
public class LRUCacheMyLinked {

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
