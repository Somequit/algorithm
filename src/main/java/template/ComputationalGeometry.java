package template;

/**
 * @author gusixue
 * @description 计算几何
 * @date 2025/9/27 3:35 下午
 */
public class ComputationalGeometry {

    /**
     * 通过平方差公式求出边长
     * @return
     */
    private double getDistByPoint(int[] pointA, int[] pointB) {
        double res = Math.sqrt(Math.pow((pointA[0] - pointB[0]), 2) + Math.pow((pointA[1] - pointB[1]), 2));
        // 也可以判断如果是负数开更就直接返回 0.0
        if(Double.isNaN(res)) {
            res = 0.0;
        }

        return res;
    }


    /**
     * 通过海伦-秦九昭公式求出面积
     * @param sideA
     * @param sideB
     * @param sideC
     * @return
     */
    private double getTriangleArea(double sideA, double sideB, double sideC) {
        // 半周长
        double p = (sideA + sideB + sideC) / 2.0;
        double res =  Math.sqrt(p * (p-sideA) * (p-sideB) * (p-sideC));
        // 也可以判断如果是负数开更就直接返回 0.0
        if(Double.isNaN(res)) {
            res = 0.0;
        }

        return res;
    }

    /**
     * 通过向量叉积求出面积
     */
    private double getTriangleArea2(int[] point1, int[] point2, int[] point3) {
        // p1->p2
        int x1 = point2[0] - point1[0];
        int y1 = point2[1] - point1[1];
        // p1->p3
        int x2 = point3[0] - point1[0];
        int y2 = point3[1] - point1[1];

        // 叉积的计算公式
        return Math.abs(x1 * y2 - x2 * y1) / 2.0;
    }


}
