import java.util.*;

/**
 * Created by zouxuan on 1/21/17.
 */
class Pair {
    int num;
    int freq;

    public Pair(int num, int freq) {
        this.num = num;
        this.freq = freq;
    }
}

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0 || k <= 0) return list;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        PriorityQueue<Pair> queue = new PriorityQueue<>(k, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.freq - o2.freq;
            }
        });
        for (Integer num : map.keySet()) {
            if (queue.size() < k) {
                queue.offer(new Pair(num, map.get(num)));
            } else {
                if (queue.peek().freq < map.get(num)) {
                    queue.poll();
                    queue.offer(new Pair(num, map.get(num)));
                }
            }
        }
        while (!queue.isEmpty()) {
            list.add(queue.poll().num);
        }
        Collections.reverse(list);
        return list;
    }

    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        if (s == null || s.length() == 0) {
            return 0;
        }
        int j = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)));
            }
            map.put(s.charAt(i), i);
            result = Math.max(result, i - j + 1);
        }
        return result;
    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<>();
        if (start.equals(end)) {
            List<String> tmp = new ArrayList<>();
            tmp.add(start);
            tmp.add(end);
            result.add(tmp);
            return result;
        }
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        HashMap<String, Integer> distance = new HashMap<>();
        bfs(map, distance, start, end, dict);
        List<String> path = new ArrayList<>();
        dfs(result, path, end, start, map, distance);
        return result;
    }

    private void bfs(HashMap<String, ArrayList<String>> map, HashMap<String, Integer> distance, String start,
                     String end, Set<String> dict) {
        Queue<String> queue = new LinkedList<>();
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        queue.offer(start);
        distance.put(start, 0);
        while (!queue.isEmpty()) {
            String curt = queue.poll();
            for (String next : expand(curt, dict)) {
                map.get(next).add(curt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(curt) + 1);
                    queue.offer(next);
                }
            }
        }
    }

    private ArrayList<String> expand(String curt, Set<String> dict) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < curt.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != curt.charAt(i)) {
                    String tmp = curt.substring(0, i) + c + curt.substring(i + 1);
                    if (dict.contains(tmp)) {
                        list.add(tmp);
                    }
                }
            }
        }
        return list;
    }

    private void dfs(List<List<String>> result, List<String> path, String curt, String start, HashMap<String, ArrayList<String>> map,
                     HashMap<String, Integer> distance) {
        path.add(curt);
        if (curt.equals(start)) {
            ArrayList<String> tmp = new ArrayList<>(path);
            Collections.reverse(tmp);
            result.add(tmp);
        } else {
            for (String prev : map.get(curt)) {
                if (distance.containsKey(prev) && distance.get(prev) + 1 == distance.get(curt)) {
                    dfs(result, path, prev, start, map, distance);
                }
            }
        }
        path.remove(path.size() - 1);
    }
}
