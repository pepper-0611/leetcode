package stack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stack.Question144.TreeNode;

public class Question145 {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        return postorderTraversal_pre_method(root);
    }

    public List<Integer> postorderTraversal_pre_method(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        TreeNode cur = root, pre = null;
        while (cur != null) {
            while (cur.left != null) {
                stack.push(cur);
                cur = cur.left;
            }
            while (cur.right == null || cur.right == pre) {
                res.add(cur.val);
                pre = cur;
                if (stack.isEmpty()) {
                    return res;
                }
                cur = stack.pop();
            }
            stack.push(cur);
            cur = cur.right;
        }
        return res;
    }


    public List<Integer> postorderTraversal_visited_method(TreeNode root) {
        TreeNode cur = root;
        LinkedList<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        Set<TreeNode> visitedSet = new HashSet<>();

        boolean leftVisited, rightVisited;
        while (cur != null) {
            leftVisited = cur.left == null || visitedSet.contains(cur.left);
            //visit left
            if (!leftVisited) {
                stack.push(cur);
                cur = cur.left;
                continue;
            }
            rightVisited = cur.right == null || visitedSet.contains(cur.right);
            if (!rightVisited) {
                stack.push(cur);
                cur = cur.right;
                continue;
            }

            res.add(cur.val);
            visitedSet.add(cur);
            cur = stack.isEmpty() ? null : stack.pop();
        }
        return res;
    }
}
