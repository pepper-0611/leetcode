package toposort;

import org.junit.Test;

public class Question210Test {

    @Test
    public void test() {
        int[][] input = new int[1][];
        int[] a1 = new int[]{0, 1};
        input[0] = a1;
        new Question210().findOrder(2, input);
    }
}
