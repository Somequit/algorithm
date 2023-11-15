package leetcode.contest_vp.contest_349_vp;


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
    private String solution(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) != 'a') {
                break;
            }
            stringBuffer.append(s.charAt(i));

        }
        if (i == s.length()) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append('z');
        }
        for (; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                break;
            }
            stringBuffer.append((char) (s.charAt(i) - 1));
        }
        for (; i < s.length(); i++) {
            stringBuffer.append(s.charAt(i));
        }
        return stringBuffer.toString();
    }


}
