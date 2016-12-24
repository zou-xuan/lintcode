import com.sun.source.tree.ArrayAccessTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by zouxuan on 12/23/16.
 */
public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        n = n > 10 ? 10 : n;
        if (n == 0) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 9;
        int sum = 10;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] * (11 - i);
            sum += dp[i];
        }
        return sum;
    }

    public String rearrangeString(String str, int k) {
        ArrayList<String> result=new ArrayList<>();
        String path="";
        HashMap<Character,Integer> map=new HashMap<>();
        HashMap<Character,Integer> charMap=new HashMap<>();
        for(char c:str.toCharArray()){
            map.put(c,-k);
            charMap.put(c,charMap.getOrDefault(c,0)+1);
        }
        dfs(result,path,charMap,map,k,str.length());
        if(result.size()==0){
            return "";
        }
        else{
            return result.get(0);
        }


    }

    private void dfs(ArrayList<String> result, String path,HashMap<Character, Integer> charMap, HashMap<Character, Integer> map, int k, int length) {
        if(result.size()!=0) return;
        if (path.length() == length) {
            result.add(path);
        } else {
            for (char c : charMap.keySet()) {
                if (charMap.get(c)>0&&path.length() - map.get(c) >= k) {
                    int index=map.get(c);
                    map.put(c,path.length());
                    charMap.put(c,charMap.get(c)-1);
                    dfs(result,path+c,charMap,map,k,length);
                    map.put(c,index);
                    charMap.put(c,charMap.get(c)+1);
                }
            }
        }
    }

    public static void main(String[] args){
        Solution s=new Solution();
        s.rearrangeString("aabbcc",3);
    }
}
