题意：

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

解题思路：先将矩阵转置，然后将矩阵的每一行翻转，就可以得到所要求的矩阵了。

代码：


class Solution:
    # @param matrix, a list of lists of integers
    # @return a list of lists of integers
    def rotate(self, matrix):
        n = len(matrix)
        for i in range(n):
            for j in range(i+1, n):
                matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
        for i in range(n):
            matrix[i].reverse()
        return matrix



关于矩阵的一些操作
1 顺时针转90度： 两种方式 一个先求转置再每行反转 一个两层循环由外向内一个个转

2 顺时针(逆时针)180度： 直接转 更简单 每次将两行对应逆序交换即可

1   2  3  4             16 15 14 13
5   6  7  8             12 11 10  9
9  10 11 12             8  7  6   5
13 14 15 16             4  3  2   1
public class Solution {
    public void rotate(int[][] matrix) {
        for(int layer=0; layer<matrix.length/2; layer++) {
            for(int i=0; i<matrix.length; i++) {
                int tmp = matrix[layer][i];
                matrix[layer][i] = matrix[matrix.length-1-layer][matrix.length-1-i];
                matrix[matrix.length-1-layer][matrix.length-1-i] = tmp;
            }
        }
    }
}



3 顺时针270度： 相当于逆时针90度 方法同1
public class Solution {
    public void rotate(int[][] matrix) {
        for(int layer=0; layer<matrix.length/2; layer++) {
            for(int i=layer; i<matrix.length-1-layer; i++) {
                int tmp = matrix[layer][i];
                matrix[layer][i] = matrix[i][matrix.length-1-layer];
                matrix[i][matrix.length-1-layer] = matrix[matrix.length-1-layer][matrix.length-1-i];
                matrix[matrix.length-1-layer][matrix.length-1-i] = matrix[matrix.length-1-i][layer];
                matrix[matrix.length-1-i][layer] = tmp;
            }
        }
    }
}







public class Solution {
    public void rotate(int[][] matrix) {
        for(int i=0; i<matrix.length; i++)
        {    
            for(int j=i+1; j<matrix[0].length; j++)
            {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix.length/2; j++)
            {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = tmp;
            }
        }
    }
}

Note: 根据python版改编 code ganker是直接转的 那个也不难 主要搞清楚四个点怎么变换 这里最后reverse array 我用Collections.reverse(Arrays.asList(matrix[i]))不行

不知道为什么 最后手动改的




 1  2  3  4
 5  6  7  8
 9 10 11 12
13 14 15 16


13  9  5  1
14 10  6  2
15 11  7  3
16 12  8  4


public class Solution {
    public void rotate(int[][] matrix) {
        for (int layer = 0; layer < matrix.length / 2; layer++) {
            for (int i = layer; i < matrix.length - 1 - layer; i++) {   // careful i = layer
                int tmp = matrix[layer][i];         // save left top
                
                matrix[layer][i] = matrix[matrix.length-1-i][layer];    // left bottom -> left top
                
                matrix[matrix.length-1-i][layer] = matrix[matrix.length-1-layer][matrix.length-1-i];    // right bottom -> left bottom
                
                matrix[matrix.length-1-layer][matrix.length-1-i] = matrix[i][matrix.length-1-layer];    // right top -> right bottom
                
                matrix[i][matrix.length-1-layer] = tmp;         // left top -> right top
            }
        }
    }
}




from code ganker:

这道题虽然操作起来有一点繁琐，但是思路比较简单，就是考察一下数组的基本操作。基本思路是把图片分为行数/2层，然后一层层进行旋转，

每一层有上下左右四个列，然后目标就是把上列放到右列，右列放到下列，下列放到左列，左列放回上列，中间保存一个临时变量即可。

因为每个元素搬运的次数不会超过一次，时间复杂度是O(矩阵的大小)，空间复杂度是O(1)。代码如下： 

public void rotate(int[][] matrix) {
    if(matrix == null || matrix.length==0 || matrix[0].length==0)
        return;
    int layerNum = matrix.length/2;
    for(int layer=0;layer<layerNum;layer++)
    {
        for(int i=layer;i<matrix.length-layer-1;i++)
        {
            int temp = matrix[layer][i];    //保存左上

            matrix[layer][i] = matrix[matrix.length-1-i][layer];        //左上变为左下

            matrix[matrix.length-1-i][layer] = matrix[matrix.length-1-layer][matrix.length-1-i];    //左下变为右下

            matrix[matrix.length-1-layer][matrix.length-1-i] = matrix[i][matrix.length-1-layer];    //右下变为右上

            matrix[i][matrix.length-1-layer] = temp;        //右上为tmp 即左上
        }
    }
}

这种题目就是思路比较简单，不过实现的时候要细心，容易出错。如果面试遇到了还得谨慎对待，尽量不要出错哈。