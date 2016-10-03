import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zouxuan on 10/1/16.
 */
class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
};

class UnionFind {
    HashMap<Integer, Integer> father = new HashMap<>();

    public UnionFind(ArrayList<DirectedGraphNode> nodes) {
        for (DirectedGraphNode node : nodes) {
            father.put(node.label, node.label);
        }
    }

    public UnionFind(int from, int to) {
        for (int i = from; i <= to; i++) {
            father.put(i, i);
        }
    }

    public int find(int x) {
        int parent = father.get(x);
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        int tmp = -1;
        while (x != father.get(x)) {
            tmp = father.get(x);
            father.put(x, parent);
            x = tmp;
        }
        return parent;
    }

    public void union(int x, int y) {
        int parent_x = find(x);
        int parent_y = find(y);
        if (parent_x != parent_y) {
            father.put(parent_x, parent_y);
        }
    }
}

public class Solution {

    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        UnionFind uf = new UnionFind(nodes);
        for (DirectedGraphNode node : nodes) {
            ArrayList<DirectedGraphNode> neighbors = node.neighbors;
            for (DirectedGraphNode neighbor : neighbors) {
                int parent1 = uf.find(node.label);
                int parent2 = uf.find(neighbor.label);
                if (parent1 != parent2) {
                    uf.union(parent1, parent2);
                }
            }
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (DirectedGraphNode node : nodes) {
            int parent = uf.find(node.label);
            if (!map.containsKey(parent)) {
                List<Integer> list = new ArrayList<>();
                list.add(node.label);
                map.put(parent, list);
            } else {
                map.get(parent).add(node.label);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (Integer i : map.keySet()) {
            result.add(map.get(i));
        }
        return result;
    }

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        if(n<=0||m<=0||operators==null||operators.length==0) return new ArrayList<Integer>();
        UnionFind uf = new UnionFind(0, n * m - 1);
        List<Integer> result = new ArrayList<>();
        int[][] islands = new int[n][m];
        int[] a = {-1, 1, 0, 0};
        int[] b = {0, 0, -1, 1};
        int count = 0;
        for (Point p : operators) {
            int x = p.x;
            int y = p.y;
            islands[x][y] = 1;
            count++;
            for (int i = 0; i < 4; i++) {
                int new_x = x + a[i];
                int new_y = y + b[i];
                if (new_x >= 0 && new_x < n && new_y >= 0 && new_y < m) {
                    if (islands[new_x][new_y] == 1) {
                        int index1 = x * m + y;
                        int index2 = new_x * m + new_y;
                        if(uf.find(index1)!=uf.find(index2)){
                            uf.union(index1,index2);
                            count--;
                        }
                    }
                }

            }
            result.add(count);
        }
        return result;
    }
}
