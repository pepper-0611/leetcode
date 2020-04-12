package dp;

import java.util.LinkedList;

public class Question84 {

    /**
     * method  with stack
     */
    public int largestRectangleArea(int[] heights) {
        LinkedList<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        stack.push(-1);
        for (int i = 0; i <= heights.length; i++) {
            int curHeight = (i == heights.length) ? 0 : heights[i];
            while (stack.size() > 1 && heights[stack.peek()] > curHeight) {
                int cur = stack.pop();
                int left = stack.peek();
                maxArea = Math.max(maxArea, heights[cur] * (i - left - 1));
            }
            stack.push(i);
        }

        return maxArea;
    }
}
