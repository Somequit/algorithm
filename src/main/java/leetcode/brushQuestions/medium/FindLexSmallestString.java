package leetcode.brushQuestions.medium;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author gusixue
 * @description 1625. 执行操作后字典序最小的字符串
 * @date 2025/10/19 3:17 上午
 */
public class FindLexSmallestString {

    /**
     * 题目意思是：累加：所有奇数下标值统一 (+a)%10；转换：起点下标转为 (-b+n)%n；
     * 同时由于字符串一定是偶数个
     *     因此如果 b 为偶数，则不论如何转换每个字符奇偶性不变，因此累加只需要统一累加奇数下标；
     *     否则轮转奇数次字符奇偶性会改变，奇数下标 与 偶数下标就可以分别统一累加；
     *     又因为 奇数下标 与 偶数下标 相互不影响，因此可以分别累加计算最小字典序
     * 解决方法：
     *     首先一直轮转，直到转回原始字符串
     *     轮转过程中，将原始字符串轮转后，累加奇数下标 与 偶数下标（如可以），找到最小字典序
     *     最后找到全部最小字典序，因为轮转仅可能使偶数下标变成技术下标然后累加，其余两者不相干
     *
     */
    public String findLexSmallestString(String s, int a, int b) {
        TreeSet<String> sResTreeSet = new TreeSet<>();

        int n = s.length();
        // 轮转
        int beginIndex = 0;
        do {
            StringBuilder sb = new StringBuilder(s.substring(beginIndex)).append(s.substring(0, beginIndex));

            StringBuilder minSB = new StringBuilder(sb);
//            System.out.println("sb: " + sb);
            // 累加，仅转奇数位
            for (int i = a; i > 0; i = (i + a) % 10) {
                for (int j = 1; j < n; j += 2) {
                    int curNum = sb.charAt(j) - '0';
                    int curMinNum = minSB.charAt(j) - '0';

                    if ((curNum + i) % 10 < curMinNum) {

                        for (int k = 1; k < n; k += 2) {
                            minSB.setCharAt(k, (char) (((sb.charAt(k) - '0' + i) % 10) + '0'));
                        }
//                        System.out.println(i + ": minSB: " + minSB);
                        break;

                    } else if ((curNum + i) % 10 > curMinNum) {
                        break;
                    }
                }
            }

            sb = new StringBuilder(minSB);
            // 累加，仅转偶数位
            if (b % 2 == 1) {
                for (int i = a; i > 0; i = (i + a) % 10) {
                    for (int j = 0; j < n; j += 2) {
                        int curNum = sb.charAt(j) - '0';
                        int curMinNum = minSB.charAt(j) - '0';

                        if ((curNum + i) % 10 < curMinNum) {

                            for (int k = 0; k < n; k += 2) {
                                minSB.setCharAt(k, (char) (((sb.charAt(k) - '0' + i) % 10) + '0'));
                            }
                            System.out.println(i + ": minSB: " + minSB);
                            break;

                        } else if ((curNum + i) % 10 > curMinNum) {
                            break;
                        }
                    }
                }
            }

            sResTreeSet.add(minSB.toString());

            beginIndex = (beginIndex - b + n) % n;
        } while ((beginIndex != 0));


        return sResTreeSet.first();
    }
}
