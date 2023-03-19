package leetcode.contest;

import org.springframework.util.CollectionUtils;
import utils.AlgorithmUtils;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gusixue
 * @date 2023/3/19
 */
public class ContestTest {

    public static void main(String[] args) {
        ContestTest contestTest = new ContestTest();
//        Integer[] nums = new Integer[]{9,4,7,5,null,null,9,null,null,5,null,8};
//        List<Integer> treeList = CollectionUtils.arrayToList(nums);
//        TreeNode root = AlgorithmUtils.createTree(treeList);

        Integer num = 100000;
        contestTest.pseudoPalindromicPaths(null, num);
        System.out.println(num);

    }

    public int pseudoPalindromicPaths (TreeNode root, Integer num) {
        num +=2;
        return num;
//        int[] valNums = new int[10];
//        return recursionPalindPaths(root, valNums, 0);
    }

//    private int recursionPalindPaths(TreeNode node, int[] valNums, int oddNum) {
//        valNums[node.val]++;
//        if (1 == (valNums[node.val] & 1)) {
//            oddNum++;
//        } else {
//            oddNum--;
//        }
//        System.out.println(node.val + ":" + oddNum + ":" + Arrays.toString(valNums));
//
//        if (node.left == null && node.right == null) {
//            valNums[node.val]--;
//            if (oddNum <= 1) {
//                return 1;
//            }
//            return 0;
//        }
//
//        int res = 0;
//        if (node.left != null) {
//            res += recursionPalindPaths(node.left, valNums, oddNum);
//        }
//        if (node.right != null) {
//            res += recursionPalindPaths(node.right, valNums, oddNum);
//        }
//
//        valNums[node.val]--;
//
//        return res;
//    }
}
