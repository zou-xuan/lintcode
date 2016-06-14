import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zouxuan on 16/5/28.
 */
public class Solution {

    public int removeElement(int[] A, int elem) {
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem) {
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }

    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            for (int j = i; j < nums.length; j++) {
                tmp += nums[j];
                if (tmp == 0) {
                    result.add(i);
                    result.add(j);
                    return result;
                }

            }
        }
        return result;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums == null) return 0;
        int index = 1;
        int current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != current) {
                nums[index] = nums[i];
                index++;
                current = nums[i];
            }
        }
        return index;
    }

    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        int Aindex = m - 1;
        int Bindex = n - 1;
        int totalIndex = n + m - 1;
        while (Aindex >= 0 && Bindex >= 0) {
            if (A[Aindex] >= B[Bindex]) {
                A[totalIndex--] = A[Aindex--];
            } else {
                A[totalIndex--] = B[Bindex--];
            }
        }
        if (Bindex >= 0) {
            while (Bindex >= 0) {
                A[totalIndex--] = B[Bindex--];
            }
        } else {
            while (Aindex >= 0) {
                A[totalIndex--] = A[Aindex--];
            }
        }
    }

    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
        ArrayList<Long> leftProduct = new ArrayList<>(A.size());
        ArrayList<Long> rightProduct = new ArrayList<>(A.size());
        ArrayList<Long> result = new ArrayList<>(A.size());
        leftProduct.add(1);

        rightProduct.get(A.size() - 1) = (L) 1;
        for (int i = 1; i < A.size(); i++) {
            leftProduct.get(i) = A.get(i - 1) * leftProduct.get(i - 1);
        }
        for (int i = A.size() - 2; i >= 0; i--) {
            rightProduct.get(i) = A.get(i + 1) * rightProduct.get(i + 1);
        }
        for (int i = 0; i < A.size(); i++) {
            result.get(i) = leftProduct.get(i) * rightProduct.get(i);
        }
        return result;
    }

    public int firstMissingPositive(int[] A) {
        boolean[] helper = new boolean[A.length + 1];
        for (int tmp : A) {
            if (tmp > 0 && tmp <= A.length) {
                helper[tmp] = true;
            }
        }
        for (int i = 1; i < helper.length; i++) {
            if (!helper[i]) {
                return i;
            }
        }
        return helper.length;
    }

    public int threeSumClosest(int[] numbers, int target) {
        Arrays.sort(numbers);
        int result = numbers[0] + numbers[1] + numbers[2];
        for (int i = 0; i <= numbers.length - 3; i++) {
            int j = i + 1;
            int k = numbers.length - 1;
            while (j < k) {
                int tmp = numbers[i] + numbers[j] + numbers[k];
                if (Math.abs(tmp - target) < Math.abs(result - target)) {
                    result = tmp;
                }
                if (tmp > target) {
                    k--;
                } else
                    j++;
            }
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Arrays.sort(numbers);
        for (int i = 0; i <= numbers.length - 3; i++) {
            if (i != 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = numbers.length - 1;
            while (j < k) {
                int tmp = numbers[i] + numbers[j] + numbers[k];

                if (tmp == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(numbers[i]);
                    list.add(numbers[j]);
                    list.add(numbers[k]);
                    result.add(list);
                    j++;
                    k--;
                    while (j < k && numbers[j] == numbers[j - 1]) j++;
                    while (j < k && numbers[k] == numbers[k + 1]) k--;


                }
                if (tmp > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return result;
    }

    public int[] twoSum(int[] num, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
            if (map.get(num[i]) != null) {
                int[] result = {map.get(num[i]) + 1, i + 1};
                return result;
            } else {
                map.put(target - num[i], i);
            }
        }
        int[] result = new int[2];
        return result;
    }

    public int partitionArray(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] < k) {
                left++;
            }
            while (left <= right && nums[right] >= k) {
                right--;
            }
            if(left<=right){
                int tmp=nums[left];
                nums[left]=nums[right];
                nums[right]=tmp;
                left++;
                right--;
            }
        }
        return left;
    }


}
