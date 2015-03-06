public class AnagramComparator implements Comparator<String> {
	public String sortChars(String s) {
		char[] content = s.toCharArray();
		Array.sort(content);
		return new String(content);
	}

	public int compare(String s1, String s2) {
		return sortChars(s1).compareTo(sortChars(s2));
	}
}

Array.sort(array, new AnagramComparator());


Note: 这题扩展看leetcode anagram

String to char[]: s.toCharArray()

char[] to String: new String(char[]) or String.valueOf(char[])