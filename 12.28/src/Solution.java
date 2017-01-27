import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result=new ArrayList<>();
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return result;
        }
        int row=matrix.length;
        int column=matrix[0].length;
        boolean[][] pacific=new boolean[row][column];
        boolean[][] atlantic=new boolean[row][column];
        for(int i=0;i<column;i++){
            visited(pacific,matrix,0,i);
        }
        for(int i=0;i<row;i++){
            visited(pacific,matrix,i,0);
        }
        for(int i=0;i<column;i++) {
            visited(atlantic, matrix, row - 1, i);
        }
        for(int i=0;i<row;i++){
            visited(atlantic,matrix,i,column-1);
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(pacific[i][j]&&atlantic[i][j]){
                    result.add(new int[]{i,j});
                }
            }
        }
        return result;
    }

    private void visited(boolean[][] ocean,int[][] matrix,int i,int j){
        if(i<0||i>=ocean.length||j<0||j>=ocean[0].length){
            return;
        }
        int[] a=new int[]{0,0,-1,1};
        int[] b=new int[]{-1,1,0,0};
        if(ocean[i][j]){
            return;
        }
        ocean[i][j]=true;
        for(int index=0;index<4;index++){
            int newi=i+a[index];
            int newj=j+b[index];
            if(newi>=0&&newi<ocean.length&&newj>=0&&newj<ocean[0].length&&matrix[newi][newj]>=matrix[i][j]){
                visited(ocean,matrix,newi,newj);
            }
        }
    }
}
