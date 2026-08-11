public class RemoveDuplicatesFromLinkedList {
    static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
        }
    }

    public static LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
        LinkedList currentNode = linkedList;

        while (currentNode != null) {
            LinkedList nextDistinctNode = currentNode.next;

            while (nextDistinctNode != null
                    && nextDistinctNode.value == currentNode.value) {
                nextDistinctNode = nextDistinctNode.next;
            }

            currentNode.next = nextDistinctNode; //BYPASS duplicates 1-3 removing duplicates makes a new LL
            currentNode = currentNode.next; // or nextDistinctNode MOVE current pointer forward and repeat
        }

        // linkedList still points to the head (first node). Returning the head
        // gives the caller access to the entire modified list through next links.
        // currentNode cannot be returned because it is null after the loop ends.
        return linkedList;
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

        LinkedList result = removeDuplicatesFromLinkedList(head);

        System.out.print("Without duplicates: ");
        printList(result);
    }
}

/*
 * REMOVE DUPLICATES FROM A SORTED LINKED LIST
 *
 * Original list:
 *
 *     1 -> 1 -> 3 -> 4 -> 4 -> 4 -> 5 -> 6 -> 6
 *
 * Without duplicates:
 *
 *     1 -> 3 -> 4 -> 5 -> 6
 *
 * IMPORTANT: This method does not sort the linked list. It requires the input
 * list to already be sorted, which places equal values next to each other.
 *
 * currentNode points to the value that should remain in the list.
 * nextDistinctNode starts at the following node and moves forward while its
 * value is equal to currentNode.value.
 *
 * For example, when currentNode points to the first 4:
 *
 *     currentNode       nextDistinctNode
 *          |                    |
 *          v                    v
 *          4       ->           4       -> 4 -> 5
 *
 * nextDistinctNode skips every additional 4 until it reaches 5:
 *
 *     currentNode                         nextDistinctNode
 *          |                                      |
 *          v                                      v
 *          4       -> 4 -> 4       ->             5
 *
 * currentNode.next is then connected directly to nextDistinctNode:
 *
 *     currentNode.next = nextDistinctNode;
 *
 * This skips the duplicate nodes:
 *
 *          4       -----------------------------> 5
 *
 * Finally, currentNode moves to the next distinct node, and the process
 * repeats until currentNode is null.
 *
 * Time complexity: O(n), because every node is visited once.
 * Space complexity: O(1), because only two node references are used.
 */
