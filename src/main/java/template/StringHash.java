package template;

import java.util.Random;

/**
 * @author gusixue
 * @description 字符串 Hash
 * @date 2024/2/18
 */
public class StringHash {
    char[] str;
    long seed = 31;
    long mod = 1_000_000_007;

    int n;
    // hash前缀和
    long[] pre;
    // p的幂次
    long[] pow;

    StringHash(String s, int seed, long mod) {
        this.str = s.toCharArray();
        this.seed = seed;
        this.mod = mod;

        preHash();
    }

    private void preHash() {
        this.n = this.str.length;
        pre = new long[n + 1];
        pow = new long[n + 1];

        pow[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow[i] = pow[i - 1] * seed % mod;
        }
        for (int i = 0; i < str.length; i++) {
            pre[i + 1] = (pre[i] * seed % mod + str[i]) % mod;
        }
    }

    StringHash(String s) {
        this.str = s.toCharArray();

        preHash();
    }

    public long query(int l, int r) {
        long res = pre[r + 1] - pre[l] * pow[r - l + 1] % mod;
        return (res % mod + mod) % mod;
    }

    public long rotate(int l) {
        if (l < 0 || l >= str.length - 1) {
            return query(0, str.length - 1);
        } else {
            long h1 = query(0, l);
            long h2 = query(l + 1, str.length - 1);
            return (h2 * pow[l + 1] % mod + h1) % mod;
        }
    }

    public long evaluate(String s) {
        long h = 0;
        for (char c: s.toCharArray()) {
            h = (h * this.seed % this.mod + c) % mod;
        }
        return h;
    }
}