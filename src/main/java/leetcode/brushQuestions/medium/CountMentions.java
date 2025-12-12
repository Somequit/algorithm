package leetcode.brushQuestions.medium;

import java.util.*;

/**
 * @author gusixue
 * @description 3433. 统计用户被提及情况
 * @date 2025/12/12 10:13 下午
 */
public class CountMentions {

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] res = new int[numberOfUsers];
        int[] onlineTime = new int[numberOfUsers];

        events.sort((e1, e2) -> {
            int t1 = Integer.parseInt(e1.get(1));
            int t2 = Integer.parseInt(e2.get(1));

            if (t1 == t2) {
                t1 = e1.get(0).equals("OFFLINE") ? 0 : 1;
                t2 = e2.get(0).equals("OFFLINE") ? 0 : 1;
            }

            return Integer.compare(t1, t2);
        });
//        System.out.println(events);

        for (int i = 0; i < events.size(); i++) {
            String eventMes = events.get(i).get(0);
            int time = Integer.parseInt(events.get(i).get(1));
            String eventIds = events.get(i).get(2);

            if (eventMes.equals("OFFLINE")) {
                onlineTime[Integer.parseInt(eventIds)] = time + 60;

            } else {
                if (eventIds.equals("ALL")) {
                    for (int j = 0; j < numberOfUsers; j++) {
                        res[j]++;
                    }

                } else if (eventIds.equals("HERE")) {
                    for (int j = 0; j < numberOfUsers; j++) {
                        if (onlineTime[j] <= time) {
                            res[j]++;
                        }
                    }

                } else {
                    for (String eventId : eventIds.split(" ")) {
                        res[Integer.parseInt(eventId.substring(2))]++;
                    }
                }
            }

        }

        return res;
    }
}
