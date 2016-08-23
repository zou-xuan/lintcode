import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zouxuan on 8/19/16.
 */
public class RateLimiter {
    HashMap<String,ArrayList<Integer>> map;
    public RateLimiter(){
        map=new HashMap<>();
    }

    public boolean isRatelimited(int timestamp, String event, String rate, boolean increment) {
        String[] split=rate.split("/");
        int limit=Integer.parseInt(split[0]);
        int totalTime=0;
        switch (split[1].charAt(0)){
            case 's':
                totalTime=1;
                break;
            case 'm':
                totalTime=60;
                break;
            case 'h':
                totalTime=60*60;
                break;
            case 'd':
                totalTime=24*60*60;
                break;
        }
        int startTime=timestamp-totalTime+1;
        boolean check=check(event,startTime,limit);
        if(check){
            if(!increment){
                return false;
            }
            if(map.containsKey(event)){
                map.get(event).add(timestamp);
            }else{
                ArrayList<Integer> list=new ArrayList<>();
                list.add(timestamp);
                map.put(event,list);
            }
            return false;
        }else{
            return true;
        }
    }

    private boolean check(String event,int startTime,int limit){
        int count=0;
        if(!map.containsKey(event)){
            return true;
        }else{
            ArrayList<Integer> list=map.get(event);
            for(Integer i:list){
                if(i>=startTime){
                    count++;
                }
            }
        }
        if(count>=limit){
            return false;
        }
        return true;
    }
}
