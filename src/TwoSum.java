import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TwoSum {

    public static void main(String[] args) {
    int [] nums = new int[]{2,7,3,4,5,6};
    System.out.println(Arrays.toString(twoSum(nums,9)));
        System.out.println(twoSumPairs(nums,9));
    }

  public static int[] twoSum(int[] nums, int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0; i<nums.length ; i ++){
            int diff = target - nums[i];

            if(map.containsKey(diff)){
                return new int[] {nums[i],diff};
            }
            map.put(nums[i],i);
        }
        return new int[]{};
  }

  public static List<List<Integer>> twoSumPairs(int[] nums, int target){
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i=0; i<nums.length; i++){
            int diff = target - nums[i];

            if(map.containsKey(diff)){
                result.add(Arrays.asList(nums[i],diff));
            }
            map.put(nums[i],i);
        }
        return result;
  }
}
/*
 * TWO SUM — Return First Matching Pair
 *
 * Time Complexity: O(n) average case
 * - The for loop traverses the array at most once: O(n).
 * - HashMap.containsKey() takes O(1) time on average.
 * - HashMap.get() and HashMap.put() take O(1) time on average.
 * - If a matching pair is found early, the loop terminates immediately.
 * - Best case: O(1), when the pair is found in the first two elements.
 * - Overall average-case upper bound: O(n).
 *
 * Space Complexity: O(n)
 * - HashMap stores previously visited elements and their indices.
 * - In the worst case, the HashMap stores up to n elements: O(n).
 * - The returned int[] contains exactly two values: O(1).
 * - Overall auxiliary space: O(n).
 */


/*
 * TWO SUM PAIRS — Return All Matching Pairs
 *
 * Time Complexity: O(n) average case
 * - The for loop traverses the entire array: O(n).
 * - HashMap.containsKey() takes O(1) time on average.
 * - HashMap.put() takes O(1) time on average.
 * - Arrays.asList() creates a fixed-size list containing two values: O(1).
 * - ArrayList.add() takes O(1) amortized time.
 * - At most n - 1 pairs are added to the result list.
 * - Overall average-case time complexity: O(n).
 *
 * Space Complexity: O(n)
 * - HashMap stores up to n elements: O(n).
 * - ArrayList stores up to n - 1 matching pairs: O(n).
 * - Each pair contains exactly two integers: O(1) per pair.
 * - Total space: O(n) + O(n) = O(n).
 *
 * Note:
 * - This implementation can return repeated value pairs when duplicates
 *   exist in the input array.
 * - The result list is included in the total space complexity.
 */