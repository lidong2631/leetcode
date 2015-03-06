1 Is string a palindrome?

public boolean isPalindrome(String str) {
	int length = str.length();

	for(int i=0; i<length/2; i++)
	{
		if(str.charAt(i)!=str.charAt(length-1-i))
			return false;
	}
	return true;
}





2 Java factorial

public int factorial(int n) {

	int fac = 1;
	for(itn i=n; i>1; i--)
		fac*=i;
	return fac;
}



public int factorial(int n) {
	if(n==1)
		return 1;
	else
		return n*factorial(n-1);
}





3 swap numbers without temp




4 Find max without comparison




5 Find continous sequences with largest sum

int maxSubArraySum(int a[], int size)
{
   int global_max = a[0];
   int curr_max = a[0];
 
   for(int i=1; i<size; i++)
   {
        curr_max = Math.max(a[i], curr_max+a[i]);            //global solution and local solution
        global_max = Math.max(global_max, curr_max);
   }
   return global_max;
}


int maxSubArraySum(int[] a) {

	int currSum = 0;
	int global_max = 0;

	for(int i=0; i<a.length; i++) {
		currSum+=a[i];
		if(global_max<currSum)
			global_max = currSum;
		else if(currSum<0)
			currSum = 0;
	}
	return global_max;
}





6 permutation of a string

public void permute(String str) {

	boolean[] used = new boolean[str.length()];
	StringBuffer sb = new StringBuffer();
	char[] arr = str.toCharArray();

	helper(arr, sb, used, str.length(), 0);
}

public void helepr(char[] arr, StringBuffer sb, boolean[] used, int length, int level) {
	if(level==length)
	{
		System.out.println(sb.toString());
		return;
	}

	for(int i=0; i<length; i++)
	{
		if(used[i])
			continue;
		sb.append(arr[i]);
		used[i] = true;
		helper(arr, sb, used, length; level++);
		used[i] = false;
		sb.setLength(sb.length()-1);
	}
}