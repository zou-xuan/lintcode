import java.util.Arrays;

/**
 * Created by zouxuan on 12/28/16.
 */
public class Solution {
    public int numSquares(int n) {
        int[] dp=new int[n+1];
        Arrays.fill(dp,-1);
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<dp.length;i++){
            int tmp=Integer.MAX_VALUE;
            for(int j=1;i-j*j>=1;j++){
                if(dp[i-j*j]>0){
                    tmp=Math.min(tmp,dp[i-j*j]+1);
                }
            }
            dp[i]=tmp;
        }
        return dp[n];
    }

    public int numSquaresii(int n) {
        // Write your code here
        int[] result = new int[n + 1];
        result[0]=0;
        for(int i=1;i<n+1;i++){
            int minSquare=Integer.MAX_VALUE;
            for(int j=1;j*j<=i;j++){
                minSquare=Math.min(minSquare,result[i-j*j]+1);
            }
            result[i]=minSquare;
        }
        return result[n];
    }

    public int coinChange(int[] coins, int amount) {
        if(coins==null||coins.length==0||amount<0) return -1;
        int[] dp=new int[amount+1];
        dp[0]=0;
        for(int i=1;i<dp.length;i++){
            int tmp=Integer.MAX_VALUE;
            for(int c:coins){
                if(i>=c&&dp[i-c]!=-1){
                    tmp=Math.min(tmp,dp[i-c]+1);
                }
            }
            if(tmp!=Integer.MAX_VALUE) dp[i]=tmp;
            else dp[i]=-1;
        }
        return dp[amount];
    }

    public static void main(String[] args){
        Solution s=new Solution();
        s.numSquaresii(2);
    }
}
