package leetcode.contest.contest_469;

/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

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
    public long splitArrayContest(int[] nums) {
        int splitLeft = -1;
        int splitRight = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                if (splitLeft == -1) {
                    splitLeft = i + 1;
                    splitRight = i + 1;

                } else {
                    return -1;
                }
            }
        }
//        System.out.println(splitLeft + " : " + splitRight);

        if (splitLeft == -1) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    splitLeft = i;
                    splitRight = i + 1;
                    break;
                }
            }
        }
//        System.out.println(splitLeft + " : " + splitRight);

        if (splitLeft == -1) {
            splitLeft = nums.length - 1;
            splitRight = nums.length - 1;
        }
        if (splitLeft == 0) {
            splitLeft = 1;
        }

        long left = 0;
        long right = 0;
        for (int i = 0; i < splitLeft - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                return -1;
            }
            left += nums[i];
        }
        left += nums[splitLeft - 1];

        for (int i = splitRight; i < nums.length - 1; i++) {
            if (nums[i] <= nums[i + 1]) {
                return -1;
            }
            right += nums[i];
        }
        right += nums[nums.length - 1];

        if (splitLeft == splitRight) {
            return Math.abs(left - right);
        } else {
            return Math.min(Math.abs(left - right - nums[splitLeft]), Math.abs(left - right + nums[splitLeft]));
        }

    }

    /**
     * 从左往右找严格递增，然后从右往左找严格递增，再判断情况
     */
    public long splitArray(int[] nums) {
        int len = nums.length;

        long leftTotal = 0;
        // 当前下标要么是最后一个下标、要么下一个不再严格递增
        int leftI = 0;
        for (; leftI < len-1; leftI++) {
            leftTotal += nums[leftI];

            if (nums[leftI] >= nums[leftI + 1]) {
                break;
            }
        }
        if (leftI == len - 1) {
            leftTotal += nums[len - 1];
        }

        long rightTotal = 0;
        // 当前下标要么是第一个下标、要么上一个不再严格递增
        int rightI = nums.length - 1;
        for (; rightI > 0; rightI--) {
            rightTotal += nums[rightI];

            if (nums[rightI] >= nums[rightI - 1]) {
                break;
            }
        }
        if (rightI == 0) {
            rightTotal += nums[0];
        }

        if (rightI - leftI > 1) {
            return -1;
        } else if (rightI - leftI == 1) {
            return Math.abs(leftTotal - rightTotal);
        } else {
            return Math.min(Math.abs(leftTotal - rightTotal + nums[leftI]), Math.abs(leftTotal - rightTotal - nums[leftI]));
        }
    }


}
