import java.util.Stack;

/**
 * Created by zouxuan on 7/13/16.
 */

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class DoublyListNode {
    int val;
    DoublyListNode next, prev;

    DoublyListNode(int val) {
        this.val = val;
        this.next = this.prev = null;
    }
}

public class Solution {

    public DoublyListNode bstToDoublyList(TreeNode root) {
        DoublyListNode dummy=new DoublyListNode(-1);
        DoublyListNode curt=dummy;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode p=root;
        while(!stack.isEmpty()||p!=null){
            if(p!=null){
                stack.push(p);
                p=p.left;
            }else{
                p= stack.pop();
                DoublyListNode tmp=new DoublyListNode(p.val);
                curt.next=tmp;
                tmp.prev=curt;
                curt=tmp;
                p=p.right;
            }
        }
        return dummy.next;
    }
}
