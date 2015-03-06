public int search(String[] strings, String str, int left, int right) {
	if(strings==null || strings.length==0 || str==null || str.length()==0)
		return -1;
	if(str=="")					//注意处理str==""的情况
	{
		for(int i=0; i<strings.length; i++)
		{
			if(strings[i]=="")
				return i;
		}
		return -1;
	}

	while(left<=right)
	{
		while(left<=right && strings[right]=="")
			right--;
		if(right<left)
			return -1;
		int mid = (left+right)/2;
		while(strings[mid]=="")
			mid++;
		int compareRes = strings[mid].compareTo(str);	//字符串这么比较比较好操作
		if(compareRes==0)
			return mid;
		else if(compareRes<0)
			left = mid + 1;
		else
			right = mid - 1;
	}
	return -1;
}

Note: 二分法变体 对字符串的操作 熟练