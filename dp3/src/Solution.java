/**
 * Created by zouxuan on 7/6/16.
 */
public class Solution {

    public int numDecodings(String s) {
        if(s==null||s.length()==0) return 0;
        int[] result=new int[s.length()+1];
        result[0]=1;
        result[1]=1;
        for(int i=2;i<result.length;i++){
            int prefix=s.charAt(i-2)-'0';
            if(prefix==1||(prefix==2&&s.charAt(i-1)<='6')){
                result[i]=result[i-2];
            }
            result[i]+=result[i-1];

        }
        return result[result.length-1];
    }
}
