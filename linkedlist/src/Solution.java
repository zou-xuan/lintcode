import java.util.*;

/**
 * Created by zouxuan on 16/6/8.
 */

public class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
};

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class Solution {

    ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode left = leftDummy;
        ListNode right = rightDummy;
        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                left.next = p;
                left = left.next;
            } else {
                right.next = p;
                right = right.next;
            }
            p = p.next;
        }
        left.next = rightDummy.next;
        right.next = null;
        return leftDummy.next;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = head;
        while (p.next != null && p.next.next != null) {
            if (p.next.val == p.next.next.val) {
                int val = p.next.val;
                while (p.next != null && p.next.val == val) {
                    p.next = p.next.next;
                }
            } else {
                p = p.next;
            }
        }
        return dummy.next;=
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            TreeNode node = new TreeNode(head.val);
            return node;
        }
        ListNode fast = head.next;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = head;
        ListNode prev = dummy;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            prev = prev.next;
        }
        TreeNode mid = new TreeNode(slow.val);
        prev.next = null;
        mid.left = sortedListToBST(dummy.next);
        mid.right = sortedListToBST(slow.next);
        return mid;
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode,RandomListNode> oldToNew =new HashMap<>();
        HashMap<RandomListNode,RandomListNode> newToOld =new HashMap<>();
        RandomListNode dummy=new RandomListNode(0);
        RandomListNode p=dummy;
        while(head!=null){
            RandomListNode current=new RandomListNode(head.label);
            newToOld.put(current,head);
            oldToNew.put(head,current);
            p.next=current;
            p=p.next;
            head=head.next;
        }
        //map random
        p=dummy.next;
        while(p!=null){
            RandomListNode old=newToOld.get(p);
            RandomListNode newRandom=null;
           if(old.random!=null&&oldToNew.containsKey(old.random)){
                newRandom=oldToNew.get(old.random);
            }
            p.random=newRandom;
            p=p.next;

        }
        return dummy.next;
    }

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists==null||lists.size()==0){
            return null;
        }
        PriorityQueue<ListNode> queue=new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                ListNode n1=o1;
                ListNode n2=o2;
                return n1.val-n2.val;
            }
        });
        for(ListNode head:lists){
            while(head!=null){
                queue.add(head);
                head=head.next;
            }
        }
        ListNode dummy=new ListNode(0);
        ListNode p=dummy;
        while(!queue.isEmpty()){
            ListNode current=queue.poll();
            p.next=current;
            p=p.next;
        }
        return dummy.next;
    }

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists==null||lists.size()==0){
            return null;
        }
        if(lists.size()==1) return lists.get(0);
        if(lists.size()==2) return merge2Lists(lists.get(0),lists.get(1));
        ArrayList<ListNode> newList=new ArrayList<>();
        for(int i=0;i<lists.size();i=i+2){
            if(i+1<lists.size()){
                newList.add(merge2Lists(lists.get(i),lists.get(i+1)));
            }
            else{
                newList.add(lists.get(i));
            }
        }
        return mergeKLists(newList);
    }

    public ListNode merge2Lists(ListNode head1,ListNode head2){
        ListNode dummy=new ListNode(0);
        ListNode p=dummy;
        while(head1!=null&&head2!=null){
            if(head1.val<head2.val){
                p.next=head1;
                p=p.next;
                head1=head1.next;
            }
            else{
                p.next=head2;
                p=p.next;
                head2=head2.next;
            }
        }
        if(head1!=null){
            p.next=head1;
        }
        if(head2!=null){
            p.next=head2;
        }
        return dummy.next;
    }


}
