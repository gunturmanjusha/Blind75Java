import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        // Sorting lets the two pointers decide which direction can change the sum.
        // It also places equal values together so we can skip duplicate triplets.
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) { // n-2 since its a triplet no need j and k will be same at end so n-2
            // Do not use the same first number twice; it would create the same triplet.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Search for the other two values on either side of the remaining range.
            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum > 0) {
                    // The array is sorted, so moving right would only increase the sum.
                    k--;
                } else if (sum < 0) {
                    // Moving left would decrease the sum, so move the left pointer right.
                    j++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;

                    // Skip repeated second and third values to keep triplets unique.
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }

                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }


        return result;
    }
}
/*
 * 3Sum — Time and Space Complexity
 *
 * Time Complexity: O(n²)
 * - Sorting the array using Arrays.sort(): O(n log n).
 * - Outer for loop iterates up to n times: O(n).
 * - Inner while loop uses two pointers (j and k): O(n) per outer iteration.
 * - Duplicate checks do not increase the overall complexity because
 *   the pointers only move inward.
 * - Total: O(n log n) + O(n * n) = O(n²).
 *
 * Space Complexity:
 * - Variables i, j, k, sum, and n: O(1).
 * - Two-pointer search uses O(1) auxiliary space.
 * - Arrays.sort(int[]) typically uses O(log n) stack space.
 * - Result list stores m unique triplets: O(m).
 * - Each triplet contains 3 integers: O(3) = O(1).
 * - Best-case result space: O(1), when no triplets are found.
 * - Worst-case result space: O(n²), when O(n²) triplets exist.
 * Meaning for a+b+c =0 ; a has n choices and b has n-1 choices and c has only one choice when you compute a + b;
 * meaning for result = n X n-1 X 1 = O(n²-n) = O(n²)
 *
 * Overall Time Complexity: O(n²).
 * Overall Auxiliary Space (excluding output): O(log n).
 * Overall Space Complexity (including output): O(n²) worst case.
 */
