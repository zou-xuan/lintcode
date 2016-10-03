import java.util.ArrayList;

/**
 * Created by zouxuan on 10/1/16.
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
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            root.insert(w, 0);
        }
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, board, root, result);
            }
        }
        return result;
    }

    private void dfs(int i, int j, char[][] board, TrieNode node, ArrayList<String> result) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        if (board[i][j] == '#') return;
        char c = board[i][j];
        if (node.children[c - 'a'] == null) return;
        TrieNode next = node.children[c - 'a'];
        if (next.word != null) {
            if (!result.contains(next.word)) {
                result.add(next.word);
            }
        }
        int[] a = {-1, 1, 0, 0};
        int[] b = {0, 0, -1, 1};
        board[i][j] = '#';
        for (int index = 0; index < 4; index++) {
            dfs(i + a[index], j + b[index], board, next, result);
        }
        board[i][j] = c;
    }

    public void heapify(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) {
            heapHelper(A, i);
        }
    }

    public void heapHelper(int[] A, int index) {
        while (index < A.length) {
            int smallest = index;
            if (2 * index + 1 < A.length && A[2 * index + 1] < A[smallest]) {
                smallest = 2 * index + 1;
            }
            if (2 * index + 2 < A.length && A[2 * index + 2] < A[smallest]) {
                smallest = 2 * index + 2;
            }
            if (smallest == index) return;
            int tmp=A[smallest];
            A[smallest]=A[index];
            A[index]=tmp;
            index=smallest;
        }
    }
}
