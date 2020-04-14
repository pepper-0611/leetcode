package stack;

import java.util.LinkedList;

public class Question155 {

    class MinStack {

        LinkedList<Integer> dataStack;
        LinkedList<Integer> minStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            dataStack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int x) {
            dataStack.push(x);
            if (minStack.isEmpty() || x < minStack.peek()) {
                minStack.push(x);
            }
        }

        public void pop() {
            if (dataStack.isEmpty()) {
                return;
            }
            if (dataStack.size() == minStack.size()) {
                minStack.pop();
            }
            dataStack.pop();
        }

        public int top() {
            return dataStack.isEmpty() ? -1 : dataStack.peek();
        }

        public int getMin() {
            return minStack.isEmpty() ? -1 : minStack.peek();
        }
    }
}
