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
        LinkedList<Integer> res = new LinkedList<>();
        TreeNode cur = root;

        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode pre = findInOrderTraversalPre(cur);
                //visited
                if (pre.right == cur) {
                    pre.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                } else {
                    pre.right = cur;
                    cur = cur.left;
                }
            }
        }
        return res;
    }

    private TreeNode findInOrderTraversalPre(TreeNode cur) {
        TreeNode pre = cur.left;//not null always
        while (pre.right != null && pre.right != cur) {
            pre = pre.right;
        }
        return pre;
    }


    public List<Integer> inorderTraversal_stack(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur == null) {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
                continue;
            }

            if (cur.left != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                res.add(cur.val);
                cur = cur.right;
            }
        }
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
