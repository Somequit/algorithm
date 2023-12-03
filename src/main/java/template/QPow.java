package template;

/**
 * @author gusixue
 * @description 快速幂模板
 * @date 2023/11/12
 */
public class QPow {

    private long qPow(long value, long pow, long mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                res *= value;
                res %= mod;
            }

            value *= value;
            value %= mod;
            pow >>= 1;
        }
        return res;
    }
}
