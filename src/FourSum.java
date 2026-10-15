import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(nums, 0));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // Sorting supports the two-pointer search and groups equal values together.
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        // i must stop at n - 4. Written as i < n - 3, this leaves room for
        // the three remaining positions: j, l, and k.
        for (int i = 0; i < n - 3; i++) {

            // Skip the same first value so we do not add duplicate quadruplets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // j must stop at n - 3. Written as j < n - 2, this leaves room
            // for the final two positions that the left and right pointers use.
            for (int j = i + 1; j < n - 2; j++) {
                // The two pointers search for the last two values of the quadruplet.
                int l = j + 1;
                int k = n - 1;

                // Skip the same second value for this i to keep results unique.
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                while (l < k) {
                    // Cast before adding so large int values cannot overflow.
                    long sum = (long) nums[i] + nums[j] + nums[l] + nums[k];
                    if (sum > target) {
                        // A smaller right value is needed because the array is sorted.
                        k--;
                    } else if (sum < target) {
                        // A larger left value is needed because the array is sorted.
                        l++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[l], nums[k]));
                        l++;
                        k--;

                        // Skip repeated third and fourth values after recording a match.
                        while (l < k && nums[l] == nums[l - 1]) {
                            l++;
                        }

                        while (l < k && nums[k] == nums[k + 1]) {
                            k--;
                        }
                    }

                }

            }

        }

        return result;
    }
}
/*
 * 4Sum — Time and Space Complexity
 *
 * Time Complexity: O(n³)
 * - Arrays.sort(nums): O(n log n).
 * - First for loop (i): O(n).
 * - Second for loop (j): O(n).
 * - Inner while loop (l and k): O(n).
 * - Hashing is not used; two pointers move inward on a sorted array.
 * - Duplicate checks do not increase complexity since pointers
 *   only move inward.
 * - Total: O(n log n) + O(n * n * n) = O(n³).
 *
 * Space Complexity:
 * - Variables i, j, l, k, sum, and n: O(1).
 * - Four-pointer search: O(1) auxiliary space.
 * - Arrays.sort(int[]): typically O(log n) stack space.
 * - Result list stores m unique quadruplets: O(m).
 * - Each quadruplet contains 4 integers: O(4) = O(1).
 * - Best-case result space: O(1), when no quadruplets are found.
 * - Worst-case result space: O(n³), when O(n³) quadruplets exist.
 *
 * Overall Time Complexity: O(n³).
 * Auxiliary Space (excluding output): O(log n) typically.
 * Overall Space Complexity (including output): O(n³) worst case.
 */
