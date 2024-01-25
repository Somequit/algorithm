package leetcode.hot_100.first.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gusixue
 * @description
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10 ^ 4 次
 * @date 2023/9/22
 */
public class Trie {

    static class TrieNode {
        private Map<Character, TrieNode> next;
        // 前一个元素是否为结尾
        private boolean prevIsEnd;

        public TrieNode() {
            next = new HashMap<>(52);
            prevIsEnd = false;
        }

        public Map<Character, TrieNode> getNext() {
            return next;
        }

        public boolean isPrevIsEnd() {
            return prevIsEnd;
        }

        public void setPrevIsEnd(boolean prevIsEnd) {
            this.prevIsEnd = prevIsEnd;
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                    "prevIsEnd=" + prevIsEnd +
                    ", next=" + next +
                    '}';
        }
    }

    private TrieNode root;

    /**
     * 初始化前缀树对象。
     */
    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * 向前缀树中插入字符串 word
     */
    public void insert(String word) {
        // 判空
        if (word == null || word.length() <= 0) {
            return;
        }

        // 循环 word 字符
        int len = word.length();
        TrieNode curNode = this.root;
        for (int i = 0; i < len; i++) {
            // 如果没有此字符则新建
            if (!curNode.getNext().containsKey(word.charAt(i))) {
                curNode.getNext().put(word.charAt(i), new TrieNode());
            }

            curNode = curNode.getNext().get(word.charAt(i));
        }

        // 最后一个字符后设置结尾为 true
        curNode.setPrevIsEnd(true);
    }

    /**
     * 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
     */
    public boolean search(String word) {
        // 判空
        if (word == null || word.length() <= 0) {
            return false;
        }

        boolean resFlag = true;
        // 循环 word 字符
        int len = word.length();
        TrieNode curNode = this.root;
        for (int i = 0; i < len; i++) {
            // 如果没有此字符则返回 false
            if (!curNode.getNext().containsKey(word.charAt(i))) {
                resFlag = false;
                break;
            }

            curNode = curNode.getNext().get(word.charAt(i));
        }

        // 最后一个字符后是否结尾为 true
        return resFlag && curNode.isPrevIsEnd();
    }

    /**
     * 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
     */
    public boolean startsWith(String prefix) {
        // 判空
        if (prefix == null || prefix.length() <= 0) {
            return false;
        }

        boolean resFlag = true;
        // 循环 prefix 字符
        int len = prefix.length();
        TrieNode curNode = this.root;
        for (int i = 0; i < len; i++) {
            // 如果没有此字符则返回 false
            if (!curNode.getNext().containsKey(prefix.charAt(i))) {
                resFlag = false;
                break;
            }

            curNode = curNode.getNext().get(prefix.charAt(i));
        }

        // 只要存在 prefix 就是 true
        return resFlag;
    }
}
