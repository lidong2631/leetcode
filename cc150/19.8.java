我们要弄清楚这个method是否会被多次使用

1 如果是被用一次 就只能遍历一遍book 记录word出现的频率 O(n)

2 如果会被多次调用

HashTable<String, Integer> setupHash(String[] book) {
	HashTable<String, Integer> hashtable = new HashTable<String, Integer>();

	for(String s : book)
	{
		s = s.toLowerCase();
		if(s.trim()!="")
		{
			if(hashtable.containsKey(s))
				hashtable.put(s, hashtable.get(s)+1);
			else
				hashtable.put(s, 1);
		}
	}
	return hashtable;
}


int getFrequency(String word, String[] book) {
	if(word==null || book==null)
		return 0;

	word = word.toLowerCase();
	HashTable<String, Integer> dict = setupHash(book);
	if(dict.containsKey(word))
		return dict.get(word);
	return 0;
}