import java.util.Stack;

/**
 * Created by zouxuan on 16/6/14.
 */
public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack=new Stack<>();
        minStack =new Stack<>();
    }

    public void push(int number) {
        stack.push(number);
        if(minStack.empty()||number<= minStack.peek()){
            minStack.push(number);
        }
    }

    public int pop() {

        if(minStack.peek().equals(stack.peek())){
            minStack.pop();
        }
        return stack.pop();
    }

    public int min() {
        return minStack.peek();
    }


}
