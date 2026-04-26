public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to handle edge cases where the head needs to be removed
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //get total number of nodes
        int totalNodes = 0;
        ListNode current = head;
        while (current != null) {
            totalNodes++;
            current = current.next;
        }

        current = dummy;
        //traverse total - n nodes from the start to get to the node before the one we want to remove
        for (int i = 0; i < totalNodes - n; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        return dummy.next;

    }
}
