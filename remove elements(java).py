class Solution:
    # @param    A       a list of integers
    # @param    elem    an integer, value need to be removed
    # @return an integer
    def removeElement(self, A, elem):
        j = len(A) - 1                      #尾指针从最后一个元素开始
        for i in range(len(A)-1, -1, -1):       #另一个指针i往前遍历
            if A[i] == elem:                #如果A[i]和elem相等 就将它和尾指针j指向的元素交换 这样所有elem元素全在后面 j-=1往前move一个
                A[i], A[j] = A[j], A[i]
                j-=1
        return j+1          #最后返回长度j+1





题意：

Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

解题思路：去掉数组中等于elem的元素，返回新的数组长度，数组中的元素不必保持原来的顺序。使用头尾指针，头指针碰到elem时，

与尾指针指向的元素交换，将elem都换到数组的末尾去。

代码：


class Solution:
    # @param    A       a list of integers
    # @param    elem    an integer, value need to be removed
    # @return an integer
    # clrs qsort
    def removeElement(self, A, elem):
        j = len(A)-1
        for i in range(len(A) - 1, -1, -1):
            if A[i] == elem:
                A[i], A[j] = A[j], A[i]
                j -= 1
        return j+1





public class Solution {
    public int removeElement(int[] A, int elem) {
        if(A==null || A.length==0)
            return 0;
        int len = A.length-1;
        for(int i=0; i<=len; i++) {
            if(A[i]==elem)
                A[i--] = A[len--];  //如果是elem 就跟后面的换 记得下一轮还得判断这个新换的数是不是elem 所以i先－－再＋＋ 但是len一直减 len以后的都是elem
        }
        return len+1;
    }
}

code ganker的写法 代码更简洁 从前往后

这题虽然简单 但三种解法代表的思想要好好理解 从前往后遍历因为后面的元素不确定性 有可能换过来的值仍是elem 所以下一轮仍要停在原处 继续判断当前位置

而从后往前由于后面的元素都判断过了 换过来的元素不需要再判断 可以保证每次都能往前走一步 第三种解法最直接但没有前两种高效


public class Solution {
    public int removeElement(int[] A, int elem) {
        int j = A.length-1;
        for(int i=A.length-1; i>-1; i--)
        {
            if(A[i]==elem)  
            {
                A[j] = A[len--];    //A[j] no--
            }
        }
        return j+1;
    }
}

另一种写法 稍微多两行 从后往前遍历 好处是交换完值不需要不用再判断新换的值是不是elem 因为是从后往前 如果之前有值是elem已经被判断过并交换到j后面去了

这里可以保证j要么跟i同步 要么指的永远是非elem



public int removeElement(int[] A, int elem) {
    if(A==null ||A.length==0)
        return 0;
    int index=0;
    for(int i=0;i<A.length;i++)
    {
        if(A[i]!=elem)
        {
            A[index]=A[i];
            index++;
        } 
    }
    return index; 
}

第三种解法 from code ganker评论 直接扫一遍数组 记下非elem的次数即可 最简单但是前面两种解法在得到length的同时还先数组值分成elem类和非elem类 并且上面两种实现方式

可以尽量减少赋值次数，如果没有重复就没有写操作，从这个角度来说，从后面搬过来更加高效一些哈~


