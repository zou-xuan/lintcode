import java.util.*;

/**
 * Created by zouxuan on 11/8/16.
 */
public class Solution {

    static int findMutationDistance(String start, String end, String[] bank) {
        if(start.equals(end)) return 0;
        HashSet<String> dict=new HashSet<>();
        for(String s:bank){
            dict.add(s);
        }
        HashSet<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int level = 0;
        queue.offer(start);
        set.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                ArrayList<String> expand=expand(s,dict);
                if (expand.contains(end)) {
                    return level;
                }
//                for (String candidate : bank) {
//                    if (!set.contains(candidate) && isNeighbor(s, candidate)) {
//                        queue.offer(candidate);
//                        set.add(candidate);
//                    }
//                }
                for(String tmp:expand){
                    if(!set.contains(tmp)){
                        queue.offer(tmp);
                        set.add(tmp);
                    }
                }
            }
        }
        return -1;
    }

//    private static boolean isNeighbor(String lhs, String rhs) {
//        int diff = 0;
//        for (int i = 0; i < lhs.length(); i++) {
//            if (lhs.charAt(i) != rhs.charAt(i)) {
//                diff++;
//            }
//        }
//        return diff == 1;
//    }

    private static ArrayList<String> expand(String curt, HashSet<String> dict) {
        ArrayList<String> list = new ArrayList<>();
        char[] chars={'A','C','G','T'};
        for (int i = 0; i < curt.length(); i++) {
            for (char c :chars) {
                if (c != curt.charAt(i)) {
                    String s = curt.substring(0, i) + c + curt.substring(i + 1);
                    if (dict.contains(s)) {
                        list.add(s);
                    }
                }
            }
        }
        return list;
    }

    static int[] getRecommendedTweets(int[][] followGraph_edges, int[][] likeGraph_edges,
                                      int targetUser, int minLikeThreshold) {
        HashSet<Integer> followers=new HashSet<>();
        for(int i=0;i<followGraph_edges.length;i++){
            if(followGraph_edges[i][0]==targetUser){
                followers.add(followGraph_edges[i][1]);
            }
        }
        HashMap<Integer,Integer> likeMap=new HashMap<>();
        for(int i=0;i<likeGraph_edges.length;i++){
            if(followers.contains(likeGraph_edges[i][0])){
                if(likeMap.containsKey(likeGraph_edges[i][1])){
                    likeMap.put(likeGraph_edges[i][1],likeMap.get(likeGraph_edges[i][1])+1);
                }
                else{
                    likeMap.put(likeGraph_edges[i][1],1);
                }
            }
        }
        System.out.println(likeMap.size());
        ArrayList<Map.Entry<Integer,Integer>> list =new ArrayList<>(likeMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        ArrayList<Integer> resultArray=new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry:list){
            if(entry.getValue()>=minLikeThreshold){
                resultArray.add(entry.getKey());
            }
            else{
                break;
            }
        }
        int[] result=new int[resultArray.size()];
        for(int i=0;i<resultArray.size();i++){
            result[i]=resultArray.get(i);
        }
        return result;
    }
}
