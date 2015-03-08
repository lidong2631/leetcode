<<<<<<< HEAD
class Solution:
    # @param gas, a list of integers
    # @param cost, a list of integers
    # @return an integer
    def canCompleteCircuit(self, gas, cost):
        if sum(gas) < sum(cost):                #首先判断是否有解 若sum(gas)<sum(cost)无解 否则一定有解
            return -1
        extraGas = 0; startIndex = 0            #extraGas为上一站剩下的油量 startIndex为起始站
        for i in range(len(gas)):               #循环遍历一遍环路
            if extraGas + gas[i] < cost[i]:     #如果油量不足以支撑到下一站 以下一站为起点 重置extraGas
                startIndex = i + 1
                extraGas = 0
            else:                               #否则计算extraGas 继续下一轮循环
                extraGas += gas[i] - cost[i]
        return startIndex

Note: 此题先判断是否有解 如果有解则循环 当碰到油量为负数 跳到下一站继续循环 并以下一站为起点 如果有解则只可能有一个地方油量为负数
就是在最后一站 所以以最后一站的下一站为起点即可 因到最后一站环路已经完成 即使从最后一站到不了起点也没事
前面的循环已证明从某一起点到油量为负数的站的前一站都可以走过 所以只要从下一站为起点开始即可



思路1：通过两层循环，依次从某个点出发并测试是否能够运行一圈。时间复杂度为O（n2），不满足要求。

思路2：首先确认gas总和大于cost，因此判断能够绕圈。接下来寻找起始位置，我们可以借鉴归并排序的思路，如果某一段路gas>cost，

则这段路剩余的油量可以支撑其他路段。因此问题变化为找到某个节点，在它之前的路段剩余油量为负，而从它开始到整个队列结束剩余油量为正（正油量可以不足前面路段的不足油量）。

时间可以在O（n）完成。



下面的代码用两个循环 不满足要求 time limit exceed
class Solution:
    # @param gas, a list of integers
    # @param cost, a list of integers
    # @return an integer
    def canCompleteCircuit(self, gas, cost):
        for startIndex in range(len(gas)):
            extraGas = 0
            for i in range(len(gas)):
                if extraGas + gas[i] - cost[i] < 0:
                    break
                extraGas = extraGas + gas[i] - cost[i]
                if i == len(gas)-1:
                    return startIndex
        return -1



题意：

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

解题思路：这道题也很tricky，自己想是很难想出来的。如果sum(gas)<sum(cost)的话，那么一定无解。diff是走完一站邮箱剩下的油，如果加上gas[i]也到不了下一站，那么继续将下一站设置为起点，然后再检查，是不是很巧妙呢？

代码：

复制代码
class Solution:
    # @param gas, a list of integers
    # @param cost, a list of integers
    # @return an integer
    def canCompleteCircuit(self, gas, cost):
        if sum(gas) < sum(cost): return -1
        n = len(gas)
        diff = 0
        stationIndex = 0
        for i in range(n):
            if gas[i]+diff < cost[i]: stationIndex = i+1; diff = 0
            else: diff += gas[i]-cost[i]
        return stationIndex






public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas==null || gas.length==0|| cost==null || cost.length==0)
            return -1;
        int sum = 0;
        int total = 0;
        int pointer = -1;
        for(int i=0; i<gas.length; i++)
        {
            int extraGas = gas[i] - cost[i];
            sum+=extraGas;      //sum判断当前序列是不是解
            total+=extraGas;    //total判断有无解
            if(sum<0)   //如果sum小于0 则以当前点为起点的序列所有点都不能做起点 sum=0 point = i
            {
                sum= 0;
                pointer = i;
            }
        }
        return total<0? -1:pointer+1;   //如果total为负则无解返回-1 否则返回pointer+1
    }
}

Note: 这题就两点： 

1 如果将全部油量累计起来，总量为正，那么一定能找到一个起点，使得可以走完一圈，也就是一定有解

2 如果一个负序列的起点不能作为起点， 不仅这个负序列的起点不能作为起点，负序列中的任意一点都不能作为起点

这题更像数学证明题 看看就好






from code ganker:

这是一道具体问题的题目，brute force的方法比较容易想到，就是从每一个站开始，一直走一圈，累加过程中的净余的油量，看它是不是有出现负的，

如果有则失败，从下一个站开始重新再走一圈；如果没有负的出现，则这个站可以作为起始点，成功。可以看出每次需要扫描一圈，对每个站都要做一次扫描，

所以时间复杂度是O(n^2)。代码比较直接，这里就不列举了。

接下来说说如何提高这个算法。方法主要思想是把这个圈划分成一个个的负序列，以及一个正序列（如果存在的话）。从任意一个站出发，我们可以累加油的净余量，

如果出现负的，序列结束，开启一个新的，并且证明旧的这个序列的起点不能作为起点，因为会出现负油量，不能继续前进。下面我们证明

不仅这个负序列的起点不能作为起点，负序列中的任意一点都不能作为起点。

