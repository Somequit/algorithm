package jianzhioffer;

import utils.AlgorithmUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵：
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
 */
public class ClockwisePrintRectangle {
    public static void main(String[] args) {
        int[][] array = AlgorithmUtils.systemInTwoArray();
        List<Integer> result = printMatrix(array);
        System.out.println(result);
    }

    /**
     * 使用类似bfs的方式，从(0，0)开始向（右、下、左、上）的方式一圈一圈移动，总共n*m次有效移动
     * 每次移动判断上次移动的方式只能和上次移动一致或者下一种方式移动，然后需要判断移动后的位置是否正确：
     * 每一圈判断是否移动出当前圈边界，最后向上移动的时候不能移动到起点、即x=y的点
     */
    private static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> resultList = new ArrayList<>();
        Point nowPoint = new Point(0, 0);
        Point nextPoint;
        // 0-右 1-下 2-左 3-上
        int direction = 0;
        // 行数 * 列数
        int allLen = matrix.length * matrix[0].length;
        Point arrPoint = new Point(matrix.length, matrix[0].length);
        // 已经转过几圈
        int rounds = 0;
        for(int i=0;i<allLen;i++){
            resultList.add(matrix[nowPoint.x][nowPoint.y]);
            nextPoint = getNextPoint(nowPoint, direction);
            // 判断点是否出当前圈边界
            if(checkPointTrue(nextPoint, arrPoint, rounds, direction)){
                if(direction == 3){
                    rounds++;
                }
                // 出圈后使用下一种方式移动
                direction = (direction+1)%4;
                nextPoint = getNextPoint(nowPoint, direction);
            }
            nowPoint.x = nextPoint.x;
            nowPoint.y = nextPoint.y;
        }
        return resultList;
    }

    /**
     * 判断点是否出当前圈边界
     */
    private static boolean checkPointTrue(Point point, Point arrPoint, int rounds, int direction) {
        if(point == null){
            return true;
        }
        if(point.x < 0+rounds || point.x >= arrPoint.x-rounds || point.y < 0+rounds || point.y >= arrPoint.y-rounds){
            return true;
        }
        if(direction == 3 && point.x == point.y){
            return true;
        }
        return false;
    }

    private static Point getNextPoint(Point nowPoint, int direction) {
        Point result = new Point(0, 0);
        if(nowPoint == null){
            return result;
        }
        // 0-右 1-下 2-左 3-上
        if(direction == 0){
            result.x = nowPoint.x;
            result.y = nowPoint.y+1;
        } else if(direction == 1){
            result.x = nowPoint.x+1;
            result.y = nowPoint.y;
        } else if(direction == 2){
            result.x = nowPoint.x;
            result.y = nowPoint.y-1;
        } else{
            result.x = nowPoint.x-1;
            result.y = nowPoint.y;
        }
        return result;
    }
}
