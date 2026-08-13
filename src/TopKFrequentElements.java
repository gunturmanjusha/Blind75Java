import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
    @SuppressWarnings("unchecked")
    public static int[] topKFrequent(int[] nums, int k) {
        // Store how many times each number appears.
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // buckets[frequency] stores every number that appears frequency times.
        // The maximum possible frequency is nums.length, so the last valid
        // index must be nums.length. That is why the array length is +1.
        List<Integer>[] buckets = (List<Integer>[]) new ArrayList[nums.length + 1];

        // Initialize every bucket before adding values to it.
        for (int frequency = 0; frequency <= nums.length; frequency++) {
            buckets[frequency] = new ArrayList<>();
        }

        // Put each number into the bucket matching its frequency.
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int number = entry.getKey();
            int frequency = entry.getValue();
            buckets[frequency].add(number);
        }

        int[] result = new int[k];
        int resultIndex = 0;

        // Visit buckets from highest frequency to lowest frequency.
        // Stop as soon as k numbers have been selected.
        for (int frequency = nums.length;
             frequency >= 0 && resultIndex < k;
             frequency--) {

            for (int number : buckets[frequency]) {
                result[resultIndex] = number;
                resultIndex++;

                if (resultIndex == k) {
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequent(nums, k);

        System.out.println("Input array:       " + Arrays.toString(nums));
        System.out.println("k:                 " + k);
        System.out.println("Top " + k + " frequent values: " + Arrays.toString(result));
    }
}

/*
 * TOP K FREQUENT ELEMENTS - BUCKET SORT
 *
 * Input:
 *
 *     nums = [1, 1, 1, 2, 2, 3]
 *     k = 2
 *
 * Frequency map:
 *
 *     1 -> 3
 *     2 -> 2
 *     3 -> 1
 *
 * Bucket structure:
 *
 *     buckets[1] = [3]
 *     buckets[2] = [2]
 *     buckets[3] = [1]
 *
 * Visit buckets from right to left because the highest indexes represent the
 * highest frequencies:
 *
 *     frequency 3 -> choose 1
 *     frequency 2 -> choose 2
 *
 * Result:
 *
 *     [1, 2]
 *
 * ============================================================
 * WHY IS THE BUCKET ARRAY nums.length + 1?
 * ============================================================
 *
 * A number can appear as many as nums.length times. For example:
 *
 *     nums = [5, 5, 5, 5, 5]
 *     frequency of 5 = 5
 *
 * Therefore, we need a valid bucket at index 5. Array indexes 0 through 5
 * require an array of length 6:
 *
 *     new ArrayList[nums.length + 1]
 *
 * Bucket 0 is unused for input values because every value appears at least
 * once, but keeping it makes frequency the direct bucket index.
 *
 * ============================================================
 * WHY DOES THIS RUN IN O(N) TIME?
 * ============================================================
 *
 * Let N be nums.length.
 *
 * 1. Build the frequency map: O(N)
 *
 *    Every number is visited once.
 *
 * 2. Create the buckets: O(N)
 *
 *    There are N + 1 bucket references to initialize.
 *
 * 3. Put distinct numbers into buckets: O(N)
 *
 *    There can be at most N distinct numbers.
 *
 * 4. Scan the buckets: O(N)
 *
 *    The bucket array has N + 1 positions, and we scan from N down to 0.
 *
 * Total:
 *
 *     O(N) + O(N) + O(N) + O(N) = O(N)
 *
 * No sorting is performed. Sorting would normally add O(N log N), but bucket
 * indexes let us process frequencies in descending order directly.
 *
 * ============================================================
 * SPACE COMPLEXITY
 * ============================================================
 *
 * Extra space complexity: O(N)
 *
 * - The frequency map stores at most N distinct numbers: O(N).
 * - The bucket array has N + 1 lists: O(N) references.
 * - All distinct numbers are stored once inside the buckets: O(N).
 * - The result array stores k values, and k <= N: O(N).
 *
 * Overall space remains O(N).
 *
 * NOTE ABOUT TIES
 *
 * If multiple numbers have the same frequency, their relative order is not
 * guaranteed. Any k values with the highest frequencies are valid.
 */
