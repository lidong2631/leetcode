<<<<<<< HEAD
class Solution:
    # @param A a list of integers
    # @return nothing, sort in place
    def sortColors(self, A):
        if A == []:
            return A
        dict = {}
        for i in range(len(A)):     #iterate A once, put each color into dictionary
            if A[i] not in dict:
                dict[A[i]] = 1
            else:
                dict[A[i]]+=1
        del A[:]                    #clear the contents of A
        if 0 in dict:                   #re-insert elements
            for i in range(dict[0]):
                A.append(0)
        if 1 in dict:
            for i in range(dict[1]):
                A.append(1)
        if 2 in dict:
            for i in range(dict[2]):
                A.append(2)

Note: 1 如果字典中放入的key是整数类型 索引时用整数 不用加‘ ’ ex if 0 in dict(Not if'0' in dict)
    2 清空list A要用del A[:] 不能用A = [] 这里A会被当成函数内变量 无法改变函数外的真正要改的变量A 除非通过返回值return给A才能重写A



第二种解法 更优
class Solution:
    # @param A a list of integers
    # @return nothing, sort in place
    def sortColors(self, A):
        p0 = 0; p2 = len(A)-1; i = 0
        while i <= p2:
            if A[i] == 0:
                A[i], A[p0] = A[p0], A[i]
                p0+=1
                i+=1            #碰到0时交换完值i要加1 因为p0和i 移动的是同方向 p0移动了i也要跟着移动一步 不然可能死循环 ex [0,0,0]
            elif A[i] == 2:
                A[i], A[p2] = A[p2], A[i]
                p2-=1           #碰到2时交换完i 不能加1 因i 被换的值不确定 ex [2,1,1,2]
            else:
                i+=1






题意：

Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

解题思路：这道题不允许使用排序库函数。那么最直观的解法是：遍历两遍数组，第一遍对0，1，2计数，第二遍对数组进行赋值，这样是可以ac的。

但题目的要求是只使用常数空间，而且只能遍历一遍。那么思路就比较巧妙了。设置两个头尾指针，头指针p0指向的位置是0该放置的位置，尾指针p2指向的位置是2该放置的位置。

i用来遍历整个数组，碰到0把它和p0指向的数交换，碰到2把它和p2指向的数交换，碰到1继续向后遍历。有点类似快速排序的分割数组这一步。

代码：
复制代码
class Solution:
    # @param A a list of integers
    # @return nothing, sort in place
    # @should learn another algorithm
    def sortColors(self, A):
        if A == []: return 
        p0 = 0; p2 = len(A) - 1; i = 0
        while i <= p2:
            if A[i] == 2:
                A[i], A[p2] = A[p2], A[i]
                p2 -= 1
            elif A[i] == 0:
                A[i], A[p0] = A[p0], A[i]
                p0 += 1
                i += 1
            else:
                i += 1








public class Solution {
    public void sortColors(int[] A) {
        if(A==null || A.length==0)
            return;
        int index0 = 0;
        int index1 = 0;
        for(int i=0; i<A.length; i++) {
            if(A[i]==0) {
                A[i] = 2;
                A[index1++] = 1;
                A[index0++] = 0;
            }
            else if(A[i]==1) {
                A[i] = 2;
                A[index1++] = 1;
            }
        }
    }
}

code ganker第二种解法 利用颜色三种这一点 两个指针一个负责指0 一个负责指1 扫一遍数组

如果当前i碰到元素是0 A[i]=2 0指针就把他指向的元素置0并前进1 这里1指针也要把他指向的元素置1并前进1 因为虽然没他什么事但由于1一定要排在0后面

意味1指针一定不能比0指针落后(否则他会把之前0指针置的0变为1) 所以他要跟0同步 也要一起前进 这里即使1指针将当前元素置为1也没关系 只要它置1排在

0指针置0后 那么他置的1就会被0指针置的0覆盖不影响结果

