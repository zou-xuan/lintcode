import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zouxuan on 16/5/28.
 */
public class Solution {

    public boolean anagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c]++;
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            count[c]--;
            if (count[c] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean compareStrings(String A, String B) {
        int[] count = new int[256];
        for (int i = 0; i < A.length(); i++) {
            char c = A.charAt(i);
            count[c]++;
        }
        for (int i = 0; i < B.length(); i++) {
            char c = B.charAt(i);
            count[c]--;
            if (count[c] < 0) return false;
        }
        return true;
    }

    public List<String> anagrams(String[] strs) {
        List<String> result = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String newString = new String(charArray);
            if (map.keySet().contains(newString)) {
                map.get(newString).add(s);
            } else {
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(s);
                map.put(newString, tmp);
            }
        }
        for (String s : map.keySet()) {
            if (map.get(s).size() > 1) {
                result.addAll(map.get(s));
            }
        }
        return result;
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        int minLen=Integer.MAX_VALUE;
        for(String each:strs){
            if(each.length()<minLen){
                minLen=each.length();
            }
        }
        for(int len=minLen;len>=1;len--){
            String compare=strs[0].substring(0,len);
            boolean pass=true;
            for(int i=1;i<strs.length;i++){
                if(!compare.equals(strs[i].substring(0,len))){
                    pass=false;
                    break;
                }
            }
            if(pass){
                return compare;
            }
        }
        return "";
    }
}
