package stack;

import java.util.LinkedList;

public class Question155_definestack {

    class MinStack {

        class Node {

            int val;
            int min;
            Node next;

            Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }

            Node() {

            }
        }

        Node head;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            head = new Node();
        }

        public void push(int x) {
            int min = x;
            if (head.next != null && head.next.min < x) {
                min = head.next.min;
            }
            head.next = new Node(x, min, head.next);
        }

        public void pop() {
            if (head.next != null) {
                head.next = head.next.next;
            }
        }

        public int top() {
            if (head.next != null) {
                return head.next.val;
            }
            return -1;
        }

        public int getMin() {
            if (head.next != null) {
                return head.next.min;
            }
            return -1;
        }
    }

}
