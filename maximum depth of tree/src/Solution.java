
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by zouxuan on 16/5/24.
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
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        int leftDepth=maxDepth(root.left);
        int rightDepth=maxDepth(root.right);
        return Math.max(leftDepth,rightDepth)+1;
    }

    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> result=new ArrayList<>();
        if(root==null) return result;
        stack.push(root);
        while(!stack.empty()){
            TreeNode p=stack.pop();
            result.add(p.val);
            if(p.right!=null){
                stack.push(p.right);
            }
            if(p.left!=null){
                stack.push(p.left);
            }

        }
        return result;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor=null;
        while(root!=p&&root!=null){
            if(root.val>p.val){
                successor=root;
                root=root.left;
            }
            else root=root.right;
        }
        if(root.right==null){
            return successor;
        }
        root=root.right;
        while(root.left!=null){
            root=root.left;
        }
        return root;

    }

    class ReturnType{
        int maxValue;
        int minValue;
        boolean isBST;
        public ReturnType(){

        }

        public ReturnType(int maxValue,int minValue,boolean isBST){
            this.maxValue=maxValue;
            this.minValue=minValue;
            this.isBST=isBST;
        }
    }


    public boolean isValidBST(TreeNode root) {
        if(root==null) return false;
        ReturnType result = getdetail(root);
        return result.isBST;
    }

    public ReturnType getdetail(TreeNode root){
        if(root==null) return new ReturnType(0,0,false);
        ReturnType result=new ReturnType();
        if(root.left!=null){
            ReturnType leftResult=getdetail(root.left);
            if(!leftResult.isBST||leftResult.maxValue>=root.val){
                result.isBST=false;
                return result;
            }
            else{
                result.minValue=leftResult.minValue;
            }
        }
        else{
            result.minValue=root.val;
        }
        if(root.right!=null){
            ReturnType rightResult=getdetail(root.right);
            if(!rightResult.isBST||rightResult.minValue<=root.val){
                result.isBST=false;
                return result;
            }
            else{
                result.maxValue=rightResult.maxValue;
            }
        }
        else{
            result.maxValue=root.val;
        }


        result.isBST=true;
        return result;

    }

}
