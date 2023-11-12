package leetcode.contest.contest_371;


import java.util.*;

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
    private List<String> solution(List<List<String>> access_times) {
        List<String> res = new ArrayList<>();

        HashMap<String, List<Integer>> timeMap = new HashMap<>();
        for (int i = 0; i < access_times.size(); i++) {
            String name = access_times.get(i).get(0);
            if (!timeMap.containsKey(name)) {
                timeMap.put(name, new ArrayList<>());
            }

            int timeStr = Integer.parseInt(access_times.get(i).get(1));
            int times = timeStr / 100 * 60 + timeStr % 100;

            timeMap.get(name).add(times);
        }

        for (Map.Entry<String, List<Integer>> timeEntry : timeMap.entrySet()) {
            List<Integer> timeList = timeEntry.getValue();
            Collections.sort(timeList);
//            System.out.println(timeList);

            for (int i = 0; i < timeList.size(); i++) {
                int count = 1;
                for (int j = i + 1; j < timeList.size(); j++) {
                    if (timeList.get(j) - timeList.get(i) < 60) {
                        count++;
                    }
                }
                if (count >= 3) {
                    res.add(timeEntry.getKey());
                    break;
                }
            }
        }

        return res;
    }


}
