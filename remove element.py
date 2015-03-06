class Solution:
    # @param    A       a list of integers
    # @param    elem    an integer, value need to be removed
    # @return an integer
    def removeElement(self, A, elem):
        start = 0, newLen = 0
        for i in range(A):
            if A[i] == elem and start == 0:
                continue
            else:
                start = i
                newLen+=1
                break
        if start == 0:
            return 0
        for i in range(start+1, A):
            if A[i] == elem:
                A[i], A[start] = A[start], A[i]
                start+=1
            else:
                newLen+=1
        return newLen