证明：假设我们取定负序列中的一个站作为起点，因为一个序列一旦遇到负的净余量就会结束并且开启新的，那么说明在这个起点前的累加结果必然是正数（否则会结束这个序列，

则前面不会是这个序列的一部分）。如此我们从当前序列出发必然会使走到序列终点时负的油量更大，本来已经是负的，所以不能去负序列的任意一个结点作为起点。

根据上面的划分方式，我们会把圈分成一段段的序列，而且其中最多只有一个正序列，那就是绕一圈回到起点的那个序列（当然也有可能整个圈是一个正序列，就是油量一直为正，

那么我们测的开始点就可以作为起点了）。接下来我们证明

如果将全部油量累计起来，总量为正，那么一定能找到一个起点，使得可以走完一圈，也就是一定有解。

证明：按照我们之前的划分，整个圈会被划分成有累积量为s1, s2, ..., sk 的负序列，以及一个正序列拥有油量sp（这里正序列一定存在因为全部累加和是正的，

如果全是负序列那么结果不会是正的）。而且我们知道s1+s2+...+sk+sp>0，也就是说sp>-s1-s2-...-sk。换句话说，如果我们从sp对应的站的起点出发，

在sp对应的序列会一直是正的，并且，当他走到负序列时，因为sp的正油量大于所有负油量的总和，所以累加油量会一直正，完整整个圈的行驶。这证明了只要累加油量是正的，

一定能找到一个起点来完成任务。

根据上面的两个命题，我们可以来实现代码，需要维护两个量，一个是总的累积油量total，另一个是当前序列的累计油量sum，如果出现负的，则切换起点，

并且将sum置0。总共是需要扫描所有站一次，时间复杂度是O(n)。而只需要两个额外变量，空间复杂度是O(1)。代码如下：

public int canCompleteCircuit(int[] gas, int[] cost) {
    if(gas==null || gas.length==0 || cost==null || cost.length==0 || gas.length!=cost.length)
        return -1;
    int sum = 0;
    int total = 0;
    int pointer = -1;
    for(int i=0;i<gas.length;i++)
    {
        int diff = gas[i]-cost[i];
        sum += diff;
        total += diff;
        if(sum<0)
        {
            sum=0;
            pointer=i;
        }
    }
    return total>=0?pointer+1:-1;
}

这个题目的优化解法更像是一个数学题，需要定义数学模型并证明命题正确性，比较需要数学逻辑的功底。通过定义的模型以及证明的命题来做一部分贪心
=======
class Solution:
    # @param gas, a list of integers
    # @param cost, a list of integers
    # @return an integer
    def canCompleteCircuit(self, gas, cost):
        if sum(gas) < sum(cost):                #首先判断是否有解 若sum(gas)<sum(cost)无解 否则一定有解
            return -1
        extraGas = 0; startIndex = 0            #extraGas为上一站剩下的油量 startIndex为起始站
        for i in range(len(gas)):               #循环遍历一遍环路
            if extraGas + gas[i] < cost[i]:     #如果油量不足以支撑到下一站 以下一站为起点 重置extraGas
                startIndex = i + 1
                extraGas = 0
            else:                               #否则计算extraGas 继续下一轮循环
                extraGas += gas[i] - cost[i]
        return startIndex

Note: 此题先判断是否有解 如果有解则循环 当碰到油量为负数 跳到下一站继续循环 并以下一站为起点 如果有解则只可能有一个地方油量为负数
就是在最后一站 所以以最后一站的下一站为起点即可 因到最后一站环路已经完成 即使从最后一站到不了起点也没事
前面的循环已证明从某一起点到油量为负数的站的前一站都可以走过 所以只要从下一站为起点开始即可



思路1：通过两层循环，依次从某个点出发并测试是否能够运行一圈。时间复杂度为O（n2），不满足要求。

思路2：首先确认gas总和大于cost，因此判断能够绕圈。接下来寻找起始位置，我们可以借鉴归并排序的思路，如果某一段路gas>cost，

则这段路剩余的油量可以支撑其他路段。因此问题变化为找到某个节点，在它之前的路段剩余油量为负，而从它开始到整个队列结束剩余油量为正（正油量可以不足前面路段的不足油量）。

时间可以在O（n）完成。



下面的代码用两个循环 不满足要求 time limit exceed
class Solution:
    # @param gas, a list of integers
    # @param cost, a list of integers
    # @return an integer
    def canCompleteCircuit(self, gas, cost):
        for startIndex in range(len(gas)):
            extraGas = 0
            for i in range(len(gas)):
                if extraGas + gas[i] - cost[i] < 0:
                    break
                extraGas = extraGas + gas[i] - cost[i]
                if i == len(gas)-1:
                    return startIndex
        return -1



题意：

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

解题思路：这道题也很tricky，自己想是很难想出来的。如果sum(gas)<sum(cost)的话，那么一定无解。diff是走完一站邮箱剩下的油，如果加上gas[i]也到不了下一站，那么继续将下一站设置为起点，然后再检查，是不是很巧妙呢？

