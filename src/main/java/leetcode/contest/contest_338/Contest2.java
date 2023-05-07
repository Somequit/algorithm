package leetcode.contest.contest_338;

import utils.AlgorithmUtils;


/**
 * @author gusixue
 * @date 2023/3/19
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
            int[] nums = AlgorithmUtils.systemInArray();

            boolean res = contest.primeSubOperation(nums);
            System.out.println(res);
        }

    }

    public boolean primeSubOperation(int[] nums) {
        int[] primeNums = new int[1001];

        pagePrimeSet(primeNums, 2, 1000);
//        System.out.println(Arrays.toString(primeNums));

        boolean res = structureNums(nums, primeNums);

        return res;
    }

    private void pagePrimeSet(int[] primeNums, int begin, int end) {
        int prevPrime = 3;
        primeNums[3] = 2;

        for (int i = 4; i <= end; i++) {
            boolean flag = true;

            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                primeNums[i] = primeNums[i - 1];
                prevPrime = i;
//                System.out.println(i);
            } else {
                primeNums[i] = prevPrime;
            }
        }
    }

    private boolean structureNums(int[] nums, int[] primeNums) {
        nums[0] = nums[0] - primeNums[nums[0]];
        int numsLen = nums.length;
        boolean res = true;

        for (int i = 1; i < numsLen; i++) {
//            System.out.println(nums[i] + ":" + nums[i - 1]);


            for (int j = nums[i]; j >= 0; j--) {
                if (nums[i] - primeNums[j] > nums[i - 1]) {
                    nums[i] -= primeNums[j];
                    break;
                }
            }
//            System.out.println(nums[i] + ":" + nums[i - 1]);

            if (nums[i] <= nums[i - 1]) {
                res = false;
                break;
            }

        }
//        System.out.println(Arrays.toString(nums));
        return res;
    }

}
