package knowledge;

import javax.swing.text.Segment;

public class SegmentTree {

    private int start;
    private int end;
    private int min;
    private SegmentTree left;
    private SegmentTree right;

    private SegmentTree(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static SegmentTree buildSegmentTree(int[] heights, int i, int j) {
        if (i > j) {
            return null;
        }
        SegmentTree root = new SegmentTree(i, j);
        if (i == j) {
            root.min = i;
            return root;
        }
        int mid = (i + j) / 2;
        root.left = buildSegmentTree(heights, i, mid);
        root.right = buildSegmentTree(heights, mid + 1, j);
        root.min =
                heights[root.right.min] < heights[root.left.min] ? root.right.min : root.left.min;
        return root;
    }

    public static int query(SegmentTree tree, int[] height, int i, int j) {
        if (tree == null || tree.end < i || tree.start > j) {
            return -1;
        }
        if (i <= tree.start && j >= tree.end) {
            return tree.min;
        }

        int leftMin = query(tree.left, height, i, j);
        int rightMin = query(tree.right, height, i, j);
        if (leftMin == -1) {
            return rightMin;
        }
        if (rightMin == -1) {
            return leftMin;
        }
        return height[leftMin] < height[rightMin] ? leftMin : rightMin;
    }

}
