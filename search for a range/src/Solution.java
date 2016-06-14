/**
 * Created by zouxuan on 16/5/24.
 */
public class Solution {

    public int[] searchRange(int[] A, int target) {
        int[] result=new int[2];
        if(A==null||A.length==0){
            result[0]=-1;
            result[1]=-1;
            return result;
        }
        int start=0;
        int end=A.length-1;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(A[mid]>=target){
                end=mid;
            }
            else
                start=mid;
        }
        if(A[start]==target){
            result[0]=start;
        }
        else if(A[end]==target){
            result[0]=end;
        }
        else{
            result[0]=result[1]=-1;
            return result;
        }

        start=0;
        end=A.length-1;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(A[mid]<=target){
                start=mid;
            }
            else
                end=mid;
        }
        if(A[end]==target){
            result[1]=end;
        }
        else if(A[start]==target){
            result[1]=start;
        }
        else{
            result[0]=result[1]=-1;
            return result;
        }
    }
}
