/**
Given the head of a linked list, return the list after sorting it in ascending order.

Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Example 3:
Input: head = []
Output: []

Constraints:
    The number of nodes in the list is in the range [0, 5 * 104].
    -105 <= Node.val <= 105

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
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

// Recursive
class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode m = getMiddle(head, head);
        ListNode head2 = m.next;
        m.next = null;
        return merge(sortList(head), sortList(head2));
    }

    public ListNode getMiddle(ListNode slow, ListNode fast) {
        if (fast == null || fast.next == null || fast.next.next == null) 
            return slow;
        slow = slow.next;
        fast = fast.next.next;
        return getMiddle(slow, fast);
    }

    public ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) 
            return list2;
        if (list2 == null) 
            return list1;
        if (list1.val <= list2.val) {
            list1.next = merge(list1.next, list2);
            return list1;
        } else {
            list2.next = merge(list1, list2.next);
            return list2;
        }
    }
}
