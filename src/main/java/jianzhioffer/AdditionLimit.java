package jianzhioffer;

/**
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）
 */
public class AdditionLimit {

    public static void main(String[] args) {
        Sum_Solution(1);
    }

    /**
     * 使用递归替代循环，使用&&短路替代判断
     */
    public static int Sum_Solution(int n) {
        int ans = n;
        boolean flag = (ans>0) && ((ans += Sum_Solution(n - 1))>0);
        return ans;
    }

}
