package leetcode.hot_100.medium;

import javafx.util.Pair;
import template.Algorithm;
import utils.AlgorithmUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gusixue
 * @description
 * 79. 单词搜索
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 * @date 2023/11/10
 */
public class Exist {

    public static void main(String[] args) {
        Exist exist = new Exist();
        while (true) {
            char[][] board = AlgorithmUtils.systemInTwoArrayChar();
            String word = AlgorithmUtils.systemInString();

            boolean res = exist.solution(board, word);
            System.out.println(res);
        }
    }

    private static final int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * DFS：board 每个点作为 word 开头（相同）进行 DFS 遍历，遍历过程中递归则记录该点已被使用过（不能再次递归该点），回溯则删除该点使用记录，
     * 剪枝：双向搜索，将 word 以 n/2 拆分成两段，[0,n/2]=w1、[n/2,n-1]=w2，记录每个点的路径为：n行m列：x*m+y，
     * 先搜索出所有 w1、记录完全匹配 w1 的路径，将除最后一个点的路径压缩到 Long 的 bit 位中，最后一个点路径作为下标，俩元素存储到 Set<Long>[] 字符串中，
     * 接着再从每个 set 字符串（长度大于 0、代表能匹配到 w1）下标为起点搜索 w2，每搜索出一个 w2 将路径压缩后与对应 set[] 中每个数字取异或与按位或，
     * 如果相等则返回 true，否则继续搜索，（a^b == a|b，代表不会出现相同位置为 1 的情况，即字母不会重复使用）
     */
    private boolean solution(char[][] board, String word) {
        // 判空
        if (word == null || board == null || word.length() == 0 || board.length == 0 || board[0].length == 0) {
            return false;
        }

        // 如果 word 仅一个字符则直接搜索整个 board
        if (word.length() == 1) {
            for (char[] boards : board) {
                for (char boardChar : boards) {
                    if (word.charAt(0) == boardChar) {
                        return true;
                    }
                }
            }

            return false;
        }

        // 双向搜索，将 word 以 n/2 拆分成两段，[0,n/2]=w1、[n/2,n-1]=w2
        int wordLen = word.length();
        int wordHalfLen = wordLen >> 1;
        int n = board.length;
        int m = board[0].length;

        int[][] vis = new int[n][m];
        Set<Long>[] wordPrevSet = new HashSet[n * m];
        // board 每个点作为 word 开头（相同）进行 DFS 遍历
        char wordFirst = word.charAt(0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == wordFirst) {
                    // 最后一个字符位置转化为点 - 路径其他位置将 bit 置为 1
                    List<Pair<Integer, Long>> lastPositionBitList = new ArrayList<>();
                    // 遍历过程中递归则记录该点已被使用过（不能再次递归该点），回溯则删除该点使用记录
                    dfsMatchWord(board, i, j, n, m, word, 0, wordHalfLen, 0L, lastPositionBitList, vis);
//                    System.out.println(lastPositionBitList);

                    for (Pair<Integer, Long> lastPositionBitPair : lastPositionBitList) {
                        int last = lastPositionBitPair.getKey();
                        long position = lastPositionBitPair.getValue();

                        if (wordPrevSet[last] == null) {
                            wordPrevSet[last] = new HashSet<>();
                        }

                        wordPrevSet[last].add(position);
                    }
                }
            }
        }

        // a^b == a|b，代表不会出现相同位置为 1 的情况，即字母不会重复使用
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curPos = gridToInt(i, j, m);
                // 当前位置一定等于 w1 的最后一个字符，即 w2 的第一个字符
                if (wordPrevSet[curPos] != null) {
                    // 最后一个字符位置转化为点 - 路径其他位置将 bit 置为 1
                    List<Pair<Integer, Long>> lastPositionBitList = new ArrayList<>();
                    dfsMatchWord(board, i, j, n, m, word, wordHalfLen, wordLen - 1, 0L, lastPositionBitList, vis);

                    for (Pair<Integer, Long> lastPositionBitPair : lastPositionBitList) {
                        int last = lastPositionBitPair.getKey();
                        long position = lastPositionBitPair.getValue() | (1 << last);

                        for (long wordPrevPosition : wordPrevSet[curPos]) {
                            if ((wordPrevPosition ^ position) == (wordPrevPosition | position)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    // 记录每个点的路径为：n行m列：x*m+y
    private int gridToInt(int x, int y, int m) {
        return x * m + y;
    }

    private void dfsMatchWord(char[][] board, int x, int y, int n, int m, String word, int cur, int end, long position,
                              List<Pair<Integer,Long>> lastPositionBitList, int[][] vis) {
        // 最后一个点作为 Integer 不加入 position
        if (cur == end) {
            lastPositionBitList.add(new Pair<>(gridToInt(x, y, m), position));
            return;
        }

        for (int i = 0; i < DIR.length; i++) {
            int xx = x + DIR[i][0];
            int yy = y + DIR[i][1];

            if (xx < 0 || xx >= n || yy < 0 || yy >= m || vis[xx][yy] == 1 || board[xx][yy] != word.charAt(cur + 1)) {
                continue;
            }

            vis[x][y] = 1;
            dfsMatchWord(board, xx, yy, n, m, word, cur + 1, end,
                    position | (1 << gridToInt(x, y, m)), lastPositionBitList, vis);
            vis[x][y] = 0;
        }

    }
}
