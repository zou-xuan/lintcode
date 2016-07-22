import java.util.*;

/**
 * Created by zouxuan on 7/22/16.
 */
public class Solution2 {
    static int range;
    static int nodes;
    HashSet<Integer> set=new HashSet<>();
    HashMap<Integer,Integer> map=new HashMap<>();

    public static Solution2 create(int n, int k) {
        // Write your code here
        range=n;
        nodes=k;
        return new Solution2();
    }

    // @param machine_id an integer
    // @return a list of shard ids
    public List<Integer> addMachine(int machine_id) {
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<nodes;){
            int rand=(int)(Math.random()*range);
            if(!set.contains(rand)){
                i++;
                set.add(rand);
                map.put(rand,machine_id);
                result.add(rand);
            }
        }
        return result;
    }

    // @param hashcode an integer
    // @return a machine id
    public int getMachineIdByHashCode(int hashcode) {
        for(int i=hashcode;i<range;i++){
            if(set.contains(i)){
                return map.get(i);
            }
        }
        for(int i=0;i<hashcode;i+++){
            if(set.contains(i)){
                return map.get(i);
            }
        }
        return -1;
    }
}
