package leetcode.contest_vp.contest_374_vp;


/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public int countCompleteSubstrings(String word, int k) {
        int n = word.length();
        int wordTotal = 26;

        int[][] wordCount = new int[n + 1][wordTotal];

        int res = 0;
        int prevLast = 0;
        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < wordTotal; j++) {
                wordCount[i][j] = wordCount[i - 1][j];
            }
            wordCount[i][word.charAt(i - 1) - 'a']++;
//            System.out.println(i + " : " + Arrays.toString(wordCount[i]));

            int curWord = word.charAt(i - 1) - 'a';
            int prevWord = i > 1 ? word.charAt(i - 2) - 'a' : 0;
            if (i > 1 && Math.abs(curWord - prevWord) > 2) {
                prevLast = i - 1;
            }

            res += countSubstring(i, k, prevLast, wordCount, wordTotal);
//            System.out.println(i + " : " + res);

        }

        return res;

    }

    private int countSubstring(int index, int k, int prevLast, int[][] wordCount, int wordTotal) {
        int res = 0;

        int indexTemp = index;

        while (indexTemp >= k + prevLast) {
            indexTemp -= k;

            boolean flag = true;
            for (int j = 0; j < wordTotal; j++) {
                if (wordCount[index][j] - wordCount[indexTemp][j] > k) {
                    return res;

                } else if (wordCount[index][j] - wordCount[indexTemp][j] > 0
                        && wordCount[index][j] - wordCount[indexTemp][j] < k) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res++;
            }
        }

        return res;
    }


}
