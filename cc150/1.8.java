public boolean isRotation(String s1, String s2) {
	if(s1.length()!=s2.length())
		return false;
	String s1s2 = s1 + s1;
	return isSubstring(s1s1, s2);
}