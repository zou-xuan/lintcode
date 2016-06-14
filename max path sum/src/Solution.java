import apple.laf.JRSUIUtils;

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

private class Result{
    int singlePath;
    int maxPath;
    public Result(int singlePath,int maxPath){
        this.singlePath=singlePath;
        this.maxPath=maxPath;
    }
}

public class Solution {
    public int maxPathSum2(TreeNode root) {
        if(root==null){
            return Integer.MIN_VALUE;
        }
        int left=maxPathSum2(root.left);
        int right=maxPathSum2(root.right);
        return root.val+Math.max(0,Math.max(left,right));
    }

    public int maxPathSum(TreeNode root) {
        Result result=helper(root);
        return result.maxPath;
    }

    private Result helper(TreeNode root){
        if(root==null){
            return new Result(0,Integer.MIN_VALUE);
        }
        Result left=helper(root.left);
        Result right=helper(root.right);
        int singlePath=Math.max(left.singlePath,right.singlePath)+root.val;
        singlePath=Math.max(singlePath,0);
        int maxPath=Math.max(left.maxPath,right.maxPath);
        maxPath=Math.max(maxPath,left.singlePath+right.singlePath+root.val);
        return new Result(singlePath,maxPath);

    }
}

