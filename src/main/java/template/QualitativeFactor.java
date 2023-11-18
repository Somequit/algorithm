package template;

import java.util.*;

/**
 * @author gusixue
 * @description 质因数分解
 * @date 2023/11/18
 */
public class QualitativeFactor {

    // 用于求质因数的数组
    private static int NUM;
    // NUM 的质因数
    private static final Set<Integer> FACTOR_SET;

    /**
     * 时间复杂度：O（sqrt(U)），空间复杂度：O（U/logU）
     */
    static {
        NUM = 1561541;
        FACTOR_SET = new HashSet<>();
        for (int i = 2; i * i <= NUM; i++) {
            if (NUM % i == 0) {
                FACTOR_SET.add(i);
                // i 一定是质数
                while (NUM % i == 0) {
                    NUM /= i;
                }
            }
        }
        if (NUM > 1) {
            FACTOR_SET.add(NUM);
        }
    }
}
