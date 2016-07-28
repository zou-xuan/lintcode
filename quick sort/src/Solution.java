import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by zouxuan on 7/27/16.
 */
 class NBComparator {
    public int cmp(String a, String b){};
}

public class Solution {
    public int median(int[] nums) {
        int k = (nums.length - 1) / 2;
        int start = 0;
        int end = nums.length - 1;
        while (true) {
            int pos = partition(nums, start, end);
            if (pos > k) {
                end = pos - 1;
            } else if (pos < k) {
                start = pos + 1;
            } else {
                return nums[pos];
            }
        }
    }

    public int partition(int[] nums, int start, int end) {
        int left = start + 1, right = end;
        int pivot = nums[start];
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

    public void sortIntegers2(int[] A) {
        if (A == null || A.length <= 1) return;
        quicksort(A, 0, A.length - 1);
    }

    private void quicksort(int[] A, int start, int end) {
        if (start >= end) return;
        int left = start;
        int right = end;
        int pivot = A[(start + end) / 2];
        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }
            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;
                left++;
                right--;
            }
        }
        quicksort(A, start, right);
        quicksort(A, left, end);
    }

    private void heapsort(int[] A) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : A) {
            queue.offer(i);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            A[count++] = queue.poll();
        }
    }

    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        sortnuts(nuts,bolts,compare,0,nuts.length-1);
    }

    private void sortnuts(String[] nuts,String[] bolts,NBComparator compare,int start,int end){
        if(start>=end) return;
        String pivot=nuts[(start+end)/2];
        int left=start;
        int right=end;
        while(left<=right){
            while(left<=right&&compare.cmp(pivot,bolts[left])==1){
                left++;
            }
            while(left<=right&&compare.cmp(pivot,bolts[right])==-1){
                right--;
            }
            if(left<=right){
                String tmp=bolts[left];
                bolts[left]=bolts[right];
                bolts[right]=tmp;
                left++;
                right--;
            }
        }
        sortnuts(nuts,bolts,compare,start,right);
        sortnuts(nuts,bolts,compare,left,end);
    }

    public int smallestDifference(int[] A, int[] B) {
        // write your code here
        Arrays.sort(A);
        Arrays.sort(B);
        int pointA=0,pointB=0;
        int result=Integer.MAX_VALUE;
        while(pointA<A.length&&pointB<B.length){
            result=Math.min(result,Math.abs(A[pointA]-B[pointB]));
            if(A[pointA]>B[pointB]) pointB++;
            else if(A[pointA]<B[pointB]) pointA++;
            else return result;
        }
        return result;
    }

    public long reversePairs(int[] A) {
        int count=0;
        for(int i=0;i<A.length-1;i++){
            int left=A[i];
            for(int j=i+1;j<A.length;j++){
                if(A[j]<left) count++;
            }
        }
        return count;
    }
}
