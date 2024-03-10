package leetcode.contest.contest_388;

import java.util.*;
/**
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

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
    public String[] shortestSubstrings(String[] arr) {
        Map<String, Integer>[] totalStrMap = new HashMap[20];
        for (int i = 0; i < 20; i++) {
            totalStrMap[i] = new HashMap<>();
        }

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j < arr[i].length(); j++) {
                for (int k = j + 1; k < arr[i].length() + 1; k++) {

                    totalStrMap[k - j - 1].merge(arr[i].substring(j, k), 1, Integer::sum);
                }
            }
        }
//        System.out.println(Arrays.toString(totalStrMap));

        String[] res = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = "";

            for (int l = 1; l < arr[i].length() + 1; l++) {

                TreeMap<String, Integer> treeMap = new TreeMap<>();
                for (int j = 0; j + l < arr[i].length() + 1; j++) {

                    treeMap.merge(arr[i].substring(j, j + l), 1, Integer::sum);
                }

                for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
                    if (entry.getValue().equals(totalStrMap[l - 1].get(entry.getKey()))) {
                        res[i] = entry.getKey();
                        break;
                    }
                }
                if (res[i].length() > 0) {
                    break;
                }
            }
        }

        return res;
    }


}
