package jianzhioffer;

/**
 *  汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 *  对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循
 *  环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 *  注意：一个码点包含两个代码单元的问题
 */
public class LeftRotateString {

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()){
//            String str = sc.nextLine();
//            int leftBit = sc.nextInt();
//            String result = circulateLeftMove(str, leftBit);
////            System.out.println(result);
//            String result2 = stringReversal(str, leftBit);
//            System.out.println(result2);
//            sc.nextLine();
//        }
//    }
//
//    /**
//     * 可以使用三次字符串翻转
//     */
//    private static String stringReversal(String str, int leftBit) {
//        if(AlgorithmUtils.strIsEmpty(str)){
//            return "";
//        }
//
//        int[] strCodePoint = str.codePoints().toArray();
//        int length = strCodePoint.length;
//        // 移位减去长度倍数
//        leftBit %= length;
//        if(leftBit <= 0){
//            return str;
//        }
//        // 翻转 [0,leftBit)、[leftBit-length)、[0-length)
//        reversal(strCodePoint, 0, leftBit);
//        reversal(strCodePoint, leftBit, length);
//        reversal(strCodePoint, 0, length);
//        return new String(strCodePoint, 0, strCodePoint.length);
//    }
//
//    /**
//     * 翻转字符串 [start,end)
//     */
//    private static void reversal(int[] strCodePoint, int start, int end) {
//        // string为不可变对象、每次添加都会重新生成新的字符串 使用StringBuffer线程安全的可变对象增加效率
//        end--;
//        while (start < end) {
//            int temp = strCodePoint[start];
//            strCodePoint[start] = strCodePoint[end];
//            strCodePoint[end] = temp;
//            start++;
//            end--;
//        }
//    }
//
//    private static String circulateLeftMove(String str, int leftBit) {
//        if(AlgorithmUtils.strIsEmpty(str)){
//            return "";
//        }
//
//        int[] strCodePoints = str.codePoints().toArray();
//        int length = strCodePoints.length;
//        // 移位减去长度倍数
//        leftBit %= length;
//        if(leftBit <= 0){
//            return str;
//        }
//        // string为不可变对象、每次添加都会重新生成新的字符串 使用StringBuffer线程安全的可变对象增加效率
//        StringBuffer result = new StringBuffer();
//        for (int i = 0; i < strCodePoints.length; i++) {
//            result.append(new String(strCodePoints, (i+leftBit)%length, 1));
//        }
//        return result.toString();
//    }
}
