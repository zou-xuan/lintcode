import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouxuan on 9/24/16.
 */

class TrieNode {
    TrieNode[] children;
    String word;

    public TrieNode() {
        children = new TrieNode[26];
        word = null;
    }

    public void insert(String word, int index) {
        if (index == word.length()) {
            this.word = word;
            return;
        }
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null) {
            children[pos] = new TrieNode();
        }
        children[pos].insert(word, index + 1);
    }
}

public class Solution {

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if(board==null||board.length==0||board[0].length==0||words==null||words.length==0){
            return result;
        }
        TrieNode root = buildTrie(words);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                dfs(board,i,j,root,result);
            }
        }
        return result;
    }

    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> result) {
        char c = board[i][j];
        if (c == '#' || p.children[c - 'a'] == null) {
            return;
        }
        p = p.children[c - 'a'];
        if (p.word != null) {
            result.add(p.word);
            p.word = null;
        }
        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j, p, result);
        if (j > 0) dfs(board, i, j - 1, p, result);
        if (i < board.length - 1) dfs(board, i + 1, j, p, result);
        if (i < board[0].length - 1) dfs(board, i, j + 1, p, result);
        board[i][j]=c;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String s : words) {
            root.insert(s, 0);
        }
        return root;
    }
}
