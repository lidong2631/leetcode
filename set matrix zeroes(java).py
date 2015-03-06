class Solution:
    # @param matrix, a list of lists of integers
    # RETURN NOTHING, MODIFY matrix IN PLACE.
    def setZeroes(self, matrix):
        rowNum = len(matrix); colNum = len(matrix[0])
        row = [0 for i in range(rowNum)]
        col = [0 for i in range(colNum)]
        for i in range(rowNum):
            for j in range(colNum):
                if matrix[i][j] == 0:
                    row[i] = 1
                    col[j] = 1
        for i in range(rowNum):
            for j in range(colNum):
                if row[i] == 1 or col[j] == 1:
                    matrix[i][j] = 0

Note: 以上解法需要O(m+n)空间 下面的解法用matrix第一行和第一列来记录0的情况 替代了上面程序中row,col的作用 优化为常数空间

一共四步：

1.先确定第一行和第一列是否需要清零
即，看看第一行中是否有0，记下来。也同时记下来第一列中有没有0。

2.扫描剩下的矩阵元素，如果遇到了0，就将对应的第一行和第一列上的元素赋值为0
这里不用担心会将本来第一行或第一列的1改成了0，因为这些值最后注定要成为0的。

3.根据第一行和第一列的信息，已经可以将剩下的矩阵元素赋值为结果所需的值了
即，拿第一行为例，如果扫描到一个0，就将这一列都清0.

4.根据1中确定的状态，处理第一行和第一列。
如果最开始得到的第一行中有0的话，就整行清零。同理对列进行处理。



class Solution:
    # @param matrix, a list of lists of integers
    # RETURN NOTHING, MODIFY matrix IN PLACE.
    def setZeroes(self, matrix):
        firstRowMark = 1; firstColMark = 1
        for i in range(len(matrix[0])):         #第一步 先记录第一行和第一列是否有0 如果有则更改firstRowMark, firstColMark值 break循环
            if matrix[0][i] == 0:
                firstRowMark = 0
                break
        for j in range(len(matrix)):            
            if matrix[j][0] == 0:
                firstColMark = 0
                break
        for i in range(1, len(matrix)):         #第二步 对于剩余矩阵 将0情况记录在第一行和第一列中
            for j in range(1, len(matrix[0])):
                if matrix[i][j] == 0:
                    matrix[0][j] = 0
                    matrix[i][0] = 0
        for i in range(1, len(matrix)):         #第三步 对于剩余矩阵 根据第一行列中记录的情况置0
            for j in range(1, len(matrix[0])):
                if matrix[0][j] == 0 or matrix[i][0] == 0:
                    matrix[i][j] = 0
        if firstRowMark == 0:                   #第四步 最后设置第一行列的0
            for i in range(len(matrix[0])):
                matrix[0][i] = 0
        if firstColMark == 0:
            for j in range(len(matrix)):
                matrix[j][0] = 0




public class Solution {
    public void setZeroes(int[][] matrix) {
        int firstRowMark = 1; int firstColMark = 1;
        for(int i=0; i<matrix.length; i++)
        {
            if(matrix[i][0]==0)
            {
                firstRowMark = 0;
                break;
            }
        }
        for(int i=0; i<matrix[0].length; i++)
        {
            if(matrix[0][i]==0)
            {
                firstColMark = 0;
                break;
            }
        }
        for(int i=1; i<matrix.length; i++)
        {
            for(int j=1; j<matrix[0].length; j++)
            {
                if(matrix[i][j]==0)
                {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i=1; i<matrix.length; i++)
            for(int j=1; j<matrix[0].length; j++)
                if(matrix[0][j]==0 || matrix[i][0]==0)
                    matrix[i][j] = 0;
        if(firstRowMark==0)
        for(int i=0; i<matrix.length; i++)
            matrix[i][0] = 0;
        if(firstColMark==0)
        for(int i=0; i<matrix[0].length; i++)
            matrix[0][i] = 0;
    }
}

Note: 这题python code ganker版都一样 cc150里也有这题 要注意三种方法 如何让空间复杂度逐步由O(m*n), O(m+n)到O(1)






from code ganker:

这是一个矩阵操作的题目，目标很明确，就是如果矩阵如果有元素为0，就把对应的行和列上面的元素都置为0。这里最大的问题就是我们遇到0的时候不能直接把矩阵的行列在当前矩阵直接置0，

否则后面还没访问到的会被当成原来是0，最后会把很多不该置0的行列都置0了。

一个直接的想法是备份一个矩阵，然后在备份矩阵上判断，在原矩阵上置0，这样当然是可以的，不过空间复杂度是O(m*n)，不是很理想。

上面的方法如何优化呢？我们看到其实判断某一项是不是0只要看它对应的行或者列应不应该置0就可以，所以我们可以维护一个行和列的布尔数组，然后扫描一遍矩阵记录那一行或者列是不是应该置0即可，

后面赋值是一个常量时间的判断。这种方法的时间复杂度是O(m+n)。

其实还可以再优化，我们考虑使用第一行和第一列来记录上面所说的行和列的置0情况，这里问题是那么第一行和第一列自己怎么办？想要记录它们自己是否要置0，

只需要两个变量（一个是第一行，一个是第一列）就可以了。然后就是第一行和第一列，如果要置0，就把它的值赋成0（反正它最终也该是0，无论第一行或者第一列有没有0），否则保留原值

然后根据第一行和第一列的记录对其他元素进行置0。最后再根据前面的两个标记来确定是不是要把第一行和第一列置0就可以了。这样的做法只需要两个额外变量，所以空间复杂度是O(1)。

时间上来说上面三种方法都是一样的，需要进行两次扫描，一次确定行列置0情况，一次对矩阵进行实际的置0操作，所以总的空间复杂度是O(m*n)。代码如下： 

public void setZeroes(int[][] matrix) {
    if(matrix==null || matrix.length==0 || matrix[0].length==0)
        return;
    boolean rowFlag = false;
    boolean colFlag = false;
    for(int i=0;i<matrix.length;i++)
    {
        if(matrix[i][0]==0)
        {
            colFlag = true;
            break;
        }
    }
    for(int i=0;i<matrix[0].length;i++)
    {
        if(matrix[0][i]==0)
        {
            rowFlag = true;
            break;
        }
    }      
    for(int i=1;i<matrix.length;i++)
    {
        for(int j=1;j<matrix[0].length;j++)
        {
            if(matrix[i][j]==0)
            {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    for(int i=1;i<matrix.length;i++)
    {
        for(int j=1;j<matrix[0].length;j++)
        {
            if(matrix[i][0]==0 || matrix[0][j]==0)
                matrix[i][j] = 0;
        }
    }
    if(colFlag)
    {
        for(int i=0;i<matrix.length;i++)
        {
            matrix[i][0] = 0;
        }
    }
    if(rowFlag)
    {
        for(int i=0;i<matrix[0].length;i++)
        {
            matrix[0][i] = 0;
        }
    }
}

这道题也是cc150里面比较经典的题目，看似比较简单，却可以重重优化，最终达到常量空间。其实面试中面试官看重的是对于算法时间空间复杂度的理解，对优化的概念，

这些常常比题目本身的难度更加重要，平常做题还是要对这些算法分析多考虑哈。






