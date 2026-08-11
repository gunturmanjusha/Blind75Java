public class MergeTwoSortedLinkedListWithDummy {

    public static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList mergeTwoLists(LinkedList list1, LinkedList list2) {
    // Example Input:
    // l1 = 1 → 3 → 5
    // l2 = 2 → 4 → 6
        LinkedList dummy = new LinkedList(-1);// final result is in here -1 is start pointer
        LinkedList current = dummy; // this is a pointer 

    // Initial State:
    // dummy = (-1) → null
    // current = (-1) → null
    // l1 = (1 → 3 → 5)
    // l2 = (2 → 4 → 6)

        while( list1 !=null && list2 !=null){

            if( list1.value < list2.value){ // Compare current values
                
                current.next = list1; // Attach l1 node to merged list
                list1 = list1.next;// Move forward
             // After this step:
            // dummy = (-1) → (1) → null
            // current = (1) → null
            // l1 = (3 → 5)
            // l2 = (2 → 4 → 6)
            }else{
                current.next = list2;
                list2 = list2.next; // Move forward

            }

            current = current.next; //move forward
        }
    // Loop continues...
    // Next iteration picks `3` from l1, then `4` from l2, then `5` from l1, and finally `6` from l2.

    // After full merging:
    // dummy = (-1) → (1 → 2 → 3 → 4 → 5 → 6)
    // current = (6) → null
    // l1 = null
    // l2 = null

    // Attach remaining elements if one list is exhausted

        if(list1 !=null){
            current.next = list1;
        } else if(list2 !=null){
            current.next = list2;
        }
        return dummy.next; // -1 -> 1->2 -3 etc  to start at list dummy.next 
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
 *     dummy/current        list1              list2
 *           |                |                  |
 *           v                v                  v
 *          -1                1 -> 3 -> 5 -> 7   2 -> 4 -> 6 -> 8
 *
 * Compare list1.value and list2.value. Since 1 is smaller, connect current to
 * node 1, advance list1, and then advance current:
 *
 *     current.next = list1;
 *     list1 = list1.next;
 *     current = current.next;
 *
 * Now the references look like this:
 *
 *     dummy       current     list1              list2
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
 * The algorithm creates only one dummy node. All other nodes are the original
 * nodes from list1 and list2, reconnected through their next references.
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
 * Only one dummy node and a fixed number of references are added. The original
 * nodes are reused, so extra memory does not grow with the input size.
 */
