package leetcode.hot_100.medium;

import leetcode.hot_100.first.medium.LongestConsecutive;
import org.junit.Test;

/**
 * @author gusixue
 * @description
 * @date 2023/6/14
 */
public class LongestConsecutiveTest {
    @Test
    public void testSolution() {
        LongestConsecutive longestConsecutive = new LongestConsecutive();

        assert 4 == longestConsecutive.solution(new int[]{100,4,200,1,3,2});
        assert 4 == longestConsecutive.solution2(new int[]{100,4,200,1,3,2});
        assert 4 == longestConsecutive.solution3(new int[]{100,4,200,1,3,2});
        assert 4 == longestConsecutive.solution4(new int[]{100,4,200,1,3,2});
        System.out.println();

        assert 9 == longestConsecutive.solution(new int[]{0,3,7,2,5,8,4,6,0,1});
        assert 9 == longestConsecutive.solution2(new int[]{0,3,7,2,5,8,4,6,0,1});
        assert 9 == longestConsecutive.solution3(new int[]{0,3,7,2,5,8,4,6,0,1});
        assert 9 == longestConsecutive.solution4(new int[]{0,3,7,2,5,8,4,6,0,1});
        System.out.println();

        assert 14 == longestConsecutive.solution(new int[]{0,1,0,2,3,4,5,6,7,1,6,2,3,4,5,6,7,8,9,11,12,13,27,10,26,25,23,24,15,17,16,19,18});
        assert 14 == longestConsecutive.solution2(new int[]{0,1,0,2,3,4,5,6,7,1,6,2,3,4,5,6,7,8,9,11,12,13,27,10,26,25,23,24,15,17,16,19,18});
        assert 14 == longestConsecutive.solution3(new int[]{0,1,0,2,3,4,5,6,7,1,6,2,3,4,5,6,7,8,9,11,12,13,27,10,26,25,23,24,15,17,16,19,18});
        assert 14 == longestConsecutive.solution4(new int[]{0,1,0,2,3,4,5,6,7,1,6,2,3,4,5,6,7,8,9,11,12,13,27,10,26,25,23,24,15,17,16,19,18});
    }


}
