import java.util.*;

/**
 * Created by zouxuan on 7/5/16.
 */

class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Solution {

    public boolean isIdentical(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a != null && b != null) {
            if (a.val != b.val) return false;
            return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
        } else {
            return false;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left != null && right != null) {
            return left.val == right.val && isSymmetric(left.right, right.left) &&
                    isSymmetric(left.left, right.right);
        }
        return false;
    }

    public boolean isComplete(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.left != null && p.right != null) {
                queue.offer(p.left);
                queue.offer(p.right);
            }
            else if(p.left==null&&p.right!=null) return false;
            else if(p.left!=null&&p.right==null) {
                queue.offer(p.left);
                break;
            }
            else{
                break;
            }
        }
        while(!queue.isEmpty()){
            TreeNode p=queue.poll();
            if(p.left!=null||p.right!=null) return false;
        }
        return true;
    }

    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        if(root.left==null) return minDepth(root.right)+1;
        if(root.right==null) return minDepth(root.left)+1;
        int left=minDepth(root.left);
        int right=minDepth(root.right);
        return Math.min(left,right)+1;
    }

    public TreeNode insertNode(TreeNode root, TreeNode node) {
        if(root==null){
            return node;
        }
        if(root.val>node.val){
            if(root.left==null){
                root.left=node;
            }
            else{
                insertNode(root.left,node);
            }
        }
        else{
            if(root.right==null){
                root.right=node;
            }
            else{
                insertNode(root.right,node);
            }
        }
        return root;
    }

    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        if(root==null) return result;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode p=stack.pop();
            result.add(p.val);
            if(p.left!=null) stack.push(p.left);
            if(p.right!=null) stack.push(p.right);
        }
        Collections.reverse(result);
        return result;
    }

    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        ArrayList<Integer> result=new ArrayList<>();
        if(root==null) return result;
        TreeNode p=root;
        while(!stack.isEmpty()||p!=null){
            if(p!=null){
                stack.push(p);
                p=p.left;
            }else{
                p=stack.pop();
                result.add(p.val);
                p=p.right;
            }
        }
        return result;
    }
}
