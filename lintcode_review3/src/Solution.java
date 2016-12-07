import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by zouxuan on 11/24/16.
 */
public class Solution {
    public int trapRainWater(int[] heights) {
        int a = 0;
        int b = heights.length - 1;
        int water = 0;
        int leftMax = 0;
        int rightMax = 0;
        while (a <= b) {
            leftMax = Math.max(leftMax, heights[a]);
            rightMax = Math.max(rightMax, heights[b]);
            if (leftMax < rightMax) {
                water += leftMax - heights[a];
                a++;
            } else {
                water += rightMax - heights[b];
                b--;
            }
        }
        return water;
    }

    static int count(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                result += subCount(s, i, i + 1);
            }
        }
        return result;
    }

    static int subCount(String s, int left, int right) {
        int count = 0;
        int i = left;
        int j = right;
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(left) && s.charAt(j) == s.charAt(right)) {
                i--;
                j++;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static boolean canDivideTwoPart(int[] num) {
        if (num == null || num.length <= 1) return false;
        int sum = 0;
        for (int i : num) {
            sum += i;
        }
        if (sum % 2 != 0) return false;
        sum /= 2;
        boolean[][] dp = new boolean[num.length + 1][sum + 1];
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = false;
        }
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j - num[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num[i - 1]];
                }
            }
        }
        return dp[num.length][sum];
    }

    public int backPack(int m, int[] A) {
        int[] dp = new int[m + 1];
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                dp[j] = Math.max(dp[j], A[i] + dp[j - A[i]]);
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        int[] num = new int[]{1, 11, 5, 5};
        System.out.print(canDivideTwoPart(num));
    }
}
