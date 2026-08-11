public class RemoveNthNodeFromEndOfLinkedList {
    static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
        }
    }

    public static void removeNthNodeFromEndOfALinkedList(LinkedList head, int n) {
        // Start both pointers at the head. second will first move n nodes ahead
        // so that a gap of n nodes exists between first and second.
        LinkedList first = head;
        LinkedList second = head;
        int counter = 1;

        // Move second exactly n steps forward.
        // Assumption: 1 <= n <= the number of nodes in the linked list.
        while (counter <= n) {
            second = second.next;
            counter++;
        }

        // If second is null after n steps, n equals the list length. Therefore,
        // the node to remove is the head. Because this method returns void, we
        // cannot give the caller a new head. Instead, copy the second node's
        // value into head and delete the second node. The list appears to have
        // removed its original head. This assumes the list has at least 2 nodes.
        // Java has no true pass-by-reference. Object references are passed by value.
        // Therefore head = head.next changes only the local reference. 1--> 2-->3

        if (second == null) {
            head.value = head.next.value;
            head.next = head.next.next;
            return;
        }

        // Move both pointers at the same speed while keeping their n-node gap.
        // When second reaches the last node, first is immediately before the
        // node that must be removed.
        while (second.next != null) {
            first = first.next;
            second = second.next;
        }

        // delete the nth node from the end. First pointet is at n-1th node
        // node before will move to n=1th by 2 steps removing nth
        first.next = first.next.next;
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

    public static void main(String[] args) {
        LinkedList head = new LinkedList(1);
        head.next = new LinkedList(1);
        head.next.next = new LinkedList(3);
        head.next.next.next = new LinkedList(4);
        head.next.next.next.next = new LinkedList(4);
        head.next.next.next.next.next = new LinkedList(4);
        head.next.next.next.next.next.next = new LinkedList(5);
        head.next.next.next.next.next.next.next = new LinkedList(6);
        head.next.next.next.next.next.next.next.next = new LinkedList(6);

        System.out.print("Original list: ");
        printList(head);

        int n = 4;
        removeNthNodeFromEndOfALinkedList(head, n);

        System.out.print(n + "th node from the end removed: ");
        printList(head);
    }
}

/*
 * REMOVE NTH NODE FROM THE END OF A LINKED LIST
 *
 * The main idea is to create a gap of n nodes between two pointers. After the
 * gap is created, move both pointers together. When second reaches the final
 * node, first will be immediately before the node that must be removed.
 *
 * Use this smaller example:
 *
 *     1 -> 2 -> 3 -> 4 -> 5
 *
 * Remove n = 2, so node 4 must be removed.
 *
 * STEP 1: Start both pointers at the head.
 *
 *     first
 *       |
 *       v
 *       1 -> 2 -> 3 -> 4 -> 5
 *       ^
 *       |
 *     second
 *
 * STEP 2: Move second n = 2 steps forward.
 *
 *     first       second
 *       |            |
 *       v            v
 *       1 -> 2 -> 3 -> 4 -> 5
 *
 * There is now a gap of two nodes between the pointer positions.
 *
 * STEP 3: Move first and second together until second is at the last node.
 *
 * After moving once:
 *
 *          first       second
 *            |            |
 *            v            v
 *       1 -> 2 -> 3 -> 4 -> 5
 *
 * After moving twice:
 *
 *               first       second
 *                 |            |
 *                 v            v
 *       1 -> 2 -> 3 -> 4 -> 5
 *
 * second is now at the final node. Because the n-node gap was preserved,
 * first is immediately before the node to remove. first.next is node 4.
 *
 * STEP 4: Skip first.next.
 *
 *     first.next = first.next.next;
 *
 * Before:
 *
 *     1 -> 2 -> 3 -> 4 -> 5
 *               first  target
 *
 * After:
 *
 *     1 -> 2 -> 3 --------> 5
 *
 * Final list:
 *
 *     1 -> 2 -> 3 -> 5
 *
 * ============================================================
 * SPECIAL CASE: REMOVING THE HEAD
 * ============================================================
 *
 * If second becomes null immediately after moving n steps, then n equals the
 * length of the list. The nth node from the end is therefore the head.
 *
 * Example with n = 5:
 *
 *     1 -> 2 -> 3 -> 4 -> 5 -> null
 *     head                         second
 *
 * The method returns void, so it cannot return a different head reference to
 * the caller. Instead, it copies the next node's value and skips that node:
 *
 *     head.value = head.next.value;
 *     head.next = head.next.next;
 *
 * Before:
 *
 *     head
 *       |
 *       v
 *       1 -> 2 -> 3 -> 4 -> 5
 *
 * After copying 2 into head and skipping the original node 2:
 *
 *     head
 *       |
 *       v
 *       2 -> 3 -> 4 -> 5
 *
 * From the caller's view, the original head value 1 has been removed. This
 * special technique requires at least two nodes in the list.
 *
 * ============================================================
 * ASSUMPTIONS AND COMPLEXITY
 * ============================================================
 *
 * Assumptions:
 *
 *     1 <= n <= number of nodes
 *     the list has at least 2 nodes when the head must be removed
 *
 * Time complexity: O(L)
 *
 * second moves through at most L nodes, and first moves through at most L
 * nodes. The work is linear, where L is the length of the linked list.
 *
 * Extra space complexity: O(1)
 *
 * Only first, second, and counter are used. No data structure grows with the
 * number of nodes.
 */
