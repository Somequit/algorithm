package leetcode.contest.contest_357;

import javafx.util.Pair;
import utils.AlgorithmUtils;

import java.util.*;


/**
 *
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();
        while (true) {
            int[][] items = AlgorithmUtils.systemInTwoArray();
            int k = AlgorithmUtils.systemInNumberInt();

            long res = contest.solution(items, k);
            System.out.println(res);
        }
    }

        public long solution(int[][] items, int k) {
            int n = items.length;
            ArrayList<Pair<Integer, Integer>>[] profitIndexList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                profitIndexList[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                int profit = items[i][0];
                int category = items[i][1] - 1;
                profitIndexList[category].add(new Pair<>(profit, i));
            }

            Queue<Pair<Integer, Integer>> maxAllCategoryProfit = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                    if (!o2.getKey().equals(o1.getKey())) {
                        return o2.getKey() - o1.getKey();
                    } else {
                        return o1.getValue() - o2.getValue();
                    }
                }
            });

            Queue<Pair<Integer, Integer>> maxDifferentCategoryProfit = new PriorityQueue<>(new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                    if (!o2.getKey().equals(o1.getKey())) {
                        return o2.getKey() - o1.getKey();
                    } else {
                        return o1.getValue() - o2.getValue();
                    }
                }
            });

            for (int i = 0; i < n; i++) {
                Collections.sort(profitIndexList[i], new Comparator<Pair<Integer, Integer>>() {
                    @Override
                    public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                        int profit1 = o1.getKey();
                        int profit2 = o2.getKey();
                        return profit2 - profit1;
                    }
                });
//            System.out.println(i + " : " + profitIndexList[i]);

                if (profitIndexList[i].size() > 0) {
                    for (Pair<Integer, Integer> profitIndex : profitIndexList[i]) {
                        int profit = profitIndex.getKey();
                        int index = profitIndex.getValue();
                        maxAllCategoryProfit.add(new Pair<>(profit, index));
                    }

                    int maxProfit = profitIndexList[i].get(0).getKey();
                    int maxIndex = profitIndexList[i].get(0).getValue();
                    maxDifferentCategoryProfit.add(new Pair<>(maxProfit, maxIndex));
                }
            }
//        System.out.println();
//
//        System.out.println(maxAllCategoryProfit);
//        System.out.println(maxDifferentCategoryProfit);
//        System.out.println();

            int[] visit = new int[n];
            long maxElegance = 0;
            Set<Integer> myCategory = new HashSet<>();

            for (int i = 0; i < k; i++) {
                while (visit[maxAllCategoryProfit.peek().getValue()] == 1) {
                    maxAllCategoryProfit.poll();
                }
                Pair<Integer, Integer> allPair = maxAllCategoryProfit.peek();

                Pair<Integer, Integer> differentPair = new Pair<>(0, 0);
                if (!maxDifferentCategoryProfit.isEmpty()) {
                    differentPair = maxDifferentCategoryProfit.peek();
                }
//            System.out.println(allPair);
//            System.out.println(differentPair);
//            System.out.println(myCategory);
//            System.out.println(items[allPair.getValue()][1] - 1);
//            System.out.println();

                int category = items[allPair.getValue()][1] - 1;
                if (myCategory.contains(category)) {
                    long allElegance = allPair.getKey();
                    long differentElegance = 0;
                    if (differentPair.getKey() > 0) {
                        differentElegance = differentPair.getKey() + 2 * (myCategory.size() + 1) - 1;
                    }

                    if (allElegance > differentElegance) {
                        maxElegance += allElegance;
                        visit[allPair.getValue()] = 1;
                        maxAllCategoryProfit.poll();

                    } else {
                        maxElegance += differentElegance;
                        visit[differentPair.getValue()] = 1;
                        myCategory.add(items[differentPair.getValue()][1] - 1);
                        maxDifferentCategoryProfit.poll();
                    }

                } else {
                    maxElegance += differentPair.getKey() + 2 * (myCategory.size() + 1) - 1;
                    visit[differentPair.getValue()] = 1;
                    myCategory.add(items[differentPair.getValue()][1] - 1);
                    maxAllCategoryProfit.poll();
                    maxDifferentCategoryProfit.poll();
                }

//            System.out.println(allPair);
//            System.out.println(differentPair);
//            System.out.println(myCategory);
//            System.out.println(maxElegance);
//            System.out.println();
            }

            return maxElegance;
        }
}
