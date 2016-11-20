
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.jar.Pack200;

/**
 * Created by zouxuan on 10/26/16.
 */
class Pair{
    int key;
    int value;
    Pair(int key,int value){
        this.key=key;
        this.value=value;
    }
}

public class Solution {
    public int solution(int[] A) {
        if(A==null||A.length==0){
            return -1;
        }
        double mean=0;
        for(int tmp:A){
            mean+=tmp;
        }
        mean/=A.length;
        int index=-1;
        double deviation=Integer.MIN_VALUE;
        for(int i=0;i<A.length;i++){
            if(Math.abs(A[i]-mean)>deviation){
                index=i;
                deviation=Math.abs(A[i]-mean);
            }
        }
        return index;
    }

    public int solution2(int K, int[] A) {
        // write your code in Java SE 8
        TreeMap<Integer,Integer> map=new TreeMap<>();
        for(int i=0;i<A.length;i++){
            if(map.containsKey(A[i])){
                map.put(A[i],map.get(A[i])+1);
            }
            else{
                map.put(A[i],1);
            }
        }
        ArrayList<Pair> list=new ArrayList<>();
        for(int tmp:map.keySet()){
            list.add(new Pair(tmp,map.get(tmp)));
        }
        int result=0;
        int left=0;
        int right=list.size();
        while(left<=right){
            int tmp=list.get(left).key+list.get(right).key;
            if(tmp>K){
                right--;
            }
            else if(tmp<K){
                left++;
            }
            else{
                int p=list.get(left).value*list.get(right).value;
                if(left!=right){
                    result+=2*p;
                }
                else result+=p;
                left++;
                right--;
            }
        }
        return result;
    }
}
