package template;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

        // 元音、数组、大小写字母判断
        checkVowel();
    }

    private static void checkVowel() {
        Set<Character> setLowerVowel = Arrays.stream(new Character[]{'a', 'e', 'i', 'o', 'u'}).collect(Collectors.toSet());
        Set<Character> setUpperVowel = Arrays.stream(new Character[]{'A','E','I','O','U'}).collect(Collectors.toSet());
        Set<Character> setLowerNotVowel = Arrays.stream(new Character[]{'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'}).collect(Collectors.toSet());
        Set<Character> setUpperNotVowel = Arrays.stream(new Character[]{'B','C','D','F','G','H','J','K','L','M','N','P','Q','R','S','T','V','W','X','Y','Z'}).collect(Collectors.toSet());
        Set<Character> setNums = Arrays.stream(new Character[]{'0','1','2','3','4','5','6','7','8','9'}).collect(Collectors.toSet());

        for (char c = 'a'; c <= 'z'; c++) {
            if (!setLowerVowel.contains((char)(c - 'A' + 'a'))) {
                System.out.print("\'" + c + "\'" + ",");
            }
        }
    }

    private static void createCollectionArrayByStream() {
        // 集合数组初始化
        int n = 10;
        Set<Integer>[] edgesSet = Stream.generate(HashSet::new).limit(n).toArray(Set[]::new);
        Arrays.setAll(edgesSet, i -> new HashSet<>());

        // list.sort 排序
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.sort(Comparator.naturalOrder());
        list.sort(Comparator.reverseOrder());

        // int 数组倒序排序：当然可以直接 Arrays.sort()，然后循环交换数组
        int[] nums1 = new int[]{1, 5, 89, 2, 5, 8, 5, 5, 5};
        nums1 = IntStream.of(nums1)
        // 变为 Stream<Integer>
        .boxed()
        .sorted(Comparator.reverseOrder())
        // 变为 IntStream
        .mapToInt(Integer::intValue)
        // 又变回 int[]
        .toArray();
        System.out.println(Arrays.toString(nums1));

        // Integer 数组倒序
        Integer[] nums2 = new Integer[]{1, 5, 2, 5, 8, 5, 5, 5};
        Arrays.sort(nums2, Collections.reverseOrder());
        System.out.println(Arrays.toString(nums2));


        // int 数组反转
        int[] nums3 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        reverseArr(nums3);
        System.out.println(Arrays.toString(nums3));
    }

    private static void reverseArr(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[n - i - 1];
            nums[n - i - 1] = temp;
        }
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
