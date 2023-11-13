package leetcode.contest_vp.double_107_vp;


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
    private int solution(String[] words) {
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (check(words[i], words[j])) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    private boolean check(String word, String word1) {
        return word.charAt(0) == word1.charAt(1) && word.charAt(1) == word1.charAt(0);
    }


}
