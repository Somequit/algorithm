package leetcode.contest.contest_358;

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
            List<Integer> nums = AlgorithmUtils.systemInList();
            int k = AlgorithmUtils.systemInNumberInt();

            int res = contest.solution(nums, k);
            System.out.println(res);
        }
    }

    public int solution(List<Integer> nums, int k) {
        long mod = 1_000_000_007;

        int n = nums.size();
        int maxNum = 0;
        for (Integer num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        boolean[] isPrime = new boolean[maxNum + 1];
        List<Integer> primNum = initPrim(maxNum, isPrime);
//        System.out.println(primNum);

        int[] factorCount = new int[n];
        int[] vis = new int[maxNum + 1];
        for (int i = 0; i < n; i++) {
            factorCount[i] = getFactorCount(nums.get(i), primNum, vis, isPrime);
//            System.out.println(nums.get(i) + " : " + factorCount[i]);
        }

        long[] numsPowNum = new long[n];
        Arrays.fill(numsPowNum, 1);

        // index--factorCount[i]
        Deque<Pair<Integer, Integer>> increaseStack = new LinkedList<>();
        increaseStack.addFirst(new Pair<>(-1, Integer.MAX_VALUE));
        for (int i = 0; i < n; i++) {
            while (increaseStack.peekFirst().getValue() < factorCount[i]) {
                increaseStack.pollFirst();
            }
            numsPowNum[i] *= i - increaseStack.peekFirst().getKey();
//            System.out.println(increaseStack + " : " + factorCount[i]);

            increaseStack.addFirst(new Pair<>(i, factorCount[i]));
        }
//        System.out.println(Arrays.toString(numsPowNum));

        increaseStack = new LinkedList<>();
        increaseStack.addFirst(new Pair<>(n, Integer.MAX_VALUE));
        for (int i = n - 1; i >= 0; i--) {
            while (increaseStack.peekFirst().getValue() <= factorCount[i]) {
                increaseStack.pollFirst();
            }
            numsPowNum[i] *= increaseStack.peekFirst().getKey() - i;
//            System.out.println(increaseStack + " : " + factorCount[i]);

            increaseStack.addFirst(new Pair<>(i, factorCount[i]));
        }
//        System.out.println(Arrays.toString(numsPowNum));

        List<Pair<Integer, Long>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair(nums.get(i), numsPowNum[i]));
        }

        Collections.sort(list, new Comparator<Pair<Integer, Long>>() {
            @Override
            public int compare(Pair<Integer, Long> o1, Pair<Integer, Long> o2) {
                return o2.getKey() - o1.getKey();
            }
        });

//        System.out.println(list);

        long kTemp = k;
        long res = 1;
        for (int i = 0; i < n; i++) {
            res *= qPow(list.get(i).getKey(), Math.min(kTemp, list.get(i).getValue()), mod);
            res %= mod;
            kTemp -= list.get(i).getValue();
            if (kTemp <= 0) {
                break;
            }
        }

        return (int)(res%mod);
    }

    private long qPow(int value, long pow, long mod) {
        long res = 1;
        long valueTemp = value;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res *= valueTemp;
                res %= mod;
            }

            valueTemp *= valueTemp;
            valueTemp %= mod;
            pow >>= 1;
        }
        return res;
    }

    private int getFactorCount(int num, List<Integer> primNum, int[] vis, boolean[] isPrime) {
        if (num == 1) {
            return 0;
        }

        if (isPrime[num]) {
            return 1;
        }

        if (vis[num] != 0) {
            return vis[num];
        }

        int res = 0;

        int n = primNum.size();
        int numTemp = num;
        for (int i = 0; i < n; i++) {
            if (primNum.get(i) * primNum.get(i) > numTemp) {
                if (numTemp > 1) {
                    res++;
                }
                break;
            }
            if (numTemp % primNum.get(i) == 0) {
                res++;
                while (numTemp % primNum.get(i) == 0) {
                    numTemp /= primNum.get(i);
                }
            }
        }

        vis[num] = res;
//        System.out.println("vis:" + num + " : " + res);
        return res;
    }

    private static List<Integer> initPrim(int toNum, boolean[] isPrime) {
        List<Integer> primNum = new ArrayList<>(toNum);

        for (int i = 2;i <= toNum;i++) {
            isPrime[i] = true;
        }

        for (long i = 2;i <= toNum;i++) {
            if (isPrime[(int)i]) {
                for (long j = i*i; j <= toNum;j+=i) {
                    isPrime[(int)j] = false;
                }
            }
        }

        for (int i = 2;i <= toNum;i++) {
            if (isPrime[i]) {
                primNum.add(i);
            }
        }

        return primNum;
    }



}
