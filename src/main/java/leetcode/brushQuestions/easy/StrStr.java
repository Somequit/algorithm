package leetcode.brushQuestions.easy;


/**
 * 28. 找出字符串中第一个匹配项的下标
 */
public class StrStr {

    public static void main(String[] args) {
        StrStr contest = new StrStr();

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
    private int solution(String haystack, String needle) {
        int res = -1;
        int len1 = haystack.length();
        int len2 = needle.length();

        for (int i = 0; i < len1 - len2 + 1; i++) {
            if (haystack.substring(i, i + len2).equals(needle)) {
                res = i;
                break;
            }
        }
        return res;
    }


}
