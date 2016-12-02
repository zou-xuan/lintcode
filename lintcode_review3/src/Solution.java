import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by zouxuan on 11/24/16.
 */
public class Solution {
    public int trapRainWater(int[] heights) {
        int a=0;
        int b=heights.length-1;
        int water=0;
        int leftMax=0;
        int rightMax=0;
        while(a<=b){
            leftMax=Math.max(leftMax,heights[a]);
            rightMax=Math.max(rightMax,heights[b]);
            if(leftMax<rightMax){
                water+=leftMax-heights[a];
                a++;
            }
            else{
                water+=rightMax-heights[b];
                b--;
            }
        }
        return water;
    }

    static int count(String s) {
        if(s==null||s.length()<=1){
            return 0;
        }
        int result=0;
        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i)!=s.charAt(i+1)){
                result+=subCount(s,i,i+1);
            }
        }
        return result;
    }

    static int subCount(String s,int left,int right){
        int count=0;
        int i=left;
        int j=right;
        while(i>=0&&j<s.length()){
            if(s.charAt(i)==s.charAt(left)&&s.charAt(j)==s.charAt(right)){
                i--;
                j++;
                count++;
            }
            else{
                break;
            }
        }
        return count;
    }
}