如果碰到1 A[i]=2 1指针就把他指向的元素置1并前进1

如果是2就都停留原地 i继续走

这里i永远将碰到的0 1 元素置为2 而前面0 1指针分别置他们所指元素为0或1







public class Solution {
    public void sortColors(int[] A) {
        if(A==null || A.length==0)
            return;
        int[] helper = new int[3];
        int[] res = new int[A.length];
        for(int i=0; i<A.length; i++)   //统计数组中每个值为i的元素出现的次数，存入数组C的第i项
            helper[A[i]]++;
        for(int i=1; i<3; i++)          //对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
            helper[i] = helper[i-1]+helper[i];
        for(int i=A.length-1; i>=0; i--)        //反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
        {
            res[helper[A[i]]-1] = A[i];
            helper[A[i]]--;
        }
        for(int i=0; i<A.length; i++)
            A[i] = res[i];
    }
}

Note: code ganker第一种解法 标准count sorting 要记住 这题关键是要牢记计数排序





public class Solution {
    public void sortColors(int[] A) {
        int i = 0; int p1 = 0; int p2 = A.length-1;
        while(i<=p2)
        {
            if(A[i]==0)
            {
                int tmp = A[i];
                A[i] = A[p1];
                A[p1] = tmp;
                i++;
                p1++;
            }
            else if(A[i]==2)
            {
                int tmp = A[i];
                A[i] = A[p2];
                A[p2] = tmp;
                p2--;
            }
            else i++;
        }
    }
}

Note: 根据python版改编的 设头尾指针 只遍历数组一次





计数排序:

计数排序（Counting sort）是一种稳定的线性时间排序算法。计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。

然后根据数组C来将A中的元素排到正确的位置。

当输入的元素是n个0到k之间的整数时，它的运行时间是Θ(n + k)。计数排序不是比较排序，排序的速度快于任何比较排序算法。

由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），这使得计数排序对于数据范围很大的数组，

需要大量时间和内存。例如：计数排序是用来排序0到100之间的数字的最好的算法，但是它不适合按字母顺序排序人名。但是，

计数排序可以用在基数排序中的算法来排序数据范围很大的数组。

通俗地理解，例如有10个年龄不同的人，统计出有8个人的年龄比A小，那A的年龄就排在第9位,用这个方法可以得到其他每个人的位置,也就排好了序。

当然，年龄有重复时需要特殊处理（保证稳定性），这就是为什么最后要反向填充目标数组，以及将每个数字的统计减去1的原因。 算法的步骤如下：

找出待排序的数组中最大和最小的元素

统计数组中每个值为i的元素出现的次数，存入数组C的第i项

对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）

反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1


Java语言的实现 (着重理解)

public class CountingSort {
    public static void main(String[] args) {
        int[] A = CountingSort.countingSort(new int[]{9, 7, 9, 3, 2});
        Utils.print(A);
    }

    public static int[] countingSort(int[] A) {
        int[] B = new int[A.length];    //B是最终的结果数组
        //假设A中的数据a有, a>=0 && a<k 并且k=10
        int k = 10;
        countingSort(A, B, k);
        return B;
    }

    public static void countingSort(int[] A, int[] B, int k) {
        int[] C = new int[k];
        //计数
        for(int j=0; j<A.length; j++) {
            int a = A[j];
            C[a] += 1;      //每次将A中的元素取出作为C的索引 对应值加1说明A数组中又出现了一次a元素 C={0, 0, 1, 1, 0, 0, 0, 1, 0, 2}
        }

        Utils.print(C);
        //求计数和
        for(int i=1; i<k; i++) {                                   2  3           7     9,9
            C[i] = C[i] + C[i-1];   //将C中的元素累加 以上例C = {0, 0, 1, 2, 2, 2, 2, 3, 3, 5}
        }

        Utils.print(C);
        //整理
        for(int j=A.length-1; j>=0; j--) {  //这里应该正向反向都可以
            int a = A[j];
            B[C[a]-1] = a;      //这里每次要反向填充B数组 这样可以处理重复元素
            c[a] -= 1;
        }
    }
}


