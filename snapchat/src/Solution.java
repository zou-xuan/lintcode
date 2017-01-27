import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by zouxuan on 11/9/16.
 */
public class Solution {
    static String[] simpleWords(String[] words) {
        if(words==null||words.length<=1) return words;
        HashSet<String> dict=new HashSet<>();
        for(String s:words){
            dict.add(s);
        }
        HashSet<String> set=new HashSet<>();
        for(String word:words){
            if(canBreak(word,dict)){
                set.add(word);
            }
        }
        String[] result=new String[dict.size()-set.size()];
        int count=0;
        for(String s:words){
            if(!set.contains(s)){
                result[count++]=s;
            }
        }
        return result;
    }

    static boolean canBreak(String word, HashSet<String> dict){
        boolean[] canBreak=new boolean[word.length()+1];
        dict.remove(word);
        canBreak[0]=true;
        for(int i=1;i<canBreak.length;i++){
            for(int j=i-1;j>=0;j--){
                if(canBreak[j]&&dict.contains(word.substring(j,i))){
                    canBreak[i]=true;
                    break;
                }
            }
        }
        dict.add(word);
        return canBreak[word.length()];
    }
}
