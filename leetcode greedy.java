Wildcard Matching




Jump Game
一维动态规划 局部解和全局解 维护一个到目前能到的最大距离和从当前点出发能到的最大距离
if(A==null || A.length==0)
    return false;
int reach = A[0];
for(int i=1; i<A.length&&i<=reach; i++) {
    reach = Math.max(reach, i+A[i]);
}
if(reach<A.length-1)
    return false;
return true;

O(n) O(1)





Jump Game ii
贪心 只要当前lastReach还能满足条件 就不更新 但每次要更新能走到的最远距离reach
if(A==null || A.length<2)
    return 0;
int step = 1, reach = A[0], lastReach = A[0];
for(int i=1; i<A.length&&i<=reach; i++) {
    reach = Math.max(reach, i+A[i]);
    if(lastReach<i) {
        step++;
        lastReach = reach;
    }
}
if(reach<A.length-1)
    return -1;
return step;

O(n) O(1)




Gas Station
从第一站一站站开过去 如果发现没油了 前面所有经过的站都被淘汰 直接从下一站开始重新算 因为这站如果带着前面剩下的油都走不完的话，把它当作起点油更不够了
if(gas==null || gas.length==0 || cost==null || cost.length==0)
    return -1;
int total = 0, sum = 0, p = -1;
for(int i=0; i<gas.length; i++) {
    int diff = gas[i]-cost[i];
    sum+=diff;
    total+=diff;
    if(sum<0) {
        sum = 0;
        p = i;
    }
}
return total<0?-1:p+1;

O(n) O(1)





Candy
思路类似Trapping Rain Water 左扫一遍先找到左边最小值 然后再右扫一遍比较左右值更新 贪心 过程中只要能放1就都放1
int[] candy = new int[ratings.length];
candy[0] = 1;
int res = 0;
for(int i=1; i<ratings.length; i++) {
    if(ratings[i]>ratings[i-1])
        candy[i] = candy[i-1]+1;
    else
        candy[i] = 1;
}
res+=candy[ratings.length-1];
for(int i=ratings.length-2; i>=0; i--) {
    if(ratings[i]>ratings[i+1] && candy[i]<=candy[i+1]) {
        candy[i] = candy[i+1]+1;
        res+=candy[i];
    }
    else
        res+=candy[i];
}
return res;

O(n) O(n)





Best Time to Buy and Sell Stock ii
贪心 只要利润为正 就一直累加
int max = 0;
for(int i=1; i<prices.length; i++) {
    int diff = prices[i]-prices[i-1];
    if(diff>0)
        max+=diff;
}
return max;

O(n) O(1)