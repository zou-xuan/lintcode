import java.util.ArrayList;

/**
 * Created by zouxuan on 7/30/16.
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
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        if (root == null) return new ArrayList<Integer>();
        ArrayList<Integer> result = new ArrayList<>();
        if (root.val < k1) {
            result = searchRange(root.right, k1, k2);
        } else if (root.val > k2) {
            result = searchRange(root.left, k1, k2);
        } else {
            ArrayList<Integer> left = searchRange(root.left, k1, k2);
            ArrayList<Integer> right = searchRange(root.right, k1, k2);
            if(left.size()!=0) result.addAll(left);
            result.add(root.val);
            if(right.size()!=0) result.addAll(right);
        }
        return result;
    }
}
