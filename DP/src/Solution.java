import java.util.Arrays;

/**
 * Created by zouxuan on 16/5/26.
 */
public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] path = new int[m][n];
        for (int i = 0; i < n; i++) {
            path[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            path[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = path[i - 1][j] + path[i][j - 1];
            }
        }
        return path[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] path = new int[m][n];
        boolean rowFlag = false;
        boolean columnFlag = false;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1 || rowFlag) {
                path[0][i] = 0;
                rowFlag = true;
            } else {
                path[0][i] = 1;

            }
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1 || columnFlag) {
                path[i][0] = 0;
                columnFlag = true;
            } else {
                path[i][0] = 1;

            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    path[i][j] = path[i - 1][j] + path[i][j - 1];
                } else {
                    path[i][j] = 0;
                }
            }
        }
        return path[m - 1][n - 1];
    }

    public int climbStairs(int n) {
        if (n == 1) return 1;
        int[] plan = new int[n];
        plan[0] = 1;
        plan[1] = 2;
        for (int i = 2; i < n - 1; i++) {
            plan[i] = plan[i - 1] + plan[i - 2];
        }
        return plan[n - 1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] path = new int[m][n];
        path[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            path[0][i] = grid[0][i] + path[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            path[i][0] = grid[i][0] + path[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = Math.min(path[i - 1][j], path[i][j - 1]) + grid[i][j];
            }
        }
        return path[m - 1][n - 1];
    }

    public int minimumTotal(int[][] triangle) {
        int level = triangle.length;
        int length = triangle[level - 1].length;
        int[][] result = new int[level][length];
        result[0][0] = triangle[0][0];
        for (int i = 1; i < level; i++) {
            result[i][0] = result[i - 1][0] + triangle[i][0];
            result[i][triangle[i].length - 1] = result[i - 1][triangle[i - 1].length - 1]
                    + triangle[i][triangle[i].length - 1];
        }
        for (int i = 2; i < level; i++) {
            for (int j = 1; j < triangle[i].length - 1; j++) {
                result[i][j] = Math.min(result[i - 1][j - 1], result[i - 1][j]) + triangle[i][j];
            }
        }
        int minTotal = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            if (result[level - 1][i] < minTotal) {
                minTotal = result[level - 1][i];
            }
        }
        return minTotal;
    }

    public boolean canJump(int[] A) {
        boolean[] access = new boolean[A.length];
        access[0] = true;
        for (int i = 1; i < A.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (access[j] && A[j] >= i - j) {
                    access[i] = true;
                    break;
                }
            }
        }
        return access[A.length - 1];
    }

    public int jump(int[] A) {
        int[] step = new int[A.length];
        Arrays.fill(step, -1);
        step[0] = 0;
        for (int i = 1; i < A.length; i++) {
            int minStep = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (step[j] != -1 && A[j] >= i - j) {
                    int tmp = step[i] + 1;
                    if (tmp < minStep) {
                        minStep = tmp;
                    }
                }
            }
            if (minStep != Integer.MAX_VALUE) {
                step[i] = minStep;
            }
        }
        return step[A.length - 1];
    }

    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] step = new int[nums.length];
        int result=1;
        Arrays.fill(step, 1);
        for (int i = 1; i < nums.length; i++) {
            int maxStep = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i]) {
                    int tmp = step[j] + 1;
                    if (tmp > maxStep) {
                        maxStep = tmp;
                    }
                }
            }
            if (maxStep != Integer.MIN_VALUE) {
                step[i] = maxStep;
                if(maxStep>result){
                    result=maxStep;
                }
            }
        }
        return result;

    }
}
