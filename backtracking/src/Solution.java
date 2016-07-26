import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by zouxuan on 7/26/16.
 */
public class Solution {
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;
        ArrayList<String> path = new ArrayList<>();
        restore(result, path, s, 0);
        return result;
    }

    private void restore(ArrayList<String> result, ArrayList<String> path, String s, int start) {
        if (start >= s.length()) return;
        if (path.size() == 3) {
            if (start < s.length() && isvalid(s.substring(start))) {
                path.add(s.substring(start));
                String tmp = path.get(0) + "." + path.get(1) + "." + path.get(2) + "." + path.get(3);
                path.remove(path.size() - 1);
                result.add(tmp);
            }
        } else {
            for (int i = start + 1; i <= start + 3; i++) {
                if (i <= s.length() && isvalid(s.substring(start, i))) {
                    path.add(s.substring(start, i));
                    restore(result, path, s, i);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    private boolean isvalid(String s) {
        if (s.charAt(0) == '0' && s.length() > 1) return false;
        int tmp = Integer.parseInt(s);
        if (tmp >= 0 && tmp <= 255) return true;
        return false;
    }

    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Character> path = new ArrayList<>();
        generate(result, path, n, n);
        return result;
    }

    private void generate(ArrayList<String> result, ArrayList<Character> path,
                          int left, int right) {
        if (left == 0 && right == 0) {
            StringBuffer sb = new StringBuffer();
            for (Character c : path) {
                sb.append(c);
            }
            result.add(sb.toString());
        } else {
            if (left > 0) {
                path.add('(');
                generate(result, path, left - 1, right);
                path.remove(path.size() - 1);
            }
            if (right > left) {
                path.add(')');
                generate(result, path, left, right - 1);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] visited=new boolean[board.length][board[0].length];
        boolean result=false;
        for(int i=0;i<visited.length;i++){
            Arrays.fill(visited[i],false);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j]==word.charAt(0)){
                    visited[i][j]=true;
                    if(exist(board,visited,word,1,i,j)){
                        result=true;
                    }
                    visited[i][j]=false;
                }
            }
        }
        return result;
    }

    private boolean exist(char[][] board, boolean[][] visited, String word, int index,
                          int row, int column) {
        boolean result=false;
        if (index == word.length()) {
            return true;
        }
        char c = word.charAt(index);
        if (isAccessible(visited, row - 1, column)) {
            if (board[row - 1][column] == c) {
                visited[row - 1][column] = true;
                result=result||exist(board, visited, word, index + 1, row - 1, column);
                visited[row - 1][column] = false;
            }
        }
        if (isAccessible(visited, row + 1, column)) {
            if (board[row + 1][column] == c) {
                visited[row + 1][column] = true;
                result=result|| exist(board, visited, word, index + 1, row + 1, column);
                visited[row + 1][column] = false;
            }
        }
        if (isAccessible(visited, row, column - 1)) {
            if (board[row][column - 1] == c) {
                visited[row][column - 1] = true;
                result=result|| exist(board, visited, word, index + 1, row, column - 1);
                visited[row][column - 1] = false;
            }
        }
        if (isAccessible(visited, row, column + 1)) {
            if (board[row][column + 1] == c) {
                visited[row][column + 1] = true;
                result=result|| exist(board, visited, word, index + 1, row, column + 1);
                visited[row][column + 1] = false;
            }
        }
        return result;
    }

    private boolean isAccessible(boolean[][] visited, int row, int column) {
        int r = visited.length;
        int c = visited[0].length;
        if (row < 0 || row >= r || column < 0 || column >= c) {
            return false;
        }
        if (visited[row][column]) {
            return false;
        }
        return true;
    }
}
