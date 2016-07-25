/**
 * Created by zouxuan on 7/24/16.
 */
public class Solution {

    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) return 0;
        int isIncrease = 0;
        int result = 1;
        int current = 1;
        int prev = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > prev && (isIncrease == 0 || isIncrease == 1)) {
                current++;
                isIncrease = 1;
            } else if (A[i] < prev && (isIncrease == 0 || isIncrease == -1)) {
                current++;
                isIncrease = -1;
            } else if (A[i] > prev) {
                current = 2;
                isIncrease = 1;
            } else if (A[i] < prev) {
                current = 2;
                isIncrease = -1;
            } else {
                current = 1;
                isIncrease = 0;
            }
            prev = A[i];
            result = Math.max(result, current);
        }
        return result;
    }

    public int numWays(int n, int k) {
        int[] result = new int[n];
        int[] dup = new int[n];
        result[0] = k;
        dup[0] = 0;
        for (int i = 1; i < n; i++) {
            result[i] = (result[i - 1] - dup[i - 1]) * k + dup[i - 1] * (k - 1);
            dup[i] = result[i - 1] - dup[i - 1];
        }
        return result[n - 1];
    }

    public int numSquares(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            int minSquare = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minSquare = Math.min(minSquare, result[i - j * j] + 1);
            }
            result[i] = minSquare;
        }
        return result[n];
    }

    public int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int result=0;
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] maxSquare = new int[row][column];
        for (int i = 0; i < column; i++) {
            maxSquare[0][i] = matrix[0][i];
            result=Math.max(result,maxSquare[0][i]);
        }
        for (int i = 0; i < row; i++) {
            maxSquare[i][0] = matrix[i][0];
            result=Math.max(result,maxSquare[i][0]);
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (matrix[i][j] == 1) {
                    maxSquare[i][j] = 1;
                    if (matrix[i - 1][j - 1] == 1) {
                        int length = maxSquare[i - 1][j - 1];
                        boolean canFill = true;
                        for (int up = 1; up <= length; up++) {
                            if (matrix[i - up][j] == 0) {
                                canFill = false;
                                break;
                            }
                        }
                        for (int left = 1; left <= length; left++) {
                            if (matrix[i][j - left] == 0) {
                                canFill = false;
                                break;
                            }
                        }
                        if(canFill){
                            maxSquare[i][j]=maxSquare[i-1][j-1]+1;
                        }
                    }
                    result=Math.max(result,maxSquare[i][j]);
                }
            }
        }
        return result*result;
    }

    public boolean firstWillWin(int[] values) {
        int len=values.length;
        if (len<2) {
            return true;
        }
        int[] dp=new int[len+1];
        dp[len]=0;
        dp[len-1]=values[len-1];
        dp[len-2]=values[len-2]+values[len-1];
        dp[len-3]=values[len-3]+values[len-2];
        for(int i=len-4;i>=0;i--){
            dp[i]=values[i]+Math.min(dp[i+2],dp[i+3]);
            dp[i]=Math.max(dp[i],Math.min(dp[i+3],dp[i+4])+values[i]+values[i+1]);
        }
        int sum=0;
        for(int tmp:values){
            sum+=tmp;
        }
        return dp[0]>sum-dp[0];
    }


}
