package template;

import utils.AlgorithmUtils;

/**
 * 数据结构：树状数组
 * 初始化维护一个bit树数组：O(n*logn)，也可以先求出每个前缀和、再计算bit数组：O(n)
 * 支持单点更新O(logn) 以及 区间查询O(logn)
 */
public class BinaryIndexedTree {


    /**
     * if(x<=0){
     * returen}
     * x|1 == 1
     * return;
     * x|1 != 1
     * now+=arr[i-2^循环];
     * x>>=1;
     *
     * bit[i]=now;
     *
     *
     * 1	0001	1
     * 2	0010	2+1
     * 3	0011		3
     * 4	0100	4+3+2+1
     * 5	0101	5
     * 6	0110		6+5
     * 7	0111		7
     * 8	1000	8+7+6+5+4+3+2+1
     * 9	1001	9
     * 10 1010	10+9
     *
     *
     * 8	1000
     * [1-8]
     *
     * 7	0111
     * 6	0110
     * 4	0100
     * [1-7]
     *
     * while(i>0)
     * total += bit[i]
     * i-=(bit(i))
     *
     * function bit(int x){
     * return x & (-x);
     * }
     * @param args
     */
    public static void main(String[] args) {
        int[] array = AlgorithmUtils.systemInArray();
        // 获取bit数组

        // 循环进行更新或者查询
        while(true){
            System.out.println("0代表更新x位为y、1代表查询[x,y]的和:");
            long operation = AlgorithmUtils.systemInNumber();
            long x = AlgorithmUtils.systemInNumber();
            long y = AlgorithmUtils.systemInNumber();
            if(operation == 0){
                // 单点更新

            } else if(operation == 1){
                // 区间查询

            } else{
                System.out.println("输入异常！");
            }
        }
    }
}
