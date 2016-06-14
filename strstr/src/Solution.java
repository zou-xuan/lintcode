/**
 * Created by zouxuan on 16/5/22.
 */
public class Solution {
    public int strStr(String source, String target) {
        if(source==null||target==null) return -1;
        int sourceLen=source.length();
        int targetLen=target.length();
        for(int i=0;i<sourceLen-targetLen+1;i++){

                int j=0;
                for(;j<targetLen;j++){
                    if(source.charAt(i+j)!=target.charAt(j)) {
                        break;
                    }
                }
                if(j==targetLen){
                    return i;
                }
            }
        return -1;
    }
}
