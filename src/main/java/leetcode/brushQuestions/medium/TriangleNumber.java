package leetcode.brushQuestions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 611. 有效三角形的个数
 * @date 2025/9/26 9:10 下午
 */
public class TriangleNumber {

    /**
     * 构成三角形特点：最小的俩边之和大于第三边，数组先升序排序，升序枚举最小的俩边，由于第三边不会变小因此可以使用滑动窗口
     * 时间复杂度：O（n^2）,空间复杂度：O（1）
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        // 尽量不改变原有数组
        List<Integer> listNums = Arrays.stream(nums).sorted().boxed().collect(Collectors.toList());
//        System.out.println(listNums);

        // 滑动窗口确定第三边
        int res = 0;
        int len = listNums.size();
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1, k = j + 1; j < len - 1; j++) {
                for (; k < len && listNums.get(i) + listNums.get(j) > listNums.get(k); k++) {
                }

                if (k > j) {
                    res += k - j - 1;

                }
            }
        }

        return  res;
    }
}
