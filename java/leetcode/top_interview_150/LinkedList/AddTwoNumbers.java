/**
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]

Constraints:
    The number of nodes in each linked list is in the range [1, 100].
    0 <= Node.val <= 9
    It is guaranteed that the list represents a number that does not have leading zeros.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class AddTwoNumbers {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2, int c) {
        if (l1 == null && l2 == null && c == 0) 
            return null;
        if (l1 != null) {
            c += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            c += l2.val;
            l2 = l2.next;
        }
        return new ListNode(c % 10, addTwoNumbers(l1, l2, c / 10));
    }
    
    /** 
    // First Attempt
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        ListNode res = l3;
        int c = 0;
        while (l1 != null || l2 != null) {
            int a = (l1 == null) ? 0 : l1.val;
            int b = (l2 == null) ? 0 : l2.val;

            int sum = a + b + c;

            int n = (sum < 9) ? sum : (sum % 10);
            c = (sum < 9) ? 0 : (sum / 10);

            l3.next = new ListNode(n);

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
            l3 = l3.next;
        }
        if (c != 0)
            l3.next = new ListNode(c);
        return res.next;
    }
    */
}
