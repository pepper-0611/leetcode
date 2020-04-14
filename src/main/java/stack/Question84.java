package stack;

import java.util.LinkedList;

public class Question84 {

    /**
     * method  with stack
     */
    public int largestRectangleArea_with_stack_method(int[] heights) {
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

    public int largestRectangleArea_dp(int[] heights) {
        int len = heights.length, maxArea = 0, tmp;
        if (len == 0) {
            return maxArea;
        }
        int[] left_i = new int[len], right_i = new int[len];

        left_i[0] = -1;
        for (int i = 1; i < len; i++) {
            tmp = i - 1;
            while (tmp >= 0 && heights[tmp] >= heights[i]) {
                tmp = left_i[tmp];
            }
            left_i[i] = tmp;
        }
        right_i[len - 1] = len;
        for (int i = len - 2; i >= 0; i--) {
            tmp = i + 1;
            while (tmp < len && heights[tmp] >= heights[i]) {
                tmp = right_i[tmp];
            }
            right_i[i] = tmp;
        }

        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, (right_i[i] - left_i[i] - 1) * heights[i]);
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        if(heights.length == 0){
            return 0;
        }
        return this.sub_largestRectangleArea_divide(heights, 0, heights.length - 1);
    }

    public int sub_largestRectangleArea_divide(int[] heights, int i, int j) {
        if (i > j) {
            return 0;
        }
        //use segmentTree to opti, link https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28941/segment-tree-solution-just-another-idea-onlogn-solution
        int min = i;
        for (int k = i + 1; k <=j ;k++){
            if(heights[k] < heights[min]){
                min = k;
            }
        }

        int left = sub_largestRectangleArea_divide(heights, i, min - 1);
        int right = sub_largestRectangleArea_divide(heights, min + 1, j);

        return Math.max(heights[min] * (j - i + 1), Math.max(left, right));
    }
}
