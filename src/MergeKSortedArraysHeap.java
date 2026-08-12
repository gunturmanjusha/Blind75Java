import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedArraysHeap {
    public static List<Integer> mergeSortedArrays(List<List<Integer>> arrayLists) {
        List<Integer> result = new ArrayList<>();

        /*
         * Each inner Queue represents one sorted array.
         *
         * The PriorityQueue compares only the FIRST value
         * of each inner queue.
         */
        PriorityQueue<Queue<Integer>> priorityQueue =
                new PriorityQueue<>(
                        (a, b) -> Integer.compare(a.peek(), b.peek())
                );

        /*
         * Convert every non-empty sorted array into an ArrayDeque.
         *
         * Example:
         *
         * [1, 5, 9]   -> Queue [1, 5, 9]
         * [-1, 0]     -> Queue [-1, 0]
         * [3, 6, 12]  -> Queue [3, 6, 12]
         */
        for (List<Integer> arrayList : arrayLists) {

            if (!arrayList.isEmpty()) {
                priorityQueue.add(new ArrayDeque<>(arrayList));
            }
        }

        /*
         * Repeatedly:
         *
         * 1. Get the queue whose first value is smallest.
         * 2. Remove that first value and add it to result.
         * 3. If that queue still has values, put it back into heap.
         */
        while (!priorityQueue.isEmpty()) {

            Queue<Integer> smallestQueue = priorityQueue.poll();

            // Remove the smallest front value.
            int smallestValue = smallestQueue.poll();

            // Add it to merged result.
            result.add(smallestValue);

            // Put the queue back if it still contains elements.
            if (!smallestQueue.isEmpty()) {
                priorityQueue.add(smallestQueue);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> arrayLists = new ArrayList<>();
        arrayLists.add(new ArrayList<>(List.of(1, 5, 9)));
        arrayLists.add(new ArrayList<>(List.of(2, 3, 8)));
        arrayLists.add(new ArrayList<>(List.of(4, 6, 7)));

        System.out.println("Sorted ArrayLists: " + arrayLists);
        System.out.println("Merged ArrayList:   " + mergeSortedArrays(arrayLists));
    }
}

/*
 * MERGE SORTED ARRAYS USING A MIN HEAP
 *
 * Assume the input is:
 *
 * [1, 5, 9]
 * [-1, 0]
 * [3, 6, 12]
 *
 *
 * STEP 1:
 *
 * Each sorted List is converted into an ArrayDeque:
 *
 * Queue 1: [1, 5, 9]
 * Queue 2: [-1, 0]
 * Queue 3: [3, 6, 12]
 *
 * We use ArrayDeque because poll() removes the first element
 * in O(1) time.
 *
 * Removing index 0 from an ArrayList would require shifting
 * the remaining elements.
 *
 *
 * STEP 2:
 *
 * The PriorityQueue contains these queues.
 *
 * It does NOT compare every element inside the queues.
 *
 * It compares only:
 *
 * q1.peek()
 * q2.peek()
 *
 * using:
 *
 * Integer.compare(q1.peek(), q2.peek())
 *
 * So initially it is effectively comparing:
 *
 *  1
 * -1
 *  3
 *
 * Therefore the queue beginning with -1 is returned first.
 *
 *
 * STEP 3:
 *
 * minHeap.poll() gives:
 *
 * [-1, 0]
 *
 * Then:
 *
 * smallestQueue.poll()
 *
 * removes -1.
 *
 * Result:
 *
 * [-1]
 *
 * The queue is now:
 *
 * [0]
 *
 *
 * STEP 4:
 *
 * Because [0] is not empty, we put the queue back
 * into the PriorityQueue.
 *
 * Now the current front values are:
 *
 * 0
 * 1
 * 3
 *
 * So the heap chooses [0] next.
 *
 *
 * We continue:
 *
 * PICK THE QUEUE WITH THE SMALLEST FRONT
 *                  ->
 * REMOVE ITS FRONT
 *                  ->
 * ADD THAT VALUE TO RESULT
 *                  ->
 * PUT THE QUEUE BACK IF IT IS NOT EMPTY
 *
 *
 * This works because every individual input list is already sorted.
 * After removing the first element of a queue, its next element
 * becomes that list's next smallest candidate.
 *
 *
 * ------------------------------------------------------------
 * TIME COMPLEXITY
 * ------------------------------------------------------------
 *
 * Let:
 *
 * N = total number of elements across all arrays
 * K = number of sorted arrays
 *
 * The PriorityQueue contains at most K queues.
 *
 * For every element:
 *
 * - we poll a queue from the heap
 * - and usually add that queue back
 *
 * Each heap operation takes O(log K).
 *
 * We process all N elements.
 *
 * Time Complexity: O(N log K)
 *
 * Creating all the ArrayDeque copies initially takes O(N),
 * which is dominated by O(N log K).
 *
 *
 * ------------------------------------------------------------
 * SPACE COMPLEXITY
 * ------------------------------------------------------------
 *
 * We copy all N input elements into ArrayDeque objects.
 *
 * Therefore:
 *
 * Space Complexity: O(N)
 *
 * The PriorityQueue itself contains at most K queue references,
 * but the queues stored inside it collectively contain the N
 * copied elements.
 *
 * The result list also contains N elements, but output space is
 * normally not counted when discussing auxiliary space.
 * The min heap chooses the queue with the smallest front; we remove that front and put the remaining queue back.
 */

