There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.
Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: 
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.




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




Java:
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, sum = 0, res = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            sum += diff;
            total += diff;
            if (sum < 0) {
                sum = 0;
                res = i + 1;
            }
        }
        return total < 0 ? -1 : res;
    }
}


1 如果将全部油量累计起来，总量为正，那么一定能找到一个起点，使得可以走完一圈，也就是一定有解

2 如果一个负序列的起点不能作为起点， 不仅这个负序列的起点不能作为起点，负序列中的任意一点都不能作为起点







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
