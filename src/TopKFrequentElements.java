import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
    @SuppressWarnings("unchecked")
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1); // store frequencies in a map
        }

        // Now lets keep all numbers into buckets based on frequencies
        // now 0th bucket is useless but need nums.length +1 if all numbers are same in the array.
        // say 5 number appears 5 times if we have 0-4 buckets as index starts with 0
        // we don't have a bucket now for 5
        List<Integer>[] buckets = (List<Integer>[]) new ArrayList[nums.length + 1];

        // now fill the buckets. Each bucket has an array of numbers with those frequencies
        for (int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Fill the buckets now from map based on freq
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int frequency = entry.getValue();
            buckets[frequency].add(entry.getKey());
        }

        // from top k buckets get top k elements so top to bottom
        List<Integer> result = new ArrayList<>();
        for (int i = nums.length; i >= 1 && result.size() < k; i--) { // we dont need 0th bucket so i>=1
            result.addAll(buckets[i]); // adding top k buckets; each bucket can have more than 1 number
        }

        int[] topk = new int[k]; // convert to array from list
        for (int j = 0; j < k; j++) { // get top k elements from ArrayList
            topk[j] = result.get(j);
        }

        return topk;
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
