public class ValidWordAbbr {

    private Map<String, Set<String>> map;
    
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for(String str : dictionary) {
            String tmp = abbr(str);
            Set<String> set = map.containsKey(tmp)?map.get(tmp):new HashSet<String>();
            set.add(str);
            map.put(tmp, set);
        }
    }

    public boolean isUnique(String word) {
        String tmp = abbr(word);
        return (!map.containsKey(tmp)) 
                || (map.get(tmp).contains(word) && map.get(tmp).size()==1);     // check原字典中相同的词
    }
    
    private String abbr(String str) {
        return str.length()<3?str:str.substring(0,1)+(str.length()-2) 
                                  +str.substring(str.length()-1, str.length());
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");


An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n

Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word abbreviation is unique if no other word 
from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true



map中key是abbr value是一个set 包含所有abbr是key的字符串

https://leetcode.com/discuss/61658/share-my-java-solution