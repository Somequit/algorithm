package leetcode.contest_vp.contest_348_vp;


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
    private int solution(String s) {
//        int[] count = new int[26];
//        int res = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (count[s.charAt(i) - 'a'] == 0) {
//                count[s.charAt(i) - 'a'] = 1;
//                res++;
//            }
//        }
//        return res;

        int mask = 0;
        for (char c : s.toCharArray()) {
            mask |= (1 << (c - '0'));
        }
        return Integer.bitCount(mask);
    }


}
