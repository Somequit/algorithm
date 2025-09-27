package leetcode.brushQuestions.easy;

/**
 * @author gusixue
 * @description 812. 最大三角形面积
 * @date 2025/9/27 2:50 下午
 */
public class LargestTriangleArea {

    /**
     * 枚举任意不同三点，通过平方差公式求出三边长，在通过海伦公式求出面积
     * 如果无法构成三角形（形成线段）则面积为 0
     * 但是 double 可能导致精度问题从而变成负数然后求平方根，因此需要剔除这种情况；也可以判断如果是负数开更就直接返回 0.0
     * @param points
     * @return
     */
    public double largestTriangleArea(int[][] points) {
        // 枚举任意不同三点
        int len = points.length;

        double res = 0;
        for (int i = 0; i < len-2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                // 通过平方差公式求出边长
                double sideA = getDistByPoint(points[i], points[j]);

                for (int k = j + 1; k < len; k++) {
                    // 通过平方差公式求出边长
                    double sideB = getDistByPoint(points[i], points[k]);
                    double sideC = getDistByPoint(points[j], points[k]);

//                    // 两最短边之和不大于第三边不构成三角形，也可以判断如果是负数开更就直接返回 0.0
//                    double sideMax = Math.max(sideA, Math.max(sideB, sideC));
//                    if (Double.compare(sideA + sideB + sideC - sideMax - sideMax, 1e-5) <= 0) {
//                        continue;
//                    }

                    res = Math.max(res, getTriangleArea(sideA, sideB, sideC));
                }
            }
        }

        return res;
    }

    /**
     * 通过海伦公式求出面积
     * @param sideA
     * @param sideB
     * @param sideC
     * @return
     */
    private double getTriangleArea(double sideA, double sideB, double sideC) {
        // 半周长
        double p = (sideA + sideB + sideC) / 2.0;
        Double res =  Math.sqrt(p * (p-sideA) * (p-sideB) * (p-sideC));
        // 也可以判断如果是负数开更就直接返回 0.0
        if(Double.isNaN(res)) {
            res = 0.0;
        }
//        System.out.println(sideA + ":" + sideB + sideC + ":" + p + ":" + res);
        return res;
    }

    /**
     * 通过平方差公式求出边长
     * @return
     */
    private double getDistByPoint(int[] pointA, int[] pointB) {
        Double res = Math.sqrt(Math.pow((pointA[0] - pointB[0]), 2) + Math.pow((pointA[1] - pointB[1]), 2));
        // 也可以判断如果是负数开更就直接返回 0.0
        if(Double.isNaN(res)) {
            res = 0.0;
        }
//        System.out.println(pointA[0] + ":" + pointA[1] + ":" + pointB[0] + ":" + pointB[1] + ":" + res);

        return res;
    }
}
