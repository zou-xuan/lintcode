import java.util.Iterator;
import java.util.List;
import java.util.Stack;

///
//   Created by zouxuan on 12/21/16.
//  /
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack=new Stack<>();
        for(int i=nestedList.size()-1;i>=0;i--){
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
       return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if(!stack.isEmpty()){
            NestedInteger n=stack.peek();
            if(n.isInteger()){
                return true;
            }else{
                while(!n.isInteger()){
                    stack.pop();
                    List<NestedInteger> list=n.getList();
                    for(int i=list.size()-1;i>=0;i--){
                        stack.push(list.get(i));
                    }
                    if(stack.isEmpty()){
                        return false;
                    }
                    n=stack.peek();
                }
                return true;
            }
        }
        else{
            return false;
        }
    }

    public boolean isPowerOfFour(int num) {
        return num>0&&((num&(num-1))==0)&&((num&0x55555555)!=0);
    }
}
