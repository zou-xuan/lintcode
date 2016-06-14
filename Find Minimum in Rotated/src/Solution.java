/**
 * Created by zouxuan on 16/5/22.
 */
public class Solution {

    public int findMin(int[] num) {

        int target=num[num.length-1];
        int start=0;
        int end=num.length-1;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(num[mid]<=target) end=mid;
            else start=mid;
        }
        if(num[start]<target) return num[start];
        return num[end];
    }
}
