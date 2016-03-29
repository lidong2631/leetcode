public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        int[] res = new int[k];
        for(int i=Math.max(0, k-m); i<=k && i<=n; i++) {
            int[] tmp = merge(maxArr(nums1, i), maxArr(nums2, k-i), k);
            if(greater(tmp, 0, res, 0))
                res = tmp;
        }
        return res;
    }
    
    private boolean greater(int[] arr1, int i, int[] arr2, int j) {
        while(i<arr1.length && j<arr2.length && arr1[i]==arr2[j]) {
            i++;
            j++;
        }
        return j==arr2.length || i<arr1.length && arr1[i]>arr2[j];
    }
    
    private int[] merge(int[] arr1, int[] arr2, int k) {
        int[] res = new int[k];
        int i=0, j=0;
        for(int index=0; index<k; index++) {
            res[index] = greater(arr1, i, arr2, j)?arr1[i++]:arr2[j++];
        }
        return res;
    }
    
    private int[] maxArr(int[] arr, int k) {
        int n = arr.length;
        int[] res = new int[k];
        int j = 0;
        for(int i=0; i<n; i++) {
            while(n-i+j>k && j>0 && res[j-1]<arr[i])
                j--;
            if(j<k)
                res[j++] = arr[i];
        }
        return res;
    }
}

O((m+n)^3)

First, we divide the k digits required into two parts, i and k-i. We then find the maximum number of length i in one array and the maximum number of length k-i in the 

other array using the algorithm in section 1. Now we combine the two results in to one array using the algorithm in section 2. After that we compare the result with 

the result we have and keep the larger one as final answer

http://algobox.org/create-maximum-number/

https://leetcode.com/discuss/75756/share-my-greedy-solution