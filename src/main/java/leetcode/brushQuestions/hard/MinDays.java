package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 1553. 吃掉 N 个橘子的最少天数
 * @date 2024/05/12
 */

public class MinDays {

    public static void main(String[] args) {
        MinDays minDays = new MinDays();
        // 暴力
        System.out.println(minDays.doMinDays(10000));
        // 记忆化搜索：Math.min((n % 2) + dfs(n / 2, mapCount), (n % 3) + dfs(n / 3, mapCount)) + 1;
        System.out.println(minDays.minDays(10000));
    }

    public int minDays(int n) {
        if (n <= 1) {
            return 1;
        }
        
        Map<Integer, Integer> mapCount = new HashMap<>();
        mapCount.put(1, 1);
        mapCount.put(0, 0);

        dfs(n, mapCount);

        return mapCount.get(n);
    }

    private int dfs(int n, Map<Integer, Integer> mapCount) {
        if (mapCount.containsKey(n)) {
            return mapCount.get(n);
        }

        int res = Math.min((n % 2) + dfs(n / 2, mapCount), (n % 3) + dfs(n / 3, mapCount)) + 1;
        mapCount.put(n, res);
        return res;
    }


    private int doMinDays(int n) {
        Set<Integer>[] setNum = new Set[n + 1];
        Arrays.setAll(setNum, i -> new HashSet<>());

        setNum[0].add(n);
        for (int i = 1; i < n; i++) {
            for (int num : setNum[i - 1]) {
                setNum[i].add(num - 1);

                if (num % 3 == 0) {
                    setNum[i].add(num / 3);

                }
                if (num % 2 == 0) {
                    setNum[i].add(num / 2);

                }
            }

            if (setNum[i].contains(0)) {
                return i;
            }
        }

        return n;
    }
}
