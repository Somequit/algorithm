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

        int res = binarySearch.binarySearchMin(new long[]{1, 3, 5, 8}, 7);
        System.out.println(res);

    }

}
