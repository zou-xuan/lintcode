import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zouxuan on 9/11/16.
 */
class Point {
    int row;
    int column;
    int value;

    public Point(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] distance = new int[length1 + 1][length2 + 1];
        for (int i = 0; i <= length1; i++) {
            distance[i][0] = i;
        }
        for (int i = 0; i <= length2; i++) {
            distance[i][0] = i;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = 1 + Math.min(distance[i - 1][j - 1],
                            Math.min(distance[i - 1][j], distance[i][j - 1]));
                }
            }
        }
        return distance[length1][length2];
    }

    public boolean isOneEditDistance(String s, String t) {
        int lengthDiff = s.length() - t.length();
        if (lengthDiff < -1 || lengthDiff > 1) return false;
        int i = 0, j = 0;
        int count = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                count++;
                if (lengthDiff == 0) {
                    i++;
                    j++;
                } else if (lengthDiff > 0) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        if (i != s.length() || j != t.length()) {
            return count == 0;
        }
        return count == 1;
    }

    public int[][] multiply(int[][] A, int[][] B) {
        ArrayList<Point> alist = getPointsList(A);
        ArrayList<Point> blist = getPointsList(B);
        int[][] result = new int[A.length][B[0].length];
        for (Point apoint : alist) {
            for (Point bpoint : blist) {
                if (apoint.column == bpoint.row) {
                    int tmp = apoint.value * bpoint.value;
                    result[apoint.row][bpoint.column] += tmp;
                }
            }
        }
        return result;
    }

    private ArrayList<Point> getPointsList(int[][] mat) {
        ArrayList<Point> list = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != 0) {
                    list.add(new Point(i, j, mat[i][j]));
                }
            }
        }
        return list;
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        queue.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = queue.poll();
            if (intervals[i].start >= interval.end) {
                interval.end = intervals[i].end;
            } else {
                queue.offer(intervals[i]);
            }
            queue.offer(interval);
        }
        return queue.size();
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor=null;
        while(root!=null&&root!=p){
            if(root.val>p.val){
                successor=root;
                root=root.left;
            }
            else{
                root=root.right;
            }
        }
        if(p.right==null){
            return successor;
        }
        root=p.right;
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }

    public boolean isMatch(String s, String p) {
        int index_s=0;
        int index_p=0;
        while(index_s<s.length()&&index_p<p.length()){
            char s_char=s.charAt(index_s);
            char p_char=p.charAt(index_p);
            if(s_char!='?'&&s_char!='*'&&p_char!='?'&&p_char!='*'){
                if(s_char!=p_char){
                    return false;
                }
                index_p++;
                index_s++;
            }
        }
    }
}
