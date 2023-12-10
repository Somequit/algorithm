package leetcode.contest.contest_375;

import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.*;

/**
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

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
    public int numberOfGoodPartitions(int[] nums) {
        int mod = (int)1e9+7;

        Map<Integer, List<Integer>> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!numMap.containsKey(nums[i])) {
                numMap.put(nums[i], new ArrayList<>());
            }

            List<Integer> listTemp = numMap.get(nums[i]);
            listTemp.add(i);
        }
//        System.out.println(numMap);

        int[][] sameNums = new int[numMap.size()][2];
        int count = 0;
        for (List<Integer> listTemp : numMap.values()) {
            sameNums[count][0] = listTemp.get(0);
            sameNums[count][1] = listTemp.get(listTemp.size() - 1);

            count++;
        }

        Arrays.sort(sameNums, (o1, o2) -> o1[0] - o2[0]);
//        AlgorithmUtils.systemOutArray(sameNums);

        int diffCount = 1;
        int maxIndex = sameNums[0][1];
        for (int i = 1; i < sameNums.length; i++) {
            if (sameNums[i][0] > maxIndex) {
                diffCount++;
            }

            maxIndex = Math.max(maxIndex, sameNums[i][1]);
        }

        return (int)qPow(2, diffCount - 1, mod);
    }

    private long qPow(long value, long pow, long mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res *= value;
                res %= mod;
            }

            value *= value;
            value %= mod;
            pow >>= 1;
        }
        return res;
    }


}
