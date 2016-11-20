import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by zouxuan on 9/30/16.
 */
class Edge {
    int location;
    int length;

    public Edge(int location, int length) {
        this.length = length;
        this.location = location;
    }
}

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> columns = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        return getMid(rows) + getMid(columns);
    }

    private int getMid(ArrayList<Integer> list) {
        Collections.sort(list);
        int distance = 0;
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            distance += list.get(right--) - list.get(left++);
        }
        return distance;
    }

    public List<int[]> getSkyline(int[][] buildings) {
        ArrayList<Edge> list = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            list.add(new Edge(buildings[i][0], buildings[i][2]));
            list.add(new Edge(buildings[i][1], buildings[i][2]));
        }
        Collections.sort(list, (Edge e1, Edge e2) -> e1.location - e2.location);
        for (int i = 1; i < list.size() - 1; i++) {

        }
    }

    public int depthSum(List<NestedInteger> nestedList) {
        return depth(nestedList, 1);
    }

    public int depth(List<NestedInteger> list, int depth) {
        int result = 0;
        for (NestedInteger n : list) {
            if (n.isInteger()) {
                result += n.getInteger() * depth;
            } else {
                result += depth(n.getList(), depth + 1);
            }
        }
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
                if (map.get(c) > 1) {
                    while (map.get(c) > 1 && left <= right) {
                        char tmp = s.charAt(left);
                        map.put(tmp, map.get(tmp) - 1);
                        left++;
                    }
                }
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    int maxLength=0;
    String result=null;

    public String longestPalindrome(String s) {
        if(s.length()<2) return s;
        for(int i=0;i<s.length();i++){
            extendPalin(s,i,i);
            extendPalin(s,i,i+1);
        }
        return result;
    }

    private void extendPalin(String s,int left,int right){
        while (left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        int length=right-left-1;
        if(length>maxLength){
            maxLength=length;
            result=s.substring(left+1,right-1);
        }
    }
}
