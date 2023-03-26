package template;

/**
 * 浮点数计算，控制误差
 * @author gusixue
 * @date 2023/2/5
 */
public class DoubleErrorController {

    public static void main(String[] args) {
        doubleCalculate(1.0 - 0.9, 0.9 - 0.8);
        doubleCalculate(0.8 - 0.7, 0.7 - 0.6);
        doubleCalculate(0.2 - 0.1, 0.1 - 0);
        doubleCalculate(0.3 - 0.1, 0.1 - 0);
    }

    private static void doubleCalculate(double g, double h) {
        System.out.println(g);
        System.out.println(h);
        double diff = 1e-6;
        if (Math.abs(g - h) < diff) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
