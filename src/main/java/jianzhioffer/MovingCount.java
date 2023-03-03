package jianzhioffer;
import utils.AlgorithmUtils;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
 * 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），
 * 因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class MovingCount {

    public static void main(String[] args) {
        while(true){
            int[] array = AlgorithmUtils.systemInArray(3);
            int result = movingCount(array[0], array[1], array[2]);
            System.out.println(result);
        }
    }

    /**
     * 从（0，0）开始、上下左右、向（rows-1，cols-1）移动，使用bfs进行处理
     * @param threshold 门槛k
     * @param rows 行
     * @param clos 列
     * @return
     */
    private static int movingCount(int threshold, int rows, int clos) {
        int result = 0;
        if(threshold < 0 ){
            return 0;
        }
        // bfs队列存数据
        Queue<Point> bfsQueue = new ConcurrentLinkedQueue<>();
        bfsQueue.add(new Point(0, 0));
        // 记录已经走过的路径
        boolean[][] record = new boolean[rows][clos];
        record[0][0] = true;
        while(!bfsQueue.isEmpty()){
            result++;
            Point pairNow = bfsQueue.poll();
            // 上下左右移动
            Point pairTempUp = new Point(pairNow.x - 1, pairNow.y);
            if(checkPosition(threshold, rows, clos, pairTempUp, record)){
                bfsQueue.add(pairTempUp);
            }
            Point pairTempDown = new Point(pairNow.x+1, pairNow.y);
            if(checkPosition(threshold, rows, clos, pairTempDown, record)){
                bfsQueue.add(pairTempDown);
            }
            Point pairTempLeft = new Point(pairNow.x, pairNow.y-1);
            if(checkPosition(threshold, rows, clos, pairTempLeft, record)){
                bfsQueue.add(pairTempLeft);
            }
            Point pairTempRight = new Point(pairNow.x, pairNow.y+1);
            if(checkPosition(threshold, rows, clos, pairTempRight, record)){
                bfsQueue.add(pairTempRight);
            }
        }
        return result;
    }

    /**
     * 判断该位置是否存在且可以走
     */
    private static boolean checkPosition(int threshold,int rows,int clos
            , Point pairTemp, boolean[][] record) {
        if(pairTemp == null || pairTemp.x < 0 || pairTemp.y < 0
                || pairTemp.x >= rows || pairTemp.y >= clos
                || record[pairTemp.x][pairTemp.y]){
            return false;
        }
        int countSum = getNumCountSum(pairTemp.x) + getNumCountSum(pairTemp.y);
        if(countSum > threshold){
            return false;
        }
        record[pairTemp.x][pairTemp.y] = true;
        return true;
    }

    /**
     * 各个位之和
     */
    private static int getNumCountSum(int num) {
        int result = 0;
        while(num != 0){
            result += num%10;
            num /= 10;
        }
        return result;
    }
}
