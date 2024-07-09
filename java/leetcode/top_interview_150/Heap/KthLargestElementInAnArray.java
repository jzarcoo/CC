/**
   Given an integer array nums and an integer k, return the kth largest element in the array.
   Note that it is the kth largest element in the sorted order, not the kth distinct element.
   Can you solve it without sorting?

   Example 1:
   Input: nums = [3,2,1,5,6,4], k = 2
   Output: 5

   Example 2:
   Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
   Output: 4

   Constraints:
   1 <= k <= nums.length <= 105
   -104 <= nums[i] <= 104
*/

import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Queue;

class KthLargestElementInAnArray {

   // time: O(nlogk), 39ms
   // space: O(k), 56.78MB
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<Integer>(k);
        // The smallest of the k largest elements is always on the top
        for (int i = 0; i < k; i++) {
            q.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > q.peek()) {
                q.poll();
                q.offer(nums[i]);
            }
        }
        return q.peek();
    }

   /**
    // time: 23 ms O(n^2), average O(nlogn)
    // space: 57.65 MB, O(1)
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
   */

   /**
   // QuickSort Accepted
   // 2162ms
   // 57.22MB
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        Random rand = new Random();
        while (true) {
            int pivot_index = left + rand.nextInt(right - left + 1);
            int new_pivot_index = partition(nums, left, right, pivot_index);
            if (new_pivot_index == nums.length - k) {
                return nums[new_pivot_index];
            } else if (new_pivot_index > nums.length - k) {
                right = new_pivot_index - 1;
            } else {
                left = new_pivot_index + 1;
            }
        }
    }

    private int partition(int[] nums, int ini, int fin, int pivot) {
        int pivotValue = nums[pivot];
        swap(nums, pivot, fin);
        int j = ini;
        for (int i = ini; i < fin; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, j++);
            }
        }
        swap(nums, fin, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
   */

   /**
   // Recursive QuickSort
   public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, nums.length - k, 0, nums.length - 1);
    }

    private int findKthLargest(int[] nums, int target, int ini, int fin) {
        if (fin <= ini)
            return nums[ini];
        int i = ini + 1;
        int j = fin;
        while (i < j) {
            if (nums[i] > nums[ini] && nums[j] <= nums[ini]) {
                swap(nums, i++, j--);
            } else if (nums[i] <= nums[ini]) {
                i++;
            } else {
                j--;
            }
        }
        if (nums[i] > nums[ini])
            i--;
        swap(nums, i, ini);
        if (i == target)
            return nums[i];
        else if (i > target)
            return findKthLargest(nums, target, ini, i - 1);
        else
            return findKthLargest(nums, target, i + 1, fin);
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j)
            return;
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
   */

   /**
   // Iterative QuickSort
    public int findKthLargest(int[] nums, int k) {
        if (nums.length <= 1)
            return nums[0];
        Stack<Integer> s = new Stack<Integer>();
        s.push(0);
        s.push(nums.length - 1);
        while (!s.empty()) {
            int b = s.pop();
            int a = s.pop();
            if (b == a)
                return nums[a];
            int i = a + 1;
            int j = b;
            while (i < j) {
                if (nums[i] > nums[a] && nums[j] <= nums[a])
                    swap(nums, i++, j--);
                else if (nums[i] <= nums[a])
                    i++;
                else
                    j--;
            }
            if (nums[i] > nums[a])
                i--;
            swap(nums, a, i);
            if (i == nums.length - k)
                return nums[i];
            if (i > nums.length - k) {
                s.push(a);
                s.push(i - 1);
            } else {
                s.push(i + 1);
                s.push(b);
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j)
            return;
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
   */
}
