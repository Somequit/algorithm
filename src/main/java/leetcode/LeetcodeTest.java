package leetcode;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 854. 相似度为 K 的字符串
 * 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
 * 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1 和 s2  只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母
 * s2 是 s1 的一个字母异位词
 * @date 2023/6/18
 */
public class LeetcodeTest {

    public static void main(String[] args) {
        LeetcodeTest leetcodeTest = new LeetcodeTest();
        while (true) {
            String s1 = AlgorithmUtils.systemInString();
            String s2 = AlgorithmUtils.systemInString();

            int res = leetcodeTest.solution(s1, s2);
            System.out.println(res);

            int res2 = leetcodeTest.solutionOptimization(s1, s2);
            System.out.println(res2);

        }
    }


    /**
     * BFS：找到 s1 每次第一个与 s2 不同的元素下标 i，找到 s1 后面与 s2[i] 相同的下标 j，交换 i、j 放入队列中，直接进行 BFS
     */
    private int solution(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }

        int n = s1.length();
        char[] char2 = s2.toCharArray();

        Map<String, Integer> visMap = new HashMap<>();
        visMap.put(s1, 0);
        Deque<String> queue = new LinkedList<>();
        queue.offer(s1);

        while (!queue.isEmpty()) {
            String curS = queue.poll();
            char[] charCur = curS.toCharArray();
//            System.out.println("curS:" + curS);

            for (int i = 0; i < n; i++) {
                if (char2[i] != charCur[i]) {

                    for (int j = i + 1; j < n; j++) {
                        if (char2[i] == charCur[j]) {
                            swap(charCur, i, j);
                            String newS = new String(charCur);
                            swap(charCur, i, j);
//                            System.out.println(i + " : " + j);
//                            System.out.println("newS:" + newS);

                            if (s2.equals(newS)) {
                                return visMap.get(curS) + 1;

                            } else if (!visMap.containsKey(newS)) {
                                visMap.put(newS, visMap.get(curS) + 1);
                                queue.offer(newS);
                            }

                        }
                    }
                    break;
                }
            }


        }

        return -1;
    }

    private void swap(char[] charCur, int i, int j) {
        char temp = charCur[i];
        charCur[i] = charCur[j];
        charCur[j] = temp;
    }

    /**
     * A* 搜索：BFS 搜索基础上添加预估函数 h(s) 代表 s 与 s2 交换的最小次数，g(s) 代表 s1 变成 s 实际交换次数，使用优先队列返回每次 f(x)=g(x)+h(x) 的最小值，
     * 注意：由于 A* 限制，所以 BFS 搜索过程中如果 s[i]=s2[i]，则此位一定不能移动，
     * 注意：没有入过队 或者 入过队但是 g(x) 更新变小还需要再次入队
     * 交换的最小次数：每一次都能够交换 2 个正确的位置（如有），即 s 与 s2 不同元素个数除 2 上取整
     */
    private int solutionOptimization(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }

        this.t = s2;
        int n = s1.length();
        char[] char2 = s2.toCharArray();

        Map<String, Integer> visMap = new HashMap<>();
        visMap.put(s1, 0);
        PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> {
            int g1 = visMap.get(o1);
            int h1 = minSwapCount(o1);
            int g2 = visMap.get(o2);
            int h2 = minSwapCount(o2);
            return (g1 + h1) - (g2 + h2);
        });
        queue.add(s1);

        while (!queue.isEmpty()) {
            String curS = queue.poll();
            char[] charCur = curS.toCharArray();

            for (int i = 0; i < n; i++) {
                if (char2[i] != charCur[i]) {

                    for (int j = i + 1; j < n; j++) {
                        // 注意：由于 A* 限制，所以 BFS 搜索过程中如果 s[i]=s2[i]，则此位一定不能移动，
                        if (char2[i] == charCur[j] && char2[j] != charCur[j]) {
                            swap(charCur, i, j);
                            String newS = new String(charCur);
                            swap(charCur, i, j);

                            if (s2.equals(newS)) {
                                return visMap.get(curS) + 1;

                            // 没有入过队 或者 入过队但是 g(x) 更新变小还需要再次入队
                            } else if (!visMap.containsKey(newS) || visMap.get(newS) > visMap.get(curS) + 1) {
                                visMap.put(newS, visMap.get(curS) + 1);
                                queue.add(newS);
                            }
                        }
                    }
                    break;
                }
            }


        }

        return -1;
    }

    private String t;

    private int minSwapCount(String s1) {
        int res = 1;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != this.t.charAt(i)) {
                res++;
            }
        }
        return res >> 1;
    }

}
