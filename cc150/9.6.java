see leetcode search a 2D matrix it has better time complexity O(logm + logn)

below is cc150 solution which has O(m+n) time complexity

public boolean findElem(int[][] matrix, int elem) {
	int row = matrix.length;
	int col = matrix[0].length;

	int i=0;
	int j = col;

	while(i<row && j>=0)
	{
		if(marix[i][j]==elem)
			return true;
		else if(matrix[i][j]>elem)
			j--;
		else i++;
	}
	return false;
}