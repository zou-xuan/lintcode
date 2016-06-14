/**
 * Created by zouxuan on 16/5/22.
 */
public class Solution {

    public int searchInsert(int[] A, int target) {
        if(A==null||A.length==0) return 0;
        int start=0;
        int end=A.length-1;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(A[mid]==target){
                return mid;
            }
            else if(A[mid]<target){
                start=mid;
            }
            else end=mid;
        }
        if(A[start]>=target){
            return start;
        }
        else if(A[end]>=target){
            return end;
        }
        return end+1;
    }
}
