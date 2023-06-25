package leetcode.contest.contest_351;

import utils.AlgorithmUtils;

import java.util.*;


/**
 * @author gusixue
 * @date 2023/5/14
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            int[] positions = AlgorithmUtils.systemInArray();
            int[] healths = AlgorithmUtils.systemInArray();
            String directions = AlgorithmUtils.systemInString();

            List<Integer> res = contest.solution(positions, healths, directions);
            System.out.println(res);
        }

    }

    public List<Integer> solution(int[] positions, int[] healths, String directions) {
        int len = positions.length;
        Map<Integer, String> robotMap = new TreeMap<>();

        for (int i = 0; i < len; i++) {
            robotMap.put(positions[i], i+"_"+healths[i]+"_"+directions.charAt(i));
        }

        Deque<String> robotRightStack = new LinkedList<>();
        Map<Integer, Integer> rotbotResult = new TreeMap<>();

        for (String robotValue : robotMap.values()) {
            int index = Integer.parseInt(robotValue.split("_")[0]);
            int health = Integer.parseInt(robotValue.split("_")[1]);
            String direction = robotValue.split("_")[2];

            if ("L".equals(direction)) {

                if (robotRightStack.isEmpty()) {
                    rotbotResult.put(index, health);

                } else {

                    while (!robotRightStack.isEmpty()) {
                        String robotRightValue = robotRightStack.pollFirst();

                        int indexRight = Integer.parseInt(robotRightValue.split("_")[0]);
                        int healthRight = Integer.parseInt(robotRightValue.split("_")[1]);
                        if (health > healthRight) {
                            health--;
                            continue;

                        } else if (health < healthRight) {
                            healthRight--;
                            robotRightStack.addFirst(indexRight+"_"+healthRight);
                            health = -1;
                            break;

                        } else {
                            health = -1;
                            break;
                        }
                    }

                    if (health > -1) {
                        rotbotResult.put(index, health);
                    }
                }
            } else {
                robotRightStack.addFirst(index+"_"+health);
            }
        }

        while (!robotRightStack.isEmpty()) {
            String robotRightValue = robotRightStack.pollFirst();

            int indexRight = Integer.parseInt(robotRightValue.split("_")[0]);
            int healthRight = Integer.parseInt(robotRightValue.split("_")[1]);
            rotbotResult.put(indexRight, healthRight);
        }

        List<Integer> resList = new ArrayList<>();
        for (Integer robotHealth : rotbotResult.values()) {
            resList.add(robotHealth);
        }

        return resList;
    }


}
