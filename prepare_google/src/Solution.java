import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zouxuan on 12/3/16.
 */
class FileStack {
    int level;
    String name;

    public FileStack(int l, String n) {
        level = l;
        name = n;
    }
}

class GridPoint {
    int x;
    int y;

    public GridPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[][][] direction = new int[4][grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 'W') {
                    if (i - 1 >= 0) {
                        if (grid[i - 1][j] == 'W') {
                            direction[0][i][j] = 0;
                        }
                        direction[0][i][j] = direction[0][i - 1][j] + (grid[i - 1][j] == 'E' ? 1 : 0);
                    }
                    if (j - 1 >= 0) {
                        if (grid[i][j - 1] == 'W') {
                            direction[0][i][j] = 0;
                        }
                        direction[2][i][j] = direction[2][i][j - 1] + (grid[i][j - 1] == 'E' ? 1 : 0);
                    }
                }
            }
        }
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (grid[i][j] != 'W') {
                    if (i + 1 < grid.length) {
                        if (grid[i + 1][j] == 'W') {
                            direction[1][i][j] = 0;
                        }
                        direction[1][i][j] = direction[1][i + 1][j] + (grid[i + 1][j] == 'E' ? 1 : 0);
                    }
                    if (j + 1 < grid[0].length) {
                        if (grid[i][j + 1] == 'W') {
                            direction[3][i][j] = 0;
                        }
                        direction[3][i][j] = direction[3][i][j + 1] + (grid[i][j + 1] == 'E' ? 1 : 0);
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    result = Math.max(result, direction[0][i][j] + direction[1][i][j] + direction[2][i][j] + direction[3][i][j]);
                }
            }
        }
        return result;
    }

    public int lengthLongestPath(String input) {
        String[] split = input.split("\n");
        Stack<FileStack> stack = new Stack<>();
        int maxlength = 0;
        for (String s : split) {
            int level = getLevel(s);
            while (!stack.isEmpty() && stack.peek().level >= level) {
                stack.pop();
            }
            if (s.contains(".")) {
                stack.push(new FileStack(level, s.substring(level)));
                maxlength = Math.max(maxlength, getDepth(stack));
            } else {
                stack.push(new FileStack(level, s.substring(level)));
            }
        }
        return maxlength;
    }

    private int getLevel(String s) {
        int level = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\t') {
                level++;
            }
        }
        return level;
    }

    private int getDepth(Stack<FileStack> stack) {
        int length = 0;
        for (FileStack f : stack) {
            length += f.name.length() + 1;
        }
        return length - 1;
    }

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] count = new int[sentence.length];
        for (int i = 0; i < sentence.length; i++) {
            count[i] = sentence[i].length();
        }
        int result = 0;
        int index = 0;
        for (int i = 0; i < rows; i++) {
            int used = 0;
            while (used + count[index] <= cols) {
                used += count[index] + 1;
                index++;
                if (index >= count.length) {
                    index = index - count.length;
                    result++;
                }
            }
        }
        return result;
    }

    public int wordsTypingII(String[] sentence, int rows, int cols) {
        StringBuffer sb = new StringBuffer();
        for (String s : sentence) {
            sb.append(s);
            sb.append(" ");
        }
        int start = 0;
        for (int i = 0; i < rows; i++) {
            start += cols;
            if (sb.charAt(start % sb.length()) == ' ') {
                start++;
            } else {
                while (start > 0 && sb.charAt((start - 1) % sb.length()) != ' ') {
                    start--;
                }
            }
        }
        return start / sb.length();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
    }

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int[][] distance = new int[grid.length][grid[0].length];
        int[][] reach = new int[grid.length][grid[0].length];
        int houseNum = 0;
        boolean[][] visited;
        Queue<GridPoint> queue;
        int[] a = {1, -1, 0, 0};
        int[] b = {0, 0, 1, -1};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    houseNum++;
                    visited = new boolean[grid.length][grid[0].length];
                    queue = new LinkedList<>();
                    int level = 0;
                    queue.offer(new GridPoint(i, j));
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        level++;
                        for (int s = 0; s < size; s++) {
                            GridPoint p = queue.poll();
                            for (int k = 0; k < 4; k++) {
                                int newX = p.x + a[k];
                                int newY = p.y + b[k];
                                if (newX >= 0 && newX < grid.length && newY >= 0
                                        && newY < grid[0].length && !visited[newX][newY] && grid[newX][newY] == 0) {
                                    queue.offer(new GridPoint(newX, newY));
                                    distance[newX][newY] += level;
                                    visited[newX][newY] = true;
                                    reach[newX][newY]++;
                                }
                            }
                        }
                    }
                }
            }
        }
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && reach[i][j] == houseNum) {
                    minDist = Math.min(minDist, distance[i][j]);
                }
            }
        }
        return minDist==Integer.MAX_VALUE?-1:minDist;
    }


}
