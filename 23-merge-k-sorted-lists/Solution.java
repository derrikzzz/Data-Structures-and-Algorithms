import java.util.PriorityQueue;

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
    public static void main(String[] args) {
        // Example usage:
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode list3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = {list1, list2, list3};

        Solution solution = new Solution();
        ListNode mergedHead = solution.mergeKSortedLists(lists);

        // Print the merged linked list
        while (mergedHead != null) {
            System.out.print(mergedHead.val + " ");
            mergedHead = mergedHead.next;
        }
    }

    ListNode mergeKSortedLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((x, y) -> x.val - y.val);

        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }
}