public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null || num.length<3)
            return res;
        Arrays.sort(num);
        for(int i=num.length-1; i>2; i--) {
            if(i==num.length-1 || num[i]!=num[i+1]) {
                List<List<Integer>> tmp = ThreeSum(num, i-1, target-num[i]);
                for(int j=0; j<tmp.size(); j++) {
                    tmp.get(j).add(num[i]);
                }
                res.addAll(tmp);
            }
        }
        return res;
    }
    
    public List<List<Integer>> ThreeSum(int[] num, int end, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null || num.length<2)
            return res;
        for(int i=end; i>1; i--) {
            if(i==end || num[i]!=num[i+1]) {
                List<List<Integer>> tmp = TwoSum(num, i-1, target-num[i]);
                for(int j=0; j<tmp.size(); j++) {
                    tmp.get(j).add(num[i]);
                }
                res.addAll(tmp);
            }
        }
        return res;
    }
    
    public List<List<Integer>> TwoSum(int[] num, int end, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null || num.length<1)
            return res;
        int left = 0; int right = end;
        while(left<right) {
            if(num[left]+num[right]==target) {
                List<Integer> tmp = new ArrayList<Integer>();
                tmp.add(num[left]);
                tmp.add(num[right]);
                res.add(tmp);
                left++;
                while(left<right && num[left]==num[left-1])
                    left++;
                right--;
                while(left<right && num[right]==num[right+1])
                    right--;
            }
            else if(num[left]+num[right]>target)
                right--;
            else
                left++;
        }
        return res;
    }
}