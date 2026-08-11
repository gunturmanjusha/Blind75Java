public class MergeTwoSortedLinkedListRecursiveSolution {

    public static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList mergeTwoLists(LinkedList head1, LinkedList head2) {

        // Base case: if one list ends, append the remainder of the other list.
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        // head1 and head2 point to the first nodes of the two remaining
        // sublists. Compare only these two nodes during this call.
        if (head1.value < head2.value) {
            // Keep head1 because it is smaller. Advance only list 1 by passing
            // head1.next, but keep head2 because it has not been selected yet.
            // The recursive call returns the next node of the merged result,
            // and assigning it here connects head1 to that node.
            head1.next = mergeTwoLists(head1.next, head2);
            return head1;
        } else {
            // Keep head2 because it is smaller (or equal). Advance only list 2
            // by passing head2.next. The returned node becomes head2.next.
            head2.next = mergeTwoLists(head1, head2.next);
            return head2;
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

    public static void main(String[] args) {
        LinkedList head1 = new LinkedList(1);
        head1.next = new LinkedList(3);
        head1.next.next = new LinkedList(5);
        head1.next.next.next = new LinkedList(7);

        LinkedList head2 = new LinkedList(2);
        head2.next = new LinkedList(4);
        head2.next.next = new LinkedList(6);
        head2.next.next.next = new LinkedList(8);

        System.out.print("First sorted list:  ");
        printList(head1);

        System.out.print("Second sorted list: ");
        printList(head2);

        LinkedList mergedHead = mergeTwoLists(head1, head2);

        System.out.print("Merged sorted list: ");
        printList(mergedHead);
    }
}

/*
 * MERGE TWO SORTED LINKED LISTS - RECURSIVE APPROACH
 *
 * First sorted list:
 *
 *     1 -> 3 -> 5 -> 7
 *
 * Second sorted list:
 *
 *     2 -> 4 -> 6 -> 8
 *
 * Merged sorted list:
 *
 *     1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
 *
 * IMPORTANT: Each argument is a reference to the first node of an entire
 * remaining sublist. It is not an isolated node. However, each recursive call
 * compares only the two current head nodes.
 *
 * For example:
 *
 *     head1                    head2
 *       |                        |
 *       v                        v
 *       3 -> 5 -> 7              2 -> 4 -> 6 -> 8
 *
 * This call compares only 3 and 2, but head1 still provides access to
 * 3 -> 5 -> 7 and head2 still provides access to 2 -> 4 -> 6 -> 8 through
 * their next references.
 *
 * The smaller current node must be next in the merged list because both input
 * sublists are already sorted. After keeping it, only its list advances.
 *
 * First comparison:
 *
 *     head1                 head2
 *       |                     |
 *       v                     v
 *       1 -> 3 -> 5 -> 7      2 -> 4 -> 6 -> 8
 *
 * Since 1 < 2, node 1 stays first. Only list 1 advances from node 1 to node 3.
 * List 2 remains at node 2 because node 2 has not been selected yet:
 *
 *     head1.next = mergeTwoLists(head1.next, head2);
 *
 * In other words:
 *
 *     node1.next = mergeTwoLists(node3, node2);
 *
 * The original call pauses while mergeTwoLists(node3, node2) runs. That next
 * call compares only nodes 3 and 2. Since 2 is smaller, node 2 is kept:
 *
 *     head2.next = mergeTwoLists(head1, head2.next);
 *     return head2;  // returns node 2 to the paused call
 *
 * The paused call receives node 2 and assigns that returned reference to
 * node1.next:
 *
 *     node1.next = node2;
 *
 * This is how node 1 becomes connected to node 2:
 *
 *     1 -> 2
 *
 * Each call follows the same pattern:
 *
 *     compare 1 and 2  -> keep 1; advance list 1
 *     compare 3 and 2  -> keep 2; advance list 2
 *     compare 3 and 4  -> keep 3; advance list 1
 *     compare 5 and 4  -> keep 4; advance list 2
 *     compare 5 and 6  -> keep 5; advance list 1
 *     compare 7 and 6  -> keep 6; advance list 2
 *     compare 7 and 8  -> keep 7; advance list 1
 *     list 1 is null   -> return the remaining node 8
 *
 * Therefore, "recursively merge the remaining lists" does not mean that one
 * call compares every remaining node at once. Each call compares two current
 * nodes, keeps the smaller node, advances that one list, and lets the next
 * recursive call perform the next comparison.
 *
 * When one list becomes null, the other list is already sorted, so all its
 * remaining nodes are returned as they are.
 *
 * The method reuses the original nodes by changing their next references. It
 * does not create a third linked list.
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time complexity: O(n + m)
 *
 * Each node from both lists is selected once. Here, n and m are the numbers
 * of nodes in the first and second lists.
 *
 * Extra space complexity: O(n + m)
 *
 * This recursive solution uses call-stack space. In the worst case, there can
 * be one recursive call for nearly every node. An iterative solution can
 * perform the same merge with O(1) extra space.
 */
