package leetcode.brushQuestions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * 13. 罗马数字转整数
 * @date 2023/9/23
 */
public class RomanToInt {

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i != s.length() - 1 && checkSubtract(s.charAt(i), s.charAt(i + 1))) {
                res -= map.get(s.charAt(i));

            } else {
                res += map.get(s.charAt(i));
            }
            System.out.println(res);
        }

        return res;
    }

    private boolean checkSubtract(char c, char c1) {
        if ((c == 'I' && c1 == 'V')
                || (c == 'I' && c1 == 'X')
                || (c == 'X' && c1 == 'L')
                || (c == 'X' && c1 == 'C')
                || (c == 'C' && c1 == 'D')
                || (c == 'C' && c1 == 'M')) {
            return true;
        }
        
        return false;
    }
}
