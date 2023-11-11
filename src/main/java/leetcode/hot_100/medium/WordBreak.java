package leetcode.hot_100.medium;

import utils.AlgorithmUtils;

import java.util.*;

/**
 * @author gusixue
 * @description
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅由小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 * @date 2023/9/19
 */
public class WordBreak {

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        while (true) {
            String s = AlgorithmUtils.systemInString();
            List<String> wordDict = AlgorithmUtils.systemInListString();

            boolean res = wordBreak.solution(s, wordDict);
            System.out.println(res);
        }
    }

    static class TrieNode{
        Map<Character, TrieNode> nextNode;
        // 上一层是否为结束
        boolean isEnd;

        public TrieNode() {
            this.nextNode = new HashMap<>(52);
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                    "isEnd=" + isEnd +
                    ", nextNode=" + nextNode +
                    '}';
        }
    }

    /**
     * 字典树+dp：将 wordDict 每次单词放入字典树中，接着将 s 字符串按照 [0,len) 为开头的字符串放入字典树去匹配，找到能够匹配上的所有 match[start]=end
     * 使用区间 dp：dp[i+1] 代表 [s[0],s[i]] 是否能够被 wordDict 拼接出来，初始化 dp[0]=true
     * 状态转移方程：dp[i]=dp[i-j]&(match[i-j]==（i-1）) 1<=j<=min(i, max(wordDict[i].length))，
     * n 为 s.length、m1 为 wordDict.length、m2 为 max(wordDict[i].length)，时间复杂度：O（m1*m2+n*m2），空间复杂度：O（26*m1*m2+n*m2）
     */
    private boolean solution(String s, List<String> wordDict) {
        // 将 wordDict 每次单词放入字典树中
        TrieNode root = new TrieNode();
        int maxWordDict = 0;
        for (String wordDictStr : wordDict) {
            maxWordDict = Math.max(maxWordDict, wordDictStr.length());
            insertTrie(root, wordDictStr);
        }

        int sLen = s.length();
        Set<Integer>[] match = new HashSet[sLen];
        // 将 s 字符串按照 [0,sLen) 为开头的字符串放入字典树去匹配，找到能够匹配上的所有 s[end]->s[start]
        for (int i = 0; i < sLen; i++) {
            String sTemp = s.substring(i, Math.min(sLen, i + maxWordDict));
            List<Integer> matchList = listTrieEnd(root, sTemp);

            match[i] = new HashSet<>();
            for (int matchIndex : matchList) {
                match[i].add(matchIndex + i);
            }
        }
//        System.out.println(Arrays.toString(match));

        // 区间 dp
        boolean[] dp = new boolean[sLen + 1];
        dp[0] = true;

        // 转移方程：dp[i]=dp[i-j]&(s[i-j]->s[i-1]?) 1<=j<=min(i, max(wordDict[i].length))，
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= Math.min(i, maxWordDict); j++) {
                dp[i] = (dp[i - j] & match[i - j].contains(i - 1));
                if (dp[i]) {
                    break;
                }
            }
        }
//        System.out.println(Arrays.toString(dp));

        return dp[sLen];
    }

    private boolean insertTrie(TrieNode root, String word) {
        // 判空
        if (word == null || word.length() <= 0) {
            return false;
        }

        TrieNode node = root;
        // 循环将 word 放入字典树 root
        for (int i = 0; i < word.length(); i++) {
            // 不存在 word.charAt(i) 则新建对应 node
            if (!node.nextNode.containsKey(word.charAt(i))) {
                node.nextNode.put(word.charAt(i), new TrieNode());
            }

            // node 移动到 next
            node = node.nextNode.get(word.charAt(i));
        }

        // 最后一个字符标记结束
        node.isEnd = true;
//        System.out.println(root);
        return true;
    }

    /**
     * word 从 0 出发有哪些前缀在字典树 root 中完全匹配（isEnd 为 true）
     */
    private List<Integer> listTrieEnd(TrieNode root, String word) {
        // 判空
        if (word == null || word.length() <= 0) {
            return null;
        }

        List<Integer> res = new ArrayList<>();
        TrieNode node = root;
        // 循环将 word 匹配字典树 root
        for (int i = 0; i < word.length(); i++) {
            // 不存在 word.charAt(i) 则直接返回
            if (!node.nextNode.containsKey(word.charAt(i))) {
                break;

            }

            // node 移动到 next
            node = node.nextNode.get(word.charAt(i));

            // 判断 isEnd 为 true 则加入 list
            if (node.isEnd) {
                res.add(i);
            }
        }

        return res;
    }
}
