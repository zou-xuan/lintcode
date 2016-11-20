import java.util.HashMap;

/**
 * Created by zouxuan on 11/11/16.
 */
class ApiCall {
    public final String action;
    public final long elapsed;
    public final boolean success;
    public final int userID;

    public ApiCall(String action, long elapsed, boolean success, int userID) {
        this.action = action;
        this.elapsed = elapsed;
        this.success = success;
        this.userID = userID;
    }
}

class Statistics {
    long total_count;
    long total_success;
    long total_elapsed;
    double avg_elapsed;

    public Statistics(int count, int success, long elapsed) {
        this.total_count = count;
        this.total_success = success;
        this.total_elapsed = elapsed;
    }
}


public class Tracker {
    HashMap<String, Statistics> map_action;
    HashMap<String, Statistics> map_userId;
    HashMap<String, Statistics> map_action_userId;


    public Tracker() {
        map_action = new HashMap<>();
        map_userId = new HashMap<>();
        map_action_userId = new HashMap<>();
    }

    public void addCall(ApiCall call) {
        addCallByParameter(map_action, call, call.action);
        addCallByParameter(map_userId, call, call.userID + "");
        addCallByParameter(map_action_userId, call, call.action + call.userID);
    }

    private void addCallByParameter(HashMap<String, Statistics> map,
                                    ApiCall call, String key) {
        int success = call.success ? 1 : 0;
        long elapsed = call.elapsed;
        if (!map.containsKey(key)) {
            map.put(key, new Statistics(1, success, elapsed));
        } else {
            Statistics s = map.get(key);
            s.total_count++;
            s.total_success += success;
            s.total_elapsed += elapsed;
            map.put(key, s);
        }
    }

    public HashMap<String, Statistics> getAndReset(boolean isAction, boolean isUserId) throws Exception {
        if(!isAction&&!isUserId){
            throw new Exception("Invalid Input!");
        }
        HashMap<String,Statistics> map;
        if(isAction&&isUserId){
            map=map_action_userId;
        }
        else if(isAction){
            map=map_action;
        }
        else{
            map=map_userId;
        }
        for (String key : map.keySet()) {
            Statistics s = map.get(key);
            s.avg_elapsed = s.total_elapsed * 1.0 / s.total_count;
            map.put(key, s);
        }
        HashMap<String, Statistics> result = new HashMap<>(map);
        map.clear();
        return result;
    }

}
