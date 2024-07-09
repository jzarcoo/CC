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
    // time: 23 ms O(n^2), promedio O(nlogn)
    // space: 57.65 MB, O(1)
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
   */
}
