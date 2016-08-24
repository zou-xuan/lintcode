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
        if(head==null||head.next==null) return head;
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode p=dummy;
        while(p.next!=null&&p.next.next!=null){
            if(p.next.val!=p.next.next.val){
                p=p.next;
            }else{
                int val=p.next.val;
                while(p.next!=null&&p.next.val==val){
                    p.next=p.next.next;
                }
            }
        }
        return dummy.next;
    }
}
