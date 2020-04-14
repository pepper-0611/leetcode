package knowledge;

import stack.Question84;
import org.junit.Assert;
import org.junit.Test;

public class Question84Test {

    @Test
    public void test_base_case0() {
        int[] height = new int[]{0};
        int res = new Question84().largestRectangleArea(height);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test_base_case1() {
        int[] height = new int[]{4};
        int res = new Question84().largestRectangleArea(height);
        Assert.assertEquals(4, res);
    }

    @Test
    public void test_dup_case0() {
        int[] height = new int[]{4,5,6};
        int res = new Question84().largestRectangleArea(height);
        Assert.assertEquals(12, res);
    }

    @Test
    public void test_dup_case1() {
        int[] height = new int[]{4,5,6,3,2,1,4,7,8,9};
        int res = new Question84().largestRectangleArea(height);
        Assert.assertEquals(21, res);
    }

    @Test
    public void test_dup_case2() {
        int[] height = new int[]{1,2,1,2,3,4,2,1,2,1};
        int res = new Question84().largestRectangleArea(height);
        Assert.assertEquals(10, res);
    }

}
