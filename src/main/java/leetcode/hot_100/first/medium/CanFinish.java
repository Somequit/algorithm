package leetcode.hot_100.first.medium;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 * @date 2023/9/19
 */
public class CanFinish {

    public static void main(String[] args) {
        CanFinish canFinish = new CanFinish();
        while (true) {
            int numCourses = AlgorithmUtils.systemInNumberInt();
            int[][] prerequisites = AlgorithmUtils.systemInTwoArray();

            boolean res = canFinish.solution(numCourses, prerequisites);
            System.out.println(res);
        }
    }

    /** 可看做有 numCourses 个点，每次新建一条 b 指向 a 的有向边（先学习 b 在学习 a），最后看是否有环，有环代表此环需要循环等待对方作为前置条件，因此有环就不能完成、否则就能完成
     * 本质是 有向无环图，把一个 有向无环图 转成 线性的排序 就叫 拓扑排序：每次选择一个入度为 0 的点（依赖以完成）学习，然后删除它所有的边，接着找下一个入度为 0 的点，直到所有点选择完
     * BFS：使用领接表（哈希表）记录 u 指向 v，计算每个点的入度，接着将所有入度为 0 的点放入队列，每次取出队列元素删除它作为起点的边，然后将入度为 0 的终点加入队列，循环直到队列为空，
     * 当所有节点都被遍历一遍、代表无环，否则就证明有环
     * n 为点的数量、m 为边的数量，时间复杂度：O（n+m），空间复杂度：O（n+m）
     */
    private boolean solution(int numCourses, int[][] prerequisites) {
        // 判空
        if (prerequisites.length == 0) {
            return true;
        }

        // 使用哈希表建立领接表
        Map<Integer, List<Integer>> graphMap = new HashMap<>(numCourses << 1);

        // 计算每个点的入度
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];

            // 新建一条 b 指向 a 的有向边
            List<Integer> pointList = graphMap.get(b);
            if (pointList == null) {
                pointList = new ArrayList<>();
                graphMap.put(b, pointList);
            }
            pointList.add(a);
            inDegree[a]++;
        }
//        System.out.println(graphMap);
//        System.out.println(Arrays.toString(inDegree));

        // 遍历每个点，入度为 0 的所有点入队，并记录这些点已使用
        Queue<Integer> queue = new LinkedList<>();
        int visCount = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                visCount++;
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            // 队列头结点出队
            Integer u = queue.poll();

            // 出队后删除以它外起点的边（每个终点入度减一）
            List<Integer> pointList = graphMap.get(u);
            if (pointList == null) {
                continue;
            }
            for (Integer curV : pointList) {
                inDegree[curV]--;
            }

            // 将入度为 0 的终点全加入队列，并记录这些点已使用
            for (Integer curV : pointList) {
                if (inDegree[curV] == 0) {
                    visCount++;
                    queue.offer(curV);
                }
            }

            // 所有点均使用则直接退出证明无环
            if (visCount == numCourses) {
                break;
            }
        }

        return visCount == numCourses;
    }


}
