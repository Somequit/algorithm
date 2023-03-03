package interview;

import java.util.HashSet;
import java.util.Set;

/**
 * 阿拉伯数字转化为大写汉语数字
 */
public class ChineseNumberByMine {

    private static final String[] INDEX_NUMBER_MAPPING = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    private static final String[] UNIT_NUMBER_MAPPING = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};

    private static final Set<Integer> MUST_UNIT_NUMBER = new HashSet<>();
    static {
        MUST_UNIT_NUMBER.add(8);
        MUST_UNIT_NUMBER.add(4);
    }

    public static void main(String[] args) {
//        while (true) {
//            long number = AlgorithmUtils.systemInNumber();
//            String result = numberExchangeUpper(number);
//            System.out.println(result);
//        }
        System.out.println("零".equals(numberExchangeUpper(0)) + System.lineSeparator());
        System.out.println("一".equals(numberExchangeUpper(1)) + System.lineSeparator());
        System.out.println("负一".equals(numberExchangeUpper(-1)) + System.lineSeparator());
        System.out.println("五千零二十亿零三百零五万零三".equals(numberExchangeUpper(502003050003L)) + System.lineSeparator());
        System.out.println("负一千零一十亿一千零一十万一千零一十".equals(numberExchangeUpper(-101010101010L)) + System.lineSeparator());
        System.out.println("一千亿".equals(numberExchangeUpper(100000000000L)) + System.lineSeparator());
        System.out.println("负一千一百一十一亿一千一百一十一万一千一百一十一".equals(numberExchangeUpper(-111111111111L)) + System.lineSeparator());
        System.out.println("一千一百一十一亿一千一百一十一万一千一百一十".equals(numberExchangeUpper(111111111110L)) + System.lineSeparator());
        System.out.println("一千一百一十一亿一千一百一十一万一千零一十一".equals(numberExchangeUpper(111111111011L)) + System.lineSeparator());
        System.out.println("一千一百一十一亿一千一百一十一万零一十一".equals(numberExchangeUpper(111111110011L)) + System.lineSeparator());
        System.out.println("一千一百一十一亿一千一百一十万零一十一".equals(numberExchangeUpper(111111100011L)) + System.lineSeparator());
        System.out.println("一千一百一十一亿零一十万零一十一".equals(numberExchangeUpper(111100100011L)) + System.lineSeparator());
        System.out.println("一千零一亿零一十万零一十一".equals(numberExchangeUpper(100100100011L)) + System.lineSeparator());
        System.out.println("一千万一千零一十一".equals(numberExchangeUpper(10001011L)) + System.lineSeparator());
    }

    /**
     * 阿拉伯数字转化为大写汉语数字
     * 数字最多到千亿
     * @param number
     * @return
     */
    private static String numberExchangeUpper(long number) {
        StringBuilder result = new StringBuilder();

        boolean negativeFlag = false;
        if (number == 0) {
//            System.out.println(INDEX_NUMBER_MAPPING[0]);
            return INDEX_NUMBER_MAPPING[0];
        } else if (number < 0) {
            negativeFlag = true;
        }

        String numberStr = String.valueOf(Math.abs(number));
        if (numberStr.length() > UNIT_NUMBER_MAPPING.length) {
            throw new RuntimeException("the number out of range！");
        }

        boolean addZreoFlag = false;
        boolean mustUnitFlag = false;
        for (int i=0; i<numberStr.length(); i++) {
            int numBit = numberStr.charAt(i) - '0';
            if (numBit == 0) {
                if (mustUnitFlag && MUST_UNIT_NUMBER.contains(numberStr.length() - i - 1)) {
                    result.append(UNIT_NUMBER_MAPPING[numberStr.length() - i - 1]);
                    addZreoFlag = false;
                    mustUnitFlag = false;
                } else {
                    addZreoFlag = true;
                }
            } else {
                if (addZreoFlag) {
                    result.append(INDEX_NUMBER_MAPPING[0]);
                }
                result.append(INDEX_NUMBER_MAPPING[numBit]);
                result.append(UNIT_NUMBER_MAPPING[numberStr.length() - i - 1]);
                addZreoFlag = false;
                mustUnitFlag = true;
            }
        }

        /**
         * 判负数
         */
        if (negativeFlag) {
            result.insert(0, "负");
        }

//        System.out.println(result.toString());
        return result.toString();
    }
}
