class Solution:
    # @return a list of lists of length 4, [[val1,val2,val3,val4]]
    def fourSum(self, num, target):
        length, res, dict = len(num), set(), {}     #length为num长度 res为set防止重复tuple dict初始为空哈希表
        if length < 4:                          #如果长度小于4 直接返回［］
            return []
        num.sort()                          #首先将num排序
        for i in range(length):
            for j in range(i+1, length):            #双循环 以两个数字的和为索引建立哈希表 如果索引不存在建立之 否则将新的tuple append到对应的索引中
                if num[i]+num[j] not in dict:
                    dict[num[i]+num[j]] = [(i, j)]
                else:
                    dict[num[i]+num[j]].append([i, j])
        for i in range(length):                     #双循环 注意内层循环到length－2即结束 因为再往后凑不出四个数字
            for j in range(i+1, length-2):
                k = target - num[i] - num[j]        #k为target和num[i], num[j]的差值
                if k in dict:                       #如果k在哈希表中有记录
                    for l in dict[k]:               #遍历每一个对应的tuple对
                        if l[0] > j:                #如果tuple对里第一个索引大于j 则可以凑成一组合法解
                            res.add((num[i], num[j], num[l[0]], num[l[1]]))     #将它们加到res中 注意res为set 可以防止重复解
        return [list(i) for i in res]               #最后将res中的tuple转成list
    








题意：从数组中找到4个数，使它们的和为target。要求去重，可能有多组解，需要都找出来。

解题思路：一开始想要像3Sum那样去解题，时间复杂度为O(N^3)，可无论怎么写都是Time Limited Exceeded。而同样的算法使用C++是可以通过的。说明Python的执行速度比C++慢很多。还说明了一点，大概出题人的意思不是要让我们去像3Sum那样去解题，否则这道题就出的没有意义了。这里参考了kitt的解法：http://chaoren.is-programmer.com/posts/45308.html

　　　　   需要用到哈希表的思路，这样可以空间换时间，以增加空间复杂度的代价来降低时间复杂度。首先建立一个字典dict，字典的key值为数组中每两个元素的和，每个key对应的value为这两个元素的下标组成的元组，元组不一定是唯一的。如对于num=[1,2,3,2]来说，dict={3:[(0,1),(0,3)], 4:[(0,2),(1,3)], 5:[(1,2),(2,3)]}。这样就可以检查target-key这个值在不在dict的key值中，如果target-key在dict中并且下标符合要求，那么就找到了这样的一组解。由于需要去重，这里选用set()类型的数据结构，即无序无重复元素集。最后将每个找出来的解(set()类型)转换成list类型输出即可。

代码：

复制代码
class Solution:
    # @return a list of lists of length 4, [[val1,val2,val3,val4]]
    def fourSum(self, num, target):
        numLen, res, dict = len(num), set(), {}
        if numLen < 4: return []
        num.sort()
        for p in range(numLen):
            for q in range(p+1, numLen): 
                if num[p]+num[q] not in dict:
                    dict[num[p]+num[q]] = [(p,q)]
                else:
                    dict[num[p]+num[q]].append((p,q))
        for i in range(numLen):
            for j in range(i+1, numLen-2):
                T = target-num[i]-num[j]
                if T in dict:
                    for k in dict[T]:
                        if k[0] > j: res.add((num[i],num[j],num[k[0]],num[k[1]]))
        return [list(i) for i in res]


下面为过不了oj的O(N^3)解法:

class Solution:
    # @return a list of lists of length 4, [[val1,val2,val3,val4]]
    def fourSum(self, num, target):
        num.sort(); res=[]
        for i in range(len(num)):
            if i>0 and num[i]==num[i-1]: continue
            for j in range(i+1,len(num)):
                if j>i+1 and num[j]==num[j-1]: continue
                l=j+1; r=len(num)-1
                while l<r:
                    sum=num[i]+num[j]+num[l]+num[r]
                    if sum>target:
                        r-=1
                    elif sum<target:
                        l+=1
                    elif l>j+1 and num[l]==num[l-1]:
                        l+=1
                    elif r<len(num)-1 and num[r]==num[r+1]:
                        r-=1
                    else:
                        res.append([num[i],num[j],num[l],num[r]])
                        l+=1; r-=1
        return res








from code ganker:

这道题要求跟3Sum差不多，只是需求扩展到四个的数字的和了。我们还是可以按照3Sum中的解法，只是在外面套一层循环，相当于求n次3Sum。

我们知道3Sum的时间复杂度是O(n^2)，所以如果这样解的总时间复杂度是O(n^3)。代码如下：

