import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeSortedArraysNotOptimalHeap {
    public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
        // result stores all values in ascending order.
        List<Integer> result = new ArrayList<>();

        // Java's PriorityQueue is a min-heap, so poll() always removes the
        // smallest value currently in the queue.
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        // Add every value from every input array to the min-heap.
        for (List<Integer> array : arrays) {
            queue.addAll(array);
        }

        // Repeatedly remove the smallest value and append it to the result.
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> arrays = Arrays.asList(
                Arrays.asList(1, 5, 9),
                Arrays.asList(2, 3, 8),
                Arrays.asList(4, 6, 7)
        );

        System.out.println("Sorted arrays: " + arrays);
        System.out.println("Merged array:  " + mergeSortedArrays(arrays));
    }
}

/*
 * MERGE SORTED ARRAYS USING A MIN-HEAP
 *
 * Input:
 *
 *     [
 *       [1, 5, 9],
 *       [2, 3, 8],
 *       [4, 6, 7]
 *     ]
 *
 * Output:
 *
 *     [1, 2, 3, 4, 5, 6, 7, 8, 9]
 *
 * Let N be the total number of elements across all input arrays. In this
 * example, N = 9.
 *
 * STEP 1: Add all N elements to the PriorityQueue.
 *
 * Java's PriorityQueue is a min-heap. Its smallest element is always at the
 * top. Adding one element can take O(log N), so adding all N elements takes:
 *
 *     N * O(log N) = O(N log N)
 *
 * STEP 2: Poll elements until the queue is empty.
 *
 * poll() removes the smallest element. Each poll can take O(log N), and it is
 * called N times:
 *
 *     N * O(log N) = O(N log N)
 *
 * Polling order:
 *
 *     1, 2, 3, 4, 5, 6, 7, 8, 9
 *
 * Each polled value is added to result, producing one sorted output list.
 *
 * Although the input arrays are already sorted, this particular solution puts
 * every element into the heap at once. It does not use the sorted order to
 * keep the heap smaller.
 *
 * ============================================================
 * COMPLEXITY
 * ============================================================
 *
 * Time complexity: O(N log N)
 *
 * Adding all elements and polling all elements each take O(N log N).
 * Dropping constants gives O(N log N) total time.
 *
 * Extra space complexity: O(N)
 *
 * The priority queue can contain all N input elements. The returned result
 * also contains N elements. If output space is not counted, the heap alone
 * still requires O(N) extra space.
 */