代码：

复制代码
class Solution:
    # @param gas, a list of integers
    # @param cost, a list of integers
    # @return an integer
    def canCompleteCircuit(self, gas, cost):
        if sum(gas) < sum(cost): return -1
        n = len(gas)
        diff = 0
        stationIndex = 0
        for i in range(n):
            if gas[i]+diff < cost[i]: stationIndex = i+1; diff = 0
            else: diff += gas[i]-cost[i]
        return stationIndex






public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas==null || gas.length==0|| cost==null || cost.length==0)
            return -1;
        int sum = 0;
        int total = 0;
        int pointer = -1;
        for(int i=0; i<gas.length; i++)
        {
            int extraGas = gas[i] - cost[i];
            sum+=extraGas;      //sum判断当前序列是不是解
            total+=extraGas;    //total判断有无解
            if(sum<0)   //如果sum小于0 则以当前点为起点的序列所有点都不能做起点 sum=0 point = i
            {
                sum= 0;
                pointer = i;
            }
        }
        return total<0? -1:pointer+1;   //如果total为负则无解返回-1 否则返回pointer+1
    }
}

Note: 这题就两点： 

1 如果将全部油量累计起来，总量为正，那么一定能找到一个起点，使得可以走完一圈，也就是一定有解

2 如果一个负序列的起点不能作为起点， 不仅这个负序列的起点不能作为起点，负序列中的任意一点都不能作为起点

这题更像数学证明题 看看就好






from code ganker:

这是一道具体问题的题目，brute force的方法比较容易想到，就是从每一个站开始，一直走一圈，累加过程中的净余的油量，看它是不是有出现负的，

如果有则失败，从下一个站开始重新再走一圈；如果没有负的出现，则这个站可以作为起始点，成功。可以看出每次需要扫描一圈，对每个站都要做一次扫描，

所以时间复杂度是O(n^2)。代码比较直接，这里就不列举了。

接下来说说如何提高这个算法。方法主要思想是把这个圈划分成一个个的负序列，以及一个正序列（如果存在的话）。从任意一个站出发，我们可以累加油的净余量，

如果出现负的，序列结束，开启一个新的，并且证明旧的这个序列的起点不能作为起点，因为会出现负油量，不能继续前进。下面我们证明

不仅这个负序列的起点不能作为起点，负序列中的任意一点都不能作为起点。

证明：假设我们取定负序列中的一个站作为起点，因为一个序列一旦遇到负的净余量就会结束并且开启新的，那么说明在这个起点前的累加结果必然是正数（否则会结束这个序列，

则前面不会是这个序列的一部分）。如此我们从当前序列出发必然会使走到序列终点时负的油量更大，本来已经是负的，所以不能去负序列的任意一个结点作为起点。

根据上面的划分方式，我们会把圈分成一段段的序列，而且其中最多只有一个正序列，那就是绕一圈回到起点的那个序列（当然也有可能整个圈是一个正序列，就是油量一直为正，

那么我们测的开始点就可以作为起点了）。接下来我们证明

如果将全部油量累计起来，总量为正，那么一定能找到一个起点，使得可以走完一圈，也就是一定有解。

证明：按照我们之前的划分，整个圈会被划分成有累积量为s1, s2, ..., sk 的负序列，以及一个正序列拥有油量sp（这里正序列一定存在因为全部累加和是正的，

如果全是负序列那么结果不会是正的）。而且我们知道s1+s2+...+sk+sp>0，也就是说sp>-s1-s2-...-sk。换句话说，如果我们从sp对应的站的起点出发，

在sp对应的序列会一直是正的，并且，当他走到负序列时，因为sp的正油量大于所有负油量的总和，所以累加油量会一直正，完整整个圈的行驶。这证明了只要累加油量是正的，

一定能找到一个起点来完成任务。

根据上面的两个命题，我们可以来实现代码，需要维护两个量，一个是总的累积油量total，另一个是当前序列的累计油量sum，如果出现负的，则切换起点，

并且将sum置0。总共是需要扫描所有站一次，时间复杂度是O(n)。而只需要两个额外变量，空间复杂度是O(1)。代码如下：

public int canCompleteCircuit(int[] gas, int[] cost) {
    if(gas==null || gas.length==0 || cost==null || cost.length==0 || gas.length!=cost.length)
        return -1;
    int sum = 0;
    int total = 0;
    int pointer = -1;
    for(int i=0;i<gas.length;i++)
    {
        int diff = gas[i]-cost[i];
        sum += diff;
        total += diff;
        if(sum<0)
        {
            sum=0;
            pointer=i;
        }
    }
    return total>=0?pointer+1:-1;
}

这个题目的优化解法更像是一个数学题，需要定义数学模型并证明命题正确性，比较需要数学逻辑的功底。通过定义的模型以及证明的命题来做一部分贪心
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
