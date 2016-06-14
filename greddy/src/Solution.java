
import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zouxuan on 16/5/31.
 */
public class Solution {

    public int singleNumber(int[] A) {
        int result = 0;
        for (int p : A) {
            result ^= p;
        }
        return result;
    }

    public int majorityNumber(ArrayList<Integer> nums) {
        int count = 1;
        int major = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) == major) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    major = nums.get(i);
                    count = 1;
                }
            }
        }
        return major;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        int total = 0;
        int result = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (tank < 0) {
                result = i + 1;
                tank = 0;
            }
        }
        return total >= 0 ? result : -1;
    }

    public String largestNumber(int[] num) {
        Integer[] arrays = new Integer[num.length];
        for (int i = 0; i < num.length; i++) {
            arrays[i] = num[i];
        }
        Arrays.sort(arrays, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        String o1o2 = o1 + "" + o2;
                        String o2o1 = o2 + "" + o1;
                        return -o1o2.compareTo(o2o1);
                    }
                }
        );

        StringBuilder sb = new StringBuilder();
        for (int p : arrays) {
            sb.append(p);
        }
        String s = sb.toString();
        int index = 0;
        while (s.charAt(index) == '0' && index < s.length() - 1) {
            index++;
        }
        return s.substring(index);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums={1,3,2};
        s.nextPermutation(nums);
    }


    public String DeleteDigits(String A, int k) {

        int length = A.length();
        int left = length - k;
        StringBuilder sb = new StringBuilder();
        int leftBorder = 0;
        for (int i = left; i > 0; i--) {
            int rightBorder = length - i;
            int min = Integer.MAX_VALUE;

            for (int j = leftBorder; j <= rightBorder; j++) {
                int tmp = A.charAt(j) - '0';
                if (tmp < min) {
                    min = tmp;
                }
            }
            leftBorder = leftBorder+A.substring(leftBorder).indexOf(min + "") + 1;
            sb.append(min + "");

        }
        String result=sb.toString();
        int index=0;
        while(index<result.length()&&result.charAt(index)=='0'){
            index++;
        }
        return result.substring(index);
    }

    public int[] nextPermutation(int[] nums) {
        if(nums==null||nums.length<=1) return nums;
        int k=nums.length-2;
        while(k>=0&&nums[k]>=nums[k+1]){
            k--;
        }
        if(k==-1){
            return reverse(nums,0,nums.length-1);
        }
        int l=nums.length-1;
        while(nums[l]<=nums[k]&&l>k){
            l--;
        }
        int tmp=nums[k];
        nums[k]=nums[l];
        nums[l]=tmp;
        int[] reverse=reverse(nums,k+1,nums.length-1);
        for(int i=k+1;i<=nums.length-1;i++){
            nums[i]=reverse[i-k-1];
        }
        return nums;
    }

    public int[] reverse(int[] nums,int start,int end){
        int[] reverse=new int[end-start+1];
        for(int i=end;i>=start;i--){
            reverse[end-i]=nums[i];
        }
        return reverse;
    }
}
