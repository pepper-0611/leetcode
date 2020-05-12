package toposort;

import org.junit.Assert;
import org.junit.Test;

public class Question207Test {

    @Test
    public void test(){
        int nums = 2;
        int[][] table = new int[1][];
        table[0] = new int[]{1, 0};
        boolean ok = new Question207_dfs().canFinish(nums,  table);
        Assert.assertTrue(ok);
    }
}