//优化一些的计数排序 对C数组的大小 可以取待排序数组中元素大小的极值差＋1
public class CountSort {
    public static void main(String[] args) {
        //排序的数组
        int[] a = {9, 7, 9, 3, 2};
        int[] b = countSort(a); 
    }

    public static int[] countSort(int[] a) {
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for(int i : a) {        //取a数组中最大值 最小值
            if(i>max) {
                max = i;
            }
            if(i<min) {
                min = i;
            }
        }

        //这里k的大小是要排序数组中 元素大小极值差＋1
        int k = max - min + 1;
        int[] c = new int[k];
        for(int i=0; i<a.length; i++) {
            int a = a[i];
            c[a-min] += 1;      //优化的地方 减小了数组c的大小
        }
        for(int i=1; i<c.length; i++) {
            c[i] = c[i] + c[i-1];
        }
        for(int i=a.length-1; i>=0; i--) {
            b[c[a[i]-min]] = a[i];
            c[a[i]-min] -= 1;
        }
        return b;
    }
}





from code ganker:

这道题也是数组操作的题目，其实就是要将数组排序，只是知道数组中只有三个元素0,1,2。熟悉计数排序的朋友可能很快就发现这其实就是使用计数排序，元素空间只需要三个元素即可。

代码如下： 

public void sortColors(int[] A) {
    if(A==null || A.length==0)
        return;
    int[] res = new int[A.length];
    int[] helper = new int[3];
    for(int i=0;i<A.length;i++)
    {
        helper[A[i]]++;
    }
    for(int i=1;i<3;i++)
    {
        helper[i]=helper[i]+helper[i-1];
    }
    for(int i=A.length-1;i>=0;i--)
    {
        res[helper[A[i]]-1] = A[i];
        helper[A[i]]--;
    }
    for(int i=0;i<A.length;i++)
    {
        A[i] = res[i];
    }
}

上面的代码是计数排序的标准解法，可以看到总共进行了三次扫描，其实最后一次只是把结果数组复制到原数组而已，如果不需要in-place的结果只需要两次扫描。

其实就算返回元素组也可以是两次扫描，这需要用到元素只有0,1,2的本质。我们知道helper[i]中是包含着0,1,2的元素数量，

我们只需要按照helper[0,1,2]的数量依次赋值过来即可（每层循环把helper[i]--，如果helper[i]到0就i++就可以了），只是这样就不是计数排序比较标准的解法，

我希望还是复习一下。

这种方法的时间复杂度是O(2*n)，空间是O(k)，k是颜色的数量，这里是3。

上述方法需要两次扫描，我们考虑怎么用一次扫描来解决。其实还是利用了颜色是三种这一点，道理其实也简单，就是搞两个指针，一个指在当前0的最后一个下标，

另一个是指在当前1的最后一个下标（2不需要指针因为剩下的都是2了）。进行一次扫描，如果遇到0就两个指针都前进一步并进行赋值，如果遇到1就后一个指针前进一步并赋值。代码如下： 

public void sortColors(int[] A) {
    if(A==null || A.length==0)
        return;
    int idx0 = 0;
    int idx1 = 0;
    for(int i=0;i<A.length;i++)
    {
        if(A[i]==0)
        {
            A[i] = 2;
            A[idx1++] = 1;
            A[idx0++] = 0;
        }
        else if(A[i]==1)
        {
            A[i] = 2;
            A[idx1++] = 1;
        }
    }
}

上述方法时间复杂度还是O(n)，只是只需要一次扫描，空间上是O(1)（如果颜色种类是已知的话）。

