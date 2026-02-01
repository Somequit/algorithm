package leetcode.contest.contest_487;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/2/1 10:22 上午
 */
public class Contest_487_3 {
}

class RideSharingSystem {
    private static final LinkedList<Integer> LIST_DRIVER = new LinkedList<>();
    private static final LinkedList<Integer> LIST_RIDER = new LinkedList<>();
    private static final Set<Integer> SET_WAIT_RIDER = new HashSet<>();

    public RideSharingSystem() {
        LIST_DRIVER.clear();
        LIST_RIDER.clear();
        SET_WAIT_RIDER.clear();
    }

    public void addRider(int riderId) {
        LIST_RIDER.addFirst(riderId);
        SET_WAIT_RIDER.add(riderId);
    }

    public void addDriver(int driverId) {
        LIST_DRIVER.addFirst(driverId);
    }

    public int[] matchDriverWithRider() {
        if (LIST_DRIVER.size() == 0) {
            return new int[]{-1, -1};
        }

        while (LIST_RIDER.size() > 0) {
            int riderId = LIST_RIDER.pollLast();
            if (SET_WAIT_RIDER.contains(riderId)) {
                return new int[]{LIST_DRIVER.pollLast(), riderId};
            }
        }

        return new int[]{-1, -1};
    }

    public void cancelRider(int riderId) {
        SET_WAIT_RIDER.remove(riderId);
    }
}