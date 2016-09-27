import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zouxuan on 9/18/16.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        int start = lower;
        for (int n : nums) {
            if (n == start) {
                start = n + 1;
            } else {
                int end = n - 1;
                if (start == end) {
                    result.add(String.valueOf(start));
                } else if (start < end) {
                    result.add(String.valueOf(start) + "->" + String.valueOf(end));
                }
                start = n + 1;
            }
        }
        if (start == upper) {
            result.add(String.valueOf(start));
        } else if (start < upper) {
            result.add(String.valueOf(start) + "->" + String.valueOf(upper));
        }
        return result;
    }

    int maxLength = 0;

    public int longestConsecutive(TreeNode root) {
        getLongest(root);
        return maxLength;
    }

    private int getLongest(TreeNode root) {
        if (root == null) return 0;
        int length = 1;
        int left = getLongest(root.left);
        int right = getLongest(root.right);
        if (root.left != null && root.left.val == root.val + 1) {
            length = Math.max(length, left + 1);
        }
        if (root.right != null && root.right.val == root.val + 1) {
            length = Math.max(length, right + 1);
        }
        maxLength = Math.max(maxLength, length);
        return length;
    }

    public String reverseVowels(String s) {
        char[] array=s.toCharArray();
        HashSet<Character> set=new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        int left=0;
        int right=array.length-1;
        while(left<right){
            while(left<right&&!set.contains(array[left])){
                left++;
            }
            while (left<right&&!set.contains(array[right])){
                right--;
            }
            char tmp=array[left];
            array[left]=array[right];
            array[right]=tmp;
            left++;
            right--;
        }
        return new String(array);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1==null||nums2==null){
            return 0;
        }
        int len=nums1.length+nums2.length;
        double result=0;
        if(len%2==0){
            result=(findKth(nums1,0,nums2,0,len/2)+findKth(nums1,0,nums2,0,len/2+1))/2.0;
        }
        else{
            result=findKth(nums1,0,nums2,0,len/2+1);
        }
        return result;
    }

    public int findKth(int[] nums1,int index1,int[] nums2,int index2,int k){
        int length1=nums1.length;
        int length2=nums2.length;
        if(index1>=length1){
            return nums2[index2+k-1];
        }
        if(index2>=length2){
            return nums1[index1+k-1];
        }
        if(k==1){
            return Math.min(nums1[index1],nums2[index2]);
        }
        int mid=k/2-1;
        int key1=index1+mid>=length1?Integer.MAX_VALUE:nums1[index1+mid];
        int key2=index2+mid>=length2?Integer.MAX_VALUE:nums2[index2+mid];
        int knew=k-k/2;
        if(key1<key2){
            return findKth(nums1,index1+k/2,nums2,index2,knew);
        }else{
            return findKth(nums1,index1,nums2,index2+k/2,knew);
        }
    }

    public int trap(int[] height) {
        int sum=0;
        int left=0;
        int right=height.length-1;
        int heightLeft=0;
        int heightRight=0;
        while(left<right){
            heightLeft=Math.max(heightLeft,height[left]);
            heightRight=Math.max(heightRight,height[right]);
            if(heightLeft<heightRight){
                sum+=heightLeft-height[left];
                left++;
            }else{
                sum+=heightRight-height[right];
                right++;
            }
        }
        return sum;
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {

    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k==0||s==null||s.length()==0) return 0;
        int count=0;
        int[] array=new int[256];
        int left=0;
        int right=0;
        int result=Integer.MIN_VALUE;
        while(right<s.length()){
            char c=s.charAt(right);
            if(array[c]==0){
                count++;
                array[c]++;
            }else{
                array[c]++;
            }
            if(count>k){
                int length=right-left;
                result=Math.max(result,length);
                while(count>k){
                    char tmp=s.charAt(left);
                    if(--array[tmp]==0){
                        count--;
                    }
                    left++;
                }
            }
            right++;
        }
        result=Math.max(result,right-left);
        return result;
    }




}
