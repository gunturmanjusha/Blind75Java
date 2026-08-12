import java.util.PriorityQueue;

public class MergeKSortedLinkedListsHeap {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        // Base case: If there are no lists, return null.
        if (lists == null || lists.length == 0) return null;

        // Dummy node acts as a placeholder for the merged linked list.
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // Min-Heap (PriorityQueue) to always process the smallest node first.
        // The comparator ensures sorting based on node values.
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
                lists.length,
                (a, b) -> Integer.compare(a.val, b.val)
        );

        for (ListNode list : lists) {

            // Step 1: Add the first node of each list to the priority queue.
            if (list != null) {
                queue.add(list);
            }
        }

        // Example:
        // Input Lists:
        // list1 = 1 -> 4 -> 5
        // list2 = 1 -> 3 -> 4
        // list3 = 2 -> 6
        //
        // After this loop, the queue contains:
        // queue = [1, 1, 2] (Min-Heap, smallest value is at the top)

        // Step 2: Take the smallest available node from the queue.
        while (!queue.isEmpty()) {
            // smallestNode is an actual node from one of the original lists.
            // For example, if we poll list1's node 1, smallestNode.next is 4.
            ListNode smallestNode = queue.poll();

            // Attach that node after the current last node of the merged list.
            current.next = smallestNode;
            current = current.next; // current now points to the attached node.

            // The attached node still knows the next node in its original list.
            // Add that next node so it can compete with the other lists' nodes.
            if (smallestNode.next != null) {
                queue.add(smallestNode.next);
            }
        }

        // current points to the last node, but dummy still points to the start.
        // dummy.next skips -1 and returns the entire merged linked list.
        return dummy.next;
    }

    private static void printList(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        ListNode[] lists = {list1, list2, list3};
        ListNode mergedHead = mergeKLists(lists);

        System.out.print("Merged linked list: ");
        printList(mergedHead);
    }
}

/*
 * MERGE K SORTED LINKED LISTS USING A MIN-HEAP
 *
 * Input:
 *
 *     list1 = 1 -> 4 -> 5
 *     list2 = 1 -> 3 -> 4
 *     list3 = 2 -> 6
 *
 * Output:
 *
 *     1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
 *
 * STEP 1: ADD ONLY THE FIRST NODE OF EACH LIST
 *
 *     list1 head = 1, and this node points to 4
 *     list2 head = 1, and this node points to 3
 *     list3 head = 2, and this node points to 6
 *
 * Add those three head nodes to the queue:
 *
 *     queue contains values [1, 1, 2]
 *
 * We do not add every node immediately. The queue has only one available node
 * from each list.
 *
 * STEP 2: POLL THE SMALLEST NODE
 *
 * Suppose the first node polled is node 1 from list1:
 *
 *     smallestNode = list1's node 1
 *     smallestNode.next = list1's node 4
 *
 * Attach node 1 to the merged list:
 *
 *     current.next = smallestNode;
 *     current = current.next;
 *
 * Now:
 *
 *     dummy -> 1
 *              ^
 *              |
 *            current
 *
 * Before continuing, add smallestNode.next, which is node 4 from list1:
 *
 *     queue.add(smallestNode.next);
 *
 * The queue now has these available values:
 *
 *     [1 from list2, 2 from list3, 4 from list1]
 *
 * STEP 3: REPEAT
 *
 * Poll node 1 from list2 and attach it after the first node 1:
 *
 *     dummy -> 1 -> 1
 *                   ^
 *                   |
 *                 current
 *
 * That polled node's next node is 3, so add node 3 to the queue:
 *
 *     [2 from list3, 3 from list2, 4 from list1]
 *
 * Next, poll node 2 and add its next node 6:
 *
 *     merged: 1 -> 1 -> 2
 *     queue:  [3 from list2, 4 from list1, 6 from list3]
 *
 * Continue the same steps until the queue becomes empty:
 *
 *     poll smallest -> attach it -> add its next node
 *
 * Final merged list:
 *
 *     dummy -> 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
 *
 * WHY IS THIS LINKED-LIST CODE SIMPLE?
 *
 * A ListNode contains both its value and its next reference:
 *
 *     [value | next]
 *
 * Therefore, after polling a node, smallestNode.next directly gives the next
 * node from the same list. We do not need to store a list index or node index.
 *
 * The nodes are not copied. The algorithm attaches the original nodes to the
 * merged chain. Only the dummy node is newly created.
 *
 * Time complexity: O(N log K)
 *
 * N is the total number of nodes and K is the number of lists. Every node is
 * added and polled once. The queue holds at most one node from each list, so
 * its maximum size is K and each queue operation costs O(log K).
 *
 * Extra space complexity: O(K)
 *
 * The heap contains at most one available node from each linked list. The
 * dummy node and pointer variables use O(1) additional space.
 */
