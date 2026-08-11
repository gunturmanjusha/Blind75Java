public class ReverseLinkedList {
    static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
        }
    }

    public static LinkedList reverseList(LinkedList head) {
        LinkedList p1 = null; // p1 starts null because head has no previous node;
        LinkedList p2 = head; // p2 starts at head because it is the first current node.

        while (p2 != null) {
            LinkedList p3 = p2.next; // p3 is temporary for the current iteration, while p1 and p2 must preserve their updated state across iterations.
            p2.next = p1;
            p1 = p2;
            p2 = p3;
        }

        return p1; // when p2 reaches null, p1 points to the new head of the reversed list
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
        head.next = new LinkedList(2);
        head.next.next = new LinkedList(3);
        head.next.next.next = new LinkedList(4);
        head.next.next.next.next = new LinkedList(5);

        System.out.print("Original list: ");
        printList(head);

        LinkedList reversedHead = reverseList(head);

        System.out.print("Reversed list: ");
        printList(reversedHead);
    }
}


/*
 * REVERSE LINKED LIST - Explanation for this code
 *
 * First, forget about the edge cases for a moment. Ignore the head boundary
 * and the tail boundary and look only at a middle section of the list.
 *
 *              p1        p2        p3
 *               |         |         |
 *               v         v         v
 *
 *              1  ----->  2  ----->  3  ----->  4  ----->  5
 *
 * Assume node 2 is p2, our current node.
 *
 * In a singly linked list, each node only knows about its next node.
 * So initially:
 *
 *              p2.next = p3
 *
 *              2 -----> 3
 *
 * But because we want to reverse the linked list, we want:
 *
 *              p2.next = p1
 *
 *              1 <----- 2
 *
 * The problem is that if we directly do p2.next = p1, we lose the connection
 * to node 3 and therefore lose the remaining linked list.
 *
 * So we first save the next node:
 *
 *              p3 = p2.next;
 *
 * Then reverse the pointer:
 *
 *              p2.next = p1;
 *
 * Now:
 *
 *              1  <-----  2         3  ----->  4  ----->  5
 *              ^          ^         ^
 *              |          |         |
 *             p1         p2        p3
 *
 * After reversing, we need to move the pointers forward.
 *
 * IMPORTANT: p1 must move to p2 FIRST.
 *
 *              p1 = p2;
 *              p2 = p3;
 *
 * If we do p2 = p3 first, p2 moves away from node 2 and we lose our reference
 * to node 2. Then we cannot move p1 to node 2.
 *
 * After moving:
 *
 *                         p1        p2
 *                          |         |
 *                          v         v
 *
 *              1  <-----  2         3  ----->  4  ----->  5
 *
 * We repeat the same steps until p2 becomes null.
 *
 * The complete pattern is:
 *
 *              p3 = p2.next;     // save next
 *              p2.next = p1;     // reverse
 *              p1 = p2;          // move p1 first
 *              p2 = p3;          // then move p2
 *
 * Easy way to remember:
 *
 *              SAVE -> REVERSE -> MOVE
 *
 * Final result:
 *
 *              5 -----> 4 -----> 3 -----> 2 -----> 1 -----> null
 *
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time Complexity: O(N)
 *
 * We navigate through the entire linked list and visit every node
 * once.
 *
 *
 * Space Complexity: O(1)
 *
 * We are not creating another linked list or any data structure that
 * grows with N. We only use the same three pointers p1, p2 and p3,
 * regardless of how many nodes exist.
 *
 * Therefore the extra memory remains constant.
 *
 *
 * This is an IN-PLACE REVERSAL because we modify the pointers of the
 * existing linked list instead of creating another list.
 */

