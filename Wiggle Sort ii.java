public class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] res = new int[nums.length];
        int n = nums.length;
        int mid = n%2==0?n/2-1:n/2;
        int index = 0;
        
        for(int i=0; i<=mid; i++) {
            res[index] = nums[mid-i];
            if(index+1<n)
                res[index+1] = nums[n-1-i];
            index+=2;
        }
        for(int i=0; i<n; i++)
            nums[i] = res[i];
    }
}


sort and then spread the numbers like in this example with nums=[0,1,...,9]

Small half:    4 . 3 . 2 . 1 . 0 .
Large half:    . 9 . 8 . 7 . 6 . 5
----------------------------------
Together:      4 9 3 8 2 7 1 6 0 5

https://leetcode.com/discuss/77122/simple-modulo-solution
https://leetcode.com/discuss/77496/ac-java-solution-7ms