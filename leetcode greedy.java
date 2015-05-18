Candy
思路类似Trapping Rain Water 左扫一遍先找到左边最小值 然后再右扫一遍比较左右值更新
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