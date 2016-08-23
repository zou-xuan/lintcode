import java.util.Arrays;

/**
 * Created by zouxuan on 8/21/16.
 */
public class Solution {

    public int aplusb(int a, int b) {
        while (b != 0) {
            int aa = a ^ b;
            int bb = (a & b) << 1;
            a = aa;
            b = bb;
        }
        return a;
    }

    public int digitCounts(int k, int n) {
        // write your code here
        int count = 0;
        for (int i = 0; i <= n; i++) {
            int tmp = i;
            while (tmp != 0) {
                int s = tmp % 10;
                if (s == k) count++;
                s = s / 10;
            }
        }
        return count;
    }

    public int kthLargestElement(int k, int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        k = nums.length - k + 1;
        while (true) {
            int pos = partition(nums, start, end);
            if (pos > k - 1) {
                end = pos - 1;
            } else if (pos < k - 1) {
                start = pos + 1;
            } else {
                return nums[pos];
            }
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start + 1;
        int right = end;
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        int tmp = nums[start];
        nums[start] = nums[right];
        nums[right] = tmp;
        return right;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1, 4, 3, 5, 2, 6, 0, 7};
        s.kthLargestElement(2, nums);
    }

    public void rotateString(char[] str, int offset) {
        if (str == null || str.length == 0) return;
        int rightStart = str.length - offset % str.length;
        reverse(str, 0, rightStart - 1);
        reverse(str, rightStart, str.length - 1);
        reverse(str, 0, str.length - 1);
    }

    private void reverse(char[] str, int start, int end) {
        while (start <= end) {
            char c = str[start];
            str[start] = str[end];
            str[end] = c;
            start++;
            end--;
        }
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        s = s.trim();
        if(s.length()==0) return s;
        char[] array = s.toCharArray();
        int index = 0;
        char prev = array[0];
        for (int i = 0; i < array.length; i++) {
            if (prev == ' ' && array[i] == ' ') {
                continue;
            }
            array[index] = array[i];
            prev=array[i];
            index++;
        }
        char[] newArray = Arrays.copyOf(array, index);
        reverse(newArray, 0, newArray.length-1);
        int start=0;
        for(int i=0;i<newArray.length;i++){
            if(newArray[i]==' '){
                int end=i-1;
                reverse(newArray,start,end);
                start=i+1;
            }
        }
        reverse(newArray,start,newArray.length-1);
        return new String(newArray);
    }

}
