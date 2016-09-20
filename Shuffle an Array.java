import java.util.*;

public class Solution {

    private int[] n;
    private Random rand;

    public Solution(int[] nums) {
        this.n = nums;
        rand = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return n;    
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (n == null) return null;
        int[] arr = n.clone();
        for (int i = 1; i < arr.length; i++) {
            int index = rand.nextInt(i+1);
            
            int tmp = arr[index];
            arr[index] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */


https://discuss.leetcode.com/topic/53978/first-accepted-solution-java

Fisher-Yates-shuffle
https://bost.ocks.org/mike/shuffle/
http://www.programming-algorithms.net/article/43676/Fisher-Yates-shuffle



Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();