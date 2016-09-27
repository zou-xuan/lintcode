import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;

/**
 * Created by zouxuan on 9/20/16.
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 3; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum >= target) {
                    right--;
                } else {
                    result += right - left;
                    left++;
                }
            }
        }
        return result;
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode oddDummy = new ListNode(-1);
        ListNode evenDummy = new ListNode(-1);
        ListNode oddtail = oddDummy;
        ListNode eventail = evenDummy;
        boolean isOdd = true;
        while (head != null) {
            if (isOdd) {
                oddtail.next = head;
                oddtail = head;
                isOdd = false;
            } else {
                eventail.next = head;
                eventail = head;
                isOdd = true;
            }
            head = head.next;
        }
        oddtail.next = evenDummy.next;
        return oddDummy.next;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] words = new char[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char u = t.charAt(i);
            if (words[c] == 0) {
                words[c] = u;
            } else {
                if (words[c] != u) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.isIsomorphic("egg", "add");
    }

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set=new HashSet<>();
        for(int n:nums){
            set.add(n);
        }
        int result=1;
        for(int n:nums){
            int count=1;
            int tmp=n;
            while(set.contains(--tmp)){
                count++;
                set.remove(tmp);
            }
            tmp=n;
            while(set.contains(++tmp)){
                count++;
                set.remove(tmp);
            }
            result=Math.max(result,count);
        }
        return result;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||k<=0) return null;
        int n=nums.length;
        int[] result=new int[n-k+1];
        int ri=0;
        Deque<Integer> queue=new ArrayDeque<>();
        for(int i=0;i<nums.length;i++){
            while(!queue.isEmpty()&& queue.peek()<=i-k){
                queue.poll();
            }
            while(!queue.isEmpty()&&nums[queue.peekLast()]<nums[i]){
                queue.pollLast();
            }
            queue.offer(i);
            if(i>k-1){
                result[ri++]=nums[queue.peek()];
            }
        }
        return result;
    }

}
