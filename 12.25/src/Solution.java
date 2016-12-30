import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by zouxuan on 12/25/16.
 */

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int row=matrix.length;
        int column=matrix[0].length;
        int result=Integer.MIN_VALUE;
        for(int left=0;left<column;left++){
            int[] sum=new int[row];
            for(int right=left;right<column;right++){
                for(int i=0;i<row;i++){
                    sum[i]+=matrix[i][right];
                }
                TreeSet<Integer> set=new TreeSet<>();
                set.add(0);
                int current_sum=0;
                int current_max=Integer.MIN_VALUE;
                for(int i=0;i<sum.length;i++){
                    current_sum+=sum[i];
                    try{
                        int prefix=set.ceiling(current_sum-k);
                        current_max=Math.max(current_max,current_sum-prefix);
                        set.add(current_sum);
                    }catch (Exception e){

                    }
                }
                result=Math.max(result,current_max);
            }
        }
        return result;
    }


    int result = 0;

    public int pathSum(TreeNode root, int sum) {
        if(root==null) return 0;
        subtree(root,sum);
        pathSum(root.left,sum);
        pathSum(root.right,sum);
        return result;
    }

    private void subtree(TreeNode root, int remain) {
        if (root == null) {
            return;
        }
        if (remain == root.val) result++;
        subtree(root.left,remain-root.val);
        subtree(root.right,remain-root.val);
    }

    public static void main(String[] args){
        Solution s=new Solution();
        s.maxSumSubmatrix()
    }

    public int closestValue(TreeNode root, double target) {
        int result=0;
        double diff=Double.MAX_VALUE;
        while(root!=null){
            if(Math.abs(target-root.val)<diff){
                result=root.val;
                diff=Math.abs(target-root.val);
            }
            if(target==root.val){
                return result;
            }
            if(target<root.val){
                root=root.left;
            }
            else{
                root=root.right;
            }
        }
        return result;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        getHeight(root,result);
        return result;
    }

    private int getHeight(TreeNode root,List<List<Integer>> result){
        if(root==null) return -1;
        int leftHeight=getHeight(root.left,result);
        int rightHeight=getHeight(root.right,result);
        int maxHeight=Math.max(leftHeight,rightHeight)+1;
        while(result.size()<maxHeight+1){
            result.add(new ArrayList<>());
        }
        result.get(maxHeight).add(root.val);
        return maxHeight;
    }
}
