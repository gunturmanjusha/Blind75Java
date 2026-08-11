import java.util.HashSet;
import java.util.Set;

public class FindCycleInLinkedList {
    static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
        }
    }

    public static boolean findCycle(LinkedList head) {
        // Floyd's Cycle Detection Algorithm (also called the Tortoise and Hare):
        // slow moves one step and fast moves two steps. If the list has a cycle,
        // the faster pointer eventually catches the slower pointer inside it.
        LinkedList slow = head;
        LinkedList fast = head;

        // fast must have two available steps before using fast.next.next.
        // If fast or fast.next is null, the list has an end and no cycle.
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move one node at a time.
            fast = fast.next.next;     // Move two nodes at a time.

            // Compare node references, not values. Two different nodes may
            // contain the same value without proving that a cycle exists.
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    private static void printListWithLoop(LinkedList head) {
        Set<LinkedList> printedNodes = new HashSet<>();
        LinkedList current = head;

        // A normal "while (current != null)" loop cannot be used because a
        // linked list containing a loop never reaches null. Stop when the next
        // node has already been printed instead of passing a fixed node count.
        while (current != null && !printedNodes.contains(current)) {
            printedNodes.add(current);
            System.out.print(current.value);
            current = current.next;
            if (current != null) {
                System.out.print(" -> ");
            }
        }

        if (current == null) {
            System.out.println(" -> null (no loop)");
        } else {
            System.out.println(current.value + " (loop starts here again)");
        }
    }

    public static void main(String[] args) {
        LinkedList node1 = new LinkedList(1);
        LinkedList node2 = new LinkedList(2);
        LinkedList node3 = new LinkedList(3);
        LinkedList node4 = new LinkedList(4);
        LinkedList node5 = new LinkedList(5);
        LinkedList node6 = new LinkedList(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3; // Node 3 starts the loop; node 6 points back to it.

        System.out.print("Linked list with loop: ");
        printListWithLoop(node1);

        boolean hasCycle = findCycle(node1);
        System.out.println("Does the linked list have a cycle? "
                + (hasCycle ? "Yes" : "No"));
    }
}

/*
 * FIND A CYCLE IN A LINKED LIST - FLOYD'S ALGORITHM
 *
 * The linked list used in this example looks like this:
 *
 *     1 -> 2 -> 3 -> 4 -> 5 -> 6
 *               ^                 |
 *               |_________________|
 *
 * Node 6 points back to node 3, so this linked list contains a cycle.
 *
 * We use two pointers that start at the head:
 *
 *     slow = head;
 *     fast = head;
 *
 * On every iteration:
 *
 *     slow = slow.next;       // moves 1 step
 *     fast = fast.next.next;  // moves 2 steps
 *
 * If there is a cycle, fast cannot escape to null. Because fast moves one
 * extra step per iteration, it eventually catches slow somewhere inside the
 * cycle. When both references point to the same node, a cycle exists:
 *
 *     if (slow == fast) {
 *         return true;
 *     }
 *
 * For this example, the pointers move as follows:
 *
 *     Start:       slow = 1, fast = 1
 *     Iteration 1: slow = 2, fast = 3
 *     Iteration 2: slow = 3, fast = 5
 *     Iteration 3: slow = 4, fast = 3
 *     Iteration 4: slow = 5, fast = 5  -> same node, cycle found
 *
 * IMPORTANT: slow == fast compares the node references. Do not compare
 * slow.value == fast.value because separate nodes are allowed to store the
 * same value.
 *
 * WHY CHECK fast != null AND fast.next != null?
 *
 * fast moves two steps using fast.next.next, so both fast and fast.next must
 * exist. If either one is null, the list has a normal ending. The while-loop
 * stops and the method returns false. This also safely handles an empty list
 * and a one-node list without a cycle.
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time complexity: O(n)
 *
 * The pointers travel at most a linear number of steps before fast reaches
 * null or catches slow inside the cycle.
 *
 * Extra space complexity: O(1)
 *
 * Only the slow and fast node references are used. No HashSet or other data
 * structure that grows with the input is needed by findCycle.
 *
 * Note: printListWithLoop uses a HashSet only so the demonstration can print a
 * cyclic list without running forever. That helper is separate from the
 * O(1)-space findCycle algorithm.
 */
