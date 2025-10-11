package leetcode.brushQuestions.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gusixue
 * @description 3186. 施咒的最大总伤害
 * @date 2025/10/11 10:06 上午
 */
public class MaximumTotalDamage {

    /**
     * 线性 dp + TreeMap 离散化 + 仅考虑 -1与-2
     * 仅考虑 -1与-2：例如 仅考虑 power[i]=5 使用后就不能使用 power[i]=3与power[i]=4 的魔法，之后使用了 power[i]=6与power[i]=7 的魔法也不会使用 power[i]=5
     */
    public long maximumTotalDamage(int[] power) {
        // 使用小于等于当前值的魔法值后-最大的伤害
        // PS:如果存储的是：使用了当前的魔法值为 key，则还需要求得前缀最大值才可以，否则如：3,3,4,7 结果会错误输出 4+7=11、正确结果为 3+3+7=13
        TreeMap<Integer, Long> useMinTreeMap = new TreeMap<>();

        // 相同魔法值用了一个不影响另一个，因此相同魔法值则全部使用，当前魔法值-个数
        TreeMap<Integer, Integer> powerCountTreeMap = new TreeMap<>();
        Arrays.stream(power).forEach(p -> powerCountTreeMap.merge(p, 1, Integer::sum));
//        System.out.println(powerCountTreeMap);

        long res = 0;
        // 添加一个最小值，避免 higherKey 的空指针
        useMinTreeMap.put(-2, 0L);
        for (Map.Entry<Integer, Integer> entry : powerCountTreeMap.entrySet()) {
            int curPower = entry.getKey();
            int curPowerCount = entry.getValue();

            // 选择使用 curPower 则小于 curPower-2 的才可以加入其中
            useMinTreeMap.put(curPower, useMinTreeMap.getOrDefault(useMinTreeMap.lowerKey(curPower - 2), 0L) + (long) curPower * curPowerCount);

            if (useMinTreeMap.get(curPower) < res) {
                useMinTreeMap.put(curPower, res);

            } else {
                res = useMinTreeMap.get(curPower);
            }
        }

        return res;
    }
}
