import java.util.*;

/**
 * Created by zouxuan on 12/26/16.
 */
public class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0) return true;
        int maxSum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (maxSum < desiredTotal) return false;
        HashMap<String, Boolean> map = new HashMap<>();
        return canWin(desiredTotal, new boolean[maxChoosableInteger + 1], map);
    }

    private boolean canWin(int total, boolean[] state, HashMap<String, Boolean> map) {
        if (total <= 0) return false;
        String s = Arrays.toString(state);
        if (map.containsKey(s)) return map.get(s);
        for (int i = 1; i < state.length; i++) {
            if (!state[i]) {
                state[i] = true;
                if (total - i <= 0 || !canWin(total - i, state, map)) {
                    map.put(s, true);
                    state[i] = false;
                    return true;
                }
                state[i] = false;
            }
        }
        map.put(s, false);
        return false;
    }


    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] prev = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            prev[i] = i;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }
        int maxLength = 1;
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }
        while (prev[maxIndex] != maxIndex) {
            result.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];
        }
        result.add(nums[maxIndex]);
        Collections.reverse(result);
        return result;
    }

    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length];
        for (int i = 0; i < updates.length; i++) {
            result[updates[i][0]] += updates[i][2];
            if (updates[i][1] + 1 < length) {
                result[updates[i][1] + 1] -= updates[i][2];
            }
        }
        int prefix = 0;
        for (int i = 0; i < length; i++) {
            prefix += result[i];
            result[i] = prefix;
        }
        return result;
    }

    public boolean isPerfectSquare(int num) {
        return true;
    }

    public boolean canPartitionII(int[] nums) {
        if (nums == null || nums.length < 2) return false;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) return false;
        sum /= 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] |= dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][sum];
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) return false;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] |= dp[j - nums[j]];
            }
        }
        return dp[sum];
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1, 2, 5};
        s.canPartition(nums);
    }

}
