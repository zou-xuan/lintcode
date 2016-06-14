import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

    public boolean isBalanced(TreeNode root) {
        return getHeight(root)!=-1;
    }

    public int getHeight(TreeNode root){
        if(root==null) return 0;
        int leftHeight=getHeight(root.left);
        int rightHeight=getHeight(root.right);
        if(leftHeight==-1||rightHeight==-1) return -1;
        int difference=Math.abs(leftHeight-rightHeight);
        if(difference>1) return -1;
        return Math.max(leftHeight,rightHeight)+1;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if(root==null||root==A||root==B){
             return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,A,B);
        TreeNode right=lowestCommonAncestor(root.right,A,B);
        if(left!=null&&right!=null) return root;
        if(left!=null) return left;
        if(right!=null) return right;
        return null;
    }

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        if(root==null) return result;
        queue.add(root);
        while(!queue.isEmpty()){
            ArrayList<Integer> list=new ArrayList<>();
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode p=queue.poll();
                list.add(p.val);
                if(p.left!=null) queue.add(p.left);
                if(p.right!=null) queue.add(p.right);
            }
            result.add(list);
        }
        return result;

    }


}
