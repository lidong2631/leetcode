<<<<<<< HEAD
public class Solution {
    public List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if(A==null)
            return res;
        for(int i=0; i<A.length && lower<=upper && A[i]<=upper; i++) {  //注意这里有3个条件 其中两个都是小于等于的关系
            if(lower==A[i])
                lower++;
            else if(lower<A[i]) {
                createStr(res, lower, A[i]-1);  //注意A[i]-1
                lower = A[i] + 1;               //记得lower要变成A[i]+1
            }
        }
        if(lower<=upper) {  //最后如果upper大于A[i] 要再检查少的range
            createStr(res, lower, upper);
        }
        return res;
    }
    
    private void createStr(List<String> res, int start, int end) {
        if(start==end)  //这里是相等
            res.add(Integer.toString(start));   //相等就把前面的数加进去
        else
            res.add(Integer.toString(start) + "->" + Integer.toString(end));
    }
}

第二遍写 单独提出createStr()函数 这题情况比较多 要考虑好边界条件 注意几个test case
[], 1, 1
[-1], -1, -1
[-1], -2, -1



public class Solution {
    public List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if(A==null)
            return res;
        for(int i=0; i<A.length && lower<=upper && A[i]<=upper; i++) {
            if(lower==A[i])
                lower++;
            else if(lower<A[i]) {
                if(lower<A[i]-1) {
                    String tmp = String.valueOf(lower) + "->" + String.valueOf(A[i]-1);
                    res.add(tmp);
                    lower = A[i]+1;
                }
                else {
                    res.add(String.valueOf(lower));
                    lower = A[i]+1;
                }
            }
        }
        if(lower<=upper) {
            if(lower==upper)
                res.add(Integer.toString(lower));
            else
                res.add(Integer.toString(lower) + "->" + Integer.toString(upper));
        }
        return res;
    }
}

扩展 想想如果range是exclusive 边界条件要注意



http://www.meetqun.com/thread-2961-1-1.html

分析：只要沿着区间lower扫一次即可。我们维持一个循环不变式： 目前[lower..upper]是不确定有没有在数组里出现的。A[]中小于lower的都已经决定好了。
于是有三种情况：
（1） A【i】 < lower, 因为小于lower的都考虑过了，所以A【i】这个数不影响结果。
（2） A【i】 == lower, 显然lower = lower + 1,继续考虑。
（3） A【i】 > lower， 这说明[lower..A【i】 - 1]是缺失的区间。然后我们输出lower..A【i】 - 1，然后lower = A【i】 + 1。
三种情况下，都能维持我们的循环不变式。写出的代码简短、清晰易懂，更容易考虑边界条件（第一个，最后一个等）。
我觉得循环不变式，可以简化问题，请仔细理解循环不变式的思想。

时间复杂度 : O(n)
=======
public class Solution {
    public List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if(A==null)
            return res;
        for(int i=0; i<A.length && lower<=upper && A[i]<=upper; i++) {  //注意这里有3个条件 其中两个都是小于等于的关系
            if(lower==A[i])
                lower++;
            else if(lower<A[i]) {
                createStr(res, lower, A[i]-1);  //注意A[i]-1
                lower = A[i] + 1;               //记得lower要变成A[i]+1
            }
        }
        if(lower<=upper) {  //最后如果upper大于A[i] 要再检查少的range
            createStr(res, lower, upper);
        }
        return res;
    }
    
    private void createStr(List<String> res, int start, int end) {
        if(start==end)  //这里是相等
            res.add(Integer.toString(start));   //相等就把前面的数加进去
        else
            res.add(Integer.toString(start) + "->" + Integer.toString(end));
    }
}

第二遍写 单独提出createStr()函数 这题情况比较多 要考虑好边界条件 注意几个test case
[], 1, 1
[-1], -1, -1
[-1], -2, -1



public class Solution {
    public List<String> findMissingRanges(int[] A, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if(A==null)
            return res;
        for(int i=0; i<A.length && lower<=upper && A[i]<=upper; i++) {
            if(lower==A[i])
                lower++;
            else if(lower<A[i]) {
                if(lower<A[i]-1) {
                    String tmp = String.valueOf(lower) + "->" + String.valueOf(A[i]-1);
                    res.add(tmp);
                    lower = A[i]+1;
                }
                else {
                    res.add(String.valueOf(lower));
                    lower = A[i]+1;
                }
            }
        }
        if(lower<=upper) {
            if(lower==upper)
                res.add(Integer.toString(lower));
            else
                res.add(Integer.toString(lower) + "->" + Integer.toString(upper));
        }
        return res;
    }
}

扩展 想想如果range是exclusive 边界条件要注意



http://www.meetqun.com/thread-2961-1-1.html

分析：只要沿着区间lower扫一次即可。我们维持一个循环不变式： 目前[lower..upper]是不确定有没有在数组里出现的。A[]中小于lower的都已经决定好了。
于是有三种情况：
（1） A【i】 < lower, 因为小于lower的都考虑过了，所以A【i】这个数不影响结果。
（2） A【i】 == lower, 显然lower = lower + 1,继续考虑。
（3） A【i】 > lower， 这说明[lower..A【i】 - 1]是缺失的区间。然后我们输出lower..A【i】 - 1，然后lower = A【i】 + 1。
三种情况下，都能维持我们的循环不变式。写出的代码简短、清晰易懂，更容易考虑边界条件（第一个，最后一个等）。
我觉得循环不变式，可以简化问题，请仔细理解循环不变式的思想。

时间复杂度 : O(n)
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
陷阱：注意当缺失的区间仅仅是一个数的时候，没有剪头"->"，还有最后一个缺失区间的处理。