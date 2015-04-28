<<<<<<< HEAD
class Solution:
    # @return a boolean
    def isScramble(self, s1, s2):
        if len(s1)!=len(s2):    #如果s1和s2长度不等 直接返回False不用继续判断了
            return False
        if s1 == s2:        #如果s1和s2相等 返回True 归根结底都要到这步或最后一句return False
            return True
        if ''.join(sorted(s1)) != ''.join(sorted(s2)):  #如果s1和s2排序后不等 就不用判断直接返回False
            return False
        length = len(s1)
        for i in range(1, length):          #循环length次一个一个字符看能否找到一个点i满足以下条件：
            if self.isScramble(s1[:i], s2[:i]) and self.isScramble(s1[i:], s2[i:]): #要么s1[：i]和s2[：i]是scramble且s[i：]和s2[i：]是scramble
                return True
            if self.isScramble(s1[:i], s2[length-i:]) and self.isScramble(s1[i:], s2[:length-i]):   #要么s1[:i],s2[length-i:]是scramble且s[i:]和s2[:length-i]是scramble
                return True         #如果存在这点返回True
        return False            #否则返回False


简单的说，就是s1和s2是scramble的话，那么必然存在一个在s1上的长度l1，将s1分成s11和s12两段，同样有s21和s22。
那么要么s11和s21是scramble的并且s12和s22是scramble的；
要么s11和s22是scramble的并且s12和s21是scramble的。

Note: python排序字符串用sorted(str),sorted()返回一个list 需要再用''.join()将它转换成字符串