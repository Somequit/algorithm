package leetcode.contest.contest_485;

import java.util.*;

/**
 * @author gusixue
 * @description
 * @date 2026/1/18 10:26 上午
 */
public class Contest_485_3 {

}

class AuctionSystem {

    private static final Map<Integer, TreeSet<Long>> MAP_ITEM_BID_USER = new HashMap<>();
    private static final Map<Long, Integer> MAP_ITEM_USER_BID = new HashMap<>();

    public AuctionSystem() {
        MAP_ITEM_BID_USER.clear();
        MAP_ITEM_USER_BID.clear();
    }

    public void addBid(int userId, int itemId, int bidAmount) {
        if (MAP_ITEM_USER_BID.containsKey(combine(itemId, userId))) {
            updateBid(userId, itemId, bidAmount);
            return;
        }

        TreeSet<Long> set = null;
        if (MAP_ITEM_BID_USER.containsKey(itemId)) {
            set = MAP_ITEM_BID_USER.get(itemId);

        } else {
            set = new TreeSet<>((bidUser1, bidUser2) -> Long.compare(bidUser2, bidUser1));
            MAP_ITEM_BID_USER.put(itemId, set);
        }
        set.add(combine(bidAmount, userId));

        MAP_ITEM_USER_BID.put(combine(itemId, userId), bidAmount);

//        System.out.println("add : " + itemId + " : " + MAP_ITEM_BID_USER.get(itemId));
    }

    private Long combine(int num1, int num2) {
        long res = Long.parseLong(num1 + "" + String.format("%05d", num2));
//        System.out.println(num1 + " : " + num2 + " : " + res);
        return res;
    }

    public void updateBid(int userId, int itemId, int newAmount) {
        int oldAmount = MAP_ITEM_USER_BID.get(combine(itemId, userId));

        MAP_ITEM_BID_USER.get(itemId).remove(combine(oldAmount, userId));
        MAP_ITEM_BID_USER.get(itemId).add(combine(newAmount, userId));

        MAP_ITEM_USER_BID.put(combine(itemId, userId), newAmount);

//        System.out.println("update : " + itemId + " : " + MAP_ITEM_BID_USER.get(itemId));
    }

    public void removeBid(int userId, int itemId) {
        int oldAmount = MAP_ITEM_USER_BID.get(combine(itemId, userId));

        MAP_ITEM_BID_USER.get(itemId).remove(combine(oldAmount, userId));

        MAP_ITEM_USER_BID.remove(combine(itemId, userId));
//        System.out.println("del : " + itemId + " : " + MAP_ITEM_BID_USER.get(itemId));
    }

    public int getHighestBidder(int itemId) {
        if (!MAP_ITEM_BID_USER.containsKey(itemId) || MAP_ITEM_BID_USER.get(itemId).size() == 0) {
            return -1;
        }

        return (int) (MAP_ITEM_BID_USER.get(itemId).first() % 100000);
    }
}