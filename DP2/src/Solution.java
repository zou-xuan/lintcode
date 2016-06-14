import java.util.Arrays;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * Created by zouxuan on 16/5/27.
 */
public class Solution {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] distance = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            distance[i][0] = i;
        }
        for (int i = 0; i < len2 + 1; i++) {
            distance[0][i] = i;
        }
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    distance[i][j] = Math.min(distance[i - 1][j - 1],
                            Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1));
                } else {
                    distance[i][j] = Math.min(distance[i - 1][j - 1] + 1,
                            Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1));
                }
            }
        }
        return distance[len1][len2];
    }

    public int numDistinct(String S, String T) {
        int lenS = S.length();
        int lenT = T.length();
        int[][] sub = new int[lenS + 1][lenT + 1];
        for (int i = 0; i < lenS + 1; i++) {
            sub[i][0] = 1;
        }
        for (int i = 1; i < lenT + 1; i++) {
            sub[0][i] = 0;
        }
        for (int i = 1; i < lenS + 1; i++) {
            for (int j = 1; j < lenT + 1; j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    sub[i][j] = sub[i - 1][j - 1] + sub[i - 1][j];
                } else {
                    sub[i][j] = sub[i - 1][j];
                }
            }
        }
        return sub[lenS][lenT];
    }

    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] canbreak = new boolean[s.length() + 1];
        int wordLength = getMaxLength(dict);
        Arrays.fill(canbreak, false);
        canbreak[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = i - 1; j >= Math.max(i - wordLength, 0); j--) {
                if (canbreak[j] && dict.contains(s.substring(j, i))) {
                    canbreak[i] = true;
                    break;
                }
            }
        }
        return canbreak[s.length()];
    }

    private int getMaxLength(Set<String> dict) {
        int maxLength = Integer.MIN_VALUE;
        for (String s : dict) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }
        return maxLength;
    }

    public int minCut(String s) {
        int[] minNum = new int[s.length() + 1];
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        for (int len = 2; len < s.length(); len++) {
            for (int start = 0; start + len < s.length(); start++) {
                isPalindrome[start][start + len] = isPalindrome[start + 1][start + len - 1] &&
                        (s.charAt(start) == s.charAt(start + len));
            }
        }
        minNum[0] = 0;
        for (int i = 1; i < s.length() + 1; i++) {
            int tmp = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    int cut = minNum[j] + 1;
                    if (cut < tmp) {
                        tmp = cut;
                    }
                }
            }
            minNum[i] = tmp;
        }
        return minNum[s.length()] - 1;
    }

    public int longestCommonSubstring(String A, String B) {
        int[][] commonSub = new int[A.length() + 1][B.length() + 1];
        int result = 0;
        for (int i = 0; i < A.length() + 1; i++) {
            commonSub[i][0] = 0;
        }
        for (int i = 0; i < B.length() + 1; i++) {
            commonSub[0][i] = 0;
        }
        for (int i = 1; i < A.length() + 1; i++) {
            for (int j = 1; j < B.length() + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    commonSub[i][j] = commonSub[i - 1][j - 1] + 1;
                    if (commonSub[i][j] > result) {
                        result = commonSub[i][j];
                    }
                } else
                    commonSub[i][j] = 0;
            }
        }
        return result;
    }

    public int longestCommonSubsequence(String A, String B) {
        int[][] commonSub = new int[A.length() + 1][B.length() + 1];
        int result = 0;
        for (int i = 0; i < A.length() + 1; i++) {
            commonSub[i][0] = 0;
        }
        for (int i = 0; i < B.length() + 1; i++) {
            commonSub[0][i] = 0;
        }
        for (int i = 1; i < A.length() + 1; i++) {
            for (int j = 1; j < B.length() + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    commonSub[i][j] = Math.max(commonSub[i - 1][j - 1] + 1,
                            Math.max(commonSub[i - 1][j], commonSub[i][j - 1]));
                } else {
                    commonSub[i][j] = Math.max(commonSub[i - 1][j], commonSub[i][j - 1]);
                }
            }
        }
        return commonSub[A.length()][B.length()];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()==0&&s2.length()==0&&s3.length()!=0) return false;
        if(s3.length()!=s1.length()+s2.length()) return false;
        boolean[][] interleave = new boolean[s1.length() + 1][s2.length() + 1];
        interleave[0][0] = true;
        for (int i = 1; i < s1.length() + 1; i++) {
            if (s1.substring(0,i).equals(s3.substring(0,i))) {
                interleave[i][0] = true;
            }
        }
        for (int i = 1; i < s2.length() + 1; i++) {
            if (s2.substring(0,i).equals(s3.substring(0,i))) {
                interleave[0][i] = true;
            }
        }
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                interleave[i][j] = (interleave[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1))) ||
                        (interleave[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1)));
            }
        }
        return interleave[s1.length()][s2.length()];
    }

    public static void main(String[] args){
        Solution s=new Solution();
        s.isInterleave("aa","a","aaa");
    }


}


