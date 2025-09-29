package leetcode.contest.contest_469;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
public class Contest1 {

    public static void main(String[] args) {
        Contest1 contest = new Contest1();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    public int[] decimalRepresentation(int n) {
        List<Integer> list = new ArrayList<>();
        String nStr = n + "";
        int base10 = (int) (Math.pow(10, nStr.length() - 1));
        for (char c : nStr.toCharArray()) {
            if (c != '0') {
                list.add((c - '0') * base10);
            }

            base10 /= 10;
        }

        int[] res = list.stream().mapToInt(Integer::intValue).toArray();

        return res;
    }


}
