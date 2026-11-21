// LeetCode 206. Reverse Linked List
//
// Given the head of a singly linked list, reverse the list, and return the reversed list.
// Follow up: a linked list can be reversed either iteratively or recursively.
//
// Constraints: 0 <= nodes in the list <= 5000; -5000 <= Node.val <= 5000.
//
// Approach: Iterative pointer reversal — save curr.next, point curr back at prev, then
//           advance prev and curr; prev ends as the new head
// Time: O(n) — each node is visited once
// Space: O(1)
import java.util.ArrayList;
import java.util.List;

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

public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            // Save the rest of the list
            ListNode next = curr.next;

            // Point node backwards
            curr.next = prev;
            
            prev = curr;
            curr = next;
        }

        return prev;
    }

    private static ListNode build(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    private static List<Integer> toList(ListNode head) {
        List<Integer> out = new ArrayList<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            out.add(cur.val);
        }
        return out;
    }

    public static void main(String[] args) {
        int[][] inputs = {{1, 2, 3, 4, 5}, {1, 2}, {}, {7}, {-5000, 0, 5000}};
        int[][] expected = {{5, 4, 3, 2, 1}, {2, 1}, {}, {7}, {5000, 0, -5000}};
        // LeetCode examples 1-3, single node, negative and boundary values

        Solution s = new Solution();
        int passed = 0;
        for (int k = 0; k < inputs.length; k++) {
            List<Integer> got = toList(s.reverseList(build(inputs[k])));
            List<Integer> want = toList(build(expected[k]));
            boolean ok = got.equals(want);
            if (ok) passed++;
            System.out.printf("%-4s %s -> %s (expected %s)%n", ok ? "ok" : "FAIL",
                    toList(build(inputs[k])), got, want);
        }
        System.out.printf("%n%d/%d passed%n", passed, inputs.length);
    }
}
