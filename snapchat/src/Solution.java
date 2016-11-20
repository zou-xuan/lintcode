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
        ArrayList<String> list=new ArrayList<>();
        for(String word:words){
            if(canBreak(word,dict)){
                list.add(word);
            }
        }
        String[] result=new String[list.size()];
        for(int i=0;i<list.size();i++){
            result[i]=list.get(i);
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

    static int getMaxLength(HashSet<String> dict){
        int maxLength=Integer.MIN_VALUE;
        for(String s: dict){
            if(s.length()>maxLength){
                maxLength=s.length();
            }
        }
        return maxLength;
    }
}
