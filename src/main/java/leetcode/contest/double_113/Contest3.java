package leetcode.contest.double_113;


import utils.AlgorithmUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @date 2023/7/22
 */
public class Contest3 {

    public static void main(String[] args) {
        Contest3 contest = new Contest3();

        while (true) {
            List<List<Integer>> coordinates = Arrays.stream(AlgorithmUtils.systemInTwoArrayInteger())
                    .map(Arrays::asList).collect(Collectors.toList());
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(coordinates, k);
            System.out.println(res);
        }

    }

    private int solution(List<List<Integer>> coordinates, int k) {
        Map<Long, Integer> coordinateCountMap = new HashMap<>(coordinates.size() << 1);

        int res = 0;
        for (int i = 0; i < coordinates.size(); i++) {
            int x = coordinates.get(i).get(0);
            int y = coordinates.get(i).get(1);

            if (i == 0) {
                coordinateCountMap.put(getCoordinates(x, y), 1);
                continue;
            }

            for (int j = 0; j <= k; j++) {
                int xx = (x ^ j);
                int yy = (y ^ (k - (x ^ xx)));
//                System.out.println(xx + " : " + yy);
                res += coordinateCountMap.getOrDefault(getCoordinates(xx, yy), 0);
//                System.out.println(res);
            }
            long c = getCoordinates(x, y);
            coordinateCountMap.put(c, coordinateCountMap.getOrDefault(c, 0) + 1);
        }
//        System.out.println(coordinateCountMap);

        return res;
    }

    private Long getCoordinates(int x, int y) {
        long res =  (long)x * 10_000_000 + y;
//        System.out.println(x + " : " + y + " : " +res);
        return res;
    }


}
