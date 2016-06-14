/**
 * Created by zouxuan on 16/5/24.
 */
public class Solution {
    public int search(int[] A, int target) {
        if(A==null||A.length==0) return -1;
        int start=0;
        int end=A.length;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(A[mid]==target) return mid;
            if(A[start]<A[mid]){
                if(A[start]<=target&&A[mid]>target){
                    end=mid;
                }
                else{
                    start=mid;
                }
            }
            else{
                if(target>A[mid]&&target<=A[end]){
                    start=mid;
                }
                else
                    end=mid;
            }
        }
        if(A[start]==target){
            return start;
        }
        if(A[end]==target){
            return end;
        }
        return -1;
    }
}
