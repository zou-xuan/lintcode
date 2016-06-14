import java.util.*;

/**
 * Created by zouxuan on 16/6/14.
 */
public class Solution {

    public int hashCode(char[] key, int HASH_SIZE) {

        long result = 0;
        for (int i = 0; i < key.length - 1; i++) {
            result = (key[i] + result) * 33;
            result = result % HASH_SIZE;
        }
        result += key[key.length - 1];
        return (int) (result % HASH_SIZE);
    }

    public int[] topk(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int n : nums) {
            queue.offer(n);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }


    public List<Integer> mergekSortedArrays(int[][] arrays) {
        // Write your code here
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                queue.offer(arrays[i][j]);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode[] rehashing(ListNode[] hashTable) {
        int afterSize = 2 * hashTable.length;
        ListNode[] result = new ListNode[afterSize];
        for (ListNode n : hashTable) {
            while (n != null) {
                int val = n.val;
                int newIndex = val < 0 ? (val % afterSize + afterSize) % afterSize : val % afterSize;
                if (result[newIndex] == null) {
                    result[newIndex] = new ListNode(val);
                } else {
                    ListNode dummy = result[newIndex];
                    while (dummy.next != null) {
                        dummy = dummy.next;
                    }
                    dummy.next = new ListNode(val);

                }
                n = n.next;
            }
        }
        return result;
    }

    public int nthUglyNumber(int n) {
        Queue<Long> queue = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        queue.offer(Long.valueOf(1));
        set.add(Long.valueOf(1));
        int count = 0;
        long number = 1;
        while (count < n) {
            long tmp = queue.poll();
            count++;
            number = tmp;
            for (int i = 2; i <= 5; i++) {
                if (i != 4) {
                    if (!set.contains(tmp * i)) {
                        queue.offer(tmp * i);
                        set.add(tmp * i);
                    }
                }
            }
        }
        return (int)number;
    }
}
