import java.util.*;

/**
 * Created by zouxuan on 7/21/16.
 */
public class Tweet {
    public int id;
    public int user_id;
    public String text;

    public static Tweet create(int user_id, String tweet_text) {
        // This will create a new tweet object,
        // and auto fill id
    }
}

public class MiniTwitter {
    HashMap<Integer, Stack<Tweet>> tweets;
    HashMap<Integer, ArrayList<Integer>> friends;


    public MiniTwitter() {
        tweets = new HashMap<>();
        friends = new HashMap<>();
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        Tweet t = Tweet.create(user_id, tweet_text);
        if (tweets.containsKey(user_id)) {
            tweets.get(user_id).push(t);
        } else {
            Stack<Tweet> stack = new Stack<>();
            stack.push(t);
            tweets.put(user_id, stack);
        }
        return t;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        List<Tweet> result = new ArrayList<>();
        ArrayList<Integer> myfriends = new ArrayList<>();
        if (friends.containsKey(user_id)) {
            myfriends = friends.get(user_id);
        }
        for (Integer i : myfriends) {
            result.addAll(getTimeline(i));
        }
        result.addAll(getTimeline(user_id));
        Collections.sort(result, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                if (o1.id > o2.id) return -1;
                return 1;
            }
        });
        if (result.size() < 10) return result;
        else {
            return result.subList(0, 10);
        }
    }

    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet> getTimeline(int user_id) {
        List<Tweet> result = new ArrayList<>();
        if(!tweets.containsKey(user_id)){
            return result;
        }
        Stack<Tweet> stack = tweets.get(user_id);
        int count = 0;
        Iterator<Tweet> it = stack.iterator();
        while (it.hasNext() && count <= 10) {
            result.add(it.next());
            count++;
        }
        Collections.sort(result, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                if (o1.id > o2.id) return -1;
                return 1;
            }
        });
        return result;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        if (friends.containsKey(from_user_id)) {
            friends.get(from_user_id).add(to_user_id);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(to_user_id);
            friends.put(from_user_id, list);
        }
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        if (friends.containsKey(from_user_id)) {
            friends.get(from_user_id).remove(Integer.valueOf(to_user_id));
        }
    }
}
