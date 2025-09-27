package leetcode.contest.double_166;

import java.awt.*;
import java.nio.channels.Pipe;
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
    public int distinctPoints(String s, int k) {
        Map<Character, Integer>  moveCharToIntMap = new HashMap<>();
        moveCharToIntMap.put('U', 1);
        moveCharToIntMap.put('D', -1);
        moveCharToIntMap.put('L', 2);
        moveCharToIntMap.put('R', -2);

        Point curPoint = new Point(0, 0);

        for (int i = 0; i < k; i++) {
            movePoint(curPoint, moveCharToIntMap.get(s.charAt(i)));
        }
//        System.out.println(point.x + " : " + point.y);

        Set<Point> dedupSet = new HashSet<>();
        dedupSet.add(new Point(curPoint));

        for (int i = k; i < s.length(); i++) {
            movePoint(curPoint, moveCharToIntMap.get(s.charAt(i)));
            movePoint(curPoint, -moveCharToIntMap.get(s.charAt(i - k)));
//            System.out.println(tmp.x + " : " + tmp.y);

            dedupSet.add(new Point(curPoint));
        }

        return dedupSet.size();
    }

    private void movePoint(Point point, int move) {
        if (move == 1) {
            point.y = point.y + 1;

        } else if (move == -1) {
            point.y = point.y - 1;

        } else if (move == 2) {
            point.x = point.x - 1;

        } else {
            point.x = point.x + 1 ;
        }
    }



}
