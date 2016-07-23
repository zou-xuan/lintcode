import java.util.HashMap;

/**
 * Created by zouxuan on 7/23/16.
 */
class TrieNode {
    // Initialize your data structure here.
    Character c;
    HashMap<Character, TrieNode> map;
    boolean isLeaf;

    public TrieNode() {
        map = new HashMap<>();
        isLeaf = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        insert(root.map, word, 0);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return search(root.map, word, 0);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchStartwith(root.map, prefix, 0);
    }

    private void insert(HashMap<Character, TrieNode> map, String word, int index) {
        if (index >= word.length()) {
            return;
        }
        Character c = word.charAt(index);
        if (map.containsKey(c)) {
            insert(map.get(c).map, word, index + 1);
        } else {
            TrieNode node = new TrieNode();
            node.c = c;
            map.put(c, node);
            if(index==word.length()-1) node.isLeaf=true;
            insert(map.get(c).map, word, index + 1);
        }
    }

    private boolean search(HashMap<Character, TrieNode> map, String word, int index) {
        if (index == word.length()-1) {
            if () {
                return true;
            } else {
                return false;
            }
        } else {
            Character c = word.charAt(index);
            if (map.containsKey(c)) {
                return search(map.get(c).map, word, index + 1);
            } else {
                return false;
            }
        }
    }

    private boolean searchStartwith(HashMap<Character, TrieNode> map, String word, int index) {
        if (index == word.length()) {
            return true;
        } else {
            Character c = word.charAt(index);
            if (map.containsKey(c)) {
                return searchStartwith(map.get(c).map, word, index + 1);
            } else {
                return false;
            }
        }

    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("lintcode");
        t.search("lintcode");
    }


}
