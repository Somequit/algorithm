package leetcode.brushQuestions.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author gusixue
 * @description 1611. 使整数变为 0 的最少操作次数
 * @date 2025/11/8 8:13 上午
 */
public class MinimumOneBitOperations {

    public static void main(String[] args) {
        MinimumOneBitOperations mobo = new MinimumOneBitOperations();
        System.out.println(mobo.minimumOneBitOperationsBFS(0));
        System.out.println(mobo.minimumOneBitOperationsBFS(1));
        System.out.println(mobo.minimumOneBitOperationsBFS(2));
        System.out.println(mobo.minimumOneBitOperationsBFS(4));
        System.out.println(mobo.minimumOneBitOperationsBFS(8));
        System.out.println(mobo.minimumOneBitOperationsBFS(16));
        System.out.println(mobo.minimumOneBitOperationsBFS(32));
        System.out.println(mobo.minimumOneBitOperationsBFS(64));
        System.out.println(mobo.minimumOneBitOperationsBFS(128));
        System.out.println(mobo.minimumOneBitOperationsBFS(256));
    }

    /**
     * BFS 打表
     */
    public int minimumOneBitOperationsBFS(int n) {
        boolean[] vis = new boolean[1 << 20];
        vis[n] = true;

        Queue<int[]> queueNumCnt = new LinkedList<>();
        queueNumCnt.offer(new int[]{n, 0});

        while (!queueNumCnt.isEmpty()) {
            int[] curQueue = queueNumCnt.poll();
            if (curQueue[0] == 0) {
                return curQueue[1];
            }

            int nextNum = curQueue[0] ^ 1;
            if (!vis[nextNum]) {
                vis[nextNum] = true;
                queueNumCnt.offer(new int[]{nextNum, curQueue[1] + 1});
            }

            if (lowbit(curQueue[0]) == curQueue[0]) {
                continue;
            }
            nextNum = curQueue[0] ^ (lowbit(curQueue[0]) << 1);
            if (!vis[nextNum]) {
                vis[nextNum] = true;
                queueNumCnt.offer(new int[]{nextNum, curQueue[1] + 1});
            }

        }

        return -1;
    }

    private int lowbit(int num) {
        return num & (-num);
    }

    /**
     * 只有两种操作且可逆，翻转第 0 位以及翻转最右侧 1 的左侧，我们的目标是变成 0，
     * 易得首要目标是翻转最高位 1 为 0，此时仅次高位为 1，接着翻转次高位，仅第三高位为 1...；
     *     例如：10-11-01-00，100-101-111-110-010...
     *     因此每个数可看做是从该数的 仅最高位1 翻转而来，
     * 该数翻转结果 = 该数仅最高位1 - （该数仅最高位1 翻转到 该数）
     *     （该数仅最高位1 翻转到 该数） = 均删除最高位翻转
     *     此时可递归
     * 接着打表/模拟可知，仅 i 位为 1 翻转次数 = 2^(i+1)-1
     * 最后假设从高到低有 i1,i2...ik 位为1，结果 = (2^(i1+1)-1) - (2^(i2+1)-1) + (2^(i3+1)-1)...
     * 时间复杂度：O（logn）,空间复杂度：O（1）
     */
    public int minimumOneBitOperations(int n) {
        int res = 0;
        boolean addFlag = true;
        while (n > 0) {
            if (addFlag) {
                res += (Integer.highestOneBit(n) << 1) - 1;

            } else {
                res -= (Integer.highestOneBit(n) << 1) - 1;
            }

            n -= Integer.highestOneBit(n);

            addFlag = !addFlag;
        }

        return res;
    }


}
