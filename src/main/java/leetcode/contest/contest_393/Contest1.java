package leetcode.contest.contest_393;

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
    public String findLatestTime(String s) {
        StringBuffer stringBuffer = new StringBuffer();

        if (s.charAt(0) == '?' && s.charAt(1) == '?') {
            stringBuffer.append("11");

        } else if (s.charAt(0) == '?') {
            if (s.charAt(1) == '0' || s.charAt(1) == '1') {
                stringBuffer.append('1').append(s.charAt(1));

            } else {
                stringBuffer.append('0').append(s.charAt(1));
            }

        } else if (s.charAt(1) == '?') {
            if (s.charAt(0) == '0') {
                stringBuffer.append(s.charAt(0)).append('9');

            } else {
                stringBuffer.append(s.charAt(0)).append('1');
            }

        } else {
            stringBuffer.append(s.charAt(0)).append(s.charAt(1));
        }

        stringBuffer.append(':');

        if (s.charAt(3) == '?' && s.charAt(4) == '?') {
            stringBuffer.append("59");

        } else if (s.charAt(3) == '?') {
            stringBuffer.append('5').append(s.charAt(4));

        } else if (s.charAt(4) == '?') {
            stringBuffer.append(s.charAt(3)).append('9');

        } else {
            stringBuffer.append(s.charAt(3)).append(s.charAt(4));
        }

        return stringBuffer.toString();
    }


}
