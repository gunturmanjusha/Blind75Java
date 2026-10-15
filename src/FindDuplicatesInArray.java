import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicatesInArray {

    public static void main(String[] args) {
        FindDuplicatesInArray array = new FindDuplicatesInArray();
        int[] nums = new int[]{1,1,4,5,6};
        String name = "Manjusha";
        array.getDuplicates(nums);
        System.out.println(array.getDuplicateCharactersInArray(name));
    }

    private List<Character> getDuplicateCharactersInArray(String name) {
        HashMap<Character,Integer> map = new HashMap<>();
        List<Character> duplicates = new ArrayList<>();

        for(char ch: name.toCharArray()){
            // Count each character as it appears in the string.
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            // A count greater than one means this character was repeated.
            if(entry.getValue() > 1){
                duplicates.add(entry.getKey());
            }
        }

        return duplicates;
    }

    private void getDuplicates(int[] nums) {
        List<Integer> dup = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();

        for(int num: nums){
           // Store how many times each number appears.
           map.put(num,map.getOrDefault(num,0)+1);
        }

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            // entry.getKey() is the number; entry.getValue() is its count.
            if(entry.getValue() >1){
                dup.add(entry.getKey());
            }
        }

        // Print only the numbers whose count was greater than one.
        System.out.println(dup);
    }
}


// Time Complexity: O(n) average case
// First loop: O(n) to build the frequency map.
// Second loop: O(n) to iterate through the map and identify duplicates.
// Total: O(n) + O(n) = O(n).

// Space Complexity: O(n)
// HashMap: O(n) to store element frequencies.
// ArrayList: O(n) in the worst case to store duplicates.
// Total: O(n) + O(n) = O(n).
