package template;

/**
 * @author gusixue
 * @description
 * @date 2023/10/31
 */
public class Algorithm {

    public static void main(String[] args) {
        // 字符串最少 6 位，不够前面补 0
        stringBeforeAdd("1567", 6, "0");
        stringBeforeAdd("1234567", 6, "0");
    }

    private static void stringBeforeAdd(String str, int len, String fill) {
        System.out.println(String.format("%" + len + "s", str).replace(" ", fill));
    }
}
