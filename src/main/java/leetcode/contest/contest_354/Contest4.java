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

        String word = AlgorithmUtils.systemInString();
        String forbiddenJson = AlgorithmUtils.systemInString();

        int res = contest.solution(word, JSONObject.parseArray(forbiddenJson, String.class));
        System.out.println(res);

//        while (true) {
//            String word = AlgorithmUtils.systemInString();
//            List<String> forbidden = CollectionUtils.arrayToList(AlgorithmUtils.systemInArrayString());
//
//            int res = contest.solution(word, forbidden);
//            System.out.println(res);
//        }

    }

    class TreeNode{
        Map<Character,TreeNode> map;
        boolean isEnd;
        public TreeNode(){
            map = new HashMap<>(32);
        }
//
//        @Override
//        public String toString() {
//            return "isEnd=" + isEnd + " TreeNode{"
//                    + " map=" + map +
//                    +'}';
//        }
    }

    TreeNode root;

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

    public int searchMinNum(String word) {
        TreeNode node = root;
        for(int i = 0;i < word.length();++i){
            char index = word.charAt(i);
            // System.out.println(node);
            if(!node.map.containsKey(index)){
                return -1;
            }
            if (node.map.get(index).isEnd) {
                return i + 1;
            }
            node = node.map.get(index);
        }
        return node.isEnd == true ? word.length() : -1;
    }

    public boolean startsWith(String prefix) {
        TreeNode node = root;
        for(int i = 0;i < prefix.length();++i){
            char index = prefix.charAt(i);
            if(!node.map.containsKey(index)){
                return false;
            }
            node = node.map.get(index);
        }
        return true;
    }

    public int solution(String word, List<String> forbidden) {
        int res = 0;

        System.out.println(System.currentTimeMillis());
        root = new TreeNode();
        Set<String> forbiddenSet = new HashSet<>(forbidden.size() << 1);
        for (String forbiddenStr : forbidden) {
//            System.out.println(forbiddenStr);
            if (!forbiddenSet.contains(forbiddenStr)) {
                forbiddenSet.add(forbiddenStr);
                insert(forbiddenStr);
            }
        }
        System.out.println(System.currentTimeMillis());


        int[] wordMinMath = new int[word.length()];

        int maxWordMinMath = 0;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            String wordTemp = word.substring(i, Math.min(len, i + 10));
            // System.out.println(wordTemp);
            wordMinMath[i] = searchMinNum(wordTemp);
            maxWordMinMath = Math.max(maxWordMinMath, wordMinMath[i]);

        }
        System.out.println(maxWordMinMath + " : " + len);
        // System.out.println(Arrays.toString(wordMinMath));
        System.out.println(System.currentTimeMillis());

        int count = 0;
        int cur = 0;
        for (int i = 0; i < len; i++) {

            if (wordMinMath[i] != -1) {
                int curOrigin = cur;
                cur += wordMinMath[i] - 1;

                int curTemp = 0;
                for (int j = i + 1; j < len && j < i + 1 + wordMinMath[i]; j++) {
                    count++;
                    curTemp++;
                    if (wordMinMath[j] != -1) {
                        cur = Math.min(cur, curOrigin + curTemp + wordMinMath[j] - 1);
                    }
                }
                // System.out.println(cur);

                res = Math.max(res, cur);
                cur = 0;

            } else {
                cur ++;
                res = Math.max(res, cur);
                // System.out.println(cur);
            }
        }

        System.out.println(System.currentTimeMillis());

        return res;
    }


}
