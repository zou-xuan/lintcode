/**
 * Created by zouxuan on 16/5/22.
 */
public class Solution {
    public int binarySearch(int[] nums, int target) {
        if(nums==null||nums.length==0) return -1;
        int start=0;
        int end=nums.length-1;
        while(start+1<end){
            int mid=star+(end-start)/2;
            if(nums[mid]>=target){
                end=mid;
            }
            else start=mid;
        }
        if(nums[start]==target) return start;
        else if(nums[end]==target) return end;
        return -1;
    }

}
