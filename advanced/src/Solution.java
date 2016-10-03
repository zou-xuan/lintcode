import java.util.*;

/**
 * Created by zouxuan on 9/28/16.
 */
class Element {
    int i;
    int j;
    int val;

    public Element(int i, int j, int val) {
        this.i = i;
        this.j = j;
        this.val = val;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Element)) {
            return false;
        }
        Element another = (Element) obj;
        return this.i == another.i && this.j == another.j;
    }
}

public class Solution {
    public int KthInArrays(int[][] arrays, int k) {
        if (arrays == null || arrays.length == 0 || k < 0) return 0;
        Queue<Integer> queue = new PriorityQueue<>(k + 1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                queue.offer(arrays[i][j]);
            }
        }
        for (int i = 0; i < k - 1; i++) {
            queue.poll();
        }
        return queue.peek();
    }

    public int twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int result = 0;
        while (left < right) {
            int tmp = nums[left] + nums[right];
            if (tmp <= target) {
                left++;
            } else {
                result += right - left;
                right--;
            }
        }
        return result;
    }

    public int kthSmallestSum(int[] A, int[] B, int k) {
        PriorityQueue<Element> queue = new PriorityQueue<>(k, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.val - o2.val;
            }
        });
        HashSet<Element> visited = new HashSet<>();
        Element e = new Element(0, 0, A[0] + B[0]);
        queue.offer(e);
        visited.add(e);
        for (int index = 0; index < k - 1; index++) {
            Element tmp = queue.poll();
            int i = tmp.i;
            int j = tmp.j;
            if (i + 1 < A.length) {
                e = new Element(i + 1, j, A[i + 1] + B[j]);
                if (!visited.contains(e)) {
                    queue.offer(e);
                    visited.add(e);
                }
            }
            if (j + 1 < B.length) {
                e = new Element(i, j + 1, A[i] + B[j + 1]);
                if (!visited.contains(e)) {
                    queue.offer(e);
                    visited.add(e);
                }
            }
        }
        return queue.peek().val;
    }

    public static void main(String[] args){
        Solution s=new Solution();
        s.getStackSimu(3);
    }

    public int getStackSimu(int n){
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();
        Stack<Integer> stack=new Stack<>();
        ArrayList<Integer> path=new ArrayList<>();
        stackDFS(n,1,result,stack,path);
        return result.size();
    }

    public void stackDFS(int n,int index,ArrayList<ArrayList<Integer>> result,
                         Stack<Integer> stack,ArrayList<Integer> path){
        if(path.size()==n){
            result.add(new ArrayList<>(path));
            System.out.println(path);
            return;
        }
        if(!stack.isEmpty()){
            int tmp=stack.pop();
            path.add(tmp);
            stackDFS(n,index,result,stack,path);
            stack.push(tmp);
            path.remove(path.size()-1);
        }
        if(index<=n){
            stack.push(index);
            stackDFS(n,index+1,result,stack,path);
            stack.pop();
        }
    }

    public int triangleCount(int S[]) {
        if(S==null||S.length==0){
            return 0;
        }
        Arrays.sort(S);
        int sum=0;
        for(int i=S.length-1;i>=2;i++){
            int longest=S[i];
            int left=0;
            int right=i-1;
            while(left<right){
                int tmp=S[left]+S[right];
                if(tmp>longest){
                    sum+=right-left;
                    right--;
                }else{
                    left++;
                }
            }
        }
        return sum;
    }
}
