package leetcode.contest.contest_356;

import utils.AlgorithmUtils;

import java.util.Arrays;


/**
 *
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            String a = AlgorithmUtils.systemInString();
            String b = AlgorithmUtils.systemInString();
            String c = AlgorithmUtils.systemInString();

            String res = contest.solution(a, b, c);
            System.out.println(res);
        }

    }

    public String solution(String a, String b, String c) {
        String res = getMinimumString(a, b, c);
        res = checkMinimumString(res, getMinimumString(a, c, b));
        res = checkMinimumString(res, getMinimumString(b, a, c));
        res = checkMinimumString(res, getMinimumString(b, c, a));
        res = checkMinimumString(res, getMinimumString(c, a, b));
        res = checkMinimumString(res, getMinimumString(c, b, a));

        return res;
    }

    private String checkMinimumString(String a, String b) {
//        System.out.println("a=" + a + " : b=" + b);
        if (a.length() > b.length()) {
//            System.out.println("resb=" + b);
            return b;

        } else if (a.length() < b.length()) {
//            System.out.println("resa=" + a);
            return a;

        } else {
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) > b.charAt(i)) {
//                    System.out.println("resb=" + b);
                    return b;

                } else if (a.charAt(i) < b.charAt(i)) {
//                    System.out.println("resa=" + a);
                    return a;
                }
            }
        }

//        System.out.println("res=" + a);
        return a;
    }

    private String getMinimumString(String a, String b, String c) {
        StringBuffer temp = getTwoMinimumString(a, b);
//        System.out.println(temp);

        temp = getTwoMinimumString(temp.toString(), c);
//        System.out.println(temp);

        return temp.toString();
    }

    private StringBuffer getTwoMinimumString(String a, String b) {
        StringBuffer temp = new StringBuffer(a);

        if (a.length() > b.length() && a.split(b).length > 1) {
//            System.out.println(a + " : " + b + " : " + Arrays.toString(a.split(b)));
            return temp;
        }

        int minLen = Math.min(a.length(), b.length());
        for (int i = minLen; i >= 0; i--) {
            if (i == 0) {
                temp.append(b);

            } else {
                String aTemp = a.substring(a.length() - i);
                String bTemp = b.substring(0, i);
//                System.out.println(aTemp + " : " + bTemp);
                if (aTemp.equals(bTemp)) {
                    temp.append(b.substring(i));
                    break;
                }
            }
        }

        return temp;
    }


}
