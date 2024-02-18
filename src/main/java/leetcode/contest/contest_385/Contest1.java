package leetcode.contest.contest_385;

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
    public int countPrefixSuffixPairs(String[] words) {
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isPrefixAndSuffix(words[i], words[j])) {
                    res++;
                }
            }
        }

        return res;
    }

    private boolean isPrefixAndSuffix(String word1, String word2) {
        return word2.length() >= word1.length() && word2.substring(0, word1.length()).equals(word1)
                && word2.substring(word2.length() - word1.length()).equals(word1);
    }


}
