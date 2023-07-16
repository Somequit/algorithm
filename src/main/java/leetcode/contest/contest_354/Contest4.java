package leetcode.contest.contest_354;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;

import java.util.*;


/**
 * 6924. 最长合法子字符串的长度
 * 给你一个字符串 word 和一个字符串数组 forbidden 。
 * 如果一个字符串不包含 forbidden 中的任何字符串，我们称这个字符串是 合法 的。
 * 请你返回字符串 word 的一个 最长合法子字符串 的长度。
 * 子字符串 指的是一个字符串中一段连续的字符，它可以为空。
 * 1 <= word.length <= 10 ^ 5
 * word 只包含小写英文字母。
 * 1 <= forbidden.length <= 10 ^ 5
 * 1 <= forbidden[i].length <= 10
 * forbidden[i] 只包含小写英文字母。
 */
public class Contest4 {

    public static void main(String[] args) {
        Contest4 contest = new Contest4();

        while (true) {
            String word = AlgorithmUtils.systemInString();
            List<String> forbidden = CollectionUtils.arrayToList(AlgorithmUtils.systemInArrayString());

            int res = contest.solution(word, forbidden);
            System.out.println(res);
        }

    }

    /**
     * Trie + 枚举最远距离：由于 forbidden[i] 长度只有 10，因此思考 word 中每个元素匹配到 forbidden 的长度是 [1，10]，
     * 如果找出 word 每个元素为前缀匹配到 forbidden 的最短长度，那么就可以边循环 word 边更新最长合法字符串了
     * 将 forbidden[i] 放入 Trie 树中同时记录每个结尾，然后将 word 每个元素为开头的最多 10 个元素放入字典树查找，找到最少匹配的个数存入 wordMinMatch 数组，
     * 循环 wordMinMatch 数组：
     *     如果无法匹配则可与前面最远的无法匹配进行组合，设置变量 ++ 即可
     *     如果匹配得上、则最远可以添加 wordMinMatch[i]-1 个元素，然后变量置为 0（因为只能下一个元素开始，否则匹配长度一定变小）
     *         还需要注意在 [i, i+wordMinMatch[i]-1] 中某个 j 元素匹配到的 forbidden[] 可能更短，
     *         例如：5 -1 2 -1 -1 -1，在 5 处 +4 会导致包含后面的 2，因此循环 [i, i+wordMinMatch[i]-1] 暴力找最近的距离
     * word.length 为 n、forbidden.length 为 m、forbidden[i].length 为 k，时间复杂度：O（mk + nk），空间复杂度：O（mk + n）
     */
    public int solution(String word, List<String> forbidden) {
        int res = 0;

        // 初始化
        root = new TreeNode();

        // 去重并加入 Trie 树
        Set<String> forbiddenSet = new HashSet<>(forbidden.size() << 1);
        for (String forbiddenStr : forbidden) {

            if (!forbiddenSet.contains(forbiddenStr)) {
                forbiddenSet.add(forbiddenStr);
                // 加入 Trie 树
                insert(forbiddenStr);
            }
        }


        // word[i] 当前元素为前缀最短匹配的长度，无法匹配则返回 -1
        int[] wordMinMatch = new int[word.length()];
        int len = word.length();
        for (int i = 0; i < len; i++) {
            String wordTemp = word.substring(i, Math.min(len, i + 10));
            wordMinMatch[i] = searchMinNum(wordTemp);

        }

        int cur = 0;
        for (int i = 0; i < len; i++) {

            // 前缀能够匹配 forbidden[]
            if (wordMinMatch[i] != -1) {

                // 找到能匹配的最远距离（forbidden[] 最大为 10）
                int curMaxMatch = wordMinMatch[i] - 1;
                for (int j = i + 1; j < i + wordMinMatch[i]; j++) {
                    // [i, i+wordMinMatch[i]-1] 中 j 元素前缀匹配到的 forbidden[] 可能更短
                    if (wordMinMatch[j] != -1) {
                        curMaxMatch = Math.min(curMaxMatch, (j - i) + (wordMinMatch[j] - 1));
                    }
                }

                res = Math.max(res, cur + curMaxMatch);
                cur = 0;

            } else {
                cur ++;
                res = Math.max(res, cur);
            }
        }

        return res;
    }


    class TreeNode{
        Map<Character,TreeNode> map;
        // 使用 map.get.isEnd 判断是否结尾
        boolean isEnd;
        public TreeNode(){
            map = new HashMap<>();
        }
    }

    TreeNode root;

    /**
     * 将 word 存入字典树
     * @param word
     */
    public void insert(String word) {
        TreeNode node = root;
        for(int i = 0;i < word.length();++i){
            char index = word.charAt(i);
            if(!node.map.containsKey(index)){
                node.map.put(index,new TreeNode());
            }
            node = node.map.get(index);
        }
        node.isEnd = true;
    }

    /**
     * 返回 word 匹配到 Trie 树的最短长度
     */
    public int searchMinNum(String word) {
        TreeNode node = root;
        for(int i = 0;i < word.length();++i){
            char index = word.charAt(i);
            if(!node.map.containsKey(index)){
                return -1;
            }
            node = node.map.get(index);

            // 中途存在直接返回长度（node 匹配 index 后 next 过了）
            if (node.isEnd) {
                return i + 1;
            }
        }
        // node 匹配 index 后 next 过了
        return node.isEnd == true ? word.length() : -1;
    }


}
