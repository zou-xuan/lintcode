
/**
 * Created by zouxuan on 9/11/16.
 */
class WordNode {
    WordNode[] nodes;
    boolean isWord;

    public WordNode() {
        nodes = new WordNode[26];
        isWord = false;
    }

    public void insert(String word, int index) {
        if (index == word.length()) {
            isWord = true;
        } else {
            int pos = word.charAt(index) - 'a';
            if (nodes[pos] == null) {
                nodes[pos] = new WordNode();
            }
            nodes[pos].insert(word, index + 1);
        }
    }

    public boolean find(String word, int index) {
        if (index == word.length()) {
            return isWord;
        } else {
            boolean result = false;
            char c = word.charAt(index);
            if (c == '.') {
                for (WordNode node : nodes) {
                    if (node != null) {
                        result |= node.find(word, index + 1);
                    }
                }
            } else {
                int pos = c - 'a';
                if (nodes[pos] == null) {
                    return false;
                }
                result |= nodes[pos].find(word, index + 1);
            }
            return result;
        }
    }
}


public class WordDictionary {
    WordNode root;

    public WordDictionary() {
        root = new WordNode();
    }

    public void addWord(String word) {
        root.insert(word, 0);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return root.find(word,0);
    }
}
