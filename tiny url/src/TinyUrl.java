import java.util.HashMap;

/**
 * Created by zouxuan on 8/19/16.
 */
public class TinyUrl {
    private static char[] keys=new char[62];
    private static HashMap<String,String> shortToLong;
    private static HashMap<String,String> longToShort;
    public TinyUrl(){
        for(int i=0;i<26;i++){
            keys[i]=(char)('a'+i);
            keys[i+26]=(char)('A'+i);
        }
        for(int i=0;i<10;i++){
            keys[i+52]=(char)('0'+i);
        }
        shortToLong=new HashMap<>();
        longToShort=new HashMap<>();
    }
    public String longToShort(String url) {
        if(longToShort.containsKey(url)){
            return longToShort.get(url);
        }
        String key=generateKey();
        String shortUrl="http://tiny.url/"+key;
        shortToLong.put(shortUrl,url);
        longToShort.put(url,shortUrl);
        return shortUrl;
    }

    private String generateKey(){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<6;i++){
            int index=(int)(Math.random()*62);
            sb.append(keys[index]);
        }
        String result=new String(sb);
        if (shortToLong.containsKey(result)){
            return generateKey();
        }else{
            return result;
        }
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        if(shortToLong.containsKey(url)){
            return shortToLong.get(url);
        }
        else return null;
    }

    String createCustom(String long_url, String short_key) {
        String short_url="http://tiny.url/"+short_key;
        if(longToShort.containsKey(long_url)){
            if(longToShort.get(long_url).equals(short_url)){
                return short_url
            }else{
                return "error";
            }
        }
        if(shortToLong.containsKey(short_url)){
            if(shortToLong.get(short_url).equals(long_url)) return short_url;
            else{
                return "error";
            }
        }
        shortToLong.put(short_url,long_url);
        longToShort.put(long_url,short_url);
        return short_url;
    }
}
