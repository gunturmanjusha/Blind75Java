public class ShiftLinkedList {
    public static LinkedList shiftLinkedList(LinkedList head, int k) {
        // An empty list or a one-node list cannot be shifted.
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Find the length and the old tail.
        int length = 1;
        LinkedList tail = head;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 2. Find the effective shift. Shifting by length gives the same list,
        // so reduce k to a value between 0 and length - 1.
        int offset = Math.abs(k) % length; // k + -> shift right ; k negative -> shift left

        // No shift is needed when k is zero or a multiple of length.
        if (offset == 0) {
            return head;
        }

        // 3. Find the position of the new tail.
        // Positive k shifts right; negative k shifts left.
        int newTailPosition = k > 0 ? length - offset : offset;
        LinkedList newTail = head;

        for (int i = 1; i < newTailPosition; i++) {
            newTail = newTail.next;
        }

        // 4. The node after the new tail becomes the new head.
        LinkedList newHead = newTail.next;

        // 5. Connect the old tail to the old head to make a temporary circle.
        tail.next = head;

        // 6. Break the circle immediately after the new tail.
        newTail.next = null;

        return newHead;
    }

    static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
        }
    }

    private static void printList(LinkedList head) {
        LinkedList current = head;

        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    private static LinkedList createList(int... values) {
        LinkedList dummy = new LinkedList(-1);
        LinkedList current = dummy;

        for (int value : values) {
            current.next = new LinkedList(value);
            current = current.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        LinkedList head = createList(0, 1, 2, 3, 4, 5);

        System.out.print("Original list: ");
        printList(head);

        head = shiftLinkedList(head, 2);

        System.out.print("Shifted list:  ");
        printList(head);
    }
}

/*
 * SHIFT A LINKED LIST
 *
 * Sample input:
 *
 *     0 -> 1 -> 2 -> 3 -> 4 -> 5
 *     k = 2
 *
 * A positive k shifts the list to the right:
 *
 *     4 -> 5 -> 0 -> 1 -> 2 -> 3
 *
 * A negative k shifts the list to the left. For example, k = -2 gives:
 *
 *     2 -> 3 -> 4 -> 5 -> 0 -> 1
 *
 * ============================================================
 * STEP 1: FIND LENGTH AND OLD TAIL
 * ============================================================
 *
 * Traverse the list once:
 *
 *     length = 6
 *     tail = 5
 *
 * ============================================================
 * STEP 2: FIND THE EFFECTIVE SHIFT
 * ============================================================
 *
 * Use modulo because shifting by the list length returns the original list:
 *
 *     offset = Math.abs(k) % length
 *     offset = Math.abs(2) % 6 = 2
 *
 * If offset is zero, return head because no rearrangement is needed.
 *
 * ============================================================
 * STEP 3: FIND THE NEW TAIL
 * ============================================================
 *
 * For positive k, the new tail is at:
 *
 *     newTailPosition = length - offset
 *     newTailPosition = 6 - 2 = 4
 *
 * The fourth node is 3:
 *
 *     0 -> 1 -> 2 -> 3 -> 4 -> 5
 *                    ^    ^         ^
 *                 newTail newHead  tail
 *
 * For negative k, the new tail position is offset:
 *
 *     newTailPosition = offset
 *
 * ============================================================
 * STEP 4: FIND THE NEW HEAD
 * ============================================================
 *
 * The new head is immediately after the new tail:
 *
 *     newHead = newTail.next
 *     newHead = 4
 *
 * ============================================================
 * STEP 5: CONNECT OLD TAIL TO OLD HEAD
 * ============================================================
 *
 * Connect node 5 to node 0:
 *
 *     tail.next = head
 *
 * This temporarily creates:
 *
 *     0 -> 1 -> 2 -> 3 -> 4 -> 5
 *     ^                         |
 *     |_________________________|
 *
 * ============================================================
 * STEP 6: BREAK AFTER THE NEW TAIL
 * ============================================================
 *
 * Break the link after node 3:
 *
 *     newTail.next = null
 *
 * Final list:
 *
 *     4 -> 5 -> 0 -> 1 -> 2 -> 3 -> null
 *
 * ============================================================
 * INTERVIEW MEMORY VERSION
 * ============================================================
 *
 *     1. Find length and old tail.
 *     2. offset = abs(k) % length.
 *     3. Find the new tail.
 *     4. newHead = newTail.next.
 *     5. oldTail.next = head.
 *     6. newTail.next = null.
 *     7. return newHead.
 *
 * Time complexity: O(n)
 *
 * The list is traversed to find its length and tail, and traversed again to
 * find the new tail. These are linear traversals, so the total is O(n).
 *
 * Extra space complexity: O(1)
 *
 * Only a fixed number of node references and integers are used. The existing
 * nodes are rearranged in place; no new list is created.
 */
