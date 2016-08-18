import java.util.*;

/**
 * Created by zouxuan on 8/16/16.
 */
class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Solution {
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        char[] array = digits.toCharArray();
        ArrayList<Character> path = new ArrayList<>();
        HashMap<Integer, ArrayList<Character>> map = new HashMap<>();
        Character[] two = new Character[]{'a', 'b', 'c'};
        Character[] three = new Character[]{'d', 'e', 'f'};
        Character[] four = new Character[]{'g', 'h', 'i'};
        Character[] five = new Character[]{'j', 'k', 'l'};
        Character[] six = new Character[]{'m', 'n', 'o'};
        Character[] seven = new Character[]{'p', 'q', 'r', 's'};
        Character[] eight = new Character[]{'t', 'u', 'v'};
        Character[] nine = new Character[]{'w', 'x', 'y', 'z'};
        map.put(2, new ArrayList<>(Arrays.asList(two)));
        map.put(3, new ArrayList<>(Arrays.asList(three)));
        map.put(4, new ArrayList<>(Arrays.asList(four)));
        map.put(5, new ArrayList<>(Arrays.asList(five)));
        map.put(6, new ArrayList<>(Arrays.asList(six)));
        map.put(7, new ArrayList<>(Arrays.asList(seven)));
        map.put(8, new ArrayList<>(Arrays.asList(eight)));
        map.put(9, new ArrayList<>(Arrays.asList(nine)));
        dfs(result, path, array, 0, map);
        return result;
    }

    public void dfs(ArrayList<String> result, ArrayList<Character> path, char[] array,
                    int index, HashMap<Integer, ArrayList<Character>> map) {
        if (index == array.length) {
            StringBuffer sb = new StringBuffer();
            for (Character c : path) {
                sb.append(c);
            }
            result.add(new String(sb));
        } else {
            ArrayList<Character> list = map.get(array[index] - '0');
            for (Character c : list) {
                path.add(c);
                dfs(result, path, array, index + 1, map);
                path.remove(path.size() - 1);
            }
        }
    }

    public int numIslands(boolean[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]) {
                    islandDFS(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void islandDFS(boolean[][] grid, int i, int j) {
        int row = grid.length;
        int column = grid[0].length;
        if (i < 0 || i >= row || j < 0 || j >= column) {
            return;
        }
        if (!grid[i][j]) return;
        grid[i][j] = false;
        islandDFS(grid, i - 1, j);
        islandDFS(grid, i + 1, j);
        islandDFS(grid, i, j - 1);
        islandDFS(grid, i, j + 1);
    }


    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return result;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end) {
                last.end = Math.max(curt.end, last.end);
            } else {
                result.add(last);
                last = curt;
            }
        }
        result.add(last);
        return result;
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if(intervals==null){
            result.add(newInterval);
            return result;
        }
        intervals.add(newInterval);
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end) {
                last.end = Math.max(curt.end, last.end);
            } else {
                result.add(last);
                last = curt;
            }
        }
        result.add(last);
        return result;

    }

    public boolean isValidParentheses(String s) {
        
    }


}
