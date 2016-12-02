import java.util.*;

/**
 * Created by zouxuan on 11/20/16.
 */
public class NBComparator {
    public int cmp(String a, String b);
}


public class Solution {

    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        if (nums == null || nums.length == 0 || k == 0) return result;
        for (int i = 0; i < k; i++) {
            inDeque(deque, nums[i]);
        }
        result.add(deque.peekFirst());
        for (int i = k; i < nums.length; i++) {
            inDeque(deque, nums[i]);
            outDeque(deque, nums[i]);
            result.add(deque.peekFirst());
        }
        return result;
    }

    public void inDeque(Deque<Integer> deque, int num) {
        while (!deque.isEmpty() && deque.peekLast() < num) {
            deque.pollLast();
        }
        deque.offer(num);
    }

    public void outDeque(Deque<Integer> deque, int num) {
        if (deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                j = Math.max(j, map.get(c) + 1);
            }
            map.put(c, i);
            maxLength = Math.max(maxLength, i - j + 1);

        }
        return maxLength;
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;
        int[] array = new int[256];
        int distinct = 0;
        int j = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (array[c] == 0) {
                distinct++;
            }
            array[c]++;
            while (distinct > k) {
                char tmp = s.charAt(j);
                array[tmp]--;
                if (array[tmp] == 0) {
                    distinct--;
                }
                j++;
            }
            length = Math.max(length, i - j + 1);
        }
        return length;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        int length = s.length();
        String result=null;
        int maxLength=Integer.MIN_VALUE;
        boolean[][] isPalin = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            isPalin[i][i] = true;
            maxLength=1;
            result=s.substring(i,i+1);
        }
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)){
                isPalin[i][i + 1] = true;
                maxLength=2;
                result=s.substring(i,i+2);
            }
        }
        for(int len=3;len<=length;len++){
            for(int i=0;i<=length-len;i++){
                if(s.charAt(i)==s.charAt(i+len-1)&&isPalin[i+1][i+len-2]){
                    isPalin[i][i+len-1]=true;
                    if(len>maxLength){
                        result=s.substring(i,i+len);
                        maxLength=len;
                    }
                }
            }
        }
        return result;
    }

    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        if(A==null||A.length==0||A[0].length==0) return 0;
        int row=A.length;
        int column=A[0].length;
        boolean[][] flag=new boolean[row][column];
        int[][] result=new int[row][column];
        for(int i=0;i<row;i++){
            Arrays.fill(result[i],1);
        }
        int max=Integer.MIN_VALUE;
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                result[i][j]=search(A,i,j,result,flag);
                max=Math.max(result[i][j],max);
            }
        }
        return max;
    }

    private int search(int[][] A,int i,int j,int[][] result,boolean[][] flag){
        if(flag[i][j]){
            return result[i][j];
        }
        flag[i][j]=true;
        int[] m={1,-1,0,0};
        int[] n={0,0,1,-1};
        int row=A.length;
        int column=A[0].length;
        for(int k=0;k<4;k++){
            if(i+m[k]>=0&&i+m[k]<row&&j+n[k]>=0&&j+n[k]<column&&A[i][j]>A[i+m[k]][j+n[k]]){
                result[i][j]=Math.max(result[i][j],search(A,i+m[k],j+n[k],result,flag)+1);
            }
        }
        return result[i][j];
    }
}
