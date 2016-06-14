import java.util.Stack;

/**
 * Created by zouxuan on 16/5/26.
 */

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Solution {
    private Stack<TreeNode> nodes=new Stack<>();
    private TreeNode current;

    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        nodes.push(root);
        current=root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        return !nodes.isEmpty();
    }

    //@return: return next node
    public TreeNode next() {

        while(current.left!=null){
            current=current.left;
            nodes.push(current);
        }
        TreeNode result=nodes.pop();
        current=current.right;

    }

}
