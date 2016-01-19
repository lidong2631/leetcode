public class NumArray {

    int[] arr;
    int[] BITree;

    public NumArray(int[] nums) {
        int len = nums.length;
        arr = new int[len];
        BITree = new int[len+1];
        
        for(int i=0; i<len; i++) {
            update(i, nums[i]);
            arr[i] = nums[i];
        }
    }

    void update(int i, int val) {
        int diff = val-arr[i];
        arr[i] = val;
        i++;
        
        while(i<=arr.length) {
            BITree[i]+=diff;
            i+= i & (-i);
        }
    }

    int getSum(int i) {
        int sum = 0;
        i++;
        
        while(i>0) {
            sum+=BITree[i];
            i-= i & (-i);
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i-1);
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);


Binary Indexed Tree

O(logn) for update and getSum, O(nlogn) for initialization
https://leetcode.com/discuss/72551/share-my-java-binary-indexed-tree-solution
http://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/


Another solution could use Segment Tree