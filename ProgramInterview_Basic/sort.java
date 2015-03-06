Bubble Sort:

Pseudocode implementation[edit]
The algorithm can be expressed as (0-based array):

procedure bubbleSort( A : list of sortable items )
   n = length(A)
   repeat     
     swapped = false
     for i = 1 to  n-1 inclusive do
       /* if this pair is out of order */
       if A[i-1] > A[i] then
         /* swap them and remember something changed */
         swap( A[i-1], A[i] )
         swapped = true
       end if
     end for
   until not swapped
end procedure



public void BubbleSort(int[] A) {
	int len = A.length;
	boolean swapped = true;

	while(swapped)
	{
		swapped = false;
		for(int i=1; i<=len-1; i++)
		{
			if(A[i-1]>A[i])
			{	
				swap(A[i-1], A[i]);
				swapped = true;
			}
		}
		len--;
	}
}



Data structure	Array
Worst case performance	O(n^2)
Best case performance	O(n)
Average case performance	O(n^2)
Worst case space complexity	O(1) auxiliary









Selection Sort:


public void SelectionSort(int[] A) {
	int len = A.length;
	int minIndex;

	for(int j=0; j<len-1; j++)
	{
		minIndex = j;
		for(int i=j+1; i<len; i++)
		{
			if(A[i]<A[minIndex])
				minIndex = i;
		}
		if(minIndex!=j)
			swap(A[j], A[minIndex]);
	}
}



Data structure	Array
Worst case performance	О(n2)
Best case performance	О(n2)
Average case performance	О(n2)
Worst case space complexity	О(n) total, O(1) auxiliary









Insertion Sort:

Pseudocode of the complete algorithm follows, where the arrays are zero-based:[2]:116

for i ← 1 to length(A)
    x ← A[i]
    j ← i
    while j > 0 and A[j-1] > x
        A[j] ← A[j-1]
        j ← j - 1
    A[j] ← x


public void InsertionSort(int[] A) {
	int len = A.length;

	for(int i=1; i<len; i++)
	{
		int x = A[i];
		int j = i;
		while(j>0 && A[j-1]>x)
		{
			A[j] = A[j-1];
			j--;
		}
		A[j] = x;
	}
}





Data structure	Array
Worst case performance	О(n2) comparisons, swaps
Best case performance	O(n) comparisons, O(1) swaps
Average case performance	О(n2) comparisons, swaps
Worst case space complexity	О(n) total, O(1) auxiliary









Merge Sort:

TopDownMergeSort(A[], B[], n)
{
    TopDownSplitMerge(A, 0, n, B);
}
 
CopyArray(B[], iBegin, iEnd, A[])
{
    for(k = iBegin; k < iEnd; k++)
        A[k] = B[k];
}
 
// iBegin is inclusive; iEnd is exclusive (A[iEnd] is not in the set)
TopDownSplitMerge(A[], iBegin, iEnd, B[])
{
    if(iEnd - iBegin < 2)                       // if run size == 1
        return;                                 //   consider it sorted
    // recursively split runs into two halves until run size == 1,
    // then merge them and return back up the call chain
    iMiddle = (iEnd + iBegin) / 2;              // iMiddle = mid point
    TopDownSplitMerge(A, iBegin,  iMiddle, B);  // split / merge left  half
    TopDownSplitMerge(A, iMiddle,    iEnd, B);  // split / merge right half
    TopDownMerge(A, iBegin, iMiddle, iEnd, B);  // merge the two half runs
    CopyArray(B, iBegin, iEnd, A);              // copy the merged runs back to A
}
 
//  left half is A[iBegin :iMiddle-1]
// right half is A[iMiddle:iEnd-1   ]
TopDownMerge(A[], iBegin, iMiddle, iEnd, B[])
{
    i0 = iBegin, i1 = iMiddle;
 
    // While there are elements in the left or right runs
    for (j = iBegin; j < iEnd; j++) {
        // If left run head exists and is <= existing right run head.
        if (i0 < iMiddle && (i1 >= iEnd || A[i0] <= A[i1]))
            B[j] = A[i0];
            i0 = i0 + 1;
        else
            B[j] = A[i1];
            i1 = i1 + 1;    }
 
}