这道题我觉得主要还是熟悉一下计数排序，计数排序是线性排序中比较重要的一种，关于排序要搞个专题专门的复习一下，很多排序的基本思想都对解题有帮助哈。
=======
class Solution:
    # @param A a list of integers
    # @return nothing, sort in place
    def sortColors(self, A):
        if A == []:
            return A
        dict = {}
        for i in range(len(A)):     #iterate A once, put each color into dictionary
            if A[i] not in dict:
                dict[A[i]] = 1
            else:
                dict[A[i]]+=1
        del A[:]                    #clear the contents of A
        if 0 in dict:                   #re-insert elements
            for i in range(dict[0]):
                A.append(0)
        if 1 in dict:
            for i in range(dict[1]):
                A.append(1)
        if 2 in dict:
            for i in range(dict[2]):
                A.append(2)

Note: 1 如果字典中放入的key是整数类型 索引时用整数 不用加‘ ’ ex if 0 in dict(Not if'0' in dict)
    2 清空list A要用del A[:] 不能用A = [] 这里A会被当成函数内变量 无法改变函数外的真正要改的变量A 除非通过返回值return给A才能重写A



第二种解法 更优
class Solution:
    # @param A a list of integers
    # @return nothing, sort in place
    def sortColors(self, A):
        p0 = 0; p2 = len(A)-1; i = 0
        while i <= p2:
            if A[i] == 0:
                A[i], A[p0] = A[p0], A[i]
                p0+=1
                i+=1            #碰到0时交换完值i要加1 因为p0和i 移动的是同方向 p0移动了i也要跟着移动一步 不然可能死循环 ex [0,0,0]
            elif A[i] == 2:
                A[i], A[p2] = A[p2], A[i]
                p2-=1           #碰到2时交换完i 不能加1 因i 被换的值不确定 ex [2,1,1,2]
            else:
                i+=1






题意：

Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

解题思路：这道题不允许使用排序库函数。那么最直观的解法是：遍历两遍数组，第一遍对0，1，2计数，第二遍对数组进行赋值，这样是可以ac的。

但题目的要求是只使用常数空间，而且只能遍历一遍。那么思路就比较巧妙了。设置两个头尾指针，头指针p0指向的位置是0该放置的位置，尾指针p2指向的位置是2该放置的位置。

i用来遍历整个数组，碰到0把它和p0指向的数交换，碰到2把它和p2指向的数交换，碰到1继续向后遍历。有点类似快速排序的分割数组这一步。

代码：
复制代码
class Solution:
    # @param A a list of integers
    # @return nothing, sort in place
    # @should learn another algorithm
    def sortColors(self, A):
        if A == []: return 
        p0 = 0; p2 = len(A) - 1; i = 0
        while i <= p2:
            if A[i] == 2:
                A[i], A[p2] = A[p2], A[i]
                p2 -= 1
            elif A[i] == 0:
                A[i], A[p0] = A[p0], A[i]
                p0 += 1
                i += 1
            else:
                i += 1








public class Solution {
    public void sortColors(int[] A) {
        if(A==null || A.length==0)
            return;
        int index0 = 0;
        int index1 = 0;
        for(int i=0; i<A.length; i++) {
            if(A[i]==0) {
                A[i] = 2;
                A[index1++] = 1;
                A[index0++] = 0;
            }
            else if(A[i]==1) {
                A[i] = 2;
                A[index1++] = 1;
            }
        }
    }
}

code ganker第二种解法 利用颜色三种这一点 两个指针一个负责指0 一个负责指1 扫一遍数组

如果当前i碰到元素是0 A[i]=2 0指针就把他指向的元素置0并前进1 这里1指针也要把他指向的元素置1并前进1 因为虽然没他什么事但由于1一定要排在0后面

意味1指针一定不能比0指针落后(否则他会把之前0指针置的0变为1) 所以他要跟0同步 也要一起前进 这里即使1指针将当前元素置为1也没关系 只要它置1排在

0指针置0后 那么他置的1就会被0指针置的0覆盖不影响结果

如果碰到1 A[i]=2 1指针就把他指向的元素置1并前进1

