import java.util.*;

public class A{
    public static void main(String[] args){
        int[] nums = {1,1,1,1,3,2,1,5,6,4};
        int k = 4;
        System.out.println(findKthLargest(nums, k));
    }
    public static int findKthLargest(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<Integer>(k);
        // The smallest of the k largest elements is always on the top
        for (int i = 0; i < k; i++) {
            q.offer(nums[i]);
        }
        System.out.println("for");
        System.out.println(q);
        for (int i = k; i < nums.length; i++) {
            System.out.println("for2");
            System.out.println(q);
            if (nums[i] > q.peek()) {
                q.poll();
                q.offer(nums[i]);
                System.out.println("if");
                System.out.println(q);
            }
        }
        return q.peek();
    }
}