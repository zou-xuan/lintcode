
import java.util.*;

/**
 * Created by zouxuan on 8/1/16.
 */
class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Point {
    int time;
    boolean flag;

    public Point(int time, boolean flag) {
        this.time = time;
        this.flag = flag;
    }
}

public class Solution {
    public int countOfAirplanes(List<Interval> airplanes) {
        ArrayList<Point> points = new ArrayList<>();
        for (Interval i : airplanes) {
            points.add(new Point(i.start, true));
            points.add(new Point(i.end, false));
        }
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.time == o2.time) return !o1.flag ? -1 : 1;
                return o1.time - o2.time;
            }
        });
        int count = 0;
        int result = 0;
        for (Point p : points) {
            if (p.flag) count++;
            else count--;
            result = Math.max(result, count);

        }
        return result;
    }

    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        ArrayList<Integer> result = new ArrayList<>();
        if (A == null || A.length == 0) return result;
        result.add(0);
        result.add(0);
        int prefix = 0;
        int minprefix = 0;
        int startIndex = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            prefix += a;
            if (prefix - minprefix > max) {
                max = prefix - minprefix;
                result.set(0, startIndex + 1);
                result.set(1, i);
            }
            if (prefix < minprefix) {
                minprefix = prefix;
                startIndex = i;
            }
        }
        return result;
    }

    public int minimumSize(int[] nums, int s) {
        int start = 0;
        int end = 0;
        int sum = 0;
        int distance = Integer.MAX_VALUE;
        while (end < nums.length) {
            while (end < nums.length && sum < s) {
                sum += nums[end];
                end++;
            }
            while (sum >= s) {
                distance = Math.min(distance, end - start);
                sum -= nums[start++];
            }
        }
        return distance == Integer.MAX_VALUE ? -1 : distance;
    }

    public int[] plusOne(int[] digits) {
        int[] result = new int[digits.length + 1];
        int carry = 1;
        for (int i = 0; i < digits.length; i++) {
            int tmp = digits[digits.length - 1 - i] + carry;
            if (tmp >= 10) {
                tmp -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            result[result.length - 1 - i] = tmp;
        }
        if (carry == 1) {
            result[0] = 1;
        } else {
            result = Arrays.copyOfRange(result, 1, result.length - 1);
        }
        return result;
    }

    public int maxArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int left = 0;
        int right = heights.length - 1;
        int shortHeight = 0;
        int result = 0;
        shortHeight = Math.min(heights[left], heights[right]);
        result = (heights.length - 1) * shortHeight;
        while (left < right) {
            while (left < right && heights[left] <= shortHeight) {
                left++;
            }
            while (left < right && heights[right] <= shortHeight) {
                right--;
            }
            shortHeight = Math.min(heights[left], heights[right]);
            result = Math.max(result, (right - left) * shortHeight);
        }
        return result;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length-1;
        while(result.size()<matrix.length*matrix[0].length){
            for(int i=left;i<=right;i++){
                result.add(matrix[top][i]);
            }
            top++;
            for(int i=top;i<=bottom;i++){
                result.add(matrix[i][right]);
            }
            right--;
            for(int i=right;i>=left;i--){
                result.add(matrix[bottom][i]);
            }
            bottom--;
            for(int i=bottom;i>=top;i--){
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
    }

    public static void main(String[] args){
        Solution s=new Solution();
        s.
    }

}
