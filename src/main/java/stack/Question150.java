package stack;

import java.util.LinkedList;

public class Question150 {

    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            int calRes;
            switch (token) {
                case "+":
                    calRes = stack.pop() + stack.pop();
                    break;
                case "-":
                    calRes = -stack.pop() + stack.pop();
                    break;
                case "*":
                    calRes = stack.pop() * stack.pop();
                    break;
                case "/":
                    int second = stack.pop();
                    calRes = stack.pop() / second;
                    break;
                default:
                    calRes = Integer.parseInt(token);
            }
            stack.push(calRes);
        }
        return stack.isEmpty() ? 0 : stack.pop();
    }
}
