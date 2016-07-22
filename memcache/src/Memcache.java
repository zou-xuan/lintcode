import com.sun.tools.corba.se.idl.InterfaceGen;

import java.util.HashMap;

/**
 * Created by zouxuan on 7/22/16.
 */
class Value{
    int value;
    int time;
    public Value(int value,int time){
        this.value=value;
        this.time=time;
    }
}

public class Memcache {
    HashMap<Integer,Value> map;
    public Memcache() {
        // Initialize your data structure here.
        map=new HashMap<>();
    }

    public int get(int curtTime, int key) {
        // Write your code here
        if(!map.containsKey(key)) return Integer.MAX_VALUE;
        Value v=map.get(key);
        if(v.time>=curtTime) return v.value;
        else{
            map.remove(key);
            return Integer.MAX_VALUE;
        }

    }

    public void set(int curtTime, int key, int value, int ttl) {
        // Write your code here
        Value v;
        if (ttl==0){
            v=new Value(value,Integer.MAX_VALUE);
        }else{
            v=new Value(value,curtTime+ttl-1);
        }
        map.put(key,v);

    }

    public void delete(int curtTime, int key) {
        // Write your code here
        if(map.containsKey(key)){
            map.remove(key);
        }
    }

    public int incr(int curtTime, int key, int delta) {
        // Write your code here
        if(map.containsKey(key)){
            Value v=map.get(key);
            if(v.time>=curtTime){
                v.value+=delta;
                return v.value;
            }
        }
        return Integer.MAX_VALUE;
    }

    public int decr(int curtTime, int key, int delta) {
        // Write your code here
        if(map.containsKey(key)){
            Value v=map.get(key);
            if(v.time>=curtTime){
                v.value-=delta;
                return v.value;
            }
        }
        return Integer.MAX_VALUE;
    }
}
