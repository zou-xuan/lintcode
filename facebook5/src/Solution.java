import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zouxuan on 9/15/16.
 */
public class Solution {
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : split) {
            if (!s.equals("")) {
                if (s.equals(".")) {
                    continue;
                } else if (s.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    stack.push(s);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) return "/";
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        return new String(sb);
    }

    public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        int prefix = 0;
        map.put(k, -1);
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (map.containsKey(prefix)) {
                int length = i - map.get(prefix);
                if (length > maxLength) {
                    maxLength = length;
                }
            }
            if (!map.containsKey(k + prefix)) {
                map.put(k + prefix, i);
            }
        }
        return maxLength;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] height = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0) {
                    height[i][j] = matrix[i][j] == '1' ? 1 : 0;
                } else {
                    height[i][j] = matrix[i][j] == '1' ? height[i - 1][j] + 1 : 0;
                }
            }
        }
        int result = 0;
        for (int i = 0; i < row; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < column; j++) {
                while (!stack.isEmpty() && height[i][j] <= height[i][stack.peek()]) {
                    int pos = stack.pop();
                    result = Math.max(result, height[i][pos] * (stack.isEmpty() ? j : j - stack.peek() - 1));
                }
                stack.push(j);
            }
            while (!stack.isEmpty()) {
                int pos = stack.pop();
                result = Math.max(result, height[i][pos] * (stack.isEmpty() ? column : column - stack.peek() - 1));
            }
        }
        return result;
    }

    public void wallsAndGates(int[][] rooms) {
        if(rooms==null||rooms.length==0||rooms[0].length==0) return;
        int row=rooms.length;
        int column=rooms[0].length;
        Queue<Point> queue=new LinkedList<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if(rooms[i][j]==0){
                    queue.offer(new Point(i,j));
                    int level=0;
                    while(!queue.isEmpty()){
                        int size=queue.size();
                        for(int k=0;k<size;k++){
                            Point point=queue.poll();
                            if(rooms[point.x][point.y]<level){
                                continue;
                            }
                            else{
                                rooms[point.x][point.y]=level;
                            }
                            if(point.x-1>=0&&rooms[point.x-1][point.y]!=-1){
                                queue.offer(new Point(point.x-1,point.y));
                            }
                            if(point.x+1<=row-1&&rooms[point.x+1][point.y]!=-1){
                                queue.offer(new Point(point.x+1,point.y));
                            }
                            if(point.y-1>=0&&rooms[point.x][point.y-1]!=-1){
                                queue.offer(new Point(point.x,point.y-1));
                            }
                            if(point.y+1<=column-1&&rooms[point.x][point.y+1]!=-1){
                                queue.offer(new Point(point.x,point.y+1));
                            }
                        }
                        level++;
                    }
                }
            }
        }
    }
}
