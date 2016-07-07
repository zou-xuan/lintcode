import java.util.HashSet;

/**
 * Created by zouxuan on 7/6/16.
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

    public ListNode removeDuplicates(ListNode head) {
        HashSet<Integer> set=new HashSet<>();
        if(head==null||head.next==null) return head;
        ListNode prev=head;
        ListNode curt=head.next;
        set.add(prev.val);
        while(curt!=null){
            if(set.contains(curt.val)){
                prev.next=curt.next;
            }else{
                set.add(curt.val);
                prev=curt;
            }
            curt=curt.next;
        }
        return head;
    }
}
