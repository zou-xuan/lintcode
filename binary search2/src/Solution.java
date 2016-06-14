import java.util.ArrayList;

/**
 * Created by zouxuan on 16/6/1.
 */
public class Solution {
    public int closestNumber(int[] A, int target) {
        if (A == null || A.length == 0) return 0;
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] - target > 0) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return Math.abs(A[start] - target) > Math.abs(A[end] - target) ? end : start;
    }

    public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }

    public int[] kClosestNumbers(int[] A, int target, int k) {
        int[] result = new int[k];
        if (A == null || A.length == 0) return result;
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] - target >= 0) {
                end = mid;
            } else {
                start = mid;
            }
        }
        int location = Math.abs(A[start] - target) > Math.abs(A[end] - target) ? end : start;
        int index = 0;
        int left = location - 1;
        int right = location;
        while (right - left <= k) {
            int diffLeft = left >= 0 ? Math.abs(A[left] - target) : Integer.MAX_VALUE;
            int diffRight = right < A.length ? Math.abs(A[right] - target) : Integer.MAX_VALUE;
            if (diffLeft <= diffRight) {
                result[index++] = A[left--];
            } else {
                result[index++] = A[right++];
            }
        }
        return result;
    }

    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            if (num[i] < min) {
                min = num[i];
            }
        }
        return min;
    }

    public boolean search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) return false;
        for (int p : A) {
            if (p == target) {
                return true;
            }
        }
        return false;
    }

    public int searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int x = n - 1;
        int y = 0;
        int count = 0;
        while (x >= 0 && y < m) {
            if (matrix[x][y] > target) {
                x--;
            } else if (matrix[x][y] < target) {
                y++;
            } else {
                count++;
                x--;
                y++;
            }
        }
        return count;
    }

    class ParentTreeNode {
        public ParentTreeNode parent, left, right;
    }

    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root,
                                                 ParentTreeNode A,
                                                 ParentTreeNode B) {
        ArrayList<ParentTreeNode> AtoRoot = getPathToRoot(A);
        ArrayList<ParentTreeNode> BtoRoot = getPathToRoot(B);
        int Aindex = AtoRoot.size() - 1;
        int Bindex = BtoRoot.size() - 1;
        while (Aindex >= 0 && Bindex >= 0) {
            if (AtoRoot.get(Aindex) != BtoRoot.get(Bindex)) {
                return AtoRoot.get(Aindex + 1);
            }
            Aindex--;
            Bindex--;
        }
        return AtoRoot.get(Aindex + 1);
    }

    private ArrayList<ParentTreeNode> getPathToRoot(ParentTreeNode p) {
        ArrayList<ParentTreeNode> result = new ArrayList<>();
        while (p != null) {
            result.add(p);
            p = p.parent;
        }
        return result;
    }

    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public boolean isTweakedIdentical(TreeNode a, TreeNode b) {
        if(a==null&&b==null) return true;
        if(a==null&&b!=null) return false;
        if(a!=null&&b==null) return false;
        if(a.val!=b.val) return false;
        boolean identical=isTweakedIdentical(a.left,b.left)&&isTweakedIdentical(a.right,b.right);
        boolean tweaked=isTweakedIdentical(a.right,b.left)&&isTweakedIdentical(a.left,b.right);
        if(identical||tweaked) return true;
        else return false;

    }

}
