package template;

/**
 * B+树
 * M >= 4
 * @param <Key>
 * @param <Value>
 */
public class BTree<Key extends Comparable<Key>, Value>  {
    /**
     * 标准B+树
     * 非根关键码：ceil(M/2)-1 ~ M-1，根：0~M-1，孩子：关键码+1
     * 此代码为了方便处理：孩子个数=关键码个数，且非根节点的所有孩子为关键码的右孩子
     */
    private static final int M = 5;

    private Node root;
    // 空树也设置为1
    private int height;
    // 所有关键码个数
    private int keyNumber;

private static final class Node {
    // 节点个数
    private int childrenNum;
    private Entry[] children = new Entry[M];
    // todo:叶子节点存在指向兄弟节点的指针

    private Node(int childrenNum) {
        this.childrenNum = childrenNum;
    }
}

private static class Entry {
    // 关键码：非叶子等于右孩子第一个节点
    private Comparable key;
    // 只有叶子节点才存储val，且叶子节点最后一个不存
    private Object value;
    // 关键码的右孩子节点
    private Node next;
    public Entry(Comparable key, Object value, Node next) {
        this.key  = key;
        this.value  = value;
        this.next = next;
    }
}

    /**
     * 初始化空树
     */
    public BTree() {
        this.root = new Node(0);
        this.keyNumber=0;
        // 空树也有根节点
        this.height=1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.keyNumber;
    }

    public int height() {
        return this.height;
    }

    /**
     * 返回给定key的value
     * @param key
     * @return 如果key存在返回value，如果key不存在返回null
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        return search(this.root, key, this.height);
    }

    private Value search(Node node, Key key, int height) {
        Entry[] children = node.children;

        if(height == 1){
            // 结果只会在叶子节点中出现
            for(int i=0;i<node.childrenNum; i++){
                if(eq(key, children[i].key)){
                    return (Value) children[i].value;
                }
            }
        } else{
            for(int i=0;i<node.childrenNum; i++){
                if(i == node.childrenNum-1 || less(key, children[i+1].key)){
                    return search(children[i].next, key, height-1);
                }
            }
        }

        return null;
    }

    /**
     * 添加key-value对
     * 如果key存在则直接覆盖value
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("argument key to put() is null");
        }
        Value resultVal = get(key);
        if(resultVal == null){
            insert(this.root, key, value, this.height);
        } else if(!value.equals(resultVal)){
            updateValue(this.root, key, value, this.height);
        }
    }

    private void updateValue(Node node, Key key, Value value, int height) {
        // 查询后继节点到根
        if(height == 1){
            for(int i=0;i <node.childrenNum; i++){
                if(eq(key, node.children[i].key)){
                    node.children[i].value = value;
                    return;
                }
            }
        } else{
            for(int i=0;i <node.childrenNum; i++){
                if(i == node.childrenNum - 1 || less(key, node.children[i+1].key)){
                    updateValue(node.children[i].next, key, value, height-1);
                    return;
                }
            }
        }
    }

    private void insert(Node root, Key key, Value value, int height) {
        Node divisionRoot = insertNode(this.root, key, value, this.height);
        this.keyNumber++;
        if (divisionRoot == null) {
            return;
        }

        // 根分裂
        Node newRoot = new Node(2);
        newRoot.children[0] = new Entry(root.children[0].key, null, this.root);
        newRoot.children[1] = new Entry(divisionRoot.children[0].key, null, divisionRoot);
        this.root = newRoot;
        this.height++;
    }

    /**
     * 添加新节点在它后继节点的前面，然后处理分裂情况
     * @param node
     * @param key
     * @param value
     * @param height
     * @return
     */
    private Node insertNode(Node node, Key key, Value value, int height) {
        int i;
        Entry newEntry = new Entry(key, value, null);
        // 查询后继节点到根
        if(height == 1){
            for(i=0;i <node.childrenNum; i++){
                if(less(key, node.children[i].key)){
                    break;
                }
            }
        } else{
            for(i=0;i <node.childrenNum; i++){
                if(i == node.childrenNum - 1 || less(key, node.children[i+1].key)){
                    Node divisionNode = insertNode(node.children[i].next, key, value, height-1);
                    // 设置非叶子节点一定等于孩子节点的第一位
                    node.children[i].key = (node.children[i].next).children[0].key;
                    // 非叶子节点添加索引
                    if(divisionNode == null){
                        return null;
                    }
                    newEntry.key = divisionNode.children[0].key;
                    newEntry.value = null;
                    newEntry.next = divisionNode;
                    i++;
                    break;
                }
            }
        }
        // 移位放置新节点
        for(int j=node.childrenNum; j>i; j--){
            node.children[j] = node.children[j-1];
        }
        node.children[i]=newEntry;
        node.childrenNum++;
        // 节点分裂
        if(node.childrenNum < this.M){
            return null;
        } else {
            // 返回分裂后的新节点
            return split(node);
        }
    }

