package template;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author gusixue
 * @description
 * @date 2023/10/31
 */
public class Algorithm {

    public static void main(String[] args) {
        // 字符串最少 6 位，不够前面补 0
        stringBeforeAdd("1567", 6, "0");
        stringBeforeAdd("1234567", 6, "0");

        // 离散化
        discretizing(Arrays.asList());

        // 创建集合数组
        createCollectionArrayByStream();
    }

    private static void createCollectionArrayByStream() {
        int n = 10;
        Set<Integer>[] edgesSet = Stream.generate(HashSet::new).limit(n).toArray(Set[]::new);
        Arrays.setAll(edgesSet, i -> new HashSet<>());

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.sort(Comparator.naturalOrder());
    }

    private static void discretizing(List<Integer> nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            treeSet.add(num);
        }
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer key : treeSet) {
            map.put(key, count);
            count++;
        }
    }

    private static void stringBeforeAdd(String str, int len, String fill) {
        System.out.println(String.format("%" + len + "s", str).replace(" ", fill));
    }
}
