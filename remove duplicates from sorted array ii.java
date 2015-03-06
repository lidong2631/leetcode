public class Solution {
    public int removeDuplicates(int[] A) {
        if(A==null || A.length==0)
            return 0;
        if(A.length==1) return 1;
        if(A.length==2) return 2;
        int index = 2;
        for(int i=2; i<A.length; i++) {
            if(A[i]!=A[index-2]) {
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }
}

非常好的解法 from code ganker评论 通过比较A[i]和A[index-2] 来移动index 代码很简洁 而且如果题目换成允许重复3次4次之类也可以类推









public class Solution {
    public int removeDuplicates(int[] A) {
        if(A==null || A.length==0)
            return 0;
        int res = 0;
        int count = 0;
        for(int i=0; i<A.length; i++)
        {
            if(i>0 && A[i-1]==A[i])
            {
                count++;
                if(count>2)
                    continue;
            }
            else
                count = 1;
            A[res++] = A[i];
        }
        return res;
    }
}

Note: 此解法是按照code ganker改写的 他的比python版感觉更清楚易懂 这种题只要会一种就可 属于简单题 要做到熟练bug free 2分钟搞定

这道题跟Remove Duplicates from Sorted Array比较类似，区别只是这里元素可以重复出现至多两次，而不是一次。其实也比较简单，只需要维护一个counter，

当counter是2时，就直接跳过即可，否则说明元素出现次数没有超，继续放入结果数组，若遇到新元素则重置counter。总体算法只需要扫描一次数组，所以时间上是O(n)，

空间上只需要维护一个index和counter，所以是O(1)