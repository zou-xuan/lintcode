

import java.util.*;

/**
 * Created by zouxuan on 12/15/16.
 */

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public int triangleCount(int S[]) {
        if (S == null || S.length == 0) return 0;
        Arrays.sort(S);
        int result = 0;
        for (int i = S.length - 1; i >= 2; i--) {
            int longest = S[i];
            int left = 0;
            int right = i - 1;
            if (S[left] + S[right] > longest) {
                result += right - left;
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int length = nums.length;
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        int gap = (int) Math.ceil((double) (max - min) / (length - 1));
        int[] bucketMin = new int[length - 1];
        int[] bucketMax = new int[length - 1];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        for (int n : nums) {
            if (n == min || n == max) {
                continue;
            }
            int index = (n - min) / gap;
            bucketMin[index] = Math.min(n, bucketMin[index]);
            bucketMax[index] = Math.max(n, bucketMax[index]);
        }
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < length - 1; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE && bucketMax[i] == Integer.MIN_VALUE) {
                continue;
            }
            maxGap = Math.max(maxGap, bucketMin[i] - previous);
            previous = bucketMax[i];
        }
        maxGap = Math.max(maxGap, max - previous);
        return maxGap;
    }

    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int row = image.length;
        int column = image[0].length;
        boolean[][] visited = new boolean[row][column];
        Queue<Point> queue = new LinkedList<>();
        int left = y;
        int right = y;
        int up = x;
        int down = x;
        int[] a = new int[]{0, 0, -1, 1};
        int[] b = new int[]{1, -1, 0, 0};
        visited[x][y] = true;
        queue.offer(new Point(x, y));
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x_new = p.x + a[i];
                int y_new = p.y + b[i];
                if (x_new >= 0 && x_new < row && y_new >= 0 && y_new < column && image[x_new][y_new] == '1' && !visited[x_new][y_new]) {
                    visited[x_new][y_new] = true;
                    queue.offer(new Point(x_new, y_new));
                    left = Math.min(left, y_new);
                    right = Math.max(right, y_new);
                    up = Math.min(up, x_new);
                    down = Math.max(down, x_new);
                }
            }
        }
        return (right - left + 1) * (down - up + 1);
    }

    public static String favoriteRestaurant(List<String> restaurants_1, List<String> restaurants_2) {
        int rank = Integer.MAX_VALUE;
        String result = "Yelpwich";
        if (restaurants_1 == null || restaurants_1.size() == 0 || restaurants_2 == null || restaurants_2.size() == 0)
            return result;
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < restaurants_1.size(); i++) {
            map1.put(restaurants_1.get(i), i);
        }
        for (int i = 0; i < restaurants_2.size(); i++) {
            map2.put(restaurants_2.get(i), i);
        }
        for (String s : map1.keySet()) {
            if (map2.containsKey(s)) {
                int tmp = map1.get(s) + map2.get(s);
                if (tmp < rank) {
                    result = s;
                    rank = tmp;
                }
            }
        }
        return result;
    }

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int row = grid.length;
        int column = grid[0].length;
        int[][] distance = new int[row][column];
        int[][] reach = new int[row][column];
        int houseNum = 0;
        int[] a = new int[]{0, 0, -1, 1};
        int[] b = new int[]{-1, 1, 0, 0};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == 1) {
                    houseNum++;
                    boolean[][] visited = new boolean[row][column];
                    int level = 1;
                    Queue<Point> queue = new LinkedList<>();
                    queue.offer(new Point(i, j));
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            Point p = queue.poll();
                            for (int s = 0; s < 4; s++) {
                                int new_x = p.x + a[s];
                                int new_y = p.y + b[s];
                                if (new_x >= 0 && new_x < row && new_y >= 0 && new_y < column
                                        && grid[new_x][new_y] == 0 && !visited[new_x][new_y]) {
                                    queue.offer(new Point(new_x, new_y));
                                    visited[new_x][new_y] = true;
                                    distance[new_x][new_y]+=level;
                                    reach[new_x][new_y]++;
                                }
                            }
                        }
                        level++;
                    }
                }
            }
        }
        int result=Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if(grid[i][j]==0&&reach[i][j]==houseNum){
                    result=Math.min(result,distance[i][j]);
                }
            }
        }
        return result==Integer.MAX_VALUE?-1:result;
    }


}
