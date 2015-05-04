public class Solution {
    public int findPeakElement(int[] num) {
        int left = 0, right = num.length-1;
        while(left<right) {
            int mid = (left+right)/2;
            if(num[mid]<num[mid+1])
                left = mid + 1;
            else
                right = mid;
        }
        return left;    //return left或right都可以
    }
}


public class Solution {
    public int findPeakElement(int[] num) {
        if(num==null || num.length==0)
            return -1;
        int left = 0; int right = num.length-1;
        while(left<=right) {
            if(left==right) break;
            int mid = (left+right)/2;
            if(num[mid]>num[mid+1])     //不会越界   
                right = mid;    //right有可能是peak
            else
                left = mid + 1; //但left绝不会是 所以要加1
        }
        return left;
    }
}

O(logn)解

这题对find minimum in sorted array i ii有指导意义 其实最终在就剩两个元素比较时 指针一定落在左边的元素 因此我们写num[mid]>num[mid+1] 这里mid＋1不会越界

还有right left变化时 right有可能是peak所以right＝mid  而left绝不可能所以是mid+1





public class Solution {
    public int findPeakElement(int[] num) {
        if(num==null || num.length==0)
            return -1;
        int left = 0; int right = num.length-1;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = Integer.MIN_VALUE;
        while(left<=right) {
            if(num[left]>num[right]) {
                map.put(num[left], left);
                max = Math.max(max, num[left]);
            }
            else {
                map.put(num[right], right);
                max = Math.max(max, num[right]);
            }
        }
        return map.get(max);
    }
}