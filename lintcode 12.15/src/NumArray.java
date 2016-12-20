/**
 * Created by zouxuan on 12/18/16.
 */
public class NumArray {
    int[] prefix;
    int[] nums;

    public NumArray(int[] nums) {
        if(nums==null||nums.length==0){
            return;
        }
        prefix=new int[nums.length];
        prefix[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            prefix[i]=prefix[i-1]+nums[i];
        }
        this.nums=nums;
    }

    void update(int i, int val) {
        if(nums==null) return;
        int diff=val-nums[i];
        nums[i]=val;
        for(int k=i;k<nums.length;k++){
            prefix[k]+=diff;
        }
    }

    public int sumRange(int i, int j) {
        if(nums==null) return 0;
        return prefix[j]-prefix[i]+nums[i];
    }
}
