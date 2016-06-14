import sun.awt.image.IntegerInterleavedRaster;

import java.util.Stack;

/**
 * Created by zouxuan on 16/6/14.
 */
public class Queue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public Queue() {
        stack1 = new Stack<>();
        stack2=new Stack<>();
    }

    public void push(int element) {
        stack1.push(element);
    }

    public int pop() {
        if(!stack2.empty()){
            return stack2.pop();
        }else{
            pushStack1toStack2(stack1,stack2);
            return stack2.pop();
        }
    }

    public int top() {
        if(!stack2.empty()){
            return stack2.peek();
        }
        else{
            pushStack1toStack2(stack1,stack2);
            return stack2.peek();
        }
    }

    private void pushStack1toStack2(Stack<Integer> stack1,Stack<Integer> stack2){
        while(!stack1.empty()){
            Integer tmp=stack1.pop();
            stack2.push(tmp);
        }
    }
}
