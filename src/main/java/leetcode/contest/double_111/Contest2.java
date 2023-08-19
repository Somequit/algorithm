package leetcode.contest.double_111;


/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int purchaseAmount = AlgorithmUtils.systemInNumberInt();

//            int res = contest.solution(purchaseAmount);
//            System.out.println(res);
        }

    }

    private boolean solution(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return false;
        }

        int j = 0;
        for (int i = 0; i < str1.length() && j < str2.length(); i++) {
            if (str1.charAt(i) == str2.charAt(j) || str1.charAt(i) + 1 == str2.charAt(j) ||
                    (str1.charAt(i) == 'z' && str2.charAt(j) == 'a')) {
                j++;
            }
        }
        return j == str2.length();
    }



}
