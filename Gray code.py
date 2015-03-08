<<<<<<< HEAD
class Solution:
    # @return a list of integers
    def grayCode(self, n):
        res = []
        size = 1<<n
        for i in range(size):
            res.append((i>>1)^i)
        return res

=======
class Solution:
    # @return a list of integers
    def grayCode(self, n):
        res = []
        size = 1<<n
        for i in range(size):
            res.append((i>>1)^i)
        return res

>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
Note: 了解格雷码和二进制码的转换