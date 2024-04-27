package leetcode.brushQuestions.medium;


/**
 * @author gusixue
 * @description 1017. 负二进制转换
 * @date 2024/4/28
 */
public class BaseNeg2 {

    public static void main(String[] args) {
        BaseNeg2 baseNeg2 = new BaseNeg2();
//        for (int i = -10; i <= 10; i++) {
//            System.out.print(i + " : " + (i % (2)) + "\t");
//            System.out.println(i + " : " + (i % (-2)));
//
//            System.out.print(i + " : " + (i / (2)) + "\t");
//            System.out.println(i + " : " + (i / (-2)));
//        }

        for (int i = 0; i <= 10000; i++) {
//            if (!baseNeg2.baseAny(i, -3).equals(Integer.toString(i, 3))) {
//                System.out.println(i + " : " + Integer.toString(i, 3) + " : " + baseNeg2.baseAny(i, -3));
//            }

            System.out.println(baseNeg2.baseAny(i, -3));
        }


    }


    /**
     * 从低位到高位加一遍，直到大于等于 n，如果大于 n 则回来再减一遍
     */
    public String baseNeg2Temp(int n) {
        if (n == 0) {
            return "0";
        }

        StringBuilder resBuilder = new StringBuilder();

        // 从低位到高位加一遍，直到大于等于 n
        long value = 1;
        int index = 0;
        while (n > 0) {
            if ((index & 1) == 0) {
                resBuilder.append(1);
                n -= value;

            } else {
                resBuilder.append(0);
            }

            index++;
            value <<= 1;
        }

        // 大于 n 则回来再减一遍
        while (n < 0 && index > 0) {
            index--;
            value >>= 1;

            if (-n >= value) {
                n += value;

                if (resBuilder.charAt(index) == '1') {
                    resBuilder.setCharAt(index, '0');

                } else {
                    resBuilder.setCharAt(index, '1');
                }

            }
        }

        return resBuilder.reverse().toString();
    }


    /**
     * 模拟 2 进制的短除法，不过除的是 -2
     */
    public String baseNeg2Temp2(int n) {
        if (n == 0) {
            return "0";
        }

        StringBuilder resBuilder = new StringBuilder();

        while (n != 0) {
            // 对 Java 来整数说 %2 与 %-2 结果一致，负数 %2 结果为非正数
//            if (n % 2 == 0) {
            if (n % (-2) == 0) {
                resBuilder.append(0);

            } else {
                // 注意此处需要变成偶数，让 /-2 变成整除
                n -= 1;
                resBuilder.append(1);
            }

            n /= -2;
        }

        return resBuilder.reverse().toString();
    }


    public String baseNeg2(int n) {
        return baseAny(n, -2);
    }

    /**
     * 转化为任意进制，使用短除法
     */
    private String baseAny(int n, int base) {
        if (n == 0) {
            return "0";
        }

        StringBuilder resBuilder = new StringBuilder();

        while (n != 0) {
            int nMod = n % base;

            // 保证找到的余数范围在 [0, abs(base)-1] 之间
            if (nMod < 0) {
                nMod -= base;
            }

            if (nMod == 0) {
                resBuilder.append(0);

            } else{
                n -= nMod;
                if (nMod >= 10) {
                    resBuilder.append((char)('a' + nMod - 10));

                } else {
                    resBuilder.append(nMod);
                }
            }

            n /= base;
        }

        return resBuilder.reverse().toString();
    }
}
