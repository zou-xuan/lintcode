import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zouxuan on 7/9/16.
 */
public class Solution {
    public int minSubArray(ArrayList<Integer> nums) {
        int sum = 0, minResult = Integer.MAX_VALUE, maxSubarray = 0;
        for (Integer i : nums) {
            sum += i;
            minResult = Math.min(minResult, sum - maxSubarray);
            maxSubarray = Math.max(maxSubarray, sum);
        }
        return minResult;
    }

    public int[] mergeSortedArray(int[] A, int[] B) {
        int[] result = new int[A.length + B.length];
        int indexA = 0;
        int indexB = 0;
        int indexResult = 0;
        while (indexA < A.length && indexB < B.length) {
            if (A[indexA] < B[indexB]) {
                result[indexResult] = A[indexA];
                indexA++;
            } else {
                result[indexResult] = B[indexB];
                indexB++;
            }


            indexResult++;
        }
        while (indexA < A.length) {
            result[indexResult] = A[indexA];
            indexResult++;
            indexA++;
        }
        while (indexB < B.length) {
            result[indexResult] = B[indexB];
            indexResult++;
            indexB++;
        }
        return result;
    }

    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE, minSub_positive = 1,
                minSub_negative = 1, product = 1;
        for (int n : nums) {
            product *= n;
            if (product > 0) {
                result = Math.max(result, product / minSub_positive);
                minSub_positive = Math.min(minSub_positive, product);
            } else if (product < 0) {
                result = Math.max(result, product / minSub_negative);
                if (minSub_negative > 0) {
                    minSub_negative = product;
                } else {
                    minSub_negative = Math.max(minSub_negative, product);
                }
            } else {
                if (result < 0) result = 0;
                minSub_positive = 1;
                minSub_negative = 1;
                product = 1;
            }
        }
        return result;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int result = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            result = Math.max(result, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return result;
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        int min = prices[0];
        left[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        int max = prices[prices.length - 1];
        right[prices.length - 1] = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }
        int result = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            result = Math.max(result, left[i] + right[i]);
        }
        return result;

    }

    public void sortColors2(int[] colors, int k) {
        int start = 0;
        int end = colors.length - 1;
        int count = 0;
        while (count < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = start; i < end; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            int left = start;
            int right = end;
            int curt = left;
            while (curt <= right) {
                if (colors[curt] == min) {
                    swap(colors, left, curt);
                    left++;
                    curt++;
                } else if (colors[curt] > min && colors[curt] < max) {
                    curt++;
                } else {
                    swap(colors, curt, right);
                    right--;
                }
            }
            count += 2;
            start = left;
            end = right;
        }
    }

    private void swap(int[] colors, int left, int right) {
        int tmp = colors[left];
        colors[left] = colors[right];
        colors[right] = tmp;
    }

    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Arrays.sort(numbers);
        for (int first = 0; first <= numbers.length - 4; first++) {
            if (first != 0 && numbers[first] == numbers[first - 1]) {
                continue;
            } else {
                for (int second = first + 1; second <= numbers.length - 3; second++) {
                    if (second != first + 1 && numbers[second] == numbers[second - 1]) {
                        continue;
                    } else {
                        int third = second + 1;
                        int forth = numbers.length - 1;
                        while (third < forth) {
                            int tmp = numbers[first] + numbers[second] + numbers[third] + numbers[forth];
                            if (tmp == target) {
                                ArrayList<Integer> list = new ArrayList<>();
                                list.add(numbers[first]);
                                list.add(numbers[second]);
                                list.add(numbers[third]);
                                list.add(numbers[forth]);
                                result.add(list);
                                third++;
                                forth--;
                                while (third < forth && numbers[third] == numbers[third - 1]) third++;
                                while (third < forth && numbers[forth] == numbers[forth + 1]) forth--;
                            } else if (tmp < target) {
                                third++;
                            } else {
                                forth--;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public void sortLetters(char[] chars) {
        if(chars==null||chars.length<=1){
            return;
        }
        int left=0;
        int right=chars.length-1;
        while(left<=right){
            while(left<=right&&chars[left]>='a'&&chars[left]<='z') left++;
            while(left<=right&&chars[right]>='A'&&chars[right]<='Z') right--;
            if(left<=right){
                char tmp=chars[left];
                chars[left]=chars[right];
                chars[right]=tmp;
                left++;
                right--;
            }
        }
    }

    public int maxDiffSubArrays(int[] nums) {
        int[] leftMin=new int[nums.length];
        int[] leftMax=new int[nums.length];
        int[] rightMin=new int[nums.length];
        int[] rightMax=new int[nums.length];

        int minsum=0;
        int maxsum=0;
        int max=Integer.MIN_VALUE;
        int sum=0;

        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            max=Math.max(max,sum-minsum);
            minsum=Math.min(minsum,sum);
            leftMax[i]=max;
        }
    }

    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        int[] leftMax=new int[nums.size()];
        int[] rightMax=new int[nums.size()];

        int minsum=0;
        int max=Integer.MIN_VALUE;
        int sum=0;

        for(int i=0;i<nums.size();i++){
            sum+=nums.get(i);
            max=Math.max(max,sum-minsum);
            minsum=Math.min(minsum,sum);
            leftMax[i]=max;
        }

        minsum=0;
        max=Integer.MIN_VALUE;
        sum=0;
        for(int i=nums.size()-1;i>=0;i--){
            sum+=nums.get(i);
            max=Math.max(max,sum-minsum);
            minsum=Math.min(minsum,sum);
            rightMax[i]=max;
        }

        int result=Integer.MIN_VALUE;
        for(int i=0;i<nums.size()-1;i++){
            result=Math.max(result,leftMax[i]+rightMax[i+1]);
        }

        return result;

    }



}
