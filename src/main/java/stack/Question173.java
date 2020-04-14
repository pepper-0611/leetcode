package stack;

import java.util.LinkedList;
import javax.swing.tree.TreeNode;

public class Question173 {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class BSTIterator {

        private LinkedList<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new LinkedList<>();
            midTravel(root);
        }

        void midTravel(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode cur = stack.pop();
            midTravel(cur.right);
            return cur.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
}
