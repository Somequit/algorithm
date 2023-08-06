package leetcode.contest;

import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.*;

/**
 * @author gusixue
 * @date 2023/3/19
 */
public class ContestTest {

    public static void main(String[] args) {
        ContestTest contestTest = new ContestTest();
        int[][] items = AlgorithmUtils.systemInTwoArray();
        int k = AlgorithmUtils.systemInNumberInt();

        long res = contestTest.findMaximumElegance(items, k);
        System.out.println(res);
    }

    public long findMaximumElegance(int[][] items, int k) {
        // 把利润从大到小排序
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        long ans = 0, totalProfit = 0;
        HashSet<Integer> vis = new HashSet<Integer>();
        ArrayDeque<Integer> duplicate = new ArrayDeque<Integer>(); // 重复类别的利润
        for (int i = 0; i < items.length; i++) {
            int profit = items[i][0], category = items[i][1];
            if (i < k) {
                totalProfit += profit;
                if (!vis.add(category)) // 重复类别
                    duplicate.push(profit);
            } else if (!duplicate.isEmpty() && vis.add(category)) {
                totalProfit += profit - duplicate.pop(); // 选一个重复类别中的最小利润替换
            } // else：比前面的利润小，而且类别还重复了，选它只会让 totalProfit 变小，vis.size() 不变，优雅度不会变大
            ans = Math.max(ans, totalProfit + (long) vis.size() * vis.size()); // 注意 1e5*1e5 会溢出
        }
        return ans;
    }

}
