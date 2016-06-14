/**
 * Created by zouxuan on 16/5/22.
 */
public class Solution {
    public int findPeak(int[] A) {
        int start=1;
        int end=A.length-2;
        while (start+1<end){
            int mid=start+(start-end)/2;
            if(A[mid]<A[mid-1]){
                end=mid;
            }
            else if (A[mid]<A[mid+1]) {
                start=mid;
            }
            else end=mid;
        }
        if(A[start]<A[end]){
            return end;
        }
        return start;
    }
}
