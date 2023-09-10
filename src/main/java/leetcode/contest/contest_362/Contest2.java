package leetcode.contest.contest_362;


/**
 *
 */
public class Contest2 {

    public static void main(String[] args) {
        Contest2 contest = new Contest2();

        while (true) {
//            int[] nums = AlgorithmUtils.systemInArray();

//            int res = contest.solution(nums);
//            System.out.println(res);
        }

    }

    public boolean solution(int sx, int sy, int fx, int fy, int t) {
        int xT = Math.abs(sx - fx);
        int yT = Math.abs(sy - fy);
        int minT = Math.max(xT, yT);
        if (t < minT) {
            return false;
        }

        if (xT > 0 && yT > 0) {
            return true;

        } else if (xT == 0 && yT == 0) {
            if (t != 1) {
                return true;
            }

        } else {
            return true;
        }
        return false;
    }


}
