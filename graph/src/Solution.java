import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class UndirectedGraphNode {
	int label;
	ArrayList<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
};

class DirectedGraphNode {
	int label;
	ArrayList<DirectedGraphNode> neighbors;

	DirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<DirectedGraphNode>();
	}
};

public class Solution {

	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<>();
		if (s == null || s.length() == 0)
			return result;
		List<String> path = new ArrayList<>();
		helper(result, path, 0, s);
		return result;
	}

	private void helper(List<List<String>> result, List<String> path, int pos, String s) {
		if (pos == s.length()) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int i = pos + 1; i <= s.length(); i++) {
			String prefix = s.substring(pos, i);
			if (!isPalindrome(prefix)) {
				continue;
			}
			path.add(prefix);
			helper(result, path, i, s);
			path.remove(path.size() - 1);
		}
	}

	private boolean isPalindrome(String s) {
		if (s.length() <= 1) {
			return true;
		}
		int left = 0;
		int right = s.length() - 1;
		while (left < right) {
			if (s.charAt(left) != s.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}

	public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
		HashSet<UndirectedGraphNode> set = new HashSet<>();
		List<Integer> component = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		if (nodes == null || nodes.size() == 0)
			return result;
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		for (int i = 0; i < nodes.size(); i++) {
			component.clear();
			if (!set.contains(nodes.get(i))) {
				queue.offer(nodes.get(i));
				set.add(nodes.get(i));
				while (!queue.isEmpty()) {
					UndirectedGraphNode p = queue.poll();
					component.add(p.label);
					for (UndirectedGraphNode neighbor : p.neighbors) {
						if (!set.contains(neighbor)) {
							queue.offer(neighbor);
							set.add(neighbor);
						}
					}
				}
				Collections.sort(component);
				result.add(new ArrayList<>(component));
			}
		}
		return result;
	}

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return null;
		ArrayList<UndirectedGraphNode> nodes = getNodes(node);
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		for (UndirectedGraphNode curt : nodes) {
			map.put(curt, new UndirectedGraphNode(curt.label));
		}
		for (UndirectedGraphNode curt : nodes) {
			UndirectedGraphNode newnode = map.get(curt);
			for (UndirectedGraphNode neighbor : curt.neighbors) {
				newnode.neighbors.add(map.get(neighbor));
			}
		}
		return map.get(node);
	}

	private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
		HashSet<UndirectedGraphNode> set = new HashSet<>();
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		ArrayList<UndirectedGraphNode> result = new ArrayList<>();
		queue.offer(node);
		set.add(node);
		while (!queue.isEmpty()) {
			UndirectedGraphNode p = queue.poll();
			result.add(p);
			for (UndirectedGraphNode neighbor : p.neighbors) {
				if (!set.contains(neighbor)) {
					queue.offer(neighbor);
					set.add(neighbor);
				}
			}
		}
		return result;
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		sumHelper(result, path, 0, target, 0, candidates);
		return result;
	}

	private void sumHelper(List<List<Integer>> result, List<Integer> path, int sum, int target, int position,
			int[] candidates) {
		if (sum > target || position >= candidates.length)
			return;
		if (sum == target) {
			result.add(new ArrayList<>(path));
			return;
		}
		path.add(candidates[position]);
		sumHelper(result, path, sum + candidates[position], target, position, candidates);
		path.remove(path.size() - 1);
		path.add(candidates[position]);
		sumHelper(result, path, sum + candidates[position], target, position + 1, candidates);
		path.remove(path.size() - 1);
		sumHelper(result, path, sum, target, position + 1, candidates);
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		sumHelper2(result, path, target, 0, candidates);
		return result;
	}

	private void sumHelper2(List<List<Integer>> result, List<Integer> path, int gap, int position, int[] candidates) {
		if (gap == 0) {
			result.add(new ArrayList<>(path));
			return;
		}
		for (int i = position; i < candidates.length; i++) {
			if (gap < candidates[i]) {
				return;
			}
			path.add(candidates[i]);
			sumHelper2(result, path, gap - candidates[i], position, candidates);
			path.remove(path.size() - 1);
		}
	}
	
	public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> map=new HashMap<>();
        ArrayList<DirectedGraphNode> result=new ArrayList<>();
        for(DirectedGraphNode node:graph){
        	for(DirectedGraphNode neighbor:node.neighbors){
        		if(map.containsKey(neighbor)){
        			map.put(neighbor, map.get(neighbor)+1);
        		}
        		else{
        			map.put(neighbor,1);
        		}
        	}
        }
        Queue<DirectedGraphNode> queue=new LinkedList<>();
        for(DirectedGraphNode node:graph){
        	if(!map.containsKey(node)){
        		queue.offer(node);
        		result.add(node);
        	}
        }
        while(!queue.isEmpty()){
        	DirectedGraphNode node=queue.poll();
        	for(DirectedGraphNode neighbor:node.neighbors){
        		map.put(neighbor, map.get(neighbor)-1);
        		if(map.get(neighbor)==0){
        			queue.offer(neighbor);
        			result.add(neighbor);
        		}
        	}
        }
        return result;
    }

}
