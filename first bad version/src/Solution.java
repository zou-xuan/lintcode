/**
 * Created by zouxuan on 16/5/24.
 */

public class SVNRepo {
        public static boolean isBadVersion(int k);
}

public class Solution {

    public int findFirstBadVersion(int n) {
        int start=1;
        int end=n;
        while(start+1<end){
            int mid=start+(end-start)/2;
            if(SVNRepo.isBadVersion(mid)){
                end=mid;
            }
            else{
                start=mid;
            }
        }
        if(SVNRepo.isBadVersion(start))
            return start;
        else return end;
    }
}
