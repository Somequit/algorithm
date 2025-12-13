package leetcode.brushQuestions.easy;

import java.util.*;

/**
 * @author gusixue
 * @description 3606. 优惠券校验器
 * @date 2025/12/13 11:38 下午
 */
public class ValidateCoupons {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        List<String>[] listCodeBusiness = new ArrayList[4];
        Arrays.setAll(listCodeBusiness, s -> new ArrayList<>());

        for (int i = 0; i < code.length; i++) {
            if (!isActive[i]) {
                continue;
            }
            if (!checkCode(code[i])) {
                continue;
            }
            if (businessLine[i] == null) {
                continue;
            }

            if (businessLine[i].equals("electronics")) {
                listCodeBusiness[0].add(code[i]);

            } else if (businessLine[i].equals("grocery")) {
                listCodeBusiness[1].add(code[i]);

            } else if (businessLine[i].equals("pharmacy")) {
                listCodeBusiness[2].add(code[i]);

            } else if (businessLine[i].equals("restaurant")) {
                listCodeBusiness[3].add(code[i]);

            }

        }

        listCodeBusiness[0].sort(String::compareTo);
        List<String> res = new ArrayList<>(listCodeBusiness[0]);
        listCodeBusiness[1].sort(String::compareTo);
        res.addAll(listCodeBusiness[1]);
        listCodeBusiness[2].sort(String::compareTo);
        res.addAll(listCodeBusiness[2]);
        listCodeBusiness[3].sort(String::compareTo);
        res.addAll(listCodeBusiness[3]);

        return res;
    }

    private boolean checkCode(String code) {
        if (code == null || code.length() == 0) {
            return false;
        }
        for (char c : code.toCharArray()) {
            if (c != '_' && !Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        return true;
    }

}
