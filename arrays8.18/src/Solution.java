import java.util.ArrayList;

/**
 * Created by zouxuan on 8/18/16.
 */
public class Solution {
    public void moveZeroes(int[] nums) {
        if(nums==null||nums.length<=1) return;
        int index=0;
        for(int i=0;i<nums.length;i++){
           if(nums[i]!=0){
               int tmp=nums[i];
               nums[i]=nums[index];
               nums[index]=tmp;
               index++;
           }
        }
    }

    public void partitionArray(int[] nums) {
        // write your code here;
        if(nums==null||nums.length<=1) return;
        int left=0, right=nums.length-1;
        while(left<=right){
            while (left<=right&&nums[left]%2!=0) left++;
            while(left<=right&&nums[right]%2==0) right--;
            if(left<=right){
                int tmp=nums[left];
                nums[left]=nums[right];
                nums[right]=tmp;
                left++;
                right--;
            }
        }
    }

    public int trapRainWater(int[] heights) {
        int a=0;
        int b=heights.length-1;
        int max=0;
        int leftMax=0;
        int rightMax=0;
        while(a<=b){
            leftMax=Math.max(leftMax,heights[a]);
            rightMax=Math.max(rightMax,heights[b]);
            if(leftMax<rightMax){
                max+=leftMax-heights[a];
                a++;
            }
            else{
                max+=rightMax-heights[b];
                b--;
            }
        }
        return max;
    }

    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        int index=1;
        while (index<nums.size()){
            if(nums.get(index)<nums.get(index-1)){
                break;
            }
            index++;
        }
        reverse(nums,0,index-1);
        reverse(nums,index,nums.size()-1);
        reverse(nums,0,nums.size()-1);
    }

    private void reverse(ArrayList<Integer> nums,int start,int end){
        while(start<=end){
            int tmp=nums.get(start);
            nums.set(start,nums.get(end));
            nums.set(end,tmp);
            start++;
            end--;
        }
    }
}
