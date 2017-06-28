Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all 



public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int res = Integer.MIN_VALUE;
        
        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0)
                index = -(index + 1);
            int dis1 = index >= 1 ? house - heaters[index-1] : Integer.MAX_VALUE;
            int dis2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
            res = Math.max(res, Math.min(dis1, dis2));
        }
        return res;
    }
}

Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.


public static int binarySearch(int[] a,
               int key)
Searches the specified array of ints for the specified value using the binary search algorithm. The array must be sorted (as by the sort(int[]) method) prior to making this call. If it is not sorted, 
the results are undefined. If the array contains multiple elements with the specified value, there is no guarantee which one will be found.
Parameters:
a - the array to be searched
key - the value to be searched for
Returns:
index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the array: 
the index of the first element greater than the key, or a.length if all elements in the array are less than the specified key. Note that this guarantees that the return value will be >= 0 
if and only if the key is found.









    