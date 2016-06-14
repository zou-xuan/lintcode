import java.util.*;

/**
 * Created by zouxuan on 16/6/10.
 */
public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> result = new HashSet<>();
        HashSet<Integer> hash = new HashSet<>();
        for (int n : nums1) {
            hash.add(n);
        }
        for (int n : nums2) {
            if (hash.contains(n) && !result.contains(n)) {
                result.add(n);
            }
        }
        int[] resultArray = new int[result.size()];
        int index = 0;
        for (Integer i : result) {
            resultArray[index++] = i;
        }
        return resultArray;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0, index2 = 0;
        HashSet<Integer> resultset = new HashSet<>();
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                resultset.add(nums1[index1]);
                index1++;
                index2++;
            }
        }
        int index = 0;
        int[] resultArray = new int[resultset.size()];
        for (Integer i : resultset) {
            resultArray[index++] = i;
        }
        return resultArray;
    }

    public int maxSubArray(int[] nums) {
        if (nums == null) return -1;
        if (nums.length == 1) return nums[0];
        int minSum = 0;
        int max = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null) return -1;
        if (nums.length == 1) return nums[0];
        int max = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }
        return max;
    }

    public int twoSumCloset(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int minDiff = Integer.MAX_VALUE;
        while (left < right) {
            int sum = nums[left] + nums[right];
            minDiff = Math.min(Math.abs(sum - target), minDiff);
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return 0;
            }
        }
        return minDiff;
    }

    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, left, i);
                left++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, right);
                right--;
            }
        }

    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    class Pair {
        int index;
        int prefix;
        public Pair(int index, int prefix) {
            this.index = index;
            this.prefix = prefix;
        }
    }

    public int[] subarraySumClosest(int[] nums) {
        ArrayList<Pair> pairList = new ArrayList<>();
        int[] result=new int[2];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            Pair p=new Pair(i,sum);
            pairList.add(p);
        }
        Collections.sort(pairList, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
               return o1.prefix-o2.prefix;
            }
        });
        int minDiff=Integer.MAX_VALUE;
        for(int i=0;i<pairList.size()-1;i++){
            int diff=pairList.get(i+1).prefix-pairList.get(i).prefix;
            minDiff=Math.min(diff,minDiff);
        }
        for(int i=0;i<pairList.size()-1;i++){
            int diff=Math.abs(pairList.get(i+1).prefix-pairList.get(i).prefix);
            if(diff==minDiff){
                int tmp1=pairList.get(i+1).index;
                int tmp2=pairList.get(i).index;
                if(tmp1<tmp2) {
                    tmp1++;
                    result[0]=tmp1;
                    result[1]=tmp2;
                }else{
                    tmp2++;
                    result[0]=tmp2;
                    result[1]=tmp1;
                }
                return result;
            }
        }
    }

    public double findMedianSortedArrays(int[] A, int[] B) {
        int len=A.length+B.length;
        if(len%2==0){
            return (findkintwoArrays(A,0,B,0,len/2)+findkintwoArrays(A,0,B,0,len/2+1))/2.0;
        }
        else{
            return findkintwoArrays(A,0,B,0,len/2+1);
        }
    }

    private int findkintwoArrays(int[] A,int A_start,int[] B,int B_start,int k){
        if(A_start>A.length-1){
            return B[B_start+k-1];
        }
        if(B_start>B.length-1){
            return A[A_start+k-1];
        }
        if(k==1){
            return Math.min(A[A_start],B[B_start]);
        }
        int A_key=A_start+k/2-1<=A.length-1?A[A_start+k/2-1]:Integer.MAX_VALUE;
        int B_key=B_start+k/2-1<=B.length-1?B[B_start+k/2-1]:Integer.MAX_VALUE;
        if(A_key<B_key){
            return findkintwoArrays(A,A_start+k/2,B,B_start,k-k/2);
        }
        else{
            return findkintwoArrays(A,A_start,B,B_start+k/2,k-k/2);
        }

    }

}
