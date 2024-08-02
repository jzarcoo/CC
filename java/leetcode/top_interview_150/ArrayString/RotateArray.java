/**
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

 

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

 

Constraints:

    1 <= nums.length <= 105
    -231 <= nums[i] <= 231 - 1
    0 <= k <= 105

 

Follow up:

    Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
    Could you do it in-place with O(1) extra space?


*/
class RotateArray {
/**
Hint: The other line of thought is a tad bit complicated but essentially it builds on the idea of placing each element in its original 
position while keeping track of the element originally in that position. Basically, at every step, we place an element in its rightful 
position and keep track of the element already there or the one being overwritten in an additional variable. We can't do this in one 
linear pass and the idea here is based on cyclic-dependencies between elements.
*/
    public void rotate1(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0 || nums.length < 2) {
            return;
        }
        int count = 0;
        int position = 0;
        while (count < nums.length) {
            int prev = position;
            int tmp = nums[position];
            do {
                int next = (position + k) % nums.length;

                int aux = nums[next];
                nums[next] = tmp;

                tmp = aux;
                position = next;

                count++;
            } while (position != prev);
            position++;
        }
    }
 
/**
One line of thought is based on reversing the array (or parts of it) to obtain the desired result. Think about how reversal might potentially help us out by using an example.
*/
    public void rotate2(int[] nums, int k) {
     k = k % nums.length;
        if (k == 0 || nums.length < 2) {
            return;
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, k, nums.length - 1);
        reverse(nums, 0, k - 1);
    }

    private void reverse(int[] arr, int l, int r) {
        while (l < r) {
            swap(arr, l++, r--);
        }
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }
}
