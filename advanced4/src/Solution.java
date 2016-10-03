import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zouxuan on 10/2/16.
 */
class Cell {
    int x;
    int y;
    int height;

    public Cell(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }
}

public class Solution {
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) return 0;
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o1.height - o2.height;
            }
        });
        int water=0;
        int row = heights.length;
        int column = heights[0].length;
        for (int i = 0; i < row; i++) {
            visited[i][0] = true;
            visited[i][column - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, column - 1, heights[i][column - 1]));
        }
        for (int i = 1; i < column-1; i++) {
            visited[0][i] = true;
            visited[row - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(row - 1, i, heights[row - 1][i]));
        }
        int[] a = {0, 0, -1, 1};
        int[] b = {-1, 1, 0, 0};
        while (!queue.isEmpty()) {
            Cell c = queue.poll();
            for (int i = 0; i < 4; i++) {
                int new_x = c.x + a[i];
                int new_y = c.y + b[i];
                if(new_x>=0&&new_x<row&&new_y>=0&&new_y<column&&!visited[new_x][new_y]){
                    int height=heights[new_x][new_y];
                    System.out.println(height);
                    if(height<c.height){
                        water+=c.height-height;
                        queue.offer(new Cell(new_x,new_y,c.height));
                    }else{
                        queue.offer(new Cell(new_x,new_y,height));
                    }
                    visited[new_x][new_y]=true;
                }
            }
        }
        return water;
    }
}
