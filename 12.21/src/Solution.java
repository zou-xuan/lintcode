import com.sun.tools.doclint.Env;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Created by zouxuan on 12/21/16.
 */

class Envelope {
    int width;
    int height;

    public Envelope(int width, int height) {
        this.width = width;
        this.height = height;
    }
}

public class Solution {
    public int[] countBits(int num) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        int totalsize = 2;
        while (totalsize <= num) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.add(list.get(i) + 1);
            }
            totalsize += size;
        }
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            for (int j = i - 1; j > 0; j--) {
                dp[i] = Math.max(dp[i], (i - j) * dp[j]);
                dp[i] = Math.max(dp[i], (i - j) * j);
            }
        }
        return dp[n];
    }

    public int maxEnvelopes(int[][] envelopes) {
        int max=1;
        if(envelopes==null||envelopes.length==0||envelopes[0].length==0) return 0;
        ArrayList<Envelope> list = new ArrayList<>();
        for (int i = 0; i < envelopes.length; i++) {
            list.add(new Envelope(envelopes[i][0], envelopes[i][1]));
        }
        Collections.sort(list, new Comparator<Envelope>() {
            @Override
            public int compare(Envelope o1, Envelope o2) {
                return o1.width-o2.width;
            }
        });
        int[] result = new int[list.size()];
        result[0] = 1;
        for (int i = 1; i < result.length; i++) {
            result[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (canPutAtoB(list.get(j),list.get(i))){
                    result[i]=Math.max(result[i],1+result[j]);
                }
            }
            max=Math.max(max,result[i]);
        }
        return max;
    }

    public boolean canPutAtoB(Envelope a, Envelope b) {
        if(a.width<b.width&&a.height<b.height){
            return true;
        }
        return false;
    }

    public boolean isReflected(int[][] points) {
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        HashSet<String> set=new HashSet<>();
        for(int i=0;i<points.length;i++){
            min=Math.min(min,points[i][0]);
            max=Math.max(max,points[i][0]);
            set.add(points[i][0]+","+points[i][1]);

        }
        int sum=max+min;
        for (int i=0;i<points.length;i++){
            String str=(sum-points[i][0])+","+points[i][1];
            if(!set.contains(str)){
                return false;
            }
        }
        return true;
    }
}
