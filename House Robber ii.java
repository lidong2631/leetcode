public class Solution {
    public int rob(int[] nums) {
        if(nums==null || nums.length==0)
            return 0;
        if(nums.length<2)
            return nums[0];
            
        int[] maxRob = new int[2];
        maxRob[1] = nums[0];
        for(int i=1; i<nums.length-1; i++) {
            int tmp = maxRob[1];
            maxRob[1] = Math.max(maxRob[1], maxRob[0]+nums[i]);
            maxRob[0] = tmp;
        }
        int max1 = maxRob[1];
        
        Arrays.fill(maxRob, 0);
        maxRob[1] = nums[1];
        for(int i=2; i<nums.length; i++) {
            int tmp = maxRob[1];
            maxRob[1] = Math.max(maxRob[1], maxRob[0]+nums[i]);
            maxRob[0] = tmp;
        }
        return Math.max(max1, maxRob[1]);
    }
}

执行两遍House rob i 第一次从第一个元素开始 到倒数第二个元素结束 第二次从第二个元素开始到倒数第一个元素结束

O(2n) O(1)