public void MergeSort(int[] res, int[] A) {
	return SplitMerge(res, A, 0, A.length-1);
}

public void SplitMerge(int[] res, int[] A, int start, int end) {
	if(start==end)
		return;
	int mid = (start+end)/2;
	SplitMerge(res, A, start, mid);
	SplitMerge(res, A, mid+1, end);
	Merge(res, A, start, mid, end);
}

public void Merge(int[] res, int[] A, int start, int mid, int end) {
	
	int j = 0;
	int middle = mid-1;

	int s = start;
	int n = end-start+1;

	while(start<=middle && mid<=end)
	{
		if(A[start]<A[mid])
			res[j++] = A[start++];
		else
			res[j++] = A[mid++];
	}

	while(start<=middle)
		res[j++] = A[start++];

	while(mid<=end)
		res[j++] = A[mid++];

	for(j=0; j<n; j++)
		A[s+j] = res[j];
}




Data structure	Array
Worst case performance	O(n log n)
Best case performance	
O(n log n) typical,

O(n) natural variant
Average case performance	O(n log n)
Worst case space complexity	O(n) auxiliary









QuickSort:

public void quicksort(int[] A) {

	recQuickSort(A, 0, A.length-1);
}

public void recQuickSort(int[] A, int left, int right) {
	if(left>=right)
		return;
	else
	{
		int pivot = A[right];
		int p = partition(A, left, right, pivot)
		recQuickSort(A, left, p-1);
		recQuickSort(A, p+1, right);
	}
}

public int partition(int[] A, int left, int right, int pivot) {

	int l = left-1;
	int r = right;

	while(true)
	{
		while(A[++l]<pivot)
			;

		while(r>0 && A[--r]>pivot)
			;

		if(l>=r)
			break;
		else
			swap(l, r);
	}
	swap(l, right);
	return l;
}




Worst case performance	O(n2)
Best case performance	O(n log n) (simple partition)
or O(n) (three-way partition and equal keys)
Average case performance	O(n log n)
Worst case space complexity	O(n) auxiliary (naive)
O(log n) auxiliary (Sedgewick 1978)







Heapsort:

Data structure  Array
Worst case performance  O(n\log n)
Best case performance   \Omega(n), O(n\log n)[1]
Average case performance    O(n\log n)
Worst case space complexity O(1) auxiliary










Counting Sort:

public static int[] countingSort(int[] A) {
        int[] B = new int[A.length];
        // 假设A中的数据a'有，0<=a' && a' < k并且k=100
        int k = 100;
        countingSort(A, B, k);
        return B;
    }
 
    private static void countingSort(int[] A, int[] B, int k) {
        int[] C = new int[k];
        // 计数
        for (int j = 0; j < A.length; j++) {
            int a = A[j];
            C[a] += 1;
        }
        Utils.print(C);
        // 求计数和
        for (int i = 1; i < k; i++) {
            C[i] = C[i] + C[i - 1];
        }
        Utils.print(C);
        // 整理
        for (int j = A.length - 1; j >= 0; j--) {
            int a = A[j];
            B[C[a] - 1] = a;
            C[a] -= 1;
        }
    }

由于用来计数的数组C的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），这使得计数排序对于数据范围很大的数组，需要大量时间和内存。

例如：计数排序是用来排序0到100之间的数字的最好的算法，但是它不适合按字母顺序排序人名。但是，计数排序可以用在基数排序中的算法来排序数据范围很大的数组。

通俗地理解，例如有10个年龄不同的人，统计出有8个人的年龄比A小，那A的年龄就排在第9位,用这个方法可以得到其他每个人的位置,也就排好了序。

当然，年龄有重复时需要特殊处理（保证稳定性），这就是为什么最后要反向填充目标数组，以及将每个数字的统计减去1的原因。 算法的步骤如下：

找出待排序的数组中最大和最小的元素

统计数组中每个值为i的元素出现的次数，存入数组C的第i项

对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）

反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
