import java.util.ArrayList;

/**
 * Created by zouxuan on 11/21/16.
 */
public class Solution {
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) return 0;
        int[][] score = new int[A.length][A.length];
        boolean[][] flag = new boolean[A.length][A.length];
        int[][] sum = new int[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            score[i][i] = 0;
            flag[i][i] = true;
            sum[i][i] = A[i];
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                sum[i][j] = sum[i][j - 1] + A[j];
            }
        }
        return searchStone(A, score, flag, sum, 0, A.length - 1);

    }

    private int searchStone(int[] A, int[][] score, boolean[][] flag, int[][] sum, int i, int j) {
        if (flag[i][j]) {
            return score[i][j];
        }
        int tmp = Integer.MAX_VALUE;
        for (int k = i + 1; k <= j; k++) {
            tmp = Math.min(tmp, searchStone(A, score, flag, sum, i, k - 1) + searchStone(A, score, flag, sum, k, j) + sum[i][j]);
        }
        score[i][j] = tmp;
        flag[i][j] = true;
        return tmp;
    }

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[][] coins = new int[nums.length][nums.length];
        boolean[][] flag = new boolean[nums.length][nums.length];
        return searchBalloon(nums, coins, flag, 0, nums.length - 1);
    }

    private int searchBalloon(int[] nums, int[][] coins, boolean[][] flag, int i, int j) {
        if (i > j || j <= -1 || i >= nums.length) {
            return 0;
        }
        if (flag[i][j]) {
            return coins[i][j];
        }
        int tmp = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            int left = i - 1 >= 0 ? nums[i - 1] : 1;
            int right = j + 1 < nums.length ? nums[j + 1] : 1;
            int sum = searchBalloon(nums, coins, flag, i, k - 1) + searchBalloon(nums, coins, flag, k + 1, j) + nums[k] * left * right;
            tmp = Math.max(tmp, sum);
        }
        coins[i][j] = tmp;
        flag[i][j] = true;
        return tmp;
    }

    public int backPack(int m, int[] A) {
        int[][] result = new int[A.length + 1][m + 1];
        for (int i = 0; i <= A.length; i++) {
            result[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            result[0][i] = 0;
        }
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (j - A[i - 1] < 0) {
                    result[i][j] = result[i - 1][j];
                } else {
                    int tmp = j - A[i - 1] >= 0 ? j - A[i - 1] : 0;
                    result[i][j] = Math.max(result[i - 1][j], result[i - 1][tmp] + A[i - 1]);
                }
            }
        }
        return result[A.length][m];
    }

    public int backPackIII(int m, int[] A, int V[]) {
        int[][] result = new int[A.length + 1][m + 1];
        for (int i = 0; i <= A.length; i++) {
            result[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            result[0][i] = 0;
        }
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (j - A[i - 1] < 0) {
                    result[i][j] = result[i - 1][j];
                } else {
                    int tmp = j - A[i - 1] >= 0 ? j - A[i - 1] : 0;
                    result[i][j] = Math.max(result[i - 1][j], result[i][tmp] + V[i - 1]);
                }
            }
        }
        return result[A.length][m];
    }

    public int backPackIV(int[] A, int m) {
        int[][] result = new int[A.length + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            result[0][i] = 0;
        }
        for (int i = 0; i <= A.length; i++) {
            result[i][0] = 1;
        }

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= m; j++) {
                if (j - A[i - 1] < 0) {
                    result[i][j] = result[i - 1][j];
                } else {
                    int tmp = j - A[i - 1] >= 0 ? j - A[i - 1] : 0;
                    result[i][j] = result[i - 1][j] + result[i][tmp];
                }

            }
        }
        return result[A.length][m];
    }

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.length() == 1 && s2.length() == 1) return s1.charAt(0) == s2.charAt(0);
        for (int i = 1; i < s1.length(); i++) {
            boolean tmp1 = isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i));
            if (tmp1) {
                return true;
            }
        }
        for (int i = 1; i < s1.length(); i++) {
            boolean tmp2 = isScramble(s1.substring(0, i), s2.substring(s1.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s1.length() - i));
            if (tmp2) {
                return true;
            }
        }
        return false;
    }

    public boolean firstWillWin(int[] values) {
        if(values.length<=2) return true;
        int[] dp = new int[values.length + 1];
        boolean[] flag = new boolean[values.length + 1];
        dp[0] = 0;
        dp[1] = values[values.length - 1];
        dp[2] = values[values.length - 1] + values[values.length - 2];
        flag[0] = flag[1] = flag[2] = true;
        int value= getMaxValue(values,dp,flag,values.length);
        int sum=0;
        for(int n:values){
            sum+=n;
        }
        if(value>sum*1.0/2) return true;
        else return false;
    }

    private int getMaxValue(int[] values, int[] dp, boolean[] flag, int n) {
        if(n<0) return 0;
        if (flag[n]) {
            return dp[n];
        }
        int length=values.length;
        dp[n] = Math.max(values[length-n]+Math.min(getMaxValue(values,dp,flag,n-2),getMaxValue(values,dp,flag,n-3)),
                values[length-n]+values[length-n+1]+Math.min(getMaxValue(values,dp,flag,n-3),getMaxValue(values,dp,flag,n-4)));
        flag[n]=true;
        return dp[n];
    }

    public boolean firstWillWinIII(int[] values) {
        if(values.length<=2) return true;
        int[][] dp=new int[values.length][values.length];
        boolean[][] flag=new boolean[values.length][values.length];
        int value=getMaxValueIII(values,dp,flag,0,values.length-1);
        int sum=0;
        for(int n:values){
            sum+=n;
        }
        if(value>sum*1.0/2) return true;
        else return false;
    }

    private int getMaxValueIII(int[] values,int[][] dp,boolean[][] flag,int start,int end){
        if(start>end) return 0;
        if(end==start){
            return values[start];
        }
        if(end-start==1){
            return values[start]+values[end];
        }
        if(flag[start][end]){
            return dp[start][end];
        }
        dp[start][end]=Math.max(values[start]+Math.min(getMaxValueIII(values,dp,flag,start+2,end),getMaxValueIII(values,dp,flag,start+1,end-1)),
                values[end]+Math.min(getMaxValueIII(values,dp,flag,start+1,end-1),getMaxValueIII(values,dp,flag,start,end-2)));
        flag[start][end]=true;
        return dp[start][end];
    }


}