    /**
     * 节点分裂、返回分裂后的新节点
     * @return
     */
    private Node split(Node oldNode) {
        int ceilHalfM = ((this.M+1)>>1);
        int floorHalfM = (this.M>>1);

        oldNode.childrenNum = ceilHalfM;
        Node newNode = new Node(floorHalfM);
        for(int i=0; i<floorHalfM; i++){
            newNode.children[i] = oldNode.children[i+ceilHalfM];
        }

        return newNode;
    }


    @Override
    public String toString() {
        return toString(this.root, this.height, "");
    }

    private String toString(Node node, int height, String indent) {
        StringBuilder stringBuilder = new StringBuilder();
        Entry[] children = node.children;

        if (height == 1) {
            for (int i = 0; i < node.childrenNum; i++) {
                stringBuilder.append(indent + children[i].key + " " + children[i].value + "\n");
            }
        } else {
            for (int i = 0; i < node.childrenNum; i++) {
                stringBuilder.append(indent + "(" + children[i].key + ")\n");
                stringBuilder.append(toString(children[i].next, height-1, indent+"    "));
            }
        }
        return stringBuilder.toString();
    }


    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }


    public static void main(String[] args) {
        BTree<Integer, String> bTree = new BTree<Integer, String>();

//        bTree.put("www.cs.princeton.edu", "128.112.136.12");
//        bTree.put("www.princeton.edu",    "128.112.128.15");
//        bTree.put("www.yale.edu",         "130.132.143.21");
//        bTree.put("www.simpsons.com",     "209.052.165.60");
//        bTree.put("www.apple.com",        "17.112.152.32");
//        bTree.put("www.amazon.com",       "207.171.182.16");
//        bTree.put("www.ebay.com",         "66.135.192.87");
//        bTree.put("www.cnn.com",          "64.236.16.20");
//        bTree.put("www.google.com",       "216.239.41.99");
//        bTree.put("www.nytimes.com",      "199.239.136.200");
//        bTree.put("www.microsoft.com",    "207.126.99.140");
//        bTree.put("www.dell.com",         "143.166.224.230");
//        bTree.put("www.slashdot.org",     "66.35.250.151");
//        bTree.put("www.espn.com",         "199.181.135.201");
//        bTree.put("www.weather.com",      "63.111.66.11");
//        bTree.put("www.yahoo.com",        "216.109.118.65");
//        bTree.put("www.cs.princeton.edu", "128.112.136.11");
//
//
//        System.out.println("cs.princeton.edu:  " + bTree.get("www.cs.princeton.edu"));
//        System.out.println("hardvardsucks.com: " + bTree.get("www.harvardsucks.com"));
//        System.out.println("simpsons.com:      " + bTree.get("www.simpsons.com"));
//        System.out.println("apple.com:         " + bTree.get("www.apple.com"));
//        System.out.println("ebay.com:          " + bTree.get("www.ebay.com"));
//        System.out.println("dell.com:          " + bTree.get("www.dell.com"));
//        System.out.println();



        bTree.put(5,  "a5");
        bTree.put(8,  "a8");
        bTree.put(15, "a15");
        bTree.put(10, "a10");
        bTree.put(16, "a16");
        bTree.put(13, "a13");
        bTree.put(14, "a14");
        bTree.put(1,  "a1");
        bTree.put(2,  "a2");
        bTree.put(7,  "a7");
        bTree.put(9,  "a9");
        bTree.put(17, "a17");
        bTree.put(18, "a28");
        bTree.put(21, "a21");
        bTree.put(5,  "aa5");
        bTree.put(10, "aa10");
        bTree.put(1,  "aa1");
        bTree.put(21, "aa21");
        bTree.put(18, "aa18");


        System.out.println("1:  " + bTree.get(1));
        System.out.println("5:  " + bTree.get(5));
        System.out.println("7:  " + bTree.get(7));
        System.out.println("10:  " + bTree.get(10));
        System.out.println("13:  " + bTree.get(13));
        System.out.println("15:  " + bTree.get(15));
        System.out.println("18:  " + bTree.get(18));
        System.out.println();

        System.out.println("size:    " + bTree.size());
        System.out.println("height:  " + bTree.height());
        System.out.println(bTree);
        System.out.println();
    }

}