如果是2就都停留原地 i继续走

这里i永远将碰到的0 1 元素置为2 而前面0 1指针分别置他们所指元素为0或1







public class Solution {
    public void sortColors(int[] A) {
        if(A==null || A.length==0)
            return;
        int[] helper = new int[3];
        int[] res = new int[A.length];
        for(int i=0; i<A.length; i++)   //统计数组中每个值为i的元素出现的次数，存入数组C的第i项
            helper[A[i]]++;
        for(int i=1; i<3; i++)          //对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
            helper[i] = helper[i-1]+helper[i];
        for(int i=A.length-1; i>=0; i--)        //反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
        {
            res[helper[A[i]]-1] = A[i];
            helper[A[i]]--;
        }
        for(int i=0; i<A.length; i++)
            A[i] = res[i];
    }
}

Note: code ganker第一种解法 标准count sorting 要记住 这题关键是要牢记计数排序





public class Solution {
    public void sortColors(int[] A) {
        int i = 0; int p1 = 0; int p2 = A.length-1;
        while(i<=p2)
        {
            if(A[i]==0)
            {
                int tmp = A[i];
                A[i] = A[p1];
                A[p1] = tmp;
                i++;
                p1++;
            }
            else if(A[i]==2)
            {
                int tmp = A[i];
                A[i] = A[p2];
                A[p2] = tmp;
                p2--;
            }
            else i++;
        }
    }
}

Note: 根据python版改编的 设头尾指针 只遍历数组一次





计数排序:

计数排序（Counting sort）是一种稳定的线性时间排序算法。计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。

然后根据数组C来将A中的元素排到正确的位置。

当输入的元素是n个0到k之间的整数时，它的运行时间是Θ(n + k)。计数排序不是比较排序，排序的速度快于任何比较排序算法。

由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），这使得计数排序对于数据范围很大的数组，

需要大量时间和内存。例如：计数排序是用来排序0到100之间的数字的最好的算法，但是它不适合按字母顺序排序人名。但是，

计数排序可以用在基数排序中的算法来排序数据范围很大的数组。

通俗地理解，例如有10个年龄不同的人，统计出有8个人的年龄比A小，那A的年龄就排在第9位,用这个方法可以得到其他每个人的位置,也就排好了序。

当然，年龄有重复时需要特殊处理（保证稳定性），这就是为什么最后要反向填充目标数组，以及将每个数字的统计减去1的原因。 算法的步骤如下：

找出待排序的数组中最大和最小的元素

统计数组中每个值为i的元素出现的次数，存入数组C的第i项

对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）

反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1


Java语言的实现 (着重理解)

public class CountingSort {
    public static void main(String[] args) {
        int[] A = CountingSort.countingSort(new int[]{9, 7, 9, 3, 2});
        Utils.print(A);
    }

    public static int[] countingSort(int[] A) {
        int[] B = new int[A.length];    //B是最终的结果数组
        //假设A中的数据a有, a>=0 && a<k 并且k=10
        int k = 10;
        countingSort(A, B, k);
        return B;
    }

    public static void countingSort(int[] A, int[] B, int k) {
        int[] C = new int[k];
        //计数
        for(int j=0; j<A.length; j++) {
            int a = A[j];
            C[a] += 1;      //每次将A中的元素取出作为C的索引 对应值加1说明A数组中又出现了一次a元素 C={0, 0, 1, 1, 0, 0, 0, 1, 0, 2}
        }

        Utils.print(C);
        //求计数和
        for(int i=1; i<k; i++) {                                   2  3           7     9,9
            C[i] = C[i] + C[i-1];   //将C中的元素累加 以上例C = {0, 0, 1, 2, 2, 2, 2, 3, 3, 5}
        }

        Utils.print(C);
        //整理
        for(int j=A.length-1; j>=0; j--) {  //这里应该正向反向都可以
            int a = A[j];
            B[C[a]-1] = a;      //这里每次要反向填充B数组 这样可以处理重复元素
            c[a] -= 1;
        }
    }
}


