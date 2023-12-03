package template;

import java.util.Map;

/**
 * @author gusixue
 * @description 字典树模板
 * @date 2023/11/4
 */
public class Trie {
    public class TrieNode{
//        Map<Character, TrieNode> nextNode;
//        // 上一层是否为结束
//        // boolean isEnd;
        TrieNode one;
        TrieNode zero;
    }

    public TrieNode root;

    Trie() {
        this.root = new TrieNode();
    }

    public void insert(int num) {
        TrieNode curNode = this.root;

        for (int i = 30; i >= 0; i--) {
            int bitNum = (1 << i);

            // 该位为 0
            if ((num & bitNum) == 0) {
                if (curNode.zero == null) {
                    curNode.zero = new TrieNode();
                }
                curNode = curNode.zero;

            } else {
                if (curNode.one == null) {
                    curNode.one = new TrieNode();
                }
                curNode = curNode.one;
            }
        }
    }

    /**
     * 将 num 从高位到低位遍历，在字典树中找是否有该位相反的元素，没有则找相同的元素继续，返回 num 异或字典树的最大值
     */
    public int searchXorMax(int num) {
        int res = 0;
        TrieNode curNode = this.root;

        for (int i = 30; i >= 0; i--) {
            int bitNum = (1 << i);

            // 该位为 0
            if ((num & bitNum) == 0) {
                if (curNode.one == null) {
                    curNode = curNode.zero;

                } else {
                    res += bitNum;
                    curNode = curNode.one;
                }

            } else {
                if (curNode.zero == null) {
                    curNode = curNode.one;

                } else {
                    res += bitNum;
                    curNode = curNode.zero;
                }
            }

        }
        return res;
    }
}