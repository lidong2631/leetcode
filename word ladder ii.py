[leetcode]Word Ladder II @ Python
[leetcode]Word Ladder II @ Python

原题地址：http://oj.leetcode.com/problems/word-ladder-ii/

参考文献：http://blog.csdn.net/doc_sgl/article/details/13341405

　　　　   http://chaoren.is-programmer.com/

题意：给定start单词，end单词，以及一个dict字典。要求找出start到end的所有最短路径，路径上的每个单词都要出现在dict中，且每次改变一个字母。如start="hit"; end="cog"; dict={"hot","dot","dog","lot","log"}，则答案为：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]。这是leetcode oj给的例子。我在实现的时候发现这个例子有点问题：end单词不在dict中。实际的测试用例应该是start和end单词都在dict中的，因为如果提前做一个删除start或者end单词的操作的话，就通不过了。我用正确的程序去测试oj给的这个例子也无法通过，就姑且认为start单词和end单词都在dict中吧，这样写出来的程序才能通过。

Word Ladder II这一道题还是比较难的，是leetcode oj中通过率最低的一道题。而由于我一直在用python来刷题，且一直在网上找不到用python写的解法，自己又写不出来，所以参考了网上的C++解法以及kitt的python程序，在此表示感谢。

 解题关键点：1，这里的dict是python中的set()类型。

　　　　　　 2，使用前驱单词表prevMap，是python中的字典类型。用来记录单词的前驱。比如prevMap={cog:[log, dog]}表示cog的前驱是：log和dog。

　　　　　　 3，在word ladder这道题中我们使用了队列，在word ladder ii中也需要使用队列，只不过在这个程序中使用了两个set()来模拟队列。candidates[previous]中存储的是前面一层的单词。如{dot，lot}为第三层单词。在程序开始执行时，现将前面一层的单词即candidates[previous]中的单词在dict中删除，再将candidates[current]清空，然后根据candidates[previous]中的单词寻找下一层的单词，如{dot，lot}的下一层为{dog，log}，并将{dog，log}存入candidates[current]中，同时将单词存入前驱单词表中。下一次循环开始时，上一次循环的candidates[current]变成了candidates[previous]，而上一次循环的candidates[previous]变成了candidates[current]并清空。如此反复执行，当某一次循环中的candidates[current]中出现了end单词时，说明我们的路径已经找出来了，工作完成了。

　　　　　　 4，程序中使用了一个子函数buildpath来重建每一条路径。如oj给的例子所产生的prevMap={cog:[log,dog], log:[lot], dog:[dot], dot:[hot], lot:[hot], hot:[hit]}，这个prevMap可以使用DFS来重建每一条路径。

源代码：

复制代码
class Solution:
    # @param start, a string
    # @param end, a string
    # @param dict, a set of string
    # @return a list of lists of string
    def findLadders(self, start, end, dict):
        def buildpath(path, word):
            if len(prevMap[word])==0:
                path.append(word); currPath=path[:]
                currPath.reverse(); result.append(currPath)
                path.pop();
                return
            path.append(word)
            for iter in prevMap[word]:
                buildpath(path, iter)
            path.pop()
        
        result=[]
        prevMap={}
        length=len(start)
        for i in dict:
            prevMap[i]=[]
        candidates=[set(),set()]; current=0; previous=1
        candidates[current].add(start)
        while True:
            current, previous=previous, current
            for i in candidates[previous]: dict.remove(i)
            candidates[current].clear()
            for word in candidates[previous]:
                for i in range(length):
                    part1=word[:i]; part2=word[i+1:]
                    for j in 'abcdefghijklmnopqrstuvwxyz':
                        if word[i]!=j:
                            nextword=part1+j+part2
                            if nextword in dict:
                                prevMap[nextword].append(word)
                                candidates[current].add(nextword)
            if len(candidates[current])==0: return result
            if end in candidates[current]: break
        buildpath([], end)
        return result