public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(num==null||num.length==0)
        return res;
    Arrays.sort(num);
    for(int i=num.length-1;i>2;i--)
    {
        if(i==num.length-1 || num[i]!=num[i+1])
        {
            ArrayList<ArrayList<Integer>> curRes = threeSum(num,i-1,target-num[i]);
            for(int j=0;j<curRes.size();j++)
            {
                curRes.get(j).add(num[i]);
            }
            res.addAll(curRes);
        }
    }
    return res;        
}
private ArrayList<ArrayList<Integer>> threeSum(int[] num, int end, int target)
{
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    for(int i=end;i>1;i--)
    {
        if(i==end || num[i]!=num[i+1])
        {
            ArrayList<ArrayList<Integer>> curRes = twoSum(num,i-1,target-num[i]);
            for(int j=0;j<curRes.size();j++)
            {
                curRes.get(j).add(num[i]);
            }
            res.addAll(curRes);
        }
    }
    return res;
}
private ArrayList<ArrayList<Integer>> twoSum(int[] num, int end, int target)
{
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    int l=0;
    int r=end;
    while(l<r)
    {
        if(num[l]+num[r]==target)
        {
            ArrayList<Integer> item = new ArrayList<Integer>();
            item.add(num[l]);
            item.add(num[r]);
            res.add(item);
            l++;
            r--;
            while(l<r&&num[l]==num[l-1])
                l++;
            while(l<r&&num[r]==num[r+1])
                r--;
        }
        else if(num[l]+num[r]>target)
        {
            r--;
        }
        else
        {
            l++;
        }
    }
    return res;
}



上述这种方法比较直接，根据3Sum的结果很容易进行推广。那么时间复杂度能不能更好呢？其实我们可以考虑用二分法的思路，如果把所有的两两pair都求出来，

然后再进行一次Two Sum的匹配，我们知道Two Sum是一个排序加上一个线性的操作，并且把所有pair的数量是O((n-1)+(n-2)+...+1)=O(n(n-1)/2)=O(n^2)。

所以对O(n^2)的排序如果不用特殊线性排序算法是O(n^2*log(n^2))=O(n^2*2logn)=O(n^2*logn)，算法复杂度比上一个方法的O(n^3)是有提高的。

思路虽然明确，不过细节上会多很多情况要处理。首先，我们要对每一个pair建一个数据结构来存储元素的值和对应的index，

这样做是为了后面当找到合适的两对pair相加能得到target值时看看他们是否有重叠的index，如果有说明它们不是合法的一个结果，因为不是四个不同的元素。

接下来我们还得对这些pair进行排序，所以要给pair定义comparable的函数。最后，当进行Two Sum的匹配时因为pair不再是一个值，所以不能像Two Sum中那样直接跳过相同的，

每一组都得进行查看，这样就会出现重复的情况，所以我们还得给每一个四个元素组成的tuple定义hashcode和相等函数，以便可以把当前求得的结果放在一个HashSet里面，

这样得到新结果如果是重复的就不加入结果集了。

代码如下：

private ArrayList<ArrayList<Integer>> twoSum(ArrayList<Pair> pairs, int target){
    HashSet<Tuple> hashSet = new HashSet<Tuple>();
    int l = 0;
    int r = pairs.size()-1;
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    while(l<r){
        if(pairs.get(l).getSum()+pairs.get(r).getSum()==target)
        {
            int lEnd = l;
            int rEnd = r;
            while(lEnd<rEnd && pairs.get(lEnd).getSum()==pairs.get(lEnd+1).getSum())
            {
                lEnd++;
            }
            while(lEnd<rEnd && pairs.get(rEnd).getSum()==pairs.get(rEnd-1).getSum())
            {
                rEnd--;
            }
            for(int i=l;i<=lEnd;i++)
            {
                for(int j=r;j>=rEnd;j--)
                {
                    if(check(pairs.get(i),pairs.get(j)))
                    {
                        ArrayList<Integer> item = new ArrayList<Integer>();
                        item.add(pairs.get(i).nodes[0].value);
                        item.add(pairs.get(i).nodes[1].value);
                        item.add(pairs.get(j).nodes[0].value);
                        item.add(pairs.get(j).nodes[1].value);
                        //Collections.sort(item);
                        Tuple tuple = new Tuple(item);
                        if(!hashSet.contains(tuple))
                        {
                            hashSet.add(tuple);
                            res.add(tuple.num);
                        }
                    }                        
                }
            }
            l = lEnd+1;
            r = rEnd-1;
        }
        else if(pairs.get(l).getSum()+pairs.get(r).getSum()>target)
        {
            r--;
        }
        else{
            l++;
        }
    }
    return res;
}
private boolean check(Pair p1, Pair p2)
{
    if(p1.nodes[0].index == p2.nodes[0].index || p1.nodes[0].index == p2.nodes[1].index)
        return false;
    if(p1.nodes[1].index == p2.nodes[0].index || p1.nodes[1].index == p2.nodes[1].index)
        return false;
    return true;
}

第二种方法比第一种方法时间上还是有提高的，其实这道题可以推广到k-Sum的问题，基本思想就是和第二种方法一样进行二分，然后两两结合，不过细节就非常复杂了（这点从上面的第二种解法就能看出来），

所以不是很适合在面试中出现，有兴趣的朋友可以进一步思考或者搜索网上材料哈

