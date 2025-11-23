package leetcode.contest.contest_477;

import leetcode.Main;

import java.io.IOException;
import java.util.*;
/**
 * @author gusixue
 * @description
 * @date 2025/11/23 10:03 上午
 */
public class Contest_477_4 {

    public static void main(String[] args) throws IOException {
        getNumSubset(8);

        Contest_477_4 contest = new Contest_477_4();

//        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
//        }
    }

    private static void getNumSubset(int maskUpper) {
        // [0,7] 各包含哪些子集
        ArrayList<Integer>[] listMask = new ArrayList[maskUpper];
        for (int mask = 0; mask < maskUpper; mask++) {
            listMask[mask] = new ArrayList<Integer>();

            for (int i = 0; i < maskUpper; i++) {
                if ((mask & i) == i) {
                    listMask[mask].add(i);
                }
            }
        }

        // 0-3 个 bit(1) 各有哪些值
        for (int i = 0; i <= Integer.bitCount(maskUpper - 1); i++) {
            for (int j = 0; j < maskUpper; j++) {
                // i 个 bit(1) 所有子集
                if (Integer.bitCount(j) == i) {
                    System.out.print(Integer.toBinaryString(j) + " : ");
//                    for (int listMaskInt : listMask[j]) {
//                        System.out.print(Integer.toBinaryString(listMaskInt) + " ");
//                    }
                    // bin(111) 减去 bin(11)、bin(101)、bin(110)，减多了（重复减）后需要加上 bin(1)、bin(10)、bin(100)，加多了（重复加）后需要减去 bin(0)
                    dfs(listMask[j], 0, listMask[j].size(), new ArrayList<String>());
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    private static void dfs(ArrayList<Integer> integers, int index, int length, List<String> listTmp) {
        if (index == length) {
            System.out.print(listTmp + " ");
            return;
        }

        dfs(integers, index + 1, length, listTmp);

        listTmp.add(Integer.toBinaryString(integers.get(index)));
        dfs(integers, index + 1, length, listTmp);
        listTmp.remove(listTmp.size() - 1);
    }

}
