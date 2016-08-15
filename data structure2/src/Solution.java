import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by zouxuan on 7/30/16.
 */
class Node {
    int x, y, val;

    public Node(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        Arrays.sort(primes);
        queue.offer(Long.valueOf(1));
        set.add(Long.valueOf(1));
        int count = 0;
        long curt = 1;
        while (!queue.isEmpty()) {
            curt = queue.poll();
            count++;
            if (count == n) break;
            for (int i : primes) {
                long tmp = i * curt;

                if (!set.contains(tmp)) {
                    queue.offer(tmp);
                    set.add(tmp);
                }
            }
        }
        return (int) curt;
    }

    public int kthSmallest(int[][] matrix, int k) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) return 0;
        if(k>matrix.length*matrix[0].length) return 0;
        return vertical(matrix,k);
    }

    private int vertical(int[][] matrix,int k){
        PriorityQueue<Node> queue=new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val-o2.val;
            }
        });
        int row=matrix.length;
        int column=matrix[0].length;
        for(int i=0;i<row;i++){
            queue.offer(new Node(i,0,matrix[i][0]));
        }
        for(int i=0;i<k-1;i++){
            Node n=queue.poll();
            if(n.y+1<column){
                queue.offer(new Node(n.x,n.y+1,matrix[n.x][n.y]+1));
            }
        }
        return queue.peek().val;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] primes = new int[]{89, 449, 499, 79, 457, 311, 281, 181, 271, 419, 379, 347, 131};
        System.out.println(s.nthSuperUglyNumber(299, primes));
    }
}
