/**
 * Created by zouxuan on 16/5/30.
 */
public class Solution {

    public int sqrt(int x) {
        if(x==0) return 0;
        int left = 0;
        int right = x;
        while(left+1<right){
            int mid=left+(right-left)/2;
            if(mid==x/mid){
                return mid;
            }
            else if(mid>x/mid){
                right=mid;
            }
            else{
                left=mid;
            }
        }
        if(right<=x/right){
            return right;
        }
        return left;
    }

    public int woodCut(int[] L, int k) {
        if(L==null||L.length==0) return 0;
        int start=1;
        int end=L[0];
        for(int i=0;i<L.length;i++){
            if(L[i]>end){
                end=L[i];
            }
        }
        while(start+1<end){
            int mid=start+(end-start)/2;
            int piece=0;
            for(int i=0;i<L.length;i++){
                piece+=L[i]/mid;
            }
            if(piece>=k){
                start=mid;
            }
            else{
                end=mid;
            }
        }
        int pieceEnd=0;
        int pieceStart=0;
        for(int i=0;i<L.length;i++){
            pieceEnd+=L[i]/end;
            pieceStart+=L[i]/start;
        }
        if(pieceEnd==k){
            return end;
        }
        if(pieceStart==k){
            return start;
        }
        return 0;
    }

    public static void main(String[] args){
        Solution s=new Solution();
        System.out.print(s.sqrt(2147483647));
    }
}
