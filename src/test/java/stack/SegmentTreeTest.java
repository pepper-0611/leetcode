package stack;

import knowledge.SegmentTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SegmentTreeTest {

    int height[];
    SegmentTree tree;

    @Before
    public void prepare() {
        height = new int[]{1, 0, 3, 4, 2, 7, 9, 11, 3, 5};
        tree = SegmentTree.buildSegmentTree(height, 0, height.length - 1);
    }

    @Test
    public void test_0() {
        int min = SegmentTree.query(tree, height, 0, 9);
        Assert.assertEquals(1, min);
    }
    @Test
    public void test_1() {
        int min = SegmentTree.query(tree, height, 3, 3);
        Assert.assertEquals(3, min);
    }
    @Test
    public void test_2() {
        int min = SegmentTree.query(tree, height, 3, 5);
        Assert.assertEquals(4, min);
    }
    @Test
    public void test_3() {
        int min = SegmentTree.query(tree, height, 7, 9);
        Assert.assertEquals(8, min);
    }
    @Test
    public void test_4() {
        int min = SegmentTree.query(tree, height, 2, 6);
        Assert.assertEquals(4, min);
    }
}
