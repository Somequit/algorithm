package leetcode.contest.contest_370;


/**
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int n = AlgorithmUtils.systemInNumberInt();
//            int[] nums = AlgorithmUtils.systemInArray();
//            List<Integer> list = AlgorithmUtils.systemInList();

//            int res = contest.solution(n, nums, list);
//            System.out.println(res);
        }

    }

    /**
     * @return
     */
    private int solution(int n, int[][] edges) {
        int res = -1;
        int[] in = new int[n];
        for (int i = 0; i < edges.length; i++) {
            in[edges[i][1]]++;
        }
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                if (res == -1) {
                    res = i;
                } else {
                    res = -1;
                    break;
                }
            }
        }

        return res;
    }


}
