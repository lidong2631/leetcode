public ArrayList<ArrayList<Integer>> subsets(int[] num) {
    if(num == null)
        return null;
    Arrays.sort(num);
    return helper(num, num.length-1);
}
private ArrayList<ArrayList<Integer>> helper(int[] num, int index)
{
    if(index == -1)
    {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> elem = new ArrayList<Integer>();
        res.add(elem);
        return res;
    }
    ArrayList<ArrayList<Integer>> res = helper(num,index-1);	//先递归到底 然后从空集［］开始一层层返回 17-24行跟非递归写法相同 只是每次res.add(elem)返回到上一层递归 没有了非递归的外层for循环
    int size = res.size();
    for(int i=0;i<size;i++)
    {
        ArrayList<Integer> elem = new ArrayList<Integer>(res.get(i));
        elem.add(num[index]);
        res.add(elem);
    }
    return res;
}

Note: 递归写法 跟非递归差不多 只是多了回溯过程











import java.util.*;

public class subsets {
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
	    ArrayList<ArrayList<Integer>> res = new  ArrayList<ArrayList<Integer>>();
	    res.add(new ArrayList<Integer>());
	    if(S == null || S.length == 0)
	        return res;
	    Arrays.sort(S);
	    for(int i=0;i<S.length;i++)
	    {
	    		int size = res.size();			//严重注意！！！！！这里必须要写这句 否则如果这里不写 而在14行的for循环里写j<res.size()的话 当执行完20行后res里又新加了一个元素item 然后返回for判断时res.size()已经被更新成2 又可以继续循环了 这样下去就是死循环
//System.out.println("number: " + i + " time" + "res.size(): " + size);
	        for(int j=0;j<size;j++)
	        {
	            ArrayList<Integer> item = new ArrayList<Integer>(res.get(j));	//当第一次执行这里j=0 res.get(0)是[] 所以item也是空的list 接下来item.add(S[i])接受第一个元素后才不是空
	            item.add(S[i]);																								//当用res.add(new ArrayList<Integer>())时res里面第一个元素是[] 而当ArrayList<Integer> item = new ArrayList<Integer>(res.get(j))新建一个list时item是空list 所以它里面没有[]这个元素
//for(int k=0; k<item.size(); k++)
		//System.out.println("now item is " + item.get(k));	            
	            res.add(item);
//for(int k=0; k<res.size(); k++)
		//System.out.println("this is j = " + j + " and res is " + res.get(k));
	        }
	    }
//System.out.println("Finally res is :");
//for(int k=0; k<res.size(); k++)
		//System.out.println("res is " + res.get(k));
	    return res;
	}
}

Note：非递归解法 主要是item每次逐个遍历上一轮生成的res里的每个元素 然后把它们都加上新的元素S[i] 再将新生成的item加到res里




number: 0 timeres.size(): 1

now item is 1
this is j = 0 and res is []
this is j = 0 and res is [1]
number: 1 timeres.size(): 2

now item is 2
this is j = 0 and res is []
this is j = 0 and res is [1]
this is j = 0 and res is [2]
now item is 1
now item is 2
this is j = 1 and res is []
this is j = 1 and res is [1]
this is j = 1 and res is [2]
this is j = 1 and res is [1, 2]
number: 2 timeres.size(): 4

now item is 3
this is j = 0 and res is []
this is j = 0 and res is [1]
this is j = 0 and res is [2]
this is j = 0 and res is [1, 2]
this is j = 0 and res is [3]
now item is 1
now item is 3
this is j = 1 and res is []
this is j = 1 and res is [1]
this is j = 1 and res is [2]
this is j = 1 and res is [1, 2]
this is j = 1 and res is [3]
this is j = 1 and res is [1, 3]
now item is 2
now item is 3
this is j = 2 and res is []
this is j = 2 and res is [1]
this is j = 2 and res is [2]
this is j = 2 and res is [1, 2]
this is j = 2 and res is [3]
this is j = 2 and res is [1, 3]
this is j = 2 and res is [2, 3]
now item is 1
now item is 2
now item is 3
this is j = 3 and res is []
this is j = 3 and res is [1]
this is j = 3 and res is [2]
this is j = 3 and res is [1, 2]
this is j = 3 and res is [3]
this is j = 3 and res is [1, 3]
this is j = 3 and res is [2, 3]
this is j = 3 and res is [1, 2, 3]
Finally res is :
res is []
res is [1]
res is [2]
res is [1, 2]
res is [3]
res is [1, 3]
res is [2, 3]
res is [1, 2, 3]