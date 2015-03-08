<<<<<<< HEAD
class Solution:
    # @return a list of lists of integer
    def generateMatrix(self, n):
        if n == 0:
            return []
        res = [[0 for i in range(n)] for j in range(n)]
        up = 0; left = 0; right = n - 1; down = n - 1
        direction = 0
        num = 1
        while(True):
            if direction == 0:
                for i in range(left, right+1):
                    res[up][i] = num
                    num += 1
                up += 1
            
            if direction == 1:
                for i in range(up, down+1):
                    res[i][right] = num
                    num += 1
                right -= 1
            
            if direction == 2:
                for i in range(right, left-1, -1):
                    res[down][i] = num
                    num += 1
                down -= 1
            
            if direction == 3:
                for i in range(down, up-1, -1):
                    res[i][left] = num
                    num += 1
                left += 1
            
            if up>down or left>right:
                return res
            direction = (direction + 1) % 4





public class Solution {
    public int[][] generateMatrix(int n) {
        if(n<0)
            return null;
        int[][] res = new int[n][n];
        int left = 0; int right = n-1; int up = 0; int down = n-1;
        int direction = 0;
        int num = 1;
        while(left<=right && up<=down) {
            if(direction==0) {
                for(int i=left; i<=right; i++) {
                    res[up][i] = num++;
                }
                up++;
            }
            if(direction==1) {
                for(int i=up; i<=down; i++) {
                    res[i][right] = num++;
                }
                right--;
            }
            if(direction==2) {
                for(int i=right; i>=left; i--) {
                    res[down][i] = num++;
                }
                down--;
            }
            if(direction==3) {
                for(int i=down; i>=up; i--) {
                    res[i][left] = num++;
                }
                left++;
            }
            direction = (direction+1)%4;
        }
        return res;
    }
}

第二遍写的 注意没歌if语句最后57行 不可以写continue 不然direction就无法＋1变方向了








public class Solution {
    public int[][] generateMatrix(int n) {
        if(n<0)
            return null;
        int[][] res = new int[n][n];
        int direction = 0;
        int left=0; int right = n-1; int up = 0; int down = n-1;
        int num = 1;
        while(true)
        {
            if(direction==0)
            {
                for(int i=left; i<right+1; i++)
                {
                    res[up][i] = num;
                    num+=1;
                }
                up++;
            }
            if(direction==1)
            {
                for(int i=up; i<down+1; i++)
                {
                    res[i][right] = num;
                    num+=1;
                }
                right--;
            }
            if(direction==2)
            {
                for(int i=right; i>=left; i--)
                {
                    res[down][i] = num;
                    num+=1;
                }
                down--;
            }
            if(direction==3)
            {
                for(int i=down; i>=up; i--)
                {
                    res[i][left] = num;
                    num+=1;
                }
                left++;
            }
            if(up>down || left>right)
                return res;
            direction = (direction+1)%4;
        }
    }
}

=======
class Solution:
    # @return a list of lists of integer
    def generateMatrix(self, n):
        if n == 0:
            return []
        res = [[0 for i in range(n)] for j in range(n)]
        up = 0; left = 0; right = n - 1; down = n - 1
        direction = 0
        num = 1
        while(True):
            if direction == 0:
                for i in range(left, right+1):
                    res[up][i] = num
                    num += 1
                up += 1
            
            if direction == 1:
                for i in range(up, down+1):
                    res[i][right] = num
                    num += 1
                right -= 1
            
            if direction == 2:
                for i in range(right, left-1, -1):
                    res[down][i] = num
                    num += 1
                down -= 1
            
            if direction == 3:
                for i in range(down, up-1, -1):
                    res[i][left] = num
                    num += 1
                left += 1
            
            if up>down or left>right:
                return res
            direction = (direction + 1) % 4





public class Solution {
    public int[][] generateMatrix(int n) {
        if(n<0)
            return null;
        int[][] res = new int[n][n];
        int left = 0; int right = n-1; int up = 0; int down = n-1;
        int direction = 0;
        int num = 1;
        while(left<=right && up<=down) {
            if(direction==0) {
                for(int i=left; i<=right; i++) {
                    res[up][i] = num++;
                }
                up++;
            }
            if(direction==1) {
                for(int i=up; i<=down; i++) {
                    res[i][right] = num++;
                }
                right--;
            }
            if(direction==2) {
                for(int i=right; i>=left; i--) {
                    res[down][i] = num++;
                }
                down--;
            }
            if(direction==3) {
                for(int i=down; i>=up; i--) {
                    res[i][left] = num++;
                }
                left++;
            }
            direction = (direction+1)%4;
        }
        return res;
    }
}

第二遍写的 注意没歌if语句最后57行 不可以写continue 不然direction就无法＋1变方向了








public class Solution {
    public int[][] generateMatrix(int n) {
        if(n<0)
            return null;
        int[][] res = new int[n][n];
        int direction = 0;
        int left=0; int right = n-1; int up = 0; int down = n-1;
        int num = 1;
        while(true)
        {
            if(direction==0)
            {
                for(int i=left; i<right+1; i++)
                {
                    res[up][i] = num;
                    num+=1;
                }
                up++;
            }
            if(direction==1)
            {
                for(int i=up; i<down+1; i++)
                {
                    res[i][right] = num;
                    num+=1;
                }
                right--;
            }
            if(direction==2)
            {
                for(int i=right; i>=left; i--)
                {
                    res[down][i] = num;
                    num+=1;
                }
                down--;
            }
            if(direction==3)
            {
                for(int i=down; i>=up; i--)
                {
                    res[i][left] = num;
                    num+=1;
                }
                left++;
            }
            if(up>down || left>right)
                return res;
            direction = (direction+1)%4;
        }
    }
}

>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
Note: 跟i一样 python版改编 比code ganker版好理解