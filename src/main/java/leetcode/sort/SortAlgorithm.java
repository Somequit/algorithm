package leetcode.sort;

/**
 * 数组：Leetcode 912. 排序数组
 * 链表：Leetcode 148. 排序链表
 * 排序算法（升序）
 * 稳定排序：冒泡、插入（直接、折半）、二路归并、计数、桶、基数
 * 不稳定排序：选择、希尔、快速、堆
 */
public class SortAlgorithm {

    public static void main(String[] args) {
        // 稳定排序：
        // 冒泡
//        arrayBubbleSort();
//        LinkedBubbleSort();
        // 插入（直接、折半）
        // 二路归并
        ArrayMergeSort.main(null);
        LinkedMergeSort.main(null);
        // 计数
        // 桶
        // 基数

        // 不稳定排序：
        // 选择
        // 希尔
        // 快速
        ArrayQuickSort.main(null);
        LinkedQuickSort.main(null);
        // 堆
//        arrayHeapSort();
    }

}
