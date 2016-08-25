import java.util.*;

/**
 * Created by zouxuan on 8/24/16.
 */
public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<>();
        if (start.equals(end)) {
            List<String> l = new ArrayList<>();
            l.add(start);
            l.add(end);
            result.add(l);
            return result;
        }
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        HashMap<String, Integer> distance = new HashMap<>();
        bfs(map, distance, start, end, dict);
        List<String> path=new ArrayList<>();
        dfs(result,path,end,start,map,distance);
        return result;

    }

    void bfs(HashMap<String, ArrayList<String>> map, HashMap<String, Integer> distance,
             String start, String end, Set<String> dict) {
        Queue<String> queue = new LinkedList<>();
        for (String s : dict) {
            map.put(s, new ArrayList<>(String));
        }
        queue.offer(start);
        distance.put(start, 0);
        while (!queue.isEmpty()) {
            String curt = queue.poll();
            ArrayList<String> expand = expand(curt, dict);
            for (String s : expand) {
                map.get(s).add(curt);
                if (!distance.containsKey(s)) {
                    distance.put(s, distance.get(curt) + 1);
                    queue.offer(s);
                }
            }
        }
    }

    private ArrayList<String> expand(String curt, Set<String> dict) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < curt.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != curt.charAt(i)) {
                    String s = curt.substring(0, i) + c + curt.substring(i + 1);
                    if (dict.contains(s)) {
                        list.add(s);
                    }
                }
            }
        }
        return list;
    }

    private void dfs(List<List<String>> result, List<String> path, String curt,
                     String start, HashMap<String, ArrayList<String>> map,
                     HashMap<String, Integer> distance) {
        path.add(curt);
        if (curt.equals(start)) {
            List<String> tmp = new ArrayList<>(path);
            Collections.reverse(tmp);
            result.add(tmp);
        } else {
            for (String next : map.get(curt)) {
                if (distance.containsKey(next) && distance.get(curt) == distance.get(next) + 1) {
                    dfs(result,path,next,start,map,distance);
                }
            }
        }
        path.remove(path.size()-1);
    }
}
