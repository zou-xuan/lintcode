import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by zouxuan on 10/24/16.
 */
class Edge {
    int position;
    int height;
    boolean isStart;

    public Edge(int position, int height, boolean isStart) {
        this.position = position;
        this.height = height;
        this.isStart = isStart;
    }
}

class TrieNode {
    TrieNode[] children;
    String word;

    public TrieNode() {
        children = new TrieNode[26];
        word = null;
    }

    public void insert(String word,int index){
        if(index==word.length()){
            this.word=word;
            return;
        }
        int pos=word.charAt(index)-'a';
        if(children[pos]==null){
            children[pos]=new TrieNode();
        }
        children[pos].insert(word,index+1);
    }
}

public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] distance = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < distance.length; i++)
            for (int i = 0; i < distance[0].length; i++) {
                distance[0][i] = i;
            }
        for (int i = 1; i < distance.length; i++) {
            for (int j = 1; j < distance[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distance[i][j] = Math.min(distance[i - 1][j - 1], Math.min(distance[i - 1][j],
                            distance[i][j - 1]) + 1);
                } else {
                    distance[i][j] = Math.min(distance[i - 1][j - 1], Math.min(distance[i - 1][j],
                            distance[i][j - 1])) + 1;
                }
            }
        }
        return distance[word1.length()][word2.length()];
    }

    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return result;
        }
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            Edge startEdge = new Edge(buildings[i][0], buildings[i][2], true);
            edgeList.add(startEdge);
            Edge endEdge = new Edge(buildings[i][1], buildings[i][2], false);
            edgeList.add(endEdge);
        }
        Collections.sort(edgeList, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.position != o2.position) {
                    return o1.position - o2.position;
                }
                if (o1.isStart && o2.isStart) {
                    return o2.height - o1.height;
                }
                if (!o1.isStart && !o2.isStart) {
                    return o1.height - o2.height;
                }
                return !o1.isStart ? -1 : 1;
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        ArrayList<Integer> now;
        for (Edge e : edgeList) {
            if (e.isStart) {
                if (queue.isEmpty() || e.height > queue.peek()) {
                    now = new ArrayList<>();
                    now.add(e.position);
                    now.add(e.height);
                    result.add(now);
                }
                queue.add(e.height);
            } else {
                queue.remove(e.height);
                if (queue.isEmpty() || e.height > queue.peek()) {
                    if (queue.isEmpty()) {
                        now = new ArrayList<>();
                        now.add(e.position);
                        now.add(0);
                    } else {
                        now = new ArrayList<>();
                        now.add(e.position);
                        now.add(queue.peek());
                    }
                    result.add(now);
                }
            }
        }
        return output(result);
    }

    private ArrayList<ArrayList<Integer>> output(ArrayList<ArrayList<Integer>> result) {
        ArrayList<ArrayList<Integer>> out = new ArrayList<>();
        if (result.size() > 0) {
            int prev = result.get(0).get(0);
            int height = result.get(0).get(1);
            for (int i = 1; i < result.size(); i++) {
                int curt = result.get(i).get(0);
                if (height > 0) {
                    ArrayList<Integer> now = new ArrayList<>();
                    now.add(prev);
                    now.add(curt);
                    now.add(height);
                    out.add(now);
                }
                prev = curt;
                height = result.get(i).get(1);
            }
        }
        return out;
    }

    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            root.insert(w, 0);
        }
        ArrayList<String> result=new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i,j,board,root,result);
            }
        }
        return result;
    }

    private void dfs(int i,int j,char[][] board,TrieNode node,ArrayList<String> result){
        if(i<0||i>=board.length||j<0||j>=board[0].length) return;
        if(board[i][j]=='#') return;
        char c=board[i][j];
        if(node.children[c-'a']==null) return ;
        TrieNode next=node.children[c-'a'];
        if(next.word !=null) {
            if(!result.contains(next.word)){
                result.add(next.word);
            }
        }
        int[] a ={-1,1,0,0};
        int[] b={0,0,-1,1};
        board[i][j]='#';
        for(int index=0;index<4;index++){
            dfs(i+a[index],j+b[index],board,next,result);
        }
        board[i][j]=c;
    }


}