//优化一些的计数排序 对C数组的大小 可以取待排序数组中元素大小的极值差＋1
public class CountSort {
    public static void main(String[] args) {
        //排序的数组
        int[] a = {9, 7, 9, 3, 2};
        int[] b = countSort(a); 
    }

    public static int[] countSort(int[] a) {
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for(int i : a) {        //取a数组中最大值 最小值
            if(i>max) {
                max = i;
            }
            if(i<min) {
                min = i;
            }
        }

        //这里k的大小是要排序数组中 元素大小极值差＋1
        int k = max - min + 1;
        int[] c = new int[k];
        for(int i=0; i<a.length; i++) {
            int a = a[i];
            c[a-min] += 1;      //优化的地方 减小了数组c的大小
        }
        for(int i=1; i<c.length; i++) {
            c[i] = c[i] + c[i-1];
        }
        for(int i=a.length-1; i>=0; i--) {
            b[c[a[i]-min]] = a[i];
            c[a[i]-min] -= 1;
        }
        return b;
    }
}





from code ganker:

这道题也是数组操作的题目，其实就是要将数组排序，只是知道数组中只有三个元素0,1,2。熟悉计数排序的朋友可能很快就发现这其实就是使用计数排序，元素空间只需要三个元素即可。

代码如下： 

public void sortColors(int[] A) {
    if(A==null || A.length==0)
        return;
    int[] res = new int[A.length];
    int[] helper = new int[3];
    for(int i=0;i<A.length;i++)
    {
        helper[A[i]]++;
    }
    for(int i=1;i<3;i++)
    {
        helper[i]=helper[i]+helper[i-1];
    }
    for(int i=A.length-1;i>=0;i--)
    {
        res[helper[A[i]]-1] = A[i];
        helper[A[i]]--;
    }
    for(int i=0;i<A.length;i++)
    {
        A[i] = res[i];
    }
}

上面的代码是计数排序的标准解法，可以看到总共进行了三次扫描，其实最后一次只是把结果数组复制到原数组而已，如果不需要in-place的结果只需要两次扫描。

其实就算返回元素组也可以是两次扫描，这需要用到元素只有0,1,2的本质。我们知道helper[i]中是包含着0,1,2的元素数量，

我们只需要按照helper[0,1,2]的数量依次赋值过来即可（每层循环把helper[i]--，如果helper[i]到0就i++就可以了），只是这样就不是计数排序比较标准的解法，

我希望还是复习一下。

这种方法的时间复杂度是O(2*n)，空间是O(k)，k是颜色的数量，这里是3。

上述方法需要两次扫描，我们考虑怎么用一次扫描来解决。其实还是利用了颜色是三种这一点，道理其实也简单，就是搞两个指针，一个指在当前0的最后一个下标，

另一个是指在当前1的最后一个下标（2不需要指针因为剩下的都是2了）。进行一次扫描，如果遇到0就两个指针都前进一步并进行赋值，如果遇到1就后一个指针前进一步并赋值。代码如下： 

public void sortColors(int[] A) {
    if(A==null || A.length==0)
        return;
    int idx0 = 0;
    int idx1 = 0;
    for(int i=0;i<A.length;i++)
    {
        if(A[i]==0)
        {
            A[i] = 2;
            A[idx1++] = 1;
            A[idx0++] = 0;
        }
        else if(A[i]==1)
        {
            A[i] = 2;
            A[idx1++] = 1;
        }
    }
}

上述方法时间复杂度还是O(n)，只是只需要一次扫描，空间上是O(1)（如果颜色种类是已知的话）。

这道题我觉得主要还是熟悉一下计数排序，计数排序是线性排序中比较重要的一种，关于排序要搞个专题专门的复习一下，很多排序的基本思想都对解题有帮助哈。
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
