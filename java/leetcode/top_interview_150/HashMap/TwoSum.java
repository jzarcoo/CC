import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /**
     * Given an array of integers and an integer, return the indices of the
     * two numbers such that they add up to target.
     * O(n) time complexity.
     * @param nums the array of integers.
     * @param target the integer.
     * @return the indices of the two numbers such that they add up to target.
     */
    public int[] twoSum (int[] nums, int target) {
	// O(n^2) time complexity.
	// for (int i = 0; i < nums.length; i++) {
	//     for (int j = i + 1; j < nums.length; j++) {
	// 	if (nums [i] + nums[j] == target) {
	// 	    return new int[]{i, j};
	// 	}
	//     }
	// }
	// return nums;

	// O(n) time complexity.
	Map <Integer, Integer> complements = new HashMap<>();
	for (int i = 0; i < nums.length; i++) {
	    Integer complementIndex = complements.get(nums[i]);
	    if (complementIndex != null) {
		return new int[]{i, complementIndex};
	    }
	    complements.put(target - nums[i], i);
	}
	return nums;
    }
}
