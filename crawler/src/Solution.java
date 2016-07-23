import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zouxuan on 7/23/16.
 */
class Document {
    public int id;
    public String content;
}

public class Solution {
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        Map<String,List<Integer>> result=new HashMap<>();
        for(Document d:docs){
            int id=d.id;
            String content=d.content;
            String[] split=content.split(" ");
            for(String s:split){
                if(s.equals("")) continue;
                if(result.containsKey(s)){
                    if(!result.get(s).contains(id)){
                        result.get(s).add(id);
                    }
                }else{
                    List<Integer> list=new ArrayList<>();
                    list.add(id);
                    result.put(s,list);
                }
            }
        }
        return result;
    }

    public List<String> parseUrls(String content) {
        List<String> result=new ArrayList<>();
        String pattern="\\shref\\s*=\\s*\"*(.*?)\"*(\\s|>)";
        Pattern r=Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        Matcher matcher=r.matcher(content);
        while(matcher.find()){
            String url=matcher.group(1);
            if(url.length()==0||url.startsWith("#")){
                continue;
            }
            result.add(url);
        }
        return result;
    }

    public static void main(String[] args){
        Solution s=new Solution();
        s.parseUrls("<html>\n" +
                "  <body>\n" +
                "    <div>\n" +
                "      <a href=\"http://www.google.com\" class=\"text-lg\">Google</a>\n" +
                "      <a href=\"http://www.facebook.com\" style=\"display:none\">Facebook</a>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "      <a href=\"https://www.linkedin.com\">Linkedin</a>\n" +
                "      <a href = \"http://github.io\">LintCode</a>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>");

    }
}
