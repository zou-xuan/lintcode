import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by zouxuan on 7/4/16.
 */
public class Solution {

    ArrayList<ArrayList<String>> solveNQueens(int n) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<Integer> col = new ArrayList<>();
        search(n, col, result);
        return result;
    }

    private void search(int n, ArrayList<Integer> col, ArrayList<ArrayList<String>> result) {
        if (col.size() == n) {
            result.add(drawBoard(col, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(col, i)) {
                col.add(i);
                search(n, col, result);
                col.remove(col.size() - 1);
            }
        }
    }

    private boolean isValid(ArrayList<Integer> col, int location) {
        int row = col.size();
        int column = location;
        for (int i = 0; i < col.size(); i++) {
            int r = i;
            int c = col.get(i);
            if (c == column) {
                return false;
            }
            if (r + c == row + column || r - c == row - column) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<String> drawBoard(ArrayList<Integer> col, int n) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < col.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j == col.get(i)) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            result.add(new String(sb));
        }
        return result;
    }

    public int totalNQueens(int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> col = new ArrayList<>();
        search2(n, col, result);
        return result.size();
    }

    private void search2(int n, ArrayList<Integer> col, ArrayList<ArrayList<Integer>> result) {
        if (col.size() == n) {
            result.add(col);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid2(col, i)) {
                col.add(i);
                search2(n, col, result);
                col.remove(col.size() - 1);
            }
        }
    }

    private boolean isValid2(ArrayList<Integer> col, int location) {
        int row = col.size();
        int column = location;
        for (int i = 0; i < col.size(); i++) {
            int r = i;
            int c = col.get(i);
            if (c == column) {
                return false;
            }
            if (r + c == row + column || r - c == row - column) {
                return false;
            }
        }
        return true;
    }

    public int ladderLength(String start, String end, Set<String> dict) {
        if (start.equals(end)) return 1;
        HashSet<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int level = 1;
        queue.offer(start);
        set.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                if (isNeighbor(s, end)) {
                    return level;
                }
                for (String candidate : dict) {
                    if (!set.contains(candidate) && isNeighbor(s, candidate)) {
                        queue.offer(candidate);
                        set.add(candidate);
                    }
                }
            }
        }
        return -1;
    }

    private boolean isNeighbor(String lhs, String rhs) {
        int diff = 0;
        for (int i = 0; i < lhs.length(); i++) {
            if (lhs.charAt(i) != rhs.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<>();
        if (start.equals(end)) {
            List<String> l = new ArrayList<>();
            l.add(start);
            l.add(end);
            result.add(l);
            return result;
        }
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        int level = 0;
        queue.offer(end);
        map.put(end, 0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                for (String candidate : dict) {
                    if(!map.containsKey(start)&&isNeighbor(s,start)){
                        map.put(start,level);
                    }
                    if (!map.containsKey(candidate) && isNeighbor(s, candidate)) {
                        queue.offer(candidate);
                        map.put(candidate, level);
                    }
                }
            }
        }

        List<String> path = new ArrayList<>();
        path.add(start);
        helper(path, result, map, dict, map.get(start) - 1, end);
        return result;

    }

    private void helper(List<String> path, List<List<String>> result,
                        HashMap<String, Integer> map, Set<String> dict, int level, String end) {
        if (level == 0) {
            path.add(end);
            result.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            return;
        }
        for (String s : dict) {
            if (map.containsKey(s)&&map.get(s) == level&&isNeighbor(s,path.get(path.size()-1))) {
                path.add(s);
                helper(path, result, map, dict, level - 1, end);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args){
        Solution s=new Solution();

        String[] array=new String[]{"frye","heat","tree","thee","game","free","hell","fame","faye"};
        Set<String> set=new HashSet<String>(Arrays.asList(array));

        s.findLadders("game","thee",set);
    }

    public boolean stringPermutation(String A, String B) {

    }


}
