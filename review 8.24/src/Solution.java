/**
 * Created by zouxuan on 8/24/16.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        while (p.next != null && p.next.next != null) {
            if (p.next.val != p.next.next.val) {
                p = p.next;
            } else {
                int val = p.next.val;
                while (p.next != null && p.next.val == val) {
                    p.next = p.next.next;
                }
            }
        }
        return dummy.next;
    }

    public int rob(int[] nums) {
        int[] result = new int[nums.length];
        if(nums==null||nums.length==0) return 0;
        if (nums.length == 1) return nums[0];
        result[0] = nums[0];
        result[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            result[i] = Math.max(result[i - 2] + nums[i], result[i-1]);
        }
        return result[nums.length-1];
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result=new int[2];
        result[0]=-1;
        result[1]=-1;
        if(nums==null||nums.length==0) return result;
        int left=0, right=nums.length-1;
        while(left+1<right){
            int mid=left+(right-left)/2;
            if(nums[mid]>=target){
                right=mid;
            }else{
                left=mid;
            }
        }
        if(nums[left]==target){
            result[0]=left;
        }else if(nums[right]==target){
            result[0]=right;
        }else{
            return result;
        }
        left=0;
        right=nums.length-1;
        while (left+1<right){
            int mid=left+(right-left)/2;
            if(nums[mid]<=target){
                left=mid;
            }else{
                right=mid;
            }
        }
        if(nums[right]==target){
            result[1]=right;
        }else {
            result[1]=left;
        }
        return result;
    }
}
