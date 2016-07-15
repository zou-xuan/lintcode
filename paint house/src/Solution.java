/**
 * Created by zouxuan on 7/15/16.
 */
public class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int[][] result = new int[costs.length][costs[0].length];
        result[0][0] = costs[0][0];
        result[0][1] = costs[0][1];
        result[0][2] = costs[0][2];
        for (int i = 1; i < costs.length; i++) {
            result[i][0] = Math.min(result[i - 1][1] + costs[i][0], result[i - 1][2] + costs[i][0]);
            result[i][1] = Math.min(result[i - 1][0] + costs[i][1], result[i - 1][2] + costs[i][1]);
            result[i][2] = Math.min(result[i - 1][0] + costs[i][2], result[i - 1][1] + costs[i][2]);
        }
        return Math.min(result[costs.length - 1][0], Math.min(result[costs.length - 1][1],
                result[costs.length - 1][2]));
    }

    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        int[][] result = new int[n][k];
        for (int i = 0; i < k; i++) {
            result[0][i] = costs[0][i];
        }
        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                for (int s = 0; s < k; s++) {
                    if (s != j){
                        int tmp=result[i-1][s]+costs[i][j];
                        if(tmp<min){
                            min=tmp;
                        }
                    }
                }
                result[i][j]=min;
            }
        }
        int minResult=Integer.MAX_VALUE;
        for(int i=0;i<k;i++){
            if(result[n-1][i]<minResult){
                minResult=result[n-1][i];
            }
        }
        return minResult;
    }
}
