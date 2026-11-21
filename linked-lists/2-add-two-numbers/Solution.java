// LeetCode 2. Add Two Numbers
//
// You are given two non-empty linked lists representing two non-negative integers. The
// digits are stored in reverse order, and each of their nodes contains a single digit.
// Add the two numbers and return the sum as a linked list.
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
// Constraints: 1 <= nodes in each list <= 100; 0 <= Node.val <= 9;
//              the lists represent numbers without leading zeros.
//
// Approach: Elementwise addition with carry — walk both lists digit by digit, appending
//           (x + y + carry) % 10 to a dummy-headed result list until both run out and carry is 0
// Time: O(max(m, n)) — one pass over the longer list
// Space: O(1) extra — not counting the output list
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // Loop until both lists are exhausted and no carry remains
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = carry + x + y;
            carry = sum / 10; // Carry for the next digit

            current.next = new ListNode(sum % 10);
            current = current.next;

            // Move to the next nodes if available
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }

    private static ListNode build(int[] digits) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int d : digits) {
            tail.next = new ListNode(d);
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
        int[][] l1s = {{2, 4, 3}, {0}, {9, 9, 9, 9, 9, 9, 9}, {5}, {1, 8}};
        int[][] l2s = {{5, 6, 4}, {0}, {9, 9, 9, 9}, {5}, {0}};
        int[][] expected = {{7, 0, 8}, {0}, {8, 9, 9, 9, 0, 0, 0, 1}, {0, 1}, {1, 8}};
        // LeetCode examples 1-3, final carry creates a new node, adding zero

        Solution s = new Solution();
        int passed = 0;
        for (int k = 0; k < l1s.length; k++) {
            List<Integer> got = toList(s.addTwoNumbers(build(l1s[k]), build(l2s[k])));
            List<Integer> want = toList(build(expected[k]));
            boolean ok = got.equals(want);
            if (ok) passed++;
            System.out.printf("%-4s %s + %s -> %s (expected %s)%n", ok ? "ok" : "FAIL",
                    toList(build(l1s[k])), toList(build(l2s[k])), got, want);
        }
        System.out.printf("%n%d/%d passed%n", passed, l1s.length);
    }
}
