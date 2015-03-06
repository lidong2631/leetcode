class Solution:
    # @return an integer
    def threeSumClosest(self, num, target):
        num.sort()                  #先将序列排序
        minDiff = 10000; res = 0    #minDiff存放最小的差值 res存放最终结果sum
        for i in range(len(num)):       #循环每一个数字
            left = i + 1; right = len(num) - 1  #第一个数字已确定 第二个数字从第一个数右边开始 第三个数从最后一个数开始
            while left < right:                     #内循环 只要第二个数没和第三个数碰到
                sum = num[i] + num[left] + num[right]   #计算三个数和和target的差值
                diff = abs(sum - target)
                if diff < minDiff:      #如果新的差值比minDiff小 更新minDiff和res值
                    minDiff = diff
                    res = sum
                if sum == target:   #如果sum直接等于target 则差值为0 不需要再继续找下去已经得到答案 返回res
                    return res
                elif sum < target:  #否则 如果sum小于target 移动第二个数让它大一点继续计算 sum大于target的话移动第三个数让它取小一点
                    left+=1
                else:
                    right-=1
        return res






题意：数组中每三个元素进行求和，找出所有和中大小最接近target的和，并返回这个和与target之间的差值。

解题思路：使用一个变量mindiff来监测和与target之间的差值，如果差值为0，直接返回sum值。

代码：

复制代码
class Solution:
    # @return an integer
    def threeSumClosest(self, num, target):
        num.sort()
        mindiff=100000
        res=0
        for i in range(len(num)):
            left=i+1; right=len(num)-1
            while left<right:
                sum=num[i]+num[left]+num[right]
                diff=abs(sum-target)
                if diff<mindiff: mindiff=diff; res=sum
                if sum==target: return sum
                elif sum<target: left+=1
                else: right-=1
        return res