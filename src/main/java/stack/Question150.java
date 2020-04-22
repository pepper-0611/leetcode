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


    public int evalRPN_define_stack(String[] tokens) {

        int[] stack = new int[tokens.length / 2 + 1];
        int index = 0;

        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack[index - 2] += stack[--index];
                    break;
                case "-":
                    stack[index - 2] -= stack[--index];
                    break;
                case "*":
                    stack[index - 2] *= stack[--index];
                    break;
                case "/":
                    stack[index - 2] /= stack[--index];
                    break;
                default:
                    stack[index++] = Integer.parseInt(token);
                    break;
            }

        }
        return stack[0];

    }
}
