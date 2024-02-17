package leetcode.contest.double_124;

import java.util.*;
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
    public String lastNonEmptyString(String s) {
        List<Integer>[] wordPositionList = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            wordPositionList[i] = new ArrayList<>();
        }
        for (int i = 0; i < s.length(); i++) {
            wordPositionList[s.charAt(i) - 'a'].add(i);
        }

        int maxCount = 0;
        for (int i = 0; i < 26; i++) {
            maxCount = Math.max(maxCount, wordPositionList[i].size());
        }

        TreeMap<Integer, Character> wordPositionChar = new TreeMap<>();
        for (int i = 0; i < 26; i++) {
            if (wordPositionList[i].size() == maxCount) {
                wordPositionChar.put(wordPositionList[i].get(maxCount - 1), (char)(i + 'a'));
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (Character character : wordPositionChar.values()) {
            stringBuffer.append(character);
        }

        return stringBuffer.toString();
    }

}
