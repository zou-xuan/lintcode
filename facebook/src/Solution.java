import java.util.*;

/**
 * Created by zouxuan on 9/6/16.
 */

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

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[left]);
                    tmp.add(nums[right]);
                    result.add(tmp);
                    left++;
                    right--;
                    while (nums[left] == nums[left - 1]) left++;
                    while (nums[right] == nums[right + 1]) right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] result = new boolean[s.length() + 1];
        result[0] = true;
        for (int i = 1; i < result.length; i++) {
            for (int j = 0; j < i; j++) {
                if (result[j]) {
                    if (wordDict.contains(s.substring(j, i))) {
                        result[i] = true;
                        continue;
                    }
                }
            }
        }
        return result[result.length - 1];
    }

    private int initTargetHash(int[] hash, String target) {
        int targetnum = 0;
        for (char c : target.toCharArray()) {
            hash[c]++;
            targetnum++;
        }
        return targetnum;
    }

    public String minWindow(String s, String t) {
        int result = Integer.MAX_VALUE;
        String minStr = "";
        int[] hash = new int[256];
        int targetNum = initTargetHash(hash, t);
        int sourceNum = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (hash[s.charAt(i)] > 0) {
                sourceNum++;
            }
            hash[s.charAt(i)]--;
            while (sourceNum >= targetNum) {
                if (i - j + 1 < result) {
                    result = i - j + 1;
                    minStr = s.substring(j, i + 1);
                }
                hash[s.charAt(j)]++;
                if (hash[s.charAt(j)] > 0) {
                    sourceNum--;
                }
                j++;
            }
        }
        return minStr;
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
        int newStart = intervals.get(0).start;
        int newEnd = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= newEnd) {
                newEnd = Math.max(newEnd, intervals.get(i).end);
            } else {
                result.add(new Interval(newStart, newEnd));
                newStart = intervals.get(i).start;
                newEnd = intervals.get(i).end;
            }
        }
        result.add(new Interval(newStart, newEnd));
        return result;
    }

    public List<String> removeInvalidParentheses(String s) {
        Queue<String> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        queue.offer(s);
        set.add(s);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String tmp = queue.poll();
                if (check(tmp)) {
                    result.add(tmp);
                } else {
                    generate(queue, tmp, set);
                }
            }
            if (!result.isEmpty()) {
                return result;
            }
        }
        return result;
    }

    private void generate(Queue<String> queue, String s, HashSet<String> set) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                String newStr = s.substring(0, i) + s.substring(i + 1, s.length());
                if (!set.contains(newStr)) {
                    queue.offer(newStr);
                    set.add(newStr);
                }
            }
        }
    }

    private boolean check(String s) {
        if (s == null || s.length() == 0) return false;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int column = board[0].length;
        boolean[][] visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, i, j, 0, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        boolean result = false;
        if (index >= word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (visited[i][j]) {
            return false;
        }
        if (board[i][j] == word.charAt(index)) {
            visited[i][j] = true;
            result |= dfs(board, word, i - 1, j, index + 1, visited);
            result |= dfs(board, word, i + 1, j, index + 1, visited);
            result |= dfs(board, word, i, j - 1, index + 1, visited);
            result |= dfs(board, word, i, j + 1, index + 1, visited);
            visited[i][j] = false;
        }
        return result;
    }

    public boolean exist2(char[][] board, String word) {
        if (board == null || board.length == 0)
            return false;
        if (word.length() == 0)
            return true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {

                    boolean rst = find(board, i, j, word, 0);
                    if (rst)
                        return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] board, int i, int j, String word, int start) {
        if (start == word.length())
            return true;

        if (i < 0 || i >= board.length ||
                j < 0 || j >= board[0].length || board[i][j] != word.charAt(start)) {
            return false;
        }

        board[i][j] = '#'; // should remember to mark it
        boolean rst = find(board, i - 1, j, word, start + 1)
                || find(board, i, j - 1, word, start + 1)
                || find(board, i + 1, j, word, start + 1)
                || find(board, i, j + 1, word, start + 1);
        board[i][j] = word.charAt(start);
        return rst;
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result=new ArrayList<>();
        int i=0;

        while(i<intervals.size()){
            if (intervals.get(i).end<newInterval.start){
                result.add(intervals.get(i));
                i++;
            }else if(intervals.get(i).start>newInterval.end){
                result.add(intervals.get(i));
                i++;
            }
            else{


            }
        }
        if(newStart==newInterval.start&&newEnd==newInterval.end){
            result.add(new Interval(newStart,newEnd));
        }
        return result;
    }



}
