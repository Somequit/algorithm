package leetcode.brushQuestions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gusixue
 * @description 2043. 简易银行系统
 * @date 2025/10/26 1:36 上午
 */
public class Bank {

    private static final List<Long> BANK_BALANCE = new ArrayList<>();

    public Bank(long[] balance) {
        BANK_BALANCE.clear();
        Arrays.stream(balance).forEach(BANK_BALANCE::add);
//        System.out.println(BANK_BALANCE);
    }

    public boolean transfer(int account1, int account2, long money) {
        int n = BANK_BALANCE.size();
        if (account1 >= 1 && account1 <= n && account2 >= 1 && account2 <= n && BANK_BALANCE.get(account1 - 1) >= money) {
            BANK_BALANCE.set(account1 - 1, BANK_BALANCE.get(account1 - 1) - money);
            BANK_BALANCE.set(account2 - 1, BANK_BALANCE.get(account2 - 1) + money);
//            System.out.println(BANK_BALANCE);
            return true;
        }

        return false;
    }

    public boolean deposit(int account, long money) {
        int n = BANK_BALANCE.size();
        if (account >= 1 && account <= n) {
            BANK_BALANCE.set(account - 1, BANK_BALANCE.get(account - 1) + money);
//            System.out.println(BANK_BALANCE);
            return true;
        }

        return false;
    }

    public boolean withdraw(int account, long money) {
        int n = BANK_BALANCE.size();
        if (account >= 1 && account <= n && BANK_BALANCE.get(account - 1) >= money) {
            BANK_BALANCE.set(account - 1, BANK_BALANCE.get(account - 1) - money);
//            System.out.println(BANK_BALANCE);
            return true;
        }

        return false;
    }
}
