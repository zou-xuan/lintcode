/**
 * Created by zouxuan on 9/11/16.
 */
public class Solution {
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] distance = new int[length1 + 1][length2 + 1];
        for (int i = 0; i <= length1; i++) {
            distance[i][0] = i;
        }
        for (int i = 0; i <= length2; i++) {
            distance[i][0] = i;
        }
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = 1 + Math.min(distance[i - 1][j - 1],
                            Math.min(distance[i - 1][j], distance[i][j - 1]));
                }
            }
        }
        return distance[length1][length2];
    }

    public boolean isOneEditDistance(String s, String t) {
        int lengthDiff=s.length()-t.length();
        if(lengthDiff<-1||lengthDiff>1) return false;
        int i=0,j=0;
        int count=0;
        while (i<s.length()&&j<t.length()){
            if(s.charAt(i)==t.charAt(j)){
                i++; j++;
            }
            else{
                count++;
                if(lengthDiff==0){
                    i++; j++;
                }else if(lengthDiff>0){
                    i++;
                }else{
                    j++;
                }
            }
        }
        if(i!=s.length()||j!=t.length()){
            return count==0;
        }
        return count==1;
    }
}
