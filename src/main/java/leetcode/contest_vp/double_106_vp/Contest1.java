package leetcode.contest_vp.double_106_vp;


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
    private boolean solution(int n) {
        int[] count = new int[10];
        for (int i = 1; i < 4; i++) {
            String str = i * n + "";
            for (int j = 0; j < str.length(); j++) {
                count[str.charAt(j) - '0']++;
            }
        }

        if (count[0] > 1) {
            return false;
        }
        for (int i = 1; i < 10; i++) {
            if (count[i] != 1) {
                return false;
            }
        }
        return true;
    }


}
