import java.util.HashSet;
import java.util.List;

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
        HashSet<Integer> set = new HashSet<>();
        if (head == null || head.next == null) return head;
        ListNode prev = head;
        ListNode curt = head.next;
        set.add(prev.val);
        while (curt != null) {
            if (set.contains(curt.val)) {
                prev.next = curt.next;
            } else {
                set.add(curt.val);
                prev = curt;
            }
            curt = curt.next;
        }
        return head;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curt = head;
        ListNode prev = dummy;
        while (curt != null && curt.next != null) {
            ListNode next = curt.next;
            prev.next = next;
            curt.next = next.next;
            next.next = curt;
            prev = curt;
            curt = curt.next;
        }
        return dummy.next;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        while (head != null) {
            ListNode p = dummy;
            while (p.val <= head.val) {
                if (p.next == null || p.next.val > head.val) {
                    break;
                }
                p = p.next;
            }
            ListNode tmp = new ListNode(head.val);
            ListNode next = p.next;
            p.next = tmp;
            tmp.next = next;
            head = head.next;
        }
        return dummy.next;
    }

    public ListNode addLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p=dummy;
        ListNode first = l1;
        ListNode second = l2;
        int carry=0;
        while (first != null || second != null) {
            int num1 = first != null ? first.val : 0;
            int num2 = second != null ? second.val : 0;
            int tmp=num1+num2+carry;
            if(tmp>=10){
                tmp=tmp-10;
                carry=1;
            }else{
                carry=0;
            }
            ListNode next=new ListNode(tmp);
            p.next=next;
            p=next;
            first=first==null?null:first.next;
            second=second==null?null:second.next;
        }
        if(carry==1){
            p.next=new ListNode(1);
        }
        return dummy.next;
    }

    public ListNode swapNodes(ListNode head, int v1, int v2) {
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode prev1=dummy, curt1=null, prev2=dummy, curt2=null;
        while(prev1.next!=null&&curt1==null){
            if(prev1.next.val==v1){
                curt1=prev1.next;
                break;
            }else{
                prev1=prev1.next;
            }
        }
        while(prev2.next!=null&&curt2==null){
            if(prev2.next.val==v2){
                curt2=prev2.next;
                break;
            }else{
                prev2=prev2.next;
            }
        }
        if(curt1==null||curt2==null) return head;
        ListNode tmp=curt2.next;
        prev1.next=curt2;
        curt2.next=curt1.next;
        prev2.next=curt1;
        curt1.next=tmp;
        return dummy.next;

    }
}
