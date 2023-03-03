package utils;

import leetcode.ListNode;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AlgorithmUtils {

    /**
     * 输入数组
     */
    public static int[] systemInArray(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入数组的个数:");
        int length = sc.nextInt();
        int[] array = new int[length];
        for(int i=0;i<length;i++){
            int temp = sc.nextInt();
            array[i] = temp;
        }
        return array;
    }

    /**
     * 输入List
     */
    public static List<Integer> systemInList(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入容器的个数（换行区分）:");
        int length = sc.nextInt();
        sc.nextLine();
        List<Integer> list = new ArrayList<>(length);
        for(int i=0; i<length; i++){
            String temp = sc.nextLine();
            list.add((temp == null || temp.length() == 0) ? null : Integer.valueOf(temp));
        }
        return list;
    }

    /**
     * 输入数组
     */
    public static int[] systemInArray(int length){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入"+length+"个数字:");
        int[] array = new int[length];
        for(int i=0;i<length;i++){
            int temp = sc.nextInt();
            array[i] = temp;
        }
        return array;
    }

    /**
     * 输出数组
     */
    public static void systemOutArray(int[] array){
        if(array == null){
            throw new RuntimeException("array is null!");
        }
        for (int anArray : array) {
            System.out.print(anArray + " ");
        }
        System.out.println();
    }

    /**
     * 输出数组
     */
    public static void systemOutArray(String[] array, String delimiter){
        if(array == null){
            throw new RuntimeException("array is null!");
        }
        for (String anArray : array) {
            System.out.print(anArray + delimiter);
        }
        System.out.println();
    }

    /**
     * 输入一颗树
     * @return 返回树根
     */
    public static TreeNode systemInTree(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入树根的值:");
        int rootVal = sc.nextInt();
        TreeNode root = new TreeNode(rootVal);
        int valTemp = 0;
        while(valTemp != -1){
            System.out.print("请输入父节点、子节点以及0-左子树 和 1-右子树:");
            int father = sc.nextInt();
            int son = sc.nextInt();
            int leftOrRight = sc.nextInt();
            TreeNode fatherTreeNode = getTreeNodeByVal(father, root);
            System.out.println("当前父节点子树:"+fatherTreeNode);
            if(fatherTreeNode != null){
                TreeNode temp = new TreeNode(son);
                if(leftOrRight == 0){
                    fatherTreeNode.left = temp;
                } else{
                    fatherTreeNode.right = temp;
                }
            }
            valTemp = father;
        }
        return root;
    }

    /**
     * 通过值获取对应的节点
     */
    private static TreeNode getTreeNodeByVal(int val, TreeNode treeNode) {
        if(treeNode == null){
            return null;
        }
        if(val == treeNode.val){
            return treeNode;
        }
        TreeNode result = getTreeNodeByVal(val, treeNode.left);
        if(result == null) {
            result = getTreeNodeByVal(val, treeNode.right);
        }
        return result;
    }

    /**
     * 先序遍历输出一颗树
     * @return 返回树根
     */
    public static void preorderTraversalSystemOutTree(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        System.out.println(treeNode);
        preorderTraversalSystemOutTree(treeNode.left);
        preorderTraversalSystemOutTree(treeNode.right);
    }

    /**
     * 中序遍历输出一颗树
     * @return 返回树根
     */
    public static void middleOrderTraversalSystemOutTree(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        middleOrderTraversalSystemOutTree(treeNode.left);
        System.out.println(treeNode);
        middleOrderTraversalSystemOutTree(treeNode.right);
    }

    /**
     * 后序遍历输出一颗树
     * @return 返回树根
     */
    public static void postorderTraversalSystemOutTree(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        postorderTraversalSystemOutTree(treeNode.left);
        postorderTraversalSystemOutTree(treeNode.right);
        System.out.println(treeNode);
    }

    /**
     * 输出List集合
     */
    public static <T> void systemOutList(List<T> list, String separator){
        if(CollectionUtils.isEmpty(list)){
            throw new RuntimeException("list is null!");
        }
        for(T t : list){
            System.out.println(t+separator);
        }
        System.out.println();
    }

    /**
     * 输出Map集合
     * @param map
     * @param separator 分隔符
     */
    public static <K,V> void systemOutMap(Map<K,V> map, String separator){
        if(map == null){
            throw new RuntimeException("map is null!");
        }
        for(K k : map.keySet()){
            System.out.print(k+separator);
            V v = map.get(k);
            if(v instanceof Map){
                systemOutMap((Map)v, separator);
            } else {
                System.out.println(v);
            }
        }
        System.out.println();
    }

    /**
     * 字符串是否为null
     */
    public static boolean strIsEmpty(String str){
        if(str == null || str.length() <= 0){
            return true;
        }
        return false;
    }

    /**
     * 输入整数
     */
    public static int systemInNumberInt(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入整数:");
        int result = sc.nextInt();
        return result;
    }

    /**
     * 输入整数
     */
    public static long systemInNumber(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入整数:");
        long result = sc.nextLong();
        return result;
    }

    /**
     * 输入二维数组
     */
    public static int[][] systemInTwoArray(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入数组的行数:");
        int row = sc.nextInt();
        System.out.print("请输入数组的列数:");
        int cols = sc.nextInt();
        int[][] array = new int[row][cols];
        for(int i=0;i<row;i++){
            for(int j=0;j<cols;j++){
                int temp = sc.nextInt();
                array[i][j] = temp;
            }
        }
        return array;
    }

    /**
     * 输入数组
     */
    public static String systemInString(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入字符串:");
        String str = sc.nextLine();
        return str;
    }

    /**
     * 创建ArrayList并且添加参数
     */
    public static <T> List<T> crateListAdd(T... ts){
        List<T> resultList = new ArrayList<>();
        for(T t : ts){
            resultList.add(t);
        }
        return resultList;
    }

    /**
     * 十进制转二进制
     * 返回的就是补码，计算机均是以补码展示的！
     */
    public static String decimalToBinary(int number){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<32; i++) {
            stringBuilder.insert(0, (number & 1));
            number >>>= 1;
        }
        return stringBuilder.toString();
    }

    /**
     * 数组转链表
     * @return 返回链表头结点，空链表返回 null
     */
    public static ListNode arrToListNode(int[] arrs) {
        if (arrs == null || arrs.length == 0) {
            return null;
        }

        ListNode virtual = new ListNode();
        ListNode tail = virtual;

        for (int arr : arrs) {
            tail.setNext(new ListNode(arr));
            tail = tail.getNext();
        }

        return virtual.getNext();
    }
}
