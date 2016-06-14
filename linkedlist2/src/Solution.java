import com.sun.org.apache.bcel.internal.generic.InstructionListObserver;

/**
 * Created by zouxuan on 16/6/9.
 */

 class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class Solution {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        //find tail
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        //find mid
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHead = slow.next;
        slow.next = null;
        //reverse second
        ListNode prev = null;
        while (secondHead != null) {
            ListNode tmp = secondHead.next;
            secondHead.next = prev;
            prev = secondHead;
            secondHead = tmp;
        }
        //merge
        ListNode p = head;
        ListNode q = tail;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (p != null && q != null) {
            current.next = p;
            ListNode pnext = p.next;
            ListNode qnext = q.next;
            p.next = q;
            current = q;
            p = pnext;
            q = qnext;
        }
        if (p != null) {
            current.next = p;
        }
        head = dummy.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHead = slow.next;
        slow.next = null;
        ListNode first = sortList(head);
        ListNode second = sortList(secondHead);
        return merge(first, second);

    }

    public ListNode merge(ListNode first, ListNode second) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (first != null && second != null) {
            if (first.val < second.val) {
                current.next = first;
                current = current.next;
                first = first.next;
            } else {
                current.next = second;
                current = current.next;
                second = second.next;
            }
        }
        if (first != null) {
            current.next = first;
        }
        if (second != null) {
            current.next = second;
        }
        return dummy.next;
    }

    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode midVal = findMid(head);
        ListNode leftDummy = new ListNode(0);
        ListNode left = leftDummy;
        ListNode rightDummy = new ListNode(0);
        ListNode midDummy=new ListNode(0);
        ListNode mid=midDummy;
        ListNode right = rightDummy;
        while (head != null) {
            if (head.val < midVal.val) {
                left.next = head;
                left = left.next;
            } else if(head.val>midVal.val){
                right.next = head;
                right = right.next;
            }else{
                mid.next=head;
                mid=mid.next;
            }
            head = head.next;
        }
        left.next = null;
        right.next = null;
        mid.next=null;
        ListNode sortedLeft = sortList2(leftDummy.next);
        ListNode sortedRight = sortList2(rightDummy.next);

        ListNode dummy=new ListNode(0);
        dummy.next=sortedLeft;
        ListNode leftTail = dummy;
        while (leftTail.next != null) {
            leftTail = leftTail.next;
        }
        leftTail.next = midDummy.next;
        ListNode midTail=dummy;
        while(midTail.next!=null){
            midTail=midTail.next;
        }

        midTail.next=sortedRight;

        return dummy.next;
    }

    private ListNode findMid(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(3);
        ListNode third = new ListNode(4);
        ListNode forth = new ListNode(2);
        first.next = second;
        second.next = third;
        third.next = forth;
        s.sortList2(first);
    }

    public ListNode reverseBetween(ListNode head, int m , int n) {
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode prev=dummy;
        for(int i=0;i<m-1;i++){
            prev=prev.next;
        }
        head=prev.next;
        ListNode end=dummy;
        for(int i=0;i<n;i++){
            end=end.next;
        }
        ListNode prevnode=end.next;
        while(prevnode!=end){
            ListNode tmp=head.next;
            head.next=prevnode;
            prevnode=head;
            head=tmp;
        }
        prev.next=end;
        return dummy.next;

    }


    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null) return null;
        ListNode slow=head.next;
        ListNode fast=head.next.next;
        while(fast!=null&&fast.next!=null&&fast!=slow){
            fast=fast.next.next;
            slow=slow.next;
        }
        System.out.println(slow);
        if(fast==null||fast.next==null) return null;
        ListNode p=head;
        while(p!=slow){
            p=p.next;
            slow=slow.next;
        }
        return p;

    }


}