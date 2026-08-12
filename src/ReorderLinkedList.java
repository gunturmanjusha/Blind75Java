public class ReorderLinkedList {
    static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static void reorderList(ListNode head) {
        // Three steps:
        // 1. Find the middle using slow and fast pointers, then split the list.
        // 2. Reverse the second half.
        // 3. Merge the first and reversed second halves alternately.

        // Use || here. With &&, head == null would still try to read head.next.
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Move slow one step and fast two steps. When fast reaches the end,
        // slow is at the end of the first half.
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the list into two lists.
        ListNode second = slow.next;
        slow.next = null;

        // Reverse the second list using three pointers.
        ListNode p1 = null;
        ListNode p2 = second;

        while (p2 != null) {
            ListNode p3 = p2.next; // Save the remaining list.
            p2.next = p1;          // Reverse the current link.
            p1 = p2;               // Move the previous pointer forward.
            p2 = p3;               // Move the current pointer forward.
        }

        // p1 is now the head of the reversed second half.
        second = p1;
        ListNode first = head;


        // Link one node from the first half, then one from the second half.
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            first.next = second;
            second.next = firstNext;

            // move pointers
            first = firstNext;
            second = secondNext;
        }
    }

    private static void printList(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    private static ListNode createList(int... values) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode evenHead = createList(1, 2, 3, 4);
        System.out.print("Original list:  ");
        printList(evenHead);
        reorderList(evenHead);
        System.out.print("Reordered list: ");
        printList(evenHead);

        ListNode oddHead = createList(1, 2, 3, 4, 5);
        System.out.print("Original list:  ");
        printList(oddHead);
        reorderList(oddHead);
        System.out.print("Reordered list: ");
        printList(oddHead);
    }
}

/*
 * REORDER LINKED LIST
 *
 * Original form:
 *
 *     L0 -> L1 -> ... -> Ln-1 -> Ln
 *
 * Required form:
 *
 *     L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 *
 * Example with an even number of nodes:
 *
 *     1 -> 2 -> 3 -> 4
 *
 * Find the middle and split:
 *
 *     first half:  1 -> 2
 *     second half: 3 -> 4
 *
 * Reverse the second half:
 *
 *     4 -> 3
 *
 * Merge one node from each half:
 *
 *     1 -> 4 -> 2 -> 3
 *
 * Example with an odd number of nodes:
 *
 *     1 -> 2 -> 3 -> 4 -> 5
 *
 * Split:
 *
 *     first half:  1 -> 2 -> 3
 *     second half: 4 -> 5
 *
 * Reverse the second half:
 *
 *     5 -> 4
 *
 * Merge:
 *
 *     1 -> 5 -> 2 -> 4 -> 3
 *
 * ============================================================
 * STEP 1: FIND THE MIDDLE AND SPLIT
 * ============================================================
 *
 * slow moves one node at a time and fast moves two nodes at a time. When fast
 * reaches the end, slow is at the end of the first half:
 *
 *     while (fast.next != null && fast.next.next != null) {
 *         slow = slow.next;
 *         fast = fast.next.next;
 *     }
 *
 * Then split the list:
 *
 *     second = slow.next;
 *     slow.next = null;
 *
 * Setting slow.next to null is important. It prevents the first half and the
 * second half from remaining connected while they are reversed and merged.
 *
 * ============================================================
 * STEP 2: REVERSE THE SECOND HALF
 * ============================================================
 *
 * Use the same in-place reversal pattern:
 *
 *     p3 = p2.next; // save
 *     p2.next = p1; // reverse
 *     p1 = p2;      // move p1
 *     p2 = p3;      // move p2
 *
 * When p2 becomes null, p1 points to the new head of the reversed second
 * half. No node values are changed; only next references are changed.
 *
 * ============================================================
 * STEP 3: MERGE ALTERNATELY
 * ============================================================
 *
 * Before changing links, save both next nodes. Otherwise, changing first.next
 * or second.next could lose the remaining portion of a list:
 *
 *     firstNext = first.next;
 *     secondNext = second.next;
 *
 * Then connect the nodes in the required order:
 *
 *     first.next = second;
 *     second.next = firstNext;
 *
 * Finally move to the saved nodes:
 *
 *     first = firstNext;
 *     second = secondNext;
 *
 * Continue until the reversed second half is exhausted.
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time complexity: O(N)
 *
 * Finding the middle, reversing the second half, and merging the halves each
 * take linear time. Their total is O(N) + O(N) + O(N), which is O(N).
 *
 * Extra space complexity: O(1)
 *
 * Only pointer variables are used. No new node is created during reordering,
 * and the existing nodes are rearranged in place.
 */
