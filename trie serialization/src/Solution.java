import sun.text.normalizer.Trie;

import java.util.*;

/**
 * Created by zouxuan on 7/25/16.
 */

class TrieNode {
    public NavigableMap<Character, TrieNode> children;
    public TrieNode() {
        children = new TreeMap<Character, TrieNode>();
    }
}

public class Solution {

    public String serialize(TrieNode root) {
        if(root==null) return "";
        StringBuffer sb=new StringBuffer();
        sb.append("<");
        Iterator<Map.Entry<Character,TrieNode>> it=root.children.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Character,TrieNode> map=it.next();
            Character c=map.getKey();
            TrieNode t=map.getValue();
            sb.append(c);
            sb.append(serialize(t));
        }
        sb.append(">");
        return sb.toString();
    }


    public TrieNode deserialize(String data) {
        if(data==null||data.length()==0){
            return null;
        }
        TrieNode root=new TrieNode();
        TrieNode current=root;
        Stack<TrieNode> path=new Stack<>();
        for(Character c:data.toCharArray()){
            switch (c){
                case '<':
                    path.push(current);
                    break;
                case '>':
                    path.pop();
                    break;
                default:
                    current=new TrieNode();
                    path.peek().children.put(c,current);
            }
        }
        return root;
    }
}
