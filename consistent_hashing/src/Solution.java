import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zouxuan on 7/22/16.
 */
public class Solution {
    public List<List<Integer>> consistentHashing(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        tmp.add(0); tmp.add(359); tmp.add(1);
        result.add(new ArrayList<>(tmp));
        for (int i = 1; i < n; i++) {
            int index = findPartIndex(result);
            int x = result.get(index).get(0);
            int y = result.get(index).get(1);
            int z = result.get(index).get(2);
            result.remove(index);
            tmp.clear();
            tmp.add(x); tmp.add((x+y)/2); tmp.add(z);
            result.add(index, new ArrayList<>(tmp));
            tmp.clear();
            tmp.add((x+y)/2+1); tmp.add(y); tmp.add(i);
            result.add(index + 1, new ArrayList<>(tmp));
        }
        return result;
    }

    private int findPartIndex(List<List<Integer>> list) {
        int index = -1;
        int largestSpace = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) {
            List<Integer> tmp = list.get(i);
            int space = tmp.get(1) - tmp.get(0);
            if (space > largestSpace ||
                    (space == largestSpace && tmp.get(2) < list.get(index).get(2))) {
                largestSpace = space;
                index = i;
            }
        }
        return index;
    }
}
