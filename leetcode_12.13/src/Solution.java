import sun.java2d.pipe.SolidTextRenderer;

import java.util.*;

/**
 * Created by zouxuan on 12/13/16.
 */


public class Solution {
    public String alienOrder(String[] words) {
        HashMap<Character, HashSet<Character>> map = initGraph(words);
        HashMap<Character, Integer> countMap = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        for (String s : words) {
            for (char c : s.toCharArray()) {
                countMap.put(c, 0);
            }
        }
        for (char c : map.keySet()) {
            for (char neighbor : map.get(c)) {
                countMap.put(neighbor, countMap.get(neighbor) + 1);
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (char c : map.keySet()) {
            if (countMap.get(c) == 0) {
                queue.offer(c);
            }
        }
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char neighbor : map.get(c)) {
                countMap.put(neighbor, countMap.get(neighbor) - 1);
                if (countMap.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        if (sb.length() != countMap.size()) {
            return "";
        }
        return sb.toString();
    }

    private HashMap<Character, HashSet<Character>> initGraph(String[] words) {
        HashMap<Character, HashSet<Character>> result = new HashMap<>();
        for (String s : words) {
            for (char c : s.toCharArray()) {
                result.put(c, new HashSet<>());
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String curt = words[i];
            String next = words[i + 1];
            int length = Math.min(curt.length(), next.length());
            for (int j = 0; j < length; j++) {
                if (curt.charAt(j) != next.charAt(j)) {
                    if (result.containsKey(curt.charAt(j))) {
                        result.get(curt.charAt(j)).add(next.charAt(j));
                    } else {
                        HashSet<Character> set = new HashSet<>();
                        set.add(next.charAt(j));
                        result.put(curt.charAt(j), set);
                    }
                    break;
                }
            }
        }
        return result;
    }

    public String alienOrder2(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
        Map<Character, Integer> degree = new HashMap<Character, Integer>();
        String result = "";
        if (words == null || words.length == 0) return result;
        for (String s : words) {
            for (char c : s.toCharArray()) {
                degree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            int length = Math.min(cur.length(), next.length());
            for (int j = 0; j < length; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = new HashSet<Character>();
                    if (map.containsKey(c1)) set = map.get(c1);
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;
                } else {
                    if (j + 1 <= cur.length() - 1 && j + 1 > next.length() - 1) return "";
                }
            }
        }
        Queue<Character> q = new LinkedList<Character>();
        for (char c : degree.keySet()) {
            if (degree.get(c) == 0) q.add(c);
        }
        while (!q.isEmpty()) {
            char c = q.remove();
            result += c;
            if (map.containsKey(c)) {
                for (char c2 : map.get(c)) {
                    degree.put(c2, degree.get(c2) - 1);
                    if (degree.get(c2) == 0) q.add(c2);
                }
            }
        }
        if (result.length() != degree.size()) return "";
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] words = new String[]{"wrtkj", "wrt"};
        s.alienOrder2(words);
    }
}
