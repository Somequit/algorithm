package leetcode.contest.double_119;


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
    public int removeAlmostEqualCharacters(String word) {
        int res = 0;
        for (int i = 1; i < word.length(); i++) {
            if (checkChar(word.charAt(i), word.charAt(i - 1))) {
                res++;
                i++;
            }
        }

        return res;
    }

    private boolean checkChar(char c, char c1) {
        return Math.abs(c - c1) <= 1;
    }


}
