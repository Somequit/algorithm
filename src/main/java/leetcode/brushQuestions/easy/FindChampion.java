package leetcode.brushQuestions.easy;

/**
 * @author gusixue
 * @description 2923. 找到冠军 I
 * @date 2024/4/12
 */
public class FindChampion {
    public int findChampion(int[][] grid) {
        for(int j=0; j<grid[0].length; j++) {
            int count = 0;
            for (int i=0; i<grid.length; i++) {
                count += grid[i][j];
            }
            if (count == 0) {
                return j;
            }
        }
        return -1;
    }
}
