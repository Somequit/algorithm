package leetcode.contest.contest_363;


import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            List<Integer> nums = AlgorithmUtils.systemInList();

            long res = contest.solution(nums);
            System.out.println(res);
        }

    }

    private static final int FACTOR_NUM = 10000;
    private static final List<Integer>[] factorList = new ArrayList[FACTOR_NUM];
    private static final List<Integer>[] factorOddList = new ArrayList[FACTOR_NUM];
    static {
        getFactorList();
        getFactorOddList();
    }

    private static void getFactorList() {
        factorList[0] = new ArrayList<Integer>();

        for (int i = 1; i < FACTOR_NUM; i++) {
            factorList[i] = new ArrayList<>();

            int tempI = i + 1;
            for (int j = 2; j * j <= tempI; j++) {
                while (tempI % j == 0) {
                    factorList[i].add(j);
                    tempI /= j;
                }
            }
            if (tempI > 1) {
                factorList[i].add(tempI);
            }
        }
    }

    private static void getFactorOddList() {
        for (int i = 0; i < factorList.length; i++) {
            factorOddList[i] = new ArrayList<Integer>();

            int num = 0;
            for (int j = 0; j < factorList[i].size(); j++) {
                int factorNum = factorList[i].get(j);

                if (j == 0) {
                    num = factorNum;

                } else if (factorNum != factorList[i].get(j - 1)) {
                    if (num > 0) {
                        factorOddList[i].add(num);
                    }
                    num = factorNum;

                } else {
                    num ^= factorList[i].get(j - 1);
                }

            }

            if (num > 0) {
                factorOddList[i].add(num);
            }

        }
    }



    private long solution(List<Integer> nums) {
        int n = nums.size();

//        System.out.println(Arrays.toString(factorOddList));

        long res = 0;
        Map<String, Long> factorMap = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            String factorStr = setToString(factorOddList[i]);

            factorMap.put(factorStr, factorMap.getOrDefault(factorStr, 0L) + nums.get(i));
            res = Math.max(res, factorMap.get(factorStr));
        }


        return res;
    }

    private String setToString(List<Integer> prefixSet) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer prefix : prefixSet) {
            stringBuffer.append(prefix);
            stringBuffer.append("_");
        }

        return stringBuffer.toString();
    }


}
