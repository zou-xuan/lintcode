import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zouxuan on 10/14/16.
 */
public class Solution {
    static String[] missingWords(String s, String t) {
        String[] split_s=s.split(" ");
        String[] split_t=t.split(" ");
        ArrayList<String> list=new ArrayList<>();
        for(String tmp:split_s){
            list.add(tmp);
        }
        for(String tmp:split_t){
            list.remove(tmp);
        }
        String[] result=new String[list.size()];
        for(int i=0;i<list.size();i++){
            result[i]=list.get(i);
        }
        return result;

    }

    static String[] missingWords2(String s, String t) {
        String[] split_s = s.split(" ");
        String[] split_t = t.split(" ");
        int s_index=0;
        int t_index=0;
        ArrayList<String> list=new ArrayList<>();
        while(s_index!=split_s.length&&t_index!=split_t.length){
            if(split_s[s_index].equals(split_t[t_index])){
                s_index++;
                t_index++;
            }
            else{
                while(s_index!=split_s.length&&t_index!=split_t.length&&!split_s[s_index].equals(split_t[t_index])){
                    list.add(split_s[s_index]);
                    s_index++;
                }
            }
        }
        while(s_index!=split_s.length){
            list.add(split_s[s_index]);
            s_index++;
        }
        String[] result=new String[list.size()];
        for(int i=0;i<list.size();i++){
            result[i]=list.get(i);
        }
        return result;

    }

    static String[] buildSubsequences(String s) {
        ArrayList<String> result=new ArrayList<>();
        LinkedList<String> list=new LinkedList<>();
        Queue<String> queue=new LinkedList<>();
        list.
        result.add("");
        subsequenceHelper(s,0,result);
        Collections.sort(result);
        String[] array=new String[result.size()-1];
        for(int i=1;i<result.size();i++){
            array[i-1]=result.get(i);
        }
        return array;
    }

    static void subsequenceHelper(String s,int index,ArrayList<String> result) {
        if(index==s.length()) return;
        int size=result.size();
        for(int i=0;i<size;i++){
            result.add(result.get(i)+s.charAt(index));
        }
        subsequenceHelper(s,index+1,result);
    }

    static void arrangeCoins(long[] coins) {
        for(int i=0;i<coins.length;i++){
            long result=coinHelper2(coins[i]);
            System.out.println(result);
        }
    }

    static long coinHelper(long coin){
        long left=1;
        long right=(long)Math.sqrt(2*coin);
        while(left+1<right){
            long mid=left+(right-left)/2;
            long sum=mid*(1+mid)/2;
            if(sum>coin){
                right=mid;
            }else if(sum<coin){
                left=mid;
            }else{
                return mid;
            }
        }
        long right_sum=right*(1+right)/2;
        if(right_sum<=coin){
            return right;
        }
        long left_sum=left*(1+left)/2;
        if(left_sum<=coin){
            return left;
        }
        return -1;
    }

    static long coinHelper2(long coin){
        return (int)(Math.sqrt(coin*8+1)-1)/2;
    }

    public static void main(String[] args){
        System.out.println(coinHelper(1));
        
    }


}
