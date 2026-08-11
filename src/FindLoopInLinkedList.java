import java.util.HashSet;
import java.util.Set;

public class FindLoopInLinkedList {
    static class LinkedList {
        int value;
        LinkedList next;
        boolean seen = false;

        LinkedList(int value) {
            this.value = value;
        }
    }

    public static LinkedList findLoop(LinkedList head) {
        while (head != null && !head.seen) {
            head.seen = true;
            head = head.next;
        }

        // If there is a loop, head is the first node visited for a second time
        // and therefore the loop-start node. If there is no loop, head is null.
        // To answer only "does this list have a cycle?", check head != null.
        // We do not check head.seen directly because head could be null. If head
        // is not null here, the loop could only have stopped because head.seen
        // is true, so head != null already means that a cycle was found.
        return head;
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

        LinkedList loopStart = findLoop(node1);
        boolean hasCycle = loopStart != null;
        System.out.println("Does the linked list have a cycle? "
                + (hasCycle ? "Yes" : "No"));
    }
}

/*
 * FIND LOOP IN A LINKED LIST
 *
 * The linked list used in this example looks like this:
 *
 *     1 -> 2 -> 3 -> 4 -> 5 -> 6
 *               ^                 |
 *               |_________________|
 *
 * Node 6 points back to node 3, so node 3 is the beginning of the loop.
 * The list never reaches null.
 *
 * Every node begins with:
 *
 *     seen = false
 *
 * head is used as the current pointer. As it moves through the list, the
 * current node is marked as visited:
 *
 *     head.seen = true;
 *     head = head.next;
 *
 * The nodes are visited in this order:
 *
 *     1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 3
 *
 * On the first visit to nodes 1 through 6, seen is false, so each node is
 * marked true and head moves to the next node.
 *
 * After node 6, head follows node6.next and points to node 3 again:
 *
 *                              head
 *                                |
 *                                v
 *     1 -> 2 -> 3 -> 4 -> 5 -> 6
 *               ^                 |
 *               |_________________|
 *
 * Node 3 already has seen == true. Therefore the while-loop stops, and head
 * points to the first node visited for a second time. This is the node where
 * the loop begins.
 *
 *     return head;
 *
 * This returns the loop-start node, not the last node and not a copy of the
 * entire linked list. In this example, the returned node contains value 3.
 * The rest of the loop is accessible by following that node's next links.
 *
 * The condition head != null prevents a NullPointerException for an empty list
 * or a list without a loop. If the list reaches null, findLoop returns null.
 * If a loop exists, it returns the first node reached for a second time.
 * Therefore, when only a Yes/No answer is needed:
 *
 *     boolean hasCycle = findLoop(head) != null;
 *
 * We use head != null instead of head.seen after traversal because head.seen
 * would throw a NullPointerException when an acyclic list reaches null. A
 * non-null head after the while-loop is necessarily a previously seen node.
 *
 * IMPORTANT: This implementation changes each visited node's seen field from
 * false to true.
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time complexity: O(n)
 *
 * Each unique node is visited once before the first node in the loop is
 * reached for the second time.
 *
 * Extra space complexity: O(1)
 *
 * The algorithm uses the existing seen field in each node and does not create
 * a separate collection whose size grows with the number of nodes.
 */
