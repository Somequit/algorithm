package leetcode.brushQuestions.medium;

import javafx.util.Pair;
import leetcode.brushQuestions.easy.SearchInsert;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 2661. 找出叠涂元素
 * @date 2023/12/1
 */
public class FirstCompleteIndex {

    public static void main(String[] args) {
        SearchInsert contest = new SearchInsert();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }
    }


    /**
     * 将 mat 每个数对应的行列号放入 HashMap，然后遍历 arr 数字，找到每个行列号对应加 1，当某个行号的数字加到 n（总列数）、或者列号的数字加到 m（总行数）就是结果
     * 时间复杂度：O（m*n），空间复杂地：O（m*n）
     */
    public int solution(int[] arr, int[][] mat) {
        // 判空
        if(arr == null || mat == null || mat.length <= 0) {
            return -1;
        }

        int m = mat.length;
        int n = mat[0].length;

        // 将 mat 每个数对应的行列号放入 HashMap
        Map<Integer, Pair<Integer, Integer>> matRowColMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matRowColMap.put(mat[i][j], new Pair<>(i, j));
            }
        }

        int res = -1;
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        // 遍历 arr 数字，找到每个行列号对应加 1
        for (int i = 0; i < m * n; i++) {
            Pair<Integer, Integer> rowColPair = matRowColMap.get(arr[i]);

            rowCount[rowColPair.getKey()]++;
            colCount[rowColPair.getValue()]++;

            if (rowCount[rowColPair.getKey()] == n || colCount[rowColPair.getValue()] == m) {
                res = i;
                break;
            }
        }

        return res;

    }
}
