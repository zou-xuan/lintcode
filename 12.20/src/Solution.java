import java.util.*;

/**
 * Created by zouxuan on 12/20/16.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ReturnType {
    int sum;
    int min;
    int max;

    public ReturnType(int sum, int min, int max) {
        this.sum = sum;
        this.min = min;
        this.max = max;
    }
}


public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        dfs(result, new StringBuffer(), word, 0, 0);
        return result;
    }

    private void dfs(List<String> result, StringBuffer sb, String word, int i, int k) {
        int len = sb.length();
        if (i == word.length()) {
            if (k != 0) sb.append(k);
            result.add(sb.toString());
        } else {
            dfs(result, sb, word, i + 1, k + 1);
            if (k != 0) {
                sb.append(k);
            }
            sb.append(word.charAt(i));
            dfs(result, sb, word, i + 1, 0);
        }
        sb.setLength(len);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0 || lower > upper) return 0;
        long[] prefix = new long[nums.length];
        int count = 0;
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                long tmp = prefix[j] - prefix[i] + nums[i];
                if (tmp >= lower && tmp <= upper) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<String> findItinerary(String[][] tickets) {
        List<String> result = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            String key = tickets[i][0];
            String value = tickets[i][1];
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(value);
        }
        List<String> path = new ArrayList<>();
        path.add("JFK");
        dfs(map, result, path, tickets.length + 1, "JFK");
        return result;
    }

    private boolean isSmaller(List<String> left, List<String> right) {
        for (int i = 0; i < left.size(); i++) {
            if (left.get(i).compareTo(right.get(i)) < 0) {
                return true;
            } else if (left.get(i).compareTo(right.get(i)) > 0) {
                return false;
            }
        }
        return false;
    }

    public void dfs(HashMap<String, ArrayList<String>> map,
                    List<String> result, List<String> path, int size, String current) {
        if (path.size() == size) {
            if (result.size() == 0) {
                result.addAll(new ArrayList<>(path));
            } else {
                if (isSmaller(path, result)) {
                    result.clear();
                    result.addAll(new ArrayList<>(path));
                }
            }
        } else {
            if (map.containsKey(current) && map.get(current).size() != 0) {
                ArrayList<String> list = map.get(current);
                ArrayList<String> tmp = new ArrayList<>(list);
                for (int i = 0; i < tmp.size(); i++) {
                    String next = tmp.get(i);
                    path.add(next);
                    list.remove(next);
                    dfs(map, result, path, size, next);
                    list.add(next);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] tickets = new String[][]{{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        s.findItinerary(tickets);

    }

    int maxNum=0;

    public int largestBSTSubtree(TreeNode root) {
        getSubtree(root);
        return maxNum;
    }

    private ReturnType getSubtree(TreeNode root){
        if(root==null){
            return new ReturnType(0,0,0);
        }
        ReturnType left=getSubtree(root.left);
        ReturnType right=getSubtree(root.right);
        if(left.sum==-1||right.sum==-1){
            return new ReturnType(-1,0,0);
        }
        if(left.sum!=0&&right.sum!=0){
            if(left.max<root.val&&right.min>root.val){
                maxNum=Math.max(maxNum,left.sum+right.sum+1);
                return new ReturnType(left.sum+right.sum+1,left.min,right.max);
            }else{
                return new ReturnType(-1,0,0);
            }
        }
        else if(left.sum==0&&right.sum!=0){
            if(right.min>root.val){
                maxNum=Math.max(maxNum,left.sum+right.sum+1);
                return new ReturnType(left.sum+right.sum+1,root.val,right.max);
            }else{
                return new ReturnType(-1,0,0);
            }
        }
        else if(right.sum==0&&left.sum!=0){
            if(left.max<root.val){
                maxNum=Math.max(maxNum,left.sum+right.sum+1);
                return new ReturnType(left.sum+right.sum+1,left.min,root.val);
            }else{
                return new ReturnType(-1,0,0);
            }
        }
        else{
            maxNum=Math.max(maxNum,1);
            return new ReturnType(1,root.val,root.val);
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result=new ArrayList<>();
        HashMap<String,Integer> map=new HashMap<>();
        if(words==null|| words.length<2){
            return result;
        }
        for(int i=0;i<words.length;i++){
            map.put(words[i],i);
        }
        for(int i=0;i<words.length;i++){
            String word=words[i];
            for(int j=0;j<=word.length();j++){
                String str1=word.substring(0,j);
                String str2=word.substring(j);
                if(isPalindrome(str1)){
                    String rev2=new StringBuffer(str2).reverse().toString();
                    if(map.containsKey(rev2)&&map.get(rev2)!=i){
                        List<Integer> tmp=new ArrayList<>();
                        tmp.add(map.get(rev2));
                        tmp.add(i);
                        result.add(tmp);
                    }
                }
                if(isPalindrome(str2)){
                    String rev1=new StringBuffer(str1).reverse().toString();
                    if(map.containsKey(rev1)&&map.get(rev1)!=i&&str2.length()!=0){
                        List<Integer> tmp=new ArrayList<>();
                        tmp.add(i);
                        tmp.add(map.get(rev1));
                        result.add(tmp);
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String word){
        int left=0;
        int right=word.length()-1;
        while(left<=right){
            if(word.charAt(left)!=word.charAt(right)) return false;
        }
        return true;
    }
}
