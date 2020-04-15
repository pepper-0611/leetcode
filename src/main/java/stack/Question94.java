package stack;

import java.util.LinkedList;
import java.util.List;
import stack.Question173.TreeNode;

public class Question94 {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inorderTraversal_recursive(root, res);
        return res;
    }




    public void inorderTraversal_recursive(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inorderTraversal_recursive(node.left, res);
        }
        res.add(node.val);
        if (node.right != null) {
            inorderTraversal_recursive(node.right, res);
        }
    }
}
