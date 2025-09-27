package leetcode.contest.double_166;

import java.util.HashMap;
import java.util.Map;


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
    public String majorityFrequencyGroup(String s) {
        int[] frequencyNum = new int[26];
        for (char c : s.toCharArray()) {
            frequencyNum[c - 'a']++;
        }

        Map<Integer, StringBuilder> kCharMap = new HashMap<>();
        StringBuilder res = new StringBuilder();
        int maxCharNum = 0;
        int maxK = 0;
        for (int i = 0; i < 26; i++) {
            if (frequencyNum[i] > 0) {

//                StringBuilder curStr = kCharMap.get(frequencyNum[i]);
////                System.out.println(i + " : " + frequencyNum[i] + " : " + curStr);
//                if (curStr != null) {
//                    curStr.append((char)(i + 'a'));
//
//                } else {
//                    curStr = new StringBuilder().append((char)(i + 'a'));
//                    kCharMap.put(frequencyNum[i], curStr);
//                }
                kCharMap.computeIfAbsent(frequencyNum[i], x -> new StringBuilder()).append((char)(i + 'a'));
                StringBuilder curStr = kCharMap.get(frequencyNum[i]);

                if (curStr.length() > maxCharNum) {
                    maxCharNum = curStr.length();
                    maxK = frequencyNum[i];
                    res = curStr;

                } else if (curStr.length() == maxCharNum && frequencyNum[i] > maxK) {
                    maxK = frequencyNum[i];
                    res = curStr;
                }
            }
        }

        return res.toString();
    }


}
