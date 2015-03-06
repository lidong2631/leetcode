public void twoSum(int[] arr, int target) {
	if(arr==null || arr.length<2)
		return;
	HashSet<Integer> set = new HashSet<Integer>();

	for(int i=0; i<arr.length; i++)
	{
		if(set.containt(target-arr[i]))
		{
			System.out.println(arr[i], target-arr[i]);
			set.remove(target-arr[i]);
		}
		set.add(arr[i]);
	}
}

Note: 用哈希表的方法要注意是否要去重


public void twoSum(int[] arr, int target) {
	if(arr==null || arr.length<2)
		return;
	Arrays.sort(arr);
	int left = 0;
	int right = arr.length-1;
	while(left<right)
	{
		if(arr[left]+arr[right]==target)
		{
			System.out.println(arr[left], arr[right]);
			left++;
			right--;
			while(left<right&&arr[left]==arr[left-1])
                left++;
            while(left<right&&arr[right]==arr[right+1])
                right--;
		}
		else if(arr[left]+arr[right]>target)
		{
			right--;
		}
		else
		{
			left++;
		}
	}
}