package stack;

import java.util.LinkedList;

public class Question155_onestack {

    class MinStack {

        LinkedList<Integer> stack;
        int min;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new LinkedList<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if (stack.isEmpty()) {
                return;
            }
            if (stack.peek() == min) {
                stack.pop();
                min = stack.pop();
            }else {
                stack.pop();
            }
        }

        public int top() {
            return stack.isEmpty() ? -1 : stack.peek();
        }

        public int getMin() {
            return min;
        }
    }
}
