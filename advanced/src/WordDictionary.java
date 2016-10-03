/**
 * Created by zouxuan on 10/1/16.
 */
class TrieNode {
    TrieNode[] children;
    boolean hasWord;

    public TrieNode() {
        children = new TrieNode[26];
        hasWord = false;
    }

    public void insert(String word, int index) {
        if (index == word.length()) {
            this.hasWord = true;
            return;
        }
        int pos = word.charAt(index) - 'a';
        if (children[pos] == null) {
            children[pos] = new TrieNode();
        }
        children[pos].insert(word, index + 1);
    }

    public boolean find(String word, int index) {
        if (index == word.length()) {
            return this.hasWord;
        }
        boolean result = false;
        if (word.charAt(index) == '.') {
            for (int i = 0; i < 26; i++) {
                if (children[i] != null) {
                    result |= children[i].find(word, index + 1);
                }
            }
        } else {
            int pos = word.charAt(index) - 'a';
            if (children[pos] == null) {
                return false;
            }
            result = children[pos].find(word, index + 1);
        }
        return result;
    }
}

public class WordDictionary {
    TrieNode root = new TrieNode();

    public void addWord(String word) {
        root.insert(word, 0);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return root.find(word,0);
    }
}
