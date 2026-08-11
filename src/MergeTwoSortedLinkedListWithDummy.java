public class MergeTwoSortedLinkedListWithDummy {

    public static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList mergeTwoLists(LinkedList head1, LinkedList head2) {
        // dummy always stays at the beginning of the merged list.
        // current moves forward as nodes are attached.
        LinkedList dummy = new LinkedList(-1);
        LinkedList current = dummy;

        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                current.next = head1;
                head1 = head1.next;
            } else {
                current.next = head2;
                head2 = head2.next;
            }

            current = current.next;
        }

        if (head1 != null) {
            current.next = head1;
        }

        if (head2 != null) {
            current.next = head2;
        }

        // Do not return current.next. current is now near the end of the list,
        // so that would return only the remaining tail (or null). dummy still
        // points to the beginning; dummy.next is the head of the entire merge.
        return dummy.next;
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
 * MERGE TWO SORTED LINKED LISTS - ITERATIVE DUMMY-NODE APPROACH
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
 * A dummy node provides a permanent reference immediately before the first
 * node of the merged list:
 *
 *     dummy
 *       |
 *       v
 *      -1
 *
 * current initially points to dummy. Unlike dummy, current moves forward as
 * each smaller node is attached.
 *
 * Initially:
 *
 *     dummy/current        head1              head2
 *           |                |                  |
 *           v                v                  v
 *          -1                1 -> 3 -> 5 -> 7   2 -> 4 -> 6 -> 8
 *
 * Compare head1.value and head2.value. Since 1 is smaller, connect current to
 * node 1, advance head1, and then advance current:
 *
 *     current.next = head1;
 *     head1 = head1.next;
 *     current = current.next;
 *
 * Now the references look like this:
 *
 *     dummy       current     head1              head2
 *       |            |         |                  |
 *       v            v         v                  v
 *      -1 ->          1         3 -> 5 -> 7        2 -> 4 -> 6 -> 8
 *
 * Next, compare 3 and 2. Node 2 is smaller, so current.next points to node 2.
 * Repeating this comparison builds the complete sorted chain:
 *
 *     dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
 *       ^                                          ^
 *       |                                          |
 *     stays                                      current
 *
 * When one list becomes null, the other list is already sorted, so all its
 * remaining nodes are attached directly to current.next.
 *
 * At the end, current points near the tail, not the beginning. Returning
 * current.next would therefore return only a remaining tail or null. dummy
 * never moved, so dummy.next points to node 1, the head of the entire merged
 * list. The dummy value -1 is not returned or printed.
 *
 *     return dummy.next;
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
 * Extra space complexity: O(1)
 *
 * Only dummy and current are added, and the original nodes are reused. The
 * number of extra references does not grow with the input size.
 */
