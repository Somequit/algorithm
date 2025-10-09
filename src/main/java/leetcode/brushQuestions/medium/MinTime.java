package leetcode.brushQuestions.medium;

/**
 * @author gusixue
 * @description 3494. 酿造药水需要的最少总时间
 * @date 2025/10/9 1:57 下午
 */
public class MinTime {

    /**
     * 结束时间可找到每个药水的开始时间，然后加上最后一个药水的总制作时间决定
     * 对于 mana[i](i>1) 来说，要保证每个巫师不会同时要求处理俩药水，
     * 开始时间需要加上 dealTime，其等于 第一个人i-1做完，第二个人i-1做完 减去 第一个人i做完...最后一个人i-1做完 减去 倒数第二个人i做完，中的最大时间开始
     * 时间复杂度：O（m*n）,空间复杂度：O（1）
     */
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        long res = 0;

        for (int i = 1; i < m; i++) {
            long prevTime = (long) skill[0] * mana[i - 1];
            long curTime = 0;
            long dealTime = prevTime - curTime;
            for (int j = 1; j < n; j++) {
                prevTime += (long) skill[j] * mana[i - 1];
                curTime += (long) skill[j - 1] * mana[i];
                dealTime = Math.max(dealTime, prevTime - curTime);

            }
            res += dealTime;

//            System.out.println(res);
        }

        /**
         * 最后一个药水的总制作时间
         */
        for (int j = 0; j < n; j++) {
            res += (long) skill[j] * mana[m - 1];
        }

        return res;
    }
}
