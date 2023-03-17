package leetcode.medium;

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
public class LRUCacheLinkedHashMap extends LinkedHashMap<Integer, Integer> {

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
