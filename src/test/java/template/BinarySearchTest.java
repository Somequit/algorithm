package template;


import org.junit.Test;

/**
 * @author gusixue
 * @date 2023/3/26
 */
public class BinarySearchTest {

    @Test
    public void test() {
        BinarySearch binarySearch = new BinarySearch();

        int res = binarySearch.binarySearchLow(new long[]{1, 1, 1, 2, 2, 3, 3}, 2);
        System.out.println(res);

        res = binarySearch.binarySearchLow(new long[]{2, 2, 3, 3}, 2);
        System.out.println(res);

        res = binarySearch.binarySearchLow(new long[]{1}, 2);
        System.out.println(res);

        res = binarySearch.binarySearchLow(new long[]{1, 1}, 2);
        System.out.println(res);

        res = binarySearch.binarySearchLow(new long[]{1, 1, 3, 3}, 2);
        System.out.println(res);


        res = binarySearch.binarySearchHigh(new long[]{1, 1, 2, 2, 3, 3, 3}, 2);
        System.out.println(res);

        res = binarySearch.binarySearchHigh(new long[]{1, 1, 2, 2, 2}, 2);
        System.out.println(res);

        res = binarySearch.binarySearchHigh(new long[]{3}, 2);
        System.out.println(res);

        res = binarySearch.binarySearchHigh(new long[]{3, 3}, 2);
        System.out.println(res);

        res = binarySearch.binarySearchHigh(new long[]{1, 1, 3, 3}, 2);
        System.out.println(res);

    }

}
