import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zouxuan on 1/21/17.
 */
public class RandomizedCollection {
    HashMap<Integer, ArrayList<Integer>> map;
    ArrayList<Integer> list;

    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            map.get(val).add(list.size());
            list.add(val);
            return false;
        } else {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(list.size());
            map.put(val,tmp);
            list.add(val);
            return true;
        }
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        else{
            ArrayList<Integer> tmp=map.get(val);
            if(tmp.size()>0){
                int index=tmp.get(0);
                tmp.remove(0);
                list.remove(index);
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        if(list.size()>0){
            return list.get((int)(list.size()*Math.random()));
        }
        else{
            return -1;
        }
    }
}
