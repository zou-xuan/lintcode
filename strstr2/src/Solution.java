import apple.laf.JRSUIUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by zouxuan on 16/6/1.
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            Integer current = nums[i];
            ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
            for (ArrayList<Integer> array : result) {
                ArrayList<Integer> p = new ArrayList<>(array);
                p.add(current);
                tmp.add(p);
            }
            result.addAll(tmp);
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
        Collections.sort(S, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 >= o2 ? 1 : -1;
            }
        });
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Integer p : S) {
            if (map.containsKey(p)) {
                map.put(p, map.get(p) + 1);
            } else {
                map.put(p, 1);
            }
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());
        for (Integer i : map.keySet()) {
            int count = map.get(i);
            ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
            for (ArrayList<Integer> array : result) {
                ArrayList<Integer> p = new ArrayList<>(array);
                for (int j = 0; j < count; j++) {
                    p.add(i);
                    tmp.add(new ArrayList<>(p));
                }
            }
            result.addAll(tmp);
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null || nums.size() == 0) {
            return result;
        }
        ArrayList<Integer> path = new ArrayList<>();
        permuteHelper(nums, result, path);
        return result;
    }

    private void permuteHelper(ArrayList<Integer> nums, ArrayList<ArrayList<Integer>> result,
                               ArrayList<Integer> path) {
        if (path.size() == nums.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (Integer i : nums) {
            if (!path.contains(i)) {
                path.add(i);
                permuteHelper(nums, result, path);
                path.remove(path.size() - 1);
            }
        }

    }

    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        if(nums==null||nums.size()==0) return result;
        ArrayList<Integer> path=new ArrayList<>();
        HashMap<Integer,Integer> map=new HashMap<>();
        for(Integer i:nums){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }
            else{
                map.put(i,1);
            }
        }
        permuteUniqueHelper(map,result,path,nums.size());
        return result;
    }

    private void permuteUniqueHelper(HashMap<Integer, Integer> map, ArrayList<ArrayList<Integer>> result,
                                     ArrayList<Integer> path, int size) {
        if (path.size() == size) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(Integer num:map.keySet()){
            int count=0;
            for(Integer i:path){
                if(i==num) count++;
            }
            if(count<map.get(num)){
                path.add(num);
                permuteUniqueHelper(map,result,path,size);
                path.remove(path.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        s.permute(list);
    }

}
