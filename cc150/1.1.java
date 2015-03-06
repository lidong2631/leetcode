Assume charset is ASCII (remember to mention that!)

public boolean isUniqueChars(String str) {
	HashSet<Character> set = new HashSet<Character>();
	for (int i=0; i<str.length(); i++)
	{
		if(set.contains(str.charAt(i)))
			return false;
		else
			set.add(str.charAt(i));
	}
	return true;
}

Note: O(n), O(n)

2. we can also use brute force O(n^2), O(1)

3. we can sort the string in O(nlogn) and then linearly check the string for duplicates. Careful for some sorting algorithms take up extra space