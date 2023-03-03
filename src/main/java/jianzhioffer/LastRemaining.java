package jianzhioffer;

import utils.AlgorithmUtils;

/**
 * 首先,让n个小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后退出并且不再回到圈中,从他的下一个小朋友开始,
 * 继续0...m-1报数....这样下去....直到剩下最后一个小朋友,他是谁？
 */
public class LastRemaining {

    public static void main(String[] args) {
        while(true) {
            int array[] = AlgorithmUtils.systemInArray(2);
            System.out.println(solution(array[0], array[1]));
        }
    }

    /**
     * 约瑟夫环的问题，可以使用循环链表（LinkedList）时间复杂度为O(nm)，但是最好可以使用数学分析使用dp
     * 因为约瑟夫环第一次出圈后、第二次是从结果后一位开始的长度为n-1的约瑟夫环，因此可以使用递推分析
     * 第一次易得(m-1)%n（定为k）出圈，然后再次从m%n开始：k+1、k+2...（n-2+k+1）%n
     * 设f(n)为下一次结果、f(n-1)为上一次，此时:f(n)=(k+1+m-1)%n=(f(n-1)+m)%n
     */
    private static int solution(int n, int m) {
        if(n<=0 || m<=0){
            return -1;
        }
        int res = 0;
        for(int i=1;i<=n;i++){
            res = (res+m)%i;
        }
        return res;
    }
}
