package jianzhioffer;

import utils.AlgorithmUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个文本文件里有100亿条数据，每个数据以回车换行分隔，格式如下：
 * 123DKFJIE456D12D
 * SDF2345DKJFKDDS1
 * SFJD234DEEF451DS
 * SDF2345DKJFKDDS1
 * 要求找出这100万条数据中是否有重复的数据、重复次数并输出、及最热门的10个数据，
 * 比如举个例子上面SDF2345DKJFKDDS1重复两次
 *
 * 思路：
 * 典型TopK问题
 * 1.如果只求是否重复、且接受一定程度的误差：使用布隆过滤器
 *
 * 2.如果能接受一定程度的误差：将数据Hash成数字，然后使用位图排序思路，将数字当作下标“+1”放入位图数组中（由于Hash会导致数据重复）
 *
 * 3.如果不能接受误差：Hash+桶 * 可以先将数据Hash成数字，然后将Hash表%num，平均放入num个桶中，桶中会存储原始数据data与出现的次数count，
 * 然后将num个桶中最热门的前10个数拿出来，使用二路归并分治或者大小为10的大顶堆，选出最终前10个最热门数据
 */
public class MassDataTopK {

    public static void main(String[] args) {
        while (true) {
            int count = AlgorithmUtils.systemInNumberInt();
            String[] sources = new String[count];
            for(int i=0; i<count; i++) {
                sources[i] = AlgorithmUtils.systemInString();
            }

            Map<String, Integer> repeatMap = getRepeatMap(sources, count);
            AlgorithmUtils.systemOutMap(repeatMap, " ");
        }
    }

    /**
     * 字符串中有重复的串
     * hash+位图
     * @param sources
     * @param count
     * @return
     */
    private static Map<String, Integer> getRepeatMap(String[] sources, int count) {
        Map<String, Integer> resultMap = new HashMap<>();

        // 位图存储，按位存在int所有数据（-2^31~2^31-1 / 8）
        byte[] bitmap = new byte[1<<29];

        for (String str : sources) {
            int hash = str.hashCode();
            // 获取byte数组下标的位置
            int hashBitHigh = (hash >>> 3);
            // 每个byte数组可以标识8位（0~7）
            int hashBitLower = (hash & 7);
            byte b = bitmap[hashBitHigh];
//            System.out.println("hashBitHigh:" + hashBitHigh);
//            System.out.println("hashBitLower:" + hashBitLower);

            if ((b & (1 << hashBitLower)) > 0) {
                Integer countInteger = resultMap.get(str);
                countInteger = countInteger==null?1:countInteger;
                resultMap.put(str, countInteger + 1);
            } else {
                b |= (1 << hashBitLower);
            }
            bitmap[hashBitHigh] = b;
//            System.out.println("b:" + bitmap[hashBitHigh]);
        }

        return resultMap;
    }

}
