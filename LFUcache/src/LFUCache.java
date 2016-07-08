import java.util.HashMap;

/**
 * Created by zouxuan on 7/8/16.
 */
class CacheNode {
    int key;
    int value;
    int frequency;

    public CacheNode(int key, int value) {
        this.key=key;
        this.value=value;
        frequency=0;
    }
}

public class LFUCache {
    HashMap<Integer, Integer> valueMap = new HashMap<>();
    HashMap<Integer, Integer> freqMap = new HashMap<>();
    int capacity;


    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        if (valueMap.size() < capacity) {
            valueMap.put(key, value);
        } else {

        }
    }

    public int get(int key) {
        // Write your code here
    }

    private void addFrequency(int key) {

    }
}
