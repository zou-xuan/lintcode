import java.util.LinkedHashMap;

/**
 * Created by zouxuan on 16/6/17.
 */
public class Solution {
    LinkedHashMap<Integer,Integer> map;
    private int capacity;

    public Solution(int capacity) {
        map=new LinkedHashMap<>(capacity);
        this.capacity=capacity;
    }

    // @return an integer
    public int get(int key) {
        if(map.containsKey(Integer.valueOf(key))){
            return map.get(key).intValue();
        }else {
            return -1;
        }
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if(map.size()<capacity){
            map.put(Integer.valueOf(key),Integer.valueOf(value));
        } else{
            map.re
        }
    }
}
