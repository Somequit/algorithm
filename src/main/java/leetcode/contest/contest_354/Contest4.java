package leetcode.contest.contest_354;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;

import java.util.*;


/**
 *
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
                    // [i, i+wordMinMatch[i]] 中 j 元素前缀匹配到的 forbidden[] 可能更短
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


}
