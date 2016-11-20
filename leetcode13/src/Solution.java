
import java.util.*;

/**
 * Created by zouxuan on 10/3/16.
 */

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int number) {
        stack.push(number);
        if (minStack.empty() == true)
            minStack.push(number);
        else if (minStack.peek().compareTo(number) >= 0)
            minStack.push(number);
    }

    public int pop() {
        if (minStack.peek().equals(stack.peek())) {
            minStack.pop();
        }
        return stack.pop();
    }

    public int min() {
        return minStack.peek();
    }

}


class ReturnType {
    int singlePath;
    int maxPath;

    public ReturnType(int singlePath, int maxPath) {
        this.singlePath = singlePath;
        this.maxPath = maxPath;
    }
}

public class Solution {
    public String largestNumber(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1.toString() + o2.toString();
                String s2 = o2.toString() + o1.toString();
                return -s1.compareTo(s2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i.toString());
        }
        int left = 0;
        while (sb.charAt(left) == '0' && left < sb.length() - 1) left++;
        return sb.substring(left).toString();
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long divid = Math.abs((long) dividend)
        long divis = Math.abs(((long) divisor));
        long result = 0;
        System.out.println(divid);
        while (divid >= divis) {
            long tmp = divis;
            long multiple = 1;
            while (divid >= (tmp << 1)) {
                tmp <<= 1;
                multiple <<= 1;
                System.out.println(tmp);
            }
            divid -= tmp;
            result += multiple;
            System.out.println(divid);
        }
        return (int) (result * sign);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.divide(Integer.MIN_VALUE, 1);
    }

    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            while (nums[i] > 0 && nums[i] <= length && nums[nums[i] - 1] != nums[i]) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return length + 1;
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] result = new int[s.length() + 1];
        result[0] = 1;
        result[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i < result.length; i++) {
            char former = s.charAt(i - 2);
            char current = s.charAt(i - 1);
            if (current != '0') {
                result[i] += result[i - 1];
            }
            if (former == '1' || (former == '2' && current <= '6')) {
                result[i] += result[i - 2];
            }
        }
        return result[s.length()];
    }

    public int maxPathSum(TreeNode root) {
        return helper(root).maxPath;
    }

    public ReturnType helper(TreeNode root) {
        if (root == null) {
            return new ReturnType(0, Integer.MIN_VALUE);
        }
        ReturnType left = helper(root.left);
        ReturnType right = helper(root.right);
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
        return new ReturnType(singlePath, maxPath);
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 1;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[index]) {
                nums[++index] = nums[i];
                count = 1;
            } else if (nums[i] == nums[index] && count == 1) {
                nums[++index] = nums[i];
                count++;
            }
        }
        return index + 1;
    }

    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i <= height.length; i++) {
            int curt = i == height.length ? -1 : height[i];
            while (!stack.isEmpty() && curt <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                result = Math.max(result, h * w);
            }
            stack.push(i);
        }
        return result;
    }

    public List<String> stringPermutation2(String str) {
        boolean[] visited = new boolean[str.length()];
        List<String> result = new ArrayList<>();
        ArrayList<Character> path = new ArrayList<>();
        char[] array = str.toCharArray();
        stringDFS(result, path, visited, array);
        return result;
    }

    public void stringDFS(List<String> result, ArrayList<Character> path, boolean[] visited, char[] array) {
        if (path.size() == array.length) {
            StringBuilder sb = new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            result.add(new String(sb));
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if (visited[i] || (array[i] == array[i - 1] && !visited[i - 1])) {
                continue;
            } else {
                visited[i] = true;
                path.add(array[i]);
                stringDFS(result, path, visited, array);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }

    public String minWindow(String source, String target) {
        int[] map = new int[256];
        for (char c : target) {
            map[c]++;
        }
        String result = "";
        int minLength = Integer.MAX_VALUE;
        int count = target.length();
        int start = 0;
        int end = 0;
        while (end < source.length()) {
            if (map[source.charAt(end)] > 0) {
                count--;
            }
            map[source.charAt(end)]--;
            end++;
            while (count == 0) {
                if (end - start < minLength) {
                    result = source.substring(start, end);
                    minLength = end - start
                }
                if (map[source.charAt(start)] == 0) {
                    count++;
                }
                map[source.charAt(start)]++;
                start++;
            }
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curt = prev;
        for (int i = 0; i < m; i++) {
            curt = curt.next;
        }
        ListNode end = prev;
        for (int i = 0; i < n; i++) {
            end = end.next;
        }
        ListNode p = curt;
        ListNode q = curt.next;
        while (p != end) {
            ListNode tmp = q.next;
            q.next = p;
            p = tmp;
        }
        prev.next = end;
        curt.next = end.next;
        return dummy.next;

    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        if (root.left == null || root.right == null) return true;
        boolean result=false;
        if(root.val>root.left.val){
            result|=isValidBST(root.left);
        }
        else{
            return false;
        }
        if(root.val<root.right.val){
            result|=isValidBST(root.right);
        }
        else{
            return false;
        }
        return result;
    }


}
