package leetcode.contest.double_171;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2025/12/6 10:24 下午
 */
public class Double_171_1 {

    public boolean completePrime(int num) {
        int n = (num + "").length();
        String numStr = num + "";
        for (int i = 0; i < n; i++) {
            if (!checkPrime(Integer.parseInt(numStr.substring(0, i + 1))) || !checkPrime(Integer.parseInt(numStr.substring(i, n)))) {
                return false;
            }
        }

        return true;
    }

    private boolean checkPrime(int parseInt) {
//        System.out.println(parseInt);

        if (parseInt == 1) {
            return false;
        }
        for (int i = 2; i * i <= parseInt; i++) {
            if (parseInt % i == 0) {
                return false;
            }
        }

        return true;
    }
}
