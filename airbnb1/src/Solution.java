import java.util.*;

/**
 * Created by zouxuan on 9/27/16.
 */


public class Solution {
    public String alienOrder(String[] words) {
        ArrayList<HashSet<Character>> list = formGraph(words);
        HashMap<Character, Integer> map = new HashMap<>();
        ArrayList<Character> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                HashSet<Character> tmp = list.get(i);
                for (Character c : tmp) {
                    if (!map.containsKey(c)) {
                        map.put(c, 1);
                    } else {
                        map.put(c, map.get(c) + 1);
                    }
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                if (!map.containsKey((char) i)) {
                    queue.offer((char) i);
                    result.add((char) i);
                }
            }
        }
        while (!queue.isEmpty()) {
            char tmp = queue.poll();
            HashSet<Character> set = list.get(tmp);
            for (Character c : set) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    result.add(c);
                    queue.offer(c);
                }
            }
        }
        if (result.size() != map.size()) return "";
        StringBuilder sb = new StringBuilder();
        for (char c : result) {
            sb.append(c);
        }
        return new String(sb);

    }

    private ArrayList<HashSet<Character>> formGraph(String[] words) {
        ArrayList<HashSet<Character>> result = new ArrayList<>(256);
        for (String s : words) {
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    continue;
                }
                char c = s.charAt(i);
                if (result.get(c) == null) {
                    result.add(c, new HashSet<>());
                }
                if (!result.get(c).contains(s.charAt(i + 1))) {
                    result.get(c).add(s.charAt(i + 1));
                }
            }
        }
        return result;
    }



    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int[] a = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] b = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                update(board, a, b, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int num = board[i][j];
                if (num < -3 || num > 4 || (num >= -2 && num <= 2)) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = 1;
                }
            }
        }
    }

    private void update(int[][] board, int[] a, int[] b, int i, int j) {
        int count = 0;
        for (int index = 0; index < a.length; index++) {
            int new_i = i + a[index];
            int new_j = j + b[index];
            if (new_i >= 0 && new_i < board.length && new_j >= 0 && new_j < board[0].length) {
                if (board[new_i][new_j] > 0) {
                    count++;
                }
            }
        }
        if (board[i][j] == 0) {
            board[i][j] -= count;
        } else {
            board[i][j] += count;
        }
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return result;
        }
        String path = "";
        HashMap<String, List<String>> map = new HashMap<>();
        dfs(result, map, path, wordDict, s, 0);
        return result;
    }

    public void dfs(List<String> result, HashMap<String, List<String>> map, String path,
                    Set<String> wordDict, String s, int index) {
        if (map.containsKey(s.substring(index))) {
            result.addAll(map.get(s.substring(index)));
            return;
        }
        if (index == s.length()) {
            result.add(path.trim());
            return;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(index, i))) {
                String tmp = path + " " + s.substring(index, i);
                dfs(result, map, tmp, wordDict, s, i);
            }
        }
        map.put(s.substring(index), new ArrayList<>(result));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s="catsanddog";
        String[] words = {"cat", "cats", "and", "sand", "dog"};
        Set<String> set=new HashSet<>();
        for(String tmp:words){
            set.add(tmp);
        }
        solution.wordBreak(s,set);
    }


}
