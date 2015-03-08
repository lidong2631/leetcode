<<<<<<< HEAD
class Solution:
    # @param A, a list of integer
    # @return an integer
    def singleNumber(self, A):
        answer = A[0]
        for i in range(1, len(A)):
            answer = answer ^ A[i]
        return answer





解题思路：这题考的是位操作。只需要使用异或(xor)操作就可以解决问题。

异或操作的定义为：x ^ 0 = x; x ^ x = 0。用在这道题里面就是：y ^ x ^ x = y; x ^ x = 0; 

举个例子：序列为：1122334556677。4是那个唯一的数，之前的数异或操作都清零了，

之后的数：4 ^ 5 ^ 5 ^ 6 ^ 6 ^ 7 ^ 7 = 4 ^ ( 5 ^ 5 ^ 6 ^ 6 ^ 7 ^ 7 ) = 4 ^ 0 = 4。问题解决。

代码：


class Solution:
    # @param A, a list of integer
    # @return an integer
    def singleNumber(self, A):
        ans = A[0]
        for i in range(1, len(A)):
            ans = ans ^ A[i]
        return ans




public class Solution {
    public int singleNumber(int[] A) {
        if(A==null || A.length==0)
            return 0;
        int answer = A[0];
        for(int i=1; i<A.length; i++)
            answer^=A[i];
        return answer;
    }
}

=======
class Solution:
    # @param A, a list of integer
    # @return an integer
    def singleNumber(self, A):
        answer = A[0]
        for i in range(1, len(A)):
            answer = answer ^ A[i]
        return answer





解题思路：这题考的是位操作。只需要使用异或(xor)操作就可以解决问题。

异或操作的定义为：x ^ 0 = x; x ^ x = 0。用在这道题里面就是：y ^ x ^ x = y; x ^ x = 0; 

举个例子：序列为：1122334556677。4是那个唯一的数，之前的数异或操作都清零了，

之后的数：4 ^ 5 ^ 5 ^ 6 ^ 6 ^ 7 ^ 7 = 4 ^ ( 5 ^ 5 ^ 6 ^ 6 ^ 7 ^ 7 ) = 4 ^ 0 = 4。问题解决。

代码：


class Solution:
    # @param A, a list of integer
    # @return an integer
    def singleNumber(self, A):
        ans = A[0]
        for i in range(1, len(A)):
            ans = ans ^ A[i]
        return ans




public class Solution {
    public int singleNumber(int[] A) {
        if(A==null || A.length==0)
            return 0;
        int answer = A[0];
        for(int i=1; i<A.length; i++)
            answer^=A[i];
        return answer;
    }
}

>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
Note: 只有出现2次可以用这个异或的解法解 更通用的方法看single number ii