package stack;

import java.util.LinkedList;
import java.util.List;
import stack.Question94.TreeNode;

public class Question144 {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        return preorderTraversal_stack(root);
    }




    public List<Integer> preorderTraversal_stack(TreeNode node) {
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode cur = node;
        while (cur != null || !stack.isEmpty()) {
            if(cur == null){
                cur = stack.pop();
            }
            res.add(cur.val);
            if (cur.left == null) {
                cur = cur.right;
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                cur = cur.left;
            }
        }
        return res;
    }

    public void preorderTraversal_recursive(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        res.add(node.val);
        if (node.left != null) {
            preorderTraversal_recursive(node.left, res);
        }
        if (node.right != null) {
            preorderTraversal_recursive(node.right, res);
        }
    }
}
