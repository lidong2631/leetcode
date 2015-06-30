public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int n1 = 0, n2 = 0;
        int count1 = 0, count2 = 0;
        for(int i=0; i<nums.length; i++) {
            if(count1!=0 && nums[i]==n1)
                count1++;
            else if(count2!=0 && nums[i]==n2)
                count2++;
            else if(count1==0) {
                count1 = 1;
                n1 = nums[i];
            }
            else if(count2==0) {
                count2 = 1;
                n2 = nums[i];
            }
            else {
                count1--;
                count2--;
            }
        }
        count1 = 0; count2 = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]==n1)
                count1++;
            if(nums[i]==n2)
                count2++;
        }
        if(count1>nums.length/3)
            res.add(n1);
        if(count2>nums.length/3 && n2!=n1)
            res.add(n2);
        return res;
    }
}