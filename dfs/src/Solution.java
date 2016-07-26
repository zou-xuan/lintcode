import java.util.*;

/**
 * Created by zouxuan on 7/26/16.
 */
class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
};

public class Solution {

    public boolean hasRoute(ArrayList<DirectedGraphNode> graph,
                            DirectedGraphNode s, DirectedGraphNode t) {
        Queue<DirectedGraphNode> queue=new LinkedList<>();
        HashSet<DirectedGraphNode> set=new HashSet<>();
        queue.offer(s);
        set.add(s);
        while(!queue.isEmpty()){
            DirectedGraphNode n=queue.poll();
            if(n==t) return true;
            ArrayList<DirectedGraphNode> neighbors=n.neighbors;
            for(DirectedGraphNode node:neighbors){
                if(!set.contains(node)){
                    queue.offer(node);
                    set.add(node);
                }
            }
        }
        return false;
    }

    public boolean validTree(int n, int[][] edges) {
        if(edges==null||edges.length==0||edges[0].length==0) {
            if(n==1) return true;
            else return false;
        }
        HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
        for(int i=0;i<edges.length;i++){
            if(map.containsKey(edges[i][0])){
                map.get(edges[i][0]).add(edges[i][1]);
            }else{
                ArrayList<Integer> list=new ArrayList<>();
                list.add(edges[i][1]);
                map.put(edges[i][0],list);
            }
            if(map.containsKey(edges[i][1])){
                map.get(edges[i][1]).add(edges[i][0]);
            }else{
                ArrayList<Integer> list=new ArrayList<>();
                list.add(edges[i][0]);
                map.put(edges[i][1],list);
            }
        }
        boolean[] visited=new boolean[n];
        Arrays.fill(visited,false);
        for(int i=0;i<n;i++){
            if(!map.containsKey(i)){
                return false;
            }
            if(!visited[i]){
                if(hasCycle(map,visited,i,-1)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasCycle(HashMap<Integer,ArrayList<Integer>> map,boolean[] visited,int v,int parent){
        visited[v]=true;
        for(Integer i:map.get(v)){
            if(i==parent){
                continue;
            }
            if(visited[i]){
                return true;
            }
            if(hasCycle(map,visited,i,v)){
                return true;
            }
        }
        return false;
    }
}
