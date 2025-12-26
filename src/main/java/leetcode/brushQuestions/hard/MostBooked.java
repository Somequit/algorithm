package leetcode.brushQuestions.hard;

import java.util.*;

/**
 * @author gusixue
 * @description 2402. 会议室 III
 * @date 2025/12/27 1:10 上午
 */
public class MostBooked {

    public int mostBooked(int n, int[][] meetings) {
        long[] time = new long[n];
        Arrays.sort(meetings, Comparator.comparing(t -> t[0]));
//        Arrays.stream(meetings).forEach(m -> System.out.println(m[0] + " : " + m[1]));

        int[] roomCnt = new int[n];
        int maxRoomCnt = 0;
        int res = 0;

        for (int[] meet : meetings) {

            long minTime = Long.MAX_VALUE;
            int minTimeRoom = 0;
            int curRoom = -1;
            for (int i = 0; i < n; i++) {
                if (time[i] <= meet[0]) {
                    time[i] = meet[1];
                    curRoom = i;
                    break;
                }

                if (time[i] < minTime) {
                    minTime = time[i];
                    minTimeRoom = i;
                }
            }

//            System.out.println(curRoom + " : " + minTimeRoom + " : " + Arrays.toString(time));

            if (curRoom == -1) {
                curRoom = minTimeRoom;
                time[curRoom] = meet[1] - meet[0] + time[curRoom];

            }

            roomCnt[curRoom]++;
            if (roomCnt[curRoom] > maxRoomCnt) {
                maxRoomCnt = roomCnt[curRoom];
                res = curRoom;
            } else if (roomCnt[curRoom] == maxRoomCnt && res > curRoom) {
                res = curRoom;
            }

//            System.out.println(Arrays.toString(time) + " : " + maxRoomCnt + " : " + Arrays.toString(roomCnt));
        }

//        System.out.println(Arrays.toString(roomCnt));

        return res;
    }

    public int mostBookedOptional(int n, int[][] meetings) {
        int[] roomCnt = new int[n];
        int maxCnt = 0;
        int maxCntRoom = 0;
        Arrays.sort(meetings, Comparator.comparing(m -> m[0]));

        Queue<long[]> minTimeRoomPriorityQueue = new PriorityQueue<>((p1, p2) -> p1[0] == p2[0] ? Long.compare(p1[1] ,p2[1]) : Long.compare(p1[0] ,p2[0]));
        Queue<Integer> minRoomPriorityQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            minRoomPriorityQueue.offer(i);
        }

        for (int[] meet : meetings) {
            while (!minTimeRoomPriorityQueue.isEmpty() && minTimeRoomPriorityQueue.peek()[0] <= meet[0]) {
                long[] timeRoom = minTimeRoomPriorityQueue.poll();
                minRoomPriorityQueue.add((int) timeRoom[1]);
            }

            int curRoom = 0;
            if (!minRoomPriorityQueue.isEmpty()) {
                curRoom = minRoomPriorityQueue.poll();
                minTimeRoomPriorityQueue.offer(new long[]{meet[1], curRoom});

            } else {
                long[] timeRoom = minTimeRoomPriorityQueue.poll();
                timeRoom[0] = timeRoom[0] + meet[1] - meet[0];
                curRoom = (int) timeRoom[1];
                minTimeRoomPriorityQueue.offer(timeRoom);
            }


            roomCnt[curRoom]++;
            if (maxCnt < roomCnt[curRoom]) {
                maxCnt = roomCnt[curRoom];
                maxCntRoom = curRoom;
            } else if (maxCnt == roomCnt[curRoom] && maxCntRoom > curRoom) {
                maxCntRoom = curRoom;
            }

        }
        return maxCntRoom;
    }
}
