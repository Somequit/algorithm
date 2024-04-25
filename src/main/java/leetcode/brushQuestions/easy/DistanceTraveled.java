package leetcode.brushQuestions.easy;

/**
 * @author gusixue
 * @description 2739. 总行驶距离
 * @date 2024/4/25
 */
public class DistanceTraveled {

    /**
     * mainTank 消耗 5 升可以换 additionalTank 补充 1 升（如有），
     * 可以看做 mainTank 消耗 4 升等于 additionalTank 消耗的油量（如有），因此为 min(mainTank/4, additionalTank),
     * 但是如果在最后一次中 mainTank 仅消耗 4 升则不会使 additionalTank 消耗，因此需要 (mainTank-1)/4，
     * 最后结果就是 (mainTank+min((mainTank-1)/4, additionalTank))*10
     */
    public int distanceTraveled(int mainTank, int additionalTank) {
        return (mainTank + Math.min((mainTank - 1) / 4, additionalTank)) * 10;
    }
}
