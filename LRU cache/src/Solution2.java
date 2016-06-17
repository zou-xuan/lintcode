import java.util.HashMap;

/**
 * Created by zouxuan on 16/6/17.
 */

class ListNode {
    ListNode prev;
    ListNode next;
    int key;
    int value;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class Solution2 {
    HashMap<Integer, ListNode> map;
    private int capacity;
    private ListNode head;
    private ListNode tail;

    public Solution2(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next=tail;
        tail.prev=head;
    }

    // @return an integer
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int result = map.get(key).value;
        ListNode node = map.get(key);
        node.prev.next=node.next;
        node.next.prev=node.prev;
        movetoTail(node);
        return result;

    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if(map.containsKey(key)){
            map.get(key).value=value;
            return;
        }
        if(map.size()==capacity){
            map.remove(head.next.key);
            head.next=head.next.next;
            head.next.prev=head;
        }
        ListNode node=new ListNode(key,value);
        map.put(key,node);
        movetoTail(node);
    }

    private void movetoTail(ListNode current) {
        current.prev = tail.prev;
        current.next = tail;
        tail.prev = current;
        current.prev.next = current;
    }

}
