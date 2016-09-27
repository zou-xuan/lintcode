import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouxuan on 9/20/16.
 */
public class Codec {
    public String encode(List<String> strs) {
        StringBuilder sb=new StringBuilder();
        for(String s:strs){
            sb.append(s.length()).append("/").append(s);
        }
        return new String(sb);
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result=new ArrayList<>();
        int i=0;
        while(i<s.length()){
            int slash=s.indexOf('/',i);
            int length=Integer.parseInt(s.substring(i,slash));
            result.add(s.substring(slash+1,slash+length+1));
            i=slash+length+1;
        }
        return result;
    }
}
