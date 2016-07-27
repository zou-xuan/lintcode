/**
 * Created by zouxuan on 7/27/16.
 */
public class Solution {
    public int median(int[] nums) {
        int k=(nums.length-1)/2;
        int start=0;
        int end=nums.length-1;
        while(true){
            int pos=partition(nums,start,end);
            if(pos>k){
                end=pos-1;
            }
            else if(pos<k){
                start=pos+1;
            }else{
                return nums[pos];
            }
        }
    }

    public int partition(int[] nums,int start, int end) {
        int left = start+1, right = end;
        int pivot=nums[start];
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        int tmp=nums[start];
        nums[start]=nums[right];
        nums[right]=tmp;
        return right;
    }
}
