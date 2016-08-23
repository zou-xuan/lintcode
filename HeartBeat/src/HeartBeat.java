import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zouxuan on 8/21/16.
 */
public class HeartBeat {
    HashMap<String,Integer> map;
    int k;
    public HeartBeat() {
        // initialize your data structure here
        map=new HashMap<>();
    }

    // @param slaves_ip_list a list of slaves'ip addresses
    // @param k an integer
    // @return void
    public void initialize(List<String> slaves_ip_list, int k) {
        // Write your code here
        for(String s:slaves_ip_list){
            map.put(s,Integer.MIN_VALUE);
        }
        this.k=k;
    }

    // @param timestamp current timestamp in seconds
    // @param slave_ip the ip address of the slave server
    // @return nothing
    public void ping(int timestamp, String slave_ip) {
        // Write your code here
        if(map.containsKey(slave_ip)){
            map.put(slave_ip,timestamp);
        }
    }

    // @param timestamp current timestamp in seconds
    // @return a list of slaves'ip addresses that died
    public List<String> getDiedSlaves(int timestamp) {
        List<String> result=new ArrayList<>();
        int startTime=timestamp-2*k;
        if(startTime<=0) return result;
        for(String s:map.keySet()){
            if(map.get(s)<s=tartTime){
                result.add(s);
            }
        }
        return result;
    }
}
