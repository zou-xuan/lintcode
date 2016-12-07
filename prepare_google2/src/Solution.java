/**
 * Created by zouxuan on 12/5/16.
 */
public class Solution {
    int totalNum = 0;
    public int numberOfPatterns(int m, int n) {
        boolean[][] visited;
        for (int num = m; num <= n; num++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    visited = new boolean[3][3];
                    dfs(visited, i, j, 1, num);
                }
            }
        }
        return totalNum;
    }

    private void dfs(boolean[][] visited, int x, int y, int currentDepth, int depth) {
        if (currentDepth == depth) {
            totalNum++;
            return;
        }
        visited[x][y] = true;
        for (int newX = 0; newX < visited.length; newX++) {
            for (int newY = 0; newY < visited[0].length; newY++) {
                if (!visited[newX][newY] && canConnect(visited, x, y, newX, newY)) {
                    dfs(visited, newX, newY, currentDepth + 1, depth);
                }
            }
        }
        visited[x][y]=false;
    }

    private boolean canConnect(boolean[][] visited, int x, int y, int newX, int newY) {
        int midX = (x + newX);
        int midY = (y + newY);
        if (midX % 2 != 0 || midY % 2 != 0) {
            return true;
        }
        midX = midX / 2;
        midY = midY / 2;
        if (visited[midX][midY]) return true;
        return false;
    }
}
