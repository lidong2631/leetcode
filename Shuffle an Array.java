import java.util.*;

public class Solution {

    int[] arr;
    Random rand;

    public Solution(int[] nums) {
        this.arr = nums;
        rand = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return arr;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (arr == null) return null;
        int[] a = arr.clone();
        int m = a.length;
        while (m > 0) {
            int i = rand.nextInt(m--);
            int tmp = a[m];
            a[m] = a[i];
            a[i] = tmp;
        }
        return a;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

Fisher-Yates-shuffle
https://bost.ocks.org/mike/shuffle/
http://www.programming-algorithms.net/article/43676/Fisher-Yates-shuffle


int[] shuffle(int[] array) {
    int m = array.length;
    Random rand = new Random();

    while (m > 0) {
        int random = rand.nextInt(m--);
        int tmp = array[m];
        array[m] = array[random];
        array[random] = tmp;
    }
    return array;
}





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