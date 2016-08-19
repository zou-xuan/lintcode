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
}
