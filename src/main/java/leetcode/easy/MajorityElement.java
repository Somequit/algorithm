package leetcode.easy;

import utils.AlgorithmUtils;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * @author gusixue
 * @date 2023/2/2
 */
public class MajorityElement {

    public static void main(String[] args) {

        while (true) {
            int[] element = AlgorithmUtils.systemInArray();

            MajorityElement majorityElement = new MajorityElement();
            int res = majorityElement.solution(element);

            System.out.println(res);
        }
    }

    /**
     * 只有一个元素即是它，超过一个元素，则循环一遍数组，将不同的元素两两"抵消"，相同的元素则记下元素极其个数，最后剩下的大于 0 个的元素即是结果
     */
    private int solution(int[] element) {

        // 判空
        if (element == null || element.length == 0) {
            return 0;
        }

        int res = element[0];
        int num = 1;
        int len = element.length;
        for (int i = 1; i < len; i++) {
            if (element[i] == res) {
                num++;
            } else if (num == 0) {
                res = element[i];
                num++;
            } else {
                num--;
            }
        }

        return res;
    }
}
