import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zouxuan on 1/19/17.
 */
public class Solution {

    public int getStep(int num) {
        if (num <= 1) return 0;
        if (num == 2) return 1;
        int result = Integer.MAX_VALUE;
        for (int i = num - 1; i > num / 2; i--) {
            int tmp = getStep(i, num - i);
            result = Math.min(result, tmp > 0 ? tmp : Integer.MAX_VALUE);
        }
        return result;
    }

    private int getStep(int left, int right) {
        if (left == 1 && right == 1) return 1;
        if (left == right || left <= 0 || right <= 0) return -1;
        if (left < right) {
            int tmp = left;
            left = right;
            right = tmp;
        }
        int next = getStep(left - right, right);
        if (next == -1) return -1;
        return next + 1;

    }


    public List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        return dfs(map, s, wordDict);
    }

    private ArrayList<String> dfs(HashMap<String, ArrayList<String>> map, String s, List<String> dict) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        ArrayList<String> result = new ArrayList<>();
        if (s.length() == 0) {
            result.add("");
            return result;
        }
        for (String word : dict) {
            if (s.startsWith(word)) {
                List<String> sublist = dfs(map, s.substring(word.length()), dict);
                for (String sub : sublist) {
                    result.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }
        map.put(s, result);
        return result;
    }

    public ArrayList<ArrayList<String>> wordBreakI(String s, HashSet<String> set) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        dfs(result, path, s, set, 0);
        return result;
    }

    private void dfs(ArrayList<ArrayList<String>> result, ArrayList<String> path, String s, HashSet<String> set, int index) {
        if (index >= s.length()) {
            result.add(new ArrayList<>(path));
        }
        for (int i = index + 1; i < s.length(); i++) {
            String tmp = s.substring(index, i + 1);
            if (set.contains(tmp)) {
                path.add(tmp);
                dfs(result, path, s, set, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    public ArrayList<ArrayList<String>> wordBreakII(String s, HashSet<String> set) {
        HashMap<Integer, ArrayList<ArrayList<String>>> map = new HashMap<>();
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        map.put(s.length(), list);
        for (int i = s.length() - 1; i >= 0; i--) {
            ArrayList<ArrayList<String>> value = new ArrayList<>();
            for (int j = i + 1; j <= s.length(); j++) {
                if (set.contains(s.substring(i, j))) {
                    for (ArrayList<String> sub : map.get(j)) {
                        ArrayList<String> tmp = new ArrayList<>(sub);
                        tmp.add(0, s.substring(i, j));
                        value.add(tmp);
                    }
                }
            }
            map.put(i, value);
        }
        return map.get(0);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        HashSet set = new HashSet();
        set.add("the");
        set.add("quick");
        set.add("bro");
        set.add("brown");
        set.add("fox");
        set.add("ox");
        set.add("own");
        set.add("ownf");
        System.out.println(s.wordBreakI("thequickbrownfox", set));
    }
}
